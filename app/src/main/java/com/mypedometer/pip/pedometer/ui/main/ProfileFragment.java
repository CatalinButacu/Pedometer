package com.mypedometer.pip.pedometer.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.mypedometer.pip.pedometer.R;

import java.text.DecimalFormat;

public class ProfileFragment extends Fragment {

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View profileView = inflater.inflate(R.layout.profile_layout, container, false);

        final String NAME = "Popescu Ion";
        final String EMAIL = "popescu.ion@gmail.com";
        final int AGE = 27;
        final String GENDER = "Male";
        final double WEIGHT = 72.6;
        final int HEIGHT = 186;
        final double STEPLENGTH = 0.4 * HEIGHT;
        final int STEPGOAL = 7000;

        //afisez nume
        TextView textName = profileView.findViewById(R.id.name);
        textName.setText(NAME);

        //afisez email
        TextView textEmail= profileView.findViewById(R.id.email);
        textEmail.setText(EMAIL);

        //afisez varsta
        TextView textAge = profileView.findViewById(R.id.age);
        textAge.setText(Integer.toString(AGE));

        //afisez gen
        TextView textGender = profileView.findViewById(R.id.gender);
        textGender.setText(GENDER);

        //afisez greutate
        TextView textWeight = profileView.findViewById(R.id.weight);
        textWeight.setText(Double.toString(WEIGHT) + " kg");

        //afisez inaltime
        TextView textHeight = profileView.findViewById(R.id.height);
        textHeight.setText(Integer.toString(HEIGHT) + " cm");

        //afisez lungimea pasului
        TextView textStepLength = profileView.findViewById(R.id.stepLength);
        DecimalFormat df = new DecimalFormat("##.##");
        String stepL = df.format(STEPLENGTH);
        textStepLength.setText(stepL + " cm");

        //afisez step goal
        TextView textStepGoal = profileView.findViewById(R.id.stepGoal);
        textStepGoal.setText(Integer.toString(STEPGOAL));

        return profileView;
    }
}
