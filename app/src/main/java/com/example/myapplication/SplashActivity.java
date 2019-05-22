package com.example.myapplication;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.Image;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.SeekBar;

import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;

import java.util.Timer;
import java.util.TimerTask;

import me.itangqi.waveloadingview.WaveLoadingView;

public class SplashActivity extends AppCompatActivity {
    int counter = 0;
    WaveLoadingView waveLoadingView;
    ImageView hands;
    Animation animAlpha,animScale;
    DiscreteSeekBar discreteSeekBar;
    private final long updateDelay = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        waveLoadingView = findViewById(R.id.waveLoadingView);
        hands = findViewById(R.id.hands);
        waveLoadingView.setShapeType(WaveLoadingView.ShapeType.CIRCLE);
        waveLoadingView.setProgressValue(55);
        waveLoadingView.setAmplitudeRatio(70);
        waveLoadingView.setAnimDuration(4000);
        waveLoadingView.startAnimation();
        discreteSeekBar = findViewById(R.id.seekbar);
        animAlpha = AnimationUtils.loadAnimation(this, R.anim.anim_alpha_for_splashscreen);
        animScale = AnimationUtils.loadAnimation(this,R.anim.anim_scale_alpha_for_logo);
        makeSplash();

    }
    public void makeSplash(){
        Timer t  = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                counter += 5;
                discreteSeekBar.setProgress(counter);
                if (counter == 90){
                    waveLoadingView.startAnimation(animAlpha);
                    waveLoadingView.setVisibility(View.INVISIBLE);

                }
                if (counter == 100) {
                    hands.startAnimation(animScale);
                    hands.setVisibility(View.INVISIBLE);
                    discreteSeekBar.setVisibility(View.INVISIBLE);
                }
                if (counter == 110){
                    Intent intent = new Intent(SplashActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        };
        t.schedule(timerTask,0,300);

    }

}
