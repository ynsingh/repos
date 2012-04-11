package pojo.hibernate;
// Generated 26 Mar, 2012 3:25:11 PM by Hibernate Tools 3.2.1.GA


import java.math.BigDecimal;

/**
 * ErpmPoTaxes generated by hbm2java
 */
public class ErpmPoTaxes  implements java.io.Serializable {


     private Integer potPoTaxesId;
     private ErpmPoDetails erpmPoDetails;
     private String potTaxName;
     private BigDecimal potTaxPercent;
     private BigDecimal potTaxOnValuePercent;
     private BigDecimal potSurchargePercent;

    public ErpmPoTaxes() {
    }

	
    public ErpmPoTaxes(ErpmPoDetails erpmPoDetails) {
        this.erpmPoDetails = erpmPoDetails;
    }
    public ErpmPoTaxes(ErpmPoDetails erpmPoDetails, String potTaxName, BigDecimal potTaxPercent, BigDecimal potTaxOnValuePercent, BigDecimal potSurchargePercent) {
       this.erpmPoDetails = erpmPoDetails;
       this.potTaxName = potTaxName;
       this.potTaxPercent = potTaxPercent;
       this.potTaxOnValuePercent = potTaxOnValuePercent;
       this.potSurchargePercent = potSurchargePercent;
    }
   
    public Integer getPotPoTaxesId() {
        return this.potPoTaxesId;
    }
    
    public void setPotPoTaxesId(Integer potPoTaxesId) {
        this.potPoTaxesId = potPoTaxesId;
    }
    public ErpmPoDetails getErpmPoDetails() {
        return this.erpmPoDetails;
    }
    
    public void setErpmPoDetails(ErpmPoDetails erpmPoDetails) {
        this.erpmPoDetails = erpmPoDetails;
    }
    public String getPotTaxName() {
        return this.potTaxName;
    }
    
    public void setPotTaxName(String potTaxName) {
        this.potTaxName = potTaxName;
    }
    public BigDecimal getPotTaxPercent() {
        return this.potTaxPercent;
    }
    
    public void setPotTaxPercent(BigDecimal potTaxPercent) {
        this.potTaxPercent = potTaxPercent;
    }
    public BigDecimal getPotTaxOnValuePercent() {
        return this.potTaxOnValuePercent;
    }
    
    public void setPotTaxOnValuePercent(BigDecimal potTaxOnValuePercent) {
        this.potTaxOnValuePercent = potTaxOnValuePercent;
    }
    public BigDecimal getPotSurchargePercent() {
        return this.potSurchargePercent;
    }
    
    public void setPotSurchargePercent(BigDecimal potSurchargePercent) {
        this.potSurchargePercent = potSurchargePercent;
    }




}


