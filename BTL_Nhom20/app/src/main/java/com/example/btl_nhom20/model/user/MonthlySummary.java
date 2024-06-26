package com.example.btl_nhom20.model.user;

public class MonthlySummary {
    private int month,numWorkOnTime,numLateForWork,numOffWork;
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public MonthlySummary(int month, int numWorkOnTime, int numLateForWork, int numOffWork) {
        this.month = month;
        this.numWorkOnTime = numWorkOnTime;
        this.numLateForWork = numLateForWork;
        this.numOffWork = numOffWork;
    }

    public MonthlySummary(int month, int numWorkOnTime, int numLateForWork, int numOffWork, String username) {
        this.month = month;
        this.numWorkOnTime = numWorkOnTime;
        this.numLateForWork = numLateForWork;
        this.numOffWork = numOffWork;
        this.username = username;
    }

    public MonthlySummary(){}

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getNumWorkOnTime() {
        return numWorkOnTime;
    }

    public void setNumWorkOnTime(int numWorkOnTime) {
        this.numWorkOnTime = numWorkOnTime;
    }

    public int getNumLateForWork() {
        return numLateForWork;
    }

    public void setNumLateForWork(int numLateForWork) {
        this.numLateForWork = numLateForWork;
    }

    public int getNumOffWork() {
        return numOffWork;
    }

    public void setNumOffWork(int numOffWork) {
        this.numOffWork = numOffWork;
    }
}
