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
import com.opensymphony.xwork2.ModelDriven;
import org.IGNOU.ePortfolio.Action.UserSession;
import org.IGNOU.ePortfolio.DAO.TestScoreDao;
import org.IGNOU.ePortfolio.Model.ProfileTest;

/**
 * @version 1.1
 * @since 12-Oct-2011
 * @author IGNOU Team XML Validation are implemented by IGNOU Team on
 * 14-Oct-2011
 */
public class TestScoreAction extends ActionSupport implements ModelDriven<Object> {

    private static final long serialVersionUID = 1L;
    private TestScoreDao dao = new TestScoreDao();
    private ProfileTest test = new ProfileTest();
    private String user_id = new UserSession().getUserInSession();
    private String tname;
    private Integer score;
    private String tdate;
    private String tdescription;
    private String msg;
    private String infoSaved = getText("msg.infoSaved");

    public TestScoreAction() {
    }

    @Override
    public String execute() throws Exception {
        try {
            dao.ProfileTestSave(test);
            msg = infoSaved;
        } catch (Exception e) {
            msg = "Data has not been Saved Successfully, Error: " + e.toString();

        }
        return SUCCESS;
    }

    @Override
    public Object getModel() {

        getTest().setUserId(user_id);
        return getTest();
    }

    /**
     * @return the test
     */
    public ProfileTest getTest() {
        return test;
    }

    /**
     * @param test the test to set
     */
    public void setTest(ProfileTest test) {
        this.test = test;
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
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * @param msg the msg to set
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * @return the infoSaved
     */
    public String getInfoSaved() {
        return infoSaved;
    }

    /**
     * @param infoSaved the infoSaved to set
     */
    public void setInfoSaved(String infoSaved) {
        this.infoSaved = infoSaved;
    }
}