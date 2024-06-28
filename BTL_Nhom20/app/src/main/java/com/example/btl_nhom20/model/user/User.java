package com.example.btl_nhom20.model.user;

import java.util.List;

public class User {
    private String username;
    private String email;
    private int uid;
    private String password;
    private List<Workspace> workspace;
    private String dateOfEmployment;
    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

//    public User(String username, String email, String dateOfEmployment, String password) {
//        this.username = username;
//        this.email = email;
//        this.password = password;
//        this.dateOfEmployment = dateOfEmployment;
//    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public User() {
    }

    public String getDateOfEmployment() {
        return dateOfEmployment;
    }

    public void setDateOfEmployment(String dateOfEmployment) {
        this.dateOfEmployment = dateOfEmployment;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public List<Workspace> getWorkspace() {
        return workspace;
    }

    public void setWorkspace(List<Workspace> workspace) {
        this.workspace = workspace;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

}
