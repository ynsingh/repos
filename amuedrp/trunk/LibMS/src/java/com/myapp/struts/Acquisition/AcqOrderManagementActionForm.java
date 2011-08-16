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
 * @author maqbool
 */
public class AcqOrderManagementActionForm extends org.apache.struts.action.ActionForm {
    
    private String order_no;
    private String vendor;
    private String button;
    private String library_id;
    private String sub_library_id;
    private String order_date;
    private String discount;
    private String due_date;
    private String order_status;
    private String cancel_reason;
    private String ordered_by;
    private String price;
    private String list;
    private String vendor_id;
    private int title_id;
    private String receiving_no;
     private String recieved_date;
     private String         recieved_by;
     private String approval_type;
     private String list1,list2,list3;

    public String getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(String unit_price) {
        this.unit_price = unit_price;
    }
     private String unit_price;

    public int getApproval_item_id() {
        return approval_item_id;
    }

    public void setApproval_item_id(int approval_item_id) {
        this.approval_item_id = approval_item_id;
    }
private int approval_item_id;
    public String getApproval_type() {
        return approval_type;
    }

    public void setApproval_type(String approval_type) {
        this.approval_type = approval_type;
    }

    public String getList1() {
        return list1;
    }

    public void setList1(String list1) {
        this.list1 = list1;
    }

    public String getList2() {
        return list2;
    }

    public void setList2(String list2) {
        this.list2 = list2;
    }

    public String getList3() {
        return list3;
    }

    public void setList3(String list3) {
        this.list3 = list3;
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

    public String getReceiving_no() {
        return receiving_no;
    }

    public void setReceiving_no(String receiving_no) {
        this.receiving_no = receiving_no;
    }
    
  /**
     * @return
     */
   
    /**
     *
     */
    public AcqOrderManagementActionForm() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param request The HTTP Request we are processing.
     * @return
     */
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
       
        return null;
    }

    public String getButton() {
        return button;
    }

    public void setButton(String button) {
        this.button = button;
    }
    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

     public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

     public String getLibrary_id() {
        return library_id;
    }

    public void setLibrary_id(String library_id) {
        this.library_id = library_id;
    }

    public String getSub_library_id() {
        return sub_library_id;
    }

    public void setSub_library_id(String sub_library_id) {
        this.sub_library_id = sub_library_id;
    }
     public String getCancel_reason() {
        return cancel_reason;
    }

    public void setCancel_reason(String cancel_reason) {
        this.cancel_reason = cancel_reason;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getDue_date() {
        return due_date;
    }

    public void setDue_date(String due_date) {
        this.due_date = due_date;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }

    public String getOrdered_by() {
        return ordered_by;
    }

    public void setOrdered_by(String ordered_by) {
        this.ordered_by = ordered_by;
    }

     public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

     public String getList() {
        return list;
    }

    public void setList(String list) {
        this.list = list;
    }

    public String getVendor_id() {
        return vendor_id;
    }

    public void setVendor_id(String vendor_id) {
        this.vendor_id = vendor_id;
    }


     public int getTitle_id() {
        return title_id;
    }

    public void setTitle_id(int title_id) {
        this.title_id = title_id;
    }

    
}
