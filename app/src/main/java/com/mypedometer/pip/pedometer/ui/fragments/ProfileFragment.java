package com.mypedometer.pip.pedometer.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.mypedometer.pip.pedometer.MainActivity;
import com.mypedometer.pip.pedometer.R;
import com.mypedometer.pip.pedometer.data.model.UserModel;

import java.text.DecimalFormat;

public class ProfileFragment extends Fragment {
    ProfileFragment pf = this;
    private Button edit_profile_button;
    private Button sing_out_button;
    
    public ProfileFragment() {
        // Required empty public constructor
    }
    public ProfileFragment(UserModel userModel) {
        user = userModel;
    }
    static UserModel user;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View profileView = inflater.inflate(R.layout.profile_layout, container, false);
        edit_profile_button = (Button) profileView.findViewById(R.id.edit_profile_button);

        edit_profile_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = new MainActivity();
                mainActivity.changeProfileFragment(pf,new EditProfileFragment());
            }
        });

        sing_out_button = (Button) profileView.findViewById(R.id.sign_out_button);

        sing_out_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = new MainActivity();
                mainActivity.changeProfileFragment(pf,new LoginFragment());
            }
        });

        //afisez nume
        TextView textName = profileView.findViewById(R.id.name);
        textName.setText(user.getNume() + " " + user.getPrenume());

        //afisez email
        TextView textEmail= profileView.findViewById(R.id.email);
        textEmail.setText(user.getEmail());

        //afisez varsta
        TextView textAge = profileView.findViewById(R.id.age);
        textAge.setText(Integer.toString(user.getVarsta()));

        //afisez gen
        TextView textGender = profileView.findViewById(R.id.gender);
        textGender.setText(user.getGen());

        //afisez greutate
        TextView textWeight = profileView.findViewById(R.id.weight);
        textWeight.setText(user.getGreutate() + " kg");

        //afisez inaltime
        TextView textHeight = profileView.findViewById(R.id.height);
        textHeight.setText(user.getInaltime() + " m");

        //afisez lungimea pasului
        TextView textStepLength = profileView.findViewById(R.id.stepLength);
        DecimalFormat df = new DecimalFormat("##.##");
        String stepL = df.format(0.415 * user.getInaltime());
        textStepLength.setText(stepL + " cm");

        //afisez step goal
        TextView textStepGoal = profileView.findViewById(R.id.stepGoal);
        textStepGoal.setText(Integer.toString(user.getGoalDailySteps()));

        return profileView;
    }
}
