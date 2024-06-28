package com.example.btl_nhom20.model.user;

import java.io.Serializable;
import java.util.List;

public class Workspace implements Serializable {
    private int idWorkspace;
    private List<User> employees;
    private String nameWorkspace;
    private String nameCompany;
    private String workTime;
    private String endWorkTime;
    private String lateTimeCheckIn;
    private String province;
    private String email;
    private String dateCreatedWsp;


    public Workspace(){

    }

    public Workspace(int idWorkspace, String nameWorkspace, String nameCompany, String workTime, String endWorkTime, String lateTimeCheckIn, String province, String email) {
        this.idWorkspace = idWorkspace;
        this.nameWorkspace = nameWorkspace;
        this.nameCompany = nameCompany;
        this.workTime = workTime;
        this.endWorkTime = endWorkTime;
        this.lateTimeCheckIn = lateTimeCheckIn;
        this.province = province;
        this.email = email;
    }



    public String getDateCreatedWsp() {
        return dateCreatedWsp;
    }

    public void setDateCreatedWsp(String dateCreatedWsp) {
        this.dateCreatedWsp = dateCreatedWsp;
    }

    public int getIdWorkspace() {
        return idWorkspace;
    }

    public void setIdWorkspace(int idWorkspace) {
        this.idWorkspace = idWorkspace;
    }

    public List<User> getEmployees() {
        return employees;
    }

    public void setEmployees(List<User> employees) {
        this.employees = employees;
    }

    public String getNameWorkspace() {
        return nameWorkspace;
    }

    public void setNameWorkspace(String nameWorkspace) {
        this.nameWorkspace = nameWorkspace;
    }

    public String getNameCompany() {
        return nameCompany;
    }

    public void setNameCompany(String nameCompany) {
        this.nameCompany = nameCompany;
    }

    public String getWorkTime() {
        return workTime;
    }

    public void setWorkTime(String workTime) {
        this.workTime = workTime;
    }

    public String getEndWorkTime() {
        return endWorkTime;
    }

    public void setEndWorkTime(String endWorkTime) {
        this.endWorkTime = endWorkTime;
    }

    public String getLateTimeCheckIn() {
        return lateTimeCheckIn;
    }

    public void setLateTimeCheckIn(String lateTimeCheckIn) {
        this.lateTimeCheckIn = lateTimeCheckIn;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
