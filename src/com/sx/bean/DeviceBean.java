package com.sx.bean;

/**
 * Created by Administrator on 2018/12/10.
 */
public class DeviceBean {
    String id = "";
    String DeviceID = "";
    String StepCount = "";
    String SignalIntensity = "";
    String UploadTime = "";
    String UpdateTime = "";
    String Longitude = "";
    String Latitude = "";


    public String getLongitude() {
        return Longitude;
    }

    public void setLongitude(String longitude) {
        Longitude = longitude;
    }

    public String getLatitude() {
        return Latitude;
    }

    public void setLatitude(String latitude) {
        Latitude = latitude;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDeviceID(String deviceID) {
        DeviceID = deviceID;
    }

    public void setStepCount(String stepCount) {
        StepCount = stepCount;
    }

    public void setSignalIntensity(String signalIntensity) {
        SignalIntensity = signalIntensity;
    }


    public String getId() {
        return id;
    }

    public String getDeviceID() {
        return DeviceID;
    }

    public String getStepCount() {
        return StepCount;
    }

    public String getSignalIntensity() {
        return SignalIntensity;
    }

    public String getUploadTime() {
        return UploadTime;
    }

    public void setUploadTime(String uploadTime) {
        UploadTime = uploadTime;
    }

    public String getUpdateTime() {
        return UpdateTime;
    }

    public void setUpdateTime(String updateTime) {
        UpdateTime = updateTime;
    }
}
