package pojo.hibernate;
// Generated May 30, 2013 2:31:14 PM by Hibernate Tools 3.2.1.GA


import java.util.Date;

/**
 * UserMessage generated by hbm2java
 */
public class UserMessage  implements java.io.Serializable {


     private Integer umId;
     private Erpmusers erpmusersByUmFromErpmuId;
     private ErpmGenMaster erpmGenMaster;
     private Erpmusers erpmusersByUmToErpmuId;
     private String umMessage;
     private String umActionName;
     private Date umRequestSubmissionDate;
     private String umReqType;
     private Short umReqTypeId;
     private Integer umMsgId;
     private Date umActionDate;

    public UserMessage() {
    }

    public UserMessage(Erpmusers erpmusersByUmFromErpmuId, ErpmGenMaster erpmGenMaster, Erpmusers erpmusersByUmToErpmuId, String umMessage, String umActionName, Date umRequestSubmissionDate, String umReqType, Short umReqTypeId, Integer umMsgId, Date umActionDate) {
       this.erpmusersByUmFromErpmuId = erpmusersByUmFromErpmuId;
       this.erpmGenMaster = erpmGenMaster;
       this.erpmusersByUmToErpmuId = erpmusersByUmToErpmuId;
       this.umMessage = umMessage;
       this.umActionName = umActionName;
       this.umRequestSubmissionDate = umRequestSubmissionDate;
       this.umReqType = umReqType;
       this.umReqTypeId = umReqTypeId;
       this.umMsgId = umMsgId;
       this.umActionDate = umActionDate;
    }
   
    public Integer getUmId() {
        return this.umId;
    }
    
    public void setUmId(Integer umId) {
        this.umId = umId;
    }
    public Erpmusers getErpmusersByUmFromErpmuId() {
        return this.erpmusersByUmFromErpmuId;
    }
    
    public void setErpmusersByUmFromErpmuId(Erpmusers erpmusersByUmFromErpmuId) {
        this.erpmusersByUmFromErpmuId = erpmusersByUmFromErpmuId;
    }
    public ErpmGenMaster getErpmGenMaster() {
        return this.erpmGenMaster;
    }
    
    public void setErpmGenMaster(ErpmGenMaster erpmGenMaster) {
        this.erpmGenMaster = erpmGenMaster;
    }
    public Erpmusers getErpmusersByUmToErpmuId() {
        return this.erpmusersByUmToErpmuId;
    }
    
    public void setErpmusersByUmToErpmuId(Erpmusers erpmusersByUmToErpmuId) {
        this.erpmusersByUmToErpmuId = erpmusersByUmToErpmuId;
    }
    public String getUmMessage() {
        return this.umMessage;
    }
    
    public void setUmMessage(String umMessage) {
        this.umMessage = umMessage;
    }
    public String getUmActionName() {
        return this.umActionName;
    }
    
    public void setUmActionName(String umActionName) {
        this.umActionName = umActionName;
    }
    public Date getUmRequestSubmissionDate() {
        return this.umRequestSubmissionDate;
    }
    
    public void setUmRequestSubmissionDate(Date umRequestSubmissionDate) {
        this.umRequestSubmissionDate = umRequestSubmissionDate;
    }
    public String getUmReqType() {
        return this.umReqType;
    }
    
    public void setUmReqType(String umReqType) {
        this.umReqType = umReqType;
    }
    public Short getUmReqTypeId() {
        return this.umReqTypeId;
    }
    
    public void setUmReqTypeId(Short umReqTypeId) {
        this.umReqTypeId = umReqTypeId;
    }
    public Integer getUmMsgId() {
        return this.umMsgId;
    }
    
    public void setUmMsgId(Integer umMsgId) {
        this.umMsgId = umMsgId;
    }
    public Date getUmActionDate() {
        return this.umActionDate;
    }
    
    public void setUmActionDate(Date umActionDate) {
        this.umActionDate = umActionDate;
    }




}


