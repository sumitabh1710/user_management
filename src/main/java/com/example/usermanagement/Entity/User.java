package com.example.usermanagement.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private String speciality;

    @Column(unique = true)
    private String email;

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getSpeciality() {
        return speciality;
    }

    public String getEmail() {
        return email;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User(Long id, String username, String password, String speciality, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.speciality = speciality;
        this.email = email;
    }

    public User() {
    }

}
