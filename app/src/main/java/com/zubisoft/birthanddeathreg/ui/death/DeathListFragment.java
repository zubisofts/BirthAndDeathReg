package com.zubisoft.birthanddeathreg.ui.death;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.zubisoft.birthanddeathreg.R;
import com.zubisoft.birthanddeathreg.adapters.BirthListAdapter;
import com.zubisoft.birthanddeathreg.adapters.DeathListAdapter;
import com.zubisoft.birthanddeathreg.model.birthmodels.BirthRegData;
import com.zubisoft.birthanddeathreg.model.deathmodels.DeathRegData;

import java.util.ArrayList;

public class DeathListFragment extends Fragment {

    private DeathsViewModel deathsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        deathsViewModel =
                new ViewModelProvider(this).get(DeathsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_death_list, container, false);

        RecyclerView deathList=root.findViewById(R.id.deathList);
        DeathListAdapter deathListAdapter=new DeathListAdapter();
        deathList.setAdapter(deathListAdapter);

        deathsViewModel.fetchDeathRegList();
        deathsViewModel.onDeathRegListResponse().observe(getViewLifecycleOwner(), restData -> {
            if (!restData.hasError()){
                ArrayList<DeathRegData> deathListItems= (ArrayList<DeathRegData>) restData.getData();
                deathListAdapter.setDeathList(deathListItems);
            }
        });
        return root;
    }
}