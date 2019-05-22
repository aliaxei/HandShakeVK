package com.example.myapplication.settings.extendedSettings;

import android.content.pm.ActivityInfo;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.example.myapplication.settings.BGChanger;
import com.example.myapplication.FontChanger;
import com.example.myapplication.R;
import com.example.myapplication.WorkWithFile;
import com.example.myapplication.settings.BGradients;
import com.example.myapplication.settings.StateSwitch;

import java.util.ArrayList;
import java.util.List;

public class ExtendedSettingsActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {
    Switch whiteSwitch,blueSwitch,brownSwitch,dkbrownSwitch;
    String font,bgColor,swState;
    WorkWithFile workWithFile;
    ConstraintLayout constraintLayout;
    List<Switch> switches = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_extended_settings);
        constraintLayout =  findViewById(R.id.background);

        workWithFile = new WorkWithFile();
        whiteSwitch = findViewById(R.id.whiteSwitch);
        whiteSwitch.setOnCheckedChangeListener(this);
        switches.add(whiteSwitch);
        blueSwitch = findViewById(R.id.blueSwitch);
        blueSwitch.setOnCheckedChangeListener(this);
        switches.add(blueSwitch);
        brownSwitch = findViewById(R.id.brownSwitch);
        brownSwitch.setOnCheckedChangeListener(this);
        switches.add(brownSwitch);
        dkbrownSwitch = findViewById(R.id.dkbrownSwitch);
        dkbrownSwitch.setOnCheckedChangeListener(this);
        switches.add(dkbrownSwitch);



        bgColor = workWithFile.ReadFromFile(workWithFile.getBgFileName(),getApplicationContext());
        if (bgColor != null){
            BGChanger.ChangeTheBG(bgColor,constraintLayout);
            BGChanger.setCheckedExtendedSwitch(bgColor,whiteSwitch,blueSwitch,brownSwitch,dkbrownSwitch);
            BGChanger.bgForSwitches(bgColor,switches);
        }
        swState = workWithFile.ReadFromFile(workWithFile.getSwitchState(),this);
        if (swState != null){
            if (swState.equals(StateSwitch.Unlocked.toString())) {
                constraintLayout.setBackgroundResource(R.drawable.bggradient);
                workWithFile.WriteToFile(workWithFile.getBgFileName(), BGradients.bggradient.toString(), getApplicationContext());
            }
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()){

            case R.id.whiteSwitch:
                if (whiteSwitch.isChecked()) {
                    blueSwitch.setChecked(false);
                    brownSwitch.setChecked(false);
                    dkbrownSwitch.setChecked(false);
                    constraintLayout.setBackgroundResource(R.drawable.white);
                    workWithFile.WriteToFile(workWithFile.getBgFileName(), BGradients.white.toString(),getApplicationContext());
                    workWithFile.WriteToFile(workWithFile.getSwitchState(), StateSwitch.Locked.toString(),getApplicationContext());
                    bgColor = workWithFile.ReadFromFile(workWithFile.getBgFileName(),getApplicationContext());
                    if (bgColor != null){
                        BGChanger.bgForSwitches(bgColor,switches);
                    }
                }
                if (!whiteSwitch.isChecked()){
                    workWithFile.WriteToFile(workWithFile.getSwitchState(),StateSwitch.Unlocked.toString(),getApplicationContext());
                    constraintLayout.setBackgroundResource(R.drawable.bggradient);
                    workWithFile.WriteToFile(workWithFile.getBgFileName(),BGradients.bggradient.toString(),getApplicationContext());
                    bgColor = workWithFile.ReadFromFile(workWithFile.getBgFileName(),getApplicationContext());
                    if (bgColor != null){
                        BGChanger.bgForSwitches(bgColor,switches);
                    }
                }
                break;
            case R.id.blueSwitch:
                if (blueSwitch.isChecked()) {
                    whiteSwitch.setChecked(false);
                    brownSwitch.setChecked(false);
                    dkbrownSwitch.setChecked(false);
                    constraintLayout.setBackgroundResource(R.drawable.blue_);
                    workWithFile.WriteToFile(workWithFile.getBgFileName(),BGradients.bllue.toString(),getApplicationContext());
                    workWithFile.WriteToFile(workWithFile.getSwitchState(),StateSwitch.Locked.toString(),getApplicationContext());
                    bgColor = workWithFile.ReadFromFile(workWithFile.getBgFileName(),getApplicationContext());
                    if (bgColor != null){
                        BGChanger.bgForSwitches(bgColor,switches);
                    }
                }
                if (!blueSwitch.isChecked()){
                    workWithFile.WriteToFile(workWithFile.getSwitchState(),StateSwitch.Unlocked.toString(),getApplicationContext());
                    constraintLayout.setBackgroundResource(R.drawable.bggradient);
                    workWithFile.WriteToFile(workWithFile.getBgFileName(),BGradients.bggradient.toString(),getApplicationContext());
                    bgColor = workWithFile.ReadFromFile(workWithFile.getBgFileName(),getApplicationContext());
                    if (bgColor != null){
                        BGChanger.bgForSwitches(bgColor,switches);
                    }
                }
                break;
            case R.id.brownSwitch:
                if (brownSwitch.isChecked()) {
                    whiteSwitch.setChecked(false);
                    dkbrownSwitch.setChecked(false);
                    blueSwitch.setChecked(false);
                    constraintLayout.setBackgroundResource(R.drawable.brown_);
                    workWithFile.WriteToFile(workWithFile.getBgFileName(), BGradients.brown.toString(), getApplicationContext());
                    workWithFile.WriteToFile(workWithFile.getSwitchState(),StateSwitch.Locked.toString(),getApplicationContext());
                    bgColor = workWithFile.ReadFromFile(workWithFile.getBgFileName(),getApplicationContext());
                    if (bgColor != null){
                        BGChanger.bgForSwitches(bgColor,switches);
                    }
                }
                if (!brownSwitch.isChecked()){
                    workWithFile.WriteToFile(workWithFile.getSwitchState(),StateSwitch.Unlocked.toString(),getApplicationContext());
                    constraintLayout.setBackgroundResource(R.drawable.bggradient);
                    workWithFile.WriteToFile(workWithFile.getBgFileName(),BGradients.bggradient.toString(),getApplicationContext());
                    bgColor = workWithFile.ReadFromFile(workWithFile.getBgFileName(),getApplicationContext());
                    if (bgColor != null){
                        BGChanger.bgForSwitches(bgColor,switches);
                    }
                }
                break;
            case R.id.dkbrownSwitch:

                if (dkbrownSwitch.isChecked()) {
                    whiteSwitch.setChecked(false);
                    blueSwitch.setChecked(false);
                    brownSwitch.setChecked(false);
                    constraintLayout.setBackgroundResource(R.drawable.dkbrown_);
                    workWithFile.WriteToFile(workWithFile.getBgFileName(),BGradients.darkbrown.toString(), getApplicationContext());
                    workWithFile.WriteToFile(workWithFile.getSwitchState(),StateSwitch.Locked.toString(),getApplicationContext());
                    bgColor = workWithFile.ReadFromFile(workWithFile.getBgFileName(),getApplicationContext());
                    if (bgColor != null){
                        BGChanger.bgForSwitches(bgColor,switches);
                    }
                }
                if (!dkbrownSwitch.isChecked()){
                    workWithFile.WriteToFile(workWithFile.getSwitchState(),StateSwitch.Unlocked.toString(),getApplicationContext());
                    constraintLayout.setBackgroundResource(R.drawable.bggradient);
                    workWithFile.WriteToFile(workWithFile.getBgFileName(),BGradients.bggradient.toString(),getApplicationContext());
                    bgColor = workWithFile.ReadFromFile(workWithFile.getBgFileName(),getApplicationContext());
                    if (bgColor != null){
                        BGChanger.bgForSwitches(bgColor,switches);
                    }
                }

                break;
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        font = workWithFile.ReadFromFile(workWithFile.getFontsFileName(),getApplicationContext());
        if(font != null){
            FontChanger.changeTheSwitchFont(font,switches,getApplicationContext());
        }
        bgColor = workWithFile.ReadFromFile(workWithFile.getBgFileName(),getApplicationContext());
        if (bgColor != null){
            BGChanger.ChangeTheBG(bgColor,constraintLayout);
            BGChanger.setCheckedExtendedSwitch(bgColor,whiteSwitch,blueSwitch,brownSwitch,dkbrownSwitch);
            BGChanger.bgForSwitches(bgColor,switches);

        }
        swState = workWithFile.ReadFromFile(workWithFile.getSwitchState(),this);
        if (swState != null){
            if (swState.equals(StateSwitch.Unlocked.toString())) {
                constraintLayout.setBackgroundResource(R.drawable.bggradient);
                workWithFile.WriteToFile(workWithFile.getBgFileName(),BGradients.bggradient.toString(), getApplicationContext());
            }
        }
    }
}
