package com.example.myapplication;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.widget.TextView;

import com.example.myapplication.settings.Fonts;

import java.util.List;

public class FontChanger {

  public static void  changeTheFont(String fontFromFile,List<TextView> textViews, Context context){

            AssetManager mngr = context.getAssets();
            Typeface font = Typeface.createFromAsset(mngr, "fonts/"+fontFromFile+".ttf");
            setFont(textViews,font);


  }
  public static void setFont(List<TextView> textViews,Typeface font){
      for (TextView tv: textViews) {
          tv.setTypeface(font);
      }
  }
}
