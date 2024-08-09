package com.demo.rest.Models;

import java.io.Serializable;

public class TinDetailsPayload implements Serializable {

public String tin ; 
public String taxPayerName ;
public String taxPayerType ; 
public String tinStatus ;
public String message ;

public String gettin() {
  return tin;
}

public void settin(String tin) {
  this.tin = tin;
}

public String gettaxPayerName() {
  return taxPayerName;
}

public void settaxPayerName(String taxPayerName) {
  this.taxPayerName = taxPayerName;
}

public String gettaxPayerType() {
  return taxPayerType;
}

public void settaxPayerType(String taxPayerType) {
  this.taxPayerType = taxPayerType;
}

public String gettinStatus() {
  return tinStatus;
}

public void settinStatus(String tinStatus) {
  this.tinStatus = tinStatus;
}

public String getmessage() {
  return message;
}

public void setmessage(String message) {
  this.message = message;
}
  
}