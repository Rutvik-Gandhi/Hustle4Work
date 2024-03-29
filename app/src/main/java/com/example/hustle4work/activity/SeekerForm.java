package com.example.hustle4work.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hustle4work.Interface.SignInInterface;
import com.example.hustle4work.R;
import com.example.hustle4work.model.LoginResponse;
import com.example.hustle4work.utility.CSPreferences;
import com.example.hustle4work.utility.CheckInternetConnection;
import com.example.hustle4work.utility.WebApiCall;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;

public class SeekerForm extends AppCompatActivity implements SignInInterface {


    EditText  editEduaction  ;
    TextView btnSave,editSkills,tvExperi,editUser , editemail ,editphone ,editaddress;
    CardView cv_skill ,cv_exp;
    private List<String> stringExperience = new ArrayList<>();
    private List<String> stringSkill = new ArrayList<>();
    ImageView back_seeker;
    CheckInternetConnection checkInternetConnection;
       @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seeker_form);
        editUser = findViewById(R.id.editUser);
        checkInternetConnection = new CheckInternetConnection();
        editemail = findViewById(R.id.editEmail);
        editphone = findViewById(R.id.editphone);
        editaddress = findViewById(R.id.editaddress);
        cv_exp= findViewById(R.id.cv_exp);
        cv_skill = findViewById(R.id.cv_skill);
        editEduaction = findViewById(R.id.editEdu);
        editSkills = findViewById(R.id.editSkil);
        tvExperi = findViewById(R.id.tvExperi);
        btnSave = findViewById(R.id.btnSave);
        back_seeker = findViewById(R.id.back_seeker);


           LoginResponse retrievedModel = CSPreferences.readModel(this, "myModelKey", LoginResponse.class);

           editUser.setText(retrievedModel.getUsername());
           editphone.setText("4373294436");
           editaddress.setText(retrievedModel.getAddress());
           editemail.setText(retrievedModel.getEmail());
           // Use the retrieved data as needed
           //Log.d("YourActivity", "Name: " + retrievedModel.getName() + ", Age: " + retrievedModel.getAge());




               editUser.setFocusable(false);
               editemail.setFocusable(false);
               editphone.setFocusable(false);
               editaddress.setFocusable(false);



           back_seeker.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   Intent i = new Intent(SeekerForm.this ,LoginActivity.class);
                   i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                   startActivity(i);
                   finish();
               }
           });



           btnSave.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   CSPreferences.putString(SeekerForm.this, "Seeker","1");
                   validateFields();

               }
           });

           cv_exp.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   showBottomSheetDialog("Add Experiences" ,"Experience");
               }
           });

           cv_skill.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   showBottomSheetDialog("Add Skills","Skill");
               }
           });


    }



      public void showBottomSheetDialog(String add, String skill) {
        // Inflate the custom view for the bottom sheet
        View viewInflated = LayoutInflater.from(this).inflate(R.layout.custom_bottom_sheet_dialog, null);

        // Find the EditText fields in the inflated view

         TextView tv_header  = viewInflated.findViewById(R.id.tv_header_bs);
          TextView   tv_skill= viewInflated.findViewById(R.id.tv_skill);
         EditText editText1 = viewInflated.findViewById(R.id.editskill);
          tv_header.setText(add);
          tv_skill.setText(skill);



        // Create a BottomSheetDialog with the inflated view
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(viewInflated);

        // Set up the buttons
        viewInflated.findViewById(R.id.editSave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the user input from the EditText fields
                String newText = editText1.getText().toString();
                if(skill.equals("Experience")){

                    // Add the text to the list
                    stringExperience.add(newText);

                    // Update the TextView with the list contents
                    updateListTextView(stringExperience,"Experience");
                    // Dismiss the bottom sheet dialog
                }
                else{

                    stringSkill.add(newText);

                    // Update the TextView with the list contents
                    updateListTextView(stringSkill,"skill");
                }

                bottomSheetDialog.dismiss();
            }
        });

        viewInflated.findViewById(R.id.tvClose).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Dismiss the bottom sheet dialog
                bottomSheetDialog.dismiss();
            }
        });
        viewInflated.findViewById(R.id.closeIcon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Dismiss the bottom sheet dialog
                bottomSheetDialog.dismiss();
            }
        });

        // Show the bottom sheet dialog
        bottomSheetDialog.show();
    }


    private void updateListTextView(List<String> stringData, String data) {
        // Build the string to display in the TextView
        StringBuilder stringBuilder = new StringBuilder();
        for (String item : stringData) {
            stringBuilder.append(item).append(",").append("");
        }

        // Set the string to the TextView
        if(data.equals("Experience")){
            tvExperi.setText(stringBuilder.toString());
            Log.e("checkstringdata",editSkills.getText().toString());
        }else{

            editSkills.setText(stringBuilder.toString());
        }



    }

    private void validateFields() {
        boolean isValid = true;


        String username = editUser.getText().toString();
        String email = editemail.getText().toString();
        String phoneNumber = editphone.getText().toString();
        String address = editaddress.getText().toString();
        String education = editEduaction.getText().toString();
        String skills = editSkills.getText().toString();
        String experience = tvExperi.getText().toString();


      //  List<String> experienceList = Arrays.asList(experience.split(","));

        if (username.isEmpty()) {
            showToast("Please enter a username");
            isValid = false;
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            showToast("Please enter a valid email address");
            isValid = false;
        }

        if (phoneNumber.isEmpty() || phoneNumber.length() < 10) {
            showToast("Please enter a valid phone number");
            isValid = false;
        }

        if (address.isEmpty()) {
            showToast("Please enter an address");
            isValid = false;
        }

        if (education.isEmpty()) {
            showToast("Please enter education details");
            isValid = false;
        }

        if (skills.isEmpty()) {
            showToast("Please enter skills information");
            isValid = false;
        }

        if (experience.isEmpty()) {
            showToast("Please enter experience details");
            isValid = false;
        }

        if (isValid) {
            callSeekerApi(username, email, phoneNumber, address, education ,skills,experience);
        }
    }

    private void callSeekerApi(String username, String email, String phoneNumber, String address, String education , String skills,String experienceList) {

        if (checkInternetConnection.isInternetAvailable(this)) {
            WebApiCall webApiCall = new WebApiCall(SeekerForm.this);
            webApiCall.SeekerForm(username, email, phoneNumber, address ,education ,skills ,experienceList);
        }else {
            showToast("No Internet Connection Available");
        }





    }


    private void showToast(String message) {
        Toast.makeText(SeekerForm.this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSignInReceived(LoginResponse jobFeedList) {
        editUser.setText(jobFeedList.getUsername());
    }
}