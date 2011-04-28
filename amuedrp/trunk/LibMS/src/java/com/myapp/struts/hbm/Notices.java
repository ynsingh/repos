package com.myapp.struts.hbm;
// Generated May 2, 2011 12:00:18 PM by Hibernate Tools 3.2.1.GA



/**
 * Notices generated by hbm2java
 */
public class Notices  implements java.io.Serializable {


     private NoticesId id;
     private Library library;
     private String subject;
     private String detail;
     private String date;
     private String sot;

    public Notices() {
    }

	
    public Notices(NoticesId id, Library library) {
        this.id = id;
        this.library = library;
    }
    public Notices(NoticesId id, Library library, String subject, String detail, String date, String sot) {
       this.id = id;
       this.library = library;
       this.subject = subject;
       this.detail = detail;
       this.date = date;
       this.sot = sot;
    }
   
    public NoticesId getId() {
        return this.id;
    }
    
    public void setId(NoticesId id) {
        this.id = id;
    }
    public Library getLibrary() {
        return this.library;
    }
    
    public void setLibrary(Library library) {
        this.library = library;
    }
    public String getSubject() {
        return this.subject;
    }
    
    public void setSubject(String subject) {
        this.subject = subject;
    }
    public String getDetail() {
        return this.detail;
    }
    
    public void setDetail(String detail) {
        this.detail = detail;
    }
    public String getDate() {
        return this.date;
    }
    
    public void setDate(String date) {
        this.date = date;
    }
    public String getSot() {
        return this.sot;
    }
    
    public void setSot(String sot) {
        this.sot = sot;
    }




}


