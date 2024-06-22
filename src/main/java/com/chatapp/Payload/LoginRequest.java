package com.chatapp.Payload;

public class LoginRequest {

    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public void setPassword() {
        this.email = email;
    }

    public LoginRequest() {
    }

    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginRequest [email=" + email + ", password=" + password +"]";
    }

}