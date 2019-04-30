package com.example.myapplication;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.graphics.drawable.AnimationDrawable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.myapplication.views.SecondActivityView;

public class SecondActivity extends AppCompatActivity {
    SecondActivityView secondActivityView;
    String bgColor;
    WorkWithFile workWithFile;
    ConstraintLayout constraintLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        constraintLayout = findViewById(R.id.background);
        workWithFile = new WorkWithFile();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        //Intent intentToSecondActivity = new Intent(this, SecondActivity.class);
        //startActivity(intentToSecondActivity);
       // overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

        ImageView img = (ImageView)findViewById(R.id.animationView);
        img.setBackgroundResource(R.drawable.animation_planet);
// получаем объект анимации
        AnimationDrawable frameAnimation = (AnimationDrawable) img.getBackground();
       // frameAnimation.setOneShot(false);
// запуск анимации
        frameAnimation.start();

        //ImageView imageView;
        //imageView = (ImageView) findViewById(R.id.imageView);
        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        int screen_height = metrics.widthPixels;
        img.getLayoutParams().height = screen_height;
        bgColor = workWithFile.ReadFromFile(workWithFile.getBgFileName(),getApplicationContext());
        if (bgColor != null){
            BGChanger.ChangeTheBG(bgColor,constraintLayout);
        }

    }


    @Override
    protected void onRestart() {
        super.onRestart();
        bgColor = workWithFile.ReadFromFile(workWithFile.getBgFileName(),getApplicationContext());
        if (bgColor != null){
            BGChanger.ChangeTheBG(bgColor,constraintLayout);
        }

    }
}
