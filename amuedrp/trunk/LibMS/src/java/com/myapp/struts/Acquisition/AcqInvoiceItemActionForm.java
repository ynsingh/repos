/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.Acquisition;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 *
 * @author edrp02
 */
public class AcqInvoiceItemActionForm extends org.apache.struts.action.ActionForm {
    
    private String receiving_no;
    private String order_no;

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public String getReceiving_no() {
        return receiving_no;
    }

    public void setReceiving_no(String receiving_no) {
        this.receiving_no = receiving_no;
    }


    private String list;
    private String list2;

    public String getList2() {
        return list2;

    }

    public void setList2(String list2) {
        this.list2 = list2;
    }
    
    public String getList() {
        return list;
    }

    public void setList(String list) {
        this.list = list;
    }


    private String invoice_no;
    private String invoicing_date;
    private String discount;
    private String net_amt;
    private String title;
    private String no_of_copies;
    private String unit_price;
    private String total_amount;
    private String sizearray;
    private String sizearrayindex;
    private String i;

    public String getI() {
        return i;
    }

    public void setI(String i) {
        this.i = i;
    }

    
    public String getSizearrayindex() {
        return sizearrayindex;
    }

    public void setSizearrayindex(String sizearrayindex) {
        this.sizearrayindex = sizearrayindex;
    }

    
    public String getSizearray() {
        return sizearray;
    }

    public void setSizearray(String sizearray) {
        this.sizearray = sizearray;
    }

    

    public String getNo_of_copies() {
        return no_of_copies;
    }

    public void setNo_of_copies(String no_of_copies) {
        this.no_of_copies = no_of_copies;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(String total_amount) {
        this.total_amount = total_amount;
    }

    public String getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(String unit_price) {
        this.unit_price = unit_price;
    }



    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getNet_amt() {
        return net_amt;
    }

    public void setNet_amt(String net_amt) {
        this.net_amt = net_amt;
    }

    
    public String getInvoice_no() {
        return invoice_no;
    }

    public void setInvoice_no(String invoice_no) {
        this.invoice_no = invoice_no;
    }

    public String getInvoicing_date() {
        return invoicing_date;
    }

    public void setInvoicing_date(String invoicing_date) {
        this.invoicing_date = invoicing_date;
    }
    

    private String prn;
    private String button;
    private String prn_date;

    public String getPrn_date() {
        return prn_date;
    }

    public void setPrn_date(String prn_date) {
        this.prn_date = prn_date;
    }


    public String getButton() {
        return button;
    }

    public void setButton(String button) {
        this.button = button;
    }

    public String getPrn() {
        return prn;
    }

    public void setPrn(String prn) {
        this.prn = prn;
    }

    private String vendor_id;

    public String getVendor_id() {
        return vendor_id;
    }

    public void setVendor_id(String vendor_id) {
        this.vendor_id = vendor_id;
    }

    

    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
       
        return null;
    }
}
