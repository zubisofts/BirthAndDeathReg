package com.zubisoft.birthanddeathreg.ui.death;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import com.zubisoft.birthanddeathreg.DeathRegistrationActivity;
import com.zubisoft.birthanddeathreg.R;
import com.zubisoft.birthanddeathreg.adapters.BirthListAdapter;
import com.zubisoft.birthanddeathreg.adapters.DeathListAdapter;
import com.zubisoft.birthanddeathreg.model.birthmodels.BirthRegData;
import com.zubisoft.birthanddeathreg.model.deathmodels.DeathRegData;

import java.util.ArrayList;

public class DeathListFragment extends Fragment implements DeathListAdapter.DeathListItemListener {

    private DeathsViewModel deathsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        deathsViewModel =
                new ViewModelProvider(this).get(DeathsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_death_list, container, false);

        RecyclerView deathList=root.findViewById(R.id.deathList);
        deathList.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        DeathListAdapter deathListAdapter=new DeathListAdapter();
        deathListAdapter.setDeathListItemListener(this);
        deathList.setAdapter(deathListAdapter);

        deathsViewModel.fetchDeathRegList();
        deathsViewModel.onDeathRegListResponse().observe(getViewLifecycleOwner(), restData -> {
            if (!restData.hasError()){
                ArrayList<DeathRegData> deathListItems= (ArrayList<DeathRegData>) restData.getData();
                if(deathListItems.isEmpty()){
                    ((TextView)root.findViewById(R.id.txtEmptyRecord)).setVisibility(View.VISIBLE);
                }else{
                    deathListAdapter.setDeathList(deathListItems);
                    ((TextView)root.findViewById(R.id.txtEmptyRecord)).setVisibility(View.GONE);
                }
            }
        });
        return root;
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