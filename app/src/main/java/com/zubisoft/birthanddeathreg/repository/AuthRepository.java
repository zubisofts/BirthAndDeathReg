package com.zubisoft.birthanddeathreg.repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.zubisoft.birthanddeathreg.model.RestData;
import com.zubisoft.birthanddeathreg.model.User;

public class AuthRepository {

    private final FirebaseAuth mFirebaseAuth;

    public AuthRepository() {
        mFirebaseAuth = FirebaseAuth.getInstance();
    }

    public void loginUser(String email, String password, MutableLiveData<RestData> authRes) {
        mFirebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener(authResult -> {
                    authRes.postValue(
                            new RestData(
                                    false,
                                    authResult.getUser()
                            )
                    );
                })
                .addOnFailureListener(e -> {

                });
    }

    public void registerUser(User user, String password, MutableLiveData<RestData> authRes) {

        mFirebaseAuth.createUserWithEmailAndPassword(user.getEmail(), password)
                .addOnSuccessListener(authResult -> {

                })
                .addOnFailureListener(e -> {

                });
    }
}
