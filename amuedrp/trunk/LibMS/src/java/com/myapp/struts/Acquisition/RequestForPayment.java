/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.Acquisition;

import java.io.Serializable;

/**
 *
 * @author edrp02
 */
public class RequestForPayment implements Serializable{

 String   prn,invoice_no,order_no,recieving_no,vendor_id,total_amt,prn_date,total_amount;

    public String getInvoice_no() {
        return invoice_no;
    }

    public void setInvoice_no(String invoice_no) {
        this.invoice_no = invoice_no;
    }

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public String getPrn() {
        return prn;
    }

    public void setPrn(String prn) {
        this.prn = prn;
    }

    public String getPrn_date() {
        return prn_date;
    }

    public void setPrn_date(String prn_date) {
        this.prn_date = prn_date;
    }

    public String getRecieving_no() {
        return recieving_no;
    }

    public void setRecieving_no(String recieving_no) {
        this.recieving_no = recieving_no;
    }

    public String getTotal_amt() {
        return total_amt;
    }

    public void setTotal_amt(String total_amt) {
        this.total_amt = total_amt;
    }

    public String getVendor_id() {
        return vendor_id;
    }

    public void setVendor_id(String vendor_id) {
        this.vendor_id = vendor_id;
    }

    public String getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(String total_amount) {
        this.total_amount = total_amount;
    }
 
    

}
