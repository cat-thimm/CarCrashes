package org.cthimm;

public class Crashes {
    String crashDate;
    String crashTime;
    String borough;
    String zipCode;
    String location;
    String onStreetName;
    String offStreetName;
    String vehicleTypeCode1;
    String vehicleTypeCode2;

    public Crashes() {
    }

    public String getCrashDate() {
        return crashDate;
    }

    public void setCrashDate(String crashDate) {
        this.crashDate = crashDate;
    }

    public String getCrashTime() {
        return crashTime;
    }

    public void setCrashTime(String crashTime) {
        this.crashTime = crashTime;
    }

    public String getBorough() {
        return borough;
    }

    public void setBorough(String borough) {
        this.borough = borough;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getOnStreetName() {
        return onStreetName;
    }

    public void setOnStreetName(String onStreetName) {
        this.onStreetName = onStreetName;
    }

    public String getOffStreetName() {
        return offStreetName;
    }

    public void setOffStreetName(String offStreetName) {
        this.offStreetName = offStreetName;
    }

    public String getVehicleTypeCode1() {
        return vehicleTypeCode1;
    }

    public void setVehicleTypeCode1(String vehicleTypeCode1) {
        this.vehicleTypeCode1 = vehicleTypeCode1;
    }

    public String getVehicleTypeCode2() {
        return vehicleTypeCode2;
    }

    public void setVehicleTypeCode2(String vehicleTypeCode2) {
        this.vehicleTypeCode2 = vehicleTypeCode2;
    }

    public Crashes(String crashDate, String crashTime, String borough, String zipCode, String location, String onStreetName, String offStreetName, String vehicleTypeCode1, String vehicleTypeCode2) {
        this.crashDate = crashDate;
        this.crashTime = crashTime;
        this.borough = borough;
        this.zipCode = zipCode;
        this.location = location;
        this.onStreetName = onStreetName;
        this.offStreetName = offStreetName;
        this.vehicleTypeCode1 = vehicleTypeCode1;
        this.vehicleTypeCode2 = vehicleTypeCode2;
    }
}
