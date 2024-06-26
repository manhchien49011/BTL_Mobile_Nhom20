package com.example.btl_nhom20.model.user;

public class ModelCalendar {
    int User_Id;
    int Workspace_Id;
    private int Year = -1;
    private int Month = -1;
    private int Day = -1;
    private int Hour=-1;
    private int Minute=-1;
    private String Type;
    private String DateOfEmployment;

    public String getDateOfEmployment() {
        return DateOfEmployment;
    }

    public void setDateOfEmployment(String dateOfEmployment) {
        DateOfEmployment = dateOfEmployment;
    }

    public ModelCalendar(){
        User_Id = -1;
        Workspace_Id = -1;
        Year = -1;
        Month = -1;
        Day = -1;
        Hour = -1;
        Minute = -1;
        Type = "";
        DateOfEmployment = "";
    }

    public ModelCalendar(int user_Id, int workspace_Id, int year, int month, int day, int hour, int minute, String type) {
        User_Id = user_Id;
        Workspace_Id = workspace_Id;
        Year = year;
        Month = month;
        Day = day;
        Hour = hour;
        Minute = minute;
        Type = type;
    }


    public int getUser_Id() {
        return User_Id;
    }

    public void setUser_Id(int user_Id) {
        User_Id = user_Id;
    }

    public int getWorkspace_Id() {
        return Workspace_Id;
    }

    public void setWorkspace_Id(int workspace_Id) {
        Workspace_Id = workspace_Id;
    }

    public int getYear() {
        return Year;
    }

    public void setYear(int year) {
        Year = year;
    }

    public int getMonth() {
        return Month;
    }

    public void setMonth(int month) {
        Month = month;
    }

    public int getDay() {
        return Day;
    }

    public void setDay(int day) {
        Day = day;
    }

    public int getHour() {
        return Hour;
    }

    public void setHour(int hour) {
        Hour = hour;
    }

    public int getMinute() {
        return Minute;
    }

    public void setMinute(int minute) {
        Minute = minute;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }


}
