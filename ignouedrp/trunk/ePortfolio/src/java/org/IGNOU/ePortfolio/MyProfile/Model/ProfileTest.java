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
package org.IGNOU.ePortfolio.MyProfile.Model;
// Generated Oct 11, 2011 11:42:23 AM by Hibernate Tools 3.2.1.GA



/**
 * ProfileTest generated by hbm2java
 */
public class ProfileTest  implements java.io.Serializable {
    private static final long serialVersionUID = 1L;


     private long testId;
     private String userId;
     private String tname;
     private Integer score;
     private String tdate;
     private String tdescription;

    public ProfileTest() {
    }

	
    public ProfileTest(long testId) {
        this.testId = testId;
    }
    public ProfileTest(long testId, String userId, String tname, Integer score, String tdate, String tdescription) {
       this.testId = testId;
       this.userId = userId;
       this.tname = tname;
       this.score = score;
       this.tdate = tdate;
       this.tdescription = tdescription;
    }
   
    public long getTestId() {
        return this.testId;
    }
    
    public void setTestId(long testId) {
        this.testId = testId;
    }
    public String getUserId() {
        return this.userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getTname() {
        return this.tname;
    }
    
    public void setTname(String tname) {
        this.tname = tname;
    }
    public Integer getScore() {
        return this.score;
    }
    
    public void setScore(Integer score) {
        this.score = score;
    }
    public String getTdate() {
        return this.tdate;
    }
    
    public void setTdate(String tdate) {
        this.tdate = tdate;
    }
    public String getTdescription() {
        return this.tdescription;
    }
    
    public void setTdescription(String tdescription) {
        this.tdescription = tdescription;
    }




}


