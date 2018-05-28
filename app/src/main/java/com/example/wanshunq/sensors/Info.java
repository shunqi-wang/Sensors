package com.example.wanshunq.sensors;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

public class Info extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        final TextView first=(TextView)findViewById(R.id.sent_info);

        Bundle extras=getIntent().getExtras();
        int intType=extras.getInt("intType");
        final String stringType=extras.getString("stringType");
        final int[] ia=extras.getIntArray("valueId");

        SensorManager manager=(SensorManager)getSystemService(Context.SENSOR_SERVICE);
        final Sensor sensor=manager.getSensorList(intType).get(0);

        SensorEventListener listener=new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                if(event.sensor.equals(sensor)){
                    JSONArray array=new JSONArray();
                    for(int i:ia){
                        array.put(event.values[i]+"");
                    }
                    JSONObject object=new JSONObject();
                    try{
                        object.put(stringType,array);
                    }catch (JSONException e){
                        e.printStackTrace();
                    }
                    first.setText(object.toString());
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {}
        };

        manager.registerListener(listener,sensor,333333);
    }
}
