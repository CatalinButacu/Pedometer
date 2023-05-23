package com.mypedometer.pip.pedometer.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.mypedometer.pip.pedometer.R;

/**
 * This is the class that generates the UI of the selected challenge and it's characteristics when called.
 */
public class ViewChallengeFragment extends Fragment {

    public ViewChallengeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.view_challenge_layout, container, false);
        // TODO: initialize views and data
        return view;
    }
}
