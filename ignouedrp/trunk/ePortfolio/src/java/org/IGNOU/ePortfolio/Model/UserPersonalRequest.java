package org.IGNOU.ePortfolio.Model;
// Generated Nov 29, 2012 1:31:37 PM by Hibernate Tools 3.2.1.GA


import java.io.File;
import java.util.Date;

/**
 * UserPersonalRequest generated by hbm2java
 */
public class UserPersonalRequest  implements java.io.Serializable {


     private Integer requestId;
     private UserList user;
     private String requestType;
     private String reason;
     private String newRecord;
     private Date requestDate;
     private Date updatedDate;
     private String recordArchive;
     private String recordProof;
     private Boolean status;

    public UserPersonalRequest() {
    }

    public UserPersonalRequest(UserList user, String requestType, String reason, String newRecord, Date requestDate, Date updatedDate, String recordArchive, String recordProof, Boolean status) {
       this.user = user;
       this.requestType = requestType;
       this.reason = reason;
       this.newRecord = newRecord;
       this.requestDate = requestDate;
       this.updatedDate = updatedDate;
       this.recordArchive = recordArchive;
       this.recordProof = recordProof;
       this.status = status;
    }
   
    public Integer getRequestId() {
        return this.requestId;
    }
    
    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }
    
    public String getRequestType() {
        return this.requestType;
    }
    
    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }
    public String getReason() {
        return this.reason;
    }
    
    public void setReason(String reason) {
        this.reason = reason;
    }
    public String getNewRecord() {
        return this.newRecord;
    }
    
    public void setNewRecord(String newRecord) {
        this.newRecord = newRecord;
    }
    public Date getRequestDate() {
        return this.requestDate;
    }
    
    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }
    public Date getUpdatedDate() {
        return this.updatedDate;
    }
    
    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }
    public String getRecordArchive() {
        return this.recordArchive;
    }
    
    public void setRecordArchive(String recordArchive) {
        this.recordArchive = recordArchive;
    }
    public String getRecordProof() {
        return this.recordProof;
    }
    
    public void setRecordProof(String recordProof) {
        this.recordProof = recordProof;
    }
    public Boolean getStatus() {
        return this.status;
    }
    
    public void setStatus(Boolean status) {
        this.status = status;
    }

     /**
     * @return the user
     */
    public UserList getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(UserList user) {
        this.user = user;
    }




}


