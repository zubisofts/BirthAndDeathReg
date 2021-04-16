package com.zubisoft.birthanddeathreg.ui.death;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;
import com.tiper.MaterialSpinner;
import com.zubisoft.birthanddeathreg.R;
import com.zubisoft.birthanddeathreg.handlers.DeathDataInteractionListener;
import com.zubisoft.birthanddeathreg.handlers.InputListener;
import com.zubisoft.birthanddeathreg.model.birthmodels.MotherBirthData;
import com.zubisoft.birthanddeathreg.model.deathmodels.DeceasedData;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class DeceasedParticularsFragment extends Fragment implements Step {
    private TextInputLayout inputName, inputStateOrigin, inputAddress, inputDeathCause, inputOccupation, inputAge;
    private TextInputEditText edtName, edtStateOrigin, edtAddress, edtDeathCause, edtOccupation, edtAge;
    private MaterialSpinner spinnerMaritalStatus, spinnerEthnic,sexSpinner, placeOfDeathSpinner;
    private long date;

    private DeathDataInteractionListener deathDataInteractionListener;

    public DeceasedParticularsFragment(DeathDataInteractionListener deathDataInteractionListener) {
        this.deathDataInteractionListener=deathDataInteractionListener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_deceased_particulars, container, false);

        inputName = view.findViewById(R.id.inputDeaceasedName);
        inputDeathCause = view.findViewById(R.id.inputDeceasedDeathCause);
        inputStateOrigin = view.findViewById(R.id.inputDeaceasedOrigin);
        inputAddress = view.findViewById(R.id.inputDeceasedAddress);
        inputOccupation = view.findViewById(R.id.inputDeaceasedOccupation);
        inputAge = view.findViewById(R.id.inputDeaceasedAge);

        edtName = view.findViewById(R.id.edtDeaceasedName);
        edtDeathCause = view.findViewById(R.id.edtDeceasedDeathCause);
        edtAddress = view.findViewById(R.id.edtDeceasedAddress);
        edtStateOrigin = view.findViewById(R.id.edtDeaceasedOrigin);
        edtOccupation = view.findViewById(R.id.edtDeaceasedOccupation);
        edtAge = view.findViewById(R.id.edtDeaceasedAge);

        spinnerEthnic = view.findViewById(R.id.spinerDeaceasedEthnic);
        spinnerMaritalStatus = view.findViewById(R.id.deaceasedStatusSpinner);
        sexSpinner = view.findViewById(R.id.deathSexSpinner);
        placeOfDeathSpinner = view.findViewById(R.id.deathPlacepinner);

        String[] status = new String[]{"Single", "Married"};
        spinnerMaritalStatus.setAdapter(new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, status));
        String[] ethnics = new String[]{"Ibo", "Yoruba", "Hausa"};
        spinnerEthnic.setAdapter(new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, ethnics));
        String[] sex = new String[]{"Male", "Female"};
        sexSpinner.setAdapter(new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, sex));
        String[] placeOfDeath = new String[]{"Home/Hospital", "Maternity Home", "Traditional"};
        placeOfDeathSpinner.setAdapter(new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, placeOfDeath));

        edtName.addTextChangedListener(new InputListener(inputName));
        edtAddress.addTextChangedListener(new InputListener(inputAddress));
        edtAge.addTextChangedListener(new InputListener(inputAge));
        edtStateOrigin.addTextChangedListener(new InputListener(inputStateOrigin));
        edtDeathCause.addTextChangedListener(new InputListener(inputDeathCause));
        edtOccupation.addTextChangedListener(new InputListener(inputOccupation));
        spinnerMaritalStatus.getEditText().addTextChangedListener(new InputListener(spinnerMaritalStatus));
        sexSpinner.getEditText().addTextChangedListener(new InputListener(sexSpinner));
        placeOfDeathSpinner.getEditText().addTextChangedListener(new InputListener(placeOfDeathSpinner));
        spinnerEthnic.getEditText().addTextChangedListener(new InputListener(spinnerEthnic));

        spinnerEthnic.setOnItemClickListener((materialSpinner, view12, i, l) -> spinnerEthnic.setError(null));
        spinnerMaritalStatus.setOnItemClickListener((materialSpinner, view1, i, l) -> spinnerMaritalStatus.setError(null));
        sexSpinner.setOnItemClickListener((materialSpinner, view12, i, l) -> sexSpinner.setError(null));
        placeOfDeathSpinner.setOnItemClickListener((materialSpinner, view1, i, l) -> placeOfDeathSpinner.setError(null));

        view.findViewById(R.id.btnDateOfBirth).setOnClickListener(v->{
            MaterialDatePicker<Long> datePicker =
                    MaterialDatePicker.Builder.datePicker()
                            .setTitleText("Select date")
                            .setSelection(new Date().getTime())
                            .build();
            datePicker.addOnPositiveButtonClickListener(selection -> {
                date=selection;
                String dateOfBirth=new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(date);
                ((TextView)(view.findViewById(R.id.txtDateOfDeath))).setText(dateOfBirth);

            });
            datePicker.show(getChildFragmentManager(),"date picker");

        });

        return view;
    }

    @Nullable
    @Override
    public VerificationError verifyStep() {
        if (isFieldsValidated()){
            deathDataInteractionListener.onDeceasedDataPassed(new DeceasedData(
                    edtName.getText().toString(),
                    edtAddress.getText().toString(),
                    Double.parseDouble(edtAge.getText().toString()),
                    edtDeathCause.getText().toString(),
                    edtStateOrigin.getText().toString(),
                    edtOccupation.getText().toString(),
                    spinnerMaritalStatus.getSelectedItem().toString(),
                    sexSpinner.getSelectedItem().toString(),
                    placeOfDeathSpinner.getSelectedItem().toString(),
                    spinnerEthnic.getSelectedItem().toString()
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
        }else if (date==0){
            Toast.makeText(getContext(), "Please select the deceased death date ", Toast.LENGTH_SHORT).show();
            return false;
        }else if (sexSpinner.getSelectedItemId()==MaterialSpinner.INVALID_POSITION){
            sexSpinner.setError("Please select an option");
            return false;
        }else if (edtOccupation.getText().toString().isEmpty()){
            inputOccupation.setError("Please fill this field");
            return false;
        }else if (placeOfDeathSpinner.getSelectedItemId()==MaterialSpinner.INVALID_POSITION){
            placeOfDeathSpinner.setError("Please select an option");
            return false;
        }else if (edtAddress.getText().toString().isEmpty()){
            inputAddress.setError("Please fill this field");
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
        }else if (edtDeathCause.getText().toString().isEmpty()){
            inputDeathCause.setError("Please fill this field");
            return false;
        }

        return true;
    }
}