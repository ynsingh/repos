package com.myapp.struts.hbm;
// Generated Jun 12, 2011 11:35:01 AM by Hibernate Tools 3.2.1.GA



/**
 * AcqOrder1 generated by hbm2java
 */
public class AcqOrder1  implements java.io.Serializable {


     private AcqOrder1Id id;
     private AcqOrderHeader acqOrderHeader;
     private Integer controlNo;
     private Integer approvalItemId;
     private String recievingStatus;
     private String recievingDate;
          private String recievingNo;

    public String getRecievingNo() {
        return recievingNo;
    }

    public void setRecievingNo(String recievingNo) {
        this.recievingNo = recievingNo;
    }

    public AcqOrder1() {
    }

	
    public AcqOrder1(AcqOrder1Id id, AcqOrderHeader acqOrderHeader) {
        this.id = id;
        this.acqOrderHeader = acqOrderHeader;
    }
    public AcqOrder1(AcqOrder1Id id, AcqOrderHeader acqOrderHeader, Integer controlNo, Integer approvalItemId, String recievingStatus, String recievingDate,String recievingNo) {
       this.id = id;
       this.acqOrderHeader = acqOrderHeader;
       this.controlNo = controlNo;
       this.approvalItemId = approvalItemId;
       this.recievingStatus = recievingStatus;
       this.recievingDate = recievingDate;
       this.recievingNo=recievingNo;
    }
   
    public AcqOrder1Id getId() {
        return this.id;
    }
    
    public void setId(AcqOrder1Id id) {
        this.id = id;
    }
    public AcqOrderHeader getAcqOrderHeader() {
        return this.acqOrderHeader;
    }
    
    public void setAcqOrderHeader(AcqOrderHeader acqOrderHeader) {
        this.acqOrderHeader = acqOrderHeader;
    }
    public Integer getControlNo() {
        return this.controlNo;
    }
    
    public void setControlNo(Integer controlNo) {
        this.controlNo = controlNo;
    }
    public Integer getApprovalItemId() {
        return this.approvalItemId;
    }
    
    public void setApprovalItemId(Integer approvalItemId) {
        this.approvalItemId = approvalItemId;
    }
    public String getRecievingStatus() {
        return this.recievingStatus;
    }
    
    public void setRecievingStatus(String recievingStatus) {
        this.recievingStatus = recievingStatus;
    }
    public String getRecievingDate() {
        return this.recievingDate;
    }
    
    public void setRecievingDate(String recievingDate) {
        this.recievingDate = recievingDate;
    }




}


