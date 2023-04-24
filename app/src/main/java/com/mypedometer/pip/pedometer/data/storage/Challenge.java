package com.mypedometer.pip.pedometer.data.storage;

import java.util.List;

public class Challenge {
    public String m_sChallengeID;
    public String m_sCreatorID;
    public String m_sNameChallenge;
    public Enum m_sStatusChallenge;
    public String m_sDateStart;
    public String m_sDateEnd;
    public List<User> m_lCandidates;

    protected Boolean insertCandidate(User user){
        return false;
    }

    protected Boolean deleteCandidates(User user){
        return false;
    }

    public String timeRemain(){
        return "string";
    }

    public Boolean synchronizeDB(){
        return false;
    }
}
