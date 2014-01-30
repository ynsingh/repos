/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.Registration;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import static org.IGNOU.ePortfolio.Action.ReadPropertiesFile.ReadPropertyFile;
import org.IGNOU.ePortfolio.Action.sendMail;
import org.IGNOU.ePortfolio.DAO.RegistrationDao;
import org.IGNOU.ePortfolio.Model.User;
import org.apache.log4j.Logger;


/**
 *
 * @author Vinay
 */
public class RegistrationTokenValidation extends ActionSupport implements Serializable  {

    private static final long serialVersionUID = 1L;
    final Logger logger = Logger.getLogger(this.getClass());
    private Map session = ActionContext.getContext().getSession();
    private User uModel = new User();
    private RegistrationDao dao = new RegistrationDao();
    private String emailId, uuid, msg, tocVerSuccess = getText("msg.RegTocVerified");
    private long registrationId;
    private Boolean emailVerify;
    private sendMail mailFuncuion = new sendMail();
    private String mailFrom = ReadPropertyFile("mailUser");
    private String PublicUrl = ReadPropertyFile("regVerificationUrl");
    private String mailSubject = getText("msg.Reg.Mail");

    public RegistrationTokenValidation() {
    }

    @Override
    public String execute() throws Exception {
      
        if (dao.UserUpdate(registrationId, emailId, uuid) == true) {
            logger.warn("Email Valicated Successfully, with Email: " + emailId + " Token: " + uuid + " Registration ID: " + registrationId + " on Date: " + new Date());
            String Message = "<p>Dear " + emailId + ",</p><p>You have Successfully Registered with ePortfolio. </p><p>Thanks</p><p>Admin, eGyankosh</p><p>IGNOU</p>";
            mailFuncuion.SendMail(mailFrom, emailId, mailSubject, Message);
            msg = tocVerSuccess;
            return SUCCESS;
        } else {
            logger.warn("Email Valicated Failed, with Email: " + emailId + " Token: " + uuid + " Registration ID: " + registrationId + " on Date: " + new Date());
            return ERROR;
        }

    }

    /**
     * @return the uModel
     */
    public User getuModel() {
        return uModel;
    }

    /**
     * @param uModel the uModel to set
     */
    public void setuModel(User uModel) {
        this.uModel = uModel;
    }

    /**
     * @return the dao
     */
    public RegistrationDao getDao() {
        return dao;
    }

    /**
     * @param dao the dao to set
     */
    public void setDao(RegistrationDao dao) {
        this.dao = dao;
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

    /**
     * @return the emailVerify
     */
    public Boolean getEmailVerify() {
        return emailVerify;
    }

    /**
     * @param emailVerify the emailVerify to set
     */
    public void setEmailVerify(Boolean emailVerify) {
        this.emailVerify = emailVerify;
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
     * @return the tocVerSuccess
     */
    public String getTocVerSuccess() {
        return tocVerSuccess;
    }

    /**
     * @param tocVerSuccess the tocVerSuccess to set
     */
    public void setTocVerSuccess(String tocVerSuccess) {
        this.tocVerSuccess = tocVerSuccess;
    }
}