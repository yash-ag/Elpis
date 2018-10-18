package com.example.hp.elpis;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;

import java.net.MalformedURLException;

public class GenerateRequest extends AppCompatActivity {

    private static final String TAG = GenerateRequest.class.getName();
    private EditText mLocationEdit;
    private EditText mNumberOfPeopleEdit;
    private EditText mCommentEdit;
    private CheckBox mDoctorRequiredCheck;
    private Button mSendRequest;

    private HelpRequestDetails help = null;
    private MobileServiceClient mClient;
    private MobileServiceTable<HelpRequestDetails> mHelpRequestTable;


    private View.OnClickListener mRequestListener = new View.OnClickListener() {
        public void onClick(View v) {
            getData();
            addData();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_request);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Send Request");

        connectAzure();
        mLocationEdit = (EditText) findViewById(R.id.location);
        mNumberOfPeopleEdit = (EditText) findViewById(R.id.number_of_people);
        mDoctorRequiredCheck = (CheckBox) findViewById(R.id.doctor_required);
        mCommentEdit = (EditText) findViewById(R.id.comments);

        mSendRequest = findViewById(R.id.send_request);
        mSendRequest.setOnClickListener(mRequestListener);

    }

    // This connects the app to Azure Infrastructure
    public void connectAzure() {
        try {
            mClient = new MobileServiceClient(
                    "https://elpisiitr.azurewebsites.net",
                    this
            );
            mHelpRequestTable = mClient.getTable("Help_required_details", HelpRequestDetails.class);
        } catch (MalformedURLException e) {
            Log.e(TAG, "connectAzure: Error connecting to table ");
        }
    }

    //Fetches the user entered Data from the text fields
    public void getData() {
        String location = mLocationEdit.getText().toString().trim();
        int numberOfPeople = Integer.parseInt(mNumberOfPeopleEdit.getText().toString().trim());
        boolean doctorRequired = mDoctorRequiredCheck.isSelected();
        String comment = mCommentEdit.getText().toString().trim();

        help = new HelpRequestDetails(location, numberOfPeople, doctorRequired, comment);
    }

    //Adds the data to the table in azure infrastructure
    @SuppressLint("StaticFieldLeak")
    public void addData() {
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... params) {
                try {
                    mHelpRequestTable.insert(help).get(); //Inserts data
                } catch (Exception exception) {
                    Log.e(TAG, exception.toString());
                }
                return null;
            }
        }.execute();

        mLocationEdit.setText("");
        mNumberOfPeopleEdit.setText("");
        mDoctorRequiredCheck.setSelected(false);
        mCommentEdit.setText("");
    }
}
