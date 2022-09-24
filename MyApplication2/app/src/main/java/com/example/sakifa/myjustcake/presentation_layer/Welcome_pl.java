package com.example.sakifa.myjustcake.presentation_layer;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.sakifa.myjustcake.Application_layer.Login_bl;
import com.example.sakifa.myjustcake.R;

public class Welcome_pl extends AppCompatActivity {
public static final int SPLASH_TIME_OUT=4000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(Welcome_pl.this,Login_bl.class);
                startActivity(intent);
                finish();

            }
        },SPLASH_TIME_OUT);
    }
}
