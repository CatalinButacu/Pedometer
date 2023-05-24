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
public class ChallengeModelTest {
    @Test
    public void getChallengeID_isCorrent() {
        ChallengeModel challengeModel = new ChallengeModel();
        challengeModel.setChallengeID("11");
        String challengeID = challengeModel.getChallengeID();
        assertEquals("11", challengeID);
    }
    //----------------------------------------------------------------
    @Test
    public void getCreatorID_isCorrent() {
        ChallengeModel challengeModel = new ChallengeModel();
        challengeModel.setCreatorID("3");
        String creatorID = challengeModel.getCreatorID();
        assertEquals("3", creatorID);
    }
    //----------------------------------------------------------------
    @Test
    public void getNameChallenge_isCorrent() {
        ChallengeModel challenge = new ChallengeModel();
        challenge.setNameChallenge("Challenge 1");
        String challengeName = challenge.getNameChallenge();
        assertEquals("Challenge 1", challengeName);
    }
    //----------------------------------------------------------------
    @Test
    public void getStatusChallenge_isCorrent() {
        ChallengeModel challenge = new ChallengeModel();
        challenge.setStatusChallenge(ChallengeModel.Status.Created);
        ChallengeModel.Status challengeStatus = challenge.getStatusChallenge();
        assertEquals(ChallengeModel.Status.Created, challengeStatus);
    }
    //----------------------------------------------------------------
    @Test
    public void getDateStart_isCorrent() {
        ChallengeModel challenge = new ChallengeModel();
        challenge.setDateStart("2018-01-01");
        String dateStart = challenge.getDateStart();
        assertEquals("2018-01-01", dateStart);
    }
    //----------------------------------------------------------------
    @Test
    public void getDateEnd_isCorrent() {
        ChallengeModel challenge = new ChallengeModel();
        challenge.setDateEnd("2018-01-01");
        String dateEnd = challenge.getDateEnd();
        assertEquals("2018-01-01", dateEnd);
    }
    //----------------------------------------------------------------
    @Test
    public void getCandidatesID_isCorrect(){
        ChallengeModel challenge = new ChallengeModel();

        UserModel user1 = new UserModel();
        user1.setUserID("1001");
        UserModel user2 = new UserModel();
        user2.setUserID("1002");
        challenge.m_lCandidates.add(user1);
        challenge.m_lCandidates.add(user2);

        String candidatesID = challenge.getCandidatesID();

        assertEquals("1001,1002,", candidatesID);
    }
    //----------------------------------------------------------------
    @Test
    public void insertCandidate_isCorrect(){
        ChallengeModel challenge = new ChallengeModel();
        UserModel user = new UserModel();
        boolean isInserted = challenge.insertCandidate(user);

        assertTrue(isInserted);
        assertTrue(challenge.m_lCandidates.contains(user));

        boolean isInsertedAgain = challenge.insertCandidate(user);
        assertFalse(isInsertedAgain);
    }
    //----------------------------------------------------------------
    @Test
    public void testSetStatusChallenge() {
        ChallengeModel challenge = new ChallengeModel();

        challenge.setStatusChallenge("Created");
        assertEquals(ChallengeModel.Status.Created, challenge.getStatusChallenge());

        challenge.setStatusChallenge("Started");
        assertEquals(ChallengeModel.Status.Started, challenge.getStatusChallenge());

        challenge.setStatusChallenge("Finished");
        assertEquals(ChallengeModel.Status.Finished, challenge.getStatusChallenge());

        challenge.setStatusChallenge("Deleted");
        assertEquals(ChallengeModel.Status.Deleted, challenge.getStatusChallenge());

        challenge.setStatusChallenge("InvalidStatus");
        assertEquals(ChallengeModel.Status.Created, challenge.getStatusChallenge());
    }
    //----------------------------------------------------------------
    @Test
    public void testSetCandidatesID() {
        // Creați un obiect ChallengeModel
        ChallengeModel challenge = new ChallengeModel();

        // Testarea când sCandidatesID este gol
        boolean result1 = challenge.setCandidatesID("");
        assertFalse(result1);
        assertTrue(challenge.m_lCandidates.isEmpty());

        // Testarea când sCandidatesID conține un singur candidat
        boolean result2 = challenge.setCandidatesID("123");
        assertTrue(result2);
        assertEquals(1, challenge.m_lCandidates.size());
        assertEquals("123", challenge.m_lCandidates.get(0).getUserID());

        // Testarea când sCandidatesID conține mai mulți candidați separate prin virgulă
        boolean result3 = challenge.setCandidatesID("456,789,012");
        assertTrue(result3);
        assertEquals(4, challenge.m_lCandidates.size());
        assertEquals("123", challenge.m_lCandidates.get(0).getUserID());
        assertEquals("456", challenge.m_lCandidates.get(1).getUserID());
        assertEquals("789", challenge.m_lCandidates.get(2).getUserID());
        assertEquals("012", challenge.m_lCandidates.get(3).getUserID());
    }
    //----------------------------------------------------------------
    @Test
    public void testDeleteCandidates() {
        // Creați un obiect ChallengeModel
        ChallengeModel challenge = new ChallengeModel();

        // Adăugați câțiva candidați înainte de testare
        UserModel user1 = new UserModel();
        UserModel user2 = new UserModel();
        UserModel user3 = new UserModel();
        challenge.m_lCandidates.add(user1);
        challenge.m_lCandidates.add(user2);
        challenge.m_lCandidates.add(user3);

        // Testarea când user este prezent în lista de candidați
        boolean result1 = challenge.deleteCandidates(user2);
        assertTrue(result1);
        assertFalse(challenge.m_lCandidates.contains(user2));

        // Testarea când user nu este prezent în lista de candidați
        UserModel user4 = new UserModel();
        boolean result2 = challenge.deleteCandidates(user4);
        assertFalse(result2);

        // Verificarea stării finale a listei de candidați
        assertEquals(2, challenge.m_lCandidates.size());
        assertTrue(challenge.m_lCandidates.contains(user1));
        assertFalse(challenge.m_lCandidates.contains(user2));
        assertTrue(challenge.m_lCandidates.contains(user3));
    }
    //----------------------------------------------------------------
    @Test
    public void testTimeRemain() {
        ChallengeModel challenge = new ChallengeModel();

        challenge.setDateStart("2023-05-01 12:00:00");
        challenge.setDateEnd("2023-05-05 18:30:00");

        String result = challenge.timeRemain();
        assertEquals("0-0-4 6:30:0", result);
    }
    //----------------------------------------------------------------
    @Test
    public void testSynchronizeDB() {
        ChallengeModel challenge = new ChallengeModel();
        boolean result = challenge.synchronizeDB();
        assertFalse(result);
    }
    //----------------------------------------------------------------
    @Test
    public void testSetDateStart_isCorrect(){
        ChallengeModel challenge = new ChallengeModel();
        assertTrue( challenge.setDateStart("2023-05-01"));
    }
    //----------------------------------------------------------------
    @Test
    public void testSetDateEnd_isCorrect(){
        ChallengeModel challenge = new ChallengeModel();
        assertTrue( challenge.setDateEnd("2023-05-01"));
    }
    //----------------------------------------------------------------
    @Test
    public void testToString() {
        ChallengeModel challenge = new ChallengeModel();
        challenge.setChallengeID("C001");
        challenge.setCreatorID("U001");
        challenge.setNameChallenge("Challenge 1");
        challenge.setStatusChallenge(ChallengeModel.Status.Created);
        challenge.setDateStart("2023-05-01");
        challenge.setDateEnd("2023-05-10");

        String result = challenge.toString();
        String expected = "ChallengeModel{" +
                "m_sChallengeID='C001'" +
                ", m_sCreatorID='U001'" +
                ", m_sChallengeName='Challenge 1'" +
                ", m_sChallengeStatus=Created" +
                ", m_sDateStart='2023-05-01'" +
                ", m_sDateEnd='2023-05-10'" +
                ", m_lCandidates=[]" +
                "}";
        assertEquals(expected, result);
    }
}
