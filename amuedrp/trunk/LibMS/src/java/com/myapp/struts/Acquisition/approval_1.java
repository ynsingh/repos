/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.Acquisition;

import java.io.Serializable;



/**
 *
 * @author SONU
 */
public class approval_1 implements Serializable{
private String approval_no,vendor_id,title,author,isbn,acq_mode;

    public String getAcq_mode() {
        return acq_mode;
    }

    public void setAcq_mode(String acq_mode) {
        this.acq_mode = acq_mode;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
private int approval_item_id,control_no,no_of_copies;
    /**
     * @return the approval_no
     */
    public String getApproval_no() {
        return approval_no;
    }

    /**
     * @param approval_no the approval_no to set
     */
    public void setApproval_no(String approval_no) {
        this.approval_no = approval_no;
    }

    /**
     * @return the vendor_id
     */
    public String getVendor_id() {
        return vendor_id;
    }

    /**
     * @param vendor_id the vendor_id to set
     */
    public void setVendor_id(String vendor_id) {
        this.vendor_id = vendor_id;
    }

    /**
     * @return the approval_item_id
     */
   

    /**
     * @return the control_no
     */
     
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @param author the author to set
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * @return the approval_item_id
     */
    public int getApproval_item_id() {
        return approval_item_id;
    }

    /**
     * @param approval_item_id the approval_item_id to set
     */
    public void setApproval_item_id(int approval_item_id) {
        this.approval_item_id = approval_item_id;
    }

    /**
     * @return the control_no
     */
    public int getControl_no() {
        return control_no;
    }

    /**
     * @param control_no the control_no to set
     */
    public void setControl_no(int control_no) {
        this.control_no = control_no;
    }

    /**
     * @return the no_of_copies
     */
    public int getNo_of_copies() {
        return no_of_copies;
    }

    /**
     * @param no_of_copies the no_of_copies to set
     */
    public void setNo_of_copies(int no_of_copies) {
        this.no_of_copies = no_of_copies;
    }

    /**
     * @return the no_of_copies
     */
   
}
