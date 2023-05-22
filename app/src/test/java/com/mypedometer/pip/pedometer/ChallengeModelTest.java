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
        UserModel user2 = new UserModel();
        challenge.m_lCandidates.add(user1);
        challenge.m_lCandidates.add(user2);

        String candidatesID = challenge.getCandidatesID();

        assertEquals("", candidatesID);
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

}
