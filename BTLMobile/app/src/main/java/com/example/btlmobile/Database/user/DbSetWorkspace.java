package com.example.btlmobile.Database.user;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.btlmobile.Database.MyDatabase;
import com.example.btlmobile.MyApplication;
import com.example.btlmobile.model.Workspace;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

public class DbSetWorkspace{
    public static final String CONTACTS_TABLE_NAME = "Workspaces";
    public static final String CONTACTS_COLUMN_ID = "Workspace_Id";
    public static final String CONTACTS_COLUMN_WORKSPACE_NAME = "Workspace_Name";
    public static final String CONTACTS_COLUMN_COMPANY_NAME = "company_name";
    public static final String CONTACTS_COLUMN_EMAIL = "Email";
    public static final String CONTACTS_COLUMN_DATE_CREATED = "Date_Created_Wsp";
    public static final String CONTACTS_COLUMN_WORK_TIME = "Work_Time";
    public static final String CONTACTS_COLUMN_END_WORK_TIME = "End_Work_Time";
    public static final String CONTACTS_COLUMN_LATE_TIME = "Late_Time_checkIn";
    public static final String CONTACTS_COLUMN_PROVINCE = "Province";

    private Context _context;
    private MyDatabase myDatabase;

    public DbSetWorkspace(Context context) {
        _context = context;
        myDatabase = MyApplication.getInstace().getDb();
    }


    public boolean insert(Workspace workspace){
        SQLiteDatabase db = myDatabase.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CONTACTS_COLUMN_WORKSPACE_NAME, workspace.getNameWorkspace());
        contentValues.put(CONTACTS_COLUMN_EMAIL, workspace.getEmail());
        contentValues.put(CONTACTS_COLUMN_COMPANY_NAME, workspace.getNameCompany());
//        contentValues.put(CONTACTS_COLUMN_ADMIN, workspace.getAdmin());
        contentValues.put(CONTACTS_COLUMN_DATE_CREATED, workspace.getDateCreatedWsp());
        contentValues.put(CONTACTS_COLUMN_WORK_TIME, workspace.getWorkTime());
        contentValues.put(CONTACTS_COLUMN_END_WORK_TIME, workspace.getEndWorkTime());
        contentValues.put(CONTACTS_COLUMN_LATE_TIME, workspace.getLateTimeCheckIn());
        contentValues.put(CONTACTS_COLUMN_PROVINCE, workspace.getProvince());
        long res = db.insert(CONTACTS_TABLE_NAME, null, contentValues);
        return res > 0;
    }

    public void update(Workspace workspace){
        SQLiteDatabase db = myDatabase.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CONTACTS_COLUMN_WORKSPACE_NAME, workspace.getNameWorkspace());
        contentValues.put(CONTACTS_COLUMN_EMAIL, workspace.getEmail());
        contentValues.put(CONTACTS_COLUMN_COMPANY_NAME, workspace.getNameCompany());
//        contentValues.put(CONTACTS_COLUMN_ADMIN, workspace.getAdmin());
        contentValues.put(CONTACTS_COLUMN_DATE_CREATED, workspace.getDateCreatedWsp());
        contentValues.put(CONTACTS_COLUMN_WORK_TIME, workspace.getWorkTime());
        contentValues.put(CONTACTS_COLUMN_END_WORK_TIME, workspace.getEndWorkTime());
        contentValues.put(CONTACTS_COLUMN_LATE_TIME, workspace.getLateTimeCheckIn());
        contentValues.put(CONTACTS_COLUMN_PROVINCE, workspace.getProvince());

        db.update(CONTACTS_TABLE_NAME, contentValues, "Workspace_Id = ?", new String[]{String.valueOf(workspace.getIdWorkspace())});
    }

    public void delete(Workspace workspace){
        SQLiteDatabase db = myDatabase.getWritableDatabase();
        db.delete(CONTACTS_TABLE_NAME, "Workspace_Id = ?", new String[]{String.valueOf(workspace.getIdWorkspace())});
    }

    public List<Workspace> getAll(){
        SQLiteDatabase db = myDatabase.getReadableDatabase();
        List<Workspace> WorkspaceList = new ArrayList<>();
        Cursor cursor = db.rawQuery(String.format("SELECT * FROM %s", CONTACTS_TABLE_NAME), null);
        if(cursor.getCount() <= 0){
            return null;
        }
        while (cursor.moveToNext()){
            Workspace workspace = new Workspace();
            workspace.setIdWorkspace(cursor.getInt(0));
            workspace.setNameWorkspace(cursor.getString(1));
            workspace.setNameCompany(cursor.getString(2));
            workspace.setEmail(cursor.getString(3));
//            workspace.setAdmin(cursor.getString(4));
            workspace.setDateCreatedWsp(cursor.getString(4));
            workspace.setWorkTime(cursor.getString(5));
            workspace.setEndWorkTime(cursor.getString(6));
            workspace.setLateTimeCheckIn(cursor.getString(7));
            workspace.setProvince(cursor.getString(8));

            WorkspaceList.add(workspace);
        }
        return WorkspaceList;
    }

    public Workspace getById(int id){
        SQLiteDatabase db = myDatabase.getReadableDatabase();
        Cursor cursor = db.rawQuery(String.format("SELECT * FROM %s WHERE %s = ?",
                CONTACTS_TABLE_NAME, CONTACTS_COLUMN_ID), new String[]{String.valueOf(id)});
        if(cursor.getCount() <= 0){
            return null;
        }
        cursor.moveToNext();

        Workspace workspace = new Workspace();

        workspace.setIdWorkspace(cursor.getInt(0));
        workspace.setNameWorkspace(cursor.getString(1));
        workspace.setNameCompany(cursor.getString(2));
        workspace.setEmail(cursor.getString(3));
        workspace.setDateCreatedWsp(cursor.getString(4));
        workspace.setWorkTime(cursor.getString(5));
        workspace.setEndWorkTime(cursor.getString(6));
        workspace.setLateTimeCheckIn(cursor.getString(7));
        workspace.setProvince(cursor.getString(8));

        return workspace;
    }

    public int lastInsertRowId(){
        SQLiteDatabase db = myDatabase.getReadableDatabase();
        Cursor cursor = db.rawQuery(String.format("SELECT last_insert_rowid()"), null);
        cursor.moveToNext();
        return cursor.getInt(0);
    }
}
