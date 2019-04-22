package com.example.myapplication.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import com.example.myapplication.R;
import com.example.myapplication.objects.StartButton;

public class SecondActivityView extends View {
 Bitmap planetBM;
 StartButton startButton;
 private int x;
 private int y;
    public SecondActivityView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //startButton = new StartButton();
        //startButton.draw(canvas);
       // x=0; y=0;
       // planetBM = BitmapFactory.decodeResource(getResources(), R.drawable.planet);
       // canvas.drawBitmap(planetBM,x,y,null);

    }
}
