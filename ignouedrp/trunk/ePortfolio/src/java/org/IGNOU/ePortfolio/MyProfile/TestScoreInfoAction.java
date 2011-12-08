/*
 * 
 *  Copyright (c) 2011 eGyankosh, IGNOU, New Delhi.
 *  All Rights Reserved.
 *
 *  Redistribution and use in source and binary forms, with or
 *  without modification, are permitted provided that the following
 *  conditions are met:
 *
 *  Redistributions of source code must retain the above copyright
 *  notice, this  list of conditions and the following disclaimer.
 *
 *  Redistribution in binary form must reproducuce the above copyright
 *  notice, this list of conditions and the following disclaimer in
 *  the documentation and/or other materials provided with the
 *  distribution.
 *
 *
 *  THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 *  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 *  OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 *  DISCLAIMED.  IN NO EVENT SHALL eGyankosh, IGNOU OR ITS CONTRIBUTORS BE LIABLE
 *  FOR ANY DIRECT, INDIRECT, INCIDENTAL,SPECIAL, EXEMPLARY, OR
 *  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT
 *  OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR
 *  BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 *  WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 *  OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 *  EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 *
 *  Contributors: Members of eGyankosh, IGNOU, New Delhi.
 *
 */


package org.IGNOU.ePortfolio.MyProfile;

import com.opensymphony.xwork2.ActionSupport;
import java.util.List;
import org.IGNOU.ePortfolio.Action.UserSession;
import org.IGNOU.ePortfolio.MyProfile.Dao.TestScoreDao;
import org.IGNOU.ePortfolio.MyProfile.Model.ProfileTest;

/**
 *
 * @author Vinay
 */
public class TestScoreInfoAction extends ActionSupport {

    private static final long serialVersionUID = 1L;

    /**
     * @return the serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
    private TestScoreDao dao = new TestScoreDao();
    private ProfileTest Score = new ProfileTest();
    private String user_id = new UserSession().getUserInSession();
    private List<ProfileTest> ScoreList;
    private long testId;
    private String userId;
    private String tname;
    private Integer score;
    private String tdate;
    private String tdescription;

    public TestScoreInfoAction() {
    }

    public String ShowInfo() throws Exception {
        ScoreList = dao.ShowTestInfo(getUser_id());
        if (ScoreList.isEmpty()) {
            // return SUCCESS;
            return INPUT;
        } else {
            //return ERROR;
            return SUCCESS;
        }
    }

    public String EditInfo() throws Exception {
        ScoreList = dao.EditTestInfo(testId);
        return SUCCESS;
    }

    public String UpdateInfo() throws Exception {
        dao.UpdateTestInfo(testId, userId, tname, score, tdate, tdescription);
        return SUCCESS;
    }
    public String DeleteInfo() throws Exception
    {
        dao.DeleteTestInfo(getTestId());
        return SUCCESS;
    }

    /**
     * @return the dao
     */
    public TestScoreDao getDao() {
        return dao;
    }

    /**
     * @param dao the dao to set
     */
    public void setDao(TestScoreDao dao) {
        this.dao = dao;
    }

    /**
     * @return the Score
     */
    public ProfileTest getTest() {
        return Score;
    }

    /**
     * @param test 
     */
    public void setTest(ProfileTest test) {
        this.Score = test;
    }

    /**
     * @return the user_id
     */
    public String getUser_id() {
        return user_id;
    }

    /**
     * @param user_id the user_id to set
     */
    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    /**
     * @return the testId
     */
    public long getTestId() {
        return testId;
    }

    /**
     * @param testId the testId to set
     */
    public void setTestId(long testId) {
        this.testId = testId;
    }

    /**
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return the tname
     */
    public String getTname() {
        return tname;
    }

    /**
     * @param tname the tname to set
     */
    public void setTname(String tname) {
        this.tname = tname;
    }

    /**
     * @return the score
     */
    public Integer getScore() {
        return score;
    }

    /**
     * @param score the score to set
     */
    public void setScore(Integer score) {
        this.score = score;
    }

    /**
     * @return the tdate
     */
    public String getTdate() {
        return tdate;
    }

    /**
     * @param tdate the tdate to set
     */
    public void setTdate(String tdate) {
        this.tdate = tdate;
    }

    /**
     * @return the tdescription
     */
    public String getTdescription() {
        return tdescription;
    }

    /**
     * @param tdescription the tdescription to set
     */
    public void setTdescription(String tdescription) {
        this.tdescription = tdescription;
    }

    /**
     * @return the ScoreList
     */
    public List<ProfileTest> getScoreList() {
        return ScoreList;
    }

    /**
     * @param ScoreList the ScoreList to set
     */
    public void setScoreList(List<ProfileTest> ScoreList) {
        this.ScoreList = ScoreList;
    }
}
