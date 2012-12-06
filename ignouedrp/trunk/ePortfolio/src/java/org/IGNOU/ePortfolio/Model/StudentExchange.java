package org.IGNOU.ePortfolio.Model;
// Generated Dec 28, 2011 12:22:55 PM by Hibernate Tools 3.2.1.GA



/**
 * StudentExchange generated by hbm2java
 */
public class StudentExchange  implements java.io.Serializable {


     private long studentExchangeId;
     private String programmeType;
     private String nameUniversity;
     private String role;
     private String programmeTheme;
     private String venue;
     private String state;
     private String country;
     private String durationFrom;
     private String durationTo;
     private String degreeLevel;
     private String degraeeName;
     private String researchColl;
     private String url;
     private String description;
     private String userId;
     private String ifOther;

    public StudentExchange() {
    }

	
    public StudentExchange(long studentExchangeId) {
        this.studentExchangeId = studentExchangeId;
    }
    public StudentExchange(long studentExchangeId, String programmeType, String nameUniversity, String role, String programmeTheme, String venue, String state, String country, String durationFrom, String durationTo, String degreeLevel, String degraeeName, String researchColl, String url, String description, String userId, String ifOther) {
       this.studentExchangeId = studentExchangeId;
       this.programmeType = programmeType;
       this.nameUniversity = nameUniversity;
       this.role = role;
       this.programmeTheme = programmeTheme;
       this.venue = venue;
       this.state = state;
       this.country = country;
       this.durationFrom = durationFrom;
       this.durationTo = durationTo;
       this.degreeLevel = degreeLevel;
       this.degraeeName = degraeeName;
       this.researchColl = researchColl;
       this.url = url;
       this.description = description;
       this.userId = userId;
       this.ifOther = ifOther;
    }
   
    public long getStudentExchangeId() {
        return this.studentExchangeId;
    }
    
    public void setStudentExchangeId(long studentExchangeId) {
        this.studentExchangeId = studentExchangeId;
    }
    public String getProgrammeType() {
        return this.programmeType;
    }
    
    public void setProgrammeType(String programmeType) {
        this.programmeType = programmeType;
    }
    public String getNameUniversity() {
        return this.nameUniversity;
    }
    
    public void setNameUniversity(String nameUniversity) {
        this.nameUniversity = nameUniversity;
    }
    public String getRole() {
        return this.role;
    }
    
    public void setRole(String role) {
        this.role = role;
    }
    public String getProgrammeTheme() {
        return this.programmeTheme;
    }
    
    public void setProgrammeTheme(String programmeTheme) {
        this.programmeTheme = programmeTheme;
    }
    public String getVenue() {
        return this.venue;
    }
    
    public void setVenue(String venue) {
        this.venue = venue;
    }
    public String getState() {
        return this.state;
    }
    
    public void setState(String state) {
        this.state = state;
    }
    public String getCountry() {
        return this.country;
    }
    
    public void setCountry(String country) {
        this.country = country;
    }
    public String getDurationFrom() {
        return this.durationFrom;
    }
    
    public void setDurationFrom(String durationFrom) {
        this.durationFrom = durationFrom;
    }
    public String getDurationTo() {
        return this.durationTo;
    }
    
    public void setDurationTo(String durationTo) {
        this.durationTo = durationTo;
    }
    public String getDegreeLevel() {
        return this.degreeLevel;
    }
    
    public void setDegreeLevel(String degreeLevel) {
        this.degreeLevel = degreeLevel;
    }
    public String getDegraeeName() {
        return this.degraeeName;
    }
    
    public void setDegraeeName(String degraeeName) {
        this.degraeeName = degraeeName;
    }
    public String getResearchColl() {
        return this.researchColl;
    }
    
    public void setResearchColl(String researchColl) {
        this.researchColl = researchColl;
    }
    public String getUrl() {
        return this.url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    public String getUserId() {
        return this.userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getIfOther() {
        return this.ifOther;
    }
    
    public void setIfOther(String ifOther) {
        this.ifOther = ifOther;
    }




}


