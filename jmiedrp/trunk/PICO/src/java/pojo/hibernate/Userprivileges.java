package pojo.hibernate;
// Generated 26 Mar, 2012 3:25:11 PM by Hibernate Tools 3.2.1.GA



/**
 * Userprivileges generated by hbm2java
 */
public class Userprivileges  implements java.io.Serializable {


     private Short rpId;
     private Erpmprogram erpmprogram;
     private byte rpUrId;
     private String rpCanAdd;
     private String rpCanDelete;
     private String rpCanEdit;

    public Userprivileges() {
    }

	
    public Userprivileges(Erpmprogram erpmprogram, byte rpUrId, String rpCanAdd, String rpCanEdit) {
        this.erpmprogram = erpmprogram;
        this.rpUrId = rpUrId;
        this.rpCanAdd = rpCanAdd;
        this.rpCanEdit = rpCanEdit;
    }
    public Userprivileges(Erpmprogram erpmprogram, byte rpUrId, String rpCanAdd, String rpCanDelete, String rpCanEdit) {
       this.erpmprogram = erpmprogram;
       this.rpUrId = rpUrId;
       this.rpCanAdd = rpCanAdd;
       this.rpCanDelete = rpCanDelete;
       this.rpCanEdit = rpCanEdit;
    }
   
    public Short getRpId() {
        return this.rpId;
    }
    
    public void setRpId(Short rpId) {
        this.rpId = rpId;
    }
    public Erpmprogram getErpmprogram() {
        return this.erpmprogram;
    }
    
    public void setErpmprogram(Erpmprogram erpmprogram) {
        this.erpmprogram = erpmprogram;
    }
    public byte getRpUrId() {
        return this.rpUrId;
    }
    
    public void setRpUrId(byte rpUrId) {
        this.rpUrId = rpUrId;
    }
    public String getRpCanAdd() {
        return this.rpCanAdd;
    }
    
    public void setRpCanAdd(String rpCanAdd) {
        this.rpCanAdd = rpCanAdd;
    }
    public String getRpCanDelete() {
        return this.rpCanDelete;
    }
    
    public void setRpCanDelete(String rpCanDelete) {
        this.rpCanDelete = rpCanDelete;
    }
    public String getRpCanEdit() {
        return this.rpCanEdit;
    }
    
    public void setRpCanEdit(String rpCanEdit) {
        this.rpCanEdit = rpCanEdit;
    }




}


