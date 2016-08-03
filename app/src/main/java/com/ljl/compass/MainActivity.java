package com.ljl.compass;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private ImageView iv;
    private SensorManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv = (ImageView) findViewById(R.id.iv_zn);
        manager = (SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor sensor = manager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        manager.registerListener(new SensorEventListener() {
            float lastAglet= 0;
            float angle = 0;
            @Override
            public void onSensorChanged(SensorEvent event) {
                // TODO Auto-generated method stub
                angle = event.values[0];
                RotateAnimation ra = new RotateAnimation(lastAglet, -angle, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                lastAglet = -angle;
                iv.startAnimation(ra);
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
                // TODO Auto-generated method stub

            }
        }, sensor, SensorManager.SENSOR_DELAY_GAME );
    }

}
