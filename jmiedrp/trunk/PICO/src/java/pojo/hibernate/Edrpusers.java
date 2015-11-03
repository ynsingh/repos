package pojo.hibernate;
// Generated May 30, 2013 2:31:14 PM by Hibernate Tools 3.2.1.GA


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Edrpusers  implements java.io.Serializable {


     private Integer edrpuId;
     private String edrpuName;
     private String edrpuPassword;
     private String edrpuEmail;

    public Edrpusers() {
    }

	
    //public Erpmusers(String erpmuName, String erpmuPassword, Date erpmuDob, String erpmuActive, String erpmuFullName) {
    public Edrpusers(String erpmuName, String erpmuPassword, String edrpuEmail) {
        this.edrpuName = edrpuName;
        this.edrpuPassword = edrpuPassword;
        this.edrpuEmail = edrpuEmail;
    }
    /*public Erpmusers(String erpmuName, String erpmuPassword, String erpmuActive, String erpmuVerifiedBy, String erpmuFullName, Set erpmIndentMasters, Set userMessagesForUmFromErpmuId, Set fileMasters, Set fileDetails, Set erpmNewses, Set erpmPurchasechallanMasters, Set erpmPurchaseinvoiceMasters, Set erpmuserroles, Set erpmPoMastersForPomUserId, Set erpmPoMastersForPomApprovedById, Set userMessagesForUmToErpmuId) {
       this.erpmuName = erpmuName;
       this.erpmuPassword = erpmuPassword;
       //this.erpmuDob = erpmuDob;
       this.erpmuActive = erpmuActive;
       this.erpmuVerifiedBy = erpmuVerifiedBy;
       //this.erpmuSecretQuestion = erpmuSecretQuestion;
       //this.erpmuSecretAnswer = erpmuSecretAnswer;
       this.erpmuFullName = erpmuFullName;
       this.erpmIndentMasters = erpmIndentMasters;
       this.userMessagesForUmFromErpmuId = userMessagesForUmFromErpmuId;
       this.fileMasters = fileMasters;
       this.fileDetails = fileDetails;
       this.erpmNewses = erpmNewses;
       this.erpmPurchasechallanMasters = erpmPurchasechallanMasters;
       this.erpmPurchaseinvoiceMasters = erpmPurchaseinvoiceMasters;
       this.erpmuserroles = erpmuserroles;
       this.erpmPoMastersForPomUserId = erpmPoMastersForPomUserId;
       this.erpmPoMastersForPomApprovedById = erpmPoMastersForPomApprovedById;
       this.userMessagesForUmToErpmuId = userMessagesForUmToErpmuId;
    }*/
   
    public Integer getEdrpuId() {
        return this.edrpuId;
    }
    
    public void setEdrpuId(Integer edrpuId) {
        this.edrpuId = edrpuId;
    }
    public String getEdrpuName() {
        return this.edrpuName;
    }
    
    public void setEdrpuName(String edrpuName) {
        this.edrpuName = edrpuName;
    }
    public String getEdrpuPassword() {
        return this.edrpuPassword;
    }
    
    public void setEdrpuPassword(String edrpuPassword) {
        this.edrpuPassword = edrpuPassword;
    }
    public String getEdrpuEmail() {
        return this.edrpuEmail;
    }
    
    public void setEdrpuEmail(String edrpuEmail) {
        this.edrpuEmail = edrpuEmail;
    }



}


