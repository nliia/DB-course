package com.pinum.dbcourse.entity.jsonmodels;

import java.io.Serializable;
import java.util.Date;

public class SignalInfo implements Serializable {
    private Long userId;
    private Date time;
    private double latitude;
    private double longitude;

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

    public SignalInfo(Long userId, Date time, double latitude, double longitude) {
        this.userId = userId;
        this.time = time;
        this.latitude = latitude;
        this.longitude = longitude;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "SignalInfo{" +
                "userId=" + userId +
                ", time=" + time +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
