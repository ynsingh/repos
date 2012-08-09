package com.myapp.struts.hbm;
// Generated May 24, 2012 5:54:59 PM by Hibernate Tools 3.2.1.GA



/**
 * AcqInvoiceHeader generated by hbm2java
 */
public class AcqInvoiceHeader  implements java.io.Serializable {


     private AcqInvoiceHeaderId id;
     private String date;
     private String recievedBy;
//     private String totalAmount;
//     private String discount;
//     private String netTotal;
     private String invoiceDate;
     private String overallDiscount;
     private String totalNetAmount;
     private String miscCharges;
     private String grandTotal;

     private String status;

    public AcqInvoiceHeader() {
    }

	
    public AcqInvoiceHeader(AcqInvoiceHeaderId id) {
        this.id = id;
    }
    public AcqInvoiceHeader(AcqInvoiceHeaderId id, String date, String recievedBy, String overallDiscount, String totalNetAmount, String miscCharges,String grandTotal ,String status,String invoiceDate) {
       this.id = id;
       this.date = date;
       this.recievedBy = recievedBy;
//       this.totalAmount = totalAmount;
//       this.discount = discount;
//       this.netTotal = netTotal;

       this.overallDiscount=overallDiscount;
       this.totalNetAmount=totalNetAmount;
       this.miscCharges=miscCharges;
       this.grandTotal=grandTotal;
       this.status = status;
       this.invoiceDate = invoiceDate;
    }
   
    public AcqInvoiceHeaderId getId() {
        return this.id;
    }
    
    public void setId(AcqInvoiceHeaderId id) {
        this.id = id;
    }
    public String getDate() {
        return this.date;
    }
    
    public void setDate(String date) {
        this.date = date;
    }
    public String getRecievedBy() {
        return this.recievedBy;
    }
    
    public void setRecievedBy(String recievedBy) {
        this.recievedBy = recievedBy;
    }

    public String getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(String grandTotal) {
        this.grandTotal = grandTotal;
    }

    public String getMiscCharges() {
        return miscCharges;
    }

    public void setMiscCharges(String miscCharges) {
        this.miscCharges = miscCharges;
    }

    public String getOverallDiscount() {
        return overallDiscount;
    }

    public void setOverallDiscount(String overallDiscount) {
        this.overallDiscount = overallDiscount;
    }

    public String getTotalNetAmount() {
        return totalNetAmount;
    }

    public void setTotalNetAmount(String totalNetAmount) {
        this.totalNetAmount = totalNetAmount;
    }

    

//    public String getTotalAmount() {
//        return this.totalAmount;
//    }
//
//    public void setTotalAmount(String totalAmount) {
//        this.totalAmount = totalAmount;
//    }
//    public String getDiscount() {
//        return this.discount;
//    }
//
//    public void setDiscount(String discount) {
//        this.discount = discount;
//    }
//    public String getNetTotal() {
//        return this.netTotal;
//    }
//
//    public void setNetTotal(String netTotal) {
//        this.netTotal = netTotal;
//    }
    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    


}


