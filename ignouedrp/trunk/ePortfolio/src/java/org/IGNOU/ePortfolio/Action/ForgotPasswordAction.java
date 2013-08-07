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
package org.IGNOU.ePortfolio.Action;

import com.opensymphony.xwork2.ActionSupport;
import java.util.List;
import java.util.Random;
import static org.IGNOU.ePortfolio.Action.ReadPropertiesFile.*;
import org.IGNOU.ePortfolio.DAO.ForgotPasswordDao;
import org.IGNOU.ePortfolio.DAO.ResetLoginPasswordDao;
import org.IGNOU.ePortfolio.DAO.UserProgrammeDao;
import org.IGNOU.ePortfolio.Model.User;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 *
 * @author IGNOU Team
 */
public class ForgotPasswordAction extends ActionSupport {

    private String email_id;
    final Logger logger = Logger.getLogger(this.getClass());
    private static final long serialVersionUID = 1L;
    private ForgotPasswordDao fpDao = new ForgotPasswordDao();
    private UserProgrammeDao updao = new UserProgrammeDao();
    private ResetLoginPasswordDao dao = new ResetLoginPasswordDao();
    private List<User> userList;
    private String msg, recoverpassword, From, Password, to, subject;
    private String pwdHash;
    private long registrationId;

    public String CheckRegisteredUser() throws Exception {
        From = ReadPropertyFile("mailFrom");
        Password = ReadPropertyFile("mailPassword");
        if (fpDao.FindRegisteredUserByEmailId(email_id)) {
            to = getEmail_id();
            userList = updao.UserListByUserId(email_id);
            registrationId = userList.iterator().next().getRegistrationId();
            Random rand = new Random();
            int num = rand.nextInt(10000);
            recoverpassword = "eport" + num;
            pwdHash = DigestUtils.md5Hex(recoverpassword);
            dao.UserUpdateByRegistrationIdPassword(registrationId, pwdHash);
            subject = "Recovered Password";
            msg = "Your Login Details for ePortfolio with New Password"
                    + "\n UserName: " + getEmail_id()
                    + "\n Password: " + recoverpassword
                    + "\n Thanks for using ePortfolio";
            new sendMail().SendMail(From, to, subject, msg);
            PropertyConfigurator.configure("log4j.properties");
            logger.warn("User ReSet His Password" + to + "New Password is" + recoverpassword);
            return SUCCESS;
        } else {
            msg = "Email Id is Not registered, Please Register to Login";
            PropertyConfigurator.configure("log4j.properties");
            logger.warn("Non register Trying to recover password" + email_id);
            return ERROR;
        }
    }

    /**
     * @return the email_id
     */
    public String getEmail_id() {
        return email_id;
    }

    /**
     * @param email_id the email_id to set
     */
    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }

    /**
     * @return the fpDao
     */
    public ForgotPasswordDao getFpDao() {
        return fpDao;
    }

    /**
     * @param fpDao the fpDao to set
     */
    public void setFpDao(ForgotPasswordDao fpDao) {
        this.fpDao = fpDao;
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
     * @return the recoverpassword
     */
    public String getRecoverpassword() {
        return recoverpassword;
    }

    /**
     * @param recoverpassword the recoverpassword to set
     */
    public void setRecoverpassword(String recoverpassword) {
        this.recoverpassword = recoverpassword;
    }

    /**
     * @return the From
     */
    public String getFrom() {
        return From;
    }

    /**
     * @param From the From to set
     */
    public void setFrom(String From) {
        this.From = From;
    }

    /**
     * @return the Password
     */
    public String getPassword() {
        return Password;
    }

    /**
     * @param Password the Password to set
     */
    public void setPassword(String Password) {
        this.Password = Password;
    }

    /**
     * @return the to
     */
    public String getTo() {
        return to;
    }

    /**
     * @param to the to to set
     */
    public void setTo(String to) {
        this.to = to;
    }

    /**
     * @return the subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * @param subject the subject to set
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * @return the dao
     */
    public ResetLoginPasswordDao getDao() {
        return dao;
    }

    /**
     * @param dao the dao to set
     */
    public void setDao(ResetLoginPasswordDao dao) {
        this.dao = dao;
    }

    /**
     * @return the userList
     */
    public List<User> getUserList() {
        return userList;
    }

    /**
     * @param userList the userList to set
     */
    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    /**
     * @return the registrationId
     */
    public long getRegistrationId() {
        return registrationId;
    }

    /**
     * @param registrationId the registrationId to set
     */
    public void setRegistrationId(long registrationId) {
        this.registrationId = registrationId;
    }
}
