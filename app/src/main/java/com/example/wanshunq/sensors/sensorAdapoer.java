package com.example.wanshunq.sensors;

import android.content.Context;
import android.hardware.Sensor;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by wsq96 on 2018/5/15.
 */

public class sensorAdapoer extends BaseAdapter{
    private Context context;
    private ArrayList<Sensor> sensors;
    private ArrayList<info_holder> infos=new ArrayList<>();

    public sensorAdapoer(Context context,List<Sensor> sensors){
        this.context=context;
        this.sensors=new ArrayList<>(sensors);
        for(int i=0;i<sensors.size();i++){
            infos.add(new info_holder());
        }
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
        final info_holder holder=infos.get(i);

        TextView info1=(TextView)view.findViewById(R.id.name);
        info1.setText(sensor.getName());

        TextView info2=(TextView)view.findViewById(R.id.vender);
        info2.setText(sensor.getVendor());

        TextView info3=(TextView)view.findViewById(R.id.version);
        String version=sensor.getVersion()+"";
        info3.setText(version);

        TextView info4=(TextView)view.findViewById(R.id.intType);
        String intType=sensor.getType()+"";
        info4.setText(intType);

        TextView info9=(TextView)view.findViewById(R.id.stringType);
        String stringType=sensorTypeToString(sensor.getType());
        info9.setText(stringType);

        TextView info5=(TextView)view.findViewById(R.id.maxRange);
        String maxRange=sensor.getMaximumRange()+"";
        info5.setText(maxRange);

        TextView info6=(TextView)view.findViewById(R.id.resolution);
        String resolution=sensor.getResolution()+"";
        info6.setText(resolution);

        TextView info7=(TextView)view.findViewById(R.id.power);
        String power=sensor.getPower()+"";
        info7.setText(power);

        TextView info8=(TextView)view.findViewById(R.id.minDelay);
        String minDelay=sensor.getMinDelay()+"";
        info8.setText(minDelay);

        final RelativeLayout line1=(RelativeLayout)view.findViewById(R.id.info1);
        line1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.changeInfo1();
                if(holder.getInfo1()){
                    line1.setBackgroundResource(R.drawable.border);
                }
                else {
                    line1.setBackgroundResource(0);
                }
            }
        });

        final RelativeLayout line2=(RelativeLayout)view.findViewById(R.id.info2);
        line2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.changeInfo2();
                if(holder.getInfo2()){
                    line2.setBackgroundResource(R.drawable.border);
                }
                else {
                    line2.setBackgroundResource(0);
                }
            }
        });

        return view;
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
