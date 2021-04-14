package com.zubisoft.birthanddeathreg.ui.birth;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;
import com.tiper.MaterialSpinner;
import com.zubisoft.birthanddeathreg.R;
import com.zubisoft.birthanddeathreg.handlers.DataInteractionListener;
import com.zubisoft.birthanddeathreg.handlers.InputListener;
import com.zubisoft.birthanddeathreg.model.FatherBirthData;
import com.zubisoft.birthanddeathreg.model.InformantBirthData;

public class InformantParticularsFragment extends Fragment implements Step {

    private TextInputLayout inputName, inputNumber,inputAddress, inputNationalId,inputRelationShip;
    private TextInputEditText edtName, edtNumber, edtAddress, edtNationalId, edtRelationShip;
    private DataInteractionListener dataInteractionListener;

    public InformantParticularsFragment(DataInteractionListener dataInteractionListener) {
        this.dataInteractionListener=dataInteractionListener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_informant_particulars, container, false);

        inputName = view.findViewById(R.id.inputInformantName);
        inputNumber = view.findViewById(R.id.inputInformantNumber);
        inputAddress = view.findViewById(R.id.inputInformantAddress);
        inputNationalId = view.findViewById(R.id.inputInformantNID);
        inputRelationShip = view.findViewById(R.id.inputRelationship);

        edtName = view.findViewById(R.id.edtInformantName);
        edtNumber = view.findViewById(R.id.edtInformantNumber);
        edtAddress = view.findViewById(R.id.edtInformantAddress);
        edtNationalId = view.findViewById(R.id.edtInformantNID);
        edtRelationShip = view.findViewById(R.id.edtRelationship);

        edtName.addTextChangedListener(new InputListener(inputName));
        edtNumber.addTextChangedListener(new InputListener(inputNumber));
        edtAddress.addTextChangedListener(new InputListener(inputAddress));
        edtNationalId.addTextChangedListener(new InputListener(inputNationalId));
        edtRelationShip.addTextChangedListener(new InputListener(inputRelationShip));

        return view;
    }

    @Nullable
    @Override
    public VerificationError verifyStep() {
        if (isFieldsValidated()){
            dataInteractionListener.onInformantBirthDataPassed(new InformantBirthData(
                    edtName.getText().toString(),
                    edtRelationShip.getText().toString(),
                    edtNumber.getText().toString(),
                    edtNationalId.getText().toString(),
                    edtAddress.getText().toString()
            ));
            return null;
        }
        return new VerificationError("You must complete this form");
    }

    @Override
    public void onSelected() {

    }

    @Override
    public void onError(@NonNull VerificationError error) {

    }

    public boolean isFieldsValidated(){

        if(edtName.getText().toString().isEmpty()){
            inputName.setError("Please fill this field");
            return false;
        }else if (edtRelationShip.getText().toString().isEmpty()){
            inputRelationShip.setError("Please fill this field");
            return false;
        }else if (edtNumber.getText().toString().isEmpty()){
            inputNumber.setError("Please fill this field");
            return false;
        }else if (edtNationalId.getText().toString().isEmpty()){
            inputNationalId.setError("Please fill this field");
            return false;
        }else if (edtAddress.getText().toString().isEmpty()){
            inputAddress.setError("Please fill this field");
            return false;
        }

        return true;
    }
}