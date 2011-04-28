package com.myapp.struts.hbm;
// Generated May 2, 2011 12:00:18 PM by Hibernate Tools 3.2.1.GA



/**
 * BookCategoryId generated by hbm2java
 */
public class BookCategoryId  implements java.io.Serializable {


     private String bookType;
     private String libraryId;
     private String emptypeId;
     private String subEmptypeId;

    public BookCategoryId() {
    }

    public BookCategoryId(String bookType, String libraryId, String emptypeId, String subEmptypeId) {
       this.bookType = bookType;
       this.libraryId = libraryId;
       this.emptypeId = emptypeId;
       this.subEmptypeId = subEmptypeId;
    }
   
    public String getBookType() {
        return this.bookType;
    }
    
    public void setBookType(String bookType) {
        this.bookType = bookType;
    }
    public String getLibraryId() {
        return this.libraryId;
    }
    
    public void setLibraryId(String libraryId) {
        this.libraryId = libraryId;
    }
    public String getEmptypeId() {
        return this.emptypeId;
    }
    
    public void setEmptypeId(String emptypeId) {
        this.emptypeId = emptypeId;
    }
    public String getSubEmptypeId() {
        return this.subEmptypeId;
    }
    
    public void setSubEmptypeId(String subEmptypeId) {
        this.subEmptypeId = subEmptypeId;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof BookCategoryId) ) return false;
		 BookCategoryId castOther = ( BookCategoryId ) other; 
         
		 return ( (this.getBookType()==castOther.getBookType()) || ( this.getBookType()!=null && castOther.getBookType()!=null && this.getBookType().equals(castOther.getBookType()) ) )
 && ( (this.getLibraryId()==castOther.getLibraryId()) || ( this.getLibraryId()!=null && castOther.getLibraryId()!=null && this.getLibraryId().equals(castOther.getLibraryId()) ) )
 && ( (this.getEmptypeId()==castOther.getEmptypeId()) || ( this.getEmptypeId()!=null && castOther.getEmptypeId()!=null && this.getEmptypeId().equals(castOther.getEmptypeId()) ) )
 && ( (this.getSubEmptypeId()==castOther.getSubEmptypeId()) || ( this.getSubEmptypeId()!=null && castOther.getSubEmptypeId()!=null && this.getSubEmptypeId().equals(castOther.getSubEmptypeId()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getBookType() == null ? 0 : this.getBookType().hashCode() );
         result = 37 * result + ( getLibraryId() == null ? 0 : this.getLibraryId().hashCode() );
         result = 37 * result + ( getEmptypeId() == null ? 0 : this.getEmptypeId().hashCode() );
         result = 37 * result + ( getSubEmptypeId() == null ? 0 : this.getSubEmptypeId().hashCode() );
         return result;
   }   


}


