package com.example.cofflag.interactivephone;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SensorManager mansens = (SensorManager)this.getSystemService(SENSOR_SERVICE);
        Sensor sens = mansens.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        Context c = getApplicationContext();
        AccelerationListener l = new AccelerationListener(c);
        mansens.registerListener(l,sens,1);
    }
}
