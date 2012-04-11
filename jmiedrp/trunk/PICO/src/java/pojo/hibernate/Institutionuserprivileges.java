package pojo.hibernate;
// Generated Feb 18, 2011 12:14:02 PM by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;

/**
 * Institutionuserprivileges generated by hbm2java
 */
public class Institutionuserprivileges  implements java.io.Serializable {


     private Short iupId;
     private Erpmprogram erpmprogram;
     private Erpmmodule erpmmodule;
     private Institutionuserroles institutionuserroles;
     private String iupCanAdd;
     private String iupCanDelete;
     private String iupCanEdit;
     private String iupCanView;
     private Set erpmuserdepartmentses = new HashSet(0);

    public Institutionuserprivileges() {
    }

	
    public Institutionuserprivileges(Erpmprogram erpmprogram, Institutionuserroles institutionuserroles, String iupCanAdd, String iupCanDelete, String iupCanEdit, String iupCanView) {
        this.erpmprogram = erpmprogram;
        this.institutionuserroles = institutionuserroles;
        this.iupCanAdd = iupCanAdd;
        this.iupCanDelete = iupCanDelete;
        this.iupCanEdit = iupCanEdit;
        this.iupCanView = iupCanView;
    }
    public Institutionuserprivileges(Erpmprogram erpmprogram, Erpmmodule erpmmodule, Institutionuserroles institutionuserroles, String iupCanAdd, String iupCanDelete, String iupCanEdit, String iupCanView, Set erpmuserdepartmentses) {
       this.erpmprogram = erpmprogram;
       this.erpmmodule = erpmmodule;
       this.institutionuserroles = institutionuserroles;
       this.iupCanAdd = iupCanAdd;
       this.iupCanDelete = iupCanDelete;
       this.iupCanEdit = iupCanEdit;
       this.iupCanView = iupCanView;
       this.erpmuserdepartmentses = erpmuserdepartmentses;
    }
   
    public Short getIupId() {
        return this.iupId;
    }
    
    public void setIupId(Short iupId) {
        this.iupId = iupId;
    }
    public Erpmprogram getErpmprogram() {
        return this.erpmprogram;
    }
    
    public void setErpmprogram(Erpmprogram erpmprogram) {
        this.erpmprogram = erpmprogram;
    }
    public Erpmmodule getErpmmodule() {
        return this.erpmmodule;
    }
    
    public void setErpmmodule(Erpmmodule erpmmodule) {
        this.erpmmodule = erpmmodule;
    }
    public Institutionuserroles getInstitutionuserroles() {
        return this.institutionuserroles;
    }
    
    public void setInstitutionuserroles(Institutionuserroles institutionuserroles) {
        this.institutionuserroles = institutionuserroles;
    }
    public String getIupCanAdd() {
        return this.iupCanAdd;
    }
    
    public void setIupCanAdd(String iupCanAdd) {
        this.iupCanAdd = iupCanAdd;
    }
    public String getIupCanDelete() {
        return this.iupCanDelete;
    }
    
    public void setIupCanDelete(String iupCanDelete) {
        this.iupCanDelete = iupCanDelete;
    }
    public String getIupCanEdit() {
        return this.iupCanEdit;
    }
    
    public void setIupCanEdit(String iupCanEdit) {
        this.iupCanEdit = iupCanEdit;
    }
    public String getIupCanView() {
        return this.iupCanView;
    }
    
    public void setIupCanView(String iupCanView) {
        this.iupCanView = iupCanView;
    }
    public Set getErpmuserdepartmentses() {
        return this.erpmuserdepartmentses;
    }
    
    public void setErpmuserdepartmentses(Set erpmuserdepartmentses) {
        this.erpmuserdepartmentses = erpmuserdepartmentses;
    }




}


