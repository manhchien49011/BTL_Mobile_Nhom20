package com.example.btl_nhom20.Reponsity;

import android.content.Context;

import com.example.btl_nhom20.Database.users.DbSetCalendar;
import com.example.btl_nhom20.model.user.ModelCalendar;

import java.util.ArrayList;
import java.util.List;

public class ReponsityCalendar {
    private DbSetCalendar dbSetCalendar;
    public ReponsityCalendar(Context context) {
        dbSetCalendar = new DbSetCalendar(context);
    }

    public List<ModelCalendar> getAll(){
        return dbSetCalendar.getAll();
    }

    public boolean insert(ModelCalendar calendar){
        return dbSetCalendar.insert(calendar);
    }

    public boolean update(ModelCalendar calendar){
        return dbSetCalendar.update(calendar);
    }

    public boolean delete(ModelCalendar calendar){
        return dbSetCalendar.delete(calendar);
    }

    public List<ModelCalendar> getByUser(int user_Id){
        return dbSetCalendar.getByUser(user_Id);
    }

    public List<ModelCalendar> getByWorkspace(int workspace_Id){
        return dbSetCalendar.getByWorkspace(workspace_Id);
    }

//    public boolean rawQuery(String query, Objects[] args){
//        return dbSetCalendar.rawQuery(query, args);
//    }
//
//    public boolean rawUpdate(String where, String[] args, ModelCalendar calendar){
//        return dbSetCalendar.rawUpdate(where, args, calendar);
//    }

    public List<ModelCalendar> getByUserAndWorkspace(int user_Id, int workspace_Id){
        return dbSetCalendar.getByUserAndWorkspace(user_Id, workspace_Id);
    }

    public List<Integer> getDistinctWorkspace(int user_Id){
        return dbSetCalendar.getDistinctWorkspace_byUser(user_Id);
    }

    public List<ModelCalendar> getByYear(int year){
        List<ModelCalendar> calendars = new ArrayList<>();
        calendars = getAll();
        List<ModelCalendar> result = new ArrayList<>();
        for (ModelCalendar calendar : calendars){
            if (calendar.getYear() == year){
                result.add(calendar);
            }
        }
        return result;
    }
}
