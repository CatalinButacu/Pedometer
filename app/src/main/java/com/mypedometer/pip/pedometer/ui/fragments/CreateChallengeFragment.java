package com.mypedometer.pip.pedometer.ui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mypedometer.pip.pedometer.R;

public class CreateChallengeFragment extends Fragment {
    
    public CreateChallengeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View createChallengeView = inflater.inflate(R.layout.create_challenge_layout, container, false);
        return createChallengeView;
    }
}
