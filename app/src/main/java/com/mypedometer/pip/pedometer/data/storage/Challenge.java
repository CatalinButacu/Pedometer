package com.mypedometer.pip.pedometer.data.storage;

import java.util.List;

public class Challenge {

    public class DataStats {
        protected Integer m_iTotalSteps;
        protected Integer m_iTotalDistance;
        protected Integer m_iTotalCalories;
        protected Integer[] m_iDailySteps;
        protected Integer[] m_iDailyDistance;
        protected Integer[] m_iDailyCalories;
        private Integer m_iGoalDailySteps;
        private Integer m_iGoalDailyCalories;
        private Integer m_iGoalDailyDistance;
        public Integer[] m_iChallengeSteps;
        public Integer[] m_iChallengeCalories;
        public Integer[] m_iChallengeDistance;
    }


    public String m_sChallengeID;
    public String m_sCreatorID;
    public String m_sNameChallenge;
    public Enum m_sStatusChallenge;
    public String m_sDateStart;
    public String m_sDateEnd;
    public List<User> m_lCandidates;

    protected Boolean insertCandidate(User user){return false;}

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
