package com.myapp.struts.hbm;
// Generated 8 Aug, 2011 11:27:34 AM by Hibernate Tools 3.2.1.GA

import java.sql.Timestamp;




/**
 * AcqRecievingDetails generated by hbm2java
 */
public class AcqRecievingDetails  implements java.io.Serializable {


     private AcqRecievingDetailsId id;
     private Integer titleId;
     private String unitPrice;
     private Integer recievedCopies;
     private Integer pendingCopies;
     private String approvalType;
     private Integer controlNo;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }
 private String vendorId;
     private String status;
     private Timestamp time;
    public AcqRecievingDetails() {
    }

	
    public AcqRecievingDetails(AcqRecievingDetailsId id) {
        this.id = id;
    }
    public AcqRecievingDetails(AcqRecievingDetailsId id, Integer titleId, String unitPrice, Integer recievedCopies, Integer pendingCopies, String approvalType, Integer controlNo,String vendor,String status,Timestamp time) {
       this.id = id;
       this.titleId = titleId;
       this.unitPrice = unitPrice;
       this.recievedCopies = recievedCopies;
       this.pendingCopies = pendingCopies;
       this.approvalType = approvalType;
       this.controlNo = controlNo;
       this.status=status;
       this.vendorId=vendor;
       this.time=time;
    }
   
    public AcqRecievingDetailsId getId() {
        return this.id;
    }
    
    public void setId(AcqRecievingDetailsId id) {
        this.id = id;
    }
    public Integer getTitleId() {
        return this.titleId;
    }
    
    public void setTitleId(Integer titleId) {
        this.titleId = titleId;
    }
    public String getUnitPrice() {
        return this.unitPrice;
    }
    
    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }
    public Integer getRecievedCopies() {
        return this.recievedCopies;
    }
    
    public void setRecievedCopies(Integer recievedCopies) {
        this.recievedCopies = recievedCopies;
    }
    public Integer getPendingCopies() {
        return this.pendingCopies;
    }
    
    public void setPendingCopies(Integer pendingCopies) {
        this.pendingCopies = pendingCopies;
    }
    public String getApprovalType() {
        return this.approvalType;
    }
    
    public void setApprovalType(String approvalType) {
        this.approvalType = approvalType;
    }
    public Integer getControlNo() {
        return this.controlNo;
    }
    
    public void setControlNo(Integer controlNo) {
        this.controlNo = controlNo;
    }




}


