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
import com.mypedometer.pip.pedometer.data.storage.LocalManager;
import com.mypedometer.pip.pedometer.data.model.UserModel;

/**
 * This is the class that generates the UI of a user profile when called.
 */
public class ProfileFragment extends Fragment {
    /**
     * Instance of a ProfileFragment used for switching in between the fragments.
     */
    ProfileFragment pf = this;
    private Button edit_profile_button;
    private Button sing_out_button;
    
    public ProfileFragment() {
        // Required empty public constructor
    }
    public ProfileFragment(UserModel userModel) {
        LocalManager.getInstance().updateLocalUser(userModel);
    }

    /**
     * Instance of a UserModel that represents the current user of the application.
     */

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
        textName.setText(LocalManager.getInstance().getLocalUser().getLastName() + " "
                + LocalManager.getInstance().getLocalUser().getFirstName());

        //afisez email
        TextView textEmail= profileView.findViewById(R.id.email);
        textEmail.setText(LocalManager.getInstance().getLocalUser().getEmail());

        //afisez varsta
        TextView textAge = profileView.findViewById(R.id.age);
        textAge.setText(Integer.toString(LocalManager.getInstance().getLocalUser().getVarsta()));

        //afisez gen
        TextView textGender = profileView.findViewById(R.id.gender);
        textGender.setText(LocalManager.getInstance().getLocalUser().getGen());

        //afisez greutate
        TextView textWeight = profileView.findViewById(R.id.weight);
        textWeight.setText(LocalManager.getInstance().getLocalUser().getGreutate() + " kg");

        //afisez inaltime
        TextView textHeight = profileView.findViewById(R.id.height);
        textHeight.setText(LocalManager.getInstance().getLocalUser().getInaltime() + " m");

        //afisez step goal
        TextView textStepGoal = profileView.findViewById(R.id.stepGoal);
        textStepGoal.setText(Integer.toString(LocalManager.getInstance().getLocalUser().getGoalDailySteps()));

        //afisez distance goal
        TextView textDistanceGoal = profileView.findViewById(R.id.distanceGoal);
        textDistanceGoal.setText(Float.toString(LocalManager.getInstance().getLocalUser().getGoalDailyDistance()) + " m");

        //afisez calories goal
        TextView textCaloriesGoal = profileView.findViewById(R.id.caloriesGoal);
        textCaloriesGoal.setText(Float.toString(LocalManager.getInstance().getLocalUser().getGoalDailyCalories()) + " kcal");

        return profileView;
    }
}
