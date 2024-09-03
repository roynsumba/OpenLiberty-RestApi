package com.demo.rest.Data;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;

// import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "tin_profile_info")
@NamedQuery(name = "TinProfilePayloadEntity.findAll", query = "SELECT e FROM TinProfilePayloadEntity e")
public class TinProfilePayloadEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

// @JsonProperty("data")  
    @OneToMany(mappedBy = "tinProfilePayload", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TinProfileData> data;

    @Column(name = "message")
    private String message;

    @Column(name = "status_code")
    private int statusCode;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<TinProfileData> getData() {
        return data;
    }

    public void setData(List<TinProfileData> data) {
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