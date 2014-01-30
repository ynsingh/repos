/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.Action;

import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import static org.IGNOU.ePortfolio.Action.ReadPropertiesFile.*;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author Amit
 */
public class FileUploadCommon extends ActionSupport implements Serializable  {

    private static final long serialVersionUID = 1L;
    private String Filepath = ReadPropertyFile("Filepath");
    private InputStream fis;
    private String requestorId, recordProof;
    final Logger logger = Logger.getLogger(this.getClass());

    public boolean UploadFile(File ufile, String ufileName, String Filepath) {
        logger.warn(this + "Uploading File: " + ufileName + " File Size(kilobytes) is: " + (ufile.length()) / 1024 + " Path: " + Filepath);
        if (Filepath != null && ufile != null && ufileName != null) {
            File folder = new File(Filepath);
            if (!folder.exists()) {
                folder.mkdir();
            }
            File fileToCreate = new File(Filepath, ufileName);
            try {
                FileUtils.copyFile(ufile, fileToCreate);
                logger.warn(this + "   File Uploaded Successfully.");
            } catch (IOException io) {
                logger.error("IoException Occured With file" + fileToCreate + "    " + io);
            }
            logger.warn("File Uploaded at" + Filepath);
            return true;
        } else {
            return false;
        }
    }

    public String DownloadFile() {
        try {
            recordProof = Filepath + "/" + requestorId + "/" + recordProof;
            fis = new FileInputStream(new File(recordProof));
        } catch (Exception e) {
            logger.warn("Exception Occured in file Downloading filepath is" + recordProof, e);
        }
        return SUCCESS;
    }

    /**
     * @return the fis
     */
    public InputStream getFis() {
        return fis;
    }

    /**
     * @param fis the fis to set
     */
    public void setFis(InputStream fis) {
        this.fis = fis;
    }

    /**
     * @return the Filepath
     */
    public String getFilepath() {
        return Filepath;
    }

    /**
     * @param Filepath the Filepath to set
     */
    public void setFilepath(String Filepath) {
        this.Filepath = Filepath;
    }

    /**
     * @return the requestorId
     */
    public String getRequestorId() {
        return requestorId;
    }

    /**
     * @param requestorId the requestorId to set
     */
    public void setRequestorId(String requestorId) {
        this.requestorId = requestorId;
    }

    /**
     * @return the recordProof
     */
    public String getRecordProof() {
        return recordProof;
    }

    /**
     * @param recordProof the recordProof to set
     */
    public void setRecordProof(String recordProof) {
        this.recordProof = recordProof;
    }
}
