package org.cthimm;

import java.util.Optional;

public class Crashes {
    String collisionId;
    String crashDate;
    String crashTime;
    String borough;
    String zipCode;
    String location;
    String onStreetName;
    String offStreetName;
    String vehicleTypeCode1;
    String vehicleTypeCode2;
    String contributingFactorVehicle1;
    String contributingFactorVehicle2;
    String numberOfPersonsInjured;
    String numberOfPersonsKilled;

    public Crashes() {
        this(null,null,null,null,null,null,null,null,null,null, null, null, null, null);
    }

    public Crashes(String collisionId, String crashDate, String crashTime, String borough, String zipCode, String location, String onStreetName, String offStreetName, String vehicleTypeCode1, String vehicleTypeCode2, String contributingFactorVehicle1, String contributingFactorVehicle2, String numberOfPersonsInjured, String numberOfPersonsKilled) {
        this.collisionId = Optional.ofNullable(collisionId).orElse("");
        this.crashDate = Optional.ofNullable(crashDate).orElse("");
        this.crashTime = Optional.ofNullable(crashTime).orElse("");
        this.borough = Optional.ofNullable(borough).orElse("");
        this.zipCode = Optional.ofNullable(zipCode).orElse("");
        this.location = Optional.ofNullable(location).orElse("");
        this.onStreetName = Optional.ofNullable(onStreetName).orElse("");
        this.offStreetName = Optional.ofNullable(offStreetName).orElse("");
        this.vehicleTypeCode1 = Optional.ofNullable(vehicleTypeCode1).orElse("");
        this.vehicleTypeCode2 = Optional.ofNullable(vehicleTypeCode2).orElse("");
        this.contributingFactorVehicle1 = Optional.ofNullable(contributingFactorVehicle1).orElse("");
        this.contributingFactorVehicle2 = Optional.ofNullable(contributingFactorVehicle2).orElse("");
        this.numberOfPersonsInjured = Optional.ofNullable(numberOfPersonsInjured).orElse("");
        this.numberOfPersonsKilled = Optional.ofNullable(numberOfPersonsKilled).orElse("");
    }

    public String getContributingFactorVehicle1() {
        return contributingFactorVehicle1;
    }

    public void setContributingFactorVehicle1(String contributingFactorVehicle1) {
        this.contributingFactorVehicle1 = contributingFactorVehicle1;
    }

    public String getContributingFactorVehicle2() {
        return contributingFactorVehicle2;
    }

    public void setContributingFactorVehicle2(String contributingFactorVehicle2) {
        this.contributingFactorVehicle2 = contributingFactorVehicle2;
    }

    public String getNumberOfPersonsInjured() {
        return numberOfPersonsInjured;
    }

    public void setNumberOfPersonsInjured(String numberOfPersonsInjured) {
        this.numberOfPersonsInjured = numberOfPersonsInjured;
    }

    public String getNumberOfPersonsKilled() {
        return numberOfPersonsKilled;
    }

    public void setNumberOfPersonsKilled(String numberOfPersonsKilled) {
        this.numberOfPersonsKilled = numberOfPersonsKilled;
    }

    public String getCollisionId() {
        return collisionId;
    }

    public void setCollisionId(String collisionId) {
        this.collisionId = collisionId;
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


}
