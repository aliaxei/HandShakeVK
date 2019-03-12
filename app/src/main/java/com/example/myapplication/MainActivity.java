package com.example.myapplication;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.settings.SettingsActivity;
import com.example.myapplication.settings.StateSwitch;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener{
    Button startButton,loadButton,settingsButton;
    TextView textViewStartButton,textViewLoadButtons,textViewSettingsButton;
    Animation animAlpha;
    String font;
    WorkWithFile workWithFile;
<<<<<<< HEAD
    List <TextView> textViewsList = new ArrayList<>();
=======
>>>>>>> 97d7e4dc936746a5e05866d8f2b273a58f2439e8
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        workWithFile = new WorkWithFile();
<<<<<<< HEAD

        startButton = findViewById(R.id.startButton);
        startButton.setOnTouchListener(this);

=======
        button1 = findViewById(R.id.startButton);
        button1.setOnTouchListener(this);
>>>>>>> 97d7e4dc936746a5e05866d8f2b273a58f2439e8
        loadButton = findViewById(R.id.loadButton);
        loadButton.setOnTouchListener(this);

        settingsButton = findViewById(R.id.settingsButton);
        settingsButton.setOnTouchListener(this);


        textViewLoadButtons = findViewById(R.id.textViewLoadButton);
        textViewsList.add(textViewLoadButtons);
        textViewSettingsButton = findViewById(R.id.textViewSettingsButton);
        textViewsList.add(textViewSettingsButton);
        textViewStartButton = findViewById(R.id.textViewStartButton);
        textViewsList.add(textViewStartButton);
        animAlpha = AnimationUtils.loadAnimation(this, R.anim.animationscale);

<<<<<<< HEAD

        font = workWithFile.ReadFromFile(workWithFile.getFontsFileName(),getApplicationContext());
=======
        font = workWithFile.ReadFromFile(getApplicationContext());
>>>>>>> 97d7e4dc936746a5e05866d8f2b273a58f2439e8
        if (font != null) {
            FontChanger.changeTheFont(font,textViewsList,this);
        }
     }

        @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (v.getId())
        {
            case R.id.startButton: {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        v.startAnimation(animAlpha);
                        break;
                    case MotionEvent.ACTION_UP:
                        Intent intent = new Intent(this, TestActivity.class);
                        startActivity(intent);
                        break;
                }
                break;
            }
            case R.id.loadButton:{
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        v.startAnimation(animAlpha);
                        break;
                    case MotionEvent.ACTION_UP:
                        Intent intent1 = new Intent(this, SecondActivity.class);
                        startActivity(intent1);
                        break;
                }
                break;
            }
            case R.id.settingsButton:{
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        v.startAnimation(animAlpha);
                        break;
                    case MotionEvent.ACTION_UP:
                        Intent intent2 = new Intent(this, SettingsActivity.class);
                        startActivity(intent2);
                        overridePendingTransition(R.anim.activity_animation,R.anim.alpha_animation);
                        break;
                }
                break;
            }
        }
        return true;
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        font = workWithFile.ReadFromFile(workWithFile.getFontsFileName(),getApplicationContext());
        if (font != null) {
          FontChanger.changeTheFont(font,textViewsList,this);
        }

    }



}
