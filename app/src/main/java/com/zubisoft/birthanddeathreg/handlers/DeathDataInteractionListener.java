package com.zubisoft.birthanddeathreg.handlers;

import com.zubisoft.birthanddeathreg.model.birthmodels.ChildBirthData;
import com.zubisoft.birthanddeathreg.model.birthmodels.FatherBirthData;
import com.zubisoft.birthanddeathreg.model.birthmodels.InformantBirthData;
import com.zubisoft.birthanddeathreg.model.birthmodels.MotherBirthData;
import com.zubisoft.birthanddeathreg.model.deathmodels.DeceasedData;
import com.zubisoft.birthanddeathreg.model.deathmodels.DeceasedInformantData;

public interface DeathDataInteractionListener {
    public void onDeceasedDataPassed(DeceasedData deceasedData);
    public void onDeceasedInformantDataPassed(DeceasedInformantData deceasedInformantData);
}
