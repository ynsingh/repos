package com.myapp.struts.hbm;
// Generated May 2, 2011 12:00:18 PM by Hibernate Tools 3.2.1.GA



/**
 * DemandlistId generated by hbm2java
 */
public class DemandlistId  implements java.io.Serializable {


     private String libraryId;
     private String memId;
     private String sublibraryId;
     private String title;

    public DemandlistId() {
    }

    public DemandlistId(String libraryId, String memId, String sublibraryId, String title) {
       this.libraryId = libraryId;
       this.memId = memId;
       this.sublibraryId = sublibraryId;
       this.title = title;
    }
   
    public String getLibraryId() {
        return this.libraryId;
    }
    
    public void setLibraryId(String libraryId) {
        this.libraryId = libraryId;
    }
    public String getMemId() {
        return this.memId;
    }
    
    public void setMemId(String memId) {
        this.memId = memId;
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


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof DemandlistId) ) return false;
		 DemandlistId castOther = ( DemandlistId ) other; 
         
		 return ( (this.getLibraryId()==castOther.getLibraryId()) || ( this.getLibraryId()!=null && castOther.getLibraryId()!=null && this.getLibraryId().equals(castOther.getLibraryId()) ) )
 && ( (this.getMemId()==castOther.getMemId()) || ( this.getMemId()!=null && castOther.getMemId()!=null && this.getMemId().equals(castOther.getMemId()) ) )
 && ( (this.getSublibraryId()==castOther.getSublibraryId()) || ( this.getSublibraryId()!=null && castOther.getSublibraryId()!=null && this.getSublibraryId().equals(castOther.getSublibraryId()) ) )
 && ( (this.getTitle()==castOther.getTitle()) || ( this.getTitle()!=null && castOther.getTitle()!=null && this.getTitle().equals(castOther.getTitle()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getLibraryId() == null ? 0 : this.getLibraryId().hashCode() );
         result = 37 * result + ( getMemId() == null ? 0 : this.getMemId().hashCode() );
         result = 37 * result + ( getSublibraryId() == null ? 0 : this.getSublibraryId().hashCode() );
         result = 37 * result + ( getTitle() == null ? 0 : this.getTitle().hashCode() );
         return result;
   }   


}


