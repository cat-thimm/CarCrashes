package org.cthimm;

import java.util.Optional;

public class Person {
    String type;
    String age;
    String emotionalStatus;
    String bodilyInjury;
    String safetyEquipment;
    String gender;

    public Person() {
        this(null, null, null, null, null, null);
    }

    public Person(String type, String age, String emotionalStatus, String bodilyInjury, String safetyEquipment, String gender) {
        this.type = Optional.ofNullable(type).orElse("");
        this.age = Optional.ofNullable(age).orElse("");
        this.emotionalStatus = Optional.ofNullable(emotionalStatus).orElse("");
        this.bodilyInjury = Optional.ofNullable(bodilyInjury).orElse("");
        this.safetyEquipment = Optional.ofNullable(safetyEquipment).orElse("");
        this.gender = Optional.ofNullable(gender).orElse("");
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAge() {
        return age;
    }



    public void setAge(String age) {
        this.age = age;
    }

    public String getEmotionalStatus() {
        return emotionalStatus;
    }

    public void setEmotionalStatus(String emotionalStatus) {
        this.emotionalStatus = emotionalStatus;
    }

    public String getBodilyInjury() {
        return bodilyInjury;
    }

    public void setBodilyInjury(String bodilyInjury) {
        this.bodilyInjury = bodilyInjury;
    }

    public String getSafetyEquipment() {
        return safetyEquipment;
    }

    public void setSafetyEquipment(String safetyEquipment) {
        this.safetyEquipment = safetyEquipment;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
