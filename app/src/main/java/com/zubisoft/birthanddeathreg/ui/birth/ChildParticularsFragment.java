package com.zubisoft.birthanddeathreg.ui.birth;

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
import com.zubisoft.birthanddeathreg.handlers.BirthDataInteractionListener;
import com.zubisoft.birthanddeathreg.handlers.InputListener;
import com.zubisoft.birthanddeathreg.model.birthmodels.ChildBirthData;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ChildParticularsFragment extends Fragment implements Step {

    private MaterialSpinner sexSpinner, inputOccurrence, inputTypeOfBirth;
    private TextInputLayout inputName, inputPlaceOfBirth;
    private TextInputEditText edtName, edtPlaceOfBirth;
    private long date;

    private BirthDataInteractionListener birthDataInteractionListener;


    public ChildParticularsFragment(BirthDataInteractionListener birthDataInteractionListener) {
        this.birthDataInteractionListener = birthDataInteractionListener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_child_particulars, container, false);

        inputName = view.findViewById(R.id.inputName);
        inputPlaceOfBirth = view.findViewById(R.id.inputPlaceOfBirth);
        inputOccurrence = view.findViewById(R.id.inputOccurrence);
        inputTypeOfBirth = view.findViewById(R.id.birthType);

        edtName = view.findViewById(R.id.edtName);
        edtPlaceOfBirth = view.findViewById(R.id.edtPlace);

        sexSpinner = view.findViewById(R.id.sexSpinner);
        String[] sexes = new String[]{"Male", "Female"};
        String[] birthTypes = new String[]{"Single", "Multiple"};
        inputTypeOfBirth.setAdapter(new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, birthTypes));
        String[] birthOccurrence = new String[]{"Maternity Home", "Hospital", "At Home", "Traditional Doctor's place"};
        inputOccurrence.setAdapter(new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, birthOccurrence));
        sexSpinner.setAdapter(new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, sexes));

        view.findViewById(R.id.btnDateOfBirth).setOnClickListener(v -> {
            MaterialDatePicker<Long> datePicker =
                    MaterialDatePicker.Builder.datePicker()
                            .setTitleText("Select date")
                            .setSelection(new Date().getTime())
                            .build();
            datePicker.addOnPositiveButtonClickListener(selection -> {
                date=selection;
                String dateOfBirth=new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(date);
                ((TextView)(view.findViewById(R.id.txtDateOfBirth))).setText(dateOfBirth);

            });
            datePicker.show(getChildFragmentManager(),"date picker");

        });

        edtName.addTextChangedListener(new InputListener(inputName));
        edtPlaceOfBirth.addTextChangedListener(new InputListener(inputPlaceOfBirth));
        sexSpinner.getEditText().addTextChangedListener(new InputListener(inputPlaceOfBirth));

        sexSpinner.setOnItemClickListener(new MaterialSpinner.OnItemClickListener() {
            @Override
            public void onItemClick(@NotNull MaterialSpinner materialSpinner, @org.jetbrains.annotations.Nullable View view, int i, long l) {
                sexSpinner.setError(null);
            }
        });

        inputOccurrence.setOnItemClickListener(new MaterialSpinner.OnItemClickListener() {
            @Override
            public void onItemClick(@NotNull MaterialSpinner materialSpinner, @org.jetbrains.annotations.Nullable View view, int i, long l) {
                inputOccurrence.setError(null);
            }
        });

        inputTypeOfBirth.setOnItemClickListener(new MaterialSpinner.OnItemClickListener() {
            @Override
            public void onItemClick(@NotNull MaterialSpinner materialSpinner, @org.jetbrains.annotations.Nullable View view, int i, long l) {
                inputTypeOfBirth.setError(null);
            }
        });

        return view;
    }

    @Nullable
    @Override
    public VerificationError verifyStep() {
        if (isFieldsValidated()){
            birthDataInteractionListener.onChildBirthDataPassed(new ChildBirthData(
                    edtName.getText().toString(),
                    0L,
                    sexSpinner.getSelectedItem().toString(),
                    edtPlaceOfBirth.getText().toString(),
                    inputOccurrence.getSelectedItem().toString(),
                    inputTypeOfBirth.getSelectedItem().toString()
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
//        Toast.makeText(getContext(), error.getErrorMessage(), Toast.LENGTH_SHORT).show();
    }

    public boolean isFieldsValidated(){
        if(edtName.getText().toString().isEmpty()){
            inputName.setError("Please fill this field");
            return false;
        }else if (date==0){
            Toast.makeText(getContext(), "Please select date of birth", Toast.LENGTH_SHORT).show();
            return false;
        }else if (sexSpinner.getSelectedItemId()==MaterialSpinner.INVALID_POSITION){
            sexSpinner.setError("Please select an option");
            return false;
        }else if (inputOccurrence.getSelectedItemId()==MaterialSpinner.INVALID_POSITION){
            inputOccurrence.setError("Please select an option");
            return false;
        }else if(edtPlaceOfBirth.getText().toString().isEmpty()){
            inputPlaceOfBirth.setError("Please fill this field");
            return false;
        }else if (inputTypeOfBirth.getSelectedItemId()==MaterialSpinner.INVALID_POSITION){
            inputTypeOfBirth.setError("Please select an option");
            return false;
        }

        return true;
    }
}