package com.mypedometer.pip.pedometer.data.model;

import java.util.ArrayList;
import java.util.List;

public class UserModel {

    static int usersCreated = 0;

    //----------------------------------------------------------------------------------------------
    // Constructors
    public UserModel(){
        this.m_sUserID = Integer.toString(1000+usersCreated);
        this.m_sEmail = "";
        this.m_sPhone = "";
        this.m_sPassword = "";

        this.m_sNume = "";
        this.m_sPrenume = "";
        this.m_sGen = "not set";
        this.m_iVarsta = 18;
        this.m_fGreutate = 60f;
        this.m_fInaltime = 1.8f;
        this.m_lFriends = null;
        this.m_bPrivateProfile = false;
        this.m_lChallenges = new ArrayList<ChallengeModel>();

        this.m_cUserStats = new DataStats();
        usersCreated++;
    }
    //----------------------------------------------------------------------------------------------
    // UserEssentials
    private String m_sUserID;
    private String m_sEmail;
    private String m_sPhone;
    private String m_sPassword;

    //----------------------------------------------------------------------------------------------
    // UserInfo
    private String m_sNume;
    private String m_sPrenume;
    private String m_sGen;
    private Integer m_iVarsta;
    private Float m_fGreutate;
    private Float m_fInaltime;
    private Boolean m_bPrivateProfile;

    //----------------------------------------------------------------------------------------------
    // DataStats
    private DataStats m_cUserStats;
    public class DataStats {

        DataStats() {
            m_iTotalSteps = 0;
            m_iTotalDistance = 0f;
            m_iTotalCalories = 0f;
            m_iGoalDailySteps = 10000;
            m_iGoalDailyCalories = 350f;
            m_iGoalDailyDistance = 8000f;

            for(int i = 0; i < 7; ++i )
            {
                m_iDailySteps[i] = 0;
                m_iDailyDistance[i] = 0f;
                m_iDailyCalories[i] = 0f;

                m_iChallengeSteps[i]=0;
                m_iChallengeCalories[i]=0f;
                m_iChallengeDistance[i]=0f;
            }
        }

        protected Integer m_iTotalSteps;
        protected Float m_iTotalDistance;
        protected Float m_iTotalCalories;
        protected Integer[] m_iDailySteps = new Integer[7];
        protected Float[] m_iDailyDistance = new Float[7];
        protected Float[] m_iDailyCalories = new Float[7];
        private Integer m_iGoalDailySteps;
        private Float m_iGoalDailyCalories;
        private Float m_iGoalDailyDistance;
        public Integer[] m_iChallengeSteps = new Integer[7];
        public Float[] m_iChallengeCalories = new Float[7];
        public Float[] m_iChallengeDistance = new Float[7];
    }

    //----------------------------------------------------------------------------------------------
    // Extra Data
    private List<UserModel> m_lFriends = new ArrayList<UserModel>();
    private List<ChallengeModel> m_lChallenges = new ArrayList<ChallengeModel>();

    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    @Override
    public String toString() {
        return "UserModel{" +
                "m_sUserID='" + m_sUserID + '\'' +
                ", m_sNume='" + m_sNume + '\'' +
                ", m_sPrenume='" + m_sPrenume + '\'' +
                ", m_sGen='" + m_sGen + '\'' +
                ", m_sEmail='" + m_sEmail + '\'' +
                ", m_sPhone='" + m_sPhone + '\'' +
                ", m_sPassword='" + m_sPassword + '\'' +
                ", m_bPrivateProfile=" + m_bPrivateProfile +
                ", m_lFriends=" + m_lFriends +
                ", m_lChallenges=" + m_lChallenges +
                ", m_iVarsta=" + m_iVarsta +
                ", m_fGreutate=" + m_fGreutate +
                ", m_fInaltime=" + m_fInaltime +
                ", m_cUserStats=" + m_cUserStats +
                '}';
    }
    //----------------------------------------------------------------------------------------------
    public void incrementSteps(){
        this.m_cUserStats.m_iTotalSteps++;
        this.m_cUserStats.m_iDailySteps[0]++;
        this.m_cUserStats.m_iChallengeSteps[0]++;
    }
    //----------------------------------------------------------------------------------------------
    public void incrementCalories(){
        this.m_cUserStats.m_iTotalCalories+=0.045f;
        this.m_cUserStats.m_iDailyCalories[0]++;
        this.m_cUserStats.m_iChallengeCalories[0]++;
    }
    //----------------------------------------------------------------------------------------------
    public void incrementDistance(){
        this.m_cUserStats.m_iTotalDistance +=0.67f;
        this.m_cUserStats.m_iDailyDistance[0]+=0.67f;
        this.m_cUserStats.m_iChallengeDistance[0]+=0.67f;
    }
    //----------------------------------------------------------------------------------------------
    public String getUserID(){
        return this.m_sUserID;
    }
    //----------------------------------------------------------------------------------------------
    public String getLastName(){
        return this.m_sNume;
    }
    //----------------------------------------------------------------------------------------------
    public String getFirstName(){
        return this.m_sPrenume;
    }
    //----------------------------------------------------------------------------------------------
    public String getGen(){
        return this.m_sGen;
    }
    //----------------------------------------------------------------------------------------------
    public Integer getVarsta(){
        return this.m_iVarsta;
    }
    //----------------------------------------------------------------------------------------------
    public Float getGreutate(){
        return this.m_fGreutate;
    }
    //----------------------------------------------------------------------------------------------
    public Float getInaltime(){
        return this.m_fInaltime;
    }
    //----------------------------------------------------------------------------------------------
    public List<UserModel> getFriends(){
        return this.m_lFriends;
    }
    //----------------------------------------------------------------------------------------------
    public UserModel getFriend(int i){
        return this.m_lFriends.get(i);
    }
    //----------------------------------------------------------------------------------------------
    public List<ChallengeModel> getChallenges(){
        return this.m_lChallenges;
    }
    //----------------------------------------------------------------------------------------------
    public ChallengeModel getChallenge(int i){
        return this.m_lChallenges.get(i);
    }
    //----------------------------------------------------------------------------------------------
    public boolean addChallenge(ChallengeModel i){
        if(i.getChallengeID().isEmpty()) return false;
        return this.m_lChallenges.add(i);
    }
    public String getEmail(){
        return this.m_sEmail;
    }
    //----------------------------------------------------------------------------------------------
    public String getPhone(){
        return this.m_sPhone;
    }
    //----------------------------------------------------------------------------------------------
    public String getPassword(){
        return this.m_sPassword;
    }
    //----------------------------------------------------------------------------------------------
    public Boolean getIsPrivateProfile(){
        return this.m_bPrivateProfile;
    }
    //----------------------------------------------------------------------------------------------
    public DataStats getUserStats(){
        return this.m_cUserStats;
    }
    //----------------------------------------------------------------------------------------------
    public Integer getTotalSteps(){
        return this.m_cUserStats.m_iTotalSteps;
    }
    //----------------------------------------------------------------------------------------------
    public Float getTotalDistance(){
        return this.m_cUserStats.m_iTotalDistance;
    }
    //----------------------------------------------------------------------------------------------
    public Float getTotalCalories(){
        return this.m_cUserStats.m_iTotalCalories;
    }
    //----------------------------------------------------------------------------------------------
    public Integer[] getDailySteps(){
        return this.m_cUserStats.m_iDailySteps;
    }
    //----------------------------------------------------------------------------------------------
    public Float[] getDailyDistance(){
        return this.m_cUserStats.m_iDailyDistance;
    }
    //----------------------------------------------------------------------------------------------
    public Float[] getDailyCalories(){
        return this.m_cUserStats.m_iDailyCalories;
    }
    //----------------------------------------------------------------------------------------------
    public Integer getTodaySteps(){
        return this.m_cUserStats.m_iDailySteps[0];
    }
    //----------------------------------------------------------------------------------------------
    public Float getTodayDistance(){
        return this.m_cUserStats.m_iDailyDistance[0];
    }
    //----------------------------------------------------------------------------------------------
    public Float getTodayCalories(){
        return this.m_cUserStats.m_iDailyCalories[0];
    }
    //----------------------------------------------------------------------------------------------
    public Integer[] getDailySteps(int i){
        return this.m_cUserStats.m_iDailySteps;
    }
    //----------------------------------------------------------------------------------------------
    public Float[] getDailyDistance(int i){
        return this.m_cUserStats.m_iDailyDistance;
    }
    //----------------------------------------------------------------------------------------------
    public Float[] getDailyCalories(int i){
        return this.m_cUserStats.m_iDailyCalories;
    }
    //----------------------------------------------------------------------------------------------
    public Integer getGoalDailySteps(){
        return this.m_cUserStats.m_iGoalDailySteps;
    }
    //----------------------------------------------------------------------------------------------
    public Float getGoalDailyCalories(){
        return this.m_cUserStats.m_iGoalDailyCalories;
    }
    //----------------------------------------------------------------------------------------------
    public Float getGoalDailyDistance(){
        return this.m_cUserStats.m_iGoalDailyDistance;
    }
    //----------------------------------------------------------------------------------------------
    public Integer[] getChallengeSteps(){
        return this.m_cUserStats.m_iChallengeSteps;
    }
    //----------------------------------------------------------------------------------------------
    public Float[] getChallengeCalories(){
        return this.m_cUserStats.m_iChallengeCalories;
    }
    //----------------------------------------------------------------------------------------------
    public Float[] getChallengeDistance(){
        return this.m_cUserStats.m_iChallengeDistance;
    }
    //----------------------------------------------------------------------------------------------
    public void setUserID(String s){
        this.m_sUserID = s;
    }
    //----------------------------------------------------------------------------------------------
    public void setFirstName(String s){
        this.m_sPrenume = s;
    }
    //----------------------------------------------------------------------------------------------
    public void setLastName(String s){
        this.m_sNume = s;
    }
    //----------------------------------------------------------------------------------------------
    public void setGender(String s){
        this.m_sGen = s;
    }
    //----------------------------------------------------------------------------------------------
    public void setVarsta(Integer i){
        this.m_iVarsta = i;
    }
    //----------------------------------------------------------------------------------------------
    public void setGreutate(Float f){
        this.m_fGreutate = f;
    }
    //----------------------------------------------------------------------------------------------
    public void setInaltime(Float f){
        this.m_fInaltime = f;
    }
    //----------------------------------------------------------------------------------------------
    public void setFriends(List<UserModel> l){
        this.m_lFriends = l;
    }
    //----------------------------------------------------------------------------------------------
    public void setEmail(String s){
        this.m_sEmail = s;
    }
    //----------------------------------------------------------------------------------------------
    public void setPhone(String s){
        this.m_sPhone = s;
    }
    //----------------------------------------------------------------------------------------------
    public void setPassword(String s){
        this.m_sPassword = s;
    }
    //----------------------------------------------------------------------------------------------
    public void setIsPrivateProfile(Boolean b){
        this.m_bPrivateProfile = b;
    }
    //----------------------------------------------------------------------------------------------
    public void setUserStats(DataStats c){
        this.m_cUserStats = c;
    }
    //----------------------------------------------------------------------------------------------
    public void setTotalSteps(Integer i){
        this.m_cUserStats.m_iTotalSteps = i;
    }
    //----------------------------------------------------------------------------------------------
    public void setTotalDistance(Float i){
        this.m_cUserStats.m_iTotalDistance = i;
    }
    //----------------------------------------------------------------------------------------------
    public void setTotalCalories(Float i){
        this.m_cUserStats.m_iTotalCalories = i;
    }
    //----------------------------------------------------------------------------------------------
    public void setDailySteps(Integer[] i){
        this.m_cUserStats.m_iDailySteps = i;
    }
    //----------------------------------------------------------------------------------------------
    public void setDailySteps(String s){
        String[] sSplit = s.split(",");
        Integer[] i = new Integer[m_cUserStats.m_iDailySteps.length];
        for (int j = 0; j < m_cUserStats.m_iDailySteps.length; j++) {
            i[j] = Integer.parseInt(sSplit[j]);
        }
        this.m_cUserStats.m_iDailySteps = i;
    }
    //----------------------------------------------------------------------------------------------
    public void setDailyDistance(Float[] i){
        this.m_cUserStats.m_iDailyDistance = i;
    }
    //----------------------------------------------------------------------------------------------
    public void setDailyDistance(String s){
        String[] sSplit = s.split(",");
        Float[] i = new Float[m_cUserStats.m_iDailySteps.length];
        for(int j = 0; j < m_cUserStats.m_iDailySteps.length; j++){
            i[j] = Float.parseFloat(sSplit[j]);
        }
        this.m_cUserStats.m_iDailyDistance = i;
    }
    //----------------------------------------------------------------------------------------------
    public void setDailyCalories(Float[] i){
        this.m_cUserStats.m_iDailyCalories = i;
    }

    //----------------------------------------------------------------------------------------------
    public void setDailyCalories(String s){
        String[] sSplit = s.split(",");
        Float[] i = new Float[m_cUserStats.m_iDailyCalories.length];
        for(int j = 0; j < sSplit.length; j++){
            i[j] = Float.parseFloat(sSplit[j]);
        }
        this.m_cUserStats.m_iDailyCalories = i;
    }
    //----------------------------------------------------------------------------------------------
    public void setGoalDailySteps(Integer i){
        this.m_cUserStats.m_iGoalDailySteps = i;
    }
    //----------------------------------------------------------------------------------------------
    public void setGoalDailyCalories(Float i){
        this.m_cUserStats.m_iGoalDailyCalories = i;
    }
    //----------------------------------------------------------------------------------------------
    public void setGoalDailyDistance(Float i){
        this.m_cUserStats.m_iGoalDailyDistance = i;
    }
    //----------------------------------------------------------------------------------------------
    public void setChallengeSteps(Integer[] i){
        this.m_cUserStats.m_iChallengeSteps = i;
    }

    //----------------------------------------------------------------------------------------------
    public void setChallengeSteps(String s){
        String[] sSplit = s.split(",");
        Integer[] i = new Integer[m_cUserStats.m_iChallengeSteps.length];
        for(int j = 0; j < sSplit.length; j++){
            i[j] = Integer.parseInt(sSplit[j]);
        }
        this.m_cUserStats.m_iChallengeSteps = i;
    }
    //----------------------------------------------------------------------------------------------
    public void setChallengeCalories(Float[] i){
        this.m_cUserStats.m_iChallengeCalories = i;
    }

    //----------------------------------------------------------------------------------------------
    public void setChallengeCalories(String s){
        String[] sSplit = s.split(",");
        Float[] i = new Float[m_cUserStats.m_iChallengeCalories.length];
        for(int j = 0; j < sSplit.length; j++){
            i[j] = Float.parseFloat(sSplit[j]);
        }
        this.m_cUserStats.m_iChallengeCalories = i;
    }
    //----------------------------------------------------------------------------------------------
    public void setChallengeDistance(Float[] i){
        this.m_cUserStats.m_iChallengeDistance = i;
    }

    //----------------------------------------------------------------------------------------------
    public void setChallengeDistance(String s){
        String[] sSplit = s.split(",");
        Float[] i = new Float[m_cUserStats.m_iChallengeDistance.length];
        for(int j = 0; j < sSplit.length; j++){
            i[j] = Float.parseFloat(sSplit[j]);
        }
        this.m_cUserStats.m_iChallengeDistance = i;
    }
    //----------------------------------------------------------------------------------------------
    public void setChallengeSteps(Integer i, Integer j){
        this.m_cUserStats.m_iChallengeSteps[i] = j;
    }
    //----------------------------------------------------------------------------------------------
    public void setChallengeCalories(Integer i, Float j){
        this.m_cUserStats.m_iChallengeCalories[i] = j;
    }
    //----------------------------------------------------------------------------------------------
    public void setChallengeDistance(Integer i, Float j){
        this.m_cUserStats.m_iChallengeDistance[i] = j;
    }
    //----------------------------------------------------------------------------------------------
    public void setDailySteps(Integer i, Integer j){
        this.m_cUserStats.m_iDailySteps[i] = j;
    }
    //----------------------------------------------------------------------------------------------
    public void setDailyCalories(Integer i, Float j){
        this.m_cUserStats.m_iDailyCalories[i] = j;
    }
    //----------------------------------------------------------------------------------------------
    public void setDailyDistance(Integer i, Float j){
        this.m_cUserStats.m_iDailyDistance[i] = j;
    }
    //----------------------------------------------------------------------------------------------
    public Boolean joinChallenge(){
        return true;
    }

    public Boolean exitChallenge(){
        return true;
    }

    public Boolean addFriend(UserModel user){
        if (m_lFriends == null) {
            m_lFriends = new ArrayList<>();
        }
        for (UserModel u:m_lFriends){
            if(u==user)
                return false;
        }
        return m_lFriends.add(user);
    }
    //----------------------------------------------------------------------------------------------
    public Boolean deleteFriend(UserModel user){
        for (UserModel u:m_lFriends){
            if(u==user)
                return m_lFriends.remove(u);
        }
        return false;
    }
    //----------------------------------------------------------------------------------------------
    public Boolean addDailySteps(Integer i){
        this.m_cUserStats.m_iDailySteps[0] += i;
        this.m_cUserStats.m_iTotalSteps += i;
        return true;
    }
    //----------------------------------------------------------------------------------------------
    public void updateStatsNextDay(){
        oneDayAfter(this.m_cUserStats.m_iDailySteps);
        oneDayAfter(this.m_cUserStats.m_iDailyDistance);
        oneDayAfter(this.m_cUserStats.m_iDailyCalories);

        oneDayAfter(this.m_cUserStats.m_iChallengeSteps);
        oneDayAfter(this.m_cUserStats.m_iChallengeDistance);
        oneDayAfter(this.m_cUserStats.m_iChallengeCalories);
    }
    //----------------------------------------------------------------------------------------------
    public void oneDayAfter(Integer[] list){
        for(int i=6;i>0;--i){
            list[i] = list[i-1];
        }
        list[0]=0;
    }
    //----------------------------------------------------------------------------------------------
    public void oneDayAfter(Float[] list){
        for(int i=6;i>0;--i){
            list[i] = list[i-1];
        }
        list[0]=0f;
    }
    //----------------------------------------------------------------------------------------------
    public double calculateAverageDailySteps() {
        int sum = 0;
        int count = 0;

        for (Integer element : this.m_cUserStats.m_iDailySteps) {
            if (element != null && element != 0) {
                sum += element;
                count++;
            }
        }

        if (count == 0) {
            return 0;
        }

        return (double) sum / count;
    }
    //----------------------------------------------------------------------------------------------
    public double calculateAverageDailyDistance() {
        int sum = 0;
        int count = 0;

        for (Float element : this.m_cUserStats.m_iDailyDistance) {
            if (element != null && element != 0) {
                sum += element;
                count++;
            }
        }

        if (count == 0) {
            return 0;
        }

        return (double) sum / count;
    }
    //----------------------------------------------------------------------------------------------
    public double calculateAverageDailyCalories() {
        int sum = 0;
        int count = 0;

        for (Float element : this.m_cUserStats.m_iDailyCalories) {
            if (element != null && element != 0) {
                sum += element;
                count++;
            }
        }

        if (count == 0) {
            return 0;
        }

        return (double) sum / count;
    }
    //----------------------------------------------------------------------------------------------
    public double calculateAverageChallengeSteps() {
        int sum = 0;
        int count = 0;

        for (Integer element : this.m_cUserStats.m_iChallengeSteps) {
            if (element != null && element != 0) {
                sum += element;
                count++;
            }
        }

        if (count == 0) {
            return 0;
        }

        return (double) sum / count;
    }
    //----------------------------------------------------------------------------------------------
    public double calculateAverageChallengeCalories() {
        int sum = 0;
        int count = 0;

        for (Float element : this.m_cUserStats.m_iChallengeCalories) {
            if (element != null && element != 0) {
                sum += element;
                count++;
            }
        }

        if (count == 0) {
            return 0;
        }

        return (double) sum / count;
    }
    //----------------------------------------------------------------------------------------------
    public double calculateAverageChallengeDistance() {
        int sum = 0;
        int count = 0;

        for (Float element : this.m_cUserStats.m_iChallengeDistance) {
            if (element != null && element != 0) {
                sum += element;
                count++;
            }
        }

        if (count == 0) {
            return 0;
        }

        return (double) sum / count;
    }
    //----------------------------------------------------------------------------------------------
}
