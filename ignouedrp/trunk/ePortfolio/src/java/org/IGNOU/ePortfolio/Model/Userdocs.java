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
package org.IGNOU.ePortfolio.Model;

import java.io.File;
import java.io.Serializable;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author IGNOU Team
 */
public class Userdocs implements Serializable {

    private String user_id;
    private long fileid;
    private Long size;
    private String filepath, filetype, filename;
    private File userImage;
    private String userImageFileName, filedate;

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
        filetype = userImageFileName.substring(userImageFileName.indexOf(".", 4));
        if (filetype.equals(".jpg") || filetype.equals(".gif") || filetype.equals(".png")
                || filetype.equals(".txt") || filetype.equals(".doc") || (filetype.equals(".docx")
                || filetype.equals(".pdf") || filetype.equals(".java") || filetype.equals(".ppt"))) {

            filepath = filepath ;
        } else {
            filepath = filepath ;
        }
        try {
            File folder = new File(filepath);
            if (!folder.exists()) {
                folder.mkdir();
            }
            File fileToCreate = new File(filepath, filename + filetype);
            FileUtils.copyFile(userImage, fileToCreate);
        } catch (Exception e) {
            System.out.println("Exception" + e);
        }
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
}
