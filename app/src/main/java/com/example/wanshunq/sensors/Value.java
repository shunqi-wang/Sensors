package com.example.wanshunq.sensors;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Value extends AppCompatActivity {
    private SensorManager manager;
    private LinearLayout values;
    int number=0;
    boolean[] ba;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_value);

        manager=(SensorManager)getSystemService(Context.SENSOR_SERVICE);
        final int intType=getIntent().getIntExtra("intType", Sensor.TYPE_DEVICE_PRIVATE_BASE);
        Sensor sensor=manager.getSensorList(intType).get(0);

        TextView name=(TextView)findViewById(R.id.sensorName);
        name.setText(sensor.getName());

        TextView type=(TextView)findViewById(R.id.sensorType);
        final String stringType=getIntent().getStringExtra("stringType");
        type.setText(stringType);

        values=(LinearLayout)findViewById(R.id.sensorValues);
        manager.registerListener(listener,sensor,333333);

        Button send=(Button)findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int length=0;
                for(boolean b:ba){
                    if(b)length++;
                }

                if(length==0){
                    Toast.makeText(getApplicationContext(),"Please Select One Value!",Toast.LENGTH_LONG);
                    return;
                }

                int[] toSend=new int[length];
                int pointer=0;
                for(int i=0;i<ba.length;i++){
                    if(ba[i]){
                        toSend[pointer]=i;
                        pointer++;
                    }
                }

                Intent intent=new Intent(getApplicationContext(),Info.class);
                intent.putExtra("intType",intType);
                intent.putExtra("stringType",stringType);
                intent.putExtra("valueId",toSend);
                startActivity(intent);
            }
        });
    }

    SensorEventListener listener=new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            if(number==0){
                for(float f:event.values){
                    final TextView view=new TextView(getApplicationContext());
                    view.setLayoutParams(new LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT
                    ));
                    view.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);
                    //view.setTextColor(Color.parseColor("#000000"));
                    String value_text=f+"";
                    view.setText(value_text);

                    view.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            for(int i=0;i<number;i++){
                                if(values.getChildAt(i).equals(view)){
                                    if(ba[i]){
                                        view.setBackgroundColor(Color.TRANSPARENT);
                                        ba[i]=false;
                                    }else {
                                        view.setBackgroundColor(Color.parseColor("#BAE1FC"));
                                        ba[i]=true;
                                    }
                                }
                            }
                        }
                    });

                    values.addView(view);
                }
                number=event.values.length;
                ba=new boolean[number];
            }else {
                for(int i=0;i<event.values.length;i++){
                    TextView textView=(TextView)values.getChildAt(i);
                    String value_text=event.values[i]+"";
                    textView.setText(value_text);
                }
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
    };
}
