package com.example.hustle4work.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hustle4work.R;
import com.example.hustle4work.adapter.AppliedUserAdapter;
import com.example.hustle4work.adapter.jobEmpAdapter;
import com.example.hustle4work.model.Job;
import com.example.hustle4work.model.JobFeed;
import com.example.hustle4work.model.LoginResponse;
import com.example.hustle4work.utility.CSPreferences;
import com.example.hustle4work.utility.CheckInternetConnection;
import com.example.hustle4work.utility.WebApiCall;

public class JobDesc extends AppCompatActivity {


    TextView tv_jobtype,appliedUse_txt,tvRatepay,tv_Date,tv_locati,tv_requ,tv_desctitle,desctv ,tv_applied;
    ImageView back_desc;

    RecyclerView recycler_view_Emp;

    CardView cv_applied;

    String jobid ;
    AppliedUserAdapter appliedUserAdapter;

    CheckInternetConnection checkInternetConnection;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_job_descr);
        checkInternetConnection = new CheckInternetConnection();
        tv_jobtype  = findViewById(R.id.tv_typejob);
        tvRatepay = findViewById(R.id.tvRatepay);
        tv_Date= findViewById(R.id.tv_Date);
        tv_locati= findViewById(R.id.tv_locati);
        tv_requ= findViewById(R.id.tv_requ);
        tv_desctitle =findViewById(R.id.tv_desctitle);
        desctv=findViewById(R.id.desctv);
        back_desc=findViewById(R.id.back_desc);
        tv_applied =findViewById(R.id.tvapplied);
        recycler_view_Emp= findViewById(R.id.recycler_view_Emp);
        appliedUse_txt =findViewById(R.id.appliedUse_txt);

        cv_applied =findViewById(R.id.cv_applied);




        // Retrieve the JobFeed data from the Intent
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("jobFeed")) {
            cv_applied.setVisibility(View.VISIBLE);
            recycler_view_Emp.setVisibility(View.GONE);
            appliedUse_txt.setVisibility(View.GONE);
            JobFeed jobFeed = intent.getParcelableExtra("jobFeed");
            tv_jobtype.setText(jobFeed.getJobType());
            tvRatepay.setText(jobFeed.getPayRate());
            tv_Date.setText(jobFeed.getUploadDate());
            tv_locati.setText(jobFeed.getLocation());
            tv_requ.setText(jobFeed.getRequirements());
            tv_desctitle.setText(jobFeed.getTitle());
            desctv.setText(jobFeed.getDescription());
            jobid = jobFeed.get_id();



        }


        cv_applied =findViewById(R.id.cv_applied);
        // Retrieve the JobFeed data from the Intent

        if (intent != null && intent.hasExtra("jobEmpFeed")) {

            cv_applied.setVisibility(View.GONE);
            appliedUse_txt.setVisibility(View.VISIBLE);

            Job jobFeed = intent.getParcelableExtra("jobEmpFeed");
            tv_jobtype.setText(jobFeed.getJobType());
            tvRatepay.setText(jobFeed.getPayRate());
            tv_Date.setText(jobFeed.getUploadDate());
            tv_locati.setText(jobFeed.getLocation());
            tv_requ.setText(jobFeed.getRequirements());
            tv_desctitle.setText(jobFeed.getTitle());
            desctv.setText(jobFeed.getDescription());


            if(jobFeed.getAppliedUsers().size()>0) {
                recycler_view_Emp.setVisibility(View.VISIBLE);
                recycler_view_Emp.setLayoutManager(new LinearLayoutManager(JobDesc.this));
                recycler_view_Emp.setItemAnimator(new DefaultItemAnimator());
                // Create and set the adapter
                appliedUserAdapter = new AppliedUserAdapter(JobDesc.this, jobFeed.getAppliedUsers());
                recycler_view_Emp.setAdapter(appliedUserAdapter);
            }
            else{
                recycler_view_Emp.setVisibility(View.GONE);
                appliedUse_txt.setVisibility(View.GONE);
            }


        }


        LoginResponse retrievedModel = CSPreferences.readModel(this, "myModelKey", LoginResponse.class);

        cv_applied.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tv_applied.getText().equals("Applied")){
                  showToast(" You Already applied this Job.");
                }
                else {
                    tv_applied.setText("Applied");
                    callDescApi(retrievedModel.getUsername(), retrievedModel.getEmail(), retrievedModel.getPhoneno(), retrievedModel.getAddress() ,jobid);
                }

            }
        });





        back_desc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
     }

    private void callDescApi(String username, String email, String phoneNumber, String address, String jobid) {

        if (checkInternetConnection.isInternetAvailable(this)) {
            WebApiCall webApiCall = new WebApiCall(JobDesc.this);
            webApiCall.Apply(username, email, phoneNumber, address ,jobid);
        }else {
            showToast("No Internet Connection Available");
        }





    }
    private void showToast(String message) {
        Toast.makeText(JobDesc.this, message, Toast.LENGTH_SHORT).show();
    }

}
