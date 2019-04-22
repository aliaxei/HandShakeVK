package com.example.myapplication.settings;

import android.widget.ToggleButton;

public class ToggleChanger {
    public static void setChecked(String tState, ToggleButton tButton){
//        if (tState.equals(ToggleState.On.toString())){
//            tButton.setChecked(false);
//        }if(tState.equals(ToggleState.Off.toString())){
//            tButton.setChecked(true);
//        }
        switch (tState){
            case "On":
                tButton.setChecked(false);
                break;
            case "Off":
                tButton.setChecked(true);
                break;
        }

    }

}
