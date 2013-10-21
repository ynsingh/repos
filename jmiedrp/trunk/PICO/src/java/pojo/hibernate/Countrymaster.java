package pojo.hibernate;
// Generated May 30, 2013 2:31:14 PM by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;

/**
 * Countrymaster generated by hbm2java
 */
public class Countrymaster  implements java.io.Serializable {


     private Byte countryId;
     private String countryName;
     private Set subinstitutionmasters = new HashSet(0);
     private Set institutionmasters = new HashSet(0);
     private Set supplierAddresses = new HashSet(0);
     private Set departmentmasters = new HashSet(0);
     private Set statemasters = new HashSet(0);

    public Countrymaster() {
    }

	
    public Countrymaster(String countryName) {
        this.countryName = countryName;
    }
    public Countrymaster(String countryName, Set subinstitutionmasters, Set institutionmasters, Set supplierAddresses, Set departmentmasters, Set statemasters) {
       this.countryName = countryName;
       this.subinstitutionmasters = subinstitutionmasters;
       this.institutionmasters = institutionmasters;
       this.supplierAddresses = supplierAddresses;
       this.departmentmasters = departmentmasters;
       this.statemasters = statemasters;
    }
   
    public Byte getCountryId() {
        return this.countryId;
    }
    
    public void setCountryId(Byte countryId) {
        this.countryId = countryId;
    }
    public String getCountryName() {
        return this.countryName;
    }
    
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
    public Set getSubinstitutionmasters() {
        return this.subinstitutionmasters;
    }
    
    public void setSubinstitutionmasters(Set subinstitutionmasters) {
        this.subinstitutionmasters = subinstitutionmasters;
    }
    public Set getInstitutionmasters() {
        return this.institutionmasters;
    }
    
    public void setInstitutionmasters(Set institutionmasters) {
        this.institutionmasters = institutionmasters;
    }
    public Set getSupplierAddresses() {
        return this.supplierAddresses;
    }
    
    public void setSupplierAddresses(Set supplierAddresses) {
        this.supplierAddresses = supplierAddresses;
    }
    public Set getDepartmentmasters() {
        return this.departmentmasters;
    }
    
    public void setDepartmentmasters(Set departmentmasters) {
        this.departmentmasters = departmentmasters;
    }
    public Set getStatemasters() {
        return this.statemasters;
    }
    
    public void setStatemasters(Set statemasters) {
        this.statemasters = statemasters;
    }




}


