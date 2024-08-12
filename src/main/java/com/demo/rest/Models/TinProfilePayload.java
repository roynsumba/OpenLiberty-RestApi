package com.demo.rest.Models;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)  // Add this annotation
public class TinProfilePayload implements Serializable {


    @JsonProperty("data")
    private List<TinProfileDataItem> data;
    private String message;
    private int statusCode;
    public List<TinProfileDataItem> getData() {
        return data;
    }

     @JsonProperty("errors")
    private Map<String, Object> errors;  // Add this field to handle the errors

    // Getters and setters for the new field
    public Map<String, Object> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, Object> errors) {
        this.errors = errors;
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

