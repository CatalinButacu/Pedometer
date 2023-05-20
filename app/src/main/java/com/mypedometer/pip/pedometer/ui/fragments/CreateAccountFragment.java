package com.mypedometer.pip.pedometer.ui.fragments;

import static com.mypedometer.pip.pedometer.MainActivity.db;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.UserManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.mypedometer.pip.pedometer.DataBaseHelper;
import com.mypedometer.pip.pedometer.MainActivity;
import com.mypedometer.pip.pedometer.R;
import com.mypedometer.pip.pedometer.data.model.UserModel;

public class CreateAccountFragment extends Fragment {
    CreateAccountFragment caf = this;
    Button createAcc;
    public CreateAccountFragment() {
        // Required empty public constructor
    }
    //----------------------------------------------------------------------------------------------
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View createAccountView = inflater.inflate(R.layout.create_account_layout, container, false);
        createAcc = (Button) createAccountView.findViewById(R.id.create_account_button);
        createAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = new MainActivity();
                mainActivity.changeProfileFragment(caf,new ProfileFragment());
                mainActivity.changeFragment(caf,new ProfileFragment());

                UserModel user = new UserModel();

                //TODO:: DE ADUS INFORMATIILE DIN BUTOANE
                user.setNume("CLIENT1");
                user.setEmail("email@me");
                user.setPhone("0700-000-000");
                user.setPassword("4DM1N!");

                boolean succes = db.insertDataUser(user);

                Toast.makeText(getActivity(), succes?"Account created successfully!":"Your account can't be procced!", Toast.LENGTH_SHORT).show();
            }
        });

        return createAccountView;
        
    }
}

