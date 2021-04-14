package com.zubisoft.birthanddeathreg;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.PatternsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.zubisoft.birthanddeathreg.handlers.InputListener;
import com.zubisoft.birthanddeathreg.model.RestData;
import com.zubisoft.birthanddeathreg.viewmodel.AuthViewModel;

public class LoginActivity extends AppCompatActivity {

    private AuthViewModel authViewModel;

    private TextInputEditText edtEmail, edtPassword;
    private TextInputLayout inputEmail, inputPassword;
    private ProgressDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        authViewModel= new ViewModelProvider.NewInstanceFactory().create(AuthViewModel.class);

        dialog=new ProgressDialog(this);

        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);

        inputEmail = findViewById(R.id.inputEmail);
        inputPassword = findViewById(R.id.inputPassword);

        setInputListeners();

        findViewById(R.id.txtCreateAccount).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        findViewById(R.id.btnLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tryLoginUser();
            }
        });

        authViewModel.authRes().observe(this, new Observer<RestData>() {
            @Override
            public void onChanged(RestData restData) {
                if(!restData.hasError()){
//                    User user= (User) restData.getData();
                    Intent intent=new Intent(LoginActivity.this, MainActivity.class);
//                    intent.putExtra("user",user);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(LoginActivity.this, restData.getData().toString(), Toast.LENGTH_SHORT).show();
                }
                hideLoadingDialog();
            }
        });
    }

    private void tryLoginUser() {

        if (TextUtils.isEmpty(edtEmail.getText().toString()) || !PatternsCompat.EMAIL_ADDRESS.matcher(edtEmail.getText().toString()).matches()) {
            inputEmail.setError("Invalid email address️");
        } else if (TextUtils.isEmpty(edtPassword.getText().toString())) {
            inputPassword.setError("Password must not be empty");
        }else{
            showLoadingDialog();
            authViewModel.loginUser(
                    edtEmail.getText().toString(), edtPassword.getText().toString());
        }

    }

    private void setInputListeners() {
        edtEmail.addTextChangedListener(new InputListener(inputEmail));
        edtPassword.addTextChangedListener(new InputListener(inputPassword));
    }



    private void showLoadingDialog(){
        dialog.setMessage("Signing in...");
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    private void hideLoadingDialog(){
        if(dialog.isShowing()){
            dialog.dismiss();
        }

    }
}