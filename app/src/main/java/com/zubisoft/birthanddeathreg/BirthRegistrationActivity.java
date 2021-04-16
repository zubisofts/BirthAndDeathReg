package com.zubisoft.birthanddeathreg;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;
import com.zubisoft.birthanddeathreg.adapters.BirthRegistrationStepperAdapter;
import com.zubisoft.birthanddeathreg.handlers.BirthDataInteractionListener;
import com.zubisoft.birthanddeathreg.model.RestData;
import com.zubisoft.birthanddeathreg.model.birthmodels.BirthRegData;
import com.zubisoft.birthanddeathreg.model.birthmodels.ChildBirthData;
import com.zubisoft.birthanddeathreg.model.birthmodels.FatherBirthData;
import com.zubisoft.birthanddeathreg.model.birthmodels.InformantBirthData;
import com.zubisoft.birthanddeathreg.model.birthmodels.MotherBirthData;
import com.zubisoft.birthanddeathreg.ui.birth.BirthViewModel;
import com.zubisoft.birthanddeathreg.ui.birth.ChildParticularsFragment;
import com.zubisoft.birthanddeathreg.ui.birth.FatherParticularsFragment;
import com.zubisoft.birthanddeathreg.ui.birth.InformantParticularsFragment;
import com.zubisoft.birthanddeathreg.ui.birth.MotherParticularsFragment;

public class BirthRegistrationActivity extends AppCompatActivity implements StepperLayout.StepperListener, BirthDataInteractionListener {

    private StepperLayout stepperLayout;
    
    private ChildBirthData childBirthData;
    private MotherBirthData motherBirthData;
    private FatherBirthData fatherBirthData;
    private InformantBirthData informantBirthData;

    private BirthViewModel birthViewModel;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_birth_registration);

        birthViewModel=new ViewModelProvider.NewInstanceFactory().create(BirthViewModel.class);
        progressDialog=new ProgressDialog(this);

        stepperLayout=findViewById(R.id.stepperLayout);
        BirthRegistrationStepperAdapter birthRegistrationStepperAdapter=new BirthRegistrationStepperAdapter(getSupportFragmentManager(), this);
        stepperLayout.setListener(this);
        birthRegistrationStepperAdapter.addFragment(new ChildParticularsFragment(this));
        birthRegistrationStepperAdapter.addFragment(new MotherParticularsFragment(this));
        birthRegistrationStepperAdapter.addFragment(new FatherParticularsFragment(this));
        birthRegistrationStepperAdapter.addFragment(new InformantParticularsFragment(this));
        stepperLayout.setAdapter(birthRegistrationStepperAdapter);

        stepperLayout.setShowErrorMessageEnabled(true);
        stepperLayout.setShowErrorStateEnabled(true);

        birthViewModel.getBirthRegResponse().observe(this, new Observer<RestData>() {
            @Override
            public void onChanged(RestData restData) {
                if(restData.hasError()){
                    Toast.makeText(BirthRegistrationActivity.this, restData.getData().toString(), Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(BirthRegistrationActivity.this, "Registration uploaded successfully", Toast.LENGTH_SHORT).show();
                    finish();
                }

                hideLoadingDialog();
            }
        });

    }

    private void showLoadingDialog(){
        progressDialog.setMessage("Saving registration details...");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
    }

    private void hideLoadingDialog(){
        if(progressDialog.isShowing()){
            progressDialog.dismiss();
        }
    }

    @Override
    public void onCompleted(View completeButton) {
        saveBirthDetails();
    }

    private void saveBirthDetails() {

        showLoadingDialog();
        birthViewModel.saveBirthInfo(new BirthRegData(
                childBirthData,
                motherBirthData,
                fatherBirthData,
                informantBirthData
        ));

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
    public void onChildBirthDataPassed(ChildBirthData childBirthData) {
        this.childBirthData=childBirthData;
    }

    @Override
    public void onMotherBirthDataPassed(MotherBirthData motherBirthData) {
        this.motherBirthData=motherBirthData;
    }

    @Override
    public void onFatherBirthDataPassed(FatherBirthData fatherBirthData) {
        this.fatherBirthData=fatherBirthData;
    }

    @Override
    public void onInformantBirthDataPassed(InformantBirthData informantBirthData) {
        this.informantBirthData=informantBirthData;
    }
}