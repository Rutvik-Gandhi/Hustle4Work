package com.example.hustle4work.utility;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.hustle4work.Interface.ApiInterface;
import com.example.hustle4work.Interface.GetUserData;
import com.example.hustle4work.Interface.JobFeedCallback;
import com.example.hustle4work.Interface.SignInInterface;
import com.example.hustle4work.activity.EmployerForm;
import com.example.hustle4work.activity.LoginActivity;
import com.example.hustle4work.activity.MainActivity;
import com.example.hustle4work.activity.ResetPassword;
import com.example.hustle4work.activity.SeekerForm;
import com.example.hustle4work.model.ApplyModel;
import com.example.hustle4work.model.Job;
import com.example.hustle4work.model.JobFeed;
import com.example.hustle4work.model.JobListResponse;
import com.example.hustle4work.model.JobListing;
import com.example.hustle4work.model.LoginResponse;
import com.example.hustle4work.model.SignupRequest;
import com.example.hustle4work.model.SignupResponse1;
import com.example.hustle4work.model.UserInfoSave;
import com.example.hustle4work.model.UserInformation;
import com.example.hustle4work.model.UserReponse_1;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WebApiCall {

    ApiInterface webApiInterface;
    Context context;
    CustomProgressDialog customProgressDialog;
    Gson gson;
    private String baseUrl;
    private String from;
    SignInInterface signInInterface ;




    public WebApiCall(Context context) {
        this.context = context;
        this.from = from;
        customProgressDialog = new CustomProgressDialog(context);
        this.context = context;



        String baseUrl = Baseurl.BASE_URL;
        Gson gson = new GsonBuilder().setLenient().create();

        String authToken = CSPreferences.readString(context, "token");

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        // Create an interceptor to add the token to the headers if it exists
        if (!authToken.isEmpty()) {
            Interceptor headerInterceptor = chain -> {
                Request request = chain.request().newBuilder()
                        .addHeader("Authorization", "Bearer " + authToken)
                        .build();
                return chain.proceed(request);
            };
            httpClient.addInterceptor(headerInterceptor);
        }

        // Add logging interceptor
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.addInterceptor(interceptor);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient.build())
                .build();

        webApiInterface = retrofit.create(ApiInterface.class);

    }



    public void registerUser(String username, String email, String password, String phoneNumber, String address, boolean isEmployer) {


        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setEmail(email);
        signupRequest.setUsername(username);
        signupRequest.setPassword(password);
        signupRequest.setPhoneNumber(phoneNumber);
        signupRequest.setAddress(address);
        signupRequest.setEmployer(isEmployer);


        Log.i("baseUrl",""+baseUrl);


        customProgressDialog.show();

        Call<SignupResponse1> signup_Service = webApiInterface.signUp(signupRequest);

        signup_Service.enqueue(new Callback<SignupResponse1>() {
            @Override
            public void onResponse(Call<SignupResponse1> call, Response<SignupResponse1> response) {
                customProgressDialog.hide();
                if (response.isSuccessful()) {





                    SignupResponse1  userResponse = response.body();

                  //  Log.e("response" , userResponse.getMessage());

                    SignupRequest user = userResponse.getUserData();



                    String token = userResponse.getToken();
                    CSPreferences.putString(context,"token" ,token);
                    showToast("Sucessfully Register");

                 Intent i = new Intent(context,LoginActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                 context.startActivity(i);


                }
                else {
                    int code =response.code();
                    if (code == 400 ) {
                        // Show "Not Found" toast
                        showToast("User already Exist!");
                    }
                }

            }

            @Override
            public void onFailure(Call<SignupResponse1> call, Throwable t) {
                if (customProgressDialog.isShowing())
                    customProgressDialog.dismiss();
                t.printStackTrace();
                Toast.makeText(context, "Poor Connection."+t.toString(), Toast.LENGTH_SHORT).show();
                Log.d("dddddd", "onFailure: " + t.getMessage());
            }

        });
    }

    public void SeekerForm(String username, String email, String phoneNumber, String address, String qualification, String skills, String experience) {


        UserInfoSave userInfo = new UserInfoSave(username, email,phoneNumber,  address,  qualification,  skills,  experience );




        customProgressDialog.show();
        Call<UserInformation> signup_Service = webApiInterface.userInfor(userInfo);

        signup_Service.enqueue(new Callback<UserInformation>() {
            @Override
            public void onResponse(Call<UserInformation> call, Response<UserInformation> response) {
                customProgressDialog.hide();
                if (response.isSuccessful()) {

                    UserInformation userResponse = response.body();
                    Log.e("sd",userResponse.getSkills().toString());

                    CSPreferences.putModel(context, "UserInformation", userResponse);

                    Intent i = new Intent(context, MainActivity.class);
                    context.startActivity(i);
                    showToast("Sucessfully Data Save.");
                }
                else
                {
                    Intent i = new Intent(context, MainActivity.class);
                    context.startActivity(i);
                }


            }

            @Override
            public void onFailure(Call<UserInformation> call, Throwable t) {
                if (customProgressDialog.isShowing())
                    customProgressDialog.dismiss();
                t.printStackTrace();
                Toast.makeText(context, "Poor Connection."+t.toString(), Toast.LENGTH_SHORT).show();
                Log.d("dddddd", "onFailure: " + t.getMessage());
            }

        });
    }

    public void UpdateProfileApi( String username, String email, String phoneNumber, String address, String qualification, String skills, String experience, String Jobid) {


        UserInfoSave userInfo = new UserInfoSave(username, email,phoneNumber,  address,  qualification,  skills,  experience );




        customProgressDialog.show();
        Call<UserInformation> signup_Service = webApiInterface.updateProfile(Jobid,userInfo);

        signup_Service.enqueue(new Callback<UserInformation>() {
            @Override
            public void onResponse(Call<UserInformation> call, Response<UserInformation> response) {
                customProgressDialog.hide();
                if (response.isSuccessful()) {

                    UserInformation userResponse = response.body();
                    Log.e("sd",userResponse.getSkills().toString());
                    CSPreferences.putModel(context, "UserInformation", userResponse);
                    showToast(userResponse.getSkills().toString());

                }


            }

            @Override
            public void onFailure(Call<UserInformation> call, Throwable t) {
                if (customProgressDialog.isShowing())
                    customProgressDialog.dismiss();
                t.printStackTrace();
                Toast.makeText(context, "Poor Connection."+t.toString(), Toast.LENGTH_SHORT).show();
                Log.d("dddddd", "onFailure: " + t.getMessage());
            }

        });
    }

    public void EmployerForm(String title, String desc, String loc, String req, String pay, String jobType, String currentDate) {



        Map<String, String> params = new HashMap<>();
        params.put("title", title);
        params.put("description", desc);
        params.put("location", loc);
        params.put("requirements", req);
        params.put("payRate", pay);
        params.put("jobType", jobType);
        params.put("uploadDate", currentDate);




        customProgressDialog.show();

        Call<JobListing> signup_Service = webApiInterface.jobDetails(params);

        signup_Service.enqueue(new Callback<JobListing>() {
            @Override
            public void onResponse(Call<JobListing> call, Response<JobListing> response) {
                customProgressDialog.hide();
                JobListing jobDetails =  response.body();
                Log.i("jobDetails",""+jobDetails.getJobType());

                Intent i = new Intent(context, MainActivity.class);

                context.startActivity(i);

                CSPreferences.putString(context,"employerString", "employerString");



            }




            @Override
            public void onFailure(Call<JobListing> call, Throwable t) {
                if (customProgressDialog.isShowing())
                    customProgressDialog.dismiss();
                t.printStackTrace();
                Toast.makeText(context, "Poor Connection."+t.toString(), Toast.LENGTH_SHORT).show();
                Log.d("dddddd", "onFailure: " + t.getMessage());
            }

        });
    }

    public void login(String email, String password){



        LoginResponse signInRequest = new LoginResponse();
        signInRequest.setEmail(email);
        signInRequest.setPassword(password);



        Log.i("baseUrl",""+baseUrl);


        customProgressDialog.show();
        Call<UserReponse_1> signinservice = webApiInterface.signIn(signInRequest);

        signinservice.enqueue(new Callback<UserReponse_1>() {
            @Override
            public void onResponse(Call<UserReponse_1> call, Response<UserReponse_1> response) {
               // showToast("User not found!");

                //UserReponse_1 userResponse1 = response.body();
                customProgressDialog.hide();
                if (response.isSuccessful()) {

                    UserReponse_1 userResponse = response.body();

                    LoginResponse user = userResponse.getUser();
                    String token = userResponse.getToken();
                    CSPreferences.putString(context,"token" ,token);


                    if(user.isEmployer()){
                        if (CSPreferences.readString(context,"Employer").equalsIgnoreCase("0"))
                        {
                            Intent i = new Intent(context, MainActivity.class);
                            context.startActivity(i);
                        }
                        else
                        {


                            Intent i = new Intent(context, EmployerForm.class);
                            context.startActivity(i);
                        }
                    }
                    else
                    {

                        if (CSPreferences.readString(context,"Seeker").equalsIgnoreCase("1"))

                        {
                            Intent i = new Intent(context, MainActivity.class);
                            context.startActivity(i);
                        }
                        else
                        {
                            CSPreferences.putModel(context, "myModelKey", user);

                            Intent i = new Intent(context, SeekerForm.class);
                            context.startActivity(i);
                        }

                    }










                } else {
                    int code =response.code();
                    if (code == 400 || code == 404) {
                        // Show "Not Found" toast
                        showToast("Invalid Credential");
                    }
                }


            }

            @Override
            public void onFailure(Call<UserReponse_1> call, Throwable t) {
                if (customProgressDialog.isShowing())
                    customProgressDialog.dismiss();
                t.printStackTrace();
                Toast.makeText(context, "Poor Connection."+t.toString(), Toast.LENGTH_SHORT).show();
                Log.d("dddddd", "onFailure: " + t.getMessage());
            }
        });




    }

    public void getjobFeed(JobFeedCallback callback){

        customProgressDialog.show();
        Call<List<JobFeed>> signinservice = webApiInterface.getJobs(

        );

       // Call<JobFeed> signinservice = webApiInterface.getJobs(options);

        signinservice.enqueue(new Callback<List<JobFeed>>() {
            @Override
            public void onResponse(Call<List<JobFeed>> call, Response<List<JobFeed>> response) {
                // showToast("User not found!");
                customProgressDialog.hide();
                if (response.isSuccessful()) {

                    List<JobFeed> jobs = response.body();




                    if (callback != null) {
                        callback.onJobFeedReceived(jobs);
                    }



                } else {
                    // Handle unsuccessful response
                }


            }

            @Override
            public void onFailure(Call<List<JobFeed>> call, Throwable t) {
                if (customProgressDialog.isShowing())
                    customProgressDialog.dismiss();
                t.printStackTrace();
                Toast.makeText(context, "Poor Connection."+t.toString(), Toast.LENGTH_SHORT).show();
                Log.d("dddddd", "onFailure: " + t.getMessage());
            }
    });




    }



    public void getEmployerFeed(JobFeedCallback callback){

        customProgressDialog.show();
        Call<JobListResponse> signinservice = webApiInterface.getEmployerJobs();



        signinservice.enqueue(new Callback<JobListResponse>() {
            @Override
            public void onResponse(Call<JobListResponse> call, Response<JobListResponse> response) {
                // showToast("User not found!");
                customProgressDialog.hide();
                if (response.isSuccessful()) {

                    JobListResponse jobsResponse = response.body();
                    List<Job> jobs = jobsResponse.getJobs();
                   // List<Job> allJobs = new ArrayList<>();



                    if (callback != null) {
                        callback.onEmployerFeedReceived(jobs);
                    }








                } else {
                    // Handle unsuccessful response
                }


            }

            @Override
            public void onFailure(Call<JobListResponse> call, Throwable t) {
                if (customProgressDialog.isShowing())
                    customProgressDialog.dismiss();
                t.printStackTrace();
                Toast.makeText(context, "Poor Connection."+t.toString(), Toast.LENGTH_SHORT).show();
                Log.d("dddddd", "onFailure: " + t.getMessage());
            }
        });




    }






    public void Apply(String username, String email, String phoneNumber, String address, String jobid) {

        Map<String, String> params = new HashMap<>();
        params.put("username", username);
        params.put("email", email);
        params.put("address", address);
        params.put("phoneNumber", phoneNumber);
        params.put("jobId", jobid);





        customProgressDialog.show();

        Call<ApplyModel> signup_Service = webApiInterface.ApplyJob(params);

        signup_Service.enqueue(new Callback<ApplyModel>() {
            @Override
            public void onResponse(Call<ApplyModel> call, Response<ApplyModel> response) {
                customProgressDialog.hide();
                ApplyModel jobDesc =  response.body();
                CSPreferences.putString(context,"jobDesc",jobDesc.getMessage());
                showToast(jobDesc.getMessage());

                Log.i("jobDetails",""+jobDesc.getMessage());
            }

            @Override
            public void onFailure(Call<ApplyModel> call, Throwable t) {
                if (customProgressDialog.isShowing())
                    customProgressDialog.dismiss();
                t.printStackTrace();
                Toast.makeText(context, "Poor Connection."+t.toString(), Toast.LENGTH_SHORT).show();
                Log.d("dddddd", "onFailure: " + t.getMessage());
            }

        });

    }

    public void callForgotApi(String email) {

        Map<String, String> params = new HashMap<>();
        params.put("email", email);






        customProgressDialog.show();

        Call<ApplyModel> signup_Service = webApiInterface.ForgotPass(params);

        signup_Service.enqueue(new Callback<ApplyModel>() {
            @Override
            public void onResponse(Call<ApplyModel> call, Response<ApplyModel> response) {
                customProgressDialog.hide();
                ApplyModel jobDesc =  response.body();
                showToast(jobDesc.getMessage());

                if(jobDesc.getMessage().equals("OTP sent successfully!")){

                    Intent i = new Intent(context, ResetPassword.class);
                    context.startActivity(i);
                }


                Log.i("jobDetails",""+jobDesc.getMessage());
            }

            @Override
            public void onFailure(Call<ApplyModel> call, Throwable t) {
                if (customProgressDialog.isShowing())
                    customProgressDialog.dismiss();
                t.printStackTrace();
                Toast.makeText(context, "Poor Connection."+t.toString(), Toast.LENGTH_SHORT).show();
                Log.d("dddddd", "onFailure: " + t.getMessage());
            }

        });
    }

    public void callResetApi(String otp, String password) {

        Map<String, String> params = new HashMap<>();
        params.put("otp", otp);
        params.put("password", password);






        customProgressDialog.show();

        Call<ApplyModel> signup_Service = webApiInterface.ResetPass(params);

        signup_Service.enqueue(new Callback<ApplyModel>() {
            @Override
            public void onResponse(Call<ApplyModel> call, Response<ApplyModel> response) {
                customProgressDialog.hide();
                ApplyModel jobDesc =  response.body();

                if(jobDesc.getMessage().equals("this code has been expired!"))
                showToast("Otp is incorrect");
                else{
                    showToast(jobDesc.getMessage());
                }

                Intent i = new Intent(context, LoginActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(i);




                Log.i("jobDetails",""+jobDesc.getMessage());
            }

            @Override
            public void onFailure(Call<ApplyModel> call, Throwable t) {
                if (customProgressDialog.isShowing())
                    customProgressDialog.dismiss();
                t.printStackTrace();
                Toast.makeText(context, "Poor Connection."+t.toString(), Toast.LENGTH_SHORT).show();
                Log.d("dddddd", "onFailure: " + t.getMessage());
            }

        });
    }


    private void showToast(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public void ProfileApi(GetUserData callback){

        customProgressDialog.show();
        Call<UserInformation> signinservice = webApiInterface.getUserData();

        // Call<JobFeed> signinservice = webApiInterface.getJobs(options);

        signinservice.enqueue(new Callback<UserInformation>() {
            @Override
            public void onResponse(Call<UserInformation> call, Response<UserInformation> response) {
                // showToast("User not found!");
                customProgressDialog.hide();
                if (response.isSuccessful()) {

                    UserInformation info = response.body();




                    if (callback != null) {
                        callback.onJobReceived(info);
                    }



                } else {
                    // Handle unsuccessful response
                }


            }

            @Override
            public void onFailure(Call<UserInformation> call, Throwable t) {
                if (customProgressDialog.isShowing())
                    customProgressDialog.dismiss();
                t.printStackTrace();
                Toast.makeText(context, "Poor Connection."+t.toString(), Toast.LENGTH_SHORT).show();
                Log.d("dddddd", "onFailure: " + t.getMessage());
            }
        });




    }


}
