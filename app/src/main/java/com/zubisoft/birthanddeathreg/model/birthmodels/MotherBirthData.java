package com.zubisoft.birthanddeathreg.model.birthmodels;

public class MotherBirthData {

    private String name;
    private String phoneNumber;
    private String address;
    private String nationalID;
    private String age;
    private String maritalStatus;
    private String stateOfOrigin;
    private String ethnicGroup;
    private String occupation;

    public MotherBirthData() {
    }

    public MotherBirthData(String name, String phoneNumber, String address, String nationalID, String age, String maritalStatus, String stateOfOrigin, String ethnicGroup, String occupation) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.nationalID = nationalID;
        this.age = age;
        this.maritalStatus = maritalStatus;
        this.stateOfOrigin = stateOfOrigin;
        this.ethnicGroup = ethnicGroup;
        this.occupation = occupation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNationalID() {
        return nationalID;
    }

    public void setNationalID(String nationalID) {
        this.nationalID = nationalID;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getStateOfOrigin() {
        return stateOfOrigin;
    }

    public void setStateOfOrigin(String stateOfOrigin) {
        this.stateOfOrigin = stateOfOrigin;
    }

    public String getEthnicGroup() {
        return ethnicGroup;
    }

    public void setEthnicGroup(String ethnicGroup) {
        this.ethnicGroup = ethnicGroup;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }
}
