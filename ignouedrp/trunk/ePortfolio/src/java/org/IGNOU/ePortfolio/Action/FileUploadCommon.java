/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.Action;

import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import static org.IGNOU.ePortfolio.Action.ReadPropertiesFile.*;

/**
 *
 * @author Amit
 */
public class FileUploadCommon extends ActionSupport{
    private String Filepath=ReadPropertyFile("Filepath");
    private InputStream fis;
    private String requestorId,recordProof;
    final Logger logger = Logger.getLogger(this.getClass());


    
    
    public boolean UploadFile(File ufile,String ufileName,String Filepath) {
          if(Filepath!=null && ufile!=null && ufileName!=null ){ 
            
           File folder = new File(Filepath);
            if (!folder.exists()) {
                folder.mkdir();
            }
            File fileToCreate = new File(Filepath,ufileName);
            try{
            FileUtils.copyFile(ufile, fileToCreate);
            }catch(IOException io){
            PropertyConfigurator.configure("log4j.properties");
            logger.warn("IoException Occured With file"+fileToCreate, io);
            }
            PropertyConfigurator.configure("log4j.properties");
            logger.warn("File Uploaded at"+Filepath);
            
             return true;
          }
          else{
             return false;
          }
    }
    public String DownloadFile(){
        try{
       recordProof=Filepath+"/"+requestorId+"/"+recordProof;
          fis = new FileInputStream(new File(recordProof));
        }
        catch(Exception e){
           PropertyConfigurator.configure("log4j.properties");
            logger.warn("Exception Occured in file Downloading filepath is"+recordProof, e);
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
