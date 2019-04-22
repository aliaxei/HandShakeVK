package com.example.myapplication.settings;

import android.widget.Spinner;

public class SelectionSetter {

    public static void SetSelection(String font, Spinner spinner){
        if (font.equals(Fonts.Arial.toString())) {
            spinner.setSelection(0);
        }
        if (font.equals(Fonts.Superfont.toString())) {
            spinner.setSelection(1);
        }
        if (font.equals(Fonts.Basic.toString())) {
            spinner.setSelection(2);
        }
    }


}
