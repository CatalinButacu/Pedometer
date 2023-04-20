package com.mypedometer.pip.pedometer.ui.main;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.mypedometer.pip.pedometer.R;

public class LoginFragment extends Fragment {
    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View LoginView = inflater.inflate(R.layout.login_layout, container, false);

        Button loginButton = (Button)LoginView.findViewById(R.id.login_button);
        //de implementat actionListener la butoanele de login si sing up
        return LoginView;
    }


}