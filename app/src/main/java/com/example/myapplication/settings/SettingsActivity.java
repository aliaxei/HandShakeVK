package com.example.myapplication.settings;

import android.content.pm.ActivityInfo;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.myapplication.BGChanger;
import com.example.myapplication.FontChanger;
import com.example.myapplication.R;
import com.example.myapplication.WorkWithFile;

import java.util.ArrayList;
import java.util.List;

public class SettingsActivity extends AppCompatActivity  implements CompoundButton.OnCheckedChangeListener{

    Switch greenBlueSwitch,redBlueSwitch,yellowPinkSwitch,yellowRedSwitch;
    ToggleButton musicToggle;
    String font,bgColor,tState;
    Animation alphaAnimation;
    WorkWithFile workWithFile;
    ConstraintLayout constraintLayout;
    Spinner spinner;
    MediaPlayer mediaPlayer = new MediaPlayer();
    List<Switch> switches = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_settings);
        constraintLayout =  findViewById(R.id.background);
        alphaAnimation = AnimationUtils.loadAnimation(this, R.anim.alpha_animation);
        final Typeface superFont = Typeface.createFromAsset(getAssets(), "fonts/Superfont.ttf");
        final Typeface arialFont = Typeface.createFromAsset(getAssets(), "fonts/Arial.ttf");
        final Typeface basicFont = Typeface.createFromAsset(getAssets(), "fonts/Basic.ttf");
        workWithFile = new WorkWithFile();
        musicToggle = findViewById(R.id.toggleButton);
        musicToggle.setOnCheckedChangeListener(this);
        greenBlueSwitch = findViewById(R.id.greenBlueSwitch);
        greenBlueSwitch.setOnCheckedChangeListener(this);
        switches.add(greenBlueSwitch);
        redBlueSwitch = findViewById(R.id.redBlueSwitch);
        redBlueSwitch.setOnCheckedChangeListener(this);
        switches.add(redBlueSwitch);
        yellowPinkSwitch = findViewById(R.id.yellowPinkSwitch);
        yellowPinkSwitch.setOnCheckedChangeListener(this);
        switches.add(yellowPinkSwitch);
        yellowRedSwitch = findViewById(R.id.yellowRedSwitch);
        yellowRedSwitch.setOnCheckedChangeListener(this);
        switches.add(yellowRedSwitch);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.fonts)){
            public View getView(int position, View convertView, ViewGroup parent) {
                // Cast the spinner collapsed item (non-popup item) as a text view
                TextView tv = (TextView) super.getView(position, convertView, parent);
                tv.setTextColor(Color.parseColor("#000000"));
                tv.setGravity(20);
                tv.setHeight(100);
                tv.setTextSize(20);
                tv.setBackgroundResource(R.drawable.spiner_style_without_stroke);
                switch (position) {
                    case 0:
                        tv.setTypeface(arialFont);
                        break;
                    case 1:
                        tv.setTypeface(superFont);
                        break;
                    case 2:
                        tv.setTypeface(basicFont);
                        break;
                }
                return tv;
            }
            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent){
                // Cast the drop down items (popup items) as text view
                TextView tv = (TextView) super.getDropDownView(position,convertView,parent);

                tv.setWidth(950);
                tv.setTextSize(20);
                tv.setTextColor(Color.parseColor("#000000"));
                tv.setBackgroundResource(R.drawable.spiner_style);
                switch (position) {
                    case 0:
                        tv.setTypeface(arialFont);
                        break;
                    case 1:
                        tv.setTypeface(superFont);
                        break;
                    case 2:
                        tv.setTypeface(basicFont);
                        break;
                }
                return tv;
            }
        };
       adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner = findViewById(R.id.spinner);
        spinner.setAdapter(adapter);
        font = workWithFile.ReadFromFile(workWithFile.getFontsFileName(),getApplicationContext());
        if(font != null){
           FontChanger.changeTheSwitchFont(font,switches,getApplicationContext());
          SelectionSetter.SetSelection(font,spinner);
        }
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                workWithFile.WriteToFile(workWithFile.getFontsFileName(), parent.getSelectedItem().toString(),getApplicationContext());
                font = workWithFile.ReadFromFile(workWithFile.getFontsFileName(),getApplicationContext());
                if(font != null){
                    FontChanger.changeTheSwitchFont(font,switches,getApplicationContext());
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        tState = workWithFile.ReadFromFile(workWithFile.getToggleFileName(),getApplicationContext());
        if(tState != null){
            mediaPlayer = MusicState.setPlayer(tState,mediaPlayer,getApplicationContext());
            ToggleChanger.setChecked(tState,musicToggle);
        }

        bgColor = workWithFile.ReadFromFile(workWithFile.getBgFileName(),getApplicationContext());
        if (bgColor != null){
            BGChanger.ChangeTheBG(bgColor,constraintLayout);
            BGChanger.setCheckedSwitch(bgColor,greenBlueSwitch,redBlueSwitch,yellowPinkSwitch,yellowRedSwitch);
        }
    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (!greenBlueSwitch.isChecked() && !redBlueSwitch.isChecked() && !yellowRedSwitch.isChecked() && !yellowPinkSwitch.isChecked()){
                   workWithFile.WriteToFile(workWithFile.getBgFileName(),BGradients.bggradient.toString(), getApplicationContext());
            constraintLayout.setBackgroundResource(R.drawable.bggradient);
        }
            switch (buttonView.getId()){
                case R.id.toggleButton:
                    if (musicToggle.isChecked()){
                        workWithFile.WriteToFile(workWithFile.getToggleFileName(),ToggleState.Off.toString(),getApplicationContext());
                    }else {
                        workWithFile.WriteToFile(workWithFile.getToggleFileName(), ToggleState.On.toString(), getApplicationContext());
                        Toast toast = Toast.makeText(getApplicationContext(), "Songs Effects is ON", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                   break;
                case R.id.greenBlueSwitch:
                    if (greenBlueSwitch.isChecked()) {

                        constraintLayout.setBackgroundResource(R.drawable.bg_gradient_green_blue);
                        workWithFile.WriteToFile(workWithFile.getBgFileName(),BGradients.bg_gradient_green_blue.toString(),getApplicationContext());
                        redBlueSwitch.setChecked(false);
                        yellowPinkSwitch.setChecked(false);
                        yellowRedSwitch.setChecked(false);
                        musicToggle.setChecked(false);
                    }
                    break;
                case R.id.redBlueSwitch:
                    if (redBlueSwitch.isChecked()) {
                        constraintLayout.setBackgroundResource(R.drawable.bg_gradient_red_blue);
                        workWithFile.WriteToFile(workWithFile.getBgFileName(),BGradients.bg_gradient_red_blue.toString(),getApplicationContext());
                        greenBlueSwitch.setChecked(false);
                        yellowPinkSwitch.setChecked(false);
                        yellowRedSwitch.setChecked(false);
                    }
                case R.id.yellowPinkSwitch:
                    if (yellowPinkSwitch.isChecked()) {
                        constraintLayout.setBackgroundResource(R.drawable.bg_gradient_yellow_pink);
                        workWithFile.WriteToFile(workWithFile.getBgFileName(), BGradients.bg_gradient_yellow_pink.toString(), getApplicationContext());
                        redBlueSwitch.setChecked(false);
                        greenBlueSwitch.setChecked(false);
                        yellowRedSwitch.setChecked(false);
                    }
                case R.id.yellowRedSwitch:
                    if (yellowRedSwitch.isChecked()) {
                        constraintLayout.setBackgroundResource(R.drawable.bg_gradient_yellow_red);
                        workWithFile.WriteToFile(workWithFile.getBgFileName(), BGradients.bg_gradient_yellow_red.toString(), getApplicationContext());
                        redBlueSwitch.setChecked(false);
                        yellowPinkSwitch.setChecked(false);
                        greenBlueSwitch.setChecked(false);
                    }
                    break;
            }

    }


    @Override
    protected void onRestart() {
        super.onRestart();
        tState = workWithFile.ReadFromFile(workWithFile.getToggleFileName(),getApplicationContext());
        if(tState != null){
            ToggleChanger.setChecked(tState,musicToggle);
        }
        font = workWithFile.ReadFromFile(workWithFile.getFontsFileName(),getApplicationContext());
        if(font != null){
            FontChanger.changeTheSwitchFont(font,switches,getApplicationContext());
        }
        bgColor = workWithFile.ReadFromFile(workWithFile.getBgFileName(),getApplicationContext());
        if (bgColor != null){
            BGChanger.ChangeTheBG(bgColor,constraintLayout);
            BGChanger.setCheckedSwitch(bgColor,greenBlueSwitch,redBlueSwitch,yellowPinkSwitch,yellowRedSwitch);
        }
    }
}





