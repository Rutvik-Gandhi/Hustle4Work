package com.example.hustle4work.model;

import java.util.List;

public class JobListing {




    private String title;
    private String description;
    private String location;
    private String requirements;
    private String payRate;
    private String jobType;
    private String uploadDate;
    List<String> appliedUser;
    private String _id;
    private String createdAt;
    private String updatedAt;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public String getPayRate() {
        return payRate;
    }

    public void setPayRate(String payRate) {
        this.payRate = payRate;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(String uploadDate) {
        this.uploadDate = uploadDate;
    }

    public List<String> getAppliedUser() {
        return appliedUser;
    }

    public void setAppliedUser(List<String> appliedUser) {
        this.appliedUser = appliedUser;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int get__v() {
        return __v;
    }

    public void set__v(int __v) {
        this.__v = __v;
    }

    private int __v;

    // Constructors
    public JobListing() {
        // Default constructor
    }

    public JobListing(String title, String description, String location, String requirements,
                      String payRate, String jobType, String uploadDate, List<String> appliedUser,
                      String _id, String createdAt, String updatedAt, int __v) {
        this.title = title;
        this.description = description;
        this.location = location;
        this.requirements = requirements;
        this.payRate = payRate;
        this.jobType = jobType;
        this.uploadDate = uploadDate;
        // Assuming appliedUser is a list of strings
        this.appliedUser = appliedUser;
        this._id = _id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.__v = __v;
    }
}
