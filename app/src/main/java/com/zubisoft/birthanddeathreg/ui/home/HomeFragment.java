package com.zubisoft.birthanddeathreg.ui.home;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import com.zubisoft.birthanddeathreg.BirthRegistrationActivity;
import com.zubisoft.birthanddeathreg.DeathRegistrationActivity;
import com.zubisoft.birthanddeathreg.R;
import com.zubisoft.birthanddeathreg.adapters.BirthListAdapter;
import com.zubisoft.birthanddeathreg.adapters.DeathListAdapter;
import com.zubisoft.birthanddeathreg.model.birthmodels.BirthRegData;
import com.zubisoft.birthanddeathreg.model.deathmodels.DeathRegData;
import com.zubisoft.birthanddeathreg.ui.birth.BirthViewModel;
import com.zubisoft.birthanddeathreg.ui.death.DeathsViewModel;

import java.text.MessageFormat;
import java.util.ArrayList;

public class HomeFragment extends Fragment implements BirthListAdapter.BirthItemListener, DeathListAdapter.DeathListItemListener {

    private BirthViewModel birthViewModel;
    private DeathsViewModel deathsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        birthViewModel =
                new ViewModelProvider(this).get(BirthViewModel.class);
        deathsViewModel =
                new ViewModelProvider(this).get(DeathsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

//        For recent birth registration list
        RecyclerView listRecentBirthReg=root.findViewById(R.id.listRecentBirthReg);
        listRecentBirthReg.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        BirthListAdapter birthListAdapter=new BirthListAdapter();
        birthListAdapter.setBirthItemListener(this);
        listRecentBirthReg.setAdapter(birthListAdapter);

//        For recent death registration list
        RecyclerView listRecentDeathReg=root.findViewById(R.id.listRecentDeathReg);
        listRecentDeathReg.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        DeathListAdapter deathListAdapter=new DeathListAdapter();
        deathListAdapter.setDeathListItemListener(this);
        listRecentDeathReg.setAdapter(deathListAdapter);

        birthViewModel.fetchBirthRegList();
        birthViewModel.onBirthRegListResponse().observe(getViewLifecycleOwner(), restData -> {
            if (!restData.hasError()){
                ArrayList<BirthRegData> birthRegDataArrayList= (ArrayList<BirthRegData>) restData.getData();
                if (birthRegDataArrayList.isEmpty()){
                    root.findViewById(R.id.emptyLayout).setVisibility(View.VISIBLE);
                    listRecentBirthReg.setVisibility(View.GONE);
                }else {
                    root.findViewById(R.id.emptyLayout).setVisibility(View.GONE);
                    listRecentBirthReg.setVisibility(View.VISIBLE);
                    if (birthRegDataArrayList.size()>3){
                        birthListAdapter.setBirthList(new ArrayList<>(birthRegDataArrayList.subList(0,3)));
                    }else {
                        birthListAdapter.setBirthList(birthRegDataArrayList);
                    }
                }
                ((TextView)root.findViewById(R.id.txtTotalBirths)).setText(MessageFormat.format("{0}", birthRegDataArrayList.size()));
            }
        });

        deathsViewModel.fetchDeathRegList();
        deathsViewModel.onDeathRegListResponse().observe(getViewLifecycleOwner(), restData -> {
            if (!restData.hasError()){
                ArrayList<DeathRegData> deathRegDataArrayList= (ArrayList<DeathRegData>) restData.getData();
                if(deathRegDataArrayList.isEmpty()){
                    root.findViewById(R.id.emptyLayout2).setVisibility(View.VISIBLE);
                    listRecentDeathReg.setVisibility(View.GONE);
                }else{
                    root.findViewById(R.id.emptyLayout2).setVisibility(View.GONE);
                    listRecentDeathReg.setVisibility(View.VISIBLE);
                    if (deathRegDataArrayList.size()>3) {
                        deathListAdapter.setDeathList(new ArrayList<>(deathRegDataArrayList.subList(0,3)));
                    }else{
                        deathListAdapter.setDeathList(deathRegDataArrayList);
                    }
                }
                ((TextView)root.findViewById(R.id.txtTotalDeaths)).setText(MessageFormat.format("{0}", deathRegDataArrayList.size()));
            }
        });

        root.findViewById(R.id.btnAddBirth).setOnClickListener(view->{
            startActivity(new Intent(getActivity(), BirthRegistrationActivity.class));
        });
        root.findViewById(R.id.btnAddDeath).setOnClickListener(view->{
            startActivity(new Intent(getActivity(), DeathRegistrationActivity.class));
        });


        return root;
    }

    @Override
    public void onBirthItemClicked(BirthRegData birthRegData) {

    }

    @Override
    public void onOptionItemClicked(BirthRegData birthRegData, ImageView view) {
        PopupMenu popupMenu=new PopupMenu(getContext(), view);
        popupMenu.getMenu().add("Edit");
        popupMenu.getMenu().add("Delete");
        popupMenu.setOnMenuItemClickListener(item -> {
            if(item.getTitle().equals("Edit")){
                Intent intent=new Intent(getContext(), BirthRegistrationActivity.class);
                intent.putExtra("type", "edit");
                intent.putExtra("data", birthRegData);
                startActivity(intent);
                return true;
            }else if(item.getTitle().equals("Delete")){
                new AlertDialog.Builder(getContext())
                        .setMessage("Do you want to delete this record")
                        .setPositiveButton("Delete", (dialog, which) -> {
                            birthViewModel.deleteBirthRecord(birthRegData.getId());
                        }).setNegativeButton("Cancel",(dialog, which)->{
                    dialog.dismiss();
                }).show();

                return true;
            }else {
                return false;
            }

        });

        popupMenu.show();
    }

    @Override
    public void onItemClicked(DeathRegData deathRegData) {

    }

    @Override
    public void onOptionsItemClicked(DeathRegData deathRegData, View view) {
        PopupMenu popupMenu=new PopupMenu(getContext(), view);
        popupMenu.getMenu().add("Edit");
        popupMenu.getMenu().add("Delete");
        popupMenu.setOnMenuItemClickListener(item -> {
            if(item.getTitle().equals("Edit")){
                Intent intent=new Intent(getContext(), DeathRegistrationActivity.class);
                intent.putExtra("type", "edit");
                intent.putExtra("data", deathRegData);
                startActivity(intent);
                return true;
            }else if(item.getTitle().equals("Delete")){
                new AlertDialog.Builder(getContext())
                        .setMessage("Do you want to delete this record")
                        .setPositiveButton("Delete", (dialog, which) -> {
                            deathsViewModel.deleteDeathRecord(deathRegData.getId());
                        }).setNegativeButton("Cancel",(dialog, which)->{
                    dialog.dismiss();
                }).show();
                return true;
            }else {
                return false;
            }

        });

        popupMenu.show();
    }
}