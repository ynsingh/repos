package com.myapp.struts.hbm;
// Generated May 24, 2012 5:54:59 PM by Hibernate Tools 3.2.1.GA



/**
 * AcqInvoiceHeaderId generated by hbm2java
 */
public class AcqInvoiceHeaderId  implements java.io.Serializable {


     private String libraryId;
     private String sublibraryId;
     private String invoiceNo;
     private String orderNo;
     private String vendorId;

    public AcqInvoiceHeaderId() {
    }

    public AcqInvoiceHeaderId(String libraryId, String sublibraryId, String invoiceNo, String orderNo, String vendorId) {
       this.libraryId = libraryId;
       this.sublibraryId = sublibraryId;
       this.invoiceNo = invoiceNo;
       this.orderNo = orderNo;
       this.vendorId = vendorId;
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
    public String getInvoiceNo() {
        return this.invoiceNo;
    }
    
    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }
    public String getOrderNo() {
        return this.orderNo;
    }
    
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
    public String getVendorId() {
        return this.vendorId;
    }
    
    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof AcqInvoiceHeaderId) ) return false;
		 AcqInvoiceHeaderId castOther = ( AcqInvoiceHeaderId ) other; 
         
		 return ( (this.getLibraryId()==castOther.getLibraryId()) || ( this.getLibraryId()!=null && castOther.getLibraryId()!=null && this.getLibraryId().equals(castOther.getLibraryId()) ) )
 && ( (this.getSublibraryId()==castOther.getSublibraryId()) || ( this.getSublibraryId()!=null && castOther.getSublibraryId()!=null && this.getSublibraryId().equals(castOther.getSublibraryId()) ) )
 && ( (this.getInvoiceNo()==castOther.getInvoiceNo()) || ( this.getInvoiceNo()!=null && castOther.getInvoiceNo()!=null && this.getInvoiceNo().equals(castOther.getInvoiceNo()) ) )
 && ( (this.getOrderNo()==castOther.getOrderNo()) || ( this.getOrderNo()!=null && castOther.getOrderNo()!=null && this.getOrderNo().equals(castOther.getOrderNo()) ) )
 && ( (this.getVendorId()==castOther.getVendorId()) || ( this.getVendorId()!=null && castOther.getVendorId()!=null && this.getVendorId().equals(castOther.getVendorId()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getLibraryId() == null ? 0 : this.getLibraryId().hashCode() );
         result = 37 * result + ( getSublibraryId() == null ? 0 : this.getSublibraryId().hashCode() );
         result = 37 * result + ( getInvoiceNo() == null ? 0 : this.getInvoiceNo().hashCode() );
         result = 37 * result + ( getOrderNo() == null ? 0 : this.getOrderNo().hashCode() );
         result = 37 * result + ( getVendorId() == null ? 0 : this.getVendorId().hashCode() );
         return result;
   }   


}


