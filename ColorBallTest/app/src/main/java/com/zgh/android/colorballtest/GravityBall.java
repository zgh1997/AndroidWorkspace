package com.zgh.android.colorballtest;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;

import static android.content.ContentValues.TAG;

/**
 * Created by Moonkey on 2017/3/5.
 */

public class GravityBall extends Ball {
    private int Gravity = 3000;


    public GravityBall(Rect frame) {
        super(frame);
        this.position = new Point(200, 200);
    }

    @Override
    public void changeColor(int color) {}

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(this.position.x, this.position.y, 10, paint);
    }

    private void changePosition(Point p){
        this.position = p;
    }
    public void changePosition(float x, float y){
        Point p = new Point((int)x, (int)y);
        this.changePosition(p);
    }

    public void usePower(Ball p) {
        int xDistance = this.getPosition().x - p.getPosition().x;
        int yDistance = this.getPosition().y - p.getPosition().y;
        double length = Math.sqrt(xDistance*xDistance + yDistance*yDistance);
        double sinAngle = yDistance / length;
        double cosAngle = xDistance / length;

        Speed powSpeed = new Speed(0, 0);
        if(this.Gravity * cosAngle / length < Math.pow(10, 5))
            powSpeed = new Speed((float) (this.Gravity * cosAngle / (length*length)), (float) (this.Gravity * sinAngle / (length*length)));
        p.changeAccelerateSpeed(powSpeed);
    }
}
