package com.example.hp.elpis;

//Contains information about the Help Request
public class HelpRequestDetails {
    private String mLocation;
    private int mNumberOfPeople;
    private boolean mDoctorRequired;
    private String mComment;

    public HelpRequestDetails(String location, int numberOfPeople, boolean doctorRequired, String comment)
    {
        mLocation = location;
        mNumberOfPeople = numberOfPeople;
        mDoctorRequired = doctorRequired;
        mComment = comment;

    }

    public String getLocation() {
        return mLocation;
    }

    public boolean isDoctorRequired() {
        return mDoctorRequired;
    }

    public int getNumberOfPeople() {
        return mNumberOfPeople;
    }

    public String getComment() {
        return mComment;
    }
}
