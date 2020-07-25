package com.devnegreiro.sample_gyroscope;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    ViewHolder mViewHolder = new ViewHolder();
    SensorManager mSensorManager;
    Sensor mGyroscope;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mGyroscope = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);


        this.mViewHolder.text_x = findViewById(R.id.Text_x);
        this.mViewHolder.text_y = findViewById(R.id.Text_y);
        this.mViewHolder.text_z = findViewById(R.id.Text_z);

    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this,mGyroscope,SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mSensorManager.unregisterListener(this);
    }


    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        float x = sensorEvent.values[0];
        float y = sensorEvent.values[1];
        float z = sensorEvent.values[2];

        BigDecimal bd_x = new BigDecimal(x).setScale(3, RoundingMode.HALF_EVEN);
        BigDecimal bd_y = new BigDecimal(y).setScale(3, RoundingMode.HALF_EVEN);
        BigDecimal bd_z = new BigDecimal(z).setScale(3, RoundingMode.HALF_EVEN);

        this.mViewHolder.text_x.setText("X:" +  bd_x + "rad/s");
        this.mViewHolder.text_y.setText("Y:" +  bd_y + "rad/s");
        this.mViewHolder.text_z.setText("Z:" +  bd_z + "rad/s");


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }



    public static class ViewHolder{
        TextView text_x, text_y, text_z;

    }
}