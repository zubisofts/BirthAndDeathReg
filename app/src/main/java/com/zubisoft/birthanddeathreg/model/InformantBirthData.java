package com.zubisoft.birthanddeathreg.model;

public class InformantBirthData {

    private String id;
    private String name;
    private String relationship;
    private String phoneNumber;
    private String nationalId;
    private String address;

    public InformantBirthData(String name, String relationship, String phoneNumber, String nationalId, String address) {
        this.name = name;
        this.relationship = relationship;
        this.phoneNumber = phoneNumber;
        this.nationalId = nationalId;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
