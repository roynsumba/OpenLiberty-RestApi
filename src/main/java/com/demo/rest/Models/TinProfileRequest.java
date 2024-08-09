package com.demo.rest.Models;

import java.io.Serializable;

public class TinProfileRequest implements Serializable  {
    
    private TinDetailsRequest authentication;
    private String tinNo;

    // Getters and setters
    public TinDetailsRequest getAuthentication() {
        return authentication;
    }

    public void setAuthentication(TinDetailsRequest authentication) {
        this.authentication = authentication;
    }

    public String getTinNo() {
        return tinNo;
    }

    public void setTinNo(String tinNo) {
        this.tinNo = tinNo;
    } 

}
