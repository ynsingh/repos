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
 *  notice, this  UserDocsListByUserId of conditions and the following disclaimer.
 *
 *  Redistribution in binary form must reproducuce the above copyright
 *  notice, this UserDocsListByUserId of conditions and the following disclaimer in
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
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import org.IGNOU.ePortfolio.Action.UserSession;
import org.IGNOU.ePortfolio.DAO.FileDAO;
import org.IGNOU.ePortfolio.Model.Userdocs;
import org.apache.log4j.Logger;

/**
 *
 * @author IGNOU Team
 * @version 1 on 17 aug 2011 Last Modified on 20 oct 2011 by IGNOU Team
 */
public class FileListAction extends ActionSupport {

    private static final long serialVersionUID = 1L;
    private Userdocs imagelist;
    private List<Userdocs> imagelistlist;
    private FileDAO dao = new FileDAO();
    private long fileid;
    private String user_id = new UserSession().getUserInSession();
    private String filename, description, filepath;
    private InputStream fis;
    private String filepathN, filename1, filepath1, filetype;
    private String filedate;
    final Logger logger = Logger.getLogger(this.getClass());
    private String msg;
    private String infoDeleted = getText("msg.infoDeleted");
    private String infoUpdated = getText("msg.infoUpdated");

    public String fetch() {
        imagelistlist = dao.UserDocsListByUserId(user_id);
        return "success";

    }

    public String delete() {
        DeleteFile();
        dao.UserDocsDeleteByFileId(getFileid());
        msg = infoDeleted;
        return SUCCESS;
    }

    public String edit() {
        imagelistlist = dao.UserDocsListByFileId(getFileid());
        return SUCCESS;
    }

    public String update() {
        imagelistlist = dao.UserDocsListByFileId(getFileid());
        filename1 = (imagelistlist.iterator().next().getFilename());
        filepath1 = (imagelistlist.iterator().next().getFilepath());
        filetype = imagelistlist.iterator().next().getFiletype();
        if (filetype != null) {
            filepathN = filepath1 + filename1 + filetype;
        } else {
            filepathN = filepath1 + filename1;
        }
        File oldfile = new File(filepathN);

        dao.UserDocsUpdate(getFileid(), getFilename(), getDescription(), new Date().toString());

        imagelistlist = dao.UserDocsListByFileId(getFileid());
        filename = imagelistlist.iterator().next().getFilename();
        filepath = imagelistlist.iterator().next().getFilepath();
        filetype = imagelistlist.iterator().next().getFiletype();
        if (filetype != null) {
            filepath = filepath + filename + filetype;
        } else {
            filepath = filepath + filename;
        }
        oldfile.renameTo(new File(filepath));
        msg = infoUpdated;
        return SUCCESS;
    }

    public boolean DeleteFile() {
        imagelistlist = dao.UserDocsListByFileId(getFileid());
        filename = imagelistlist.iterator().next().getFilename();
        filepath = imagelistlist.iterator().next().getFilepath();
        filetype = imagelistlist.iterator().next().getFiletype();
        filepath = filepath + filename;
        if (new File(filepath).isDirectory()) {
            new File(filepath).delete();
            msg = infoDeleted;
            return true;
        } else {
            filepath = filepath + filetype;
            new File(filepath).delete();
            msg = infoDeleted;
            return true;
        }

    }

    public String download() throws IOException {
        try {
            imagelistlist = dao.UserDocsListByFileId(getFileid());
            filename = imagelistlist.iterator().next().getFilename();
            filepath = imagelistlist.iterator().next().getFilepath();
            filetype = imagelistlist.iterator().next().getFiletype();
            filepath = filepath + filename + filetype;
            fis = new FileInputStream(new File(filepath));
        } catch (Exception e) {
           
        }
        return SUCCESS;
    }

    /**
     * @return the imagelist
     */
    public Userdocs getImagelist() {
        return imagelist;
    }

    /**
     * @param imagelist the imagelist to set
     */
    public void setImagelist(Userdocs imagelist) {
        this.imagelist = imagelist;
    }

    /**
     * @return the imagelistlist
     */
    public List<Userdocs> getImagelistlist() {
        return imagelistlist;
    }

    /**
     * @param imagelistlist the imagelistlist to set
     */
    public void setImagelistlist(List<Userdocs> imagelistlist) {
        this.imagelistlist = imagelistlist;
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

    /**
     * @return the filename
     */
    public String getFilename() {
        return filename;
    }

    /**
     * @param filename the filename to set
     */
    public void setFilename(String filename) {
        this.filename = filename;
    }

    /**
     * @return the fis
     */
    public InputStream getFis() {
        return fis;
    }

    /**
     * @return the filetype
     */
    public String getFiletype() {
        return filetype;
    }

    /**
     * @param filetype the filetype to set
     */
    public void setFiletype(String filetype) {
        this.filetype = filetype;
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
     * @return the infoDeleted
     */
    public String getInfoDeleted() {
        return infoDeleted;
    }

    /**
     * @param infoDeleted the infoDeleted to set
     */
    public void setInfoDeleted(String infoDeleted) {
        this.infoDeleted = infoDeleted;
    }

    /**
     * @return the infoUpdated
     */
    public String getInfoUpdated() {
        return infoUpdated;
    }

    /**
     * @param infoUpdated the infoUpdated to set
     */
    public void setInfoUpdated(String infoUpdated) {
        this.infoUpdated = infoUpdated;
    }
}
