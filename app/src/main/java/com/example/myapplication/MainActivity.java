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

import com.example.myapplication.settings.Fonts;
import com.example.myapplication.settings.SettingsActivity;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener{
    final String FILENAME = "font";
    Button button1;
    Button loadButton;
    Button settingsButton;
    Animation animAlpha;
    String font;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        button1 = findViewById(R.id.startButton);
        button1.setOnTouchListener(this);
        loadButton = findViewById(R.id.loadButton);
        loadButton.setOnTouchListener(this);
        settingsButton = findViewById(R.id.settingsButton);
        settingsButton.setOnTouchListener(this);
        animAlpha = AnimationUtils.loadAnimation(this, R.anim.animationscale);

     }

    @Override
    protected void onRestart() {
        super.onRestart();

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    openFileInput(FILENAME)));
            font = br.readLine();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (font != null) {
            if (font.equals(Fonts.Superfont.toString())) {
                Typeface font = Typeface.createFromAsset(getAssets(), "fonts/14704.ttf");
                button1.setTypeface(font);
                loadButton.setTypeface(font);
                settingsButton.setTypeface(font);
            }
            if (font.equals(Fonts.Arial.toString())) {
                Typeface font = Typeface.createFromAsset(getAssets(), "fonts/14372.ttf");
                button1.setTypeface(font);
                loadButton.setTypeface(font);
                settingsButton.setTypeface(font);
            }
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
