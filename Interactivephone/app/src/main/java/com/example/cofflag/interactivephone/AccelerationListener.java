package com.example.cofflag.interactivephone;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.View;

import java.util.concurrent.TimeUnit;

/**
 * Created by cofflag on 2/16/19.
 */

public class AccelerationListener implements SensorEventListener{
    private MediaPlayer mediaplayer;
    private boolean screaming;

    public AccelerationListener(Context c){
        mediaplayer = MediaPlayer.create(c, R.raw.r2d2_scream);
        screaming = false;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];
        double f = Math.sqrt(x*x+y*y+z*z);
        Log.d("#jacob","X: "+x+" Y: "+y+" Z: "+z + " ||V||: " + f);
        boolean falling = (-1 < f && f < 1);
        boolean playing = mediaplayer.isPlaying();
        boolean atZero = mediaplayer.getCurrentPosition() == 0;
        if(playing) {
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
        }

    }
}
/*if(f < 1 && f > -1){
        if(!screaming) {
        mediaplayer.start();
        screaming = true;
        try {
        TimeUnit.SECONDS.sleep(1);
        } catch (Exception e) {

        }
        Log.d("SCREAM", "SCREAM");
        }
        }
        else if (screaming) {
        if(mediaplayer.isPlaying()){
        mediaplayer.pause();
        }
        mediaplayer.seekTo(0);
        screaming = false;
        }*/