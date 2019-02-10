package com.example.myapplication.views;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import com.example.myapplication.objects.StartButton;

public class MainView extends View {
    StartButton startButton;
    public MainView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        startButton = new StartButton();
        startButton.draw(canvas);
    }
}
