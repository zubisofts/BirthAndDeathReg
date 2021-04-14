package com.zubisoft.birthanddeathreg.ui.birth;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

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

public class FatherParticularsFragment extends Fragment implements Step {

    private TextInputLayout inputName, inputNumber, inputStateOrigin, inputAddress, inputNationalId, inputOccupation, inputAge;
    private TextInputEditText edtName, edtNumber, edtStateOrigin, edtAddress, edtNationalId, edtOccupation, edtAge;
    private MaterialSpinner spinnerMaritalStatus, spinnerEthnic;
    private DataInteractionListener dataInteractionListener;

    public FatherParticularsFragment(DataInteractionListener dataInteractionListener) {
       this.dataInteractionListener=dataInteractionListener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_father_particulars, container, false);

        inputName = view.findViewById(R.id.inputFatherName);
        inputNumber = view.findViewById(R.id.inputFathersNumber);
        inputStateOrigin = view.findViewById(R.id.inputFatherStateOfOrigin);
        inputAddress = view.findViewById(R.id.inputFatherAddress);
        inputNationalId = view.findViewById(R.id.inputFatherNID);
        inputOccupation = view.findViewById(R.id.inputFatherOccupation);
        inputAge = view.findViewById(R.id.inputFatherAge);

        edtName = view.findViewById(R.id.edtFathersName);
        edtNumber = view.findViewById(R.id.edtFathersNumber);
        edtAddress = view.findViewById(R.id.edtFatherAddress);
        edtStateOrigin = view.findViewById(R.id.edtFatherOrigin);
        edtNationalId = view.findViewById(R.id.edtFatherNID);
        edtOccupation = view.findViewById(R.id.edtFatherOccupation);
        edtAge = view.findViewById(R.id.edtFatherAge);

        spinnerEthnic = view.findViewById(R.id.fatherEthnicSpinner);
        spinnerMaritalStatus = view.findViewById(R.id.spinnerMaritalStatus);

        String[] status = new String[]{"Single", "Married"};
        spinnerMaritalStatus.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, status));
        String[] ethnics = new String[]{"Ibo", "Yoruba", "Hausa"};
        spinnerEthnic.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, ethnics));

        edtName.addTextChangedListener(new InputListener(inputName));
        edtNumber.addTextChangedListener(new InputListener(inputNumber));
        edtAddress.addTextChangedListener(new InputListener(inputAddress));
        edtStateOrigin.addTextChangedListener(new InputListener(inputStateOrigin));
        edtNationalId.addTextChangedListener(new InputListener(inputNationalId));
        edtOccupation.addTextChangedListener(new InputListener(inputOccupation));
        edtAge.addTextChangedListener(new InputListener(inputAge));

        spinnerEthnic.setOnItemClickListener((materialSpinner, view12, i, l) -> spinnerEthnic.setError(null));

        spinnerMaritalStatus.setOnItemClickListener((materialSpinner, view1, i, l) -> spinnerMaritalStatus.setError(null));

        return view;
    }

    @Nullable
    @Override
    public VerificationError verifyStep() {
                if (isFieldsValidated()){
            dataInteractionListener.onFatherBirthDataPassed(new FatherBirthData(
                    edtName.getText().toString(),
                    edtNumber.getText().toString(),
                    edtAddress.getText().toString(),
                    edtNationalId.getText().toString(),
                    edtAge.getText().toString(),
                    spinnerMaritalStatus.getSelectedItem().toString(),
                    edtStateOrigin.getText().toString(),
                    spinnerEthnic.getSelectedItem().toString(),
                    edtOccupation.getText().toString()
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
        }else if (edtNumber.getText().toString().isEmpty()){
            inputNumber.setError("Please fill this field");
            return false;
        }else if (edtAddress.getText().toString().isEmpty()){
            inputAddress.setError("Please fill this field");
            return false;
        }else if (edtNationalId.getText().toString().isEmpty()){
            inputNationalId.setError("Please fill this field");
            return false;
        }else if (edtAge.getText().toString().isEmpty()){
            inputAge.setError("Please fill this field");
            return false;
        }else if (spinnerMaritalStatus.getSelectedItemId()==MaterialSpinner.INVALID_POSITION){
            spinnerMaritalStatus.setError("Please select an option");
            return false;
        }else if (edtStateOrigin.getText().toString().isEmpty()){
            inputStateOrigin.setError("Please fill this field");
            return false;
        }else if (spinnerEthnic.getSelectedItemId()==MaterialSpinner.INVALID_POSITION){
            spinnerEthnic.setError("Please select an option");
            return false;
        }else if (edtOccupation.getText().toString().isEmpty()){
            inputOccupation.setError("Please fill this field");
            return false;
        }

        return true;
    }
}