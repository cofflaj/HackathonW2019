package com.example.cofflag.interactivephone;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

/**
 * Created by cofflag on 2/16/19.
 */

public class AccelerationListener implements SensorEventListener{
    private MediaPlayer mediaplayer;
    private MainActivity ma;
    private boolean screaming;
    private Context con;

    public AccelerationListener(Context c, MainActivity a){
        mediaplayer = MediaPlayer.create(c, R.raw.r2d2_scream);
        mediaplayer.setLooping(true);
        con = c;
        screaming = false;
        ma = a;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];
        long sTime = 0;
        double f = Math.sqrt(x*x+y*y+z*z);
        Log.d("#jacob","X: "+x+" Y: "+y+" Z: "+z + " ||V||: " + f);
        boolean falling = (-1 < f && f < 1);
        boolean scared = falling || MainActivity.spinningFast;
        if(mediaplayer == null) {
            mediaplayer = MediaPlayer.create(con, R.raw.r2d2_scream);
            mediaplayer.setLooping(true);
        }
        boolean playing = mediaplayer.isPlaying();
        boolean atZero = mediaplayer.getCurrentPosition() == 0;
        if(scared && !playing) {
            mediaplayer.start();
            sTime = System.currentTimeMillis();
        }
        else if(!scared && playing) {
            mediaplayer.stop();
            mediaplayer.release();
            mediaplayer = null;
            //long eTime = System.currentTimeMillis();
            //long timeElapsed = eTime - sTime;
            //TextView dropText = (TextView) ma.findViewById(R.id.drop_text);
            //String fillDropText = String.format("I was scared for %.1f seconds :(", timeElapsed);
            //dropText.setText(fillDropText);
        }

    }
}

/*if(playing) {
            if(falling) {
                // good job!
            }
            else {
                if(atZero) {
                    mediaplayer.pause();
                }
                else {
                    mediaplayer.pause();
                    mediaplayer.seekTo(0);
                }
            }
        }
        else { // not playing
            if(falling){
                if(atZero){
                    mediaplayer.start();
                }
                else {
                    // Hopefully this never happens
                    Log.d("log", "...shit");
                }
            } else {
                if(atZero) {
                    // good job!
                } else {
                    mediaplayer.seekTo(0);
                }

            }
        }*/