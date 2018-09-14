package com.pinum.dbcourse.entity.jsonmodels;

import java.io.Serializable;
import java.util.Date;

public class SignalInfo implements Serializable {
    private double latitude;
    private double longitude;
    private Date time;

    public SignalInfo() {
    }

    public SignalInfo(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.time = new Date();
    }

    public SignalInfo(double latitude, double longitude, Date time) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.time = time;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "SignalInfo{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                ", time=" + time +
                '}';
    }
}
