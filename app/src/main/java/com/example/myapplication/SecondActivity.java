package com.example.myapplication;

import android.content.pm.ActivityInfo;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.myapplication.views.SecondActivityView;

public class SecondActivity extends AppCompatActivity {
SecondActivityView secondActivityView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        secondActivityView = findViewById(R.id.secondActivityView);
        ImageView img = (ImageView)findViewById(R.id.animationView);
        img.setBackgroundResource(R.drawable.animation_planet);

// получаем объект анимации
        AnimationDrawable frameAnimation = (AnimationDrawable) img.getBackground();
       // frameAnimation.setOneShot(false);
// запуск анимации
        frameAnimation.start();
    }
}
