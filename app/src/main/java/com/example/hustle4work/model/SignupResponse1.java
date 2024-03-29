package com.example.hustle4work.model;



public class SignupResponse1 {


    private SignupRequest user;

    private String token;

    private String message;

    public void setUserData(SignupRequest userData) {
        this.user = userData;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    // Constructor


    // Getter method for accessing UserData
    public SignupRequest getUserData() {
        return user;
    }
}
