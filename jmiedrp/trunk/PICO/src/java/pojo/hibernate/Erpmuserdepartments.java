package pojo.hibernate;
// Generated May 30, 2013 2:31:14 PM by Hibernate Tools 3.2.1.GA



/**
 * Erpmuserdepartments generated by hbm2java
 */
public class Erpmuserdepartments  implements java.io.Serializable {


     private Integer erpmudId;
     private Institutionroleprivileges institutionroleprivileges;
     private Departmentmaster departmentmaster;

    public Erpmuserdepartments() {
    }

    public Erpmuserdepartments(Institutionroleprivileges institutionroleprivileges, Departmentmaster departmentmaster) {
       this.institutionroleprivileges = institutionroleprivileges;
       this.departmentmaster = departmentmaster;
    }
   
    public Integer getErpmudId() {
        return this.erpmudId;
    }
    
    public void setErpmudId(Integer erpmudId) {
        this.erpmudId = erpmudId;
    }
    public Institutionroleprivileges getInstitutionroleprivileges() {
        return this.institutionroleprivileges;
    }
    
    public void setInstitutionroleprivileges(Institutionroleprivileges institutionroleprivileges) {
        this.institutionroleprivileges = institutionroleprivileges;
    }
    public Departmentmaster getDepartmentmaster() {
        return this.departmentmaster;
    }
    
    public void setDepartmentmaster(Departmentmaster departmentmaster) {
        this.departmentmaster = departmentmaster;
    }




}


