package com.zubisoft.birthanddeathreg.handlers;

import com.zubisoft.birthanddeathreg.model.ChildBirthData;
import com.zubisoft.birthanddeathreg.model.FatherBirthData;
import com.zubisoft.birthanddeathreg.model.InformantBirthData;
import com.zubisoft.birthanddeathreg.model.MotherBirthData;

public interface DataInteractionListener {
    public void onChildBirthDataPassed(ChildBirthData childBirthData);
    public void onMotherBirthDataPassed(MotherBirthData motherBirthData);
    public void onFatherBirthDataPassed(FatherBirthData fatherBirthData);
    public void onInformantBirthDataPassed(InformantBirthData informantBirthData);
}
