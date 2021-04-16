package com.zubisoft.birthanddeathreg.ui.death;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.zubisoft.birthanddeathreg.model.RestData;
import com.zubisoft.birthanddeathreg.model.birthmodels.BirthRegData;
import com.zubisoft.birthanddeathreg.model.deathmodels.DeathRegData;
import com.zubisoft.birthanddeathreg.repository.DataRepository;

public class DeathsViewModel extends ViewModel {

    private final MutableLiveData<RestData> deathRegResponse=new MutableLiveData<>();
    private final MutableLiveData<RestData> deathRegListResponse=new MutableLiveData<>();

    public void savDeathInfo(DeathRegData deathRegData){
        new DataRepository().saveDeathRegistrationData(deathRegData,deathRegResponse);
    }

    public void fetchDeathRegList(){
        new DataRepository().fetchDeathRegList(deathRegListResponse);
    }

    public MutableLiveData<RestData> onDeathRegListResponse(){
        return deathRegListResponse;
    }

    public MutableLiveData<RestData> getDeathRegResponse(){
        return deathRegResponse;
    }
}