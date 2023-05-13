package com.mypedometer.pip.pedometer.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.mypedometer.pip.pedometer.R;
import com.mypedometer.pip.pedometer.data.storage.Challenge;
import com.mypedometer.pip.pedometer.data.storage.User;

public class EditProfileFragment extends Fragment {

    private EditText edit_Nume;
    private EditText edit_Prenume;
    private EditText edit_Varsta;
    private EditText edit_Inaltime;
    private EditText edit_Greutate;
    private EditText edit_Email;
    private EditText edit_Phone;

    public EditProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View editProfileView = inflater.inflate(R.layout.edit_profile_layout, container, false);

        edit_Nume = (EditText) editProfileView.findViewById(R.id.editNume);
        edit_Prenume = (EditText) editProfileView.findViewById(R.id.editPrenume);
        edit_Varsta = (EditText) editProfileView.findViewById(R.id.editVarsta);
        edit_Inaltime = (EditText) editProfileView.findViewById(R.id.editInaltime);
        edit_Greutate = (EditText) editProfileView.findViewById(R.id.editGreutate);
        edit_Email = (EditText) editProfileView.findViewById(R.id.editEmail);
        edit_Phone = (EditText) editProfileView.findViewById(R.id.editPhone);
/*
        edit_profile_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean valid = true;
                String nume = "";
                String prenume = "";
                String varsta = "";
                String inaltime = "";
                String greutate = "";
                String email = "";
                String phone = "";

                //TODO:: DE IMPLEMENTAT VALIDAREA CU BAZA DE DATE
                try {
                    nume = edit_Nume.getText().toString();
                    prenume = edit_Prenume.getText().toString();
                    varsta = edit_Varsta.getText().toString();
                    inaltime = edit_Inaltime.getText().toString();
                    greutate = edit_Greutate.getText().toString();
                    email = edit_Email.getText().toString();
                    phone = edit_Phone.getText().toString();


                    if (!nume.isEmpty())  //revin aici
                    {
                        valid = false;
                    }
                } catch (Exception e)
                {
                    valid = false;
                }

                if (valid)
                {
                    //update profile
                }
            }
        });*/
        return editProfileView;
    }
    //public User updateUser(){
        //creaza un obiect de tipul challenge
        //extragerea inputului din UI
        //atribuie campurilor obiectului inputul
        //returneaza obiectul
    //}
}