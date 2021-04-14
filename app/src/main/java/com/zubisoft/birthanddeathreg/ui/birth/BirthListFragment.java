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

import com.zubisoft.birthanddeathreg.R;

public class BirthListFragment extends Fragment {

    private BirthViewModel birthViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        birthViewModel =
                new ViewModelProvider(this).get(BirthViewModel.class);
        View root = inflater.inflate(R.layout.fragment_birth_list, container, false);
        final TextView textView = root.findViewById(R.id.text_gallery);
        birthViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}