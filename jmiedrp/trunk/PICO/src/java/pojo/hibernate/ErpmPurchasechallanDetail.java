package pojo.hibernate;
// Generated May 30, 2013 2:31:14 PM by Hibernate Tools 3.2.1.GA


import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * ErpmPurchasechallanDetail generated by hbm2java
 */
public class ErpmPurchasechallanDetail  implements java.io.Serializable {


     private Integer pcdPcdId;
     private ErpmPurchasechallanMaster erpmPurchasechallanMaster;
     private ErpmItemMaster erpmItemMaster;
     private BigDecimal pcdRecvQuantity;
     private char pcdQNQChecked;
     private char pcdQNQVerified;
     private String pcdVerifiedBy;
     private Set erpmPurchasechallanSerials = new HashSet(0);

    public ErpmPurchasechallanDetail() {
    }

	
    public ErpmPurchasechallanDetail(ErpmPurchasechallanMaster erpmPurchasechallanMaster, char pcdQNQChecked, char pcdQNQVerified) {
        this.erpmPurchasechallanMaster = erpmPurchasechallanMaster;
        this.pcdQNQChecked = pcdQNQChecked;
        this.pcdQNQVerified = pcdQNQVerified;
    }
    public ErpmPurchasechallanDetail(ErpmPurchasechallanMaster erpmPurchasechallanMaster, ErpmItemMaster erpmItemMaster, BigDecimal pcdRecvQuantity, char pcdQNQChecked, char pcdQNQVerified, String pcdVerifiedBy, Set erpmPurchasechallanSerials) {
       this.erpmPurchasechallanMaster = erpmPurchasechallanMaster;
       this.erpmItemMaster = erpmItemMaster;
       this.pcdRecvQuantity = pcdRecvQuantity;
       this.pcdQNQChecked = pcdQNQChecked;
       this.pcdQNQVerified = pcdQNQVerified;
       this.pcdVerifiedBy = pcdVerifiedBy;
       this.erpmPurchasechallanSerials = erpmPurchasechallanSerials;
    }
   
    public Integer getPcdPcdId() {
        return this.pcdPcdId;
    }
    
    public void setPcdPcdId(Integer pcdPcdId) {
        this.pcdPcdId = pcdPcdId;
    }
    public ErpmPurchasechallanMaster getErpmPurchasechallanMaster() {
        return this.erpmPurchasechallanMaster;
    }
    
    public void setErpmPurchasechallanMaster(ErpmPurchasechallanMaster erpmPurchasechallanMaster) {
        this.erpmPurchasechallanMaster = erpmPurchasechallanMaster;
    }
    public ErpmItemMaster getErpmItemMaster() {
        return this.erpmItemMaster;
    }
    
    public void setErpmItemMaster(ErpmItemMaster erpmItemMaster) {
        this.erpmItemMaster = erpmItemMaster;
    }
    public BigDecimal getPcdRecvQuantity() {
        return this.pcdRecvQuantity;
    }
    
    public void setPcdRecvQuantity(BigDecimal pcdRecvQuantity) {
        this.pcdRecvQuantity = pcdRecvQuantity;
    }
    public char getPcdQNQChecked() {
        return this.pcdQNQChecked;
    }
    
    public void setPcdQNQChecked(char pcdQNQChecked) {
        this.pcdQNQChecked = pcdQNQChecked;
    }
    public char getPcdQNQVerified() {
        return this.pcdQNQVerified;
    }
    
    public void setPcdQNQVerified(char pcdQNQVerified) {
        this.pcdQNQVerified = pcdQNQVerified;
    }
    public String getPcdVerifiedBy() {
        return this.pcdVerifiedBy;
    }
    
    public void setPcdVerifiedBy(String pcdVerifiedBy) {
        this.pcdVerifiedBy = pcdVerifiedBy;
    }
    public Set getErpmPurchasechallanSerials() {
        return this.erpmPurchasechallanSerials;
    }
    
    public void setErpmPurchasechallanSerials(Set erpmPurchasechallanSerials) {
        this.erpmPurchasechallanSerials = erpmPurchasechallanSerials;
    }




}


