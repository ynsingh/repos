package com.myapp.struts.hbm;
// Generated 1 Oct, 2011 2:10:18 PM by Hibernate Tools 3.2.1.GA



/**
 * CancellationReasonId generated by hbm2java
 */
public class CancellationReasonId  implements java.io.Serializable {


     private String id;
     private String libraryId;
     private String sublibraryId;

    public CancellationReasonId() {
    }

    public CancellationReasonId(String id, String libraryId, String sublibraryId) {
       this.id = id;
       this.libraryId = libraryId;
       this.sublibraryId = sublibraryId;
    }
   
    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
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


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof CancellationReasonId) ) return false;
		 CancellationReasonId castOther = ( CancellationReasonId ) other; 
         
		 return ( (this.getId()==castOther.getId()) || ( this.getId()!=null && castOther.getId()!=null && this.getId().equals(castOther.getId()) ) )
 && ( (this.getLibraryId()==castOther.getLibraryId()) || ( this.getLibraryId()!=null && castOther.getLibraryId()!=null && this.getLibraryId().equals(castOther.getLibraryId()) ) )
 && ( (this.getSublibraryId()==castOther.getSublibraryId()) || ( this.getSublibraryId()!=null && castOther.getSublibraryId()!=null && this.getSublibraryId().equals(castOther.getSublibraryId()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getId() == null ? 0 : this.getId().hashCode() );
         result = 37 * result + ( getLibraryId() == null ? 0 : this.getLibraryId().hashCode() );
         result = 37 * result + ( getSublibraryId() == null ? 0 : this.getSublibraryId().hashCode() );
         return result;
   }   


}


