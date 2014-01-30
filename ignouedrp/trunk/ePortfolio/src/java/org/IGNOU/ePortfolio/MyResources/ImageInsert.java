/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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
package org.IGNOU.ePortfolio.MyResources;

import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import org.IGNOU.ePortfolio.Action.FileUploadCommon;
import static org.IGNOU.ePortfolio.Action.ReadPropertiesFile.*;
import org.IGNOU.ePortfolio.Action.UserSession;
import org.IGNOU.ePortfolio.DAO.ResourceDao;
import org.IGNOU.ePortfolio.Model.Userdocs;
import org.apache.log4j.Logger;

/**
 *
 * @author IGNOU Team
 * * @version 1.0 on 19 aug 2011
 */
public class ImageInsert extends ActionSupport implements Serializable  {

    private static final long serialVersionUID = 1L;
    final Logger logger = Logger.getLogger(this.getClass());
    private String user_id = new UserSession().getUserInSession();
    private ResourceDao rdao = new ResourceDao();
    private Userdocs ud = new Userdocs();
    private String filepath = ReadPropertyFile("Filepath") + "/" + user_id + "/";
    private String filedate, filetype, filename, description;
    private long fileid;
    private Long size;
    private File userImage;
    private String userImageFileName;
    private String msg;
    private String infoSaved = getText("msg.infoSaved");

    public String dataInsert() throws IOException {

        new FileUploadCommon().UploadFile(userImage, getFilename() + getFiletype(), getFilepath());
        rdao.saveUserDocs(user_id, getSize(), getFiletype(), getFilename(), getFilepath(), getDescription(), new Date().toString());
        msg = infoSaved;
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
     * @return the fileid
     */
    public long getFileid() {
        return fileid;
    }

    /**
     * @param fileid the fileid to set
     */
    public void setFileid(long fileid) {
        this.fileid = fileid;
    }

    /**
     * @return the size
     */
    public Long getSize() {
        size = (userImage.length()) / 1024;
        return size;
    }

    /**
     * @param size the size to set
     */
    public void setSize(Long size) {

        this.size = size;
    }

    /**
     * @return the filepath
     */
    public String getFilepath() {
        return filepath;
    }

    /**
     * @param filepath the filepath to set
     */
    public void setFilepath(String filepath) {


        this.filepath = filepath;
    }

    /**
     * @return the filetype
     */
    public String getFiletype() {
        filetype = userImageFileName.substring(userImageFileName.indexOf(".", 4));
        return filetype;
    }

    /**
     * @param filetype the filetype to set
     */
    public void setFiletype(String filetype) {

        this.filetype = filetype;
    }

    /**
     * @return the filename
     */
    public String getFilename() {
        if (filename.isEmpty()) {
            filename = userImageFileName;
            return filename;
        } else {

            return filename;
        }

    }

    /**
     * @param filename the filename to set
     */
    public void setFilename(String filename) {

        this.filename = filename;

    }

    /**
     * @return the userImage
     */
    public File getUserImage() {
        return userImage;
    }

    /**
     * @param userImage the userImage to set
     */
    public void setUserImage(File userImage) {
        this.userImage = userImage;
    }

    /**
     * @return the userImageFileName
     */
    public String getUserImageFileName() {
        userImageFileName = userImage.getName();
        return userImageFileName;

    }

    /**
     * @param userImageFileName the userImageFileName to set
     */
    public void setUserImageFileName(String userImageFileName) {
        this.userImageFileName = userImageFileName;
    }

    /**
     * @return the filedate
     */
    public String getFiledate() {
        return filedate;
    }

    /**
     * @param filedate the filedate to set
     */
    public void setFiledate(String filedate) {
        this.filedate = filedate;
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
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
