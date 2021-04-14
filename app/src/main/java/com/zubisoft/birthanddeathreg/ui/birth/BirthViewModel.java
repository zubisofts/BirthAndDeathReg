package com.zubisoft.birthanddeathreg.ui.birth;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BirthViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public BirthViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}