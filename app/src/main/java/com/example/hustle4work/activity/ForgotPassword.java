package com.example.hustle4work.activity;

import android.content.Intent;
import android.os.Bundle;

import com.example.hustle4work.utility.CheckInternetConnection;
import com.example.hustle4work.utility.WebApiCall;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;



import com.example.hustle4work.R;

public class ForgotPassword extends AppCompatActivity {

     EditText et_Emailforgot;
     TextView tvcode;
     ImageView back_forgot;

     CardView cv_verfiForgot;
     CheckInternetConnection checkInternetConnection;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        checkInternetConnection = new CheckInternetConnection();
        back_forgot =findViewById(R.id.back_forgot);
        et_Emailforgot =findViewById(R.id.et_Emailforgot);
        tvcode = findViewById(R.id.tvcode);
        cv_verfiForgot= findViewById(R.id.cv_verfiForgot);

        back_forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(ForgotPassword.this, LoginActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });

        cv_verfiForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateField();
            }
        });













    }

    public void validateField(){

        String email = et_Emailforgot.getText().toString().toString();
        boolean isValid = true;
        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
        showToast("Please enter a valid email address");
        isValid = false;

        }

        if (isValid) {
            callForgotApi(email);
        }
    }

    private void callForgotApi(String email) {

        if (checkInternetConnection.isInternetAvailable(this)) {
            WebApiCall webApiCall = new WebApiCall(ForgotPassword.this);
            webApiCall.callForgotApi(email);
        }else {
            showToast("No Internet Connection Available");
        }
    }

    private void showToast(String message) {
        Toast.makeText(ForgotPassword.this, message, Toast.LENGTH_SHORT).show();
    }



}