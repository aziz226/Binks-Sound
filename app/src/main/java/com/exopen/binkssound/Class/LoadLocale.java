package com.exopen.binkssound.Class;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class LoadLocale {
    Context context;

    public LoadLocale(Context context) {
        this.context = context;

        SharedPreferences prefs= context.getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String language= prefs.getString("My_Lang", "");
        new setLocale(context, language);
    }
}
