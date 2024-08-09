package com.demo.rest.Models;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TinProfilePayload implements Serializable {
    @JsonProperty("data")
    private List<TinProfileDataItem> data;
    private String message;
    private int statusCode;


    public List<TinProfileDataItem> getData() {
        return data;
    }

    public void setData(List<TinProfileDataItem> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}

