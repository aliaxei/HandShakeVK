package com.example.myapplication.settings;

import android.content.Context;
import android.graphics.Color;
import android.graphics.SweepGradient;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.settings.BGradients;

import java.util.List;

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
        if (color.equals(BGradients.white.toString())) {
            constraintLayout.setBackgroundResource(R.drawable.white);
        }
        if (color.equals(BGradients.bllue.toString())) {
            constraintLayout.setBackgroundResource(R.drawable.blue_);
        }
        if (color.equals(BGradients.brown.toString())) {
            constraintLayout.setBackgroundResource(R.drawable.brown_);
        }
        if (color.equals(BGradients.darkbrown.toString())) {
            constraintLayout.setBackgroundResource(R.drawable.dkbrown_);
        }
    }
    public static void bgForButtons(String color,List<Button> buttons){

       if(color.equals(BGradients.bg_gradient_yellow_pink.toString()) || color.equals(BGradients.bg_gradient_red_blue.toString()) ||
               color.equals(BGradients.bg_gradient_yellow_red.toString()) || color.equals(BGradients.bg_gradient_green_blue.toString()) ||
               color.equals(BGradients.bggradient.toString())) {
           for (Button b : buttons) {
               b.setTextColor(Color.WHITE);
               b.setBackgroundResource(R.drawable.buttonstyle);
           }
       }
        if (color.equals(BGradients.white.toString())) {
            for (Button b:buttons) {
                b.setTextColor(Color.BLACK);
               b.setBackgroundResource(R.drawable.white);
                b.setBackgroundResource(R.drawable.white_back_for_pwos);
            }
        }
        if (color.equals(BGradients.bllue.toString())) {
            for (Button b:buttons) {
                b.setTextColor(Color.parseColor("#063462"));
                b.setBackgroundResource(R.drawable.blue_back_for_pwos);
            }
        }
        if (color.equals(BGradients.brown.toString())) {
            for (Button b:buttons) {
                b.setTextColor(Color.parseColor("#4D4B43"));
                b.setBackgroundResource(R.drawable.brown_);
                b.setBackgroundResource(R.drawable.brown_back_for_pwos);
            }
        }
        if (color.equals(BGradients.darkbrown.toString())) {
            for (Button b:buttons) {
                b.setTextColor(Color.parseColor("#A9E44D"));
                b.setBackgroundResource(R.drawable.dkbrown_);
                b.setBackgroundResource(R.drawable.green_back_for_pwos);
            }
        }
    }


    public static void bgForTextViews(String color, List<TextView> textViews){
        if(color.equals(BGradients.bg_gradient_yellow_pink.toString()) || color.equals(BGradients.bg_gradient_red_blue.toString()) ||
                color.equals(BGradients.bg_gradient_yellow_red.toString()) || color.equals(BGradients.bg_gradient_green_blue.toString()) ||
                color.equals(BGradients.bggradient.toString())) {
            for (TextView tv : textViews) {
                tv.setTextColor(Color.WHITE);
            }
        }
        if (color.equals(BGradients.white.toString())) {
            for (TextView tv:textViews) {
                tv.setTextColor(Color.BLACK);
            }

        }
        if (color.equals(BGradients.bllue.toString())) {
            for (TextView tv:textViews) {
                tv.setTextColor(Color.parseColor("#063462"));
            }
        }
        if (color.equals(BGradients.brown.toString())) {
            for (TextView tv:textViews) {
                tv.setTextColor(Color.parseColor("#4D4B43"));
            }
        }
        if (color.equals(BGradients.darkbrown.toString())) {
            for (TextView tv:textViews) {
                tv.setTextColor(Color.parseColor("#A9E44D"));
            }
        }
    }
    public static void bgForSwitches (String color, List<Switch> switches) {
        if (color.equals(BGradients.bg_gradient_yellow_pink.toString()) || color.equals(BGradients.bg_gradient_red_blue.toString()) ||
                color.equals(BGradients.bg_gradient_yellow_red.toString()) || color.equals(BGradients.bg_gradient_green_blue.toString()) ||
                color.equals(BGradients.bggradient.toString())) {
            for ( Switch  sw : switches) {
                sw.setTextColor(Color.WHITE);
            }
        }
        if (color.equals(BGradients.white.toString())) {
            for (Switch sw : switches) {
                sw.setTextColor(Color.BLACK);
            }

        }
        if (color.equals(BGradients.bllue.toString())) {
            for (Switch sw : switches) {
                sw.setTextColor(Color.parseColor("#063462"));
            }
        }
        if (color.equals(BGradients.brown.toString())) {
            for (Switch sw : switches) {
                sw.setTextColor(Color.parseColor("#4D4B43"));
            }
        }
        if (color.equals(BGradients.darkbrown.toString())) {
            for (Switch sw : switches) {
                sw.setTextColor(Color.parseColor("#A9E44D"));
            }
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
    public static void setCheckedExtendedSwitch(String bgColor, Switch whiteSwitch,Switch blueSwitch,Switch brownSwitch,Switch dkbrownSwitch){

        if (bgColor.equals(BGradients.white.toString())) {
            whiteSwitch.setChecked(true);
        }
        if (bgColor.equals(BGradients.bllue.toString())) {
            blueSwitch.setChecked(true);
        }
        if (bgColor.equals(BGradients.brown.toString())) {
            brownSwitch.setChecked(true);
        }
        if (bgColor.equals(BGradients.darkbrown.toString())) {
            dkbrownSwitch.setChecked(true);
        }
    }
    public static void ChangeScrollViewBG(String color, ScrollView scrollView){

        if (color.equals(BGradients.white.toString())) {
            scrollView.setBackgroundResource(R.drawable.white);
        }
        if (color.equals(BGradients.bllue.toString())) {
            scrollView.setBackgroundResource(R.drawable.blue_);
        }
        if (color.equals(BGradients.brown.toString())) {
            scrollView.setBackgroundResource(R.drawable.brown_);
        }
        if (color.equals(BGradients.darkbrown.toString())) {
            scrollView.setBackgroundResource(R.drawable.dkbrown_);
        }
    }
    public static Boolean ChangeView(String color){
        boolean flag = false;
        if (color.equals(BGradients.bg_gradient_yellow_pink.toString()) || color.equals(BGradients.bg_gradient_red_blue.toString()) ||
                color.equals(BGradients.bg_gradient_yellow_red.toString()) || color.equals(BGradients.bg_gradient_green_blue.toString()) ||
                color.equals(BGradients.bggradient.toString())) {
            flag = true;
        }
        return flag;
    }
    }
