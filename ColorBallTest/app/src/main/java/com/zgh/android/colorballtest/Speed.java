package com.zgh.android.colorballtest;

/**
 * Created by Moonkey on 2017/3/5.
 */

public class Speed {
    public float xSpeed;
    public float ySpeed;
    public Speed(float xSpeed, float ySpeed) {
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
    }
    public static Speed relativeSpeed(Speed A, Speed B) {
        Speed newSpeed = new Speed(A.xSpeed - B.xSpeed, A.ySpeed - B.ySpeed);
        return newSpeed;
    }

    public static Speed addSpeed(Speed A, Speed B) {
        Speed newSpeed = new Speed(A.xSpeed + B.xSpeed, A.ySpeed + B.ySpeed);
        return newSpeed;
    }

    public static float mutiplySpeed(Speed A, Speed B) {
        return A.xSpeed*B.xSpeed + A.ySpeed*B.ySpeed;
    }
    public static Speed mutiplySpeed(float A, Speed B) {
        Speed newSpeed = new Speed(A * B.xSpeed, A * B.ySpeed);
        return newSpeed;
    }
}
