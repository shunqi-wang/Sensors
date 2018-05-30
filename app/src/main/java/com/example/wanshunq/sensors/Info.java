package com.example.wanshunq.sensors;

import android.content.Context;
import android.content.res.AssetManager;
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

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
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


        JSONObject names=null;
        try{
            AssetManager assetManager=getAssets();
            InputStream is=assetManager.open("Test.json");
            String content="";
            InputStreamReader ir=new InputStreamReader(is);
            BufferedReader br=new BufferedReader(ir);
            String st;
            while ((st=br.readLine())!=null){
                content+=st;
            }
            System.out.println(content);
            names=new JSONObject(content);

        }catch (Exception e){
            e.printStackTrace();
        }

        final JSONObject final_names=names;

        SensorManager manager=(SensorManager)getSystemService(Context.SENSOR_SERVICE);
        final Sensor sensor=manager.getSensorList(intType).get(0);

        SensorEventListener listener=new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                if(event.sensor.equals(sensor)){
                    JSONArray array=new JSONArray();
                    try{
                        JSONArray nameArray=(JSONArray)final_names.get(stringType);
                        for(int i:ia){
                            array.put(new JSONObject().put(
                                    (String)nameArray.get(i),event.values[i]
                            ));
                        }
                        JSONObject object=new JSONObject();

                        object.put(stringType,array);
                        first.setText(object.toString());
                    }catch (JSONException e){
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {}
        };

        manager.registerListener(listener,sensor,333333);
    }
}
