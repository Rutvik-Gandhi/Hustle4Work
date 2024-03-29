package com.example.hustle4work.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class JobFeed implements Parcelable {
    private String _id;
    private String title;
    private String description;
    private String location;
    private String requirements;
    private String payRate;
    private String jobType;
    private String uploadDate;
    private String createdAt;
    private String updatedAt;
    private int __v;




/*    @SerializedName("_id")
    private String id;

    @SerializedName("title")
    private String title;

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

    // Getters and setters

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }*/


    // Constructors, other methods...

    // Parcelable implementation
    protected JobFeed(Parcel in) {
        _id = in.readString();
        title = in.readString();
        description = in.readString();
        location = in.readString();
        requirements = in.readString();
        payRate = in.readString();
        jobType = in.readString();
        uploadDate = in.readString();
        createdAt = in.readString();
        updatedAt = in.readString();
        __v = in.readInt();
    }

    public static final Creator<JobFeed> CREATOR = new Creator<JobFeed>() {
        @Override
        public JobFeed createFromParcel(Parcel in) {
            return new JobFeed(in);
        }

        @Override
        public JobFeed[] newArray(int size) {
            return new JobFeed[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(_id);
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(location);
        dest.writeString(requirements);
        dest.writeString(payRate);
        dest.writeString(jobType);
        dest.writeString(uploadDate);
        dest.writeString(createdAt);
        dest.writeString(updatedAt);
        dest.writeInt(__v);
    }

    // Getters and setters for Parcelable
    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
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
}
