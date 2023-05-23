package com.mypedometer.pip.pedometer.data.model;

import android.annotation.SuppressLint;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


/**
 * This is the base of every Challenge.
 * Data will localy store here.
 */
public class ChallengeModel {
    static int challengesCreated = 0;

    //----------------------------------------------------------------------------------------------
    // Constructors
    public ChallengeModel(){
        m_sChallengeID = Integer.toString(7000+challengesCreated);
        m_sCreatorID = "";
        m_sChallengeName = "";
        m_sChallengeStatus = Status.Created;
        m_sDateStart = "";
        m_sDateEnd = "";
        challengesCreated++;
    }
    //----------------------------------------------------------------------------------------------
    // ChallengeInfo
    private String m_sChallengeID;
    private String m_sCreatorID;
    private String m_sChallengeName;
    public List<UserModel> m_lCandidates = new ArrayList<UserModel>();

    //----------------------------------------------------------------------------------------------
    // DataStats
    private Status m_sChallengeStatus;
    private String m_sDateStart;
    private String m_sDateEnd;
    public enum Status{
        Created,
        Started,
        Finished,
        Deleted
    };

    //----------------------------------------------------------------------------------------------

    /**
     * Overridden toString method for debugging and working easier with the DataBase.
     */
    @Override
    public String toString() {
        return "ChallengeModel{" +
                "m_sChallengeID='" + m_sChallengeID + '\'' +
                ", m_sCreatorID='" + m_sCreatorID + '\'' +
                ", m_sChallengeName='" + m_sChallengeName + '\'' +
                ", m_sChallengeStatus=" + m_sChallengeStatus +
                ", m_sDateStart='" + m_sDateStart + '\'' +
                ", m_sDateEnd='" + m_sDateEnd + '\'' +
                ", m_lCandidates=" + m_lCandidates +
                '}';
    }
    //----------------------------------------------------------------------------------------------
    public String getChallengeID(){
        return m_sChallengeID;
    }
    //----------------------------------------------------------------------------------------------
    public String getCreatorID(){
        return m_sCreatorID;
    }
    //----------------------------------------------------------------------------------------------
    public String getNameChallenge(){
        return m_sChallengeName;
    }
    //----------------------------------------------------------------------------------------------
    public Status getStatusChallenge(){
        return m_sChallengeStatus;
    }
    //----------------------------------------------------------------------------------------------
    public String getDateStart(){
        return m_sDateStart;
    }
    //----------------------------------------------------------------------------------------------
    public String getDateEnd(){
        return m_sDateEnd;
    }
    //----------------------------------------------------------------------------------------------
    public String getCandidatesID(){
        String sCandidatesID = "";
        for (UserModel user : m_lCandidates) {
            sCandidatesID += user.getUserID() + ",";
        }
        return sCandidatesID;
    }
    //----------------------------------------------------------------------------------------------
    public boolean setChallengeID(String sChallengeID){
        boolean OK = true;
        if (sChallengeID.isEmpty()){
            OK = false;
        }
        else{
            m_sChallengeID = sChallengeID;
        }
        return OK;
    }
    //----------------------------------------------------------------------------------------------
    public boolean setCreatorID(String sCreatorID){
        boolean OK = true;
        if (sCreatorID.isEmpty()){
            OK = false;
        }
        else{
            m_sCreatorID = sCreatorID;
        }
        return OK;
    }
    //----------------------------------------------------------------------------------------------
    public void setNameChallenge(String sNameChallenge){
        m_sChallengeName = sNameChallenge;
    }
    //----------------------------------------------------------------------------------------------
    public void setStatusChallenge(String status) {
        switch (status) {
            case "Created":
                m_sChallengeStatus = Status.Created;
                break;
            case "Started":
                m_sChallengeStatus = Status.Started;
                break;
            case "Finished":
                m_sChallengeStatus = Status.Finished;
                break;
            case "Deleted":
                m_sChallengeStatus = Status.Deleted;
                break;
            default:
                m_sChallengeStatus = Status.Created;
                break;
        }
    }
    //----------------------------------------------------------------------------------------------
    public void setStatusChallenge(Status sStatusChallenge){
        m_sChallengeStatus = sStatusChallenge;
    }
    //----------------------------------------------------------------------------------------------
    public boolean setDateStart(String sDateStart){
        boolean OK = true;
        if (m_sDateStart.isEmpty()){
            OK = false;
        }
        else{
            m_sDateStart = sDateStart;
        }
        return OK;
    }
    //----------------------------------------------------------------------------------------------
    public boolean setDateEnd(String sDateEnd){
        boolean OK = true;
        if (m_sDateEnd.isEmpty()){
            OK = false;
        }
        else{
            m_sDateEnd = sDateEnd;
        }
        return OK;
    }
    //----------------------------------------------------------------------------------------------
    public boolean setCandidatesID(String sCandidatesID){
        boolean OK = true;
        if (sCandidatesID.isEmpty()){
            OK = false;
        }
        else{
            String[] sCandidates = sCandidatesID.split(",");
            for (String sCandidate : sCandidates) {
                UserModel user = new UserModel();
                user.setUserID(sCandidate);
                m_lCandidates.add(user);
            }
        }
        return OK;
    }
    //----------------------------------------------------------------------------------------------

    /**
     * Inserts the current user into the candidates list of the selected challenge.
     * @param user
     * @return
     */
    public Boolean insertCandidate(UserModel user){
        boolean OK = true;
        if (m_lCandidates.contains(user)){
            OK = false;
        }
        else{
            m_lCandidates.add(user);
        }
        return OK;
    };
    //----------------------------------------------------------------------------------------------
    public Boolean deleteCandidates(UserModel user){
        boolean OK = true;

        if (m_lCandidates.contains(user)){
            m_lCandidates.remove(user);
        }
        else{
            OK = false;
        }
        return OK;
    }
    //----------------------------------------------------------------------------------------------
    @SuppressLint("DefaultLocale")
    @RequiresApi(api = Build.VERSION_CODES.S)
    public String timeRemain(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime startDateTime = LocalDateTime.parse(m_sDateStart, formatter);
        LocalDateTime endDateTime = LocalDateTime.parse(m_sDateEnd, formatter);

        Duration duration = null;
        long year = 0;
        long months = 0;
        long days = 0;
        long hours = 0;
        long minutes = 0;
        long seconds = 0;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            duration = Duration.between(startDateTime, endDateTime);
            year = duration.toDays() / 365;
            months = duration.toDays() / 30;
            days = duration.toDays();
            hours = duration.toHours() % 24;
            minutes = duration.toMinutes() % 60;
            seconds = duration.toMinutes() % 360;
        }

        return String.format("%d-%d-%d %d:%d:%d",year, months, days, hours, minutes, seconds);
    }
    //----------------------------------------------------------------------------------------------
    public Boolean synchronizeDB(){
        return false;
    }
}
