package com.example.hp.elpis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;


public class MainActivity extends AppCompatActivity {

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
                Intent requestIntent = new Intent(MainActivity.this, GenerateRequest.class);
                startActivity(requestIntent);
            }
        });
    }
}