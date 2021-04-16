package com.zubisoft.birthanddeathreg.model.deathmodels;

import java.io.Serializable;

public class DeceasedData implements Serializable {

    private String name;
    private String address;
    private double age;
    private String deathCause;
    private String stateOrigin;
    private String occupation;
    private String maritalStatus;
    private String gender;
    private String placeOfDeath;
    private String ethnicGroup;
    private long dateOfDeath;

    public DeceasedData() {
    }

    public DeceasedData(String name, String address, double age, String deathCause, String stateOrigin, String occupation, String maritalStatus, String gender, String placeOfDeath, String ethnicGroup, long dateOfDeath) {
        this.name = name;
        this.address = address;
        this.age = age;
        this.deathCause = deathCause;
        this.stateOrigin = stateOrigin;
        this.occupation = occupation;
        this.maritalStatus = maritalStatus;
        this.gender = gender;
        this.placeOfDeath = placeOfDeath;
        this.ethnicGroup = ethnicGroup;
        this.dateOfDeath = dateOfDeath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getAge() {
        return age;
    }

    public void setAge(double age) {
        this.age = age;
    }

    public String getDeathCause() {
        return deathCause;
    }

    public void setDeathCause(String deathCause) {
        this.deathCause = deathCause;
    }

    public String getStateOrigin() {
        return stateOrigin;
    }

    public void setStateOrigin(String stateOrigin) {
        this.stateOrigin = stateOrigin;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPlaceOfDeath() {
        return placeOfDeath;
    }

    public void setPlaceOfDeath(String placeOfDeath) {
        this.placeOfDeath = placeOfDeath;
    }

    public String getEthnicGroup() {
        return ethnicGroup;
    }

    public void setEthnicGroup(String ethnicGroup) {
        this.ethnicGroup = ethnicGroup;
    }

    public long getDateOfDeath() {
        return dateOfDeath;
    }

    public void setDateOfDeath(long dateOfDeath) {
        this.dateOfDeath = dateOfDeath;
    }
}
