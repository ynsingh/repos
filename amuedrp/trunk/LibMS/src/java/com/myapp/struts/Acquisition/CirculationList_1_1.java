/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.Acquisition;
import com.myapp.struts.hbm.AcqBibliographyDetails;
import com.myapp.struts.hbm.AcqApproval;
//import com.myapp.struts.hbm.AcqBudgetAllocation;
import java.io.Serializable;

/**
 *
 * @author System Administrator
 */
public class CirculationList_1_1 implements Serializable{
   private String title,author,currency,budgethead_name,vendor;
   private int control_no,no_of_copies,unit_price;

    /**
     * @return the title
     */
   public String getVendor()
   {
       return vendor;
   }
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }
public void setVendor(String vendor)
{
    this.vendor=vendor;
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
     * @return the currency
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * @param currency the currency to set
     */
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    /**
     * @return the primary_budget
     */
   

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
     * @return the unit_price
     */
    public int getUnit_price() {
        return unit_price;
    }

    /**
     * @param unit_price the unit_price to set
     */
    public void setUnit_price(int unit_price) {
        this.unit_price = unit_price;
    }

    /**
     * @return the budgethead_name
     */
    public String getBudgethead_name() {
        return budgethead_name;
    }

    /**
     * @param budgethead_name the budgethead_name to set
     */
    public void setBudgethead_name(String budgethead_name) {
        this.budgethead_name = budgethead_name;
    }
}
