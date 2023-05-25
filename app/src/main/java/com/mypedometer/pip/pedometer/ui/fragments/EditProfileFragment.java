package com.mypedometer.pip.pedometer.ui.fragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.mypedometer.pip.pedometer.MainActivity;
import com.mypedometer.pip.pedometer.R;
import com.mypedometer.pip.pedometer.data.storage.LocalManager;

/**
 * This is the class that generates the UI for editing an already existent account when called.
 */


public class EditProfileFragment extends Fragment {
    /**
     * Instance of an EditProfileFragment used for switching in between the fragments.
     */
    EditProfileFragment epf = this;
    private Button update_profile;
    private EditText edit_Nume;
    private EditText edit_Prenume;
    private EditText edit_Varsta;
    private EditText edit_Inaltime;
    private EditText edit_Greutate;
    private EditText edit_Email;
    private EditText edit_Phone;
    private EditText edit_StepGoal;
    private EditText edit_DistanceGoal;
    private EditText edit_CaloriesGoal;
    private RadioGroup radioGroupGender;
    private RadioButton radioButtonMale;
    private RadioButton radioButtonFemale;
    private String edit_gender;
    private RadioGroup radioProfileType;
    private RadioButton radioButtonPrivateProfile;
    private RadioButton radioButtonPublicProfile;
    private boolean private_profile;

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
        edit_StepGoal = (EditText) editProfileView.findViewById(R.id.editStepGoal);
        edit_DistanceGoal = (EditText) editProfileView.findViewById(R.id.editDistanceGoal);
        edit_CaloriesGoal = (EditText) editProfileView.findViewById(R.id.editCaloriesGoal);

        radioGroupGender = editProfileView.findViewById(R.id.editGender);
        radioButtonMale = editProfileView.findViewById(R.id.radioMale);
        radioButtonFemale = editProfileView.findViewById(R.id.radioFemale);

        radioProfileType = editProfileView.findViewById(R.id.editProfileType);
        radioButtonPrivateProfile = editProfileView.findViewById(R.id.PrivateProfile);
        radioButtonPublicProfile = editProfileView.findViewById(R.id.PublicProfile);

        edit_Nume.setText(LocalManager.getInstance().getLocalUser().getLastName());
        edit_Prenume.setText(LocalManager.getInstance().getLocalUser().getFirstName());
        edit_Varsta.setText(Integer.toString(LocalManager.getInstance().getLocalUser().getVarsta()));
        edit_Inaltime.setText(Float.toString(LocalManager.getInstance().getLocalUser().getInaltime()));
        edit_Greutate.setText(Float.toString(LocalManager.getInstance().getLocalUser().getGreutate()));
        edit_Email.setText(LocalManager.getInstance().getLocalUser().getEmail());
        edit_Phone.setText(LocalManager.getInstance().getLocalUser().getPhone());
        edit_StepGoal.setText(Integer.toString(LocalManager.getInstance().getLocalUser().getGoalDailySteps()));
        edit_DistanceGoal.setText(Float.toString(LocalManager.getInstance().getLocalUser().getGoalDailyDistance()));
        edit_CaloriesGoal.setText(Float.toString(LocalManager.getInstance().getLocalUser().getGoalDailyCalories()));

        //cand dai click pe text sa dispara, daca treci mai departe sa completezi o sa tina minte ce date ai pus
        edit_Nume.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String currentText = edit_Nume.getText().toString();
                if (hasFocus) {
                    edit_Nume.setText("");
                }else if(!hasFocus){
                    if(currentText.isEmpty()){
                        edit_Nume.setText(LocalManager.getInstance().getLocalUser().getLastName());
                    }else{
                        edit_Nume.setText(currentText);
                    }
                }
            }
        });
        edit_Prenume.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String currentText = edit_Prenume.getText().toString();
                if (hasFocus) {
                    edit_Prenume.setText("");
                }else if(!hasFocus){
                    if(currentText.isEmpty()){
                        edit_Prenume.setText(LocalManager.getInstance().getLocalUser().getFirstName());
                    }else{
                        edit_Prenume.setText(currentText);
                    }
                }
            }
        });
        edit_Email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String currentText = edit_Email.getText().toString();
                if (hasFocus) {
                    edit_Email.setText("");
                }else if(!hasFocus){
                    if(currentText.isEmpty()){
                        edit_Email.setText(LocalManager.getInstance().getLocalUser().getEmail());
                    }else{
                        edit_Email.setText(currentText);
                    }
                }
            }
        });
        edit_Varsta.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String currentText = edit_Varsta.getText().toString();
                if (hasFocus) {
                    edit_Varsta.setText("");
                } else if (!hasFocus) {
                    if (TextUtils.isEmpty(currentText)) {
                        edit_Varsta.setText(Integer.toString(LocalManager.getInstance().getLocalUser().getVarsta()));
                    } else {
                        edit_Varsta.setText(String.valueOf(Integer.parseInt(currentText)));
                    }
                }
            }
        });
        edit_Inaltime.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String currentText = edit_Inaltime.getText().toString();
                if (hasFocus) {
                    edit_Inaltime.setText("");
                } else if (!hasFocus) {
                    if (TextUtils.isEmpty(currentText)) {
                        edit_Inaltime.setText(Float.toString(LocalManager.getInstance().getLocalUser().getInaltime()));
                    } else {
                        edit_Inaltime.setText(String.valueOf(Float.parseFloat(currentText)));
                    }
                }
            }
        });
        edit_Greutate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String currentText = edit_Greutate.getText().toString();
                if (hasFocus) {
                    edit_Greutate.setText("");
                } else if (!hasFocus) {
                    if (TextUtils.isEmpty(currentText)) {
                        edit_Greutate.setText(Float.toString(LocalManager.getInstance().getLocalUser().getGreutate()));
                    } else {
                        edit_Greutate.setText(String.valueOf(Float.parseFloat(currentText)));
                    }
                }
            }
        });
        edit_StepGoal.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String currentText = edit_StepGoal.getText().toString();
                if (hasFocus) {
                    edit_StepGoal.setText("");
                } else if (!hasFocus) {
                    if (TextUtils.isEmpty(currentText)) {
                        edit_StepGoal.setText(Integer.toString(LocalManager.getInstance().getLocalUser().getGoalDailySteps()));
                    } else {
                        edit_StepGoal.setText(String.valueOf(Integer.parseInt(currentText)));
                    }
                }
            }
        });
        edit_DistanceGoal.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String currentText = edit_DistanceGoal.getText().toString();
                if (hasFocus) {
                    edit_DistanceGoal.setText("");
                } else if (!hasFocus) {
                    if (TextUtils.isEmpty(currentText)) {
                        edit_DistanceGoal.setText(Float.toString(LocalManager.getInstance().getLocalUser().getGoalDailyDistance()));
                    } else {
                        edit_DistanceGoal.setText(String.valueOf(Integer.parseInt(currentText)));
                    }
                }
            }
        });
        edit_CaloriesGoal.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String currentText = edit_CaloriesGoal.getText().toString();
                if (hasFocus) {
                    edit_CaloriesGoal.setText("");
                } else if (!hasFocus) {
                    if (TextUtils.isEmpty(currentText)) {
                        edit_CaloriesGoal.setText(Float.toString(LocalManager.getInstance().getLocalUser().getGoalDailyCalories()));
                    } else {
                        edit_CaloriesGoal.setText(String.valueOf(Integer.parseInt(currentText)));
                    }
                }
            }
        });
        edit_Phone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String currentText = edit_Phone.getText().toString();
                if (hasFocus) {
                    edit_Phone.setText("");
                }else if(!hasFocus){
                    if(currentText.isEmpty()){
                        edit_Phone.setText(LocalManager.getInstance().getLocalUser().getPhone());
                    }else{
                        edit_Phone.setText(currentText);
                    }
                }
            }
        });

        //selectare gen
        radioGroupGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == radioButtonMale.getId()) {
                    edit_gender="Male";
                } else if (checkedId == radioButtonFemale.getId()) {
                    edit_gender="Female";
                }
            }
        });

        //selectare tip profil
        radioProfileType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == radioButtonPrivateProfile.getId()) {
                    private_profile=true;
                } else if (checkedId == radioButtonPublicProfile.getId()) {
                    private_profile=false;
                }
            }
        });
        update_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String LastNameValid = String.valueOf(edit_Nume.getText());
                String FirstNameValid = String.valueOf(edit_Prenume.getText());
                Integer AgeValid = Integer.valueOf(String.valueOf(edit_Varsta.getText()));
                Float HeightValid = Float.valueOf(String.valueOf(edit_Inaltime.getText()));
                Float WeightValid = Float.valueOf(String.valueOf(edit_Greutate.getText()));
                String EmailValid = String.valueOf(edit_Email.getText());
                String PhoneValid = String.valueOf(edit_Phone.getText());
                Integer GoalStepsValid = Integer.valueOf(String.valueOf(edit_StepGoal.getText()));
                Float GoalDistanceValid = Float.valueOf(String.valueOf(edit_DistanceGoal.getText()));
                Float GoalCaloriesValid = Float.valueOf(String.valueOf(edit_CaloriesGoal.getText()));

                boolean success = (LastNameValid.length() >=0 && LastNameValid.length() <=15 && FirstNameValid.length() >=0 && FirstNameValid.length() <=20 && AgeValid>0 && AgeValid<=120 && HeightValid>0.5 && HeightValid<=3 && WeightValid>5 && WeightValid<=400 && EmailValid.length() >=2 && EmailValid.length()<=30 && GoalStepsValid>0 && PhoneValid.length()==10 && GoalDistanceValid>0 && GoalCaloriesValid>0);
                    if(success){
                        LocalManager.getInstance().getLocalUser().setLastName(LastNameValid);
                        LocalManager.getInstance().getLocalUser().setFirstName(FirstNameValid);
                        LocalManager.getInstance().getLocalUser().setVarsta(AgeValid);
                        LocalManager.getInstance().getLocalUser().setInaltime(HeightValid);
                        LocalManager.getInstance().getLocalUser().setGreutate(WeightValid);
                        LocalManager.getInstance().getLocalUser().setEmail(EmailValid);
                        LocalManager.getInstance().getLocalUser().setPhone(PhoneValid);
                        LocalManager.getInstance().getLocalUser().setGoalDailyDistance(GoalDistanceValid);
                        LocalManager.getInstance().getLocalUser().setGoalDailyCalories(GoalCaloriesValid);
                        LocalManager.getInstance().getLocalUser().setGender(edit_gender);
                        LocalManager.getInstance().getLocalUser().setIsPrivateProfile(private_profile);

                        MainActivity mainActivity = new MainActivity();
                        mainActivity.changeProfileFragment(epf,new ProfileFragment());
                    }
                Toast.makeText(getActivity(), success ? "Account edited successfully!":"Something is not correct!", Toast.LENGTH_SHORT).show();
            }
        });
        return editProfileView;
    }
}