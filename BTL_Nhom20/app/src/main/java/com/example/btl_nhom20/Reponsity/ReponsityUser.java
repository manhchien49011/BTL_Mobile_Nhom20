package com.example.btl_nhom20.Reponsity;

import android.content.Context;

import com.example.btl_nhom20.Database.users.DbSetUser;
import com.example.btl_nhom20.model.user.User;

import java.util.List;

public class ReponsityUser {
    DbSetUser dbSetUser;
    User userCurrent;
    public ReponsityUser(Context context){
        dbSetUser = new DbSetUser(context);
        userCurrent = new User();
    }

    public boolean insert(User user){
        if(!dbSetUser.checkExistEmail(user.getEmail())){
            dbSetUser.insert(user);
            return true;
        }
        return false;
    }

    public boolean update(User user){
        return dbSetUser.update(user);
    }

    public void delete(User user){
        dbSetUser.delete(user);
    }

    public List<User> getAll(){
        return dbSetUser.getAll();
    }

    public User getById(int id){
        return dbSetUser.getByID(id);
    }

    public User auth(String email, String password){
        userCurrent = dbSetUser.auth(email, password);
        return userCurrent;
    }


    public User getUserCurrent() {
        return userCurrent;
    }



}
