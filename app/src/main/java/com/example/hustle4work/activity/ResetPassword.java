package com.example.hustle4work.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.hustle4work.R;
import com.example.hustle4work.utility.CheckInternetConnection;
import com.example.hustle4work.utility.WebApiCall;

public class ResetPassword extends AppCompatActivity {
     EditText editotp ,etpassreset ,etcnfmreset;

     TextView tvconformreset;
    CheckInternetConnection checkInternetConnection;

    CardView cv_confrm;

    ImageView back_reset;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_reset);

        checkInternetConnection = new CheckInternetConnection();
        cv_confrm= findViewById(R.id.cv_confrm);
        editotp = findViewById(R.id.editotp);
        etcnfmreset = findViewById(R.id.etcnfmreset);
        etpassreset = findViewById(R.id.etpassreset);
        back_reset =findViewById(R.id.back_reset);
        tvconformreset= findViewById(R.id.tvconformreset);


        cv_confrm.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View v) {
           validateField();
       }
   });

        back_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(ResetPassword.this, ForgotPassword.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });


    }

    public void validateField(){

        String otp = editotp.getText().toString().trim();
        String password = etpassreset.getText().toString().trim();
        String conformreset = etcnfmreset.getText().toString().trim();


        boolean isValid = true;


        if (otp.isEmpty()) {
            showToast("Otp is required");
            isValid = false;
        }

        if (password.isEmpty()) {
            showToast("Password is required");
            isValid = false;
        }

        if (conformreset.isEmpty()) {
            showToast("Confirm password is required");
            isValid = false;
        }
        if (!conformreset.equals(password.trim())) {
            showToast("Passwords do not match");
            isValid = false;
        }

        if (isValid) {
            callResetApi(otp, password);
        }
    }

    private void callResetApi(String otp ,String password) {

        if (checkInternetConnection.isInternetAvailable(this)) {
            WebApiCall webApiCall = new WebApiCall(ResetPassword.this);
            webApiCall.callResetApi(otp ,password);
        }else {
            showToast("No Internet Connection Available");
        }
    }

    private void showToast(String message) {
        Toast.makeText(ResetPassword.this, message, Toast.LENGTH_SHORT).show();
    }



}
