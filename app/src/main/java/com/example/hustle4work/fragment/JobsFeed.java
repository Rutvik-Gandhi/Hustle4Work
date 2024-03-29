package com.example.hustle4work.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.hustle4work.Interface.JobFeedCallback;
import com.example.hustle4work.R;
import com.example.hustle4work.activity.EmployerForm;
import com.example.hustle4work.activity.LoginActivity;
import com.example.hustle4work.activity.SeekerForm;
import com.example.hustle4work.adapter.jobAdapter;
import com.example.hustle4work.adapter.jobEmpAdapter;
import com.example.hustle4work.model.Job;
import com.example.hustle4work.model.JobFeed;
import com.example.hustle4work.model.JobListResponse;
import com.example.hustle4work.utility.CSPreferences;
import com.example.hustle4work.utility.CheckInternetConnection;
import com.example.hustle4work.utility.WebApiCall;

import java.util.ArrayList;
import java.util.List;

public class JobsFeed extends Fragment  implements JobFeedCallback {


    View view;
    RecyclerView recyclerView;
    CheckInternetConnection checkInternetConnection;
    private List<JobFeed> dataList = new ArrayList<JobFeed>();
    private List<JobFeed> filteredList = new ArrayList<JobFeed>();

    private List<Job> dataEmpList = new ArrayList<Job>();
    private List<Job> filteredEmpList = new ArrayList<Job>();
    EditText editUser;
    jobAdapter adapter ;
    jobEmpAdapter jobEmpAdapter ;
    ImageView add_employer ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_jobs_feed, container, false);
        checkInternetConnection = new CheckInternetConnection();
         recyclerView = view.findViewById(R.id.recycler_view);
        editUser = view.findViewById(R.id.editUser);
        add_employer =view.findViewById(R.id.add_employer);

        add_employer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity() , EmployerForm.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);

            }
        });
        editUser.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterData(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


       
        if(CSPreferences.readString(getActivity(), "employerString").equals("employerString")){
            callEmplJobFeedApi();

        }
        else{

            callSJobFeedApi();
        }

        return  view;
    }

    private void callEmplJobFeedApi() {
        if (checkInternetConnection.isInternetAvailable(getActivity())) {
            WebApiCall webApiCall = new WebApiCall(getActivity());
            webApiCall.getEmployerFeed(this);
        }else {
            showToast("No Internet Connection Available");
        }


    }

    private void callSJobFeedApi() {

        if (checkInternetConnection.isInternetAvailable(getActivity())) {
            WebApiCall webApiCall = new WebApiCall(getActivity());
            webApiCall.getjobFeed(this);
        }else {
            showToast("No Internet Connection Available");
        }



    }

    private void filterData(String query) {
        filteredList.clear();
        for (JobFeed item : dataList) {
            if (item.getTitle().toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(item);
            }
        }
        adapter.notifyDataSetChanged();
    }

    private void showToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }



    // this interface call from webApiClass and get list and show in recyclerview .
    @Override
    public void onJobFeedReceived(List<JobFeed> jobFeedList) {
        dataList.addAll(jobFeedList);
        filteredList.addAll(dataList);
        add_employer.setVisibility(View.GONE);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        // Create and set the adapter
         adapter = new jobAdapter(getActivity(),filteredList);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onEmployerFeedReceived(List<Job> jobEmpFeedList) {
        dataEmpList.addAll(jobEmpFeedList);
        filteredEmpList.addAll(dataEmpList);
        add_employer.setVisibility(View.VISIBLE);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        // Create and set the adapter
        jobEmpAdapter = new jobEmpAdapter(getActivity(),filteredEmpList);
        recyclerView.setAdapter(jobEmpAdapter);
    }
}