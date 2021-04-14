package com.zubisoft.birthanddeathreg.ui.death;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DeathsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public DeathsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}