/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.Requests;

import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.util.Date;
import org.IGNOU.ePortfolio.Action.FileUploadCommon;
import static org.IGNOU.ePortfolio.Action.ReadPropertiesFile.*;
import org.IGNOU.ePortfolio.Action.UserSession;
import org.IGNOU.ePortfolio.DAO.RequestDao;

/**
 *
 * @author Vinay
 */
public class ChangeRequestAction extends ActionSupport  {

    private String user_id = new UserSession().getUserInSession();
    private RequestDao dao = new RequestDao();
    private String msg, reqMsg = getText("msg.Request");
    private String FilePath = ReadPropertyFile("Filepath");
    private File uploadProof;
    private String FileUpPath,uploadProofFileName;
    private FileUploadCommon fupload=new FileUploadCommon();
    private String requestType;
     private String reason;
     private String newRecord;
     private Date requestDate;
     private String recordProof;
     private Boolean status;
    

    public ChangeRequestAction() {
    }

    @Override
    public String execute() throws Exception {
            if(uploadProof!=null)
           {
           FileUpPath=FilePath+"/"+user_id+"/";
           fupload.UploadFile(uploadProof, uploadProofFileName, FileUpPath);
           dao.UserPersonalRequestSave(user_id,requestType,reason,newRecord,new Date(),uploadProofFileName, Boolean.FALSE);
           msg = reqMsg;
        return SUCCESS;
        }
        else
           {
              setUploadProofFileName("null");
               dao.UserPersonalRequestSave(user_id,requestType,reason,newRecord,new Date(),uploadProofFileName, Boolean.FALSE);
          return SUCCESS;
        }
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
     * @return the reqMsg
     */
    public String getReqMsg() {
        return reqMsg;
    }

    /**
     * @param reqMsg the reqMsg to set
     */
    public void setReqMsg(String reqMsg) {
        this.reqMsg = reqMsg;
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
     * @return the FileUpPath
     */
    public String getFileUpPath() {
        return FileUpPath;
    }

    /**
     * @param FileUpPath the FileUpPath to set
     */
    public void setFileUpPath(String FileUpPath) {
        this.FileUpPath = FileUpPath;
    }

    /**
     * @return the uploadProof
     */
    public File getUploadProof() {
        return uploadProof;
    }

    /**
     * @param uploadProof the uploadProof to set
     */
    public void setUploadProof(File uploadProof) {
        this.uploadProof = uploadProof;
    }

    /**
     * @return the uploadProofFileName
     */
    public String getUploadProofFileName() {
        return uploadProofFileName;
    }

    /**
     * @param uploadProofFileName the uploadProofFileName to set
     */
    public void setUploadProofFileName(String uploadProofFileName) {
        this.uploadProofFileName = uploadProofFileName;
    }

    /**
     * @return the requestType
     */
    public String getRequestType() {
        return requestType;
    }

    /**
     * @param requestType the requestType to set
     */
    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    /**
     * @return the reason
     */
    public String getReason() {
        return reason;
    }

    /**
     * @param reason the reason to set
     */
    public void setReason(String reason) {
        this.reason = reason;
    }

    /**
     * @return the newRecord
     */
    public String getNewRecord() {
        return newRecord;
    }

    /**
     * @param newRecord the newRecord to set
     */
    public void setNewRecord(String newRecord) {
        this.newRecord = newRecord;
    }

    /**
     * @return the requestDate
     */
    public Date getRequestDate() {
        return requestDate;
    }

    /**
     * @param requestDate the requestDate to set
     */
    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
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

    /**
     * @return the status
     */
    public Boolean getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(Boolean status) {
        this.status = status;
    }
}
