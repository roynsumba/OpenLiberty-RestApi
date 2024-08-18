package com.demo.rest.Data;

import java.io.Serializable;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.GenerationType;

@Entity
@Table(name = "TaxInfo")
@NamedQuery(name = "TaxInfo.findAll", query = "SELECT e FROM TaxInfo e")
// @NamedQuery(name = "TaxInfo.findEvent", query = "SELECT e FROM TaxInfo e WHERE "
//     + "e.tin = :tin ")
public class TaxInfo implements Serializable {


    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column()
    private int id;
    @Column()
    public String tin ; 
    @Column()
    private String taxPayerName ;
    @Column()
    private String taxPayerType ; 
    @Column()
    private String tinStatus ;
    @Column()
    private String message ;

    public TaxInfo() {
    }

    public TaxInfo(String tin, String taxPayerName, String taxPayerType, String tinStatus, String message) {
        this.tin = tin; 
        this.taxPayerName = taxPayerName;
        this.taxPayerType = taxPayerType;
        this.tinStatus = tinStatus;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTin() {
        return tin;
    }

    public void setTin(String tin) {
        this.tin = tin;
    }

    public String getTaxPayerName() {
        return taxPayerName;
    }

    public void settTaxPayerName(String taxPayerName) {
        this.taxPayerName = taxPayerName;
    }

    public void setTaxPayerType(String taxPayerType) {
        this.taxPayerType = taxPayerType;
    }

    public String getTaxPayerType() {
        return taxPayerType; 
    } 

    public void setTinStatus(String tinStatus) {
      this.tinStatus = tinStatus;
  }

  public String getTinStatus() {
      return tinStatus; 
  }  

  public void setMessage(String message) {
    this.message = message;
}

public String getMessage() {
    return message; 
} 


    @Override
    public String toString() {
        return "Event [tin=" + tin + ", taxPayerType=" + taxPayerType + ", taxPayerName=" + taxPayerName + ", tinStatus=" + tinStatus + ", message=" + message
                + "]";
    }
}