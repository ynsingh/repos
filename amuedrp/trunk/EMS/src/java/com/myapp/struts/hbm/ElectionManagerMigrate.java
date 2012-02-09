package com.myapp.struts.hbm;
// Generated Feb 3, 2012 11:38:42 PM by Hibernate Tools 3.2.1.GA



/**
 * ElectionManagerMigrate generated by hbm2java
 */
public class ElectionManagerMigrate  implements java.io.Serializable {


     private ElectionManagerMigrateId id;
     private String electionName;
     private String oldEm;
     private String newEm;
     private String date;

    public ElectionManagerMigrate() {
    }

	
    public ElectionManagerMigrate(ElectionManagerMigrateId id) {
        this.id = id;
    }
    public ElectionManagerMigrate(ElectionManagerMigrateId id, String electionName, String oldEm, String newEm, String date) {
       this.id = id;
       this.electionName = electionName;
       this.oldEm = oldEm;
       this.newEm = newEm;
       this.date = date;
    }
   
    public ElectionManagerMigrateId getId() {
        return this.id;
    }
    
    public void setId(ElectionManagerMigrateId id) {
        this.id = id;
    }
    public String getElectionName() {
        return this.electionName;
    }
    
    public void setElectionName(String electionName) {
        this.electionName = electionName;
    }
    public String getOldEm() {
        return this.oldEm;
    }
    
    public void setOldEm(String oldEm) {
        this.oldEm = oldEm;
    }
    public String getNewEm() {
        return this.newEm;
    }
    
    public void setNewEm(String newEm) {
        this.newEm = newEm;
    }
    public String getDate() {
        return this.date;
    }
    
    public void setDate(String date) {
        this.date = date;
    }




}


