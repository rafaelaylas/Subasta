package com.platzi.subasta;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class LoginFragment extends Fragment {
    Button btnLogin, btnRegister;
    EditText etUserName, etPassword;
    CallbackFragment callbackFragment;
    String userName, pass;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    public void onAttach(@NonNull Context context) {
        sharedPreferences = context.getSharedPreferences("userfile", context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login, container, false);
        etUserName = view.findViewById(R.id.etUsername);
        etPassword = view.findViewById(R.id.etPassword);

        btnLogin = view.findViewById(R.id.bLogin);
        btnRegister = view.findViewById(R.id.bRegister);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userName = etUserName.getText().toString();
                pass = etPassword.getText().toString();

                String uName, uPass;
                uName = sharedPreferences.getString("username", null);
                uPass = sharedPreferences.getString("pass", null);

                if(userName.equals(uName) && pass.equals(uPass)){
                    Toast.makeText(getContext(), "Login", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(callbackFragment != null){
                    callbackFragment.changeFragment();
                }

            }
        });


        return view;
    }

    public void setCallbackFragment(CallbackFragment callbackFragment){
        this.callbackFragment = callbackFragment;
    }
}
