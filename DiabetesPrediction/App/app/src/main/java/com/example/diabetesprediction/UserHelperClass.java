package com.example.diabetesprediction;

public class UserHelperClass {
    String signupName, signupUsername, signupEmail, signupPassword;

        public UserHelperClass(){

        }
    public UserHelperClass(String signupName, String signupUsername, String signupEmail, String signupPassword) {
        this.signupName = signupName;
        this.signupUsername = signupUsername;
        this.signupEmail = signupEmail;
        this.signupPassword = signupPassword;
    }

    public String getSignupName() {
        return signupName;
    }

    public void setSignupName(String signupName) {
        this.signupName = signupName;
    }

    public String getSignupUsername() {
        return signupUsername;
    }

    public void setSignupUsername(String signupUsername) {
        this.signupUsername = signupUsername;
    }

    public String getSignupEmail() {
        return signupEmail;
    }

    public void setSignupEmail(String signupEmail) {
        this.signupEmail = signupEmail;
    }

    public String getSignupPassword() {
        return signupPassword;
    }

    public void setSignupPassword(String signupPassword) {
        this.signupPassword = signupPassword;
    }
}
