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
package org.IGNOU.ePortfolio.Registration;

import com.opensymphony.xwork2.ActionSupport;
import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.util.List;
import org.IGNOU.ePortfolio.DAO.UserProgrammeDao;
import org.IGNOU.ePortfolio.Model.User;

/**
 *
 * @author IGNOU Team
 */
public class Check extends ActionSupport {

    private UserProgrammeDao updao = new UserProgrammeDao();
    private List<User> stRegList;
    private String emailId;
    private InputStream inputStream;
    private Integer instituteId;

    public InputStream getInputStream() {
        return inputStream;
    }

    public String Avail() {

        stRegList = updao.CheckUserExistByUserId(emailId);
        if (stRegList.isEmpty()) {
            inputStream = new StringBufferInputStream("User-Id is Available");

            return SUCCESS;
        } else {
            inputStream = new StringBufferInputStream("This User-Id is Already Registered");

            return SUCCESS;
        }
    }

    /**
     * @return the stRegList
     */
    public List<User> getStRegList() {
        return stRegList;
    }

    /**
     * @param stRegList the stRegList to set
     */
    public void setStRegList(List<User> stRegList) {
        this.stRegList = stRegList;
    }

    /**
     * @return the emailId
     */
    public String getEmailId() {
        return emailId;
    }

    /**
     * @param emailId the emailId to set
     */
    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    /**
     * @return the instituteId
     */
    public Integer getInstituteId() {
        return instituteId;
    }

    /**
     * @param instituteId the instituteId to set
     */
    public void setInstituteId(Integer instituteId) {
        this.instituteId = instituteId;
    }
}
