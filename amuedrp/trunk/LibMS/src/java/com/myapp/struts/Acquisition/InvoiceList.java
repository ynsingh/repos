/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.Acquisition;

import java.io.Serializable;


 
public class InvoiceList implements Serializable{

    String order_no,recieved_copies,unit_price,recieving_no,vendor_id,recieved_by,status,title,pending_copies;
    int control_no;

    public int getControl_no() {
        return control_no;
    }

    public void setControl_no(int control_no) {
        this.control_no = control_no;
    }

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

    public String getRecieved_copies() {
        return recieved_copies;
    }

    public void setRecieved_copies(String recieved_copies) {
        this.recieved_copies = recieved_copies;
    }

    public String getRecieving_no() {
        return recieving_no;
    }

    public void setRecieving_no(String recieving_no) {
        this.recieving_no = recieving_no;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(String unit_price) {
        this.unit_price = unit_price;
    }

    public String getVendor_id() {
        return vendor_id;
    }

    public void setVendor_id(String vendor_id) {
        this.vendor_id = vendor_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPending_copies() {
        return pending_copies;
    }

    public void setPending_copies(String pending_copies) {
        this.pending_copies = pending_copies;
    }

    

}
