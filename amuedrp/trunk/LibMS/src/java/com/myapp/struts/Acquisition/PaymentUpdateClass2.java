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
public class PaymentUpdateClass2 implements Serializable{

  String  prn,total_amount,vendor_id,status,payment_update_date,no_of_invoices;

    public String getPayment_update_date() {
        return payment_update_date;
    }

    public void setPayment_update_date(String payment_update_date) {
        this.payment_update_date = payment_update_date;
    }

    public String getPrn() {
        return prn;
    }

    public void setPrn(String prn) {
        this.prn = prn;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(String total_amount) {
        this.total_amount = total_amount;
    }

    public String getVendor_id() {
        return vendor_id;
    }

    public void setVendor_id(String vendor_id) {
        this.vendor_id = vendor_id;
    }

    public String getNo_of_invoices() {
        return no_of_invoices;
    }

    public void setNo_of_invoices(String no_of_invoices) {
        this.no_of_invoices = no_of_invoices;
    }
  
    

}
