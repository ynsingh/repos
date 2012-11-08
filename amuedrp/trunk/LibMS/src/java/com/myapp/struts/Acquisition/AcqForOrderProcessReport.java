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
public class AcqForOrderProcessReport implements Serializable{
 String title,recieving_no,vendor_id,order_no;
 Integer control_no,recieved_copies,pending_copies;

    public Integer getControl_no() {
        return control_no;
    }

    public void setControl_no(Integer control_no) {
        this.control_no = control_no;
    }

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public Integer getPending_copies() {
        return pending_copies;
    }

    public void setPending_copies(Integer pending_copies) {
        this.pending_copies = pending_copies;
    }

    public Integer getRecieved_copies() {
        return recieved_copies;
    }

    public void setRecieved_copies(Integer recieved_copies) {
        this.recieved_copies = recieved_copies;
    }

    public String getRecieving_no() {
        return recieving_no;
    }

    public void setRecieving_no(String recieving_no) {
        this.recieving_no = recieving_no;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVendor_id() {
        return vendor_id;
    }

    public void setVendor_id(String vendor_id) {
        this.vendor_id = vendor_id;
    }

 
}
