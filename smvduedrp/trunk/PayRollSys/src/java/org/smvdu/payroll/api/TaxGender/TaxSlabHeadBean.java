/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.api.TaxGender;


/**
 *  Copyright (c) 2010 - 2011,2015 SMVDU, Katra, IITKanpur.
 *  All Rights Reserved.
 *  Redistribution and use in source and binary forms, with or
 *  without modification, are permitted provided that the following
 *  conditions are met:
 *  Redistributions of source code must retain the above copyright
 *  notice, this  list of conditions and the following disclaimer.
 *
 *  Redistribution in binary form must reproduce the above copyright
 *  notice, this list of conditions and the following disclaimer in
 *  the documentation and/or other materials provided with the
 *  distribution.
 *
 *
 *  THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 *  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 *  OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 *  DISCLAIMED.  IN NO EVENT SHALL SMVDU OR ITS CONTRIBUTORS BE LIABLE
 *  FOR ANY DIRECT, INDIRECT, INCIDENTAL,SPECIAL, EXEMPLARY, OR
 *  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT
 *  OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR
 *  BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 *  WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 *  OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 *  EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 *
 *  Contributors: Members of ERP Team @ SMVDU, Katra, IITKanpur
 *  Modified Date: 30 April 2015, IITK (palseema30@gmail.com)
 *  @author <a href="palseema30@gmail.com"> Manorama Pal </a>
 */

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIData;
import javax.faces.context.FacesContext;
import org.smvdu.payroll.beans.SessionMaster;

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
    
    
     @Override
    public boolean equals(Object obj)
    {
        TaxSlabHeadBean tshb = (TaxSlabHeadBean)obj;
        if(this.slabHeadCode==tshb.slabHeadCode)
        {
            return true;
        }
        else
        {
            return false;
        }
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
