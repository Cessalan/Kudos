package com.example.kudos.util;

import android.app.Activity;
import android.content.SharedPreferences;

import androidx.annotation.Nullable;

import com.example.kudos.model.Status;

import java.util.Map;
import java.util.Set;

public class UserStatus_SharedPreferences {


    private SharedPreferences preferences;
    public UserStatus_SharedPreferences(Activity activity){
        this.preferences=activity.getPreferences(activity.MODE_PRIVATE);
    }
    public void accountStatus(int stat){
        preferences.edit().putInt("status",stat).apply();

    }


}
