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
    private Context con;

    public AccelerationListener(Context c){
        mediaplayer = MediaPlayer.create(c, R.raw.r2d2_scream);
        con = c;
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
        if(mediaplayer == null) {
            mediaplayer = MediaPlayer.create(con, R.raw.r2d2_scream);
        }
        if(falling && !playing) {
            mediaplayer.start();
        }
        else if(!falling && playing) {
            mediaplayer.stop();
            mediaplayer.release();
            mediaplayer = null;
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