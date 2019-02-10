package com.example.myapplication;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.objects.StartButton;
import com.example.myapplication.views.MainView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    MainView mainView;
    Button button1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
       // mainView = findViewById(R.id.mainView);
        button1 = findViewById(R.id.startButton);
        button1.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
       switch (v.getId()){
           case R.id.startButton:
               Intent intent = new Intent(this, SecondActivity.class);
               startActivity(intent);

       }


    }
}
