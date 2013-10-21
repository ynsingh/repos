package pojo.hibernate;
// Generated May 30, 2013 2:31:14 PM by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;

/**
 * Departmentmaster generated by hbm2java
 */
public class Departmentmaster  implements java.io.Serializable {


     private Integer dmId;
     private Employeemaster employeemaster;
     private Countrymaster countrymaster;
     private Subinstitutionmaster subinstitutionmaster;
     private Institutionmaster institutionmaster;
     private Statemaster statemaster;
     private String dmName;
     private String dmShortName;
     private String dmAddressLine1;
     private String dmAddressLine2;
     private String dmDistrict;
     private String dmPinNo;
     private String dmEmailId;
     private String dmHeadDesignation;
     private Set erpmPoLocationses = new HashSet(0);
     private Set erpmTenderMasters = new HashSet(0);
     private Set fileMasters = new HashSet(0);
     private Set employeemasters = new HashSet(0);
     private Set erpmTenderSubmissions = new HashSet(0);
     private Set erpmPoMasters = new HashSet(0);
     private Set erpmPurchaseinvoiceMasters = new HashSet(0);
     private Set workflowmasters = new HashSet(0);
     private Set erpmIndentMasters = new HashSet(0);
     private Set committeemasters = new HashSet(0);
     private Set departmentalBudgetAllocations = new HashSet(0);
     private Set erpmPurchasechallanMasters = new HashSet(0);
     private Set erpmIssueReturnMasters = new HashSet(0);
     private Set fileDetails = new HashSet(0);
     private Set budgetmasters = new HashSet(0);
     private Set erpmStockReceiveds = new HashSet(0);
     private Set storemasters = new HashSet(0);
     private Set erpmTenderRevisionses = new HashSet(0);
     private Set erpmIssueMastersForIsmFromDepartmentId = new HashSet(0);
     private Set erpmuserroles = new HashSet(0);
     private Set supplierregistrationauthorities = new HashSet(0);
     private Set erpmIssueReceives = new HashSet(0);
     private Set erpmuserdepartmentses = new HashSet(0);
     private Set erpmTempOpeningStocks = new HashSet(0);
     private Set erpmIssueMastersForIsmToDepartmentId = new HashSet(0);
     private Set erpmTenderSchedules = new HashSet(0);

    public Departmentmaster() {
    }

	
    public Departmentmaster(String dmName) {
        this.dmName = dmName;
    }
    public Departmentmaster(Employeemaster employeemaster, Countrymaster countrymaster, Subinstitutionmaster subinstitutionmaster, Institutionmaster institutionmaster, Statemaster statemaster, String dmName, String dmShortName, String dmAddressLine1, String dmAddressLine2, String dmDistrict, String dmPinNo, String dmEmailId, String dmHeadDesignation, Set erpmPoLocationses, Set erpmTenderMasters, Set fileMasters, Set employeemasters, Set erpmTenderSubmissions, Set erpmPoMasters, Set erpmPurchaseinvoiceMasters, Set workflowmasters, Set erpmIndentMasters, Set committeemasters, Set departmentalBudgetAllocations, Set erpmPurchasechallanMasters, Set erpmIssueReturnMasters, Set fileDetails, Set budgetmasters, Set erpmStockReceiveds, Set storemasters, Set erpmTenderRevisionses, Set erpmIssueMastersForIsmFromDepartmentId, Set erpmuserroles, Set supplierregistrationauthorities, Set erpmIssueReceives, Set erpmuserdepartmentses, Set erpmTempOpeningStocks, Set erpmIssueMastersForIsmToDepartmentId, Set erpmTenderSchedules) {
       this.employeemaster = employeemaster;
       this.countrymaster = countrymaster;
       this.subinstitutionmaster = subinstitutionmaster;
       this.institutionmaster = institutionmaster;
       this.statemaster = statemaster;
       this.dmName = dmName;
       this.dmShortName = dmShortName;
       this.dmAddressLine1 = dmAddressLine1;
       this.dmAddressLine2 = dmAddressLine2;
       this.dmDistrict = dmDistrict;
       this.dmPinNo = dmPinNo;
       this.dmEmailId = dmEmailId;
       this.dmHeadDesignation = dmHeadDesignation;
       this.erpmPoLocationses = erpmPoLocationses;
       this.erpmTenderMasters = erpmTenderMasters;
       this.fileMasters = fileMasters;
       this.employeemasters = employeemasters;
       this.erpmTenderSubmissions = erpmTenderSubmissions;
       this.erpmPoMasters = erpmPoMasters;
       this.erpmPurchaseinvoiceMasters = erpmPurchaseinvoiceMasters;
       this.workflowmasters = workflowmasters;
       this.erpmIndentMasters = erpmIndentMasters;
       this.committeemasters = committeemasters;
       this.departmentalBudgetAllocations = departmentalBudgetAllocations;
       this.erpmPurchasechallanMasters = erpmPurchasechallanMasters;
       this.erpmIssueReturnMasters = erpmIssueReturnMasters;
       this.fileDetails = fileDetails;
       this.budgetmasters = budgetmasters;
       this.erpmStockReceiveds = erpmStockReceiveds;
       this.storemasters = storemasters;
       this.erpmTenderRevisionses = erpmTenderRevisionses;
       this.erpmIssueMastersForIsmFromDepartmentId = erpmIssueMastersForIsmFromDepartmentId;
       this.erpmuserroles = erpmuserroles;
       this.supplierregistrationauthorities = supplierregistrationauthorities;
       this.erpmIssueReceives = erpmIssueReceives;
       this.erpmuserdepartmentses = erpmuserdepartmentses;
       this.erpmTempOpeningStocks = erpmTempOpeningStocks;
       this.erpmIssueMastersForIsmToDepartmentId = erpmIssueMastersForIsmToDepartmentId;
       this.erpmTenderSchedules = erpmTenderSchedules;
    }
   
    public Integer getDmId() {
        return this.dmId;
    }
    
    public void setDmId(Integer dmId) {
        this.dmId = dmId;
    }
    public Employeemaster getEmployeemaster() {
        return this.employeemaster;
    }
    
    public void setEmployeemaster(Employeemaster employeemaster) {
        this.employeemaster = employeemaster;
    }
    public Countrymaster getCountrymaster() {
        return this.countrymaster;
    }
    
    public void setCountrymaster(Countrymaster countrymaster) {
        this.countrymaster = countrymaster;
    }
    public Subinstitutionmaster getSubinstitutionmaster() {
        return this.subinstitutionmaster;
    }
    
    public void setSubinstitutionmaster(Subinstitutionmaster subinstitutionmaster) {
        this.subinstitutionmaster = subinstitutionmaster;
    }
    public Institutionmaster getInstitutionmaster() {
        return this.institutionmaster;
    }
    
    public void setInstitutionmaster(Institutionmaster institutionmaster) {
        this.institutionmaster = institutionmaster;
    }
    public Statemaster getStatemaster() {
        return this.statemaster;
    }
    
    public void setStatemaster(Statemaster statemaster) {
        this.statemaster = statemaster;
    }
    public String getDmName() {
        return this.dmName;
    }
    
    public void setDmName(String dmName) {
        this.dmName = dmName;
    }
    public String getDmShortName() {
        return this.dmShortName;
    }
    
    public void setDmShortName(String dmShortName) {
        this.dmShortName = dmShortName;
    }
    public String getDmAddressLine1() {
        return this.dmAddressLine1;
    }
    
    public void setDmAddressLine1(String dmAddressLine1) {
        this.dmAddressLine1 = dmAddressLine1;
    }
    public String getDmAddressLine2() {
        return this.dmAddressLine2;
    }
    
    public void setDmAddressLine2(String dmAddressLine2) {
        this.dmAddressLine2 = dmAddressLine2;
    }
    public String getDmDistrict() {
        return this.dmDistrict;
    }
    
    public void setDmDistrict(String dmDistrict) {
        this.dmDistrict = dmDistrict;
    }
    public String getDmPinNo() {
        return this.dmPinNo;
    }
    
    public void setDmPinNo(String dmPinNo) {
        this.dmPinNo = dmPinNo;
    }
    public String getDmEmailId() {
        return this.dmEmailId;
    }
    
    public void setDmEmailId(String dmEmailId) {
        this.dmEmailId = dmEmailId;
    }
    public String getDmHeadDesignation() {
        return this.dmHeadDesignation;
    }
    
    public void setDmHeadDesignation(String dmHeadDesignation) {
        this.dmHeadDesignation = dmHeadDesignation;
    }
    public Set getErpmPoLocationses() {
        return this.erpmPoLocationses;
    }
    
    public void setErpmPoLocationses(Set erpmPoLocationses) {
        this.erpmPoLocationses = erpmPoLocationses;
    }
    public Set getErpmTenderMasters() {
        return this.erpmTenderMasters;
    }
    
    public void setErpmTenderMasters(Set erpmTenderMasters) {
        this.erpmTenderMasters = erpmTenderMasters;
    }
    public Set getFileMasters() {
        return this.fileMasters;
    }
    
    public void setFileMasters(Set fileMasters) {
        this.fileMasters = fileMasters;
    }
    public Set getEmployeemasters() {
        return this.employeemasters;
    }
    
    public void setEmployeemasters(Set employeemasters) {
        this.employeemasters = employeemasters;
    }
    public Set getErpmTenderSubmissions() {
        return this.erpmTenderSubmissions;
    }
    
    public void setErpmTenderSubmissions(Set erpmTenderSubmissions) {
        this.erpmTenderSubmissions = erpmTenderSubmissions;
    }
    public Set getErpmPoMasters() {
        return this.erpmPoMasters;
    }
    
    public void setErpmPoMasters(Set erpmPoMasters) {
        this.erpmPoMasters = erpmPoMasters;
    }
    public Set getErpmPurchaseinvoiceMasters() {
        return this.erpmPurchaseinvoiceMasters;
    }
    
    public void setErpmPurchaseinvoiceMasters(Set erpmPurchaseinvoiceMasters) {
        this.erpmPurchaseinvoiceMasters = erpmPurchaseinvoiceMasters;
    }
    public Set getWorkflowmasters() {
        return this.workflowmasters;
    }
    
    public void setWorkflowmasters(Set workflowmasters) {
        this.workflowmasters = workflowmasters;
    }
    public Set getErpmIndentMasters() {
        return this.erpmIndentMasters;
    }
    
    public void setErpmIndentMasters(Set erpmIndentMasters) {
        this.erpmIndentMasters = erpmIndentMasters;
    }
    public Set getCommitteemasters() {
        return this.committeemasters;
    }
    
    public void setCommitteemasters(Set committeemasters) {
        this.committeemasters = committeemasters;
    }
    public Set getDepartmentalBudgetAllocations() {
        return this.departmentalBudgetAllocations;
    }
    
    public void setDepartmentalBudgetAllocations(Set departmentalBudgetAllocations) {
        this.departmentalBudgetAllocations = departmentalBudgetAllocations;
    }
    public Set getErpmPurchasechallanMasters() {
        return this.erpmPurchasechallanMasters;
    }
    
    public void setErpmPurchasechallanMasters(Set erpmPurchasechallanMasters) {
        this.erpmPurchasechallanMasters = erpmPurchasechallanMasters;
    }
    public Set getErpmIssueReturnMasters() {
        return this.erpmIssueReturnMasters;
    }
    
    public void setErpmIssueReturnMasters(Set erpmIssueReturnMasters) {
        this.erpmIssueReturnMasters = erpmIssueReturnMasters;
    }
    public Set getFileDetails() {
        return this.fileDetails;
    }
    
    public void setFileDetails(Set fileDetails) {
        this.fileDetails = fileDetails;
    }
    public Set getBudgetmasters() {
        return this.budgetmasters;
    }
    
    public void setBudgetmasters(Set budgetmasters) {
        this.budgetmasters = budgetmasters;
    }
    public Set getErpmStockReceiveds() {
        return this.erpmStockReceiveds;
    }
    
    public void setErpmStockReceiveds(Set erpmStockReceiveds) {
        this.erpmStockReceiveds = erpmStockReceiveds;
    }
    public Set getStoremasters() {
        return this.storemasters;
    }
    
    public void setStoremasters(Set storemasters) {
        this.storemasters = storemasters;
    }
    public Set getErpmTenderRevisionses() {
        return this.erpmTenderRevisionses;
    }
    
    public void setErpmTenderRevisionses(Set erpmTenderRevisionses) {
        this.erpmTenderRevisionses = erpmTenderRevisionses;
    }
    public Set getErpmIssueMastersForIsmFromDepartmentId() {
        return this.erpmIssueMastersForIsmFromDepartmentId;
    }
    
    public void setErpmIssueMastersForIsmFromDepartmentId(Set erpmIssueMastersForIsmFromDepartmentId) {
        this.erpmIssueMastersForIsmFromDepartmentId = erpmIssueMastersForIsmFromDepartmentId;
    }
    public Set getErpmuserroles() {
        return this.erpmuserroles;
    }
    
    public void setErpmuserroles(Set erpmuserroles) {
        this.erpmuserroles = erpmuserroles;
    }
    public Set getSupplierregistrationauthorities() {
        return this.supplierregistrationauthorities;
    }
    
    public void setSupplierregistrationauthorities(Set supplierregistrationauthorities) {
        this.supplierregistrationauthorities = supplierregistrationauthorities;
    }
    public Set getErpmIssueReceives() {
        return this.erpmIssueReceives;
    }
    
    public void setErpmIssueReceives(Set erpmIssueReceives) {
        this.erpmIssueReceives = erpmIssueReceives;
    }
    public Set getErpmuserdepartmentses() {
        return this.erpmuserdepartmentses;
    }
    
    public void setErpmuserdepartmentses(Set erpmuserdepartmentses) {
        this.erpmuserdepartmentses = erpmuserdepartmentses;
    }
    public Set getErpmTempOpeningStocks() {
        return this.erpmTempOpeningStocks;
    }
    
    public void setErpmTempOpeningStocks(Set erpmTempOpeningStocks) {
        this.erpmTempOpeningStocks = erpmTempOpeningStocks;
    }
    public Set getErpmIssueMastersForIsmToDepartmentId() {
        return this.erpmIssueMastersForIsmToDepartmentId;
    }
    
    public void setErpmIssueMastersForIsmToDepartmentId(Set erpmIssueMastersForIsmToDepartmentId) {
        this.erpmIssueMastersForIsmToDepartmentId = erpmIssueMastersForIsmToDepartmentId;
    }
    public Set getErpmTenderSchedules() {
        return this.erpmTenderSchedules;
    }
    
    public void setErpmTenderSchedules(Set erpmTenderSchedules) {
        this.erpmTenderSchedules = erpmTenderSchedules;
    }




}


