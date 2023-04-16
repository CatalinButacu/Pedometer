package com.mypedometer.pip.pedometer.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.mypedometer.pip.pedometer.R;

public class ChallengeFragment extends Fragment {

    public ChallengeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.challenge_layout, container, false);
        // TODO: initialize views and data
        return view;
    }
}
