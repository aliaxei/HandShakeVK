package com.example.myapplication.settings;

import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.WorkWithFile;

public class SettingsActivity extends AppCompatActivity  implements CompoundButton.OnCheckedChangeListener{
    Switch superSwitch;
    Switch aSwitch;
    String state,font;
    WorkWithFile workWithFile;
    Spinner spinner;
    String[] data = {Fonts.Superfont.toString(),Fonts.Arial.toString()};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        setContentView(R.layout.activity_settings);
        workWithFile = new WorkWithFile();
        superSwitch = findViewById(R.id.superfontSwitch);
        superSwitch.setOnCheckedChangeListener(this);
        aSwitch = findViewById(R.id.arialSwitch);
        aSwitch.setOnCheckedChangeListener(this);

        font = workWithFile.ReadFromFile(workWithFile.getFontsFileName(),getApplicationContext());
        if (font != null) {
            if (font.equals(Fonts.Basic.toString())){
                superSwitch.setChecked(false);
                aSwitch.setChecked(false);
            }
            if (font.equals(Fonts.Superfont.toString())) {
                superSwitch.setChecked(true);
            }
            if (font.equals(Fonts.Arial.toString())) {
                aSwitch.setChecked(true);
            }
        }
        Typeface superfont = Typeface.createFromAsset(getAssets(), "fonts/Superfont.ttf");
        superSwitch.setTypeface(superfont);
        Typeface arialfont = Typeface.createFromAsset(getAssets(), "fonts/Arial.ttf");
        aSwitch.setTypeface(arialfont);

        //////////////////////////////

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner =  findViewById(R.id.spinner);
        spinner.setAdapter(adapter);
        spinner.setPrompt("!23");
        font = workWithFile.ReadFromFile(workWithFile.getFontsFileName(),getApplicationContext());
        if (font != null) {
            if (font.equals(Fonts.Arial.toString())) {
                spinner.setSelection(1);
            }
            if (font.equals(Fonts.Superfont.toString())) {
                spinner.setSelection(0);
            }
        }
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                workWithFile.WriteToFile(workWithFile.getFontsFileName(), parent.getSelectedItem().toString(),getApplicationContext());

            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()){
            case R.id.superfontSwitch:
                if (superSwitch.isChecked()) {
                    workWithFile.WriteToFile(workWithFile.getFontsFileName(),Fonts.Superfont.toString(), getApplicationContext());
                    aSwitch.setChecked(false);


                }
                if (!superSwitch.isChecked() && !aSwitch.isChecked()){
                    workWithFile.WriteToFile(workWithFile.getFontsFileName(),Fonts.Basic.toString(), getApplicationContext());
                }
                break;
            case R.id.arialSwitch:
                if (aSwitch.isChecked()){
                    workWithFile.WriteToFile(workWithFile.getFontsFileName(),Fonts.Arial.toString(), getApplicationContext());
                    superSwitch.setChecked(false);
                    if (!superSwitch.isChecked() && !aSwitch.isChecked()){
                        workWithFile.WriteToFile(workWithFile.getFontsFileName(),Fonts.Basic.toString(), getApplicationContext());
                    }
                }

                break;
        }

    }
    @Override
    protected void onRestart() {
        super.onRestart();

        font = workWithFile.ReadFromFile(workWithFile.getFontsFileName(),getApplicationContext());
        if (font != null) {
            if (font.equals(Fonts.Basic.toString())){
                Typeface font = Typeface.createFromAsset(getAssets(), "fonts/Basic.ttf");
                superSwitch.setTypeface(font);
                aSwitch.setTypeface(font);

            }
            if (font.equals(Fonts.Superfont.toString())) {

                Typeface font = Typeface.createFromAsset(getAssets(), "fonts/Superfont.ttf");
                superSwitch.setTypeface(font);
                aSwitch.setTypeface(font);
            }
            if (font.equals(Fonts.Arial.toString())) {
                Typeface font = Typeface.createFromAsset(getAssets(), "fonts/Arial.ttf");
                superSwitch.setTypeface(font);
                aSwitch.setTypeface(font);
            }
        }

    }

}





