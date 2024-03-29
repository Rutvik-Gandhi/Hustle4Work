package com.example.hustle4work.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Job implements Parcelable {

    @SerializedName("_id")
    private String id;

    @SerializedName("title")
    private String title;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public List<AppliedUser> getAppliedUsers() {
        return appliedUsers;
    }

    public void setAppliedUsers(List<AppliedUser> appliedUsers) {
        this.appliedUsers = appliedUsers;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
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

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @SerializedName("description")
    private String description;

    @SerializedName("location")
    private String location;

    @SerializedName("requirements")
    private String requirements;

    @SerializedName("payRate")
    private String payRate;

    @SerializedName("jobType")
    private String jobType;

    @SerializedName("uploadDate")
    private String uploadDate;

    @SerializedName("appliedUser")
    private List<AppliedUser> appliedUsers;

    @SerializedName("userID")
    private String userID;

    @SerializedName("createdAt")
    private String createdAt;

    @SerializedName("updatedAt")
    private String updatedAt;

    @SerializedName("__v")
    private int version;

    // Constructors, getters, setters...

    // Parcelable implementation

    protected Job(Parcel in) {
        id = in.readString();
        title = in.readString();
        description = in.readString();
        location = in.readString();
        requirements = in.readString();
        payRate = in.readString();
        jobType = in.readString();
        uploadDate = in.readString();
        appliedUsers = in.createTypedArrayList(AppliedUser.CREATOR);
        userID = in.readString();
        createdAt = in.readString();
        updatedAt = in.readString();
        version = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(location);
        dest.writeString(requirements);
        dest.writeString(payRate);
        dest.writeString(jobType);
        dest.writeString(uploadDate);
        dest.writeTypedList(appliedUsers);
        dest.writeString(userID);
        dest.writeString(createdAt);
        dest.writeString(updatedAt);
        dest.writeInt(version);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Job> CREATOR = new Creator<Job>() {
        @Override
        public Job createFromParcel(Parcel in) {
            return new Job(in);
        }

        @Override
        public Job[] newArray(int size) {
            return new Job[size];
        }
    };
}

