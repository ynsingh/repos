package com.myapp.struts.hbm;
// Generated Jan 21, 2011 8:43:24 PM by Hibernate Tools 3.2.1.GA



/**
 * AcqFinalDemandList generated by hbm2java
 */
public class AcqFinalDemandList  implements java.io.Serializable {


     private AcqFinalDemandListId id;
     private Library library;
     private String listId;
     private String isbn;
     private String title;
     private String subtitle;
     private String author;
     private String price;
     private String totalAmount;
     private String volume;
     private String edition;
     private String publisherId;
     private String bindId;

    public AcqFinalDemandList() {
    }

	
    public AcqFinalDemandList(AcqFinalDemandListId id, Library library) {
        this.id = id;
        this.library = library;
    }
    public AcqFinalDemandList(AcqFinalDemandListId id, Library library, String listId, String isbn, String title, String subtitle, String author, String price, String totalAmount, String volume, String edition, String publisherId, String bindId) {
       this.id = id;
       this.library = library;
       this.listId = listId;
       this.isbn = isbn;
       this.title = title;
       this.subtitle = subtitle;
       this.author = author;
       this.price = price;
       this.totalAmount = totalAmount;
       this.volume = volume;
       this.edition = edition;
       this.publisherId = publisherId;
       this.bindId = bindId;
    }
   
    public AcqFinalDemandListId getId() {
        return this.id;
    }
    
    public void setId(AcqFinalDemandListId id) {
        this.id = id;
    }
    public Library getLibrary() {
        return this.library;
    }
    
    public void setLibrary(Library library) {
        this.library = library;
    }
    public String getListId() {
        return this.listId;
    }
    
    public void setListId(String listId) {
        this.listId = listId;
    }
    public String getIsbn() {
        return this.isbn;
    }
    
    public void setIsbn(String isbn) {
        this.isbn = isbn;
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
    public String getAuthor() {
        return this.author;
    }
    
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getPrice() {
        return this.price;
    }
    
    public void setPrice(String price) {
        this.price = price;
    }
    public String getTotalAmount() {
        return this.totalAmount;
    }
    
    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }
    public String getVolume() {
        return this.volume;
    }
    
    public void setVolume(String volume) {
        this.volume = volume;
    }
    public String getEdition() {
        return this.edition;
    }
    
    public void setEdition(String edition) {
        this.edition = edition;
    }
    public String getPublisherId() {
        return this.publisherId;
    }
    
    public void setPublisherId(String publisherId) {
        this.publisherId = publisherId;
    }
    public String getBindId() {
        return this.bindId;
    }
    
    public void setBindId(String bindId) {
        this.bindId = bindId;
    }




}


