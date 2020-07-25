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

    //new ViewHolder instance
    ViewHolder mViewHolder = new ViewHolder();

    //Variable to control the sensor
    SensorManager mSensorManager;
    Sensor mGyroscope;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get an instance of the default gyroscope
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mGyroscope = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        //Finds a view with the given ID,  automatically cast
        this.mViewHolder.text_x = findViewById(R.id.Text_x);
        this.mViewHolder.text_y = findViewById(R.id.Text_y);
        this.mViewHolder.text_z = findViewById(R.id.Text_z);

    }

    @Override
    protected void onResume() {
        super.onResume();
        //Enable Gyroscope sensor
        mSensorManager.registerListener(this,mGyroscope,SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mSensorManager.unregisterListener(this);
    }

    //onSensorChanged is abstract method of SensorEventListener
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        //Axis of the rotation sample
        float x = sensorEvent.values[0];
        float y = sensorEvent.values[1];
        float z = sensorEvent.values[2];

        //Float to decimal with a new scale
        BigDecimal bd_x = new BigDecimal(x).setScale(3, RoundingMode.HALF_EVEN);
        BigDecimal bd_y = new BigDecimal(y).setScale(3, RoundingMode.HALF_EVEN);
        BigDecimal bd_z = new BigDecimal(z).setScale(3, RoundingMode.HALF_EVEN);

        //print in the screen each sample x,y and z.
        this.mViewHolder.text_x.setText("X:" +  bd_x + "rad/s");
        this.mViewHolder.text_y.setText("Y:" +  bd_y + "rad/s");
        this.mViewHolder.text_z.setText("Z:" +  bd_z + "rad/s");


    }
    //onAccuracyChanged is abstract method of SensorEventListener
    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }


    public static class ViewHolder{
        TextView text_x, text_y, text_z;

    }
}