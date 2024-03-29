package com.example.hustle4work.model;

import android.os.Parcel;
import android.os.Parcelable;

public class AppliedUser implements Parcelable {

    private String username;
    private String email;
    private String address;
    private boolean isEmployer;
    private String otp;
    private String appliedUserId;

    // Constructors, getters, setters...

    // Parcelable implementation

    protected AppliedUser(Parcel in) {
        username = in.readString();
        email = in.readString();
        address = in.readString();
        isEmployer = in.readByte() != 0;
        otp = in.readString();
        appliedUserId = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(username);
        dest.writeString(email);
        dest.writeString(address);
        dest.writeByte((byte) (isEmployer ? 1 : 0));
        dest.writeString(otp);
        dest.writeString(appliedUserId);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<AppliedUser> CREATOR = new Creator<AppliedUser>() {
        @Override
        public AppliedUser createFromParcel(Parcel in) {
            return new AppliedUser(in);
        }

        @Override
        public AppliedUser[] newArray(int size) {
            return new AppliedUser[size];
        }
    };

    // Getters and setters...

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isEmployer() {
        return isEmployer;
    }

    public void setEmployer(boolean employer) {
        isEmployer = employer;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getAppliedUserId() {
        return appliedUserId;
    }
}
