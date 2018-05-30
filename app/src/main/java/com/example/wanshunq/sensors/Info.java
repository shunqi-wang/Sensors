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
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Info extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        final LinearLayout linearLayout=(LinearLayout)findViewById(R.id.sent_info);

        Bundle extras=getIntent().getExtras();
        final int[] ia=extras.getIntArray("select");
        for(int i:ia){
            TextView textView=new TextView(this);
            textView.setLayoutParams(new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT
            ));
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);
            linearLayout.addView(textView);
        }

        SensorManager manager=(SensorManager)getSystemService(Context.SENSOR_SERVICE);
        final ArrayList<Sensor> sensors=new ArrayList<>(manager.getSensorList(Sensor.TYPE_ALL));
        final ArrayList<Sensor> selected=new ArrayList<>();
        final ArrayList<String> sensorNames=new ArrayList<>();

        for(int i:ia){
            Sensor sensor=sensors.get(i);
            selected.add(sensor);
            sensorNames.add(sensorAdapter.sensorTypeToString(sensor.getType()));
        }

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

        for(int j=0;j<ia.length;j++){
            final int i=j;
            SensorEventListener listener=new SensorEventListener() {
                @Override
                public void onSensorChanged(SensorEvent event) {
                    if(event.sensor.equals(selected.get(i))){
                        JSONArray array=new JSONArray();
                        try{
                            JSONArray nameArray=(JSONArray)final_names.get(sensorNames.get(i));
                            Log.i("shunqi",nameArray.toString());
                            Log.i("shunqi",Arrays.toString(event.values));
                            for(int x=0;x<event.values.length;x++){
                                if(nameArray==null){
                                    array.put(new JSONObject().put(
                                            "Unknown",event.values[x]
                                    ));
                                }else{
                                    array.put(new JSONObject().put(
                                            (String)nameArray.get(x),event.values[x]
                                    ));
                                }
                            }
                            JSONObject object=new JSONObject();

                            object.put(sensorNames.get(i),array);
                            TextView text=(TextView)linearLayout.getChildAt(i);
                            text.setText(object.toString());
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onAccuracyChanged(Sensor sensor, int accuracy) {}
            };

            manager.registerListener(listener,selected.get(i),333333);
        }

        for(int i=0;i<linearLayout.getChildCount();i++){
            TextView textView=(TextView)linearLayout.getChildAt(i);
            if(textView.getText().equals("")){
                textView.setText("No Data");
            }
        }
    }
}
