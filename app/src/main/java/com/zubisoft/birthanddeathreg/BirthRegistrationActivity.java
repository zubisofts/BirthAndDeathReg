package com.zubisoft.birthanddeathreg;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;
import com.zubisoft.birthanddeathreg.adapters.BirthRegistrationStepperAdapter;
import com.zubisoft.birthanddeathreg.handlers.DataInteractionListener;
import com.zubisoft.birthanddeathreg.model.ChildBirthData;
import com.zubisoft.birthanddeathreg.model.FatherBirthData;
import com.zubisoft.birthanddeathreg.model.InformantBirthData;
import com.zubisoft.birthanddeathreg.model.MotherBirthData;
import com.zubisoft.birthanddeathreg.ui.birth.ChildParticularsFragment;
import com.zubisoft.birthanddeathreg.ui.birth.FatherParticularsFragment;
import com.zubisoft.birthanddeathreg.ui.birth.InformantParticularsFragment;
import com.zubisoft.birthanddeathreg.ui.birth.MotherParticularsFragment;

public class BirthRegistrationActivity extends AppCompatActivity implements StepperLayout.StepperListener, DataInteractionListener {

    private MaterialButton btnPrevious, btnContinue;
    private StepperLayout stepperLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_birth_registration);

        stepperLayout=stepperLayout=findViewById(R.id.stepperLayout);
        BirthRegistrationStepperAdapter birthRegistrationStepperAdapter=new BirthRegistrationStepperAdapter(getSupportFragmentManager(), this);
        stepperLayout.setListener(this);
        birthRegistrationStepperAdapter.addFragment(new ChildParticularsFragment(this));
        birthRegistrationStepperAdapter.addFragment(new MotherParticularsFragment(this));
        birthRegistrationStepperAdapter.addFragment(new FatherParticularsFragment(this));
        birthRegistrationStepperAdapter.addFragment(new InformantParticularsFragment(this));
        stepperLayout.setAdapter(birthRegistrationStepperAdapter);

        stepperLayout.setShowErrorMessageEnabled(true);
        stepperLayout.setShowErrorStateEnabled(true);

    }

    @Override
    public void onCompleted(View completeButton) {
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
        Toast.makeText(this, childBirthData.getChildName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMotherBirthDataPassed(MotherBirthData motherBirthData) {
        Toast.makeText(this, motherBirthData.getName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFatherBirthDataPassed(FatherBirthData fatherBirthData) {
        Toast.makeText(this, fatherBirthData.getName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onInformantBirthDataPassed(InformantBirthData informantBirthData) {
        Toast.makeText(this, informantBirthData.getName(), Toast.LENGTH_SHORT).show();
    }
}