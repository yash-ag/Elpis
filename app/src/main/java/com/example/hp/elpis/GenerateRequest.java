package com.example.hp.elpis;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class GenerateRequest extends AppCompatActivity {

    private EditText mLocationEdit;
    private EditText mNumberOfPeopleEdit;
    private EditText mCommentEdit;
    private CheckBox mDoctorRequiredCheck;
    private Button mSendRequest;
    private View.OnClickListener mRequestListener = new View.OnClickListener() {
        public void onClick(View v) {
            insertData();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_request);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Send Request");
        
        mLocationEdit = (EditText) findViewById(R.id.location);
        mNumberOfPeopleEdit = (EditText) findViewById(R.id.number_of_people);
        mDoctorRequiredCheck = (CheckBox) findViewById(R.id.doctor_required);
        mCommentEdit = (EditText) findViewById(R.id.comments);

        mSendRequest = findViewById(R.id.send_request);
        mSendRequest.setOnClickListener(mRequestListener);

    }

    public void insertData() {
        String location = mLocationEdit.getText().toString().trim();
        int numberOfPeople = Integer.parseInt(mNumberOfPeopleEdit.getText().toString().trim());
        boolean doctorRequired = mDoctorRequiredCheck.isSelected();
        String comment = mCommentEdit.getText().toString().trim();

        HelpRequestDetails help = new HelpRequestDetails(location, numberOfPeople, doctorRequired, comment);
    }
}
