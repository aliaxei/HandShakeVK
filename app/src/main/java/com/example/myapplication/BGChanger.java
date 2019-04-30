package com.example.myapplication;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.widget.Switch;

import com.example.myapplication.settings.BGradients;

public class BGChanger {
    public static void ChangeTheBG(String color, ConstraintLayout constraintLayout){
        constraintLayout.findViewById(R.id.background);
        if (color.equals(BGradients.bggradient.toString())) {
            constraintLayout.setBackgroundResource(R.drawable.bggradient);
        }
        if (color.equals(BGradients.bg_gradient_green_blue.toString())) {
            constraintLayout.setBackgroundResource(R.drawable.bg_gradient_green_blue);
        }
        if (color.equals(BGradients.bg_gradient_red_blue.toString())) {
            constraintLayout.setBackgroundResource(R.drawable.bg_gradient_red_blue);
        }
        if (color.equals(BGradients.bg_gradient_yellow_pink.toString())) {
            constraintLayout.setBackgroundResource(R.drawable.bg_gradient_yellow_pink);
        }
        if (color.equals(BGradients.bg_gradient_yellow_red.toString())) {
            constraintLayout.setBackgroundResource(R.drawable.bg_gradient_yellow_red);
        }
    }
    public static void setCheckedSwitch(String bgColor, Switch greenBlueSwitch,Switch redBlueSwitch,Switch yellowPinkSwitch,Switch yellowRedSwitch){

        if (bgColor.equals(BGradients.bg_gradient_green_blue.toString())) {
            greenBlueSwitch.setChecked(true);
        }
        if (bgColor.equals(BGradients.bg_gradient_red_blue.toString())) {
          redBlueSwitch.setChecked(true);
        }
        if (bgColor.equals(BGradients.bg_gradient_yellow_pink.toString())) {
            yellowPinkSwitch.setChecked(true);
        }
        if (bgColor.equals(BGradients.bg_gradient_yellow_red.toString())) {
            yellowRedSwitch.setChecked(true);
        }
    }
}
