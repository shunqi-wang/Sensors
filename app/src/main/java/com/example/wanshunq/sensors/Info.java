package com.example.wanshunq.sensors;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Info extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        TextView info=(TextView)findViewById(R.id.sent_info);
        info.setText(getIntent().getStringExtra("info"));
    }
}
