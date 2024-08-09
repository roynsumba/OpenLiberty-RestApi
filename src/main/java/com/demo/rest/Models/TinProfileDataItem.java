package com.demo.rest.Models;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TinProfileDataItem implements Serializable {
  
        @JsonProperty("SurName")
        private String SurName;
        @JsonProperty("FirstName")
        private String FirstName;
        @JsonProperty("MiddleName")
        private String MiddleName;
        @JsonProperty("BirthDt")
        private String BirthDt;
        @JsonProperty("Nin")
        private String Nin;
        @JsonProperty("TaxPayerType")
        private String TaxPayerType;

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
