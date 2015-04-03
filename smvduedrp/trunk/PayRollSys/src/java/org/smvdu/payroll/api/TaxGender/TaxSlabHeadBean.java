/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.api.TaxGender;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.component.UIData;
import javax.faces.context.FacesContext;
import org.smvdu.payroll.beans.SessionMaster;

@ManagedBean
@RequestScoped
/**
 *
 * @author ERP
 */
public class TaxSlabHeadBean implements Serializable {

    /** Creates a new instance of TaxSlabHeadBean */
    public TaxSlabHeadBean() {
    }
    
    private int slabHeadCode;
    private SessionMaster fyear;
    private String slabName = new String();
    private int startSlabValue ;
    private int endSlabValue;
    private float percent;
  
    private boolean select;
   // private int fyear;
    

    
    
    private Set empSlabCode = new HashSet();

    public Set getEmpSlabCode() {
        return empSlabCode;
    }

    public void setEmpSlabCode(Set empSlabCode) {
        this.empSlabCode = empSlabCode;
    }

    private int fyearDropDown;
    
    public int getFyearDropDown() {
        return fyearDropDown;
    }

    public void setFyearDropDown(int fyearDropDown) {
        this.fyearDropDown = fyearDropDown;
    }
    
    private int session;
    private float surcharge;
    private float eduCess;
    private float heduCess;
    private int orgcode;

 /*   public TaxSlabHeadBean(int SlabHeadCode, String SlabName){
        this.slabHeadCode = SlabHeadCode;
        this.slabName = SlabName;
    }   */
        
    private UIData grid;
    private ArrayList<TaxSlabHeadBean> taxHeadValue = new ArrayList<TaxSlabHeadBean>();

    public UIData getGrid() {
        return grid;
    }

    public void populate()
    {
        taxHeadValue = new TaxSlabHeadDB().loadSelectedSlab(session);
        grid.setValue(taxHeadValue);
    }
    
    public void setGrid(UIData grid) {
        this.grid = grid;
    }
    
    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }
    
    public int getEndSlabValue() {
        return endSlabValue;
    }

    public void setEndSlabValue(int endSlabValue) {
        this.endSlabValue = endSlabValue;
    }

    public float getPercent() {
        return percent;
    }

    public void setPercent(float percent) {
        this.percent = percent;
    }

    public int getSlabHeadCode() {
        return slabHeadCode;
    }

    public void setSlabHeadCode(int slabHeadCode) {
        this.slabHeadCode = slabHeadCode;
    }

    public String getSlabName() {
        return slabName;
    }

    public void setSlabName(String slabName) {
        this.slabName = slabName;
    }

    public int getStartSlabValue() {
        return startSlabValue;
    }

    public void setStartSlabValue(int startSlabValue) {
        this.startSlabValue = startSlabValue;
    }
    
 /*   public int getFyear() {
        return fyear;
    }
    
    public void setFyear(int fyear) {
        this.fyear = fyear;
    }   */
    
    public SessionMaster getFyear() {
        return fyear;
    }

    public void setFyear(SessionMaster fyear) {
        this.fyear = fyear;
    }
    
    public ArrayList<TaxSlabHeadBean> getTaxHeadValue() {
     //  taxHeadValue = new TaxSlabHeadConttroler().loadSlabHead();
        return taxHeadValue;
    }

    public void setTaxHeadValue(ArrayList<TaxSlabHeadBean> taxHeadValue) {
        this.taxHeadValue = taxHeadValue;
    }
       
    public int getSession() {
        return session;
    }

    public void setSession(int session) {
        this.session = session;
    //  taxHeadValue = new TaxSlabHeadDB().loadSelectedSlab(session);
    }
    
    public float getSurcharge() {
        return surcharge;
    }

    public void setSurcharge(float surcharge) {
        this.surcharge = surcharge;
    }
    
    public float getEduCess() {
        return eduCess;
    }

    public void setEduCess(float educess) {
        this.eduCess = educess;
    }
    
    public float getHeduCess() {
        return heduCess;
    }

    public void setHeduCess(float heducess) {
        this.heduCess = heducess;
    }
    
    public int getOrgcode() {
        return orgcode;
    }

    public void setOrgcode(int orgcode) {
        this.orgcode = orgcode;
    }
    
    public void saveSlabHead()
    {
        try
        {
            FacesContext fc = FacesContext.getCurrentInstance();
            
            if (this.getSlabName().matches("^[a-zA-Z0-9\\s]*$") == false) {
                FacesMessage message = new FacesMessage();
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                message.setSummary("Please Enter Valid Slab Name. No Special Characters are Allowed");
                fc.addMessage("", message);
                return;
            }
            
            Exception ex = new TaxSlabHeadDB().saveSlabHead(this);
            if(ex == null)
            {
                FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "Slab Name : "+ this.getSlabName()+" Added", ""));
            }
            else
            {
                FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR," "+this.getSlabName()+" already exist", ""));
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

}
