package com.zgh.android.colorballtest;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;

/**
 * Created by Moonkey on 2017/3/5.
 */

public class BallView extends View{

    public BallView(Context context) {
        super(context);
    }

    protected void onDraw(Canvas canvas) {
        canvas.drawLine();
    }
}
