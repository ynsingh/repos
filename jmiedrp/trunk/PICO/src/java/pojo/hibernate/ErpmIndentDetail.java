package pojo.hibernate;
// Generated May 30, 2013 2:31:14 PM by Hibernate Tools 3.2.1.GA


import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * ErpmIndentDetail generated by hbm2java
 */
public class ErpmIndentDetail  implements java.io.Serializable {


     private Short indtDetailId;
     private ErpmItemRate erpmItemRate;
     private ErpmIndentMaster erpmIndentMaster;
     private ErpmItemMaster erpmItemMaster;
     private short indtQuantity;
     private BigDecimal indtApproxcost;
     private String indtPurpose;
     private Short indtApprovedQuantity;
     private BigDecimal indtAcceptedUnitRate;
     private Set erpmPoDetailses = new HashSet(0);

    public ErpmIndentDetail() {
    }

	
    public ErpmIndentDetail(ErpmIndentMaster erpmIndentMaster, ErpmItemMaster erpmItemMaster, short indtQuantity, BigDecimal indtApproxcost) {
        this.erpmIndentMaster = erpmIndentMaster;
        this.erpmItemMaster = erpmItemMaster;
        this.indtQuantity = indtQuantity;
        this.indtApproxcost = indtApproxcost;
    }
    public ErpmIndentDetail(ErpmItemRate erpmItemRate, ErpmIndentMaster erpmIndentMaster, ErpmItemMaster erpmItemMaster, short indtQuantity, BigDecimal indtApproxcost, String indtPurpose, Short indtApprovedQuantity, BigDecimal indtAcceptedUnitRate, Set erpmPoDetailses) {
       this.erpmItemRate = erpmItemRate;
       this.erpmIndentMaster = erpmIndentMaster;
       this.erpmItemMaster = erpmItemMaster;
       this.indtQuantity = indtQuantity;
       this.indtApproxcost = indtApproxcost;
       this.indtPurpose = indtPurpose;
       this.indtApprovedQuantity = indtApprovedQuantity;
       this.indtAcceptedUnitRate = indtAcceptedUnitRate;
       this.erpmPoDetailses = erpmPoDetailses;
    }
   
    public Short getIndtDetailId() {
        return this.indtDetailId;
    }
    
    public void setIndtDetailId(Short indtDetailId) {
        this.indtDetailId = indtDetailId;
    }
    public ErpmItemRate getErpmItemRate() {
        return this.erpmItemRate;
    }
    
    public void setErpmItemRate(ErpmItemRate erpmItemRate) {
        this.erpmItemRate = erpmItemRate;
    }
    public ErpmIndentMaster getErpmIndentMaster() {
        return this.erpmIndentMaster;
    }
    
    public void setErpmIndentMaster(ErpmIndentMaster erpmIndentMaster) {
        this.erpmIndentMaster = erpmIndentMaster;
    }
    public ErpmItemMaster getErpmItemMaster() {
        return this.erpmItemMaster;
    }
    
    public void setErpmItemMaster(ErpmItemMaster erpmItemMaster) {
        this.erpmItemMaster = erpmItemMaster;
    }
    public short getIndtQuantity() {
        return this.indtQuantity;
    }
    
    public void setIndtQuantity(short indtQuantity) {
        this.indtQuantity = indtQuantity;
    }
    public BigDecimal getIndtApproxcost() {
        return this.indtApproxcost;
    }
    
    public void setIndtApproxcost(BigDecimal indtApproxcost) {
        this.indtApproxcost = indtApproxcost;
    }
    public String getIndtPurpose() {
        return this.indtPurpose;
    }
    
    public void setIndtPurpose(String indtPurpose) {
        this.indtPurpose = indtPurpose;
    }
    public Short getIndtApprovedQuantity() {
        return this.indtApprovedQuantity;
    }
    
    public void setIndtApprovedQuantity(Short indtApprovedQuantity) {
        this.indtApprovedQuantity = indtApprovedQuantity;
    }
    public BigDecimal getIndtAcceptedUnitRate() {
        return this.indtAcceptedUnitRate;
    }
    
    public void setIndtAcceptedUnitRate(BigDecimal indtAcceptedUnitRate) {
        this.indtAcceptedUnitRate = indtAcceptedUnitRate;
    }
    public Set getErpmPoDetailses() {
        return this.erpmPoDetailses;
    }
    
    public void setErpmPoDetailses(Set erpmPoDetailses) {
        this.erpmPoDetailses = erpmPoDetailses;
    }




}


