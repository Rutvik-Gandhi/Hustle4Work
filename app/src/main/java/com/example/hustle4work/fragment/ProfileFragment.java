package com.example.hustle4work.fragment;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hustle4work.Interface.GetUserData;
import com.example.hustle4work.R;
import com.example.hustle4work.activity.SeekerForm;
import com.example.hustle4work.model.LoginResponse;
import com.example.hustle4work.model.UserInformation;
import com.example.hustle4work.utility.CSPreferences;
import com.example.hustle4work.utility.CheckInternetConnection;
import com.example.hustle4work.utility.WebApiCall;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class ProfileFragment extends Fragment implements GetUserData {

   View  view;

   ImageView iv_back;

    LinearLayout toolbar_ll;

    EditText editEduaction  ;
    TextView btnSave,editSkills,tvExperi,editUser , editemail ,editphone ,editaddress ,tvEducationshow;
    CardView cv_skill ,cv_exp;
    private List<String> stringExperience = new ArrayList<>();
    private List<String> stringSkill = new ArrayList<>();
    UserInformation retrievedModel ;
    String finalSkill ,finalExper ,jobid;

    ConstraintLayout empGone;


    CheckInternetConnection checkInternetConnection;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.activity_seeker_form, container, false);
        editUser = view.findViewById(R.id.editUser);
        checkInternetConnection = new CheckInternetConnection();
        editemail = view.findViewById(R.id.editEmail);
        tvEducationshow = view.findViewById(R.id.tvEducationshow);
        editphone = view.findViewById(R.id.editphone);
        editaddress = view.findViewById(R.id.editaddress);
        cv_exp= view.findViewById(R.id.cv_exp);
        cv_skill = view.findViewById(R.id.cv_skill);
        editEduaction = view.findViewById(R.id.editEdu);
        editSkills = view.findViewById(R.id.editSkil);
        tvExperi = view.findViewById(R.id.tvExperi);
        iv_back = view.findViewById(R.id.back_seeker);
        btnSave = view.findViewById(R.id.btnSave);
        empGone = view.findViewById(R.id.empGone);
        toolbar_ll =view.findViewById(R.id.custom_toolbar_activity);
        toolbar_ll.setVisibility(View.GONE);
        iv_back.setVisibility(View.GONE);


        btnSave.setText("Update");

        callProfileApi();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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


        if(CSPreferences.readString(getActivity(), "employerString").equals("employerString")){

            empGone.setVisibility(View.GONE);
        }
        else{

            empGone.setVisibility(View.VISIBLE);
        }



         /*retrievedModel = CSPreferences.readModel(getActivity(), "UserInformation", UserInformation.class);

        editUser.setText(retrievedModel.getUsername());
        editphone.setText("4373294436");
        editaddress.setText(retrievedModel.getAddress());
        editemail.setText(retrievedModel.getEmail());
        editEduaction.setText(retrievedModel.getQualification());


         finalSkill = retrievedModel.getSkills().toString().replace("[", "").replace(",]", "");
         finalExper = retrievedModel.getExperience().toString().replace("[", "").replace(",]", "");*/
        /*editSkills.setText(finalSkill);
        tvExperi.setText(finalExper);*/


        return view;
    }

    public void showBottomSheetDialog(String add, String skill) {
        // Inflate the custom view for the bottom sheet
        View viewInflated = LayoutInflater.from(getActivity()).inflate(R.layout.custom_bottom_sheet_dialog, null);

        // Find the EditText fields in the inflated view

        TextView tv_header  = viewInflated.findViewById(R.id.tv_header_bs);
        TextView   tv_skill= viewInflated.findViewById(R.id.tv_skill);
        EditText editText1 = viewInflated.findViewById(R.id.editskill);
        tv_header.setText(add);
        tv_skill.setText(skill);



        // Create a BottomSheetDialog with the inflated view
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getActivity());
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
            String previousExperience = finalExper +","+ stringBuilder.toString();
            tvExperi.setText(previousExperience);
            Log.e("checkstringdata",editSkills.getText().toString());
        }
        else{
            String previousSkill = finalSkill +","+ stringBuilder.toString();
            editSkills.setText(previousSkill);
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
             callUpdateProfileApi(username, email, phoneNumber, address, education ,skills,experience);
        }
    }

    private void callUpdateProfileApi(String username, String email, String phoneNumber, String address, String education , String skills,String experienceList ) {

        if (checkInternetConnection.isInternetAvailable(getActivity())) {
            WebApiCall webApiCall = new WebApiCall(getActivity());
            webApiCall.UpdateProfileApi(username, email, phoneNumber, address ,education ,skills ,experienceList ,jobid);
        }
        else
        {
            showToast("No Internet Connection Available");
        }





    }

    private void callProfileApi() {

        if (checkInternetConnection.isInternetAvailable(getActivity())) {
            WebApiCall webApiCall = new WebApiCall(getActivity());
            webApiCall.ProfileApi(this);
        }else {
            showToast("No Internet Connection Available");
        }





    }

    private void showToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onJobReceived(UserInformation jobFeedList) {
        editUser.setText(jobFeedList.getUsername());
        editphone.setText(jobFeedList.getPhoneNumber());
        editaddress.setText(jobFeedList.getAddress());
        editemail.setText(jobFeedList.getEmail());
        editEduaction.setText(jobFeedList.getQualification());

        finalSkill = jobFeedList.getSkills().toString().replace("[", "").replace(",]", "");
        finalExper = jobFeedList.getExperience().toString().replace("[", "").replace(",]", "");
        editSkills.setText(finalSkill);
        tvExperi.setText(finalExper);

       jobid=  jobFeedList.getId();
    }
}