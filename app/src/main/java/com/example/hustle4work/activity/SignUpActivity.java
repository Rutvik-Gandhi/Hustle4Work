package com.example.hustle4work.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hustle4work.R;
import com.example.hustle4work.model.SignupData;
import com.example.hustle4work.utility.CheckInternetConnection;
import com.example.hustle4work.utility.WebApiCall;

public class SignUpActivity extends AppCompatActivity  {

    private EditText editTextUsername;
    private EditText editEmail;
    private EditText editTxtpasswd;
    private EditText editTxtconfirmpasswd;
    private EditText editTxtphoneno;
    private EditText editTxtaddress;
    CheckInternetConnection checkInternetConnection;

    TextView jobSeekerTextView ,employerTextView ;

    private TextView txtlogin ,btnLogin;
    private boolean isEmployerSelected = false;
    private boolean isJobSeekerSelected = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        checkInternetConnection = new CheckInternetConnection();
        editTextUsername = findViewById(R.id.editUser);
        editEmail = findViewById(R.id.editEmail);
        editTxtpasswd = findViewById(R.id.editpasswd);
        editTxtconfirmpasswd = findViewById(R.id.editcnfmpasswd);
        editTxtphoneno = findViewById(R.id.editphone);
        editTxtaddress = findViewById(R.id.editaddress);
        txtlogin=  findViewById(R.id.txtSignin);
        employerTextView=  findViewById(R.id.editemployer);
        jobSeekerTextView=  findViewById(R.id.editjobseeker);

         btnLogin = findViewById(R.id.btnSave);


        employerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleUserTypeSelection("employer");
            }
        });
        jobSeekerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleUserTypeSelection("job_seeker");
            }
        });


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateFields();
            }
        });

        txtlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignUpActivity.this, LoginActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });

    }


    private void handleUserTypeSelection(String userType) {
        // Reset background colors
        employerTextView.setBackgroundTintList(null);
        jobSeekerTextView.setBackgroundTintList(null);

        // Handle the user type selection
        if ("employer".equals(userType)) {
            isEmployerSelected = true;
            isJobSeekerSelected = false;

            employerTextView.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.black)));
            // Handle employer selection
        } else if ("job_seeker".equals(userType)) {
            isEmployerSelected = false;
            isJobSeekerSelected = true;

            jobSeekerTextView.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.black)));
            // Handle job seeker selection
        }
    }



    private void validateFields() {
        boolean isValid = true;

        String username = editTextUsername.getText().toString();
        String email = editEmail.getText().toString();
        String password = editTxtpasswd.getText().toString();
        String confirmPassword = editTxtconfirmpasswd.getText().toString();
        String phoneNumber = editTxtphoneno.getText().toString();
        String address = editTxtaddress.getText().toString();

        if (username.isEmpty()) {
            showToast("Username is required");
            isValid = false;
        }

        if (email.isEmpty()) {
            showToast("Email is required");
            isValid = false;
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            showToast("Invalid email format");
            isValid = false;
        }

        if (password.isEmpty()) {
            showToast("Password is required");
            isValid = false;
        }

        if (confirmPassword.isEmpty()) {
            showToast("Confirm password is required");
            isValid = false;
        } else if (!confirmPassword.equals(password)) {
            showToast("Passwords do not match");
            isValid = false;
        }

        if (phoneNumber.isEmpty()) {
            showToast("Phone number is required");
            isValid = false;
        }

        if (address.isEmpty()) {
            showToast("Address is required");
            isValid = false;
        }
        if (!isEmployerSelected && !isJobSeekerSelected) {
            // Neither employer nor job seeker is selected
            Toast.makeText(this, "Please select Employer or Job Seeker", Toast.LENGTH_SHORT).show();
            isValid = false;
        }

        if (isValid) {


            callSignupApi(username, email, password, phoneNumber, address,isEmployerSelected);

        }
    }

    private void callSignupApi(String username, String email, String password, String phoneNumber, String address, boolean isEmployer) {
        if (checkInternetConnection.isInternetAvailable(this)) {
            WebApiCall webApiCall = new WebApiCall(SignUpActivity.this);
            webApiCall.registerUser(username, email, password, phoneNumber, address,isEmployer);
        }else {
            showToast("No Internet Connection Available");
        }



    }

    private void showToast(String message) {
        Toast.makeText(SignUpActivity.this, message, Toast.LENGTH_SHORT).show();
    }



}