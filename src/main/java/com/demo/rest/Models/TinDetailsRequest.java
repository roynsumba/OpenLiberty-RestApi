package com.demo.rest.Models;

import java.io.Serializable;

public class TinDetailsRequest implements Serializable {
    private String userName;
    private String password;
    private String signature;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
