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
import android.widget.TextView;


import com.example.myapplication.requestEngine.InputUserInfoActivity;

import java.util.ArrayList;
import java.util.List;

public class TestActivity extends AppCompatActivity implements View.OnTouchListener {

    Button requestButton;
    Animation animScale;
    String font;
    WorkWithFile workWithFile;
    List<TextView> textViewsList = new ArrayList<>();
    TextView textView,textView1,textViewSearchUserButton,textViewSearchButton,textViewRequestButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        workWithFile = new WorkWithFile();
        requestButton = findViewById(R.id.requestButton);
        requestButton.setOnTouchListener(this);
        animScale = AnimationUtils.loadAnimation(this, R.anim.alpha_animation);
        textView = findViewById(R.id.textView2);
        textViewsList.add(textView);
        textView1 = findViewById(R.id.textView3);
        textViewsList.add(textView1);
        textViewSearchUserButton = findViewById(R.id.textViewSearchUserButton);
        textViewsList.add(textViewSearchUserButton);
        textViewSearchButton = findViewById(R.id.textViewSearchButton);
        textViewsList.add(textViewSearchButton);
        textViewRequestButton = findViewById(R.id.textViewRequestButton);
        textViewsList.add(textViewRequestButton);





        font = workWithFile.ReadFromFile(workWithFile.getFontsFileName(),getApplicationContext());
        if (font != null) {
            FontChanger.changeTheFont(font,textViewsList,this);
        }


    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (v.getId()){
            case R.id.requestButton:
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        v.startAnimation(animScale);
                        break;
                        case MotionEvent.ACTION_UP:
                            Intent intent = new Intent(this, InputUserInfoActivity.class);
                            startActivity(intent);
                            break;
                }
                break;
        }
        return true;
    }
}
