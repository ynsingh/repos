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
public class VendorList implements Serializable{
   int control_no;
   String price;
   String title;
   String isbn;
   int unit_price;
   int no_of_copies;
   String volume;
   String acq_mode;
    String status;
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
   
    

}
