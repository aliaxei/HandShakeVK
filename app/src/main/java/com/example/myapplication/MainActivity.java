package com.example.myapplication;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.example.myapplication.objects.StartButton;
import com.example.myapplication.views.MainView;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener{
    MainView mainView;
    Button button1;
    Animation animAlpha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
       // mainView = findViewById(R.id.mainView);
        button1 = findViewById(R.id.startButton);
        button1.setOnTouchListener(this);
       animAlpha = AnimationUtils.loadAnimation(this, R.anim.animationscale);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                v.startAnimation(animAlpha);
                break;
            case MotionEvent.ACTION_UP:
                Intent intent = new Intent(this, SecondActivity.class);
                startActivity(intent);
                break;
        }
        return true;
    }

    //    @Override
//    public void onClick(View v) {
//
//       switch (v.getId()){
//           case R.id.startButton:
//               v.startAnimation(animAlpha);
//               Intent intent = new Intent(this, SecondActivity.class);
//               startActivity(intent);
//       }
//
//
//           }
}
