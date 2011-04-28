package com.myapp.struts.hbm;
// Generated May 2, 2011 12:00:18 PM by Hibernate Tools 3.2.1.GA



/**
 * NoticesId generated by hbm2java
 */
public class NoticesId  implements java.io.Serializable {


     private String libraryId;
     private String sublibraryId;
     private String noticeId;

    public NoticesId() {
    }

    public NoticesId(String libraryId, String sublibraryId, String noticeId) {
       this.libraryId = libraryId;
       this.sublibraryId = sublibraryId;
       this.noticeId = noticeId;
    }
   
    public String getLibraryId() {
        return this.libraryId;
    }
    
    public void setLibraryId(String libraryId) {
        this.libraryId = libraryId;
    }
    public String getSublibraryId() {
        return this.sublibraryId;
    }
    
    public void setSublibraryId(String sublibraryId) {
        this.sublibraryId = sublibraryId;
    }
    public String getNoticeId() {
        return this.noticeId;
    }
    
    public void setNoticeId(String noticeId) {
        this.noticeId = noticeId;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof NoticesId) ) return false;
		 NoticesId castOther = ( NoticesId ) other; 
         
		 return ( (this.getLibraryId()==castOther.getLibraryId()) || ( this.getLibraryId()!=null && castOther.getLibraryId()!=null && this.getLibraryId().equals(castOther.getLibraryId()) ) )
 && ( (this.getSublibraryId()==castOther.getSublibraryId()) || ( this.getSublibraryId()!=null && castOther.getSublibraryId()!=null && this.getSublibraryId().equals(castOther.getSublibraryId()) ) )
 && ( (this.getNoticeId()==castOther.getNoticeId()) || ( this.getNoticeId()!=null && castOther.getNoticeId()!=null && this.getNoticeId().equals(castOther.getNoticeId()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getLibraryId() == null ? 0 : this.getLibraryId().hashCode() );
         result = 37 * result + ( getSublibraryId() == null ? 0 : this.getSublibraryId().hashCode() );
         result = 37 * result + ( getNoticeId() == null ? 0 : this.getNoticeId().hashCode() );
         return result;
   }   


}


