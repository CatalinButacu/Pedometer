package com.mypedometer.pip.pedometer.ui.main;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mypedometer.pip.pedometer.R;

public class CreateAccountFragment extends Fragment {

    public CreateAccountFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View createAccountView = inflater.inflate(R.layout.create_account_layout, container, false);
        return createAccountView;
    }
}