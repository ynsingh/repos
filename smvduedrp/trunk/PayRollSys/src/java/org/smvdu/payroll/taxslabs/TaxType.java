/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.smvdu.payroll.taxslabs;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.smvdu.payroll.beans.db.TaxSlabDB;

/**
 *
 * @author smvdu
 */
public class TaxType {

    /** Creates a new instance of TaxType */
    private String name;
    private String start;
    private String end;
    private double taxpercent;
    private int code;
    private String gender;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    

    public String getGender() 
    {

        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

   
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public double getTaxpercent() {
        return taxpercent;
    }

    public void setTaxpercent(double taxpercent) {
        this.taxpercent = taxpercent;
    }

    public void save()
    {
        Exception e = new TaxSlabDB().save(this);
        if (e == null)
        {
            FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "Tax Slab Saved", ""));
        } 
        else
        {
            FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
        }
    }
}
