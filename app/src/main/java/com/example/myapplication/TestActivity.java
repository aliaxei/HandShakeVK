package com.example.myapplication;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ScrollView;
import android.widget.TextView;


import com.example.myapplication.settings.BGChanger;

import java.util.ArrayList;
import java.util.List;

public class TestActivity extends AppCompatActivity  {
TextView largetextView;

    String font,bgColor,musicState;
    ScrollView scrollView;
    WorkWithFile workWithFile;
    MediaPlayer mp = new MediaPlayer();
    List <TextView> textViewsList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        scrollView = findViewById(R.id.background);
        workWithFile = new WorkWithFile();
        largetextView = findViewById(R.id.largeTextView);
        textViewsList.add(largetextView);





        Intent intent = new Intent(this, MainActivity.class);
       // intent.putExtra(MainActivity.ExtraData, intent);
       // ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, findViewById(R.id.questionButton), "questionTransition");
      //  startActivity(intent, options.toBundle());
        bgColor = workWithFile.ReadFromFile(workWithFile.getBgFileName(),getApplicationContext());
        if (bgColor != null){
            BGChanger.ChangeScrollViewBG(bgColor,scrollView);
            BGChanger.bgForTextViews(bgColor,textViewsList);
        }
        font = workWithFile.ReadFromFile(workWithFile.getFontsFileName(),getApplicationContext());
        if (font != null) {
            FontChanger.changeTheFont(font,textViewsList,this);
        }
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        bgColor = workWithFile.ReadFromFile(workWithFile.getBgFileName(), getApplicationContext());
        if (bgColor != null) {
            BGChanger.ChangeScrollViewBG(bgColor, scrollView);
            BGChanger.bgForTextViews(bgColor, textViewsList);
        }
        font = workWithFile.ReadFromFile(workWithFile.getFontsFileName(), getApplicationContext());
        if (font != null) {
            FontChanger.changeTheFont(font, textViewsList, this);
        }
    }
}
