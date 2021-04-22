package com.zubisoft.birthanddeathreg.adapters;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.stepstone.stepper.Step;
import com.stepstone.stepper.adapter.AbstractFragmentStepAdapter;
import com.stepstone.stepper.viewmodel.StepViewModel;

import java.util.ArrayList;

public class DeathRegistrationStepperAdapter extends AbstractFragmentStepAdapter {

    private final ArrayList<Fragment> fragments=new ArrayList<>();


    public DeathRegistrationStepperAdapter(@NonNull FragmentManager fm, @NonNull Context context) {
        super(fm, context);
    }

    @Override
    public Step createStep(int position) {
        return (Step) fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return "Step "+position;
    }

    @NonNull
    @Override
    public StepViewModel getViewModel(int position) {
        StepViewModel.Builder builder = new StepViewModel.Builder(context)
                .setTitle("Step "+(position+1));
        switch (position) {
            case 0:
                builder
                        .setEndButtonLabel("Informant Info")
                        .setBackButtonLabel("Cancel")
                        .setTitle("Deceased Data")
                        .setBackButtonStartDrawableResId(StepViewModel.NULL_DRAWABLE);
                break;
            case 1:
                builder
                        .setEndButtonLabel("Done")
                        .setBackButtonLabel("Go to Deceased Info")
                        .setTitle("Informant Data");
                break;
            default:
                break;
        }
        return builder.create();
    }

    public void addFragment(Fragment fragment){
        fragments.add(fragment);
    }
}
