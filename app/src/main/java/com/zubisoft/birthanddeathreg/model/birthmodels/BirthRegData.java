package com.zubisoft.birthanddeathreg.model.birthmodels;

import java.io.Serializable;

public class BirthRegData implements Serializable {
    private String id;
    private ChildBirthData childBirthData;
    private MotherBirthData motherBirthData;
    private FatherBirthData fatherBirthData;
    private InformantBirthData informantBirthData;

    public BirthRegData() {
    }

    public BirthRegData(ChildBirthData childBirthData, MotherBirthData motherBirthData, FatherBirthData fatherBirthData, InformantBirthData informantBirthData) {
        this.childBirthData = childBirthData;
        this.motherBirthData = motherBirthData;
        this.fatherBirthData = fatherBirthData;
        this.informantBirthData = informantBirthData;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ChildBirthData getChildBirthData() {
        return childBirthData;
    }

    public void setChildBirthData(ChildBirthData childBirthData) {
        this.childBirthData = childBirthData;
    }

    public MotherBirthData getMotherBirthData() {
        return motherBirthData;
    }

    public void setMotherBirthData(MotherBirthData motherBirthData) {
        this.motherBirthData = motherBirthData;
    }

    public FatherBirthData getFatherBirthData() {
        return fatherBirthData;
    }

    public void setFatherBirthData(FatherBirthData fatherBirthData) {
        this.fatherBirthData = fatherBirthData;
    }

    public InformantBirthData getInformantBirthData() {
        return informantBirthData;
    }

    public void setInformantBirthData(InformantBirthData informantBirthData) {
        this.informantBirthData = informantBirthData;
    }
}
