package com.example.btl_nhom20;

import android.app.Application;

import com.example.btl_nhom20.Database.users.weathers.MyDatabase;

//import com.example.btl_nhom20.Database.MyDatabase;

public class MyApplication extends Application {
    private static MyApplication instance;
    private MyDatabase db;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        db = new MyDatabase(this);
    }

    public static MyApplication getInstace() {
        return instance;
    }

    public MyDatabase getDb() {
        return db;
    }
}