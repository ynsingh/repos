package pojo.hibernate;
// Generated May 30, 2013 2:31:14 PM by Hibernate Tools 3.2.1.GA



/**
 * ErpmPoLocations generated by hbm2java
 */
public class ErpmPoLocations  implements java.io.Serializable {


     private Integer poLocationsId;
     private ErpmItemMaster erpmItemMaster;
     private ErpmPoMaster erpmPoMaster;
     private Departmentmaster departmentmaster;
     private int qty;
     private String location;

    public ErpmPoLocations() {
    }

	
    public ErpmPoLocations(ErpmItemMaster erpmItemMaster, ErpmPoMaster erpmPoMaster, int qty) {
        this.erpmItemMaster = erpmItemMaster;
        this.erpmPoMaster = erpmPoMaster;
        this.qty = qty;
    }
    public ErpmPoLocations(ErpmItemMaster erpmItemMaster, ErpmPoMaster erpmPoMaster, Departmentmaster departmentmaster, int qty, String location) {
       this.erpmItemMaster = erpmItemMaster;
       this.erpmPoMaster = erpmPoMaster;
       this.departmentmaster = departmentmaster;
       this.qty = qty;
       this.location = location;
    }
   
    public Integer getPoLocationsId() {
        return this.poLocationsId;
    }
    
    public void setPoLocationsId(Integer poLocationsId) {
        this.poLocationsId = poLocationsId;
    }
    public ErpmItemMaster getErpmItemMaster() {
        return this.erpmItemMaster;
    }
    
    public void setErpmItemMaster(ErpmItemMaster erpmItemMaster) {
        this.erpmItemMaster = erpmItemMaster;
    }
    public ErpmPoMaster getErpmPoMaster() {
        return this.erpmPoMaster;
    }
    
    public void setErpmPoMaster(ErpmPoMaster erpmPoMaster) {
        this.erpmPoMaster = erpmPoMaster;
    }
    public Departmentmaster getDepartmentmaster() {
        return this.departmentmaster;
    }
    
    public void setDepartmentmaster(Departmentmaster departmentmaster) {
        this.departmentmaster = departmentmaster;
    }
    public int getQty() {
        return this.qty;
    }
    
    public void setQty(int qty) {
        this.qty = qty;
    }
    public String getLocation() {
        return this.location;
    }
    
    public void setLocation(String location) {
        this.location = location;
    }




}

