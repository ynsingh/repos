package pojo.hibernate;
// Generated May 30, 2013 2:31:14 PM by Hibernate Tools 3.2.1.GA


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * ErpmTenderMaster generated by hbm2java
 */
public class ErpmTenderMaster  implements java.io.Serializable {


     private Integer tmTmId;
     private Institutionmaster institutionmaster;
     private Departmentmaster departmentmaster;
     private Subinstitutionmaster subinstitutionmaster;
     private ErpmGenMaster erpmGenMasterByTmTypeId;
     private ErpmGenMaster erpmGenMasterByTmStatusId;
     private ErpmTenderSubmission erpmTenderSubmission;
     private String tmTenderNo;
     private String tmName;
     private Date tmDate;
     private Integer tmFee;
     private Integer tmEstimatedAmount;
     private Integer tmEmdAmount;
     private String tmDocumentLink;
     private String tmNoticeLink;
     private String tmRemarks;
     private Set erpmTenderSubmissions = new HashSet(0);
     private Set erpmTenderSchedules = new HashSet(0);
     private Set erpmTenderRevisionses = new HashSet(0);

    public ErpmTenderMaster() {
    }

	
    public ErpmTenderMaster(Institutionmaster institutionmaster, Departmentmaster departmentmaster, Subinstitutionmaster subinstitutionmaster, ErpmGenMaster erpmGenMasterByTmTypeId, ErpmGenMaster erpmGenMasterByTmStatusId, String tmTenderNo, String tmName, Date tmDate) {
        this.institutionmaster = institutionmaster;
        this.departmentmaster = departmentmaster;
        this.subinstitutionmaster = subinstitutionmaster;
        this.erpmGenMasterByTmTypeId = erpmGenMasterByTmTypeId;
        this.erpmGenMasterByTmStatusId = erpmGenMasterByTmStatusId;
        this.tmTenderNo = tmTenderNo;
        this.tmName = tmName;
        this.tmDate = tmDate;
    }
    public ErpmTenderMaster(Institutionmaster institutionmaster, Departmentmaster departmentmaster, Subinstitutionmaster subinstitutionmaster, ErpmGenMaster erpmGenMasterByTmTypeId, ErpmGenMaster erpmGenMasterByTmStatusId, ErpmTenderSubmission erpmTenderSubmission, String tmTenderNo, String tmName, Date tmDate, Integer tmFee, Integer tmEstimatedAmount, Integer tmEmdAmount, String tmDocumentLink, String tmNoticeLink, String tmRemarks, Set erpmTenderSubmissions, Set erpmTenderSchedules, Set erpmTenderRevisionses) {
       this.institutionmaster = institutionmaster;
       this.departmentmaster = departmentmaster;
       this.subinstitutionmaster = subinstitutionmaster;
       this.erpmGenMasterByTmTypeId = erpmGenMasterByTmTypeId;
       this.erpmGenMasterByTmStatusId = erpmGenMasterByTmStatusId;
       this.erpmTenderSubmission = erpmTenderSubmission;
       this.tmTenderNo = tmTenderNo;
       this.tmName = tmName;
       this.tmDate = tmDate;
       this.tmFee = tmFee;
       this.tmEstimatedAmount = tmEstimatedAmount;
       this.tmEmdAmount = tmEmdAmount;
       this.tmDocumentLink = tmDocumentLink;
       this.tmNoticeLink = tmNoticeLink;
       this.tmRemarks = tmRemarks;
       this.erpmTenderSubmissions = erpmTenderSubmissions;
       this.erpmTenderSchedules = erpmTenderSchedules;
       this.erpmTenderRevisionses = erpmTenderRevisionses;
    }
   
    public Integer getTmTmId() {
        return this.tmTmId;
    }
    
    public void setTmTmId(Integer tmTmId) {
        this.tmTmId = tmTmId;
    }
    public Institutionmaster getInstitutionmaster() {
        return this.institutionmaster;
    }
    
    public void setInstitutionmaster(Institutionmaster institutionmaster) {
        this.institutionmaster = institutionmaster;
    }
    public Departmentmaster getDepartmentmaster() {
        return this.departmentmaster;
    }
    
    public void setDepartmentmaster(Departmentmaster departmentmaster) {
        this.departmentmaster = departmentmaster;
    }
    public Subinstitutionmaster getSubinstitutionmaster() {
        return this.subinstitutionmaster;
    }
    
    public void setSubinstitutionmaster(Subinstitutionmaster subinstitutionmaster) {
        this.subinstitutionmaster = subinstitutionmaster;
    }
    public ErpmGenMaster getErpmGenMasterByTmTypeId() {
        return this.erpmGenMasterByTmTypeId;
    }
    
    public void setErpmGenMasterByTmTypeId(ErpmGenMaster erpmGenMasterByTmTypeId) {
        this.erpmGenMasterByTmTypeId = erpmGenMasterByTmTypeId;
    }
    public ErpmGenMaster getErpmGenMasterByTmStatusId() {
        return this.erpmGenMasterByTmStatusId;
    }
    
    public void setErpmGenMasterByTmStatusId(ErpmGenMaster erpmGenMasterByTmStatusId) {
        this.erpmGenMasterByTmStatusId = erpmGenMasterByTmStatusId;
    }
    public ErpmTenderSubmission getErpmTenderSubmission() {
        return this.erpmTenderSubmission;
    }
    
    public void setErpmTenderSubmission(ErpmTenderSubmission erpmTenderSubmission) {
        this.erpmTenderSubmission = erpmTenderSubmission;
    }
    public String getTmTenderNo() {
        return this.tmTenderNo;
    }
    
    public void setTmTenderNo(String tmTenderNo) {
        this.tmTenderNo = tmTenderNo;
    }
    public String getTmName() {
        return this.tmName;
    }
    
    public void setTmName(String tmName) {
        this.tmName = tmName;
    }
    public Date getTmDate() {
        return this.tmDate;
    }
    
    public void setTmDate(Date tmDate) {
        this.tmDate = tmDate;
    }
    public Integer getTmFee() {
        return this.tmFee;
    }
    
    public void setTmFee(Integer tmFee) {
        this.tmFee = tmFee;
    }
    public Integer getTmEstimatedAmount() {
        return this.tmEstimatedAmount;
    }
    
    public void setTmEstimatedAmount(Integer tmEstimatedAmount) {
        this.tmEstimatedAmount = tmEstimatedAmount;
    }
    public Integer getTmEmdAmount() {
        return this.tmEmdAmount;
    }
    
    public void setTmEmdAmount(Integer tmEmdAmount) {
        this.tmEmdAmount = tmEmdAmount;
    }
    public String getTmDocumentLink() {
        return this.tmDocumentLink;
    }
    
    public void setTmDocumentLink(String tmDocumentLink) {
        this.tmDocumentLink = tmDocumentLink;
    }
    public String getTmNoticeLink() {
        return this.tmNoticeLink;
    }
    
    public void setTmNoticeLink(String tmNoticeLink) {
        this.tmNoticeLink = tmNoticeLink;
    }
    public String getTmRemarks() {
        return this.tmRemarks;
    }
    
    public void setTmRemarks(String tmRemarks) {
        this.tmRemarks = tmRemarks;
    }
    public Set getErpmTenderSubmissions() {
        return this.erpmTenderSubmissions;
    }
    
    public void setErpmTenderSubmissions(Set erpmTenderSubmissions) {
        this.erpmTenderSubmissions = erpmTenderSubmissions;
    }
    public Set getErpmTenderSchedules() {
        return this.erpmTenderSchedules;
    }
    
    public void setErpmTenderSchedules(Set erpmTenderSchedules) {
        this.erpmTenderSchedules = erpmTenderSchedules;
    }
    public Set getErpmTenderRevisionses() {
        return this.erpmTenderRevisionses;
    }
    
    public void setErpmTenderRevisionses(Set erpmTenderRevisionses) {
        this.erpmTenderRevisionses = erpmTenderRevisionses;
    }




}


