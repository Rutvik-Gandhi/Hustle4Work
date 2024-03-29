package com.example.hustle4work.Interface;

import com.example.hustle4work.model.ApplyModel;
import com.example.hustle4work.model.JobFeed;
import com.example.hustle4work.model.JobListResponse;
import com.example.hustle4work.model.JobListing;
import com.example.hustle4work.model.LoginResponse;
import com.example.hustle4work.model.SignupRequest;
import com.example.hustle4work.model.SignupResponse1;
import com.example.hustle4work.model.UserInfoSave;
import com.example.hustle4work.model.UserInformation;
import com.example.hustle4work.model.UserReponse_1;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {


    /*@POST("users/signup")
    Call<SignUpResponse> signUp(@FieldMap Map<String, String> params);*/
    @POST("users/signup")
    Call<SignupResponse1> signUp(@Body SignupRequest signupRequest);

    @POST("users/signin")
    Call<UserReponse_1> signIn(@Body LoginResponse signInRequest);




  @GET("jobs")
  Call<List<JobFeed>> getJobs();

  @GET("userData")
    Call<UserInformation> getUserData();

    @GET("users/getUserJob")
    Call<JobListResponse> getEmployerJobs();



    @POST("userData/createUserData/")
    Call<UserInformation> userInfor(@Body UserInfoSave UserInformation);

    @PUT("userData/{userId}")
    Call<UserInformation> updateProfile(@Path("userId") String userId, @Body UserInfoSave UserInformation);


    @POST("jobs/createJob/")
    Call<JobListing> jobDetails(@Body Map<String, String> params);

    @POST("users/JobApply")
    Call<ApplyModel> ApplyJob(@Body Map<String, String> params);

    @POST("users/forgetpassword")
    Call<ApplyModel> ForgotPass(@Body Map<String, String> params);


    @POST("users/resetPassword")
    Call<ApplyModel> ResetPass(@Body Map<String, String> params);




}
