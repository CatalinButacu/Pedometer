package com.mypedometer.pip.pedometer.data.storage;

import android.provider.ContactsContract;

import java.util.List;

public class User {

    public User(String s){
        if(s=="TEST"){
            m_sNume = "Popa";
            m_sPrenume = "Andrei";
            m_iVarsta = 20;
            //...
        }
    }


    // Members

    public String m_sUserID = "100";
    protected String m_sNume = "";
    protected String m_sPrenume = "";
    protected Integer m_iVarsta = 0;
    protected Float m_fGreutate = 0f;
    protected Float m_fInaltime = 0f;
    protected List<User> m_lFriends = null;
    private String m_sEmail = "";
    private String m_sPhone = "";
    private String m_sPassword = "";
    public Boolean m_bPrivateProfile = false;
    public DataStats m_cUserStats;

    public class DataStats {
        protected Integer m_iTotalSteps = 0;
        protected Integer m_iTotalDistance = 0;
        protected Integer m_iTotalCalories = 0;
        protected Integer[] m_iDailySteps = new Integer[6];
        protected Integer[] m_iDailyDistance = new Integer[6];
        protected Integer[] m_iDailyCalories = new Integer[6];
        private Integer m_iGoalDailySteps = 0;
        private Integer m_iGoalDailyCalories = 0;
        private Integer m_iGoalDailyDistance = 0;
        public Integer[] m_iChallengeSteps = new Integer[6];
        public Integer[] m_iChallengeCalories = new Integer[6];
        public Integer[] m_iChallengeDistance = new Integer[6];
    }

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
        return m_lFriends.add(user);
    }

    protected Boolean deleteFriend(User user){
        for (User u:m_lFriends){
            if(u==user)
                return m_lFriends.remove(u);
        }
        return false;
    }
    //
}
