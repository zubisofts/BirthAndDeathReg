package com.zubisoft.birthanddeathreg.ui.death;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.zubisoft.birthanddeathreg.model.RestData;
import com.zubisoft.birthanddeathreg.model.birthmodels.BirthRegData;
import com.zubisoft.birthanddeathreg.model.deathmodels.DeathRegData;
import com.zubisoft.birthanddeathreg.repository.DataRepository;

public class DeathsViewModel extends AndroidViewModel {

    private static final String TAG = "DeathsViewModel";

    private final MutableLiveData<RestData> deathRegResponse=new MutableLiveData<>();
    private final MutableLiveData<RestData> deathRegListResponse=new MutableLiveData<>();
    private final MutableLiveData<DeathRegData> editDeathRegData=new MutableLiveData<>();

    public DeathsViewModel(@NonNull Application application) {
        super(application);
    }

    public void saveDeathInfo(DeathRegData deathRegData){
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

    public void deleteDeathRecord(String id) {
        new DataRepository().deleteDeathRecord(id);
    }

    public void setEditDeathRegData(DeathRegData deathRegData){
        editDeathRegData.postValue(deathRegData);
    }

    public MutableLiveData<DeathRegData> getEditDeathRegData() {
        return editDeathRegData;
    }

    public void updateDeathInfo(DeathRegData deathRegData) {
        new DataRepository().updateDeathRegData(deathRegData,deathRegResponse);
    }
}