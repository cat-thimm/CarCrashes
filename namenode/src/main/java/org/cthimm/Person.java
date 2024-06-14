package org.cthimm;

public class Person {
    String type;
    String age;
    String emotionalStatus;
    String bodilyInjury;
    String safetyEquipment;
    String gender;

    public Person(String type, String age, String emotionalStatus, String bodilyInjury, String safetyEquipment, String gender) {
        this.type = type;
        this.age = age;
        this.emotionalStatus = emotionalStatus;
        this.bodilyInjury = bodilyInjury;
        this.safetyEquipment = safetyEquipment;
        this.gender = gender;
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

    public Person() {
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
