package com.mypedometer.pip.pedometer.ui.fragments;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import com.mypedometer.pip.pedometer.MainActivity;
import com.mypedometer.pip.pedometer.R;
import com.mypedometer.pip.pedometer.data.storage.*;
import com.mypedometer.pip.pedometer.ui.widgets.ChallengeItems;

import java.util.ArrayList;
import java.util.List;

public class ChallengeFragment extends Fragment {
    ChallengeFragment cf = this;
    Button createCh;
    List<Challenge> challengeList = new ArrayList<>();

    Challenge ch1 = new Challenge();
    ch1.m_sNameChallenge = "Nume Challenge1";
    //ch1.m_sDecription = "Descriere 1";
    Challenge ch2 = new Challenge();
    ch2.m_sNameChallenge = "Nume Challenge2";
    //ch2.m_sDecription = "Descriere 2";
    Challenge ch3 = new Challenge();
    ch3.m_sNameChallenge = "Nume Challenge3";
    //ch3.m_sDecription = "Descriere 3";

    challengeList.add(ch1);
    challengeList.add(ch2);
    challengeList.add(ch3);

    public ChallengeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View ChallengeView = inflater.inflate(R.layout.challenge_layout, container, false);

        FrameLayout GroupLayout = (FrameLayout) ChallengeView.findViewById(R.id.ch_group_layout);

        for(int i=0;i<challengeList.size();i++){

            //set de date artiicial
            ChallengeItems item = new ChallengeItems("Titlu-test", "Descriere-test",null);

            View view = item.onCreateView(inflater, container, savedInstanceState);

            FrameLayout.LayoutParams layoutParams1 = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
            layoutParams1.gravity = Gravity.TOP; // Adjust the gravity as per your requirements
            GroupLayout.addView(view, layoutParams1);

        }

        ChallengeView.setVisibility(View.VISIBLE);

        container.addView(ChallengeView);

        createCh = (Button) ChallengeView.findViewById(R.id.create_challenge_button);

        /*
        LinearLayout ch1 = (LinearLayout) ChallengeView.findViewById(R.id.challenge1);
        LinearLayout ch2 = (LinearLayout) ChallengeView.findViewById(R.id.challenge2);

        ch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               MainActivity mainActivity = new MainActivity();
               mainActivity.changeFragment(cf,new ViewChallengeFragment());
            }
        });

        ch2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               MainActivity mainActivity = new MainActivity();
               mainActivity.changeFragment(cf,new ViewChallengeFragment());
            }
        });
        */

        createCh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = new MainActivity();
                mainActivity.changeFragment(cf,new CreateChallengeFragment());
            }
        });
        return ChallengeView;
    }
}
