package pojo.hibernate;
// Generated 26 Mar, 2012 3:25:11 PM by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;

/**
 * ErpmCapitalCategory generated by hbm2java
 */
public class ErpmCapitalCategory  implements java.io.Serializable {


     private Integer erpmccId;
     private Institutionmaster institutionmaster;
     private String ermccDesc;
     private Set erpmItemMasters = new HashSet(0);

    public ErpmCapitalCategory() {
    }

	
    public ErpmCapitalCategory(String ermccDesc) {
        this.ermccDesc = ermccDesc;
    }
    public ErpmCapitalCategory(Institutionmaster institutionmaster, String ermccDesc, Set erpmItemMasters) {
       this.institutionmaster = institutionmaster;
       this.ermccDesc = ermccDesc;
       this.erpmItemMasters = erpmItemMasters;
    }
   
    public Integer getErpmccId() {
        return this.erpmccId;
    }
    
    public void setErpmccId(Integer erpmccId) {
        this.erpmccId = erpmccId;
    }
    public Institutionmaster getInstitutionmaster() {
        return this.institutionmaster;
    }
    
    public void setInstitutionmaster(Institutionmaster institutionmaster) {
        this.institutionmaster = institutionmaster;
    }
    public String getErmccDesc() {
        return this.ermccDesc;
    }
    
    public void setErmccDesc(String ermccDesc) {
        this.ermccDesc = ermccDesc;
    }
    public Set getErpmItemMasters() {
        return this.erpmItemMasters;
    }
    
    public void setErpmItemMasters(Set erpmItemMasters) {
        this.erpmItemMasters = erpmItemMasters;
    }




}


