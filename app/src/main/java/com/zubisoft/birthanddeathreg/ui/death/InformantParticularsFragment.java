package com.zubisoft.birthanddeathreg.ui.death;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;
import com.zubisoft.birthanddeathreg.R;
import com.zubisoft.birthanddeathreg.handlers.BirthDataInteractionListener;
import com.zubisoft.birthanddeathreg.handlers.DeathDataInteractionListener;
import com.zubisoft.birthanddeathreg.handlers.InputListener;
import com.zubisoft.birthanddeathreg.model.birthmodels.InformantBirthData;
import com.zubisoft.birthanddeathreg.model.deathmodels.DeathRegData;
import com.zubisoft.birthanddeathreg.model.deathmodels.DeceasedInformantData;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Locale;

public class InformantParticularsFragment extends Fragment implements Step {

    private TextInputLayout inputName,inputAddress,inputRelationShip;
    private TextInputEditText edtName, edtAddress, edtRelationShip;
    private final DeathDataInteractionListener deathDataInteractionListener;


    public InformantParticularsFragment(DeathDataInteractionListener deathDataInteractionListener) {
        this.deathDataInteractionListener=deathDataInteractionListener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_informant_particulars2, container, false);

        inputName = view.findViewById(R.id.inputDeaceasedInformantName);
        inputRelationShip = view.findViewById(R.id.inputDeaceasedRelationShip);
        inputAddress = view.findViewById(R.id.inputDeaceasedInformantAddress);

        edtName = view.findViewById(R.id.edtDeaceasedInformantName);
        edtRelationShip = view.findViewById(R.id.edtDeaceasedRelationship);
        edtAddress = view.findViewById(R.id.edtDeaceasedInformantAddress);

        edtName.addTextChangedListener(new InputListener(inputName));
        edtRelationShip.addTextChangedListener(new InputListener(inputRelationShip));
        edtAddress.addTextChangedListener(new InputListener(inputAddress));

        return view;
    }

    @Nullable
    @Override
    public VerificationError verifyStep() {
        if (isFieldsValidated()){
            deathDataInteractionListener.onDeceasedInformantDataPassed(new DeceasedInformantData(
                    edtName.getText().toString(),
                    edtAddress.getText().toString(),
                    edtRelationShip.getText().toString()

            ));
            return null;
        }
        return new VerificationError("You must complete this form");

    }

    @Override
    public void onSelected() {
        String type=getActivity().getIntent().getStringExtra("type");
        if(type!=null) {
            if (type.equals("edit")) {
                setupInitialData();
            }
        }
    }

    private void setupInitialData() {
        DeathRegData deathRegData= (DeathRegData) getActivity().getIntent().getSerializableExtra("data");
        if(deathRegData != null){
            setDataToViews(deathRegData);
        }
    }

    private void setDataToViews(DeathRegData deathRegData) {
        edtName.setText(deathRegData.getDeceasedInformantData().getName());
        edtRelationShip.setText(deathRegData.getDeceasedInformantData().getRelationShip());
        edtAddress.setText(deathRegData.getDeceasedInformantData().getAddress());
    }

    @Override
    public void onError(@NonNull VerificationError error) {

    }

    public boolean isFieldsValidated(){

        if (edtRelationShip.getText().toString().isEmpty()){
            inputRelationShip.setError("Please fill this field");
            return false;
        }else if (edtName.getText().toString().isEmpty()){
            inputName.setError("Please fill this field");
            return false;
        }else if (edtAddress.getText().toString().isEmpty()){
            inputAddress.setError("Please fill this field");
            return false;
        }

        return true;
    }
}