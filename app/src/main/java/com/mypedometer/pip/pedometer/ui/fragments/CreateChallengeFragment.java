package com.mypedometer.pip.pedometer.ui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mypedometer.pip.pedometer.R;
import com.mypedometer.pip.pedometer.data.model.ChallengeModel;
import com.mypedometer.pip.pedometer.data.model.UserModel;

import java.util.ArrayList;
import java.util.List;

public class CreateChallengeFragment extends Fragment {
    private EditText createChallengeName;
    private EditText challengeStartDate;
    private EditText challengeEndDate;
    private EditText challengeInviteFriends;

    private Button createChallengeButton;

    public CreateChallengeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View createChallengeView = inflater.inflate(R.layout.create_challenge_layout, container, false);

        createChallengeButton = (Button) createChallengeView.findViewById(R.id.create_account_button);

        createChallengeName = (EditText) createChallengeView.findViewById(R.id.createChallengeName);
        challengeStartDate = (EditText) createChallengeView.findViewById(R.id.challengeStartDate);
        challengeEndDate = (EditText) createChallengeView.findViewById(R.id.challengeEndDate);
        challengeInviteFriends = (EditText) createChallengeView.findViewById(R.id.challengeInviteFriends);

        createChallengeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean valid = true;
                String ChallengeName = "";
                String Ch_StartDate = "";
                String Ch_EndDate = "";
                String Ch_Friends = "";


                //TODO:: DE IMPLEMENTAT VALIDAREA CU BAZA DE DATE
                try {
                    ChallengeName = createChallengeName.getText().toString();
                    Ch_StartDate = challengeStartDate.getText().toString();
                    Ch_EndDate = challengeEndDate.getText().toString();
                    Ch_Friends = challengeInviteFriends.getText().toString();

                    /*
                    createChallengeName.getText().clear();
                    challengeStartDate.getText().clear();
                    challengeEndDate.getText().clear();
                    challengeInviteFriends.getText().clear();
                    */

                    if (ChallengeName.isEmpty() || Ch_StartDate.isEmpty() || Ch_EndDate.isEmpty() || Ch_Friends.isEmpty())
                    {
                        valid = false;
                    }
                } catch (Exception e)
                {
                    valid = false;
                }

                if (valid)
                {
                    //create challenge

                }
                Toast.makeText(getActivity(), "Challenge created!", Toast.LENGTH_SHORT).show();
            }
        });
        //Test
        List<UserModel> ss = initFriendsToChallenge("{1,3,2,5}");

        return createChallengeView;
    }

    public ChallengeModel createChallenge(){

        //creaza un obiect de tipul challenge
        ChallengeModel challenge = new ChallengeModel();

        //extragerea inputului din UI
        String ChallengeName = createChallengeName.getText().toString();
        String Ch_StartDate = challengeStartDate.getText().toString();
        String Ch_EndDate = challengeEndDate.getText().toString();
        String Ch_Friends = challengeInviteFriends.getText().toString();

        //atribuie campurilor obiectului inputul
        challenge.setChallengeID("5000");
        challenge.setCreatorID("1000");
        challenge.setStatusChallenge(ChallengeModel.Status.Created);
        challenge.setNameChallenge(ChallengeName);
        challenge.setDateStart(Ch_StartDate);
        challenge.setDateEnd(Ch_EndDate);
        challenge.m_lCandidates = initFriendsToChallenge(Ch_Friends);

        //returneaza obiectul
        return challenge;
    }
    //***************************************************************************************
    List<UserModel> initFriendsToChallenge(String s){

        List<UserModel> listFriendsID = new ArrayList<>();
        String[] FriendsArrayID = s.split(",");
        String ss = "";
        for (String id:FriendsArrayID) {
            //TODO:: if user exist, keep it, else remove it
            //TODO:: check if ids are correct

            if(id.matches(".*[^0-9].*"))
                ss = id.replaceAll("\\D+", "");

            UserModel friend = new UserModel();
            friend.setUserID(ss);
            listFriendsID.add(friend);
        }
        return listFriendsID;
    }
}


