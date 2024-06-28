package com.example.btl_nhom20.Database.users;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

//import com.example.btl_nhom20.Database.MyDatabase;
import com.example.btl_nhom20.Database.users.weathers.MyDatabase;
import com.example.btl_nhom20.MyApplication;
import com.example.btl_nhom20.model.user.UserWorkspace;

import java.util.ArrayList;
import java.util.List;

public class DbSetUserWorkspace {
    public static final String CONTACTS_TABLE_NAME = "UserWorkspace";
    public static final String CONTACTS_COLUMN_USER_ID = "User_Id";
    public static final String CONTACTS_COLUMN_WORKSPACE_ID = "Workspace_Id";
    public static final String CONTACTS_COLUMN_IS_ADMIN = "Is_Admin";
    Context context;
    MyDatabase myDatabase;
    public DbSetUserWorkspace(Context context) {
        this.context = context;
        myDatabase = MyApplication.getInstace().getDb();
    }

    public boolean insert(UserWorkspace userWorkspace) {
        try{
            SQLiteDatabase db = myDatabase.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(CONTACTS_COLUMN_USER_ID, userWorkspace.getUserId());
            values.put(CONTACTS_COLUMN_WORKSPACE_ID, userWorkspace.getWorkspaceId());
            values.put(CONTACTS_COLUMN_IS_ADMIN, userWorkspace.isAdmin());
            db.insert(CONTACTS_TABLE_NAME, null, values);
            return true;
        }
        catch (Exception e){
            Log.e("Exception", e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public List<UserWorkspace> getByUserId(int userId) {
        SQLiteDatabase db = myDatabase.getReadableDatabase();
        List<UserWorkspace> list = new ArrayList<>();
        Cursor cursor = db.query(CONTACTS_TABLE_NAME, null, CONTACTS_COLUMN_USER_ID + "=?", new String[]{String.valueOf(userId)}, null, null, null);

//        int userIdIndex = cursor.getColumnIndex(CONTACTS_COLUMN_USER_ID);
        int workspaceIdIndex = cursor.getColumnIndex(CONTACTS_COLUMN_WORKSPACE_ID);
        int isAdminIndex = cursor.getColumnIndex(CONTACTS_COLUMN_IS_ADMIN);

        while (cursor.moveToNext()) {
            int workspaceId = cursor.getInt(workspaceIdIndex);
            boolean isAdmin = cursor.getInt(isAdminIndex) == 1;
            list.add(new UserWorkspace(userId, workspaceId, isAdmin));
        }
        return list;
    }

    public List<UserWorkspace> getByWorkspaceId(int workspaceId) {
        SQLiteDatabase db = myDatabase.getReadableDatabase();
        List<UserWorkspace> list = new ArrayList<>();
        Cursor cursor = db.query(CONTACTS_TABLE_NAME, null, CONTACTS_COLUMN_WORKSPACE_ID + "=?", new String[]{String.valueOf(workspaceId)}, null, null, null);

        int userIdIndex = cursor.getColumnIndex(CONTACTS_COLUMN_USER_ID);
//        int workspaceIdIndex = cursor.getColumnIndex(CONTACTS_COLUMN_WORKSPACE_ID);
        int isAdminIndex = cursor.getColumnIndex(CONTACTS_COLUMN_IS_ADMIN);
        while (cursor.moveToNext()) {
            int userId = cursor.getInt(userIdIndex);
            boolean isAdmin = cursor.getInt(isAdminIndex) == 1;
            list.add(new UserWorkspace(userId, workspaceId, isAdmin));
        }
        return list;
    }

    public List<UserWorkspace> getWorkspaceByUserId(int userId) {
//        SQLiteDatabase db = myDatabase.getReadableDatabase();
        return  null;
    }
}
