package com.example.btl_nhom20.model.user;

import java.sql.Timestamp;

public class Notification {
    int id;
    int senderId;
    int receiverId;
    String message;
    int workspaceId;
    String emailSender;
    int isAccept;
    int isRefuse;
    Timestamp created_at;

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Notification(){
        id = -1;
        senderId = -1;
        receiverId = -1;
        message = "";
        workspaceId = -1;
        emailSender = "";
        isAccept = -1;
        isRefuse = -1;
    }

    public Notification(int senderId, int receiverId, String message, int workspaceId, String emailSender, int isAccept, int isRefuse) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.message = message;
        this.workspaceId = workspaceId;
        this.emailSender = emailSender;
        this.isAccept = isAccept;
        this.isRefuse = isRefuse;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getWorkspaceId() {
        return workspaceId;
    }

    public void setWorkspaceId(int workspaceId) {
        this.workspaceId = workspaceId;
    }

    public String getEmailSender() {
        return emailSender;
    }

    public void setEmailSender(String emailSender) {
        this.emailSender = emailSender;
    }

    public int getIsAccept() {
        return isAccept;
    }

    public void setIsAccept(int isAccept) {
        this.isAccept = isAccept;
    }

    public int getIsRefuse() {
        return isRefuse;
    }

    public void setIsRefuse(int isRefuse) {
        this.isRefuse = isRefuse;
    }
}
