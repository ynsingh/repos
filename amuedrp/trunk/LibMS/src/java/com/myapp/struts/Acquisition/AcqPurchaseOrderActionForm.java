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
public class AcqPurchaseOrderActionForm extends org.apache.struts.action.ActionForm {
    
    private String order_no;
    private String order_date;
    private String library_id;
    private String sub_library_id;
    private String vendor_name;
    private String vendor_company;
    private String vendor_address;
    private String vendor_pin;
    private String vendor_fax;
    private String vendor_email;
    private String ship_contact_person1;
    private String ship_company1;
    private String ship_address1;
    private String ship_pin1;
    private String ship_fax1;
    private String ship_email1;
    private String ship_method;
    private String ship_terms;
    private String ship_delivery_date;
    private String notes;
    private String vendor_currency;


    private String button;
    
    private String discount;
    private String total;
    private String ship_cost;
    private String other_cost;
    private String tax_rate;
    private String tax_amount;
    private String grand_total;
    private String due_date;
    private String order_status;
    private String cancel_reason;
    private String ordered_by;
    private String price;
    private String list;
    private String vendor_id;
    private String delivering_date;
    private String comment;
    private int title_id;
    private String order_item_id;

    public String getShip_address1() {
        return ship_address1;
    }

    public void setShip_address1(String ship_address1) {
        this.ship_address1 = ship_address1;
    }

    public String getShip_company1() {
        return ship_company1;
    }

    public void setShip_company1(String ship_company1) {
        this.ship_company1 = ship_company1;
    }

    public String getShip_contact_person1() {
        return ship_contact_person1;
    }

    public void setShip_contact_person1(String ship_contact_person1) {
        this.ship_contact_person1 = ship_contact_person1;
    }

    public String getShip_email1() {
        return ship_email1;
    }

    public void setShip_email1(String ship_email1) {
        this.ship_email1 = ship_email1;
    }

    public String getShip_fax1() {
        return ship_fax1;
    }

    public void setShip_fax1(String ship_fax1) {
        this.ship_fax1 = ship_fax1;
    }

    public String getShip_pin1() {
        return ship_pin1;
    }

    public void setShip_pin1(String ship_pin1) {
        this.ship_pin1 = ship_pin1;
    }



    
    



    
  /**
     * @return
     */
   
    /**
     *
     */
    public AcqPurchaseOrderActionForm() {
        super();
        // TODO Auto-generated constructor stub
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

    /**
     * @return the vendor_name
     */
    public String getVendor_name() {
        return vendor_name;
    }

    /**
     * @param vendor_name the vendor_name to set
     */
    public void setVendor_name(String vendor_name) {
        this.vendor_name = vendor_name;
    }

    /**
     * @return the vendor_company
     */
    public String getVendor_company() {
        return vendor_company;
    }

    /**
     * @param vendor_company the vendor_company to set
     */
    public void setVendor_company(String vendor_company) {
        this.vendor_company = vendor_company;
    }

    /**
     * @return the vendor_address
     */
    public String getVendor_address() {
        return vendor_address;
    }

    /**
     * @param vendor_address the vendor_address to set
     */
    public void setVendor_address(String vendor_address) {
        this.vendor_address = vendor_address;
    }

    /**
     * @return the vendor_pin
     */
    public String getVendor_pin() {
        return vendor_pin;
    }

    /**
     * @param vendor_pin the vendor_pin to set
     */
    public void setVendor_pin(String vendor_pin) {
        this.vendor_pin = vendor_pin;
    }

    /**
     * @return the vendor_fax
     */
    public String getVendor_fax() {
        return vendor_fax;
    }

    /**
     * @param vendor_fax the vendor_fax to set
     */
    public void setVendor_fax(String vendor_fax) {
        this.vendor_fax = vendor_fax;
    }

    /**
     * @return the vendor_email
     */
    public String getVendor_email() {
        return vendor_email;
    }

    /**
     * @param vendor_email the vendor_email to set
     */
    public void setVendor_email(String vendor_email) {
        this.vendor_email = vendor_email;
    }

  
    /**
     * @return the ship_method
     */
    public String getShip_method() {
        return ship_method;
    }

    /**
     * @param ship_method the ship_method to set
     */
    public void setShip_method(String ship_method) {
        this.ship_method = ship_method;
    }

    /**
     * @return the ship_terms
     */
    public String getShip_terms() {
        return ship_terms;
    }

    /**
     * @param ship_terms the ship_terms to set
     */
    public void setShip_terms(String ship_terms) {
        this.ship_terms = ship_terms;
    }

    /**
     * @return the ship_delivery_date
     */
    public String getShip_delivery_date() {
        return ship_delivery_date;
    }

    /**
     * @param ship_delivery_date the ship_delivery_date to set
     */
    public void setShip_delivery_date(String ship_delivery_date) {
        this.ship_delivery_date = ship_delivery_date;
    }

    /**
     * @return the notes
     */
    public String getNotes() {
        return notes;
    }

    /**
     * @param notes the notes to set
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }


     public String getVendor_currency() {
        return vendor_currency;
    }

    public void setVendor_currency(String vendor_currency) {
        this.vendor_currency = vendor_currency;
    }
    
 public String getOther_cost() {
        return other_cost;
    }

    public void setOther_cost(String other_cost) {
        this.other_cost = other_cost;
    }

    public String getShip_cost() {
        return ship_cost;
    }

    public void setShip_cost(String ship_cost) {
        this.ship_cost = ship_cost;
    }

    public String getGrand_total() {
        return grand_total;
    }

    public void setGrand_total(String grand_total) {
        this.grand_total = grand_total;
    }

    public String getTax_amount() {
        return tax_amount;
    }

    public void setTax_amount(String tax_amount) {
        this.tax_amount = tax_amount;
    }

    public String getTax_rate() {
        return tax_rate;
    }

    public void setTax_rate(String tax_rate) {
        this.tax_rate = tax_rate;
    }
    public String getDelivering_date() {
        return delivering_date;
    }

    public void setDelivering_date(String delivering_date) {
        this.delivering_date = delivering_date;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
 public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    public String getOrder_item_id() {
        return order_item_id;
    }

    public void setOrder_item_id(String order_item_id) {
        this.order_item_id = order_item_id;
    }


}
