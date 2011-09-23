/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.Acquisition;
import com.myapp.struts.hbm.AcqBibliographyDetails;
import com.myapp.struts.hbm.AcqApproval;
import com.myapp.struts.hbm.AcqBudgetAllocation;
import java.io.Serializable;

/**
 *
 * @author System Administrator
 */
public class ApprovalList implements Serializable{
   int control_no;
   String price;
   String title;
   String isbn;
   int unit_price;
   int no_of_copies;
   String volume;
   String acq_mode;
    String status;
    String author;
   String recieving_no,order_no;
   String recieved_by;
   String recieved_date;
   String vendor_id;
   String invoice_no;
   String acq_budget_head_id;
   Double amount;

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getInvoice_no() {
        return invoice_no;
    }

    public void setInvoice_no(String invoice_no) {
        this.invoice_no = invoice_no;
    }

    public int getRecieved_copies() {
        return recieved_copies;
    }

    public void setRecieved_copies(int recieved_copies) {
        this.recieved_copies = recieved_copies;
    }
   private int recieved_copies;

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public String getRecieved_by() {
        return recieved_by;
    }

    public void setRecieved_by(String recieved_by) {
        this.recieved_by = recieved_by;
    }

    public String getRecieved_date() {
        return recieved_date;
    }

    public void setRecieved_date(String recieved_date) {
        this.recieved_date = recieved_date;
    }

    public String getRecieving_no() {
        return recieving_no;
    }

    public void setRecieving_no(String recieving_no) {
        this.recieving_no = recieving_no;
    }

    public String getVendor_id() {
        return vendor_id;
    }

    public void setVendor_id(String vendor_id) {
        this.vendor_id = vendor_id;
    }




    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    String conversion_rate;

    public String getConversion_rate() {
        return conversion_rate;
    }

    public void setConversion_rate(String conversion_rate) {
        this.conversion_rate = conversion_rate;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public String getAcq_mode() {
        return acq_mode;
    }

    public void setAcq_mode(String acq_mode) {
        this.acq_mode = acq_mode;
    }


    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getApproval_no() {
        return approval_no;
    }

    public void setApproval_no(String approval_no) {
        this.approval_no = approval_no;
    }
String approval_no;
    public int getNo_of_copies() {
        return no_of_copies;
    }

    public void setNo_of_copies(int no_of_copies) {
        this.no_of_copies = no_of_copies;
    }

    public int getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(int unit_price) {
        this.unit_price = unit_price;
    }

    public int getControl_no() {
        return control_no;
    }

    public void setControl_no(int control_no) {
        this.control_no = control_no;
    }



    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getAcq_budget_head_id() {
        return acq_budget_head_id;
    }

    public void setAcq_budget_head_id(String acq_budget_head_id) {
        this.acq_budget_head_id = acq_budget_head_id;
    }




}
