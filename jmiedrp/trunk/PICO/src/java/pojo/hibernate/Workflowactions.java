package pojo.hibernate;
// Generated May 30, 2013 2:31:14 PM by Hibernate Tools 3.2.1.GA



/**
 * Workflowactions generated by hbm2java
 */
public class Workflowactions  implements java.io.Serializable {


     private Integer wfaId;
     private ErpmGenMaster erpmGenMaster;
     private Workflowdetail workflowdetail;

    public Workflowactions() {
    }

	
    public Workflowactions(Workflowdetail workflowdetail) {
        this.workflowdetail = workflowdetail;
    }
    public Workflowactions(ErpmGenMaster erpmGenMaster, Workflowdetail workflowdetail) {
       this.erpmGenMaster = erpmGenMaster;
       this.workflowdetail = workflowdetail;
    }
   
    public Integer getWfaId() {
        return this.wfaId;
    }
    
    public void setWfaId(Integer wfaId) {
        this.wfaId = wfaId;
    }
    public ErpmGenMaster getErpmGenMaster() {
        return this.erpmGenMaster;
    }
    
    public void setErpmGenMaster(ErpmGenMaster erpmGenMaster) {
        this.erpmGenMaster = erpmGenMaster;
    }
    public Workflowdetail getWorkflowdetail() {
        return this.workflowdetail;
    }
    
    public void setWorkflowdetail(Workflowdetail workflowdetail) {
        this.workflowdetail = workflowdetail;
    }




}


