package com.example.btl_nhom20.SharedPreferences;

import android.content.Context;
import android.content.SharedPreferences;

public class MySharedPreferences {
    public static MySharedPreferences mySharedPreferences;
    private static SharedPreferences share;
    private Context _context;
    public static String name = "MySharedPreferences";
    public static MySharedPreferences getInstance(Context context) {
        if (mySharedPreferences == null) {
            share = context.getSharedPreferences(name, Context.MODE_PRIVATE);
            mySharedPreferences = new MySharedPreferences();
        }
        return mySharedPreferences;
    }
    public void putString(String key, String value) {
        SharedPreferences.Editor editor = share.edit();
        editor.putString(key, value);
        editor.apply();
    }
    public String getString(String key) {
        return share.getString(key, "");
    }

    public void clear() {
        SharedPreferences.Editor editor = share.edit();
        editor.clear();
        editor.apply();
    }

    public boolean contains(String key) {
        return share.contains(key);
    }
}
