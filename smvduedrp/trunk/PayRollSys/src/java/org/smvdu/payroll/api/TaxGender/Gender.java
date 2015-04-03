/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.api.TaxGender;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;


@ManagedBean
@RequestScoped
/**
 *
 * @author ERP
 */
public class Gender {

    /** Creates a new instance of Gender */
    
    private Set empSlabGender = new HashSet();

    public Set getEmpSlabGender() {
        return empSlabGender;
    }

    public void setEmpSlabGender(Set empSlabGender) {
        this.empSlabGender = empSlabGender;
    }
    
    private int orgcode;

    public int getOrgcode() {
        return orgcode;
    }

    public void setOrgcode(int orgcode) {
        this.orgcode = orgcode;
    }
    
    public Gender() {
    }


    private String genderName = new String();
    private int genderCode;
    private ArrayList<Gender> loadGender = new ArrayList<Gender>();

    public ArrayList<Gender> getLoadGender() {
        loadGender = new GenderDB().loadGenderDetail();
        return loadGender;
    }

    public void setLoadGender(ArrayList<Gender> loadGender) {
        this.loadGender = loadGender;
    }
    public int getGenderCode() {
        return genderCode;
    }

    public void setGenderCode(int genderCode) {
        this.genderCode = genderCode;
    }

    public String getGenderName() {
        return genderName;
    }

    public void setGenderName(String genderName) {
        this.genderName = genderName;
    }
    public void save()
    {
        FacesContext fc = FacesContext.getCurrentInstance();
        
        if (this.getGenderName().matches("^[a-zA-Z\\s]*$") == false) {
                FacesMessage message = new FacesMessage();
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                message.setSummary("Please Enter Valid Gender Name. Only Alphabets are Allowed");
                fc.addMessage("", message);
                return;
            }
        
        boolean b = new GenderDB().saveGender(this);
        if(b == true)
        {
            FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "Gender Name : "+ this.getGenderName()+" Added", ""));
        }
        else
        {
            FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Gender Name already Exist : "+getGenderName(), ""));
        }
    }

}
