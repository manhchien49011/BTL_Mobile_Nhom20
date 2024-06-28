package com.example.btl_nhom20.Reponsity;

import android.content.Context;

import com.example.btl_nhom20.Database.users.DbSetNotification;
import com.example.btl_nhom20.model.user.Notification;

import java.util.List;

public class ReponsitoryNotification {
    private DbSetNotification dbSetNotification;
    public ReponsitoryNotification(Context context) {
        dbSetNotification = new DbSetNotification(context);
    }

    public boolean insert(Notification notification) {
        return dbSetNotification.insert(notification);
    }

    public boolean update(Notification notification) {
        return dbSetNotification.update(notification);
    }

    public boolean delete(int id) {
        return dbSetNotification.delete(id);
    }

    public List<Notification> getAll() {
        return dbSetNotification.getAll();
    }

    public List<Notification> getByUserId_and_WorkspaceId(int senderId, int workspaceId) {
        return dbSetNotification.getByUserId_and_WorkspaceId(senderId, workspaceId);
    }

    public List<Notification> getByReceiverId(int receiverId) {
        return dbSetNotification.getByReceiverId(receiverId);
    }
}
