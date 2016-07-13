package com.dalbo.jajanon.Core;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by alkaaf on 7/13/2016.
 */
public class Pref {
    static SharedPreferences sp;
    public static void init(Context c) {
        sp = c.getSharedPreferences("Auth",Context.MODE_PRIVATE);
    }
    public static void put(String key, String val){
        SharedPreferences.Editor edit = sp.edit();
        edit.putString(key, val);
        edit.commit();
    }
    public static void putUid(int id){
        sp.edit().putInt("uid",id).commit();
    }
    public static int getUid(){
        return sp.getInt("uid",0);
    }
    public static String get(String key){
        return  sp.getString(key, null);
    }
    public static void testing(String val){
        SharedPreferences.Editor edit = sp.edit();
        edit.putString("auth",val);
        edit.commit();
    }
}