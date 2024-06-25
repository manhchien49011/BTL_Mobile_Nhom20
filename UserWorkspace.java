package com.example.btl_nhom20.model.user;

public class UserWorkspace {
    int userId;
    int workspaceId;
    boolean isAdmin;

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public UserWorkspace(int userId, int workspaceId, boolean isAdmin) {
        this.userId = userId;
        this.workspaceId = workspaceId;
        this.isAdmin = isAdmin;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getWorkspaceId() {
        return workspaceId;
    }

    public void setWorkspaceId(int workspaceId) {
        this.workspaceId = workspaceId;
    }
}
