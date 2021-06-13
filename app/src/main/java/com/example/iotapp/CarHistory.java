package com.example.iotapp;

public class CarHistory {
    public String ownedBy, plateNo, time, isAllowed, date;

    public CarHistory() {

    }

    public CarHistory(String OwnedBy, String PlateNo, String Time, String IsAllowed, String Date) {
        this.ownedBy = OwnedBy;
        this.plateNo = PlateNo;
        this.time = Time;
        this.isAllowed = IsAllowed;
        this.date = Date;
    }

    public String getOwnedBy() {
        return ownedBy;
    }

    public void setOwnedBy(String ownedBy) {
        this.ownedBy = ownedBy;
    }

    public String getPlateNo() {
        return plateNo;
    }

    public void setPlateNo(String plateNo) {
        this.plateNo = plateNo;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getIsAllowed() {
        return isAllowed;
    }

    public void setIsAllowed(String isAllowed) {
        this.isAllowed = isAllowed;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
