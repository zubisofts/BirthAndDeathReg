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

import com.zubisoft.birthanddeathreg.R;

public class DeathListFragment extends Fragment {

    private DeathsViewModel deathsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        deathsViewModel =
                new ViewModelProvider(this).get(DeathsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_death_list, container, false);
        final TextView textView = root.findViewById(R.id.text_slideshow);
        deathsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}