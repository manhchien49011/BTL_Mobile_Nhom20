package com.example.btl_nhom20.Reponsity;

import android.content.Context;

import com.example.btl_nhom20.Database.users.DbSetUserWorkspace;
import com.example.btl_nhom20.model.user.UserWorkspace;

import java.util.ArrayList;
import java.util.List;

public class ReponsityUserWorkspace {
    Context context;
    DbSetUserWorkspace dbSetUserWorkspace;
    List<UserWorkspace> listUserWorkspace;
    public ReponsityUserWorkspace(Context context) {
        this.context = context;
        dbSetUserWorkspace = new DbSetUserWorkspace(context);
        listUserWorkspace = new ArrayList<>();
    }
    public boolean insert(UserWorkspace userWorkspace) {
        return dbSetUserWorkspace.insert(userWorkspace);
    }

    public List<UserWorkspace> getByUserId(int userId) {
        return dbSetUserWorkspace.getByUserId(userId);
    }
    public List<UserWorkspace> getByWorkspaceId(int workspaceId) {
        return dbSetUserWorkspace.getByWorkspaceId(workspaceId);
    }

    public int getUserIdAdminByWorkspaceId(int workspaceId) {
        List<UserWorkspace> listUserWorkspace = getByWorkspaceId(workspaceId);
        for (UserWorkspace userWorkspace : listUserWorkspace) {
            if (userWorkspace.isAdmin()) {
                return userWorkspace.getUserId();
            }
        }
        return -1;
    }
}
