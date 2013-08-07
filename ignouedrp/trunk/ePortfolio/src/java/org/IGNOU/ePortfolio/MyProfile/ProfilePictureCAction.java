/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.MyProfile;

import com.opensymphony.xwork2.ActionSupport;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.IGNOU.ePortfolio.Action.UserSession;
import org.IGNOU.ePortfolio.DAO.UserListDao;
import org.IGNOU.ePortfolio.Model.User;

/**
 *
 * @author Amit
 */
public class ProfilePictureCAction extends ActionSupport {

    private String user_id = new UserSession().getUserInSession();
    private UserListDao uldao = new UserListDao();
    private String msg;
    private String infoSaved = getText("msg.infoSaved");
    private List<User> userlist;
    private byte[] picture;
    private File upUserImage;
    private long registrationId;
    private String upUserImageContentType;

    public String UpdateProfilePicture() {
        registrationId = uldao.UserListByUserId(user_id).iterator().next().getRegistrationId();
        uldao.UserPictureUpdate(registrationId, getPicture(), upUserImageContentType);
        return SUCCESS;
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

    /**
     * @return the picture
     */
    public byte[] getPicture() {
        try {

            FileInputStream fis = new FileInputStream(getUpUserImage());
            ByteArrayOutputStream bs = new ByteArrayOutputStream();
            byte[] bf = new byte[1024];
            try {
                for (int readNum; (readNum = fis.read(bf)) != -1;) {
                    bs.write(bf, 0, readNum);
                }
            } catch (IOException ex) {
                Logger.getLogger(ProfilePictureCAction.class.getName()).log(Level.SEVERE, null, ex);
            }
            picture = bs.toByteArray();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ProfilePictureCAction.class.getName()).log(Level.SEVERE, null, ex);
        }

        return this.picture;
    }

    /**
     * @param picture the picture to set
     */
    public void setPicture(byte[] picture) {
        this.picture = picture;
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
     * @return the upUserImage
     */
    public File getUpUserImage() {
        return upUserImage;
    }

    /**
     * @param upUserImage the upUserImage to set
     */
    public void setUpUserImage(File upUserImage) {
        this.upUserImage = upUserImage;
    }

    /**
     * @return the userlist
     */
    public List<User> getUserlist() {
        return userlist;
    }

    /**
     * @param userlist the userlist to set
     */
    public void setUserlist(List<User> userlist) {
        this.userlist = userlist;
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
     * @return the upUserImageContentType
     */
    public String getUpUserImageContentType() {
        return upUserImageContentType;
    }

    /**
     * @param upUserImageContentType the upUserImageContentType to set
     */
    public void setUpUserImageContentType(String upUserImageContentType) {
        this.upUserImageContentType = upUserImageContentType;
    }
}
