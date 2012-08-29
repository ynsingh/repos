package com.myapp.struts.hbm;
// Generated Jun 12, 2012 4:17:47 PM by Hibernate Tools 3.2.1.GA



/**
 * FineDetails generated by hbm2java
 */
public class FineDetails  implements java.io.Serializable {


     private FineDetailsId id;
     
     private Double tfine;
     private Double paid;
     private Double paid1;
     private Double paid2;
     private Double paid3;
     private Double paid4;
     private Double remaining;
     private String paymod;
     private String chequeDdNo;
     private String bankname;
     private String issuedate;
     private String paydate;

    public FineDetails() {
    }

	
    public FineDetails(FineDetailsId id) {
        this.id = id;
    }
    public FineDetails(FineDetailsId id,  Double tfine, Double paid,Double paid1,Double paid2,Double paid3,Double paid4, Double remaining, String paymod, String chequeDdNo, String bankname, String issuedate, String paydate) {
       this.id = id;
       
       this.tfine = tfine;
       this.paid = paid;
       this.paid1 = paid1;
       this.paid2 = paid2;
       this.paid3 = paid3;
       this.paid4 = paid4;
       this.remaining = remaining;
       this.paymod = paymod;
       this.chequeDdNo = chequeDdNo;
       this.bankname = bankname;
       this.issuedate = issuedate;
       this.paydate = paydate;
    }
   
    public FineDetailsId getId() {
        return this.id;
    }
    
    public void setId(FineDetailsId id) {
        this.id = id;
    }
    
    public Double getTfine() {
        return this.tfine;
    }
    
    public void setTfine(Double tfine) {
        this.tfine = tfine;
    }
    public Double getPaid() {
        return this.paid;
    }
    
    public void setPaid(Double paid) {
        this.paid = paid;
    }
    public Double getRemaining() {
        return this.remaining;
    }
    
    public void setRemaining(Double remaining) {
        this.remaining = remaining;
    }
    public String getPaymod() {
        return this.paymod;
    }
    
    public void setPaymod(String paymod) {
        this.paymod = paymod;
    }
    public String getChequeDdNo() {
        return this.chequeDdNo;
    }
    
    public void setChequeDdNo(String chequeDdNo) {
        this.chequeDdNo = chequeDdNo;
    }
    public String getBankname() {
        return this.bankname;
    }
    
    public void setBankname(String bankname) {
        this.bankname = bankname;
    }
    public String getIssuedate() {
        return this.issuedate;
    }
    
    public void setIssuedate(String issuedate) {
        this.issuedate = issuedate;
    }
    public String getPaydate() {
        return this.paydate;
    }
    
    public void setPaydate(String paydate) {
        this.paydate = paydate;
    }

    public Double getPaid1() {
        return paid1;
    }

    public void setPaid1(Double paid1) {
        this.paid1 = paid1;
    }

    public Double getPaid2() {
        return paid2;
    }

    public void setPaid2(Double paid2) {
        this.paid2 = paid2;
    }

    public Double getPaid3() {
        return paid3;
    }

    public void setPaid3(Double paid3) {
        this.paid3 = paid3;
    }

    public Double getPaid4() {
        return paid4;
    }

    public void setPaid4(Double paid4) {
        this.paid4 = paid4;
    }




}

