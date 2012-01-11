package com.myapp.struts.hbm;
// Generated 16 Nov, 2011 3:38:39 PM by Hibernate Tools 3.2.1.GA



/**
 * SerNewEntryId generated by hbm2java
 */
public class SerNewEntryId  implements java.io.Serializable {


     private String libraryId;
     private String sublibraryId;
     private String newSerialId;

    public SerNewEntryId() {
    }

    public SerNewEntryId(String libraryId, String sublibraryId, String newSerialId) {
       this.libraryId = libraryId;
       this.sublibraryId = sublibraryId;
       this.newSerialId = newSerialId;
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
    public String getNewSerialId() {
        return this.newSerialId;
    }
    
    public void setNewSerialId(String newSerialId) {
        this.newSerialId = newSerialId;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof SerNewEntryId) ) return false;
		 SerNewEntryId castOther = ( SerNewEntryId ) other; 
         
		 return ( (this.getLibraryId()==castOther.getLibraryId()) || ( this.getLibraryId()!=null && castOther.getLibraryId()!=null && this.getLibraryId().equals(castOther.getLibraryId()) ) )
 && ( (this.getSublibraryId()==castOther.getSublibraryId()) || ( this.getSublibraryId()!=null && castOther.getSublibraryId()!=null && this.getSublibraryId().equals(castOther.getSublibraryId()) ) )
 && ( (this.getNewSerialId()==castOther.getNewSerialId()) || ( this.getNewSerialId()!=null && castOther.getNewSerialId()!=null && this.getNewSerialId().equals(castOther.getNewSerialId()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getLibraryId() == null ? 0 : this.getLibraryId().hashCode() );
         result = 37 * result + ( getSublibraryId() == null ? 0 : this.getSublibraryId().hashCode() );
         result = 37 * result + ( getNewSerialId() == null ? 0 : this.getNewSerialId().hashCode() );
         return result;
   }   


}


