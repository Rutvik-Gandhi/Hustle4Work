package com.example.hustle4work.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hustle4work.R;
import com.example.hustle4work.utility.CSPreferences;
import com.example.hustle4work.utility.CheckInternetConnection;
import com.example.hustle4work.utility.WebApiCall;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class EmployerForm extends AppCompatActivity {


   EditText editTitle ,editdesc , editLoc ,editReq ,editpaay ,editJobtype ;
   ImageView back_emp;
   TextView btnSaveEmp;
    CheckInternetConnection checkInternetConnection;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employer_form);
        checkInternetConnection = new CheckInternetConnection();
        editTitle = findViewById(R.id.edittitle);

        editdesc = findViewById(R.id.etDesc);
        editLoc = findViewById(R.id.etloc);
        editReq = findViewById(R.id.etreq);
        editpaay = findViewById(R.id.etpay);
        editJobtype = findViewById(R.id.et_jobtype);
        back_emp =findViewById(R.id.back_emp);




        btnSaveEmp = findViewById(R.id.btnSaveEmp);

        getCurrentDate();
        btnSaveEmp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CSPreferences.putString(EmployerForm.this, "Employer","0");
                validateFields();
            }
        });

        back_emp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();

            }
        });


    }

    private void validateFields() {
        boolean isValid = true; // Assume all fields are valid initially

        String title = editTitle.getText().toString();
        String desc = editdesc.getText().toString();
        String loc = editLoc.getText().toString();
        String req = editReq.getText().toString();
        String pay = editpaay.getText().toString();
        String jobType = editJobtype.getText().toString();

        if (title.isEmpty()) {
            showToast("Title is required");
            isValid = false;
        }

        if (desc.isEmpty()) {
            showToast("Description is required");
            isValid = false;
        }

        if (loc.isEmpty()) {
            showToast("Location is required");
            isValid = false;
        }

        if (req.isEmpty()) {
            showToast("Requirements are required");
            isValid = false;
        }

        if (pay.isEmpty()) {
            showToast("Pay is required");
            isValid = false;
        }

        if (jobType.isEmpty()) {
            showToast("Job type is required");
            isValid = false;
        }

        // If any of the fields are empty, isValid will be false
        if (isValid) {
            callEmployerApi(title ,desc,loc,req,pay,jobType,getCurrentDate());
        }
    }

    private void callEmployerApi(String title, String desc, String loc, String req, String pay, String jobType, String currentDate) {


        if (checkInternetConnection.isInternetAvailable(this)) {
            WebApiCall webApiCall = new WebApiCall(EmployerForm.this);
            webApiCall.EmployerForm(title, desc, loc, req ,pay ,jobType ,currentDate);

        }
        else {
            showToast("No Internet Connection Available");
        }


    }


    private String getCurrentDate() {
        // Get the current date using Calendar
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();

        // Format the date as needed (in this example, it's formatted as "yyyy-MM-dd HH:mm:ss")
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        return sdf.format(date);
    }




    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


}