/**
 *
 * Copyright (c) 2011 eGyankosh, IGNOU, New Delhi. All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.
 *
 * Redistribution in binary form must reproducuce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 *
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED WARRANTIES,
 * INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 * FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL eGyankosh,
 * IGNOU OR ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL,SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA,
 * OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 *
 * Contributors: Members of eGyankosh, IGNOU, New Delhi.
 *
 */
package org.IGNOU.ePortfolio.Registration;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import java.io.Serializable;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import net.tanesha.recaptcha.ReCaptchaImpl;
import net.tanesha.recaptcha.ReCaptchaResponse;
import org.IGNOU.ePortfolio.DAO.RegistrationDao;
import org.IGNOU.ePortfolio.Model.User;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import static org.IGNOU.ePortfolio.Action.RandomTokenString.*;
import static org.IGNOU.ePortfolio.Action.ReadPropertiesFile.ReadPropertyFile;
import org.IGNOU.ePortfolio.Action.sendMail;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author IGNOU Team
 * @version 1 Modified by IGNOU Team
 */
public class RegistrationAction extends ActionSupport implements Serializable  , ModelDriven<User> {

    private static final long serialVersionUID = 1L;
    final Logger logger = Logger.getLogger(this.getClass());
    private User userModel = new User();
    private RegistrationDao rgDao = new RegistrationDao();
    private String msg, RegSuccess = getText("msg.RegSuccess"), RegFail = getText("msg.RegFailure");
    private String pwd, password, programmeId_widget, instituteId_widget;
    private Integer instituteId, departmentId, programmeId;
    private String remoteAddr, recaptcha_challenge_field, recaptcha_response_field;
    HttpServletRequest request = ServletActionContext.getRequest();
    private String uuid, type, univRegNo, empId;
    private sendMail mailFuncuion = new sendMail();
    private String mailFrom = ReadPropertyFile("mailUser");
    private String PublicUrl = ReadPropertyFile("regVerificationUrl");
    private String mailSubject = getText("msg.Reg.Mail");

    @Override
    public String execute() throws Exception {
        remoteAddr = request.getRemoteAddr();
        ReCaptchaImpl reCaptcha = new ReCaptchaImpl();
        reCaptcha.setPrivateKey("6LcyTNwSAAAAAOhc_dWi9uuXrMAABMInwaiuwvC3");
        ReCaptchaResponse reCaptchaResponse = reCaptcha.checkAnswer(remoteAddr, recaptcha_challenge_field, recaptcha_response_field);
        if (reCaptchaResponse.isValid()) {
            password = userModel.getPassword();
            pwd = DigestUtils.md5Hex(password);
            userModel.setPassword(pwd);
            setUuid(generateTokenString(10));
            userModel.setUuid(uuid);
            userModel.setEmailVerify(Boolean.FALSE);
            userModel.setRegTime(new Date());
            userModel.setAccessIp(getRemoteAddr().toString());
            if (type.equals("faculty")) {
                userModel.setUnivRegNo(empId);
                userModel.setRole(type);
            }
            if (type.equals("student")) {
                userModel.setUnivRegNo(univRegNo);
                userModel.setRole(type);
            }
            rgDao.UserSaveByRegModel(userModel);
            msg = RegSuccess;
            /*Send email to user on Registered email ID.*/
            /*Formate Message for mail*/
            String Message = "<p>Dear " + userModel.getFname() + " " + userModel.getMname() + " " + userModel.getLname() + ",</p><p>Please click <a href=" + PublicUrl + "?emailId=" + userModel.getEmailId() + "&registrationId=" + userModel.getRegistrationId() + "&uuid=" + userModel.getUuid() + ">here</a> to complete your registration.</p><p>Thanks</p><p>Admin, eGyankosh</p><p>IGNOU</p>";
            /*Formating End*/
            mailFuncuion.SendMail(mailFrom, userModel.getEmailId(), mailSubject, Message);
            return SUCCESS;
        } else {
            msg = RegFail;
            return INPUT;
        }
    }

    @Override
    public User getModel() {
        return userModel;
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
     * @return the RegSuccess
     */
    public String getRegSuccess() {
        return RegSuccess;
    }

    /**
     * @param RegSuccess the RegSuccess to set
     */
    public void setRegSuccess(String RegSuccess) {
        this.RegSuccess = RegSuccess;
    }

    /**
     * @return the userModel
     */
    public User getUserModel() {
        return userModel;
    }

    /**
     * @param userModel the userModel to set
     */
    public void setUserModel(User userModel) {
        this.userModel = userModel;
    }

    /**
     * @return the pwd
     */
    public String getPwd() {
        return pwd;
    }

    /**
     * @param pwd the pwd to set
     */
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the programmeId_widget
     */
    public String getProgrammeId_widget() {
        return programmeId_widget;
    }

    /**
     * @param programmeId_widget the programmeId_widget to set
     */
    public void setProgrammeId_widget(String programmeId_widget) {
        this.programmeId_widget = programmeId_widget;
    }

    /**
     * @return the instituteId_widget
     */
    public String getInstituteId_widget() {
        return instituteId_widget;
    }

    /**
     * @param instituteId_widget the instituteId_widget to set
     */
    public void setInstituteId_widget(String instituteId_widget) {
        this.instituteId_widget = instituteId_widget;
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

    /**
     * @return the departmentId
     */
    public Integer getDepartmentId() {
        return departmentId;
    }

    /**
     * @param departmentId the departmentId to set
     */
    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    /**
     * @return the programmeId
     */
    public Integer getProgrammeId() {
        return programmeId;
    }

    /**
     * @param programmeId the programmeId to set
     */
    public void setProgrammeId(Integer programmeId) {
        this.programmeId = programmeId;
    }

    /**
     * @return the remoteAddr
     */
    public String getRemoteAddr() {
        return remoteAddr;
    }

    /**
     * @param remoteAddr the remoteAddr to set
     */
    public void setRemoteAddr(String remoteAddr) {
        this.remoteAddr = remoteAddr;
    }

    /**
     * @return the recaptcha_challenge_field
     */
    public String getRecaptcha_challenge_field() {
        return recaptcha_challenge_field;
    }

    /**
     * @param recaptcha_challenge_field the recaptcha_challenge_field to set
     */
    public void setRecaptcha_challenge_field(String recaptcha_challenge_field) {
        this.recaptcha_challenge_field = recaptcha_challenge_field;
    }

    /**
     * @return the recaptcha_response_field
     */
    public String getRecaptcha_response_field() {
        return recaptcha_response_field;
    }

    /**
     * @param recaptcha_response_field the recaptcha_response_field to set
     */
    public void setRecaptcha_response_field(String recaptcha_response_field) {
        this.recaptcha_response_field = recaptcha_response_field;
    }

    /**
     * @return the RegFail
     */
    public String getRegFail() {
        return RegFail;
    }

    /**
     * @param RegFail the RegFail to set
     */
    public void setRegFail(String RegFail) {
        this.RegFail = RegFail;
    }

    /**
     * @return the uuid
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * @param uuid the uuid to set
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * @return the mailFrom
     */
    public String getMailFrom() {
        return mailFrom;
    }

    /**
     * @param mailFrom the mailFrom to set
     */
    public void setMailFrom(String mailFrom) {
        this.mailFrom = mailFrom;
    }

    /**
     * @return the mailSubject
     */
    public String getMailSubject() {
        return mailSubject;
    }

    /**
     * @param mailSubject the mailSubject to set
     */
    public void setMailSubject(String mailSubject) {
        this.mailSubject = mailSubject;
    }

    /**
     * @return the PublicUrl
     */
    public String getPublicUrl() {
        return PublicUrl;
    }

    /**
     * @param PublicUrl the PublicUrl to set
     */
    public void setPublicUrl(String PublicUrl) {
        this.PublicUrl = PublicUrl;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the univRegNo
     */
    public String getUnivRegNo() {
        return univRegNo;
    }

    /**
     * @param univRegNo the univRegNo to set
     */
    public void setUnivRegNo(String univRegNo) {
        this.univRegNo = univRegNo;
    }

    /**
     * @return the empId
     */
    public String getEmpId() {
        return empId;
    }

    /**
     * @param empId the empId to set
     */
    public void setEmpId(String empId) {
        this.empId = empId;
    }
}