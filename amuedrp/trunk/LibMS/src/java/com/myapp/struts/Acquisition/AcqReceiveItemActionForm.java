/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.Acquisition;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author edrp02
 */
public class AcqReceiveItemActionForm extends org.apache.struts.action.ActionForm {
    
    private String sname;
    private String order_no;
    private String checkbox;
    private String button;

    public String getButton() {
        return button;
    }

    public void setButton(String button) {
        this.button = button;
    }

    
    public String getCheckbox() {
        return checkbox;
    }

    public void setCheckbox(String checkbox) {
        this.checkbox = checkbox;
    }

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }




    private String list;

    public String getList() {
        return list;
    }

    public void setList(String list) {
        this.list = list;
    }



    private String recieving_no;
    private String recieved_date;
    private String recieved_by;
    private String no_of_copies;
    private String recieved_copies;
    private String recieved_now;
    private String pending_copies;
    private String control_no;
    private String title_id;
    private String unit_price;
    private String acq_mode;
    private String vendor_id1;
    private String order_no1;

    public String getOrder_no1() {
        return order_no1;
    }

    public void setOrder_no1(String order_no1) {
        this.order_no1 = order_no1;
    }

    

    public String getAcq_mode() {
        return acq_mode;
    }

    public void setAcq_mode(String acq_mode) {
        this.acq_mode = acq_mode;
    }

    public String getControl_no() {
        return control_no;
    }

    public void setControl_no(String control_no) {
        this.control_no = control_no;
    }

    public String getNo_of_copies() {
        return no_of_copies;
    }

    public void setNo_of_copies(String no_of_copies) {
        this.no_of_copies = no_of_copies;
    }

    public String getPending_copies() {
        return pending_copies;
    }

    public void setPending_copies(String pending_copies) {
        this.pending_copies = pending_copies;
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

    public String getRecieved_date() {
        return recieved_date;
    }

    public void setRecieved_date(String recieved_date) {
        this.recieved_date = recieved_date;
    }

    public String getRecieved_now() {
        return recieved_now;
    }

    public void setRecieved_now(String recieved_now) {
        this.recieved_now = recieved_now;
    }

    public String getRecieving_no() {
        return recieving_no;
    }

    public void setRecieving_no(String recieving_no) {
        this.recieving_no = recieving_no;
    }

    public String getTitle_id() {
        return title_id;
    }

    public void setTitle_id(String title_id) {
        this.title_id = title_id;
    }

    public String getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(String unit_price) {
        this.unit_price = unit_price;
    }

    public String getVendor_id1() {
        return vendor_id1;
    }

    public void setVendor_id1(String vendor_id1) {
        this.vendor_id1 = vendor_id1;
    }

    
    



    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
       
        return null;
    }
}
