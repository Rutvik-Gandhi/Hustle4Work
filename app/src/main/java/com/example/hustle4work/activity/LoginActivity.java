package com.example.hustle4work.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import android.widget.TextView;
import android.widget.Toast;

import com.example.hustle4work.R;
import com.example.hustle4work.utility.CSPreferences;
import com.example.hustle4work.utility.CheckInternetConnection;
import com.example.hustle4work.utility.WebApiCall;

public class LoginActivity extends AppCompatActivity {


    EditText editTextUsername ,editTxtPassword;
    TextView txtSignUp, btnLogin,txtForgot;
    CheckInternetConnection checkInternetConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);

        checkInternetConnection = new CheckInternetConnection();
        editTextUsername = findViewById(R.id.editEmail);
        editTxtPassword = findViewById(R.id.editpasswd);
        txtSignUp = findViewById(R.id.txtSignUplogin);
        btnLogin =findViewById(R.id.btnLoginscreen);
        txtForgot =findViewById(R.id.txtForgot);

        txtForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Intent i= new Intent(LoginActivity.this , ForgotPassword.class);
                 startActivity(i);
            }
        });


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               /* Intent i = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(i);*/
               validateFields();
            }
        });



        txtSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(i);
            }
        });
    }

    private void validateFields() {
        String username = editTextUsername.getText().toString().trim();
        String password = editTxtPassword.getText().toString().trim();



        if (username.isEmpty() || password.isEmpty()) {
            showToast("Username and password are required");
        } else {

            if (checkInternetConnection.isInternetAvailable(this)) {
                WebApiCall webApiCall = new WebApiCall(LoginActivity.this);
                webApiCall.login(username,password);
            }else {
                showToast("No Internet Connection Available");
            }


        }//q@gamil.com
    }

    private void showToast(String message) {
        Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}