/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.Action;

import com.opensymphony.xwork2.ActionSupport;
import java.util.List;
import org.IGNOU.ePortfolio.DAO.ResetLoginPasswordDao;
import org.IGNOU.ePortfolio.DAO.UserProgrammeDao;
import org.IGNOU.ePortfolio.Model.User;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 *
 * @author Vinay
 */
public class ResetLoginPasswordAction extends ActionSupport {

    private String user_id = new UserSession().getUserInSession();
    private User u = new User();
    private ResetLoginPasswordDao dao = new ResetLoginPasswordDao();
    private UserProgrammeDao upDao=new UserProgrammeDao();
    private List<User> userList;
    private String oldPassword, passwordField, passwordConfirmField, msg;
    private String oldPwdHash,pwdFieldHash;
    private long registrationId;
    final Logger logger = Logger.getLogger(this.getClass());

    public ResetLoginPasswordAction() {
    }

    public String ResetOldPassword() {
        userList = upDao.UserListByUserId(user_id);
        oldPwdHash=DigestUtils.md5Hex(oldPassword);
        if (oldPwdHash.equals(userList.iterator().next().getPassword())) {
            registrationId = userList.iterator().next().getRegistrationId();
            pwdFieldHash=DigestUtils.md5Hex(passwordField);
            dao.UserUpdateByRegistrationIdPassword(registrationId, pwdFieldHash);
            PropertyConfigurator.configure("log4j.properties");
            logger.warn("User change his password"+user_id+"registration Id"+registrationId);
            return SUCCESS;
        } else {
            msg = "You Have Entered Wrong Password";
            return INPUT;
        }

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
     * @return the u
     */
    public User getU() {
        return u;
    }

    /**
     * @param u the u to set
     */
    public void setU(User u) {
        this.u = u;
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
     * @return the passwordField
     */
    public String getPasswordField() {
        return passwordField;
    }

    /**
     * @param passwordField the passwordField to set
     */
    public void setPasswordField(String passwordField) {
        this.passwordField = passwordField;
    }

    /**
     * @return the passwordConfirmField
     */
    public String getPasswordConfirmField() {
        return passwordConfirmField;
    }

    /**
     * @param passwordConfirmField the passwordConfirmField to set
     */
    public void setPasswordConfirmField(String passwordConfirmField) {
        this.passwordConfirmField = passwordConfirmField;
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
     * @return the oldPassword
     */
    public String getOldPassword() {
        return oldPassword;
    }

    /**
     * @param oldPassword the oldPassword to set
     */
    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
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
