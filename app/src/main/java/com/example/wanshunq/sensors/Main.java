package com.example.wanshunq.sensors;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.List;

public class Main extends AppCompatActivity {

    private SensorManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manager=(SensorManager)getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> sensorList=manager.getSensorList(Sensor.TYPE_ALL);

        ListView sensors=(ListView)findViewById(R.id.sensor_list);
        sensorAdapoer adapoer=new sensorAdapoer(this,sensorList);
        sensors.setAdapter(adapoer);
    }
}
