package com.example.myapplication.objects;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;




public class StartButton implements IDrawable {
    private Paint paint;
    private int x;
    private int y;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public StartButton() {
       paint = new Paint(Paint.ANTI_ALIAS_FLAG);
       paint.setColor(Color.RED);
    }

    @Override
    public void draw(Canvas canvas) {
        int canvasHeight,canvasWidth;
        canvasWidth = canvas.getWidth();
        canvasHeight = canvas.getHeight();
        x = canvasWidth/2;
        y = canvasHeight/2;

        canvas.drawRect(x-100,y-100,x+100,y+100,paint);
    }
}
