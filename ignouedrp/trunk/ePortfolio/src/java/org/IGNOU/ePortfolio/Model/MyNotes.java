package org.IGNOU.ePortfolio.Model;
// Generated Dec 2, 2011 11:06:04 AM by Hibernate Tools 3.2.1.GA



/**
 * MyNotes generated by hbm2java
 */
public class MyNotes  implements java.io.Serializable {


     private long notesId;
     private String userId;
     private String note;
     private String date;
     private String topic;

    public MyNotes() {
    }

	
    public MyNotes(long notesId, String userId, String date) {
        this.notesId = notesId;
        this.userId = userId;
        this.date = date;
    }
    public MyNotes(long notesId, String userId, String note, String date, String topic) {
       this.notesId = notesId;
       this.userId = userId;
       this.note = note;
       this.date = date;
       this.topic = topic;
    }
   
    public long getNotesId() {
        return this.notesId;
    }
    
    public void setNotesId(long notesId) {
        this.notesId = notesId;
    }
    public String getUserId() {
        return this.userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getNote() {
        return this.note;
    }
    
    public void setNote(String note) {
        this.note = note;
    }
    public String getDate() {
        return this.date;
    }
    
    public void setDate(String date) {
        this.date = date;
    }
    public String getTopic() {
        return this.topic;
    }
    
    public void setTopic(String topic) {
        this.topic = topic;
    }




}


