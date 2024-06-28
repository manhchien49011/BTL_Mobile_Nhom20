package com.example.btl_nhom20.Database.users.weathers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDatabase extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "CheckIn";

    public MyDatabase(Context context){
        super(context, DATABASE_NAME, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Users(\n" +
                "    User_Id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    User_Name NVARCHAR(255),\n" +
                "    User_Email NVARCHAR(255),\n" +
                "    User_PASSWORD NVARCHAR(255)\n" +
                ")");

        db.execSQL("CREATE TABLE Workspaces(\n" +
                "    Workspace_Id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    Workspace_Name NVARCHAR(255),\n" +
                "    company_name NVARCHAR(255),\n" +
                "    Email NVARCHAR(255),\n" +
                "    Date_Created_Wsp NVARCHAR(255),\n" +
                "    Work_Time NVARCHAR(255),\n" +
                "    End_Work_Time NVARCHAR(255),\n" +
                "    Late_Time_checkIn NVARCHAR(255),\n" +
                "    Province NVARCHAR(255)\n" +
                ")\n");

        db.execSQL("CREATE TABLE UserWorkspace(\n" +
                "    User_Id INTEGER not null,\n" +
                "    Workspace_Id INTEGER not null,\n" +
                "    Is_Admin INTEGER DEFAULT(0),\n" +
                "    PRIMARY KEY (User_Id, Workspace_Id),\n" +
                "    FOREIGN KEY(User_Id) REFERENCES Users(User_Id),\n" +
                "    FOREIGN KEY(Workspace_Id) REFERENCES Workspaces(Workspace_Id)\n" +
                ")");

        db.execSQL("CREATE TABLE Calendar(\n" +
                "    User_Id INTEGER not null,\n" +
                "    Workspace_Id INTEGER not null,\n" +
                "    Year INTEGER ,\n" +
                "    Month INTEGER,\n" +
                "    Day INTEGER,\n" +
                "    Hour INTEGER,\n" +
                "    Minute INTEGER,\n" +
                "    Type NVARCHAR(255),\n" +
                "    DateOf_Employment NVARCHAR(255),\n" +
                "    PRIMARY KEY(User_Id, Workspace_Id, Year, Month, Day, Type),\n" +
                "    FOREIGN KEY (User_Id) REFERENCES Users(User_Id),\n" +
                "    FOREIGN KEY (Workspace_Id) REFERENCES Workspaces(Workspace_Id)\n" +
                ")\n");

        db.execSQL("CREATE TABLE Notifications(\n" +
                "    Notification_Id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    Sender_Id INTEGER not null,\n" +
                "    Receiver_Id INTEGER not null,\n" +
                "    Message NVARCHAR(255) not null,\n" +
                "    Workspace_Id INTEGER not null,\n" +
                "    Email_Sender NVARCHAR(255),\n" +
                "    Is_Accept INTEGER,\n" +
                "    Is_Refuse INTEGER,\n" +
                "    FOREIGN KEY(Sender_Id) REFERENCES Users(User_Id),\n" +
                "    FOREIGN KEY(Receiver_Id) REFERENCES Users(User_Id),\n" +
                "    FOREIGN KEY(Workspace_Id) REFERENCES Workspaces(Workspace_Id)\n" +
                ")\n");

        db.execSQL("CREATE TABLE Letters(\n" +
                "    User_Id INTEGER not null,\n" +
                "    Workspace_Id INTEGER not null,\n" +
                "    User_Name NVARCHAR(255),\n" +
                "    Type_of_Letter NVARCHAR(255) not null,\n" +
                "    Time_of_Letter NVARCHAR(255) not null,\n" +
                "    Reason_Resignation NVARCHAR(255),\n" +
                "    FOREIGN KEY(User_Id) REFERENCES Users(User_Id),\n" +
                "    FOREIGN KEY(Workspace_Id) REFERENCES Workspaces(Workspace_Id)\n" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
