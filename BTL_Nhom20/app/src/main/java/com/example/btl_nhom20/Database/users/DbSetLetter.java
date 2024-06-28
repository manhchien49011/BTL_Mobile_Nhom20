package com.example.btl_nhom20.Database.users;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

//import com.example.btl_nhom20.Database.MyDatabase;
import com.example.btl_nhom20.Database.users.weathers.MyDatabase;
import com.example.btl_nhom20.MyApplication;
import com.example.btl_nhom20.model.letter.Letter;
import com.example.btl_nhom20.types.TypeOfLetter;

import java.util.ArrayList;
import java.util.List;

public class DbSetLetter {
    public final String CONTACTS_TABLE_NAME = "Letters";
    public  final String CONTACTS_COLUMN_USER_ID = "User_Id";
    public  final String CONTACTS_COLUMN_WORKSPACE_ID = "Workspace_Id";
    public  final String CONTACTS_COLUMN_USER_NAME = "User_Name";
    public  final String CONTACTS_COLUMN_TYPE_OF_LETTER = "Type_of_Letter";
    public  final String CONTACTS_COLUMN_TIME_OF_LETTER = "Time_of_Letter";
    public  final String CONTACTS_COLUMN_REASON_RESIGNATION = "Reason_Resignation";
    Context context;
    MyDatabase myDatabase;
    public DbSetLetter(Context context) {
        this.context = context;
        myDatabase = MyApplication.getInstace().getDb();
    }
    
    public boolean insert(Letter letter){
        SQLiteDatabase db = myDatabase.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CONTACTS_COLUMN_USER_ID,letter.getUserId());
        values.put(CONTACTS_COLUMN_WORKSPACE_ID,letter.getWorkspaceId());
        values.put(CONTACTS_COLUMN_USER_NAME,letter.getUsername());
        values.put(CONTACTS_COLUMN_TYPE_OF_LETTER,letter.getTypeOfLetter().toString());
        values.put(CONTACTS_COLUMN_TIME_OF_LETTER,letter.getTimeOfLetter());
        values.put(CONTACTS_COLUMN_REASON_RESIGNATION,letter.getReasonResignation());
        long result = db.insert(CONTACTS_TABLE_NAME,null,values);

        return result > 0;
        
    }

    public boolean update(Letter letter){
        SQLiteDatabase db = myDatabase.getWritableDatabase();
        ContentValues values = new ContentValues();

        if(letter.getUserId() != null){
            values.put(CONTACTS_COLUMN_USER_ID,letter.getUserId());
        }
        if(letter.getWorkspaceId() > 0){
            values.put(CONTACTS_COLUMN_WORKSPACE_ID,letter.getWorkspaceId());
        }
        if(letter.getUsername() != null) {
            values.put(CONTACTS_COLUMN_USER_NAME, letter.getUsername());
        }
        if (letter.getTypeOfLetter() != null) {
            values.put(CONTACTS_COLUMN_TYPE_OF_LETTER, letter.getTypeOfLetter().toString());
        }
        if (letter.getTimeOfLetter() != null) {
            values.put(CONTACTS_COLUMN_TIME_OF_LETTER, letter.getTimeOfLetter());
        }
        if (letter.getReasonResignation() != null) {
            values.put(CONTACTS_COLUMN_REASON_RESIGNATION, letter.getReasonResignation());
        }

        String whereClause = String.format("%s = ? AND %s = ? AND %s = ? ", CONTACTS_COLUMN_USER_ID,CONTACTS_COLUMN_WORKSPACE_ID,CONTACTS_COLUMN_TIME_OF_LETTER);
        return db.update(CONTACTS_TABLE_NAME,values,whereClause,
                                new String[]{String.valueOf(letter.getUserId()),
                                            String.valueOf(letter.getWorkspaceId()),
                                            letter.getTimeOfLetter()}) > 0;
    }

    public boolean delete(int userId, int workspaceId, String timeOfLetter){
        SQLiteDatabase db = myDatabase.getWritableDatabase();
        String whereClause = String.format("%s = ? AND %s = ? AND %s = ? ", CONTACTS_COLUMN_USER_ID,CONTACTS_COLUMN_WORKSPACE_ID,CONTACTS_COLUMN_TIME_OF_LETTER);
        return db.delete(CONTACTS_TABLE_NAME,whereClause,
                new String[]{String.valueOf(userId),
                            String.valueOf(workspaceId),
                            timeOfLetter}) > 0;
    }

    public List<Letter> getAll(){
        SQLiteDatabase db = myDatabase.getReadableDatabase();
        List<Letter> letters = new ArrayList<>();
        Cursor cursor = db.query(CONTACTS_TABLE_NAME,null,null,null,null,null,null);

        int userIdIndex = cursor.getColumnIndex(CONTACTS_COLUMN_USER_ID);
        int workspaceIdIndex = cursor.getColumnIndex(CONTACTS_COLUMN_WORKSPACE_ID);
        int userNameIndex = cursor.getColumnIndex(CONTACTS_COLUMN_USER_NAME);
        int typeOfLetterIndex = cursor.getColumnIndex(CONTACTS_COLUMN_TYPE_OF_LETTER);
        int timeOfLetterIndex = cursor.getColumnIndex(CONTACTS_COLUMN_TIME_OF_LETTER);
        int reasonResignationIndex = cursor.getColumnIndex(CONTACTS_COLUMN_REASON_RESIGNATION);

        while (cursor.moveToNext()){
            Letter letter = new Letter();
            letter.setUserId(cursor.getString(userIdIndex));
            letter.setWorkspaceId(cursor.getInt(workspaceIdIndex));
            letter.setUsername(cursor.getString(userNameIndex));
            letter.setTypeOfLetter(TypeOfLetter.valueOf(cursor.getString(typeOfLetterIndex)));
            letter.setTimeOfLetter(cursor.getString(timeOfLetterIndex));
            letter.setReasonResignation(cursor.getString(reasonResignationIndex));
            letters.add(letter);
        }
        return letters;
    }

    public List<Letter> getById(int userId, int workspaceId, String timeOfLetter){
        SQLiteDatabase db = myDatabase.getReadableDatabase();
        List<Letter> letters = new ArrayList<>();
        String whereClause = String.format("%s = ? AND %s = ? AND %s = ? ", CONTACTS_COLUMN_USER_ID,CONTACTS_COLUMN_WORKSPACE_ID,CONTACTS_COLUMN_TIME_OF_LETTER);
        Cursor cursor = db.query(CONTACTS_TABLE_NAME,null,whereClause,
                new String[]{String.valueOf(userId),
                            String.valueOf(workspaceId),
                            timeOfLetter},null,null,null);
        int userIdIndex = cursor.getColumnIndex(CONTACTS_COLUMN_USER_ID);
        int workspaceIdIndex = cursor.getColumnIndex(CONTACTS_COLUMN_WORKSPACE_ID);
        int userNameIndex = cursor.getColumnIndex(CONTACTS_COLUMN_USER_NAME);
        int typeOfLetterIndex = cursor.getColumnIndex(CONTACTS_COLUMN_TYPE_OF_LETTER);
        int timeOfLetterIndex = cursor.getColumnIndex(CONTACTS_COLUMN_TIME_OF_LETTER);
        int reasonResignationIndex = cursor.getColumnIndex(CONTACTS_COLUMN_REASON_RESIGNATION);
        while (cursor.moveToNext()){
            Letter letter = new Letter();
            letter.setUserId(cursor.getString(userIdIndex));
            letter.setWorkspaceId(cursor.getInt(workspaceIdIndex));
            letter.setUsername(cursor.getString(userNameIndex));
            letter.setTypeOfLetter(TypeOfLetter.valueOf(cursor.getString(typeOfLetterIndex)));
            letter.setTimeOfLetter(cursor.getString(timeOfLetterIndex));
            letter.setReasonResignation(cursor.getString(reasonResignationIndex));
            letters.add(letter);
        }
        return letters;

    }

    public List<Letter> getByWorkspaceId(int workspaceId){
        SQLiteDatabase db = myDatabase.getReadableDatabase();
        List<Letter> letters = new ArrayList<>();

        String whereClause = String.format("%s = ? ", CONTACTS_COLUMN_WORKSPACE_ID);

        Cursor cursor = db.query(CONTACTS_TABLE_NAME,null,whereClause,
                new String[]{String.valueOf(workspaceId)},null,null,null);

        int userIdIndex = cursor.getColumnIndex(CONTACTS_COLUMN_USER_ID);
        int workspaceIdIndex = cursor.getColumnIndex(CONTACTS_COLUMN_WORKSPACE_ID);
        int userNameIndex = cursor.getColumnIndex(CONTACTS_COLUMN_USER_NAME);
        int typeOfLetterIndex = cursor.getColumnIndex(CONTACTS_COLUMN_TYPE_OF_LETTER);
        int timeOfLetterIndex = cursor.getColumnIndex(CONTACTS_COLUMN_TIME_OF_LETTER);
        int reasonResignationIndex = cursor.getColumnIndex(CONTACTS_COLUMN_REASON_RESIGNATION);
        while (cursor.moveToNext()){
            Letter letter = new Letter();
            letter.setUserId(cursor.getString(userIdIndex));
            letter.setWorkspaceId(cursor.getInt(workspaceIdIndex));
            letter.setUsername(cursor.getString(userNameIndex));
            letter.setTypeOfLetter(TypeOfLetter.valueOf(cursor.getString(typeOfLetterIndex)));
            letter.setTimeOfLetter(cursor.getString(timeOfLetterIndex));
            letter.setReasonResignation(cursor.getString(reasonResignationIndex));
            letters.add(letter);
        }
        return letters;
    }


}
