package com.example.AP_A3;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "userprofile")
public class UserModel implements Serializable {
    
    @Id
    String username;
    String name;
    String password;
    String gender;
    String dob;
    
    //Constructors
    public UserModel(){
        
    }

    public UserModel(String username, String name, String password, String gender, String dob){
        this.username = username;
        this.gender = gender;
        this.name = name;
        this.password = password;
        this.dob = dob;
    }

    //Setters
    public void setUsername(String username) {
        this.username = username;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    
    public void setDob(String dob) {
        this.dob = dob;
    }
    
    //Getters
    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getGender() {
        return gender;
    }
    
    public String getDob() {
        return dob;
    }

    
}
