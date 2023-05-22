package com.mypedometer.pip.pedometer.ui.fragments;

import static com.mypedometer.pip.pedometer.MainActivity.db;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mypedometer.pip.pedometer.MainActivity;
import com.mypedometer.pip.pedometer.R;
import com.mypedometer.pip.pedometer.data.model.UserModel;

public class CreateAccountFragment extends Fragment {
    CreateAccountFragment caf = this;

    EditText editTextFirstName;
    EditText editTextLastName;
    EditText editTextEmail;
    EditText editTextPhone;
    EditText editTextPassword;
    Button createAcc;
    public CreateAccountFragment() {
        // Required empty public constructor
    }
    //----------------------------------------------------------------------------------------------
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View createAccountView = inflater.inflate(R.layout.create_account_layout, container, false);

        editTextFirstName = (EditText) createAccountView.findViewById(R.id.inputFirstName);
        editTextLastName = (EditText) createAccountView.findViewById(R.id.inputLastName);
        editTextEmail = (EditText) createAccountView.findViewById(R.id.inputEmail);
        editTextPhone = (EditText) createAccountView.findViewById(R.id.inputPhone);
        editTextPassword = (EditText) createAccountView.findViewById(R.id.inputPassword);

        createAcc = (Button) createAccountView.findViewById(R.id.create_account_button);
        createAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = new MainActivity();
                mainActivity.changeProfileFragment(caf,new ProfileFragment());
                //mainActivity.changeFragment(caf,new ProfileFragment());

                UserModel user = new UserModel();

                //TODO:: DE ADUS INFORMATIILE DIN BUTOANE
                user.setFirstName(editTextFirstName.getText().toString());
                user.setLastName(editTextLastName.getText().toString());
                user.setEmail(editTextEmail.getText().toString());
                user.setPhone(editTextPhone.getText().toString());
                user.setPassword(editTextPassword.getText().toString());

                boolean succes = db.insertDataUser(user);

                if(succes) {
                    MainActivity mainActivity = new MainActivity();
                    mainActivity.changeProfileFragment(caf,new ProfileFragment(user));
                }
                else
                {
                    editTextFirstName.getText().clear();
                    editTextLastName.getText().clear();
                    editTextEmail.getText().clear();
                    editTextPhone.getText().clear();
                    editTextPassword.getText().clear();
                }

                Toast.makeText(getActivity(), succes ? "Account created successfully!":"Your account can't be procced!", Toast.LENGTH_SHORT).show();
            }
        });

        return createAccountView;
        
    }
}

