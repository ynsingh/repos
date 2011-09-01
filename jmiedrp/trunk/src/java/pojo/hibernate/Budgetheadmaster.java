package pojo.hibernate;
// Generated May 29, 2011 11:33:37 PM by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;

/**
 * Budgetheadmaster generated by hbm2java
 */
public class Budgetheadmaster  implements java.io.Serializable {


     private Short bhmId;
     private Institutionmaster institutionmaster;
     private String bhmName;
     private Set departmentalBudgetAllocations = new HashSet(0);
     private Set erpmIndentMasters = new HashSet(0);

    public Budgetheadmaster() {
    }

    public Budgetheadmaster(Institutionmaster institutionmaster, String bhmName, Set departmentalBudgetAllocations, Set erpmIndentMasters) {
       this.institutionmaster = institutionmaster;
       this.bhmName = bhmName;
       this.departmentalBudgetAllocations = departmentalBudgetAllocations;
       this.erpmIndentMasters = erpmIndentMasters;
    }
   
    public Short getBhmId() {
        return this.bhmId;
    }
    
    public void setBhmId(Short bhmId) {
        this.bhmId = bhmId;
    }
    public Institutionmaster getInstitutionmaster() {
        return this.institutionmaster;
    }
    
    public void setInstitutionmaster(Institutionmaster institutionmaster) {
        this.institutionmaster = institutionmaster;
    }
    public String getBhmName() {
        return this.bhmName;
    }
    
    public void setBhmName(String bhmName) {
        this.bhmName = bhmName;
    }
    public Set getDepartmentalBudgetAllocations() {
        return this.departmentalBudgetAllocations;
    }
    
    public void setDepartmentalBudgetAllocations(Set departmentalBudgetAllocations) {
        this.departmentalBudgetAllocations = departmentalBudgetAllocations;
    }
    public Set getErpmIndentMasters() {
        return this.erpmIndentMasters;
    }
    
    public void setErpmIndentMasters(Set erpmIndentMasters) {
        this.erpmIndentMasters = erpmIndentMasters;
    }




}


