package com.example.btl_nhom20.Database.users;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

//import com.example.btl_nhom20.Database.MyDatabase;
import com.example.btl_nhom20.Database.users.weathers.MyDatabase;
import com.example.btl_nhom20.MyApplication;
import com.example.btl_nhom20.model.user.Notification;

import java.util.ArrayList;
import java.util.List;

public class DbSetNotification {

    public static final String CONTACTS_TABLE_NAME = "Notifications";
    public static final String CONTACTS_COLUMN_ID = "Notification_Id";
    public static final String CONTACTS_COLUMN_SEND_ID = "Sender_Id";
    public static final String CONTACTS_COLUMN_RECEIVE_ID = "Receiver_Id";
    public static final String CONTACTS_COLUMN_MESSAGE = "Message";
    public static final String CONTACTS_COLUMN_WORKSPACE_ID = "Workspace_Id";
    public static final String CONTACTS_COLUMN_EMAIL_SENDER = "Email_Sender";
    public static final String CONTACTS_COLUMN_IS_ACCEPT = "Is_Accept";
    public static final String CONTACTS_COLUMN_IS_REFUSE = "Is_Refuse";
    Context context;
    MyDatabase myDatabase;
    public DbSetNotification(Context context) {
        this.context = context;
        myDatabase = MyApplication.getInstace().getDb();
    }

    public boolean insert(Notification noti){
        SQLiteDatabase db = myDatabase.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(CONTACTS_COLUMN_SEND_ID, noti.getSenderId());
        values.put(CONTACTS_COLUMN_RECEIVE_ID, noti.getReceiverId());
        values.put(CONTACTS_COLUMN_MESSAGE, noti.getMessage());
        values.put(CONTACTS_COLUMN_WORKSPACE_ID, noti.getWorkspaceId());
        values.put(CONTACTS_COLUMN_EMAIL_SENDER, noti.getEmailSender());
        values.put(CONTACTS_COLUMN_IS_ACCEPT, noti.getIsAccept());
        values.put(CONTACTS_COLUMN_IS_REFUSE, noti.getIsRefuse());

        long result = db.insert(CONTACTS_TABLE_NAME, null, values);
        return result > 0;
    }

    public boolean update(Notification noti){
        SQLiteDatabase db = myDatabase.getWritableDatabase();
        ContentValues values = new ContentValues();

        if(noti.getReceiverId() > -1)
            values.put(CONTACTS_COLUMN_RECEIVE_ID, noti.getReceiverId());
        if(!noti.getMessage().isEmpty())
            values.put(CONTACTS_COLUMN_MESSAGE, noti.getMessage());
        if(noti.getWorkspaceId() > -1)
            values.put(CONTACTS_COLUMN_WORKSPACE_ID, noti.getWorkspaceId());
        if(!noti.getEmailSender().isEmpty())
            values.put(CONTACTS_COLUMN_EMAIL_SENDER, noti.getEmailSender());
        if(noti.getIsAccept() > -1)
            values.put(CONTACTS_COLUMN_IS_ACCEPT, noti.getIsAccept());
        if(noti.getIsRefuse() > -1)
            values.put(CONTACTS_COLUMN_IS_REFUSE, noti.getIsRefuse());

        long result = db.update(CONTACTS_TABLE_NAME, values, CONTACTS_COLUMN_ID + "=?", new String[]{String.valueOf(noti.getId())});
        return result > 0;
    }

    public boolean delete(int id){
        SQLiteDatabase db = myDatabase.getWritableDatabase();
        long result = db.delete(CONTACTS_TABLE_NAME, CONTACTS_COLUMN_ID + "=?", new String[]{String.valueOf(id)});
        return result > 0;
    }

    public List<Notification> getAll(){
        List<Notification> list = new ArrayList<>();
        SQLiteDatabase db = myDatabase.getReadableDatabase();
        Cursor cursor = db.query(CONTACTS_TABLE_NAME, null, null, null, null, null, null);

        int idIndex = cursor.getColumnIndex(CONTACTS_COLUMN_ID);
        int sendIdIndex = cursor.getColumnIndex(CONTACTS_COLUMN_SEND_ID);
        int receiveIdIndex = cursor.getColumnIndex(CONTACTS_COLUMN_RECEIVE_ID);
        int messageIndex = cursor.getColumnIndex(CONTACTS_COLUMN_MESSAGE);
        int workspaceIdIndex = cursor.getColumnIndex(CONTACTS_COLUMN_WORKSPACE_ID);
        int emailSenderIndex = cursor.getColumnIndex(CONTACTS_COLUMN_EMAIL_SENDER);
        int isAcceptIndex = cursor.getColumnIndex(CONTACTS_COLUMN_IS_ACCEPT);
        int isRefuseIndex = cursor.getColumnIndex(CONTACTS_COLUMN_IS_REFUSE);

        while (cursor.moveToNext()){
            Notification noti = new Notification();
            noti.setId(cursor.getInt(idIndex));
            noti.setSenderId(cursor.getInt(sendIdIndex));
            noti.setReceiverId(cursor.getInt(receiveIdIndex));
            noti.setMessage(cursor.getString(messageIndex));
            noti.setWorkspaceId(cursor.getInt(workspaceIdIndex));
            noti.setEmailSender(cursor.getString(emailSenderIndex));
            noti.setIsAccept(cursor.getInt(isAcceptIndex));
            noti.setIsRefuse(cursor.getInt(isRefuseIndex));
            list.add(noti);
        }

        return list;
    }

    public List<Notification> getByUserId_and_WorkspaceId(int senderId, int workspaceId){
        List<Notification> list = new ArrayList<>();

        SQLiteDatabase db = myDatabase.getReadableDatabase();
        Cursor cursor = db.query(CONTACTS_TABLE_NAME, null,
                CONTACTS_COLUMN_SEND_ID + "=?" + " AND " + CONTACTS_COLUMN_WORKSPACE_ID + "=?",
                new String[]{String.valueOf(senderId), String.valueOf(workspaceId)},
                null, null, null);
        int idIndex = cursor.getColumnIndex(CONTACTS_COLUMN_ID);
        int sendIdIndex = cursor.getColumnIndex(CONTACTS_COLUMN_SEND_ID);
        int receiveIdIndex = cursor.getColumnIndex(CONTACTS_COLUMN_RECEIVE_ID);
        int messageIndex = cursor.getColumnIndex(CONTACTS_COLUMN_MESSAGE);
        int workspaceIdIndex = cursor.getColumnIndex(CONTACTS_COLUMN_WORKSPACE_ID);
        int emailSenderIndex = cursor.getColumnIndex(CONTACTS_COLUMN_EMAIL_SENDER);
        int isAcceptIndex = cursor.getColumnIndex(CONTACTS_COLUMN_IS_ACCEPT);
        int isRefuseIndex = cursor.getColumnIndex(CONTACTS_COLUMN_IS_REFUSE);

        while (cursor.moveToNext()){
            Notification noti = new Notification();
            noti.setId(cursor.getInt(idIndex));
            noti.setSenderId(cursor.getInt(sendIdIndex));
            noti.setReceiverId(cursor.getInt(receiveIdIndex));
            noti.setMessage(cursor.getString(messageIndex));
            noti.setWorkspaceId(cursor.getInt(workspaceIdIndex));
            noti.setEmailSender(cursor.getString(emailSenderIndex));
            noti.setIsAccept(cursor.getInt(isAcceptIndex));
            noti.setIsRefuse(cursor.getInt(isRefuseIndex));
            list.add(noti);
        }

        return list;
    }

    public List<Notification> getByReceiverId(int receiverId){
        List<Notification> list = new ArrayList<>();
        SQLiteDatabase db = myDatabase.getReadableDatabase();
        Cursor cursor = db.query(CONTACTS_TABLE_NAME, null,
                CONTACTS_COLUMN_RECEIVE_ID + "=?",
                new String[]{String.valueOf(receiverId)},
                null, null, null);
        int idIndex = cursor.getColumnIndex(CONTACTS_COLUMN_ID);
        int sendIdIndex = cursor.getColumnIndex(CONTACTS_COLUMN_SEND_ID);
        int receiveIdIndex = cursor.getColumnIndex(CONTACTS_COLUMN_RECEIVE_ID);
        int messageIndex = cursor.getColumnIndex(CONTACTS_COLUMN_MESSAGE);
        int workspaceIdIndex = cursor.getColumnIndex(CONTACTS_COLUMN_WORKSPACE_ID);
        int emailSenderIndex = cursor.getColumnIndex(CONTACTS_COLUMN_EMAIL_SENDER);
        int isAcceptIndex = cursor.getColumnIndex(CONTACTS_COLUMN_IS_ACCEPT);
        int isRefuseIndex = cursor.getColumnIndex(CONTACTS_COLUMN_IS_REFUSE);

        while (cursor.moveToNext()){
            Notification noti = new Notification();
            noti.setId(cursor.getInt(idIndex));
            noti.setSenderId(cursor.getInt(sendIdIndex));
            noti.setReceiverId(cursor.getInt(receiveIdIndex));
            noti.setMessage(cursor.getString(messageIndex));
            noti.setWorkspaceId(cursor.getInt(workspaceIdIndex));
            noti.setEmailSender(cursor.getString(emailSenderIndex));
            noti.setIsAccept(cursor.getInt(isAcceptIndex));
            noti.setIsRefuse(cursor.getInt(isRefuseIndex));
            list.add(noti);
        }

        return list;
    }


}
