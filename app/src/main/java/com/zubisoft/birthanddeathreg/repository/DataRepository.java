package com.zubisoft.birthanddeathreg.repository;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.zubisoft.birthanddeathreg.model.RestData;
import com.zubisoft.birthanddeathreg.model.birthmodels.BirthRegData;
import com.zubisoft.birthanddeathreg.model.deathmodels.DeathRegData;
import com.zubisoft.birthanddeathreg.model.deathmodels.DeceasedData;

import java.util.ArrayList;

public class DataRepository {

    private static final String TAG = "DataRepository";

    private FirebaseFirestore firestore;

    public DataRepository() {
        firestore = FirebaseFirestore.getInstance();
    }

    public void saveBirthRegistrationData(BirthRegData birthRegData, MutableLiveData<RestData> birthRegResponse) {

        DocumentReference ref = firestore.collection("birth_registrations").document();
        birthRegData.setId(ref.getId());
        ref.set(birthRegData)
                .addOnSuccessListener(documentReference -> {
                    birthRegResponse.postValue(
                            new RestData(
                                    false,
                                    "Record uploaded successfully!"
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

    public void saveDeathRegistrationData(DeathRegData deathRegData, MutableLiveData<RestData> deathRegResponse) {

        DocumentReference ref = firestore.collection("death_registrations").document();
        deathRegData.setId(ref.getId());
        ref.set(deathRegData)
                .addOnSuccessListener(documentReference -> {
                    deathRegResponse.postValue(
                            new RestData(
                                    false,
                                    "Record uploaded successfully"
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

    public void fetchBirthRegList(MutableLiveData<RestData> birthRegListResponse) {

        firestore.collection("birth_registrations")
                .addSnapshotListener((value, error) -> {

                    if (error == null) {
                        ArrayList<BirthRegData> data = new ArrayList<>();
                        for (DocumentSnapshot doc : value.getDocuments()) {
                            data.add(doc.toObject(BirthRegData.class));
                        }
                        birthRegListResponse.postValue(new RestData(
                                false,
                                data
                        ));
                    } else {
                        birthRegListResponse.postValue(new RestData(
                                true,
                                error.getMessage()
                        ));
                    }
                });

    }

    public void fetchDeathRegList(MutableLiveData<RestData> deathRegListResponse) {

        firestore.collection("death_registrations")
                .addSnapshotListener((value, error) -> {
                    if (error == null) {
                        ArrayList<DeathRegData> data = new ArrayList<>();
                        for (DocumentSnapshot doc : value.getDocuments()) {
                            data.add(doc.toObject(DeathRegData.class));
                        }
                        deathRegListResponse.postValue(new RestData(
                                false,
                                data
                        ));
                    } else {
                        deathRegListResponse.postValue(new RestData(
                                true,
                                error.getMessage()
                        ));
                    }
                });

    }

    public void deleteDeathRecord(String id) {
        firestore.collection("death_registrations")
                .document(id)
                .delete();
    }

    public void deleteBirthRecord(String id) {
        firestore.collection("birth_registrations")
                .document(id)
                .delete();
    }

    public void updateDeathRegData(DeathRegData deathRegData, MutableLiveData<RestData> deathRegResponse) {
        firestore.collection("death_registrations")
       .document(deathRegData.getId())
                .set(deathRegData)
                .addOnSuccessListener(documentReference -> {
                    deathRegResponse.postValue(
                            new RestData(
                                    false,
                                    "Record updated successfully!"
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

    public void updateBirthRegData(BirthRegData birthRegData, MutableLiveData<RestData> birthRegResponse) {
        firestore.collection("birth_registrations")
                .document(birthRegData.getId())
                .set(birthRegData)
                .addOnSuccessListener(documentReference -> {
                    birthRegResponse.postValue(
                            new RestData(
                                    false,
                                    "Record updated successfully!"
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
}
