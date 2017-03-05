package com.zgh.android.colorballtest;

import android.graphics.Color;
import android.net.Uri;
import android.support.annotation.StyleRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.view.MotionEvent;
import android.view.View.OnTouchListener;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity
        implements SeekBar.OnSeekBarChangeListener {


    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    private TextView btColorAdd;
    private TextView txtRed, txtGreen, txtBlue;
    private SeekBar skRed, skGreen, skBlue, skSpeed;
    private BallView ballView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

        this.init();
    }

    private void init() {
        this.btColorAdd =(Button)this.findViewById(R.id.btColorAdd);
        this.btColorAdd.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                ballView.addBall();
            }
        });

        this.txtRed =(TextView)this.findViewById(R.id.txtRed);
        this.txtGreen =(TextView)this.findViewById(R.id.txtGreen);
        this.txtBlue =(TextView)this.findViewById(R.id.txtBlue);

        this.skRed = (SeekBar)this.findViewById(R.id.skRed);
        this.skGreen = (SeekBar)this.findViewById(R.id.skGreen);
        this.skBlue = (SeekBar)this.findViewById(R.id.skBlue);
        this.skSpeed = (SeekBar) this.findViewById(R.id.skSpeed);

        this.skRed.setOnSeekBarChangeListener(this);
        this.skGreen.setOnSeekBarChangeListener(this);
        this.skBlue.setOnSeekBarChangeListener(this);
        this.skSpeed.setOnSeekBarChangeListener(this);

        this.ballView = (BallView)this.findViewById(R.id.ballView);
        this.ballView.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                float x = event.getX();
                float y = event.getY();
                MainActivity.this.ballView.createGravity(x, y);
                return true;
            }
        });
    }

    @Override
    public void setTheme(@StyleRes int resid) {
        super.setTheme(resid);
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        int red = this.skRed.getProgress();
        int green = this.skGreen.getProgress();
        int blue = this.skBlue.getProgress();
        int speed = this.skSpeed.getProgress();

        this.txtRed.setText(String.valueOf(red));
        this.txtGreen.setText(String.valueOf(green));
        this.txtBlue.setText(String.valueOf(blue));

        int color = Color.argb(255, red, green, blue);

        btColorAdd.setBackgroundColor(color);
        ballView.changeColor(color);
        ballView.changeSpeed(speed);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

}
