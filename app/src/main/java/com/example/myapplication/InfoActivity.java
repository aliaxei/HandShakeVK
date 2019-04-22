package com.example.myapplication;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


public class InfoActivity extends AppCompatActivity {
    MainActivity activity = new MainActivity();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);

       /* Bundle bundle = null;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1){
            View v = findViewById(R.id.infoButton);
            if (v != null) {
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(activity, v, activity.getString(R.string.question_mark));
                bundle = options.toBundle();
            }
        }
        Intent intent = new Intent(activity, InfoActivity.class);
        if (bundle == null) {
            activity.startActivity(intent);
        } else {
            activity.startActivity(intent, bundle);
        }*/
    }
}
