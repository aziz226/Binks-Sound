package com.exopen.binkssound.Class;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;

import java.util.Locale;

public class setLocale {
    Context context;
    String langage;

    public setLocale(Context context, String langage) {
        this.context = context;
        this.langage = langage;


        Locale locale = new Locale(langage);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        context.getResources().updateConfiguration(config,
                context.getResources().getDisplayMetrics());
        SharedPreferences.Editor editor = context.getSharedPreferences("Settings", MODE_PRIVATE).edit();
        editor.putString("My_Lang", langage);
        editor.apply();
    }
}
