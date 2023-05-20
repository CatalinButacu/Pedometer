package com.mypedometer.pip.pedometer.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.mypedometer.pip.pedometer.MainActivity;
import com.mypedometer.pip.pedometer.R;

public class ChallengeFragment extends Fragment {
    ChallengeFragment cf = this;
    Button createCh;
    public ChallengeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View ChallengeView = inflater.inflate(R.layout.challenge_layout, container, false);

    /*
        ListView GroupLayout = (ListView) ChallengeView.findViewById(R.id.ch_group_layout);

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
    */
        createCh = (Button) ChallengeView.findViewById(R.id.create_challenge_button);
        LinearLayout ch1 = (LinearLayout) ChallengeView.findViewById(R.id.challenge1);
        LinearLayout ch2 = (LinearLayout) ChallengeView.findViewById(R.id.challenge2);
        ch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               MainActivity mainActivity = new MainActivity();
               mainActivity.changeChallengeFragment(cf,new ViewChallengeFragment());
            }
        });

        ch2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               MainActivity mainActivity = new MainActivity();
               mainActivity.changeChallengeFragment(cf,new ViewChallengeFragment());
            }
        });

        createCh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = new MainActivity();
                mainActivity.changeChallengeFragment(cf,new CreateChallengeFragment());
            }
        });
        return ChallengeView;
    }
}
