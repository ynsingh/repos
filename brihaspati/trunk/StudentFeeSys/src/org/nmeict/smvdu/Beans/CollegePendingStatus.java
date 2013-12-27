package org.nmeict.smvdu.Beans;
// Generated Mar 30, 2013 9:21:41 AM by Hibernate Tools 3.2.1.GA



/**
 * CollegePendingStatus generated by hbm2java
 */
public class CollegePendingStatus  implements java.io.Serializable {


     private String orgPenEmail;
     private OrgProfile orgProfile;
     private Byte orgRequestStatus;

    public CollegePendingStatus() {
    }

	
    public CollegePendingStatus(String orgPenEmail) {
        this.orgPenEmail = orgPenEmail;
    }
    public CollegePendingStatus(String orgPenEmail, OrgProfile orgProfile, Byte orgRequestStatus) {
       this.orgPenEmail = orgPenEmail;
       this.orgProfile = orgProfile;
       this.orgRequestStatus = orgRequestStatus;
    }
   
    public String getOrgPenEmail() {
        return this.orgPenEmail;
    }
    
    public void setOrgPenEmail(String orgPenEmail) {
        this.orgPenEmail = orgPenEmail;
    }
    public OrgProfile getOrgProfile() {
        return this.orgProfile;
    }
    
    public void setOrgProfile(OrgProfile orgProfile) {
        this.orgProfile = orgProfile;
    }
    public Byte getOrgRequestStatus() {
        return this.orgRequestStatus;
    }
    
    public void setOrgRequestStatus(Byte orgRequestStatus) {
        this.orgRequestStatus = orgRequestStatus;
    }




}

