package com.example.hp.elpis;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;

import java.net.MalformedURLException;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class GenerateRequest extends AppCompatActivity {

    private static final String TAG = GenerateRequest.class.getName();
    private EditText mLocationEdit;
    private EditText mNumberOfPeopleEdit;
    private EditText mCommentEdit;
    private CheckBox mDoctorRequiredCheck;
    private Button mSendRequest;
    private ImageButton mGetLocation;

    private FusedLocationProviderClient mFusedLocationClient;


    private HelpRequestDetails help = null; // Initializing the help details
    private MobileServiceClient mClient;
    private MobileServiceTable<HelpRequestDetails> mHelpRequestTable;


    private View.OnClickListener mRequestListener = new View.OnClickListener() {
        public void onClick(View v) {
            getData();
            addData();
            Toast.makeText(getBaseContext(), "Request Added Succesfully", Toast.LENGTH_LONG).show();
            GenerateRequest.this.finish(); //Closes the activity after entering details

        }
    };
    private View.OnClickListener mLocationListener = new View.OnClickListener() {
        public void onClick(View v) {
            ActivityCompat.requestPermissions(GenerateRequest.this, new String[]{ACCESS_FINE_LOCATION}, 1);
            if (ActivityCompat.checkSelfPermission(getBaseContext(), ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getBaseContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                return;
            }
            mFusedLocationClient.getLastLocation().addOnSuccessListener(GenerateRequest.this, new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            // Got last known location. In some rare situations this can be null.
                            if (location != null) {
                                setLocation(location);
                            }
                        }

                    }
            );
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

        mSendRequest = (Button)findViewById(R.id.send_request);
        mSendRequest.setOnClickListener(mRequestListener);

        mGetLocation = (ImageButton) findViewById(R.id.get_location);
        mGetLocation.setOnClickListener(mLocationListener);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

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

    //Converts loaction to latitude and longitude and sets it in text field
    public void setLocation(Location location) {
        String l = "Lat-" + location.getLatitude() + " Lon-" + location.getLongitude();
        mLocationEdit.setText(l);
    }
}
