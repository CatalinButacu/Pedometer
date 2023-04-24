package com.mypedometer.pip.pedometer.data.storage;

import android.provider.ContactsContract;

import java.util.List;

public class User {

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


    // Members

    public String m_sUserID;
    protected String m_sNume;
    protected String m_sPrenume;
    protected Integer m_iVarsta;
    protected Float m_fGreutate;
    protected Float m_fInaltime;
    protected List<User> m_lFriends;
    private String m_sEmail;
    private String m_sPhone;
    private String m_sPassword;
    public Boolean m_bPrivateProfile;
    public DataStats m_cUserStats;
    //

    // Getters / Setters

    public String getM_sUserID(){
        return this.m_sUserID;
    }

    public String getM_sNume(){
        return this.m_sNume;
    }

    public String getM_sPrenume(){
        return this.m_sPrenume;
    }

    public Integer getM_iVarsta(){
        return this.m_iVarsta;
    }

    public Float getM_fGreutate(){
        return this.m_fGreutate;
    }

    public Float getM_fInaltime(){
        return this.m_fInaltime;
    }

    public List<User> getM_lFriends(){
        return this.m_lFriends;
    }

    public String getM_sEmail() {
        return m_sEmail;
    }

    public String getM_sPhone(){
        return this.m_sPhone;
    }

    public String getM_sPassword(){
        return this.m_sPassword;
    }

    public void setM_bPrivateProfile(Boolean value) {
        this.m_bPrivateProfile = value;
    }

    public DataStats getM_cUserStats(){
        return this.m_cUserStats;
    }
    //

    // Class Methods

    public Boolean updateStats(){
        return true;
    }

    private Boolean editProfile(){
        return true;
    }

    public Boolean joinChallenge(){
        return true;
    }

    public Boolean exitChallenge(){
        return true;
    }

    protected Boolean addFriend(User user){
        return true;
    }

    protected Boolean deleteFriend(User user){
        return true;
    }
    //
}
