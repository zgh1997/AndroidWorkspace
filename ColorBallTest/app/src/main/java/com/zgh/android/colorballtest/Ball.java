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

public class Ball {
    protected Point position;
    private float radius;
    private Speed speed = new Speed(0, 3);
    private Speed accelerateSpeed = new Speed(0, 0);
    private int color = Color.BLACK;
    private Rect frame;

    public Ball(Rect frame) {
        this.position = new Point(500, 500);
        this.radius = 30;
        this.frame = frame;
        this.color = Color.BLACK;
    }

    public Point getPosition() {
        return this.position;
    }
    public float getRadius() {
        return this.radius;
    }
    public Speed getSpeed() {
        return this.speed;
    }

    private void run() {
        this.speed = Speed.addSpeed(this.speed, this.accelerateSpeed);

        this.position.x += (int) this.speed.xSpeed;
        this.position.y += (int) this.speed.ySpeed;

        if (this.position.x - this.radius<=this.frame.left ||
                this.position.x + this.radius > this.frame.right) {
            this.speed.xSpeed = -this.speed.xSpeed;

            if(this.position.x - this.radius<=this.frame.left )
                this.position.x = this.frame.left + Math.abs(this.frame.left - this.position.x) + (int)this.radius;
            if(this.position.x + this.radius > this.frame.right)
                this.position.x = this.frame.right - Math.abs(this.frame.right - this.position.x) - (int)this.radius;
        }
        if (this.position.y - this.radius < this.frame.top ||
                this.position.y + this.radius > this.frame.bottom)
        {
            this.speed.ySpeed = - this.speed.ySpeed;

            if(this.position.y - this.radius<=this.frame.top )
                this.position.y = this.frame.top+ Math.abs(this.frame.top - this.position.y) + (int)this.radius;
            if(this.position.y + this.radius > this.frame.bottom)
                this.position.y = this.frame.bottom - Math.abs(this.frame.bottom - this.position.y) - (int)this.radius;
        }
    }

    public void changeFrame(Rect frame) {
        this.frame = frame;
    }
    public void changeColor(int color) {
        this.color = color;
    }

    public void changeSpeed(float xSpeed, float ySpeed) {
        this.speed.xSpeed = xSpeed;
        this.speed.ySpeed = ySpeed;
    }
    public void changeSpeed(Speed newSpeed) {
        this.speed = newSpeed;
    }
    public void changeSpeed(int rate) {
        this.speed.xSpeed = rate * (this.speed.xSpeed/(this.speed.xSpeed*this.speed.xSpeed + this.speed.ySpeed*this.speed.ySpeed));
        this.speed.ySpeed = rate * (this.speed.ySpeed/(this.speed.xSpeed*this.speed.xSpeed + this.speed.ySpeed*this.speed.ySpeed));
    }


    public void changeAccelerateSpeed(Speed accelerateSpeed) {
        this.accelerateSpeed = accelerateSpeed;
    }

    public void draw(Canvas canvas) {
        this.run();

        Paint paint = new Paint();
        paint.setColor(this.color);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(this.position.x, this.position.y, this.radius, paint);
    }
}
