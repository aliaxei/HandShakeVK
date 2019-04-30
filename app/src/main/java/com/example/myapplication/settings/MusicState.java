package com.example.myapplication.settings;

import android.content.Context;
import android.media.MediaPlayer;

import com.example.myapplication.R;

public class MusicState {
    public  static MediaPlayer  setPlayer(String musicState, MediaPlayer mp, Context context){
        if (musicState.equals(ToggleState.On.toString())){
            mp = MediaPlayer.create(context,R.raw.light_tap);
        }else {
            mp.reset();
        }

     return mp;
    }
    public static void startPlayer(String toggleButtonState,MediaPlayer mediaPlayer){

       if (toggleButtonState.equals(ToggleState.On.toString())){
           mediaPlayer.start();
       }
    }
}
