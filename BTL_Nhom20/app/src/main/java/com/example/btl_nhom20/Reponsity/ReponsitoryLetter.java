package com.example.btl_nhom20.Reponsity;

import android.content.Context;

import com.example.btl_nhom20.Database.users.DbSetLetter;
import com.example.btl_nhom20.model.letter.Letter;

import java.util.List;

public class ReponsitoryLetter {
    DbSetLetter dbSetLetter;
    Context context;
    public ReponsitoryLetter(Context context) {
        this.context = context;
        dbSetLetter = new DbSetLetter(context);
    }

    public boolean insert(Letter letter) {
        return dbSetLetter.insert(letter);
    }

    public boolean update(Letter letter) {
        return dbSetLetter.update(letter);
    }

    public boolean delete(int userId, int workspacesId, String time) {
        return dbSetLetter.delete(userId, workspacesId, time);
    }

    public List<Letter> getById(int userId, int workspacesId, String time) {
        return dbSetLetter.getById(userId, workspacesId, time);
    }

    public List<Letter> getAll() {
        return dbSetLetter.getAll();
    }

    public List<Letter> getByWorkspacesId(int workspacesId) {
        return dbSetLetter.getByWorkspaceId(workspacesId);
    }
}
