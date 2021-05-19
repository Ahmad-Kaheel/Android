package com.example.hp.myapplication_9;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import gr.net.maroulis.library.EasySplashScreen;

public class First extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EasySplashScreen config = new EasySplashScreen(First.this)
                .withFullScreen()
                .withTargetActivity(login.class)
                .withSplashTimeOut(3000)
                .withLogo(R.drawable.splash);
        View easySplashScreen = config.create();
        setContentView(easySplashScreen);
    }


}
