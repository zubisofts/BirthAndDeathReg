package com.zubisoft.birthanddeathreg.model;

public class ChildBirthData {

    private String id;
    private String childName;
    private long dateOfBirth;
    private String sex;
    private String placeOfBirth;
    private String placeOfBirthOccurrence;
    private String typeOfBirth;

    public ChildBirthData(String childName, long dateOfBirth, String sex, String placeOfBirth, String placeOfBirthOccurrence, String typeOfBirth) {
        this.childName = childName;
        this.dateOfBirth = dateOfBirth;
        this.sex = sex;
        this.placeOfBirth = placeOfBirth;
        this.placeOfBirthOccurrence = placeOfBirthOccurrence;
        this.typeOfBirth = typeOfBirth;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getChildName() {
        return childName;
    }

    public void setChildName(String childName) {
        this.childName = childName;
    }

    public long getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(long dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public String getPlaceOfBirthOccurrence() {
        return placeOfBirthOccurrence;
    }

    public void setPlaceOfBirthOccurrence(String placeOfBirthOccurrence) {
        this.placeOfBirthOccurrence = placeOfBirthOccurrence;
    }

    public String getTypeOfBirth() {
        return typeOfBirth;
    }

    public void setTypeOfBirth(String typeOfBirth) {
        this.typeOfBirth = typeOfBirth;
    }
}