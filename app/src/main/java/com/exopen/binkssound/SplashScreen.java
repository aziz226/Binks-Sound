package com.exopen.binkssound;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.exopen.binkssound.Class.LoadLocale;
import com.exopen.binkssound.Identifiant.LogIn;
import com.exopen.binkssound.OnBoarding.OnBoarding;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new LoadLocale(this);

        setContentView(R.layout.splash_screen);

        SharedPreferences settings=getSharedPreferences("prefs",0);
        boolean firstRun=settings.getBoolean("firstRun",false);
        if(!firstRun)//if running for first time
        //Splash will load for first time
        {
            SharedPreferences.Editor editor=settings.edit();
            editor.putBoolean("firstRun",true);
            editor.apply();
            new Handler().postDelayed(() -> {
                Intent intent = new Intent(getApplicationContext(), OnBoarding.class );
                startActivity(intent);
                finish();


            },3000);
        }
        else
        {
            new Handler().postDelayed(() -> {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class );
                startActivity(intent);
                finish();


            },3000);
        }

    }
}