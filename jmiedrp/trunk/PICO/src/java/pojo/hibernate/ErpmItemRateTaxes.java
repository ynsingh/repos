package pojo.hibernate;
// Generated 26 Mar, 2012 3:25:11 PM by Hibernate Tools 3.2.1.GA


import java.math.BigDecimal;

/**
 * ErpmItemRateTaxes generated by hbm2java
 */
public class ErpmItemRateTaxes  implements java.io.Serializable {


     private Integer irtItemRateTaxesId;
     private ErpmGenMaster erpmGenMaster;
     private ErpmItemRate erpmItemRate;
     private BigDecimal irtTaxPercent;
     private BigDecimal irtTaxOnValuePercent;
     private BigDecimal irtSurchargePercent;

    public ErpmItemRateTaxes() {
    }

	
    public ErpmItemRateTaxes(ErpmGenMaster erpmGenMaster, ErpmItemRate erpmItemRate) {
        this.erpmGenMaster = erpmGenMaster;
        this.erpmItemRate = erpmItemRate;
    }
    public ErpmItemRateTaxes(ErpmGenMaster erpmGenMaster, ErpmItemRate erpmItemRate, BigDecimal irtTaxPercent, BigDecimal irtTaxOnValuePercent, BigDecimal irtSurchargePercent) {
       this.erpmGenMaster = erpmGenMaster;
       this.erpmItemRate = erpmItemRate;
       this.irtTaxPercent = irtTaxPercent;
       this.irtTaxOnValuePercent = irtTaxOnValuePercent;
       this.irtSurchargePercent = irtSurchargePercent;
    }
   
    public Integer getIrtItemRateTaxesId() {
        return this.irtItemRateTaxesId;
    }
    
    public void setIrtItemRateTaxesId(Integer irtItemRateTaxesId) {
        this.irtItemRateTaxesId = irtItemRateTaxesId;
    }
    public ErpmGenMaster getErpmGenMaster() {
        return this.erpmGenMaster;
    }
    
    public void setErpmGenMaster(ErpmGenMaster erpmGenMaster) {
        this.erpmGenMaster = erpmGenMaster;
    }
    public ErpmItemRate getErpmItemRate() {
        return this.erpmItemRate;
    }
    
    public void setErpmItemRate(ErpmItemRate erpmItemRate) {
        this.erpmItemRate = erpmItemRate;
    }
    public BigDecimal getIrtTaxPercent() {
        return this.irtTaxPercent;
    }
    
    public void setIrtTaxPercent(BigDecimal irtTaxPercent) {
        this.irtTaxPercent = irtTaxPercent;
    }
    public BigDecimal getIrtTaxOnValuePercent() {
        return this.irtTaxOnValuePercent;
    }
    
    public void setIrtTaxOnValuePercent(BigDecimal irtTaxOnValuePercent) {
        this.irtTaxOnValuePercent = irtTaxOnValuePercent;
    }
    public BigDecimal getIrtSurchargePercent() {
        return this.irtSurchargePercent;
    }
    
    public void setIrtSurchargePercent(BigDecimal irtSurchargePercent) {
        this.irtSurchargePercent = irtSurchargePercent;
    }




}


