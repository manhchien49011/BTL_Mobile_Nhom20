package com.example.btl_nhom20.Database.users;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

//import com.example.btl_nhom20.Database.MyDatabase;
import com.example.btl_nhom20.Database.users.weathers.MyDatabase;
import com.example.btl_nhom20.MyApplication;
import com.example.btl_nhom20.model.user.ModelCalendar;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DbSetCalendar {

    public  final String CONTACTS_TABLE_NAME = "Calendar";
    public  final String CONTACTS_COLUMN_USER_ID = "User_Id";
    public  final String CONTACTS_COLUMN_WORKSPACE_ID = "Workspace_Id";
    public  final String CONTACTS_COLUMN_YEAR = "Year";
    public  final String CONTACTS_COLUMN_MONTH = "Month";
    public  final String CONTACTS_COLUMN_DAY = "Day";
    public  final String CONTACTS_COLUMN_HOUR = "Hour";
    public  final String CONTACTS_COLUMN_MINUTE = "Minute";
    public  final String CONTACTS_COLUMN_TYPE = "Type";
    public  final String CONTACTS_COLUMN_DATE_OF_EMPLOYMENT = "DateOf_Employment";

    Context context;
    MyDatabase myDatabase;
    public DbSetCalendar(Context context){
        this.context = context;
        myDatabase = MyApplication.getInstace().getDb();
    }

//    public boolean rawQuery(String sql, Object[] args){
//        try{
//            SQLiteDatabase db = myDatabase.getWritableDatabase();
//            db.execSQL(sql, args);
//            return true;
//        }
//        catch (Exception e){
//            return false;
//        }
//    }
//
//    public boolean rawUpdate(String where, String[] args, ModelCalendar calendar){
//        SQLiteDatabase db = myDatabase.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(CONTACTS_COLUMN_USER_ID, calendar.getUser_Id());
//        contentValues.put(CONTACTS_COLUMN_WORKSPACE_ID, calendar.getWorkspace_Id());
//        contentValues.put(CONTACTS_COLUMN_YEAR, calendar.getYear());
//        contentValues.put(CONTACTS_COLUMN_MONTH, calendar.getMonth());
//        contentValues.put(CONTACTS_COLUMN_DAY, calendar.getDay());
//        contentValues.put(CONTACTS_COLUMN_HOUR, calendar.getHour());
//        contentValues.put(CONTACTS_COLUMN_MINUTE, calendar.getMinute());
//        contentValues.put(CONTACTS_COLUMN_TYPE, calendar.getType());
//
//        return db.update(CONTACTS_TABLE_NAME, contentValues, where, args) > 0;
//    }

    public boolean insert(ModelCalendar calendar){
        SQLiteDatabase db = myDatabase.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CONTACTS_COLUMN_USER_ID, calendar.getUser_Id());
        contentValues.put(CONTACTS_COLUMN_WORKSPACE_ID, calendar.getWorkspace_Id());
        contentValues.put(CONTACTS_COLUMN_YEAR, calendar.getYear());
        contentValues.put(CONTACTS_COLUMN_MONTH, calendar.getMonth());
        contentValues.put(CONTACTS_COLUMN_DAY, calendar.getDay());
        contentValues.put(CONTACTS_COLUMN_HOUR, calendar.getHour());
        contentValues.put(CONTACTS_COLUMN_MINUTE, calendar.getMinute());
        contentValues.put(CONTACTS_COLUMN_TYPE, calendar.getType());
        contentValues.put(CONTACTS_COLUMN_DATE_OF_EMPLOYMENT, calendar.getDateOfEmployment());
        try {
            long result = db.insert(CONTACTS_TABLE_NAME, null, contentValues);
            return result > 0;
        }
        catch (Exception e){
            Log.e("insertCalendar", Objects.requireNonNull(e.getMessage()));
            return false;
        }

    }

    public boolean update(ModelCalendar calendar){
        SQLiteDatabase db = myDatabase.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(CONTACTS_COLUMN_USER_ID, calendar.getUser_Id());
        contentValues.put(CONTACTS_COLUMN_WORKSPACE_ID, calendar.getWorkspace_Id());
        if(calendar.getYear() > -1)
            contentValues.put(CONTACTS_COLUMN_YEAR, calendar.getYear());
        if(calendar.getMonth() > -1)
            contentValues.put(CONTACTS_COLUMN_MONTH, calendar.getMonth());
        if(calendar.getDay() > -1)
            contentValues.put(CONTACTS_COLUMN_DAY, calendar.getDay());
        if(calendar.getHour() > -1)
            contentValues.put(CONTACTS_COLUMN_HOUR, calendar.getHour());
        if(calendar.getMinute() > -1)
            contentValues.put(CONTACTS_COLUMN_MINUTE, calendar.getMinute());
        if(calendar.getType() != null)
            contentValues.put(CONTACTS_COLUMN_TYPE, calendar.getType());
        if(calendar.getDateOfEmployment() !=null)
            contentValues.put(CONTACTS_COLUMN_DATE_OF_EMPLOYMENT, calendar.getDateOfEmployment());

        String whereClause = String.format("%s = ? AND %s = ? AND %s = ? AND %s = ? AND %s = ?",
                CONTACTS_COLUMN_USER_ID, CONTACTS_COLUMN_WORKSPACE_ID, CONTACTS_COLUMN_YEAR, CONTACTS_COLUMN_MONTH, CONTACTS_COLUMN_DAY);
//            String whereClause = String.format("%s = ? AND %s = ? ",
//                                CONTACTS_COLUMN_USER_ID
        long result = db.update(CONTACTS_TABLE_NAME, contentValues, whereClause,
                new String[]{String.valueOf(calendar.getUser_Id()), String.valueOf(calendar.getWorkspace_Id()),
                        String.valueOf(calendar.getYear()), String.valueOf(calendar.getMonth()), String.valueOf(calendar.getDay())});

        return result > 0;
    }

    public boolean delete(ModelCalendar calendar){
        SQLiteDatabase db = myDatabase.getWritableDatabase();
        int result = db.delete(CONTACTS_TABLE_NAME,
                CONTACTS_COLUMN_USER_ID + " = ? AND " + CONTACTS_COLUMN_WORKSPACE_ID + " = ? ",
                new String[]{String.valueOf(calendar.getUser_Id()), String.valueOf(calendar.getWorkspace_Id())});
        return result > 0;
    }

    public List<ModelCalendar> getAll(){
        SQLiteDatabase db = myDatabase.getReadableDatabase();
        List<ModelCalendar> calendars = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + CONTACTS_TABLE_NAME, null);

        int indexUser_Id = cursor.getColumnIndex(CONTACTS_COLUMN_USER_ID);
        int indexWorkspace_Id = cursor.getColumnIndex(CONTACTS_COLUMN_WORKSPACE_ID);
        int indexYear = cursor.getColumnIndex(CONTACTS_COLUMN_YEAR);
        int indexMonth = cursor.getColumnIndex(CONTACTS_COLUMN_MONTH);
        int indexDay = cursor.getColumnIndex(CONTACTS_COLUMN_DAY);
        int indexHour = cursor.getColumnIndex(CONTACTS_COLUMN_HOUR);
        int indexMinute = cursor.getColumnIndex(CONTACTS_COLUMN_MINUTE);
        int indexType = cursor.getColumnIndex(CONTACTS_COLUMN_TYPE);
        int indexDateOfEmployment = cursor.getColumnIndex(CONTACTS_COLUMN_DATE_OF_EMPLOYMENT);

        while (cursor.moveToNext()){
            ModelCalendar calendar = new ModelCalendar();
            calendar.setUser_Id(cursor.getInt(indexUser_Id));
            calendar.setWorkspace_Id(cursor.getInt(indexWorkspace_Id));
            calendar.setYear(cursor.getInt(indexYear));
            calendar.setMonth(cursor.getInt(indexMonth));
            calendar.setDay(cursor.getInt(indexDay));
            calendar.setHour(cursor.getInt(indexHour));
            calendar.setMinute(cursor.getInt(indexMinute));
            calendar.setType(cursor.getString(indexType));
            calendar.setDateOfEmployment(cursor.getString(indexDateOfEmployment));
            calendars.add(calendar);

        }

        return calendars;
    }

    public List<ModelCalendar> getByUser(int user_Id){
        SQLiteDatabase db = myDatabase.getReadableDatabase();
        List<ModelCalendar> calendars = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + CONTACTS_TABLE_NAME + " WHERE " + CONTACTS_COLUMN_USER_ID + " = ?",
                new String[]{String.valueOf(user_Id)});

        int indexUser_Id = cursor.getColumnIndex(CONTACTS_COLUMN_USER_ID);
        int indexWorkspace_Id = cursor.getColumnIndex(CONTACTS_COLUMN_WORKSPACE_ID);
        int indexYear = cursor.getColumnIndex(CONTACTS_COLUMN_YEAR);
        int indexMonth = cursor.getColumnIndex(CONTACTS_COLUMN_MONTH);
        int indexDay = cursor.getColumnIndex(CONTACTS_COLUMN_DAY);
        int indexHour = cursor.getColumnIndex(CONTACTS_COLUMN_HOUR);
        int indexMinute = cursor.getColumnIndex(CONTACTS_COLUMN_MINUTE);
        int indexType = cursor.getColumnIndex(CONTACTS_COLUMN_TYPE);
        int indexDateOfEmployment = cursor.getColumnIndex(CONTACTS_COLUMN_DATE_OF_EMPLOYMENT);

        while (cursor.moveToNext()){
            ModelCalendar calendar = new ModelCalendar();
            calendar.setUser_Id(cursor.getInt(indexUser_Id));
            calendar.setWorkspace_Id(cursor.getInt(indexWorkspace_Id));
            calendar.setYear(cursor.getInt(indexYear));
            calendar.setMonth(cursor.getInt(indexMonth));
            calendar.setDay(cursor.getInt(indexDay));
            calendar.setHour(cursor.getInt(indexHour));
            calendar.setMinute(cursor.getInt(indexMinute));
            calendar.setType(cursor.getString(indexType));
            calendar.setDateOfEmployment(cursor.getString(indexDateOfEmployment));
            calendars.add(calendar);

        }

        return calendars;

    }

    public List<ModelCalendar> getByWorkspace(int workspace_Id){
        SQLiteDatabase db = myDatabase.getReadableDatabase();
        List<ModelCalendar> calendars = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + CONTACTS_TABLE_NAME + " WHERE " + CONTACTS_COLUMN_WORKSPACE_ID + " = ?",
                new String[]{String.valueOf(workspace_Id)});

        int indexUser_Id = cursor.getColumnIndex(CONTACTS_COLUMN_USER_ID);
        int indexWorkspace_Id = cursor.getColumnIndex(CONTACTS_COLUMN_WORKSPACE_ID);
        int indexYear = cursor.getColumnIndex(CONTACTS_COLUMN_YEAR);
        int indexMonth = cursor.getColumnIndex(CONTACTS_COLUMN_MONTH);
        int indexDay = cursor.getColumnIndex(CONTACTS_COLUMN_DAY);
        int indexHour = cursor.getColumnIndex(CONTACTS_COLUMN_HOUR);
        int indexMinute = cursor.getColumnIndex(CONTACTS_COLUMN_MINUTE);
        int indexType = cursor.getColumnIndex(CONTACTS_COLUMN_TYPE);
        int indexDateOfEmployment = cursor.getColumnIndex(CONTACTS_COLUMN_DATE_OF_EMPLOYMENT);

        while (cursor.moveToNext()){
            ModelCalendar calendar = new ModelCalendar();
            calendar.setUser_Id(cursor.getInt(indexUser_Id));
            calendar.setWorkspace_Id(cursor.getInt(indexWorkspace_Id));
            calendar.setYear(cursor.getInt(indexYear));
            calendar.setMonth(cursor.getInt(indexMonth));
            calendar.setDay(cursor.getInt(indexDay));
            calendar.setHour(cursor.getInt(indexHour));
            calendar.setMinute(cursor.getInt(indexMinute));
            calendar.setType(cursor.getString(indexType));
            calendar.setDateOfEmployment(cursor.getString(indexDateOfEmployment));
            calendars.add(calendar);

        }

        return calendars;

    }

    public List<ModelCalendar> getByUserAndWorkspace(int user_Id, int workspace_Id){
        SQLiteDatabase db = myDatabase.getReadableDatabase();
        List<ModelCalendar> calendars = new ArrayList<>();
        String whereClause = String.format("%s = ? AND %s = ? ",
                CONTACTS_COLUMN_USER_ID, CONTACTS_COLUMN_WORKSPACE_ID);
        try{

            Cursor cursor = db.query(CONTACTS_TABLE_NAME, null, whereClause,
                    new String[]{String.valueOf(user_Id), String.valueOf(workspace_Id)},
                    null, null, null);
            Log.i("NumberCursor", String.valueOf(cursor.getCount()));
            int indexYear = cursor.getColumnIndex(CONTACTS_COLUMN_YEAR);
            int indexMonth = cursor.getColumnIndex(CONTACTS_COLUMN_MONTH);
            int indexDay = cursor.getColumnIndex(CONTACTS_COLUMN_DAY);
            int indexHour = cursor.getColumnIndex(CONTACTS_COLUMN_HOUR);
            int indexMinute = cursor.getColumnIndex(CONTACTS_COLUMN_MINUTE);
            int indexType = cursor.getColumnIndex(CONTACTS_COLUMN_TYPE);
            int indexDateOfEmployment = cursor.getColumnIndex(CONTACTS_COLUMN_DATE_OF_EMPLOYMENT);

            while (cursor.moveToNext()){
                ModelCalendar calendar = new ModelCalendar();
                calendar.setUser_Id(user_Id);
                calendar.setWorkspace_Id(workspace_Id);
                calendar.setYear(cursor.getInt(indexYear));
                calendar.setMonth(cursor.getInt(indexMonth));
                calendar.setDay(cursor.getInt(indexDay));
                calendar.setHour(cursor.getInt(indexHour));
                calendar.setMinute(cursor.getInt(indexMinute));
                calendar.setType(cursor.getString(indexType));
                calendar.setDateOfEmployment(cursor.getString(indexDateOfEmployment));
                calendars.add(calendar);

            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return calendars;
    }

    public List<Integer> getDistinctWorkspace_byUser(int user_Id){
        SQLiteDatabase db = myDatabase.getReadableDatabase();

        List<Integer> workspaces = new ArrayList<>();
        Cursor cursor = db.rawQuery(String.format("SELECT DISTINCT %s FROM %s WHERE %s = ?",
                CONTACTS_COLUMN_WORKSPACE_ID, CONTACTS_TABLE_NAME, CONTACTS_COLUMN_USER_ID),
                new String[]{String.valueOf(user_Id)});
        int indexWorkspace_Id = cursor.getColumnIndex(CONTACTS_COLUMN_WORKSPACE_ID);
        while (cursor.moveToNext()){
            workspaces.add(cursor.getInt(indexWorkspace_Id));
        }
        return workspaces;
    }

}
