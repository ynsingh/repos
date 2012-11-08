package com.myapp.struts.hbm;
// Generated May 2, 2011 12:00:18 PM by Hibernate Tools 3.2.1.GA


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * StaffDetail generated by hbm2java
 */
public class StaffDetail  implements java.io.Serializable {


     private StaffDetailId id;
     private Library library;
     private String sublibraryId;
     private String title;
     private String firstName;
     private String lastName;
     private String contactNo;
     private String mobileNo;
     private String emailId;
     private String dateJoining;
     private String dateReleaving;
     private String fatherName;
     private String dateOfBirth;
     private String gender;
     private String address1;
     private String city1;
     private String state1;
     private String country1;
     private String zip1;
     private String address2;
     private String city2;
     private String state2;
     private String country2;
     private String zip2;
     private byte[] staffImage;
     private Set privileges = new HashSet(0);
     private Set acqPrivileges = new HashSet(0);
     private Set serPrivileges = new HashSet(0);
     private Set cirPrivileges = new HashSet(0);
     private Set catPrivileges = new HashSet(0);
     private Set logins = new HashSet(0);

    public StaffDetail() {
    }

	
    public StaffDetail(StaffDetailId id, Library library, String sublibraryId) {
        this.id = id;
        this.library = library;
        this.sublibraryId = sublibraryId;
    }
    public StaffDetail(StaffDetailId id, Library library, String sublibraryId, String title, String firstName, String lastName, String contactNo, String mobileNo, String emailId, String dateJoining, String dateReleaving, String fatherName, String dateOfBirth, String gender, String address1, String city1, String state1, String country1, String zip1, String address2, String city2, String state2, String country2, String zip2, byte[] staffImage, Set privileges, Set acqPrivileges, Set serPrivileges, Set cirPrivileges, Set catPrivileges, Set logins) {
       this.id = id;
       this.library = library;
       this.sublibraryId = sublibraryId;
       this.title = title;
       this.firstName = firstName;
       this.lastName = lastName;
       this.contactNo = contactNo;
       this.mobileNo = mobileNo;
       this.emailId = emailId;
       this.dateJoining = dateJoining;
       this.dateReleaving = dateReleaving;
       this.fatherName = fatherName;
       this.dateOfBirth = dateOfBirth;
       this.gender = gender;
       this.address1 = address1;
       this.city1 = city1;
       this.state1 = state1;
       this.country1 = country1;
       this.zip1 = zip1;
       this.address2 = address2;
       this.city2 = city2;
       this.state2 = state2;
       this.country2 = country2;
       this.zip2 = zip2;
       this.staffImage = staffImage;
       this.privileges = privileges;
       this.acqPrivileges = acqPrivileges;
       this.serPrivileges = serPrivileges;
       this.cirPrivileges = cirPrivileges;
       this.catPrivileges = catPrivileges;
       this.logins = logins;
    }
   
    public StaffDetailId getId() {
        return this.id;
    }
    
    public void setId(StaffDetailId id) {
        this.id = id;
    }
    public Library getLibrary() {
        return this.library;
    }
    
    public void setLibrary(Library library) {
        this.library = library;
    }
    public String getSublibraryId() {
        return this.sublibraryId;
    }
    
    public void setSublibraryId(String sublibraryId) {
        this.sublibraryId = sublibraryId;
    }
    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    public String getFirstName() {
        return this.firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return this.lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getContactNo() {
        return this.contactNo;
    }
    
    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }
    public String getMobileNo() {
        return this.mobileNo;
    }
    
    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }
    public String getEmailId() {
        return this.emailId;
    }
    
    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
    public String getDateJoining() {
        return this.dateJoining;
    }
    
    public void setDateJoining(String dateJoining) {
        this.dateJoining = dateJoining;
    }
    public String getDateReleaving() {
        return this.dateReleaving;
    }
    
    public void setDateReleaving(String dateReleaving) {
        this.dateReleaving = dateReleaving;
    }
    public String getFatherName() {
        return this.fatherName;
    }
    
    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }
    public String getDateOfBirth() {
        return this.dateOfBirth;
    }
    
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    public String getGender() {
        return this.gender;
    }
    
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getAddress1() {
        return this.address1;
    }
    
    public void setAddress1(String address1) {
        this.address1 = address1;
    }
    public String getCity1() {
        return this.city1;
    }
    
    public void setCity1(String city1) {
        this.city1 = city1;
    }
    public String getState1() {
        return this.state1;
    }
    
    public void setState1(String state1) {
        this.state1 = state1;
    }
    public String getCountry1() {
        return this.country1;
    }
    
    public void setCountry1(String country1) {
        this.country1 = country1;
    }
    public String getZip1() {
        return this.zip1;
    }
    
    public void setZip1(String zip1) {
        this.zip1 = zip1;
    }
    public String getAddress2() {
        return this.address2;
    }
    
    public void setAddress2(String address2) {
        this.address2 = address2;
    }
    public String getCity2() {
        return this.city2;
    }
    
    public void setCity2(String city2) {
        this.city2 = city2;
    }
    public String getState2() {
        return this.state2;
    }
    
    public void setState2(String state2) {
        this.state2 = state2;
    }
    public String getCountry2() {
        return this.country2;
    }
    
    public void setCountry2(String country2) {
        this.country2 = country2;
    }
    public String getZip2() {
        return this.zip2;
    }
    
    public void setZip2(String zip2) {
        this.zip2 = zip2;
    }
    public byte[] getStaffImage() {
        return this.staffImage;
    }
    
    public void setStaffImage(byte[] staffImage) {
        this.staffImage = staffImage;
    }
    public Set getPrivileges() {
        return this.privileges;
    }
    
    public void setPrivileges(Set privileges) {
        this.privileges = privileges;
    }
    public Set getAcqPrivileges() {
        return this.acqPrivileges;
    }
    
    public void setAcqPrivileges(Set acqPrivileges) {
        this.acqPrivileges = acqPrivileges;
    }
    public Set getSerPrivileges() {
        return this.serPrivileges;
    }
    
    public void setSerPrivileges(Set serPrivileges) {
        this.serPrivileges = serPrivileges;
    }
    public Set getCirPrivileges() {
        return this.cirPrivileges;
    }
    
    public void setCirPrivileges(Set cirPrivileges) {
        this.cirPrivileges = cirPrivileges;
    }
    public Set getCatPrivileges() {
        return this.catPrivileges;
    }
    
    public void setCatPrivileges(Set catPrivileges) {
        this.catPrivileges = catPrivileges;
    }
    public Set getLogins() {
        return this.logins;
    }
    
    public void setLogins(Set logins) {
        this.logins = logins;
    }




}


