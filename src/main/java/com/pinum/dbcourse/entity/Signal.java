package com.pinum.dbcourse.entity;

import com.pinum.dbcourse.entity.jsonmodels.SignalInfo;

public class Signal {
    private String id;
    private SignalInfo signalInfo;

    public Signal() {
    }

    public Signal(SignalInfo signalInfo) {
        this.signalInfo = signalInfo;
    }

    public Signal(String id, SignalInfo signalInfo) {
        this(signalInfo);
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public SignalInfo getSignalInfo() {
        return signalInfo;
    }

    public void setSignalInfo(SignalInfo signalInfo) {
        this.signalInfo = signalInfo;
    }

    @Override
    public String toString() {
        return "Signal{" +
                "id=" + id +
                ", signalInfo=" + signalInfo +
                '}';
    }
}
