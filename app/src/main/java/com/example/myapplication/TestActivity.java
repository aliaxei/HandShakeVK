package com.example.myapplication;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.myapplication.requestEngine.InputUserInfoActivity;

import java.util.ArrayList;
import java.util.List;

public class TestActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Intent intent = new Intent(this, MainActivity.class);
       // intent.putExtra(MainActivity.ExtraData, intent);
       // ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, findViewById(R.id.questionButton), "questionTransition");
      //  startActivity(intent, options.toBundle());

    }
}
