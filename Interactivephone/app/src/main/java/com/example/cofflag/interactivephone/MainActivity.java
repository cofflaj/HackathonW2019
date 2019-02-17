package com.example.cofflag.interactivephone;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    public static boolean spinningFast = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SensorManager mansens = (SensorManager)this.getSystemService(SENSOR_SERVICE);
        Sensor sens = mansens.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        Sensor spinsens = mansens.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        Context c = getApplicationContext();
        AccelerationListener l = new AccelerationListener(c);
        SpinListener s = new SpinListener();
        mansens.registerListener(l,sens,1);
        mansens.registerListener(s,spinsens,1);
    }
}
