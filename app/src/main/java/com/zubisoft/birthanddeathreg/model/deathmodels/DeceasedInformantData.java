package com.zubisoft.birthanddeathreg.model.deathmodels;

public class DeceasedInformantData {
    private String name;
    private String address;
    private String relationShip;

    public DeceasedInformantData() {
    }

    public DeceasedInformantData(String name, String address, String relationShip) {
        this.name = name;
        this.address = address;
        this.relationShip = relationShip;
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

    public String getRelationShip() {
        return relationShip;
    }

    public void setRelationShip(String relationShip) {
        this.relationShip = relationShip;
    }
}
