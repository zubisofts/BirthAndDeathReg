package com.zubisoft.birthanddeathreg;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;
import com.zubisoft.birthanddeathreg.adapters.DeathRegistrationStepperAdapter;
import com.zubisoft.birthanddeathreg.handlers.DeathDataInteractionListener;
import com.zubisoft.birthanddeathreg.model.RestData;
import com.zubisoft.birthanddeathreg.model.birthmodels.BirthRegData;
import com.zubisoft.birthanddeathreg.model.deathmodels.DeathRegData;
import com.zubisoft.birthanddeathreg.model.deathmodels.DeceasedData;
import com.zubisoft.birthanddeathreg.model.deathmodels.DeceasedInformantData;
import com.zubisoft.birthanddeathreg.ui.birth.BirthViewModel;
import com.zubisoft.birthanddeathreg.ui.death.DeathsViewModel;
import com.zubisoft.birthanddeathreg.ui.death.DeceasedParticularsFragment;
import com.zubisoft.birthanddeathreg.ui.death.InformantParticularsFragment;

public class DeathRegistrationActivity extends AppCompatActivity implements StepperLayout.StepperListener, DeathDataInteractionListener {

    private DeceasedData deceasedData;
    private DeceasedInformantData deceasedInformantData;

    private StepperLayout stepperLayout;
    private DeathsViewModel deathsViewModel;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_death_registration);

        deathsViewModel=new ViewModelProvider.NewInstanceFactory().create(DeathsViewModel.class);
        progressDialog=new ProgressDialog(this);

        stepperLayout = findViewById(R.id.stepperLayout);
        DeathRegistrationStepperAdapter deathRegistrationStepperAdapter = new DeathRegistrationStepperAdapter(getSupportFragmentManager(), this);
        deathRegistrationStepperAdapter.addFragment(new DeceasedParticularsFragment(this));
        deathRegistrationStepperAdapter.addFragment(new InformantParticularsFragment(this));
        stepperLayout.setListener(this);
        stepperLayout.setAdapter(deathRegistrationStepperAdapter);

        stepperLayout.setShowErrorMessageEnabled(true);
        stepperLayout.setShowErrorStateEnabled(true);

        deathsViewModel.getDeathRegResponse().observe(this, restData -> {
            if(restData.hasError()){
                Toast.makeText(DeathRegistrationActivity.this, restData.getData().toString(), Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(DeathRegistrationActivity.this, "Registration uploaded successfully", Toast.LENGTH_SHORT).show();
                finish();
            }

            hideLoadingDialog();
        });
    }

    private void showLoadingDialog() {
        progressDialog.setMessage("Saving registration details...");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
    }

    private void hideLoadingDialog() {
        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void onCompleted(View completeButton) {
        saveDeathDetails();
    }

    @Override
    public void onError(VerificationError verificationError) {
        stepperLayout.updateErrorState(verificationError);
    }

    @Override
    public void onStepSelected(int newStepPosition) {

    }

    @Override
    public void onReturn() {

    }

    @Override
    public void onDeceasedDataPassed(DeceasedData deceasedData) {
        this.deceasedData = deceasedData;
    }

    @Override
    public void onDeceasedInformantDataPassed(DeceasedInformantData deceasedInformantData) {
        this.deceasedInformantData = deceasedInformantData;
    }

    private void saveDeathDetails() {

        showLoadingDialog();
        deathsViewModel.savDeathInfo(new DeathRegData(
                deceasedData,
                deceasedInformantData
        ));

    }
}