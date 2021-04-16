package com.zubisoft.birthanddeathreg.handlers;

import com.zubisoft.birthanddeathreg.model.birthmodels.ChildBirthData;
import com.zubisoft.birthanddeathreg.model.birthmodels.FatherBirthData;
import com.zubisoft.birthanddeathreg.model.birthmodels.InformantBirthData;
import com.zubisoft.birthanddeathreg.model.birthmodels.MotherBirthData;

public interface BirthDataInteractionListener {
    public void onChildBirthDataPassed(ChildBirthData childBirthData);
    public void onMotherBirthDataPassed(MotherBirthData motherBirthData);
    public void onFatherBirthDataPassed(FatherBirthData fatherBirthData);
    public void onInformantBirthDataPassed(InformantBirthData informantBirthData);
}
