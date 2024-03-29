package com.example.hustle4work.model;

public class SignupData {

        private String username;
        private String password;
        private String email;
        private String phoneNumber;
        private String address;
        private boolean isEmployer;
        private String _id;

        private String token;

        // Constructors
        public SignupData() {
            // Default constructor required for serialization
        }

        public SignupData(String username, String password, String email, String phoneNumber, String address,
                        boolean isEmployer, String _id, String token) {
            this.username = username;
            this.password = password;
            this.email = email;
            this.phoneNumber = phoneNumber;
            this.address = address;
            this.isEmployer = isEmployer;
            this._id = _id;

            this.token = token;
        }

        // Getter methods

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }

        public String getEmail() {
            return email;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public String getAddress() {
            return address;
        }

    public void setEmployer(boolean employer) {
        isEmployer = employer;
    }

    public boolean isEmployer() {
            return isEmployer;
        }

        public String get_id() {
            return _id;
        }

        public String getToken() {
            return token;
        }

}
