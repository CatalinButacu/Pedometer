package com.mypedometer.pip.pedometer;

import org.junit.Test;

import static org.junit.Assert.*;

import com.mypedometer.pip.pedometer.data.model.ChallengeModel;
import com.mypedometer.pip.pedometer.data.model.UserModel;
import com.mypedometer.pip.pedometer.data.model.UserModel.DataStats;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class UserModelTest {
    @Test
    public void getUserID_isCorrect() {
        UserModel user = new UserModel();
        assertEquals(1000, user.getUserID());
    }
    //----------------------------------------------------------------------------------------------
    @Test
    public void getUserID2_isCorrect() {
        UserModel user = new UserModel();
        user.setUserID("1001");
        assertEquals(1001, user.getUserID());
    }
    //----------------------------------------------------------------------------------------------
    @Test
    public void getNume_isCorrect() {
        UserModel user = new UserModel();
        user.setNume("Vasile");
        assertEquals("Vasile", user.getNume());
    }
    //----------------------------------------------------------------------------------------------
    @Test
    public void getPrenume_isCorrect() {
        UserModel user = new UserModel();
        user.setPrenume("Vasile cel mic");
        assertEquals("Vasile cel mic", user.getPrenume());
    }
    //----------------------------------------------------------------------------------------------
    @Test
    public void getVarsta_isCorrect() {
        UserModel user = new UserModel();
        user.setVarsta(22);
        assertEquals(Optional.of(22), user.getVarsta());
    }
    //----------------------------------------------------------------------------------------------
    @Test
    public void getGreutate_isCorrect() {
        UserModel user = new UserModel();
        user.setGreutate((float) 22.2);
        assertEquals(Optional.of(22.2), user.getGreutate());
    }
    //----------------------------------------------------------------------------------------------
    @Test
    public void getInaltime_isCorrect() {
        UserModel user = new UserModel();
        user.setInaltime((float) 170);
        assertEquals(Optional.of(170), user.getInaltime());
    }
    //----------------------------------------------------------------------------------------------
    @Test
    public void getFriends_isCorrect() {
        UserModel user = new UserModel();
        List<UserModel> expectedFriends = new ArrayList<>();
        expectedFriends.add(new UserModel());
        expectedFriends.add(new UserModel());
        user.setFriends(expectedFriends);

        List<UserModel> actualFriends = user.getFriends();
        assertEquals(expectedFriends, actualFriends);
    }
    //----------------------------------------------------------------------------------------------
    @Test
    public void getFriends2_isCorrect() {
        UserModel user = new UserModel();
        List<UserModel> expectedFriends = new ArrayList<>();
        expectedFriends.add(new UserModel());
        expectedFriends.add(new UserModel());
        user.setFriends(expectedFriends);

        UserModel friend = user.getFriend(0);

        // Verificați dacă prietenul returnat este cel așteptat
        assertEquals("", friend.getNume());
    }
    //----------------------------------------------------------------------------------------------
    /*@Test
    public void getChallenge_isCorrect() {
        ChallengeModel challengeModel = new ChallengeModel();
        List<ChallengeModel> challenges = new ArrayList<>();
        challenges.add(new ChallengeModel(""));
        challenges.add(new ChallengeModel(""));
        //challengeModel.m_lCandidates = challenges;

        // Obțineți lista de provocări utilizând metoda getChallenges()
        List<ChallengeModel> retrievedChallenges = myObject.getChallenges();

        // Verificați dacă lista de provocări returnată este aceeași cu lista așteptată
        assertEquals(challenges, retrievedChallenges);
    }*/
    //----------------------------------------------------------------------------------------------
    @Test
    public void getEmail_isCorrect() {
        UserModel user = new UserModel();
        user.setEmail("Viorel@yahoo.com");
        assertEquals("Viorel@yahoo.com", user.getEmail());
    }
    //----------------------------------------------------------------------------------------------
    @Test
    public void getPhone_isCorrect() {
        UserModel user = new UserModel();
        user.setPhone("0123456789");
        assertEquals("0123456789", user.getPhone());
    }
    //----------------------------------------------------------------------------------------------
    @Test
    public void getPassword_isCorrect() {
        UserModel user = new UserModel();
        user.setPassword("123456");
        assertEquals("123456", user.getPassword());
    }
    //----------------------------------------------------------------------------------------------
    @Test
    public void getIsPrivateProfile_isCorrect() {
        UserModel user = new UserModel();
        user.setIsPrivateProfile(true);
        assertEquals(true, user.getIsPrivateProfile());
    }
    //----------------------------------------------------------------------------------------------
    @Test
    public void getDataStats_isCorrect() {
        UserModel user = new UserModel();
        DataStats dataStats = new DataStats();
        user.setUserStats(dataStats);
        DataStats retrievedStats = user.getUserStats();
        assertEquals(dataStats, retrievedStats);

    }
    //----------------------------------------------------------------------------------------------
    @Test
    public void getTotalSpeps_isCorrect(){
        UserModel user = new UserModel();
        DataStats dataStats = new DataStats();
        user.setTotalSteps(12);
        assertEquals(Optional.of(12), user.getTotalSteps());
    }
    //----------------------------------------------------------------------------------------------
    @Test
    public void getTotalDistance_isCorrect(){
        UserModel user = new UserModel();
        DataStats dataStats = new DataStats();
        user.setTotalDistance(13);
        assertEquals(Optional.of(13), user.getTotalDistance());
    }
    //----------------------------------------------------------------------------------------------
    @Test
    public void getTotalCalories_isCorrect(){
        UserModel user = new UserModel();
        DataStats dataStats = new DataStats();
        user.setTotalCalories(14);
        assertEquals(Optional.of(14), user.getTotalCalories());
    }
    //----------------------------------------------------------------------------------------------
    @Test
    public void getDailySteps_isCorrect(){
        UserModel user = new UserModel();
        DataStats dataStats = new DataStats();
        Integer[] dailySteps = {16,13,15};
        user.setDailySteps(dailySteps);
        assertEquals(dailySteps, user.getDailySteps());
    }
    //----------------------------------------------------------------------------------------------
    @Test
    public void getDailyDistance_isCorrect(){
        UserModel user = new UserModel();
        DataStats dataStats = new DataStats();
        Integer[] dailyDistance = {100,130,150};
        user.setDailyDistance(dailyDistance);
        assertEquals(dailyDistance, user.getDailyDistance());
    }
    //----------------------------------------------------------------------------------------------
    @Test
    public void getDailyCalories_isCorrect(){
        UserModel user = new UserModel();
        DataStats dataStats = new DataStats();
        Integer[] dailyCalories = {100,103,105};
        user.setDailyCalories(dailyCalories);
        assertEquals(dailyCalories, user.getDailyCalories());
    }
    //----------------------------------------------------------------------------------------------
    @Test
    public void getDailySteps2_isCorrect(){
        UserModel user = new UserModel();
        Integer[] dailySteps = {16,13,15};
        user.setDailySteps(dailySteps);
        assertEquals(Optional.of(16), user.getDailySteps(0));
    }
    //----------------------------------------------------------------------------------------------
    @Test
    public void getDailyDistance2_isCorrect(){
        UserModel user = new UserModel();
        DataStats dataStats = new DataStats();
        Integer[] dailyDistance = {100,130,150};
        user.setDailyDistance(dailyDistance);
        assertEquals(dailyDistance, user.getDailyDistance(0));
    }
    //----------------------------------------------------------------------------------------------
    @Test
    public void getDailyCalories2_isCorrect(){
        UserModel user = new UserModel();
        DataStats dataStats = new DataStats();
        Integer[] dailyCalories = {100,103,105};
        user.setDailyCalories(dailyCalories);
        assertEquals(dailyCalories, user.getDailyCalories(0));
    }
    //----------------------------------------------------------------------------------------------
    @Test
    public void getGoalDailySteps_isCorrect(){
        UserModel user = new UserModel();
        user.setGoalDailySteps(5000);
        assertEquals(Optional.of(5000), user.getGoalDailySteps());
    }
    //----------------------------------------------------------------------------------------------
    @Test
    public void getGoalDailyCalories_isCorrect(){
        UserModel user = new UserModel();
        user.setGoalDailyCalories(1000);
        assertEquals(Optional.of(1000), user.getGoalDailyCalories());
    }
    //----------------------------------------------------------------------------------------------
    @Test
    public void getGoalDailyDistance_isCorrect(){
        UserModel user = new UserModel();
        user.setGoalDailyDistance(2000);
        assertEquals(Optional.of(2000), user.getGoalDailyDistance());
    }
    //----------------------------------------------------------------------------------------------
    @Test
    public void getGoalDailySteps2_isCorrect(){
        UserModel user = new UserModel();
        user.setGoalDailySteps(5000);
        assertEquals(Optional.of(5000), user.getGoalDailySteps());
    }
    //----------------------------------------------------------------------------------------------
    @Test
    public void getGoalDailyCalories2_isCorrect(){
        UserModel user = new UserModel();
        user.setGoalDailyCalories(1000);
        assertEquals(Optional.of(1000), user.getGoalDailyCalories());
    }
    //----------------------------------------------------------------------------------------------
    @Test
    public void getChallengeSteps_isCorrect(){
        UserModel user = new UserModel();
        DataStats dataStats = new DataStats();
        Integer[] challengeSteps = {100,103,105};
        user.setChallengeSteps(challengeSteps);
        assertEquals(challengeSteps, user.getChallengeSteps());
    }
    //----------------------------------------------------------------------------------------------
    @Test
    public void getChallengeCalories_isCorrect(){
        UserModel user = new UserModel();
        DataStats dataStats = new DataStats();
        Integer[] challengeCalories = {1020,1203,1205};
        user.setChallengeCalories(challengeCalories);
        assertEquals(challengeCalories, user.getChallengeCalories());
    }
    //----------------------------------------------------------------------------------------------
    @Test
    public void getChallengeDistance_isCorrect(){
        UserModel user = new UserModel();
        DataStats dataStats = new DataStats();
        Integer[] challengeDistance = {1007,1703,1075};
        user.setChallengeDistance(challengeDistance);
        assertEquals(challengeDistance, user.getChallengeDistance());
    }
    //----------------------------------------------------------------------------------------------
    @Test
    public void joinChallenge_isCorrect() {
        UserModel user = new UserModel();
        Boolean result = user.joinChallenge();
        assertTrue(result);
    }
    //----------------------------------------------------------------------------------------------
    @Test
    public void exitChallenge_isCorrect() {
        UserModel user = new UserModel();
        Boolean result = user.exitChallenge();
        assertTrue(result);
    }




}
