package com.example.btl_nhom20.Reponsity;

import android.content.Context;

import com.example.btl_nhom20.Database.users.DbSetWorkspace;
import com.example.btl_nhom20.model.user.Workspace;

import java.util.ArrayList;
import java.util.List;

public class ReponsityWorkspace {

    public static List<Workspace> data;
    DbSetWorkspace dbSetWorkspace;
    public ReponsityWorkspace(Context context){
        data = new ArrayList<>();
        dbSetWorkspace = new DbSetWorkspace(context);
//        data = dbSetWorkspace.getAll();
    }
    public List<Workspace> getAll(){
        return dbSetWorkspace.getAll();
    }
    public boolean insert(Workspace workspace){
        return dbSetWorkspace.insert(workspace);
    }
    public void update(Workspace workspace){
        dbSetWorkspace.update(workspace);
    }
    public void delete(Workspace workspace){
        dbSetWorkspace.delete(workspace);
    }
    public Workspace getById(int id){
        return dbSetWorkspace.getById(id);
    }
//    public List<Workspace> getByAdmin(String admin){
//        return dbSetWorkspace.getByAdmin(admin);
//    }

    public int lastInsertRowId(){
        return dbSetWorkspace.lastInsertRowId();
    }
}
