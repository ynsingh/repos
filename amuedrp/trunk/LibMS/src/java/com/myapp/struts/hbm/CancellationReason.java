package com.myapp.struts.hbm;
// Generated Oct 20, 2012 11:20:56 AM by Hibernate Tools 3.2.1.GA



/**
 * CancellationReason generated by hbm2java
 */
public class CancellationReason  implements java.io.Serializable {


     private CancellationReasonId id;
     private String details;

    public CancellationReason() {
    }

	
    public CancellationReason(CancellationReasonId id) {
        this.id = id;
    }
    public CancellationReason(CancellationReasonId id, String details) {
       this.id = id;
       this.details = details;
    }
   
    public CancellationReasonId getId() {
        return this.id;
    }
    
    public void setId(CancellationReasonId id) {
        this.id = id;
    }
    public String getDetails() {
        return this.details;
    }
    
    public void setDetails(String details) {
        this.details = details;
    }




}


