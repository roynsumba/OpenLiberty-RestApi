package com.demo.rest.Data;

import jakarta.persistence.*;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "tin_profile_data")
@NamedQuery(name = "tin_profile_data.findone", query="SELECT e FROM TinProfilePayloadEntity e LEFT JOIN FETCH e.data WHERE e.id = :apiResponseId")
public class TinProfileData implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "api_response_id")
    private TinProfilePayloadEntity tinProfilePayload;

    @JsonProperty("SurName")
    @Column(name = "sur_name")
    private String SurName;

    @JsonProperty("FirstName")
    @Column(name = "first_name")
    private String FirstName;

    @JsonProperty("MiddleName")
    @Column(name = "middle_name")
    private String MiddleName;

    @JsonProperty("BirthDt")
    @Column(name = "birth_date")
    private String BirthDt;

    @JsonProperty("Nin")
    @Column(name = "nin")
    private String Nin;

    @JsonProperty("TaxPayerType")
    @Column(name = "tax_payer_type")
    private String TaxPayerType; 

    

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    } 


    public TinProfilePayloadEntity getTinProfilePayload() {
        return tinProfilePayload;
    }

    public void setTinProfilePayload(TinProfilePayloadEntity tinProfilePayload) {
        this.tinProfilePayload = tinProfilePayload;
    }

    public String getSurName() {
        return SurName;
    }

    public void setSurName(String surName) {
        this.SurName = surName;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        this.FirstName = firstName;
    }

    public String getMiddleName() {
        return MiddleName;
    }

    public void setMiddleName(String middleName) {
        this.MiddleName = middleName;
    }

    public String getBirthDt() {
        return BirthDt;
    }

    public void setBirthDt(String birthDt) {
        this.BirthDt = birthDt;
    }

    public String getNin() {
        return Nin;
    }

    public void setNin(String nin) {
        this.Nin = nin;
    }

    public String getTaxPayerType() {
        return TaxPayerType;
    }

    public void setTaxPayerType(String taxPayerType) {
        this.TaxPayerType = taxPayerType;
    }
}