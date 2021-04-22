package com.zubisoft.birthanddeathreg.ui.birth;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import com.zubisoft.birthanddeathreg.BirthRegistrationActivity;
import com.zubisoft.birthanddeathreg.DeathRegistrationActivity;
import com.zubisoft.birthanddeathreg.R;
import com.zubisoft.birthanddeathreg.adapters.BirthListAdapter;
import com.zubisoft.birthanddeathreg.model.RestData;
import com.zubisoft.birthanddeathreg.model.birthmodels.BirthRegData;

import java.util.ArrayList;

public class BirthListFragment extends Fragment implements BirthListAdapter.BirthItemListener {

    private BirthViewModel birthViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        birthViewModel =
                new ViewModelProvider(this).get(BirthViewModel.class);
        View root = inflater.inflate(R.layout.fragment_birth_list, container, false);

        RecyclerView birthList=root.findViewById(R.id.birthList);
        birthList.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        BirthListAdapter birthListAdapter=new BirthListAdapter();
        birthListAdapter.setBirthItemListener(this);
        birthList.setAdapter(birthListAdapter);

        birthViewModel.fetchBirthRegList();
        birthViewModel.onBirthRegListResponse().observe(getViewLifecycleOwner(), restData -> {
            if (!restData.hasError()){
                ArrayList<BirthRegData> birthListItems= (ArrayList<BirthRegData>) restData.getData();
                if(birthListItems.isEmpty()){
                    ((TextView)root.findViewById(R.id.txtEmptyRecord)).setVisibility(View.VISIBLE);
                }else{
                    birthListAdapter.setBirthList(birthListItems);
                    ((TextView)root.findViewById(R.id.txtEmptyRecord)).setVisibility(View.GONE);
                }

            }
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
}