package com.myapp.struts.hbm;
// Generated May 2, 2011 12:00:18 PM by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;

/**
 * Library generated by hbm2java
 */
public class Library  implements java.io.Serializable {


     private String libraryId;
     private Integer registrationId;
     private String libraryName;
     private String staffId;
     private String workingStatus;
     private Set courseses = new HashSet(0);
     private Set locations = new HashSet(0);
     private Set feedbacks = new HashSet(0);
     private Set noticeses = new HashSet(0);
     private Set subEmployeeTypes = new HashSet(0);
     private Set staffDetails = new HashSet(0);
     private Set departments = new HashSet(0);
     private Set reservationlists = new HashSet(0);
     private Set bibliographicDetailses = new HashSet(0);
     private Set cirCheckouts = new HashSet(0);
     private Set cirMemberDetails = new HashSet(0);
     private Set documentCategories = new HashSet(0);
     private Set employeeTypes = new HashSet(0);
     private Set acqFinalDemandLists = new HashSet(0);
     private Set cirTransactionHistories = new HashSet(0);
     private Set faculties = new HashSet(0);
     private Set subLibraries = new HashSet(0);

    public Library() {
    }

	
    public Library(String libraryId, String workingStatus) {
        this.libraryId = libraryId;
        this.workingStatus = workingStatus;
    }
    public Library(String libraryId, Integer registrationId, String libraryName, String staffId, String workingStatus, Set courseses, Set locations, Set feedbacks, Set noticeses, Set subEmployeeTypes, Set staffDetails, Set departments, Set reservationlists, Set bibliographicDetailses, Set cirCheckouts, Set cirMemberDetails, Set documentCategories, Set employeeTypes, Set acqFinalDemandLists, Set cirTransactionHistories, Set faculties, Set subLibraries) {
       this.libraryId = libraryId;
       this.registrationId = registrationId;
       this.libraryName = libraryName;
       this.staffId = staffId;
       this.workingStatus = workingStatus;
       this.courseses = courseses;
       this.locations = locations;
       this.feedbacks = feedbacks;
       this.noticeses = noticeses;
       this.subEmployeeTypes = subEmployeeTypes;
       this.staffDetails = staffDetails;
       this.departments = departments;
       this.reservationlists = reservationlists;
       this.bibliographicDetailses = bibliographicDetailses;
       this.cirCheckouts = cirCheckouts;
       this.cirMemberDetails = cirMemberDetails;
       this.documentCategories = documentCategories;
       this.employeeTypes = employeeTypes;
       this.acqFinalDemandLists = acqFinalDemandLists;
       this.cirTransactionHistories = cirTransactionHistories;
       this.faculties = faculties;
       this.subLibraries = subLibraries;
    }
   
    public String getLibraryId() {
        return this.libraryId;
    }
    
    public void setLibraryId(String libraryId) {
        this.libraryId = libraryId;
    }
    public Integer getRegistrationId() {
        return this.registrationId;
    }
    
    public void setRegistrationId(Integer registrationId) {
        this.registrationId = registrationId;
    }
    public String getLibraryName() {
        return this.libraryName;
    }
    
    public void setLibraryName(String libraryName) {
        this.libraryName = libraryName;
    }
    public String getStaffId() {
        return this.staffId;
    }
    
    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }
    public String getWorkingStatus() {
        return this.workingStatus;
    }
    
    public void setWorkingStatus(String workingStatus) {
        this.workingStatus = workingStatus;
    }
    public Set getCourseses() {
        return this.courseses;
    }
    
    public void setCourseses(Set courseses) {
        this.courseses = courseses;
    }
    public Set getLocations() {
        return this.locations;
    }
    
    public void setLocations(Set locations) {
        this.locations = locations;
    }
    public Set getFeedbacks() {
        return this.feedbacks;
    }
    
    public void setFeedbacks(Set feedbacks) {
        this.feedbacks = feedbacks;
    }
    public Set getNoticeses() {
        return this.noticeses;
    }
    
    public void setNoticeses(Set noticeses) {
        this.noticeses = noticeses;
    }
    public Set getSubEmployeeTypes() {
        return this.subEmployeeTypes;
    }
    
    public void setSubEmployeeTypes(Set subEmployeeTypes) {
        this.subEmployeeTypes = subEmployeeTypes;
    }
    public Set getStaffDetails() {
        return this.staffDetails;
    }
    
    public void setStaffDetails(Set staffDetails) {
        this.staffDetails = staffDetails;
    }
    public Set getDepartments() {
        return this.departments;
    }
    
    public void setDepartments(Set departments) {
        this.departments = departments;
    }
    public Set getReservationlists() {
        return this.reservationlists;
    }
    
    public void setReservationlists(Set reservationlists) {
        this.reservationlists = reservationlists;
    }
    public Set getBibliographicDetailses() {
        return this.bibliographicDetailses;
    }
    
    public void setBibliographicDetailses(Set bibliographicDetailses) {
        this.bibliographicDetailses = bibliographicDetailses;
    }
    public Set getCirCheckouts() {
        return this.cirCheckouts;
    }
    
    public void setCirCheckouts(Set cirCheckouts) {
        this.cirCheckouts = cirCheckouts;
    }
    public Set getCirMemberDetails() {
        return this.cirMemberDetails;
    }
    
    public void setCirMemberDetails(Set cirMemberDetails) {
        this.cirMemberDetails = cirMemberDetails;
    }
    public Set getDocumentCategories() {
        return this.documentCategories;
    }
    
    public void setDocumentCategories(Set documentCategories) {
        this.documentCategories = documentCategories;
    }
    public Set getEmployeeTypes() {
        return this.employeeTypes;
    }
    
    public void setEmployeeTypes(Set employeeTypes) {
        this.employeeTypes = employeeTypes;
    }
    public Set getAcqFinalDemandLists() {
        return this.acqFinalDemandLists;
    }
    
    public void setAcqFinalDemandLists(Set acqFinalDemandLists) {
        this.acqFinalDemandLists = acqFinalDemandLists;
    }
    public Set getCirTransactionHistories() {
        return this.cirTransactionHistories;
    }
    
    public void setCirTransactionHistories(Set cirTransactionHistories) {
        this.cirTransactionHistories = cirTransactionHistories;
    }
    public Set getFaculties() {
        return this.faculties;
    }
    
    public void setFaculties(Set faculties) {
        this.faculties = faculties;
    }
    public Set getSubLibraries() {
        return this.subLibraries;
    }
    
    public void setSubLibraries(Set subLibraries) {
        this.subLibraries = subLibraries;
    }




}


