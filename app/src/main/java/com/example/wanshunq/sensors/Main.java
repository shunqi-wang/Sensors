package com.example.wanshunq.sensors;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Main extends AppCompatActivity implements View.OnClickListener{

    private SensorManager manager;
    private sensorAdapter adapoer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manager=(SensorManager)getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> sensorList=manager.getSensorList(Sensor.TYPE_ALL);

        ListView sensors=(ListView)findViewById(R.id.sensor_list);
        adapoer=new sensorAdapter(this,sensorList,manager);
        sensors.setAdapter(adapoer);

        //Button Send=(Button)findViewById(R.id.send);
        //Send.setOnClickListener(this);

        //Button Clear=(Button)findViewById(R.id.clear);
//        Clear.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                adapoer.infoReset();
//                recreate();
//            }
//        });
    }

    @Override
    public void onClick(View v) {
        ArrayList<sensorAdapter.info_holder> infos=adapoer.getInfos();
        ArrayList<Sensor> sensors=adapoer.getSensors();
        JSONObject toSend=new JSONObject();

        for(int i=0;i<infos.size();i++){
            if(infos.get(i).ContainsInfo()){
                JSONObject tmp=new JSONObject();
                ArrayList<Integer> ids=infos.get(i).getInfoId();
                Sensor tmp_sensor=sensors.get(i);
                try{
                    for(int id:ids){
                        tmp.put(getInfoName(id),getInfoWithID(id,tmp_sensor));
                    }
                    toSend.put(tmp_sensor.getName(),tmp);
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }

        Intent intent=new Intent(this, Info.class);
        intent.putExtra("info",toSend.toString());
        startActivity(intent);
    }

    public String getInfoName(int id){
        String name="";
        switch (id){
            case 0: name="Vender";
                break;
            case 1: name="Version";
                break;
            case 2: name="Integer Type";
                break;
            case 3: name="String Type";
                break;
            case 4: name="Max Range";
                break;
            case 5: name="Resolution";
                break;
            case 6: name="Power";
                break;
            case 7: name="Min Delay";
                break;
        }
        return name;
    }

    public String getInfoWithID(int id,Sensor sensor){
        String info="";
        switch (id){
            case 0: info=sensor.getVendor();
                    break;
            case 1: info=sensor.getVersion()+"";
                    break;
            case 2: info=sensor.getType()+"";
                    break;
            case 3: info=sensorAdapter.sensorTypeToString(sensor.getType());
                    break;
            case 4: info=sensor.getMaximumRange()+"";
                    break;
            case 5: info=sensor.getResolution()+"";
                    break;
            case 6: info=sensor.getPower()+"";
                    break;
            case 7: info=sensor.getMinDelay()+"";
                    break;
        }
        return info;
    }
}
