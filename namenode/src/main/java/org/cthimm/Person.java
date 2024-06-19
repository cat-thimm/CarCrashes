package org.cthimm;

import java.util.Objects;
import java.util.Optional;

public class Person {
    String type;
    String age;
    String emotionalStatus;
    String bodilyInjury;
    String positionInVehicle;
    String safetyEquipment;
    String pedRole;
    String gender;


    public Person() {
        this(null, null, null, null, null, null, null, null);
    }

    public Person(String type, String age, String emotionalStatus, String bodilyInjury, String positionInVehicle, String safetyEquipment, String pedRole, String gender) {
        this.type = Optional.ofNullable(type).orElse("");
        this.age = Optional.ofNullable(age).orElse("");
        this.emotionalStatus = Optional.ofNullable(emotionalStatus).orElse("");
        this.bodilyInjury = Optional.ofNullable(bodilyInjury).orElse("");
        this.positionInVehicle = Optional.ofNullable(positionInVehicle).orElse("");
        this.safetyEquipment = Optional.ofNullable(safetyEquipment).orElse("");
        this.pedRole = Optional.ofNullable(pedRole).orElse("");
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

    public String getPositionInVehicle() {
        return positionInVehicle;
    }

    public void setPositionInVehicle(String positionInVehicle) {
        this.positionInVehicle = positionInVehicle;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(type, person.type) &&
                Objects.equals(age, person.age) &&
                Objects.equals(emotionalStatus, person.emotionalStatus) &&
                Objects.equals(bodilyInjury, person.bodilyInjury) &&
                Objects.equals(positionInVehicle, person.positionInVehicle) &&
                Objects.equals(safetyEquipment, person.safetyEquipment) &&
                Objects.equals(gender, person.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, age, emotionalStatus, bodilyInjury, positionInVehicle, safetyEquipment, gender);
    }
}
