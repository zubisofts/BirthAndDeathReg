package com.zubisoft.birthanddeathreg.ui.birth;

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
import com.zubisoft.birthanddeathreg.model.RestData;
import com.zubisoft.birthanddeathreg.model.birthmodels.BirthRegData;

import java.util.ArrayList;

public class BirthListFragment extends Fragment {

    private BirthViewModel birthViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        birthViewModel =
                new ViewModelProvider(this).get(BirthViewModel.class);
        View root = inflater.inflate(R.layout.fragment_birth_list, container, false);

        RecyclerView birthList=root.findViewById(R.id.birthList);
        BirthListAdapter birthListAdapter=new BirthListAdapter();
        birthList.setAdapter(birthListAdapter);

        birthViewModel.fetchBirthRegList();
        birthViewModel.onBirthRegListResponse().observe(getViewLifecycleOwner(), restData -> {
            if (!restData.hasError()){
                ArrayList<BirthRegData> birthListItems= (ArrayList<BirthRegData>) restData.getData();
                birthListAdapter.setBirthList(birthListItems);
            }
        });

        return root;
    }
}