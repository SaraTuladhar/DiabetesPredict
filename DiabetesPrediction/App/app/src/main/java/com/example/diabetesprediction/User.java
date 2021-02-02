package com.example.diabetesprediction;

public class User {
    public String signupName, signupUsername, signupEmail, signupPassword, signupPassword2;
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


