package com.example.hp.elpis;

import com.microsoft.windowsazure.mobileservices.table.DateTimeOffset;

//Contains information about the Help Request
public class HelpRequestDetails {
    @com.google.gson.annotations.SerializedName("id")
    private String mId;
    @com.google.gson.annotations.SerializedName("createdAt")
    private DateTimeOffset mCreatedAt;
    @com.google.gson.annotations.SerializedName("updatedAt")
    private DateTimeOffset mUpdatedAt;
    @com.google.gson.annotations.SerializedName("version")
    private String mVersion;
    @com.google.gson.annotations.SerializedName("location")
    private String mLocation;
    @com.google.gson.annotations.SerializedName("numberOfPeople")
    private int mNumberOfPeople;
    @com.google.gson.annotations.SerializedName("doctorRequired")
    private boolean mDoctorRequired;
    @com.google.gson.annotations.SerializedName("comment")
    private String mComment;

    public HelpRequestDetails(String location, int numberOfPeople, boolean doctorRequired, String comment) {
        mLocation = location;
        mNumberOfPeople = numberOfPeople;
        mDoctorRequired = doctorRequired;
        mComment = comment;

    }

    public String getId() {
        return mId;
    }

    public final void setId(String id) {
        mId = id;
    }

    public DateTimeOffset getCreatedAt() {
        return mCreatedAt;
    }

    protected void setCreatedAt(DateTimeOffset createdAt) {
        mCreatedAt = createdAt;
    }

    public DateTimeOffset getUpdatedAt() {
        return mUpdatedAt;
    }

    protected void setUpdatedAt(DateTimeOffset updatedAt) {
        mUpdatedAt = updatedAt;
    }

    public String getVersion() {
        return mVersion;
    }

    public final void setVersion(String version) {
        mVersion = version;
    }

    public String getLocation() {
        return mLocation;
    }

    public final void setLocation(String location) {
        mLocation = location;
    }

    public int getNumberOfPeople() {
        return mNumberOfPeople;
    }

    public final void setNumberOfPeople(int numberOfPeople) {
        mNumberOfPeople = numberOfPeople;
    }

    public boolean getDoctorRequired() {
        return mDoctorRequired;
    }

    public final void setDoctorRequired(boolean doctorRequired) {
        mDoctorRequired = doctorRequired;
    }

    public String getComment() {
        return mComment;
    }

    public final void setComment(String comment) {
        mComment = comment;
    }


}
