package com.myapp.struts.hbm;
// Generated May 2, 2011 12:00:18 PM by Hibernate Tools 3.2.1.GA



/**
 * CirCheckin generated by hbm2java
 */
public class CirCheckin  implements java.io.Serializable {


     private CirCheckinId id;
     private CirCheckout cirCheckout;
     private String memberId;
     private String returningDate;
     private String documentId;
     private String damagedStatus;
     private String lossStatus;
     private String reason;

    public CirCheckin() {
    }

	
    public CirCheckin(CirCheckinId id, CirCheckout cirCheckout) {
        this.id = id;
        this.cirCheckout = cirCheckout;
    }
    public CirCheckin(CirCheckinId id, CirCheckout cirCheckout, String memberId, String returningDate, String documentId, String damagedStatus, String lossStatus, String reason) {
       this.id = id;
       this.cirCheckout = cirCheckout;
       this.memberId = memberId;
       this.returningDate = returningDate;
       this.documentId = documentId;
       this.damagedStatus = damagedStatus;
       this.lossStatus = lossStatus;
       this.reason = reason;
    }
   
    public CirCheckinId getId() {
        return this.id;
    }
    
    public void setId(CirCheckinId id) {
        this.id = id;
    }
    public CirCheckout getCirCheckout() {
        return this.cirCheckout;
    }
    
    public void setCirCheckout(CirCheckout cirCheckout) {
        this.cirCheckout = cirCheckout;
    }
    public String getMemberId() {
        return this.memberId;
    }
    
    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }
    public String getReturningDate() {
        return this.returningDate;
    }
    
    public void setReturningDate(String returningDate) {
        this.returningDate = returningDate;
    }
    public String getDocumentId() {
        return this.documentId;
    }
    
    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }
    public String getDamagedStatus() {
        return this.damagedStatus;
    }
    
    public void setDamagedStatus(String damagedStatus) {
        this.damagedStatus = damagedStatus;
    }
    public String getLossStatus() {
        return this.lossStatus;
    }
    
    public void setLossStatus(String lossStatus) {
        this.lossStatus = lossStatus;
    }
    public String getReason() {
        return this.reason;
    }
    
    public void setReason(String reason) {
        this.reason = reason;
    }




}


