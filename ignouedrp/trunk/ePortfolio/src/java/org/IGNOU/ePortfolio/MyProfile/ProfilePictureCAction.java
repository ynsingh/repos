/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.MyProfile;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.IGNOU.ePortfolio.Action.FileUploadCommon;
import org.IGNOU.ePortfolio.Action.UserSession;
import org.IGNOU.ePortfolio.DAO.UserProgrammeDao;
import org.IGNOU.ePortfolio.Model.ProfilePicture;
import org.IGNOU.ePortfolio.Model.User;
import org.IGNOU.ePortfolio.DAO.ProfilePictureDAO;
import static org.IGNOU.ePortfolio.Action.ReadPropertiesFile.*;

/**
 *
 * @author Amit
 */
public class ProfilePictureCAction extends ActionSupport implements ModelDriven<ProfilePicture>{

   
    private  ProfilePicture pplst = new ProfilePicture();
    private String user_id = new UserSession().getUserInSession();
    private ProfilePictureDAO ppDao = new ProfilePictureDAO();
    private String msg;
    private String infoSaved = getText("msg.infoSaved");
    private ProfilePicture ppl = new ProfilePicture();
    private List<ProfilePicture> ppll;
    private Long picId;
    private byte[] picture;
    private String userId, filetype, upUserImageContentType;
    private File upUserImage;
    private FileUploadCommon fup=new FileUploadCommon();
    private UserProgrammeDao userdao=new UserProgrammeDao();
    private List<User> userlist;
    private String FilePath = ReadPropertyFile("Filepath");
    private String picUploadpath;
    private long registrationId;
   
    
    
    public String UpdateProfilePicture() throws Exception {
           ppll=ppDao.ProfilePictureListByUserId(user_id);
           userlist=userdao.UserListByUserId(user_id);
           registrationId=userlist.iterator().next().getRegistrationId();
           if (ppll.isEmpty()) {
            pplst.setPicture(getPicture());
            ppDao.ProfilePictureSave(pplst);
            picUploadpath = FilePath+ "/" + user_id + "/";
           fup.UploadFile(upUserImage,(user_id.substring(0, 4))+".png", picUploadpath);          
           return SUCCESS;
          } else {
           picId=ppll.iterator().next().getPicId();
           ppDao.ProfilePictureUpdate(picId, getPicture(), getUser_id(), getFiletype());
           picUploadpath = FilePath+ "/" + user_id + "/";
            fup.UploadFile(upUserImage,(user_id.substring(0, 4))+".png", picUploadpath);       
            return SUCCESS;
        }
   }
        
    @Override
    public ProfilePicture getModel() {
        pplst.setUserId(getUser_id());
        return pplst;
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
     * @return the ppDao
     */
    public ProfilePictureDAO getPpDao() {
        return ppDao;
    }

    /**
     * @param ppDao the ppDao to set
     */
    public void setPpDao(ProfilePictureDAO ppDao) {
        this.ppDao = ppDao;
    }

    /**
     * @return the ppl
     */
    public ProfilePicture getPpl() {
        return ppl;
    }

    /**
     * @param ppl the ppl to set
     */
    public void setPpl(ProfilePicture ppl) {
        this.ppl = ppl;
    }

    /**
     * @return the ppll
     */
    public List<ProfilePicture> getPpll() {
        return ppll;
    }

    /**
     * @param ppll the ppll to set
     */
    public void setPpll(List<ProfilePicture> ppll) {
        this.ppll = ppll;
    }

    /**
     * @return the picId
     */
    public Long getPicId() {
        return picId;
    }

    /**
     * @param picId the picId to set
     */
    public void setPicId(Long picId) {
        this.picId = picId;
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
     * @return the filetype
     */
    public String getFiletype() {
        filetype = getUpUserImageContentType().replace("image/", "");
        return filetype;
    }


    /**
     * @param filetype the filetype to set
     */
    public void setFiletype(String filetype) {
        this.filetype = filetype;
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
     * @return the pplst
     */
    public ProfilePicture getPplst() {
        return pplst;
    }

    /**
     * @param pplst the pplst to set
     */
    public void setPplst(ProfilePicture pplst) {
        this.pplst = pplst;
    }

    /**
     * @return the fup
     */
    public FileUploadCommon getFup() {
        return fup;
    }

    /**
     * @param fup the fup to set
     */
    public void setFup(FileUploadCommon fup) {
        this.fup = fup;
    }

    /**
     * @return the FilePath
     */
    public String getFilePath() {
        return FilePath;
    }

    /**
     * @param FilePath the FilePath to set
     */
    public void setFilePath(String FilePath) {
        this.FilePath = FilePath;
    }

    /**
     * @return the picUploadpath
     */
    public String getPicUploadpath() {
        return picUploadpath;
    }

    /**
     * @param picUploadpath the picUploadpath to set
     */
    public void setPicUploadpath(String picUploadpath) {
        this.picUploadpath = picUploadpath;
    }

    /**
     * @return the userdao
     */
    public UserProgrammeDao getUserdao() {
        return userdao;
    }

    /**
     * @param userdao the userdao to set
     */
    public void setUserdao(UserProgrammeDao userdao) {
        this.userdao = userdao;
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

   
}
