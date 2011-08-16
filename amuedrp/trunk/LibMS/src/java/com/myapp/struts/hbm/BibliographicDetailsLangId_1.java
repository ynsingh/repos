package com.myapp.struts.hbm;
// Generated Jun 13, 2011 5:00:05 PM by Hibernate Tools 3.2.1.GA



/**
 * BibliographicDetailsLangId generated by hbm2java
 */
public class BibliographicDetailsLangId_1  implements java.io.Serializable {


     private int biblioId;
     private String libraryId;
     private String sublibraryId;

    public BibliographicDetailsLangId_1() {
    }

    public BibliographicDetailsLangId_1(int biblioId, String libraryId, String sublibraryId) {
       this.biblioId = biblioId;
       this.libraryId = libraryId;
       this.sublibraryId = sublibraryId;
    }
   
    public int getBiblioId() {
        return this.biblioId;
    }
    
    public void setBiblioId(int biblioId) {
        this.biblioId = biblioId;
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
		 if ( !(other instanceof BibliographicDetailsLangId) ) return false;
		 BibliographicDetailsLangId castOther = ( BibliographicDetailsLangId ) other; 
         
		 return (this.getBiblioId()==castOther.getBiblioId())
 && ( (this.getLibraryId()==castOther.getLibraryId()) || ( this.getLibraryId()!=null && castOther.getLibraryId()!=null && this.getLibraryId().equals(castOther.getLibraryId()) ) )
 && ( (this.getSublibraryId()==castOther.getSublibraryId()) || ( this.getSublibraryId()!=null && castOther.getSublibraryId()!=null && this.getSublibraryId().equals(castOther.getSublibraryId()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getBiblioId();
         result = 37 * result + ( getLibraryId() == null ? 0 : this.getLibraryId().hashCode() );
         result = 37 * result + ( getSublibraryId() == null ? 0 : this.getSublibraryId().hashCode() );
         return result;
   }   


}


