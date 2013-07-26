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
import org.IGNOU.ePortfolio.Model.User;
import org.IGNOU.ePortfolio.DAO.RegistrationDao;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author IGNOU Team
 * @version 1 Modified by IGNOU Team
 */
public class StudRegistrationAction extends ActionSupport implements ModelDriven<User> {

    final Logger logger = Logger.getLogger(this.getClass());
     private User userModel=new User();
    private RegistrationDao rgDao = new RegistrationDao();
    private String msg, RegSuccess = getText("msg.RegSuccess");
    private String pwd,password;

    @Override
    public String execute() throws Exception {
        password=userModel.getPassword();
        pwd=DigestUtils.md5Hex(password);
        userModel.setPassword(pwd);
        rgDao.UserSaveByRegModel(userModel);
        msg = RegSuccess;
        return SUCCESS;
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
}