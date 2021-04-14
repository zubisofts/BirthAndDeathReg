package com.zubisoft.birthanddeathreg.handlers;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;

import com.google.android.material.textfield.TextInputLayout;

public class InputListener implements TextWatcher {

    private TextInputLayout inputLayout;

    public InputListener(TextInputLayout inputLayout) {
        this.inputLayout = inputLayout;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (!TextUtils.isEmpty(s)) {
            inputLayout.setError(null);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}