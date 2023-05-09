package com.mypedometer.pip.pedometer.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.mypedometer.pip.pedometer.R;

public class ChallengeFragment extends Fragment {
    Button createCh;
    public ChallengeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View ChallengeView = inflater.inflate(R.layout.challenge_layout, container, false);

        createCh = (Button) ChallengeView.findViewById(R.id.create_challenge_button);
        LinearLayout ch1 = (LinearLayout) ChallengeView.findViewById(R.id.challenge1);
        LinearLayout ch2 = (LinearLayout) ChallengeView.findViewById(R.id.challenge2);
        ch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChallengeView.setVisibility(View.GONE);
                ViewChallengeFragment vcf = new ViewChallengeFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.setReorderingAllowed(true);
                transaction.replace(R.id.fragment_container, vcf);
                transaction.commit();
            }
        });

        ch2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChallengeView.setVisibility(View.GONE);
                ViewChallengeFragment vcf = new ViewChallengeFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.setReorderingAllowed(true);
                transaction.replace(R.id.fragment_container, vcf);
                transaction.commit();
            }
        });

        createCh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChallengeView.setVisibility(View.GONE);
                CreateChallengeFragment ccf = new CreateChallengeFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.setReorderingAllowed(true);
                transaction.replace(R.id.fragment_container, ccf);
                transaction.commit();
            }
        });
        return ChallengeView;
    }
}
