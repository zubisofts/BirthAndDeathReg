package com.zubisoft.birthanddeathreg.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.zubisoft.birthanddeathreg.model.RestData;
import com.zubisoft.birthanddeathreg.model.User;
import com.zubisoft.birthanddeathreg.repository.AuthRepository;

public class AuthViewModel extends ViewModel {

    private MutableLiveData<RestData> authRes=new MutableLiveData<>();
    private AuthRepository authRepository;

    public AuthViewModel(){
        authRepository=new AuthRepository();
    }

    public void loginUser(String email, String password){
        authRepository.loginUser(email,password,authRes);
    }

    public LiveData<RestData> authRes(){
        return authRes;
    }

    public void registerUser(User user, String password){
        authRepository.registerUser(user,password,authRes);
    }
}
