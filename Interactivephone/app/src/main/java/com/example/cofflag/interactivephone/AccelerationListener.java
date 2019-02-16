package com.example.cofflag.interactivephone;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.View;

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
        if(f < 1 && f > -1){
            if(!screaming) {
                mediaplayer.start();
                screaming = true;
                Log.d("SCREAM", "SCREAM");
            }
        }
        if(f > 1 || f < -1){
            mediaplayer.stop();
            screaming = false;
            try {
                mediaplayer.prepare();
            } catch (Exception e) {
                //do nothing
            }
        }
    }
}
