package com.example.hp.elpis;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {


    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //OnClickListener for request button
        Button send_request = (Button) findViewById(R.id.send_req);
        send_request.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent requestIntent = new Intent(MainActivity.this, GenerateRequest.class);
                startActivity(requestIntent);
            }
        });


        //OnClickListener for Volunteer button
        Button volunteer = (Button) findViewById(R.id.volunteer);
        volunteer.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent requestIntent = new Intent(MainActivity.this, Volunteer.class);
                startActivity(requestIntent);
            }
        });


    }
}