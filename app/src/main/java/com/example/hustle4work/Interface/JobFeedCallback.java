package com.example.hustle4work.Interface;

import com.example.hustle4work.model.Job;
import com.example.hustle4work.model.JobFeed;

import java.util.List;



public interface JobFeedCallback {

    void onJobFeedReceived(List<JobFeed> jobFeedList);
    void onEmployerFeedReceived(List<Job> jobFeedList);

}
