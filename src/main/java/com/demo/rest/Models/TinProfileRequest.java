package com.demo.rest.Models;

import java.io.Serializable;

public class TinProfileRequest implements Serializable  {
    
    private Authentication authentication;
    private String tinNo;

    // Getters and setters
    public Authentication getAuthentication() {
        return authentication;
    }

    public void setAuthentication(Authentication authentication) {
        this.authentication = authentication;
    }

    public String getTinNo() {
        return tinNo;
    }

    public void setTinNo(String tinNo) {
        this.tinNo = tinNo;
    } 

}
