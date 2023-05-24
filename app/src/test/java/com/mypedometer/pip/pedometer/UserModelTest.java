package com.mypedometer.pip.pedometer;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import static java.util.Optional.*;

import com.mypedometer.pip.pedometer.data.model.ChallengeModel;
import com.mypedometer.pip.pedometer.data.model.UserModel;
import com.mypedometer.pip.pedometer.data.model.UserModel.DataStats;

import java.util.ArrayList;
import java.util.List;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class UserModelTest {
    @Test
    public void getUserID2_isCorrect() {
        UserModel user = new UserModel();
        user.setUserID("1001");
        assertEquals("1001", user.getUserID());
    }
    //----------------------------------------------------------------------------------------------
    @Test
    public void getNume_isCorrect() {
        UserModel user = new UserModel();
        user.setLastName("Vasile");
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
    public void getPrenume2_isCorrect() {
        UserModel user = new UserModel();
        user.setFirstName("Vasile cel mic");
        assertEquals("Vasile cel mic", user.getPrenume());
    }
    //----------------------------------------------------------------------------------------------
    @Test
    public void getPrenume3_isCorrect() {
        UserModel user = new UserModel();
        user.setPrenume("Vasile cel mic");
        assertEquals("Vasile cel mic", user.getPrenume());
    }
    //----------------------------------------------------------------------------------------------
    @Test
    public void getVarsta_isCorrect() {
        UserModel user = new UserModel();
        user.setVarsta(22);
        assertEquals((Integer)22, user.getVarsta());
    }
    //----------------------------------------------------------------------------------------------
    @Test
    public void getGreutate_isCorrect() {
        UserModel user = new UserModel();
        user.setGreutate(Float.valueOf(22));
        assertEquals(Float.valueOf(22), user.getGreutate());
    }
    //----------------------------------------------------------------------------------------------
    @Test
    public void getGen_isCorrect() {
        UserModel user = new UserModel();
        user.setGender("Male");
        assertEquals("Male", user.getGen());
    }
    //----------------------------------------------------------------------------------------------
    @Test
    public void getInaltime_isCorrect() {
        UserModel user = new UserModel();
        user.setInaltime(Float.valueOf(170));
        assertEquals(Float.valueOf(170),user.getInaltime());
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
    @Test
    public void getChallenge_isCorrect() {
        UserModel user = new UserModel();
        ChallengeModel ch1 = new ChallengeModel();
        ChallengeModel ch2 = new ChallengeModel();
        user.addChallenge(ch1);
        user.addChallenge(ch2);

        // Verificați dacă lista de provocări returnată este aceeași cu lista așteptată
        assertEquals(ch1, user.getChallenge(0));
    }
    //---------------------------------------------------------------------
    @Test
    public void getChallenges1_isCorrect() {
        UserModel user = new UserModel();

        ChallengeModel challenge1 = new ChallengeModel();
        ChallengeModel challenge2 = new ChallengeModel();
        user.getChallenges().add(challenge1);
        user.getChallenges().add(challenge2);

        assertEquals(2, user.getChallenges().size());
        assertTrue(user.getChallenges().contains(challenge1));
        assertTrue(user.getChallenges().contains(challenge2));
    }
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
        DataStats dataStats = new UserModel().new DataStats();
        user.setUserStats(dataStats);
        DataStats retrievedStats = user.getUserStats();
        assertEquals(dataStats, retrievedStats);

    }
    //----------------------------------------------------------------------------------------------
    @Test
    public void getTotalSpeps_isCorrect(){
        UserModel user = new UserModel();
        user.setTotalSteps(12);
        assertEquals((Integer)12, user.getTotalSteps());
    }
    //----------------------------------------------------------------------------------------------
    @Test
    public void getTotalDistance_isCorrect(){
        UserModel user = new UserModel();
        user.setTotalDistance(1000);
        assertEquals((Integer) 1000, user.getTotalDistance());
    }
    //----------------------------------------------------------------------------------------------
    @Test
    public void getTotalCalories_isCorrect(){
        UserModel user = new UserModel();
        DataStats dataStats = new UserModel().new DataStats();
        user.setTotalCalories(14);
        assertEquals((Integer) 14, user.getTotalCalories());
    }
    //----------------------------------------------------------------------------------------------
    @Test
    public void getDailySteps_isCorrect(){
        UserModel user = new UserModel();
        Integer[] dailySteps = {16,13,15};
        user.setDailySteps(dailySteps);
        assertEquals(dailySteps, user.getDailySteps());
    }
    //----------------------------------------------------------------------------------------------
    @Test
    public void getDailySteps22_isCorrect(){
        UserModel user = new UserModel();
        user.setDailySteps("1000");
        assertEquals((Integer) 1000, user.getDailySteps()[0]);
    }
    //----------------------------------------------------------------------------------------------
    @Test
    public void setDailySteps_isCorrect(){
        UserModel user = new UserModel();
        ChallengeModel challenge = new ChallengeModel();
        Integer[] dailySteps = {1000, 2000, 1500};
        user.setDailySteps(1, 3000);

        Integer[] retrievedSteps = user.getDailySteps();
        assertEquals(Integer.valueOf(3000), retrievedSteps[1]);
    }
    //------------------------------------------------------------------------------------------------
    @Test
    public void setDailyCalories22_isCorrect(){
        UserModel user = new UserModel();
        ChallengeModel challenge = new ChallengeModel();
        user.addChallenge(challenge);
        user.setDailyCalories(0, 300);

        assertEquals((Integer) 300, user.getDailyCalories()[0]);
    }
    //----------------------------------------------------------------------------------------------
    @Test
    public void setDailyDistance_isCorrect(){
        UserModel user = new UserModel();
        ChallengeModel challenge = new ChallengeModel();
        Integer[] dailyDistance = {100,130,150};
        user.setDailyDistance(1, 300);

        Integer[] retrievedDistance = user.getDailyDistance();
        assertEquals(Integer.valueOf(300), retrievedDistance[1]);
    }
    @Test
    public void getDailySteps2_isCorrect(){
        UserModel user = new UserModel();
        Integer[] dailySteps = {16,13,15};
        user.setDailySteps(dailySteps);
        assertEquals((Integer) 13, user.getDailySteps(1)[1]);
    }
    //----------------------------------------------------------------------------------------------
    @Test
    public void getDailyDistance_isCorrect(){
        UserModel user = new UserModel();
        Integer[] dailyDistance = {100,130,150};
        user.setDailyDistance(dailyDistance);
        assertEquals(dailyDistance, user.getDailyDistance());
    }
    //----------------------------------------------------------------------------------------------
    @Test
    public void getDailyDistance22_isCorrect(){
        UserModel user = new UserModel();
        user.setDailyDistance("5000");
        assertEquals((Integer) 5000, user.getDailyDistance()[0]);
    }
    //----------------------------------------------------------------------------------------------
    @Test
    public void getDailyCalories_isCorrect(){
        UserModel user = new UserModel();
        Integer[] dailyCalories = {100,103,105};
        user.setDailyCalories(dailyCalories);
        assertEquals(dailyCalories, user.getDailyCalories());
    }
    //----------------------------------------------------------------------------------------------
    @Test
    public void getDailyCalories22_isCorrect(){
        UserModel user = new UserModel();
        user.setDailyCalories("300");
        assertEquals((Integer) 300, user.getDailyCalories()[0]);
    }
    //----------------------------------------------------------------------------------------------
    @Test
    public void getDailyDistance2_isCorrect(){
        UserModel user = new UserModel();
        Integer[] dailyDistance = {100,130,150};
        user.setDailyDistance(dailyDistance);
        assertEquals(dailyDistance, user.getDailyDistance(0));
    }
    //----------------------------------------------------------------------------------------------
    @Test
    public void getDailyCalories2_isCorrect(){
        UserModel user = new UserModel();
        Integer[] dailyCalories = {100,103,105};
        user.setDailyCalories(dailyCalories);
        assertEquals(dailyCalories, user.getDailyCalories(0));
    }
    //----------------------------------------------------------------------------------------------
    @Test
    public void getGoalDailySteps_isCorrect(){
        UserModel user = new UserModel();
        user.setGoalDailySteps(5000);
        assertEquals((Integer)5000, user.getGoalDailySteps());
    }
    //----------------------------------------------------------------------------------------------
    @Test
    public void getGoalDailyCalories_isCorrect(){
        UserModel user = new UserModel();
        user.setGoalDailyCalories(1000);
        assertEquals((Integer) 1000, user.getGoalDailyCalories());
    }
    //----------------------------------------------------------------------------------------------
    @Test
    public void getGoalDailyDistance_isCorrect(){
        UserModel user = new UserModel();
        user.setGoalDailyDistance(2000);
        assertEquals((Integer) 2000, user.getGoalDailyDistance());
    }
    //----------------------------------------------------------------------------------------------
    @Test
    public void getGoalDailySteps2_isCorrect(){
        UserModel user = new UserModel();
        user.setGoalDailySteps(5000);
        assertEquals((Integer)5000, user.getGoalDailySteps());
    }
    //----------------------------------------------------------------------------------------------
    @Test
    public void getGoalDailyCalories2_isCorrect(){
        UserModel user = new UserModel();
        user.setGoalDailyCalories(1000);
        assertEquals((Integer) 1000, user.getGoalDailyCalories());
    }
    //----------------------------------------------------------------------------------------------
    @Test
    public void getChallengeSteps_isCorrect(){
        UserModel user = new UserModel();
        Integer[] challengeSteps = {100,103,105};
        user.setChallengeSteps(challengeSteps);
        assertEquals(challengeSteps, user.getChallengeSteps());
    }
    //----------------------------------------------------------------------------------------------
    @Test
    public void getChallengeSteps2_isCorrect(){
        UserModel user = new UserModel();
        user.setChallengeSteps("2000");
        assertEquals((Integer) 2000, user.getChallengeSteps()[0]);
    }
    //----------------------------------------------------------------------------------------------
    @Test
    public void setChallengeSteps22_isCorrect(){
        UserModel user = new UserModel();
        ChallengeModel challenge = new ChallengeModel();
        Integer[] challengeSteps = {1000, 2000, 1500};
        user.setChallengeSteps(1, 3000);

        Integer[] retrievedSteps = user.getChallengeSteps();
        assertEquals(Integer.valueOf(3000), retrievedSteps[1]);
    }
    //----------------------------------------------------------------------------------------------
    @Test
    public void getChallengeCalories_isCorrect(){
        UserModel user = new UserModel();
        Integer[] challengeCalories = {1020,1203,1205};
        user.setChallengeCalories(challengeCalories);
        assertEquals(challengeCalories, user.getChallengeCalories());
    }
    //----------------------------------------------------------------------------------------------
    @Test
    public void getChallengeCalories2_isCorrect(){
        UserModel user = new UserModel();
        user.setChallengeCalories("250");
        assertEquals((Integer) 250, user.getChallengeCalories()[0]);
    }
    //----------------------------------------------------------------------------------------------
    @Test
    public void setChallengeCalories22_isCorrect(){
        UserModel user = new UserModel();
        user.setChallengeCalories(0, 3000);

        assertEquals((Integer)3000, user.getChallengeCalories()[0]);
    }
    //----------------------------------------------------------------------------------------------
    @Test
    public void getChallengeDistance_isCorrect(){
        UserModel user = new UserModel();
        Integer[] challengeDistance = {1007,1703,1075};
        user.setChallengeDistance(challengeDistance);
        assertEquals(challengeDistance, user.getChallengeDistance());
    }
    //----------------------------------------------------------------------------------------------
    @Test
    public void getChallengeDistance2_isCorrect(){
        UserModel user = new UserModel();
        user.setChallengeDistance("8000");
        assertEquals((Integer) 8000, user.getChallengeDistance()[0]);
    }
    //----------------------------------------------------------------------------------------------
    @Test
    public void setChallengeDistance22_isCorrect(){
        UserModel user = new UserModel();
        ChallengeModel challenge = new ChallengeModel();
        Integer[] challengeDistance = {1000, 2000, 1500};
        user.setChallengeDistance(1, 3000);

        assertEquals((Integer)3000, user.getChallengeDistance()[1]);
    }
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
    //----------------------------------------------------------------------------------------------
    @Test
    public void testToString() {
        UserModel user = new UserModel();
        user.setUserID("U001");
        user.setLastName("John");
        user.setPrenume("Doe");
        user.setGender("M");
        user.setEmail("john.doe@example.com");
        user.setPhone("1234567890");
        user.setPassword("password");
        user.setIsPrivateProfile(true);

        String result = user.toString();
        String expected = "UserModel{" +
                "m_sUserID='U001'" +
                ", m_sNume='John'" +
                ", m_sPrenume='Doe'" +
                ", m_sGen='M'" +
                ", m_sEmail='john.doe@example.com'" +
                ", m_sPhone='1234567890'" +
                ", m_sPassword='password'" +
                ", m_bPrivateProfile=true" +
                ", m_lFriends=[]" +
                ", m_lChallenges=[]" +
                ", m_iVarsta=0" +
                ", m_fGreutate=0.0" +
                ", m_fInaltime=0.0" +
                ", m_cUserStats=null" +
                "}";
        assertEquals(expected, result);
    }
    //----------------------------------------------------------------------------------------------
    @Test
    public void addFriend_isCorrect() {
        UserModel user = new UserModel();

        UserModel friend = new UserModel();
        boolean result = user.addFriend(friend);

        assertTrue(result);
        assertEquals(1, user.getFriends().size());
        assertTrue(user.getFriends().contains(friend));

        boolean resultDuplicate = user.addFriend(friend);

        assertFalse(resultDuplicate);
        assertEquals(1, user.getFriends().size());
    }
    //----------------------------------------------------------------------------------------------
    @Test
    public void deleteFriend_isCorrect() {
        UserModel user1 = new UserModel();

        user1.setUserID("user1");
        UserModel user2 = new UserModel();
        user2.setUserID("user2");
        UserModel user3 = new UserModel();
        user3.setUserID("user3");

        user1.addFriend(user2);
        assertTrue(user1.getFriends().contains(user2));
        assertFalse(user1.deleteFriend(user3));

        assertTrue(user1.getFriends().contains(user2));
        assertTrue(user1.deleteFriend(user2));
        assertFalse(user1.getFriends().contains(user2));
    }
    //----------------------------------------------------------------------------------------------
    /*@Test
    public void addDailySteps_isCorrect() {
        UserModel user = new UserModel();
        user.addDailySteps(100);
        assertEquals((Integer) 100, user.getDailySteps()[0]);
    }*/
}
