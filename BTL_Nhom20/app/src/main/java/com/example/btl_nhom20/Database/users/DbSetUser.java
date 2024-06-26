package com.example.btl_nhom20.Database.users;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

//import com.example.btl_nhom20.Database.MyDatabase;
import com.example.btl_nhom20.Database.users.weathers.MyDatabase;
import com.example.btl_nhom20.MyApplication;
import com.example.btl_nhom20.model.user.User;

import java.util.ArrayList;
import java.util.List;

public class DbSetUser {
    public static final String CONTACTS_TABLE_NAME = "Users";
    public static final String CONTACTS_COLUMN_ID = "User_Id";
    public static final String CONTACTS_COLUMN_NAME = "User_Name";
    public static final String CONTACTS_COLUMN_EMAIL = "User_Email";
    public static final String CONTACTS_COLUMN_PASSWORD = "User_Password";
    public static final String CONTACTS_COLUMN_DATEOF_EMPLOYMENT = "DateOf_Employment";
//    public static final String CONTACTS_COLUMN_WORKSPACE_ID = "Workspace_Id";

    private Context _context;
    private MyDatabase myDatabase;
    public DbSetUser(Context context){
        _context = context;
        myDatabase = MyApplication.getInstace().getDb();
    }

    public void insert(User user){
        SQLiteDatabase db = myDatabase.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CONTACTS_COLUMN_NAME, user.getUsername());
        contentValues.put(CONTACTS_COLUMN_EMAIL, user.getEmail());
        contentValues.put(CONTACTS_COLUMN_PASSWORD, user.getPassword());
//        contentValues.put(CONTACTS_COLUMN_DATEOF_EMPLOYMENT, user.getDateOfEmployment());
        db.insert(CONTACTS_TABLE_NAME, null, contentValues);
    }

    public boolean update(User user){
        SQLiteDatabase db = myDatabase.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        if(user.getUsername() != null)
            contentValues.put(CONTACTS_COLUMN_NAME, user.getUsername());
        if(user.getEmail() != null){
            if(!checkExistEmail(user.getEmail())){
                contentValues.put(CONTACTS_COLUMN_EMAIL, user.getEmail());
            }else{
                return false;
            }
        }
        if(user.getPassword() != null)
            contentValues.put(CONTACTS_COLUMN_PASSWORD, user.getPassword());

        return db.update(CONTACTS_TABLE_NAME, contentValues, "User_Id = ?", new String[]{String.valueOf(user.getUid())}) > 0;

    }



    public void delete(User user){
        SQLiteDatabase db = myDatabase.getWritableDatabase();
        db.delete(CONTACTS_TABLE_NAME, "User_Id = ?", new String[]{String.valueOf(user.getUid())});
    }

    public List<User> getAll(){
        SQLiteDatabase db = myDatabase.getReadableDatabase();
        List<User> userList = new ArrayList<>();
        Cursor cursor = db.rawQuery(String.format("SELECT * FROM %s", CONTACTS_TABLE_NAME), null);
        while (cursor.moveToNext()){
            User user = new User();
            user.setUid(cursor.getInt(0));
            user.setUsername(cursor.getString(1));
            user.setEmail(cursor.getString(2));
            user.setPassword(cursor.getString(3));
//            user.setDateOfEmployment(cursor.getString(4));
            userList.add(user);
        }
        return userList;
    }

    public User getByID(int id){
        SQLiteDatabase db = myDatabase.getReadableDatabase();
        Cursor cursor = db.rawQuery(String.format("SELECT * FROM %s WHERE %s = ?", CONTACTS_TABLE_NAME, CONTACTS_COLUMN_ID), new String[]{String.valueOf(id)});
        cursor.moveToNext();
        User user = new User();
        user.setUid(cursor.getInt(0));
        user.setUsername(cursor.getString(1));
        user.setEmail(cursor.getString(2));
        user.setPassword(cursor.getString(3));
//        user.setDateOfEmployment(cursor.getString(4));
        return user;
    }

    public boolean checkExistEmail(String email){
        SQLiteDatabase db = myDatabase.getReadableDatabase();
        Cursor cursor = db.rawQuery(String.format("SELECT * FROM %s WHERE %s = ?",
                CONTACTS_TABLE_NAME, CONTACTS_COLUMN_EMAIL), new String[]{email});
        Log.i("checkExistEmail", String.valueOf(cursor.getCount()));
        if(cursor.getCount() > 0){
            return true;
        }else{
            return false;
        }
    }

    public User auth(String email, String password) {
        SQLiteDatabase db = myDatabase.getReadableDatabase();

        Cursor cursor = db.rawQuery(String.format("SELECT * FROM %s WHERE %s = ? AND %s = ?",
                CONTACTS_TABLE_NAME, CONTACTS_COLUMN_EMAIL, CONTACTS_COLUMN_PASSWORD), new String[]{email, password});
        if (cursor.getCount() <= 0) {
            return null;
        }
        cursor.moveToNext();
        User user = new User();
        user.setUid(cursor.getInt(0));
        user.setUsername(cursor.getString(1));
        user.setEmail(cursor.getString(2));
        user.setPassword(cursor.getString(3));
//        user.setDateOfEmployment(cursor.getString(4));
        return user;
    }
}
