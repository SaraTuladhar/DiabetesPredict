package com.example.diabetesprediction;

public class User {
    public String signupName;
    public String signupUsername;
    public String signupEmail;
    public String signupPassword;
    public String signupPassword2;
    public String result;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }


public User(){ }
    public String getSignupName() {
        return signupName;
    }

    public void setSignupName(String firstName) {
        signupName= firstName;
    }

    public String getSignupUsername()
    {
        return signupUsername;
    }
    public void setSignupUsername(String username) {
        signupUsername = username;
    }

    public String getSignupEmail() {
        return signupEmail;
    }

    public void setSignupEmail(String email) {
    signupEmail= email;
    }

    public String getSignupPassword() {
        return signupPassword;
    }

    public void setSignupPassword(String password) {
        signupPassword = password;
    }
    public String getSignupPassword2() {
        return signupPassword2;
    }

    public void setSignupPassword2(String password2) {
        signupPassword2 = password2;
    }


    }


