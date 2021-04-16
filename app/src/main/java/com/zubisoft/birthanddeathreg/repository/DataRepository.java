package com.zubisoft.birthanddeathreg.repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.zubisoft.birthanddeathreg.model.RestData;
import com.zubisoft.birthanddeathreg.model.birthmodels.BirthRegData;
import com.zubisoft.birthanddeathreg.model.deathmodels.DeathRegData;
import com.zubisoft.birthanddeathreg.model.deathmodels.DeceasedData;

import java.util.ArrayList;

public class DataRepository {

    private FirebaseFirestore firestore;
    public DataRepository(){
        firestore=FirebaseFirestore.getInstance();
    }

public void saveBirthRegistrationData(BirthRegData birthRegData, MutableLiveData<RestData> birthRegResponse){

    CollectionReference ref=firestore.collection("birth_registrations");
    birthRegData.setId(ref.getId());
            ref.add(birthRegData)
            .addOnSuccessListener(documentReference -> {
                birthRegResponse.postValue(
                        new RestData(
                                false,
                                documentReference.getId()
                        )
                );
            }).addOnFailureListener(e -> {
        birthRegResponse.postValue(
                new RestData(
                        true,
                        e.getMessage()
                )
        );
            });

}

    public void saveDeathRegistrationData(DeathRegData deathRegData, MutableLiveData<RestData> deathRegResponse){

        CollectionReference ref=firestore.collection("death_registrations");
        deathRegData.setId(ref.getId());
                ref.add(deathRegData)
                .addOnSuccessListener(documentReference -> {
                    deathRegResponse.postValue(
                            new RestData(
                                    false,
                                    documentReference.getId()
                            )
                    );
                }).addOnFailureListener(e -> {
            deathRegResponse.postValue(
                    new RestData(
                            true,
                            e.getMessage()
                    )
            );
        });

    }

    public void fetchBirthRegList(MutableLiveData<RestData> birthRegListResponse){
        firestore.collection("birth_registrations")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    ArrayList<BirthRegData> data=new ArrayList<>();
                    for(DocumentSnapshot doc:queryDocumentSnapshots.getDocuments()){
                        data.add(doc.toObject(BirthRegData.class));
                    }
                    birthRegListResponse.postValue(new RestData(
                            false,
                            data
                    ));
                }).addOnFailureListener(e -> {
            birthRegListResponse.postValue(new RestData(
                    true,
                    e.getMessage()
            ));
                });
    }

    public void fetchDeathRegList(MutableLiveData<RestData> deathRegListResponse){
        firestore.collection("death_registrations")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    ArrayList<DeathRegData> data=new ArrayList<>();
                    for(DocumentSnapshot doc:queryDocumentSnapshots.getDocuments()){
                        data.add(doc.toObject(DeathRegData.class));
                    }
                    deathRegListResponse.postValue(new RestData(
                            false,
                            data
                    ));
                }).addOnFailureListener(e -> {
            deathRegListResponse.postValue(new RestData(
                    true,
                    e.getMessage()
            ));
        });
    }

}
