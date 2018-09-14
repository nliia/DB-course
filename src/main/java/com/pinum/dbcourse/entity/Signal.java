package com.pinum.dbcourse.entity;

import com.pinum.dbcourse.entity.jsonmodels.SignalInfo;

import java.util.UUID;

public class Signal {
    private UUID id;
    private SignalInfo signalInfo;

    public Signal() {
    }

    public Signal(SignalInfo signalInfo) {
        this.signalInfo = signalInfo;
    }

    public Signal(UUID id, SignalInfo signalInfo) {
        this(signalInfo);
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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
