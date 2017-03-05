package com.zgh.android.colorballtest;

import android.app.Notification;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by Moonkey on 2017/3/5.
 */

public class BallView extends View{
    private List<Ball> balls = new ArrayList<Ball>();
    private List<GravityBall> gravityBalls = new ArrayList<GravityBall>();
    Ball ball;

    private void init() {
        Rect frame = new Rect(getLeft(), getTop(), getRight(), getBottom());
        ball = new Ball(frame);
        balls.add(ball);

//        GravityBall gravityBall = new GravityBall(frame);
//        this.gravityBalls.add(gravityBall);

        new Thread(new ThreadTime()).start();
    }

    public void addBall() {
        Rect frame = new Rect(getLeft(), getTop(), getRight(), getBottom());
        Ball ball = new Ball(frame);
        balls.add(ball);
        Log.d(TAG, "addBall: Success!");
    }

    public BallView(Context context)
    {
        super(context);

        this.init();
    }

    public BallView(Context context, AttributeSet attrs)
    {
        super(context, attrs);

        this.init();
    }

    public BallView(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);

        this.init();
    }

    Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            if (msg.what==1)
            {
                Rect frame = new Rect(getLeft(),getTop(),getRight(),getBottom());
                for (Ball ball:balls)
                    ball.changeFrame(frame);
                for (GravityBall gravityBall:gravityBalls)
                    gravityBall.changeFrame(frame);

                invalidate();
            }
        }
    };
    class ThreadTime implements Runnable{

        @Override
        public void run() {
            while(true) {
                try {
                    Thread.sleep(10);

                    Message msg = new Message();
                    msg.what = 1;
                    handler.sendMessage(msg);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    public void changeColor(int color) {
        for (Ball ball:this.balls)
            ball.changeColor(color);
    }
    public void changeSpeed(int speed) {
        for (Ball ball:this.balls)
            ball.changeSpeed(speed);
    }

    public static void touchJudge(Ball ballA, Ball ballB) {
        int xDistance = ballA.getPosition().x - ballB.getPosition().x;
        int yDistance = ballA.getPosition().y - ballB.getPosition().y;
        float rDistance = ballA.getRadius() + ballB.getRadius();

        Speed oldA = ballA.getSpeed();
        Speed oldB = ballB.getSpeed();

        float sinAngle = xDistance / rDistance;
        float cosAngle = yDistance / rDistance;
        Speed unitSpeed = new Speed(cosAngle, sinAngle);
        if( Math.abs(xDistance) <= Math.pow(10, -3) && Math.abs(yDistance) <= Math.pow(10, -3))
            return;
        if( xDistance*xDistance + yDistance*yDistance
                            <= rDistance*rDistance) {
            Speed newA, newB;
            newA = Speed.addSpeed(oldA,
                    Speed.mutiplySpeed(Speed.mutiplySpeed(Speed.relativeSpeed(oldB,oldA), unitSpeed),unitSpeed));
            newB = Speed.addSpeed(oldB,
                    Speed.mutiplySpeed(Speed.mutiplySpeed(Speed.relativeSpeed(oldA,oldB), unitSpeed),unitSpeed));

            ballA.changeSpeed(newA);
            ballB.changeSpeed(newB);
        }
    }

    public void createGravity(float x, float y){
        Rect frame = new Rect(getLeft(), getTop(), getRight(), getBottom());
        GravityBall gravityBall = new GravityBall(frame);
        gravityBall.changePosition(x, y);
        this.gravityBalls.add(gravityBall);
    }
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);
        for (Ball ballA:this.balls) {
            for (Ball ballB:this.balls){
                touchJudge(ballA, ballB);
            }
        }
        for (GravityBall gravityBall:this.gravityBalls ){
            for (Ball ball:this.balls) {
                gravityBall.usePower(ball);
            }
        }
        for (Ball ball:this.balls)
            ball.draw(canvas);
        for (GravityBall gravityBall:this.gravityBalls)
            gravityBall.draw(canvas);
    }
}
