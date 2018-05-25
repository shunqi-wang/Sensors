package com.example.wanshunq.sensors;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by wsq96 on 2018/5/15.
 */

public class sensorAdapter extends BaseAdapter{
    private Context context;
    private ArrayList<Sensor> sensors;
    private ArrayList<info_holder> infos=new ArrayList<>();
    private SensorManager manager;
    private ArrayList<Integer> numbers=new ArrayList<>();



    public sensorAdapter(Context context,List<Sensor> sensors,SensorManager manager){
        this.context=context;
        this.sensors=new ArrayList<>(sensors);
        for(int i=0;i<sensors.size();i++){
            numbers.add(0);
        }
        this.manager=manager;
    }

    @Override
    public int getCount() {
        return sensors.size();
    }

    @Override
    public Object getItem(int i) {
        return sensors.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View convertView, ViewGroup viewGroup) {
        View view=convertView;
        if(view==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.one_sensor, null);
        }

        final Sensor sensor=sensors.get(i);

        TextView name=(TextView)view.findViewById(R.id.name);
        name.setText(sensor.getName());

        TextView type=(TextView)view.findViewById(R.id.type);
        final String sensor_type=sensorTypeToString(sensor.getType());
        type.setText(sensor_type);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,Value.class);
                intent.putExtra("intType",sensor.getType());
                intent.putExtra("stringType",sensor_type);
                context.startActivity(intent);
            }
        });

        return view;
    }

    void setBackground(Boolean set,RelativeLayout layout){
        if(set){
            layout.setBackgroundColor(Color.parseColor("#BAE1FC"));
        }else {
            layout.setBackgroundColor(Color.TRANSPARENT);
        }
    }


    public static String sensorTypeToString(int type) {
        switch (type) {
            case Sensor.TYPE_ACCELEROMETER:
                return "Accelerometer";
            case Sensor.TYPE_AMBIENT_TEMPERATURE:
                return "Ambient Temperature";
            case Sensor.TYPE_GAME_ROTATION_VECTOR:
                return "Game Rotation Vector";
            case Sensor.TYPE_GEOMAGNETIC_ROTATION_VECTOR:
                return "Geomagnetic Rotation Vector";
            case Sensor.TYPE_GRAVITY:
                return "Gravity";
            case Sensor.TYPE_GYROSCOPE:
                return "Gyroscope";
            case Sensor.TYPE_GYROSCOPE_UNCALIBRATED:
                return "Gyroscope Uncalibrated";
            case Sensor.TYPE_HEART_RATE:
                return "Heart Rate Monitor";
            case Sensor.TYPE_LIGHT:
                return "Light";
            case Sensor.TYPE_LINEAR_ACCELERATION:
                return "Linear Acceleration";
            case Sensor.TYPE_MAGNETIC_FIELD:
                return "Magnetic Field";
            case Sensor.TYPE_MAGNETIC_FIELD_UNCALIBRATED:
                return "Magnetic Field Uncalibrated";
            case Sensor.TYPE_ORIENTATION:
                return "Orientation";
            case Sensor.TYPE_PRESSURE:
                return "Pressure";
            case Sensor.TYPE_PROXIMITY:
                return "Proximity";
            case Sensor.TYPE_RELATIVE_HUMIDITY:
                return "Relative Humidity";
            case Sensor.TYPE_ROTATION_VECTOR:
                return "Rotation Vector";
            case Sensor.TYPE_SIGNIFICANT_MOTION:
                return "Significant Motion";
            case Sensor.TYPE_STEP_COUNTER:
                return "Step Counter";
            case Sensor.TYPE_STEP_DETECTOR:
                return "Step Detector";
            case Sensor.TYPE_ACCELEROMETER_UNCALIBRATED:
                return "Accelerometer Uncalibrated";
            case Sensor.TYPE_DEVICE_PRIVATE_BASE:
                return "Device Private Base";
            case Sensor.TYPE_HEART_BEAT:
                return "Heart Beat Monitor";
            case Sensor.TYPE_LOW_LATENCY_OFFBODY_DETECT:
                return "Low Latency Offbody Detect";
            case Sensor.TYPE_MOTION_DETECT:
                return "Motion Detect";
            case Sensor.TYPE_POSE_6DOF:
                return "6-DoF Pose Detect";
            case Sensor.TYPE_STATIONARY_DETECT:
                return "Stationary Detect";
            default:
                return "Unknown";
        }
    }

    public ArrayList<info_holder> getInfos(){
        return new ArrayList<>(infos);
    }

    public ArrayList<Sensor> getSensors() {
        return new ArrayList<>(sensors);
    }

    public void infoReset(){
        for(int i=0;i<infos.size();i++){
            infos.get(i).reset();
        }
    }

    class info_holder{
        boolean info1;
        boolean info2;
        boolean info3;
        boolean info4;
        boolean info5;
        boolean info6;
        boolean info7;
        boolean info8;

        public info_holder(){
            info1=false;
            info2=false;
            info3=false;
            info4=false;
            info5=false;
            info6=false;
            info7=false;
            info8=false;

        }

        public boolean ContainsInfo(){
            return info1||info2||info3||info4||info5||info6||info7||info8;
        }

        public ArrayList<Integer> getInfoId(){
            ArrayList<Integer> toReturn=new ArrayList<>();
            if(info1)toReturn.add(0);
            if(info2)toReturn.add(1);
            if(info3)toReturn.add(2);
            if(info4)toReturn.add(3);
            if(info5)toReturn.add(4);
            if(info6)toReturn.add(5);
            if(info7)toReturn.add(6);
            if(info8)toReturn.add(7);
            return toReturn;
        }

        public void reset(){
            info1=false;
            info2=false;
            info3=false;
            info4=false;
            info5=false;
            info6=false;
            info7=false;
            info8=false;
        }

        public boolean getInfo1(){
            return info1;
        }
        public void changeInfo1(){
            if(info1)info1=false;
            else info1=true;
        }

        public boolean getInfo2(){
            return info2;
        }
        public void changeInfo2(){
            if(info2)info2=false;
            else info2=true;
        }

        public boolean getInfo3(){
            return info3;
        }
        public void changeInfo3(){
            if(info3)info3=false;
            else info3=true;
        }

        public boolean getInfo4(){
            return info4;
        }
        public void changeInfo4(){
            if(info4)info4=false;
            else info4=true;
        }

        public boolean getInfo5(){
            return info5;
        }
        public void changeInfo5(){
            if(info5)info5=false;
            else info5=true;
        }

        public boolean getInfo6(){
            return info6;
        }
        public void changeInfo6(){
            if(info6)info6=false;
            else info6=true;
        }

        public boolean getInfo7(){
            return info7;
        }
        public void changeInfo7(){
            if(info7)info7=false;
            else info7=true;
        }

        public boolean getInfo8(){
            return info8;
        }
        public void changeInfo8(){
            if(info8)info8=false;
            else info8=true;
        }
    }
}
