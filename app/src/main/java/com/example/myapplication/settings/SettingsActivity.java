package com.example.myapplication.settings;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.example.myapplication.R;
import com.example.myapplication.WorkWithFile;
import com.example.myapplication.requestEngine.Application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;

public class SettingsActivity extends AppCompatActivity  {
    Switch superSwitch;
    Switch aSwitch;
    WorkWithFile workWithFile;
    final String FILENAME = "font";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        setContentView(R.layout.activity_settings);
        workWithFile = new WorkWithFile();
        superSwitch = findViewById(R.id.superfontSwitch);
        superSwitch.setChecked(false);
        superSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (superSwitch.isChecked()) {
                    workWithFile.WriteToFile(Fonts.Superfont, getApplicationContext());
                    aSwitch.setChecked(false);
                    Typeface font = Typeface.createFromAsset(getAssets(), "fonts/14704.ttf");
                    superSwitch.setTypeface(font);
                    aSwitch.setTypeface(font);
                }else {
                    Typeface typeface = Typeface.DEFAULT;
                    superSwitch.setTypeface(typeface);
                    aSwitch.setTypeface(typeface);
                }
            }
        });
        aSwitch = findViewById(R.id.arialSwitch);
        aSwitch.setChecked(false);
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (aSwitch.isChecked()){
                    try {
                        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                                openFileOutput(FILENAME, MODE_PRIVATE)));
                        bw.write(Fonts.Arial.toString());
                        bw.close();

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    superSwitch.setChecked(false);
                    Typeface font = Typeface.createFromAsset(getAssets(), "fonts/14372.ttf");
                    superSwitch.setTypeface(font);
                    aSwitch.setTypeface(font);
                }else {
                    Typeface typeface = Typeface.DEFAULT;
                    superSwitch.setTypeface(typeface);
                    aSwitch.setTypeface(typeface);
                }
            }
        });
//        fontButton = findViewById(R.id.fontButton);
//        fontButton.setOnTouchListener(this);
//
//        font2Button = findViewById(R.id.font2Button);
//        font2Button.setOnTouchListener(this);
        //animAlpha = AnimationUtils.loadAnimation(this, R.anim.alpha_animation);
    }



//    @Override
//    public boolean onTouch(View v, MotionEvent event) {
//        switch (v.getId()){
//            case R.id.fontButton:
//                switch (event.getAction()){
//                    case MotionEvent.ACTION_DOWN:
//                        fontButton.startAnimation(animAlpha);
//                        break;
//                    case MotionEvent.ACTION_UP:
//                        try {
//                            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
//                                    openFileOutput(FILENAME, MODE_PRIVATE)));
//                            bw.write(Fonts.Superfont.toString());
//                            bw.close();
//
//                        } catch (FileNotFoundException e) {
//                            e.printStackTrace();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/14704.ttf");
//                        fontButton.setTypeface(font);
//                        font2Button.setTypeface(font);
//                        break;
//                }
//                break;
//            case R.id.font2Button:
//                switch (event.getAction()){
//                    case MotionEvent.ACTION_DOWN:
//                        font2Button.startAnimation(animAlpha);
//                        break;
//                    case MotionEvent.ACTION_UP:
//                        try {
//                            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
//                                    openFileOutput(FILENAME, MODE_PRIVATE)));
//                            bw.write(Fonts.Arial.toString());
//                            bw.close();
//
//                        } catch (FileNotFoundException e) {
//                            e.printStackTrace();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                        Typeface font1 = Typeface.createFromAsset(getAssets(), "fonts/14372.ttf");
//                        fontButton.setTypeface(font1);
//                        font2Button.setTypeface(font1);
//                        break;
//                }
//                break;
//        }
//
//        return true;
//    }
}
