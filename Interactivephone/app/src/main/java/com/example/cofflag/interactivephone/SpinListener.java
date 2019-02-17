package com.example.cofflag.interactivephone;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

/**
 * Created by cofflaj on 2/16/19.
 */

public class SpinListener implements SensorEventListener {

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];
        double f = Math.sqrt(x*x+y*y+z*z);
        boolean spinningFastEnough = f > 8;
        MainActivity.spinningFast = spinningFastEnough;
    }

}
