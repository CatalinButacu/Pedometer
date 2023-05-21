package com.mypedometer.pip.pedometer.ui.fragments;

import static com.mypedometer.pip.pedometer.ui.fragments.ProfileFragment.user;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.mypedometer.pip.pedometer.MainActivity;
import com.mypedometer.pip.pedometer.R;

public class EditProfileFragment extends Fragment {
    EditProfileFragment epf = this;
    private Button update_profile;
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

        update_profile = (Button) editProfileView.findViewById(R.id.editProfileButton);

        edit_Nume = (EditText) editProfileView.findViewById(R.id.editNume);
        edit_Prenume = (EditText) editProfileView.findViewById(R.id.editPrenume);
        edit_Varsta = (EditText) editProfileView.findViewById(R.id.editVarsta);
        edit_Inaltime = (EditText) editProfileView.findViewById(R.id.editInaltime);
        edit_Greutate = (EditText) editProfileView.findViewById(R.id.editGreutate);
        edit_Email = (EditText) editProfileView.findViewById(R.id.editEmail);
        edit_Phone = (EditText) editProfileView.findViewById(R.id.editPhone);

        edit_Nume.setText(user.getNume());
        edit_Prenume.setText(user.getPrenume());
        edit_Varsta.setText(Integer.toString(user.getVarsta()));
        edit_Inaltime.setText(Float.toString(user.getInaltime()));
        edit_Greutate.setText(Float.toString(user.getGreutate()));
        edit_Email.setText(user.getEmail());
        edit_Phone.setText(user.getPhone());
        
        update_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = new MainActivity();
                mainActivity.changeProfileFragment(epf,new ProfileFragment());
            }
        });
        return editProfileView;
    }
    //public UserModel updateUser(){
        //creaza un obiect de tipul challenge
        //extragerea inputului din UI
        //atribuie campurilor obiectului inputul
        //returneaza obiectul
    //}
}