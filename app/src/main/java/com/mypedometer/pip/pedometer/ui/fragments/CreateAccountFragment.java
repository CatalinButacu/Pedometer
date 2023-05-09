package com.mypedometer.pip.pedometer.ui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.mypedometer.pip.pedometer.R;

public class CreateAccountFragment extends Fragment {
    Button createAcc;
    public CreateAccountFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View createAccountView = inflater.inflate(R.layout.create_account_layout, container, false);
        createAcc = (Button) createAccountView.findViewById(R.id.create_account_button);
        createAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAccountView.setVisibility(View.GONE);
                ProfileFragment pf = new ProfileFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.setReorderingAllowed(true);
                transaction.replace(R.id.fragment_container, pf);
                transaction.commit();
            }
        });

        return createAccountView;
    }
}