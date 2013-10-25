package pojo.hibernate;
// Generated May 30, 2013 2:31:14 PM by Hibernate Tools 3.2.1.GA



/**
 * ErpmPurchaseinvoiceTerms generated by hbm2java
 */
public class ErpmPurchaseinvoiceTerms  implements java.io.Serializable {


     private Integer pitPitId;
     private ErpmPurchaseinvoiceMaster erpmPurchaseinvoiceMaster;
     private int pitEgmId;
     private String pitTermsDescription;

    public ErpmPurchaseinvoiceTerms() {
    }

	
    public ErpmPurchaseinvoiceTerms(ErpmPurchaseinvoiceMaster erpmPurchaseinvoiceMaster, int pitEgmId) {
        this.erpmPurchaseinvoiceMaster = erpmPurchaseinvoiceMaster;
        this.pitEgmId = pitEgmId;
    }
    public ErpmPurchaseinvoiceTerms(ErpmPurchaseinvoiceMaster erpmPurchaseinvoiceMaster, int pitEgmId, String pitTermsDescription) {
       this.erpmPurchaseinvoiceMaster = erpmPurchaseinvoiceMaster;
       this.pitEgmId = pitEgmId;
       this.pitTermsDescription = pitTermsDescription;
    }
   
    public Integer getPitPitId() {
        return this.pitPitId;
    }
    
    public void setPitPitId(Integer pitPitId) {
        this.pitPitId = pitPitId;
    }
    public ErpmPurchaseinvoiceMaster getErpmPurchaseinvoiceMaster() {
        return this.erpmPurchaseinvoiceMaster;
    }
    
    public void setErpmPurchaseinvoiceMaster(ErpmPurchaseinvoiceMaster erpmPurchaseinvoiceMaster) {
        this.erpmPurchaseinvoiceMaster = erpmPurchaseinvoiceMaster;
    }
    public int getPitEgmId() {
        return this.pitEgmId;
    }
    
    public void setPitEgmId(int pitEgmId) {
        this.pitEgmId = pitEgmId;
    }
    public String getPitTermsDescription() {
        return this.pitTermsDescription;
    }
    
    public void setPitTermsDescription(String pitTermsDescription) {
        this.pitTermsDescription = pitTermsDescription;
    }




}

