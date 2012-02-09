package com.myapp.struts.hbm;

// Generated 19 May, 2011 1:05:44 PM by Hibernate Tools 3.2.1.GA

//import java.security.Date;
import java.util.Date;
import java.sql.Timestamp;




/**
 * Election generated by hbm2java
 */
public class Election  implements java.io.Serializable {


     private ElectionId id;
     private String electionName;
     private String description;
     private Timestamp startDate;
     
     private Timestamp endDate;
      private Timestamp nstart;
       private Timestamp nend;
       private Timestamp scrutnyDate;
        private Timestamp scrutnyEndDate;
         private Timestamp withdrawlDate;
       private Timestamp withdrawlEndDate;
       private Timestamp resultDeclarationDate;

    public Timestamp getResultDeclarationDate() {
        return resultDeclarationDate;
    }

    public void setResultDeclarationDate(Timestamp resultDeclarationDate) {
        this.resultDeclarationDate = resultDeclarationDate;
    }
     
     private String status;
     private String createdBy;

    public Election() {
    }

	
    public Election(ElectionId id) {
        this.id = id;
    }
    public Election(ElectionId id, String electionName, String description, Timestamp startDate, Timestamp endDate, String status, String createdBy,Timestamp nstart,Timestamp nend) {
       this.id = id;
       this.electionName = electionName;
       this.description = description;
       this.startDate = startDate;
      
       this.endDate = endDate;
       
       this.status = status;
       this.createdBy = createdBy;
       this.nstart=nstart;
       this.nend=nend;
    }
   
    public ElectionId getId() {
        return this.id;
    }
    
    public void setId(ElectionId id) {
        this.id = id;
    }
    public String getElectionName() {
        return this.electionName;
    }
    
    public void setElectionName(String electionName) {
        this.electionName = electionName;
    }
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    public Timestamp getStartDate() {
        return this.startDate;
    }
    
    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    
    public Timestamp getEndDate() {
        return this.endDate;
    }
    
    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

     
    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    public String getCreatedBy() {
        return this.createdBy;
    }
    
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * @return the nstart
     */
    public Timestamp getNstart() {
        return nstart;
    }

    /**
     * @param nstart the nstart to set
     */
    public void setNstart(Timestamp nstart) {
        this.nstart = nstart;
    }

    /**
     * @return the nend
     */
    public Timestamp getNend() {
        return nend;
    }

    /**
     * @param nend the nend to set
     */
    public void setNend(Timestamp nend) {
        this.nend = nend;
    }

    /**
     * @return the scrutnyDate
     */
    public Timestamp getScrutnyDate() {
        return scrutnyDate;
    }

    /**
     * @param scrutnyDate the scrutnyDate to set
     */
    public void setScrutnyDate(Timestamp scrutnyDate) {
        this.scrutnyDate = scrutnyDate;
    }

    /**
     * @return the scrutnyEndDate
     */
    public Timestamp getScrutnyEndDate() {
        return scrutnyEndDate;
    }

    /**
     * @param scrutnyEndDate the scrutnyEndDate to set
     */
    public void setScrutnyEndDate(Timestamp scrutnyEndDate) {
        this.scrutnyEndDate = scrutnyEndDate;
    }

    /**
     * @return the withdrawlDate
     */
    public Timestamp getWithdrawlDate() {
        return withdrawlDate;
    }

    /**
     * @param withdrawlDate the withdrawlDate to set
     */
    public void setWithdrawlDate(Timestamp withdrawlDate) {
        this.withdrawlDate = withdrawlDate;
    }

    /**
     * @return the withdrawlEndDate
     */
    public Timestamp getWithdrawlEndDate() {
        return withdrawlEndDate;
    }

    /**
     * @param withdrawlEndDate the withdrawlEndDate to set
     */
    public void setWithdrawlEndDate(Timestamp withdrawlEndDate) {
        this.withdrawlEndDate = withdrawlEndDate;
    }

    
    

   
   




}


