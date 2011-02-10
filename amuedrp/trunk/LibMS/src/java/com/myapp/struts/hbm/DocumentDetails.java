package com.myapp.struts.hbm;
// Generated Jan 21, 2011 8:43:24 PM by Hibernate Tools 3.2.1.GA



/**
 * DocumentDetails generated by hbm2java
 */
public class DocumentDetails  implements java.io.Serializable {


     private DocumentDetailsId id;
     private Library library;
     private Integer biblioId;
     private String serialId;
     private String documentType;
     private String title;
     private String subtitle;
     private String authorMain;
     private String authorSub;
     private String publisherName;
     private String publicationPlace;
     private int publishingYear;
     private String callNo;
     private String isbn10;
     private String issn;
     private String isbn13;
     private String edition;
     private String indexNo;
     private String noOfPages;
     private String physicalWidth;
     private String noOfCopy;
     private String notes;
     private String subject;
     private String editor;
     private String volumeNo;

    public DocumentDetails() {
    }

	
    public DocumentDetails(DocumentDetailsId id, Library library) {
        this.id = id;
        this.library = library;
    }
    public DocumentDetails(DocumentDetailsId id, Library library, Integer biblioId, String serialId, String documentType, String title, String subtitle, String authorMain, String authorSub, String publisherName, String publicationPlace, int publishingYear, String callNo, String isbn10, String issn, String isbn13, String edition, String indexNo, String noOfPages, String physicalWidth, String noOfCopy, String notes, String subject, String editor, String volumeNo) {
       this.id = id;
       this.library = library;
       this.biblioId = biblioId;
       this.serialId = serialId;
       this.documentType = documentType;
       this.title = title;
       this.subtitle = subtitle;
       this.authorMain = authorMain;
       this.authorSub = authorSub;
       this.publisherName = publisherName;
       this.publicationPlace = publicationPlace;
       this.publishingYear = publishingYear;
       this.callNo = callNo;
       this.isbn10 = isbn10;
       this.issn = issn;
       this.isbn13 = isbn13;
       this.edition = edition;
       this.indexNo = indexNo;
       this.noOfPages = noOfPages;
       this.physicalWidth = physicalWidth;
       this.noOfCopy = noOfCopy;
       this.notes = notes;
       this.subject = subject;
       this.editor = editor;
       this.volumeNo = volumeNo;
    }
   
    public DocumentDetailsId getId() {
        return this.id;
    }
    
    public void setId(DocumentDetailsId id) {
        this.id = id;
    }
    public Library getLibrary() {
        return this.library;
    }
    
    public void setLibrary(Library library) {
        this.library = library;
    }
    public Integer getBiblioId() {
        return this.biblioId;
    }
    
    public void setBiblioId(Integer biblioId) {
        this.biblioId = biblioId;
    }
    public String getSerialId() {
        return this.serialId;
    }
    
    public void setSerialId(String serialId) {
        this.serialId = serialId;
    }
    public String getDocumentType() {
        return this.documentType;
    }
    
    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }
    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    public String getSubtitle() {
        return this.subtitle;
    }
    
    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }
    public String getAuthorMain() {
        return this.authorMain;
    }
    
    public void setAuthorMain(String authorMain) {
        this.authorMain = authorMain;
    }
    public String getAuthorSub() {
        return this.authorSub;
    }
    
    public void setAuthorSub(String authorSub) {
        this.authorSub = authorSub;
    }
    public String getPublisherName() {
        return this.publisherName;
    }
    
    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }
    public String getPublicationPlace() {
        return this.publicationPlace;
    }
    
    public void setPublicationPlace(String publicationPlace) {
        this.publicationPlace = publicationPlace;
    }
    public int getPublishingYear() {
        return this.publishingYear;
    }
    
    public void setPublishingYear(int publishingYear) {
        this.publishingYear = publishingYear;
    }
    public String getCallNo() {
        return this.callNo;
    }
    
    public void setCallNo(String callNo) {
        this.callNo = callNo;
    }
    public String getIsbn10() {
        return this.isbn10;
    }
    
    public void setIsbn10(String isbn10) {
        this.isbn10 = isbn10;
    }
    public String getIssn() {
        return this.issn;
    }
    
    public void setIssn(String issn) {
        this.issn = issn;
    }
    public String getIsbn13() {
        return this.isbn13;
    }
    
    public void setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
    }
    public String getEdition() {
        return this.edition;
    }
    
    public void setEdition(String edition) {
        this.edition = edition;
    }
    public String getIndexNo() {
        return this.indexNo;
    }
    
    public void setIndexNo(String indexNo) {
        this.indexNo = indexNo;
    }
    public String getNoOfPages() {
        return this.noOfPages;
    }
    
    public void setNoOfPages(String noOfPages) {
        this.noOfPages = noOfPages;
    }
    public String getPhysicalWidth() {
        return this.physicalWidth;
    }
    
    public void setPhysicalWidth(String physicalWidth) {
        this.physicalWidth = physicalWidth;
    }
    public String getNoOfCopy() {
        return this.noOfCopy;
    }
    
    public void setNoOfCopy(String noOfCopy) {
        this.noOfCopy = noOfCopy;
    }
    public String getNotes() {
        return this.notes;
    }
    
    public void setNotes(String notes) {
        this.notes = notes;
    }
    public String getSubject() {
        return this.subject;
    }
    
    public void setSubject(String subject) {
        this.subject = subject;
    }
    public String getEditor() {
        return this.editor;
    }
    
    public void setEditor(String editor) {
        this.editor = editor;
    }
    public String getVolumeNo() {
        return this.volumeNo;
    }
    
    public void setVolumeNo(String volumeNo) {
        this.volumeNo = volumeNo;
    }




}


