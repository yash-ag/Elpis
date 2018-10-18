package com.example.hp.elpis;

//Contains information about the Help Request
public class HelpRequest {
    private String mLocation;
    private int mNumberOfPeople;
    private boolean mDoctorRequired;
    private long mComment;

    public HelpRequest(String location, int numberOfPeople, boolean doctorRequired, long comment)
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

    public long getComment() {
        return mComment;
    }
}
