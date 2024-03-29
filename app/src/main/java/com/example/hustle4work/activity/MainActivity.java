package com.example.hustle4work.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hustle4work.R;
import com.example.hustle4work.adapter.NavigationAdapter;
import com.example.hustle4work.adapter.RecyclerItemClickListener;
import com.example.hustle4work.fragment.About;
import com.example.hustle4work.fragment.EmployerJObFeed;
import com.example.hustle4work.fragment.JobsFeed;
import com.example.hustle4work.fragment.ProfileFragment;
import com.example.hustle4work.model.NavigationModel;
import com.example.hustle4work.model.SignupData;
import com.example.hustle4work.utility.CSPreferences;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener  {

    FragmentManager fm;
    FragmentTransaction fragmentTransaction;

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    NavigationAdapter navigationAdapter;
    private Toolbar toolbar;

    private List<NavigationModel> navigationModels = new ArrayList<>();
    private ImageView customIcon;

    TextView header_toolbar,textView_nav;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        drawerLayout = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.toolbarr);

        header_toolbar =findViewById(R.id.header_toolbar);

        if(CSPreferences.readString(MainActivity.this,"Employer").equalsIgnoreCase("0")){

        }


        loadFragment("JobsFeed");
        CSPreferences.putBolean(MainActivity.this ,"login",true);

        NavigationView navigationView = findViewById(R.id.nav_view);
         navigationView.setNavigationItemSelectedListener(MainActivity.this);

       // View headerView = navigationView.getHeaderView(0);
        textView_nav=findViewById(R.id.tv_navbar);



        header_toolbar.setText("Job Feed");


        // Your ImageView in the Toolbar
        customIcon = findViewById(R.id.custom_icon);



        // Setting up the ActionBarDrawerToggle with the DrawerLayout
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();






        RecyclerView recyclerView = findViewById(R.id.nav_recyclerview);
       recyclerView.setLayoutManager(new LinearLayoutManager(this));

        navigationAdapter = new NavigationAdapter(this, navigationModels);
        recyclerView.setAdapter(navigationAdapter);
        prepareAlbums();



        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        if (position == 0){

                            loadFragment("JobsFeed");
                            header_toolbar.setText("Job Feed");
                        }else if (position == 1){
                            loadFragment("Profile");
                            header_toolbar.setText("Profile");
                        }else if (position == 2){
                            loadFragment("About");
                            header_toolbar.setText("About");
                        }else if(position == 3) {
                            CSPreferences.putBolean(MainActivity.this,"login",false);
                            Intent i = new Intent(MainActivity.this,LoginActivity.class);
                            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(i);
                        }
                        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                        drawer.closeDrawer(GravityCompat.START);


                    }



                }));

        customIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    drawerLayout.openDrawer(GravityCompat.START);

            }
        });





    }

    private void prepareAlbums() {
        int[] covers = new int[]{
                R.drawable.portfolio,
                R.drawable.user_bottom,
                R.drawable.about,
                R.drawable.logout
        };

        NavigationModel a = new NavigationModel("Job Feed", covers[0]);
        navigationModels.add(a);

        a = new NavigationModel("Profile", covers[1]);
        navigationModels.add(a);


        a = new NavigationModel("About", covers[2]);
        navigationModels.add(a);
        a = new NavigationModel("Logout ", covers[3]);
        navigationModels.add(a);


       // navigationAdapter.notifyDataSetChanged();

    }

    private void loadFragment(String fragmentName) {
        Fragment fragment = null;

        switch (fragmentName) {
            case "JobsFeed":
                fragment = new JobsFeed();

                break;
            case "Profile":
                fragment = new ProfileFragment();
                break;
            case "About":
                fragment = new About();
                break;

        }

        if (fragment != null) {
            startFragmentTransaction(fragment);
        }
    }

    private void startFragmentTransaction(Fragment fragment) {
        fm = getSupportFragmentManager();
        fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.addToBackStack(null); // If you want to add the fragments to the back stack
        fragmentTransaction.commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}