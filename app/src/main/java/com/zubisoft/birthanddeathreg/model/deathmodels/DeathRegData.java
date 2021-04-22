package com.zubisoft.birthanddeathreg.model.deathmodels;

import java.io.Serializable;

public class DeathRegData implements Serializable {

    private String id;
    private DeceasedData deceasedData;
    private DeceasedInformantData deceasedInformantData;

    public DeathRegData() {
    }

    public DeathRegData(DeceasedData deceasedData, DeceasedInformantData deceasedInformantData) {
        this.deceasedData = deceasedData;
        this.deceasedInformantData = deceasedInformantData;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public DeceasedData getDeceasedData() {
        return deceasedData;
    }

    public void setDeceasedData(DeceasedData deceasedData) {
        this.deceasedData = deceasedData;
    }

    public DeceasedInformantData getDeceasedInformantData() {
        return deceasedInformantData;
    }

    public void setDeceasedInformantData(DeceasedInformantData deceasedInformantData) {
        this.deceasedInformantData = deceasedInformantData;
    }
}
