package com.example.hustle4work.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class JobListResponse {

    @SerializedName("jobs")
    private List<Job> jobs;

    public List<Job> getJobs() {
        return jobs;
    }
}

