package com.example.myapplication;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.requestEngine.InputUserInfoActivity;
import com.example.myapplication.settings.BGChanger;
import com.example.myapplication.settings.MusicState;
import com.example.myapplication.settings.SettingsActivity;
import com.example.myapplication.settings.extendedSettings.ExtendedSettingsActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener{
    Button startButton,loadButton,settingsButton;
    TextView textViewStartButton,textViewLoadButtons,textViewSettingsButton;
    ImageView questionButton;
    Animation animAlpha;
    String font,bgColor,musicState;
    ConstraintLayout constraintLayout;
    WorkWithFile workWithFile;
    MediaPlayer mp = new MediaPlayer();
    List <TextView> textViewsList = new ArrayList<>();
    List<Button> buttons = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        constraintLayout =  findViewById(R.id.background);

        workWithFile = new WorkWithFile();

        startButton = findViewById(R.id.startButton);
        startButton.setOnTouchListener(this);
buttons.add(startButton);
        loadButton = findViewById(R.id.loadButton);
        loadButton.setOnTouchListener(this);
        buttons.add(loadButton);
        settingsButton = findViewById(R.id.settingsButton);
        settingsButton.setOnTouchListener(this);
        buttons.add(settingsButton);
        questionButton = findViewById(R.id.questionButton);
        questionButton.setOnTouchListener(this);


        textViewLoadButtons = findViewById(R.id.textViewLoadButton);
        textViewsList.add(textViewLoadButtons);
        textViewSettingsButton = findViewById(R.id.textViewSettingsButton);
        textViewsList.add(textViewSettingsButton);
        textViewStartButton = findViewById(R.id.textViewStartButton);
        textViewsList.add(textViewStartButton);
        animAlpha = AnimationUtils.loadAnimation(this, R.anim.animationscale);

        bgColor = workWithFile.ReadFromFile(workWithFile.getBgFileName(),getApplicationContext());
        if (bgColor != null){
            BGChanger.ChangeTheBG(bgColor,constraintLayout);
            BGChanger.bgForButtons(bgColor,buttons);
            BGChanger.bgForTextViews(bgColor,textViewsList);
        }
        font = workWithFile.ReadFromFile(workWithFile.getFontsFileName(),getApplicationContext());
        if (font != null) {
            FontChanger.changeTheFont(font,textViewsList,this);
        }
        musicState = workWithFile.ReadFromFile(workWithFile.getToggleFileName(),getApplicationContext());
        if (musicState != null ){
           mp = MusicState.setPlayer(musicState,mp,getApplicationContext());
        }
     }

        @Override
    public boolean onTouch(View v, MotionEvent event) {



        switch (v.getId())
        {
            case R.id.startButton: {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        mp.start();
                        v.startAnimation(animAlpha);
                        break;
                    case MotionEvent.ACTION_UP:
                        Intent intent = new Intent(this, InputUserInfoActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.activity_animation,R.anim.alpha_animation);
                        break;
                }
                break;
            }
            case R.id.loadButton:{
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        mp.start();
                        v.startAnimation(animAlpha);
                        break;
                    case MotionEvent.ACTION_UP:
                        Intent intent1 = new Intent(this, ExtendedSettingsActivity.class);
                        startActivity(intent1);
                        overridePendingTransition(R.anim.activity_animation,R.anim.alpha_animation);
                        break;
                }
                break;
            }
            case R.id.settingsButton:{
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        mp.start();
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
            case R.id.questionButton:{
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        mp.start();
                        Intent intent3 = new Intent(this, TestActivity.class);
                        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                                MainActivity.this, findViewById(R.id.questionButton), "questionTransition");
                        startActivity(intent3, options.toBundle());
                }
            }
        }
        return true;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        bgColor = workWithFile.ReadFromFile(workWithFile.getBgFileName(),getApplicationContext());
        if (bgColor != null){
            BGChanger.ChangeTheBG(bgColor,constraintLayout);
            BGChanger.bgForButtons(bgColor,buttons);
            BGChanger.bgForTextViews(bgColor,textViewsList);
        }
        font = workWithFile.ReadFromFile(workWithFile.getFontsFileName(),getApplicationContext());
        if (font != null) {
          FontChanger.changeTheFont(font,textViewsList,this);
        }
        musicState = workWithFile.ReadFromFile(workWithFile.getToggleFileName(),getApplicationContext());
        if (musicState != null ){
            mp = MusicState.setPlayer(musicState,mp,getApplicationContext());
        }
    }



}
