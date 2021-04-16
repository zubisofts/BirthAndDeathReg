package com.zubisoft.birthanddeathreg.ui.birth;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.zubisoft.birthanddeathreg.model.RestData;
import com.zubisoft.birthanddeathreg.model.birthmodels.BirthRegData;
import com.zubisoft.birthanddeathreg.repository.DataRepository;

public class BirthViewModel extends ViewModel {

    private final MutableLiveData<RestData> birthRegResponse=new MutableLiveData<>();
    private final MutableLiveData<RestData> birthRegListResponse=new MutableLiveData<>();

    public void saveBirthInfo(BirthRegData birthRegData){
        new DataRepository().saveBirthRegistrationData(birthRegData,birthRegResponse);
    }

    public MutableLiveData<RestData> getBirthRegResponse(){
        return birthRegResponse;
    }

    public void fetchBirthRegList(){
        new DataRepository().fetchBirthRegList(birthRegListResponse);
    }

    public MutableLiveData<RestData> onBirthRegListResponse(){
        return birthRegListResponse;
    }

}