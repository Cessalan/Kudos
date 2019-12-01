package com.example.kudos.model;

import android.app.Activity;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class Status {
    private Activity activity;

    public Status(Activity activity){
        this.activity=activity;
    }
    public Status(){}


 public  boolean userLoggedIn(Activity activity){

        SharedPreferences preferences=activity.getSharedPreferences("status", MODE_PRIVATE);
        int conn=2;
               conn =preferences.getInt("status",0);
        if(conn==1){
            return true;
        }else{
            return false;
        }
    }
}
