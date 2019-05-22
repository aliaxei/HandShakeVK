package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.myapplication.requestEngine.InputUserInfoActivity;
import com.example.myapplication.requestEngine.outputActivity.HandShakeOutputScrollingActivity;
import com.example.myapplication.requestEngine.outputActivity.OutputForPWOS;
import com.example.myapplication.settings.BGChanger;
import com.example.myapplication.views.SecondActivityView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;




public class SecondActivity extends AppCompatActivity {
    String bgColor;
    WorkWithFile workWithFile;
    ConstraintLayout constraintLayout;
    ProgressBar progressBar;
    private static int counter,temp;
    Timer t = new Timer();
    List<Integer> ids = new ArrayList<>();
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_second);
        constraintLayout = findViewById(R.id.background);
        workWithFile = new WorkWithFile();



        ImageView img =  findViewById(R.id.animationView);
        img.setBackgroundResource(R.drawable.animation_planet);
// получаем объект анимации
        AnimationDrawable frameAnimation = (AnimationDrawable) img.getBackground();
        // frameAnimation.setOneShot(false);
// запуск анимации
        frameAnimation.start();
        progressBar = findViewById(R.id.progressBar2);
        Drawable draw= getDrawable(R.drawable.super_proggress_bar);
        progressBar.setProgressDrawable(draw);
        //ImageView imageView;
        //imageView = (ImageView) findViewById(R.id.imageView);
        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        int screen_height = metrics.widthPixels - 300;
        img.getLayoutParams().height = screen_height;
        bgColor = workWithFile.ReadFromFile(workWithFile.getBgFileName(), getApplicationContext());
        if (bgColor != null) {
            BGChanger.ChangeTheBG(bgColor, constraintLayout);
        }
        counter = 0;

        final Toast toast = Toast.makeText(this,"Load Complete!",Toast.LENGTH_LONG);
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                ids = workWithFile.ReadListFromFile(workWithFile.getVkIdList(),getApplicationContext());
                temp = ids.size();
                ids.clear();
                if(temp != 0) {
                    counter += 100 / temp;
                }
             progressBar.setProgress(counter);
             if (counter >= 100){
                 t.cancel();
                 toast.show();
                 Boolean flag;
                 bgColor = workWithFile.ReadFromFile(workWithFile.getBgFileName(),getApplicationContext());
                 if (bgColor != null) {
                     flag = BGChanger.ChangeView(bgColor);
                     if (flag == true){
                         intent = new Intent(SecondActivity.this, HandShakeOutputScrollingActivity.class);
                     }else {
                         intent = new Intent(SecondActivity.this, OutputForPWOS.class);
                     }
                 }
                 startActivity(intent);
                 overridePendingTransition(R.anim.activity_animation,R.anim.alpha_animation);

             }
            }
        };
        t.schedule(timerTask,0,3000);

    }




    @Override
    protected void onRestart() {
        super.onRestart();
        Intent intent1 = new Intent(SecondActivity.this, InputUserInfoActivity.class);
        startActivity(intent1);
        overridePendingTransition(R.anim.activity_animation,R.anim.alpha_animation);
        bgColor = workWithFile.ReadFromFile(workWithFile.getBgFileName(),getApplicationContext());
        if (bgColor != null){
            BGChanger.ChangeTheBG(bgColor,constraintLayout);
        }
    }
}
