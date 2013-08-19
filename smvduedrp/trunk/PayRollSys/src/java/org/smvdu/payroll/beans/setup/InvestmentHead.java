/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans.setup;

import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIData;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.smvdu.payroll.beans.BaseBean;
import org.smvdu.payroll.beans.TempDB;

import org.smvdu.payroll.beans.db.InvestmentHeadDB;
import org.smvdu.payroll.beans.db.InvestmentTypeDB;

/**
 *
 *  *  Copyright (c) 2010 - 2011 SMVDU, Katra.
*  All Rights Reserved.
**  Redistribution and use in source and binary forms, with or 
*  without modification, are permitted provided that the following 
*  conditions are met: 
**  Redistributions of source code must retain the above copyright 
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
*  Contributors: Members of ERP Team @ SMVDU, Katra
*
 */
public class InvestmentHead extends BaseBean implements Serializable{


    


    private String  selectionLength = "Selection Length : ";
    private SelectItem[] selectedItems;
    private UIData dataGrid;
    public UIData getDataGrid() {

        return dataGrid;
    }

    public void setDataGrid(UIData dataGrid) {
        this.dataGrid = dataGrid;
    }

    public SelectItem[] getSelectedItems() {
        ArrayList<InvestmentHead> deps = new InvestmentHeadDB().loadHeads();
        selectedItems =new SelectItem[deps.size()];
        InvestmentHead dep = null;
        for(int i=0;i<deps.size();i++)
        {
            dep = deps.get(i);
            SelectItem si = new SelectItem(dep.getCode(), dep.getName());
            selectedItems[i] = si;
        }
        return selectedItems;
    }

    public String getSelectionLength() {
        return selectionLength;
    }

    public void setSelectionLength(String selectionLength) {
        //this.selectionLength = selectionLength;
    }

    public String print()   {
        int[] da = new int[selectedItems.length];
        for(int i=0;i<selectedItems.length;i++)
        {
            SelectItem si = selectedItems[i];
            da[i] = (Integer)si.getValue();
            System.out.println("Value : "+si.getValue());
        }
        new TempDB().save(da);
        return "case1";
        //this.selectedItems = selectedItems;
    }

    public void setSelectedItems(SelectItem[] selectedItems) {
        int[] da = new int[selectedItems.length];
        for(int i=0;i<selectedItems.length;i++)
        {
            SelectItem si = selectedItems[i];
            da[i] = (Integer)si.getValue();
            System.out.println("Value : "+si.getValue());
        }
        new TempDB().save(da);
        this.selectedItems = selectedItems;

        selectionLength+= this.selectedItems.length;
    }



    private SelectItem[] allDepts;
   
    private String underGroupName;
    private int underGroupCode;

    public int getUnderGroupCode() {
        return underGroupCode;
    }

    public void setUnderGroupCode(int underGroupCode) {
        this.underGroupCode = underGroupCode;
    }

    public String getUnderGroupName() {
        return underGroupName;
    }

    public void setUnderGroupName(String underGroupName) {
        this.underGroupName = underGroupName;
    }
    


    public void setAllDepts(SelectItem[] allDepts) {
        this.allDepts = allDepts;
    }

    public void setAllheads(ArrayList<InvestmentHead> departments) {
        this.allHeads = departments;
    }

    public ArrayList<InvestmentHead> getAllHeads() {

        allHeads= new InvestmentHeadDB().loadHeads();
        dataGrid.setValue(allHeads);
        return allHeads;
    }
   

    private ArrayList<InvestmentHead> allHeads;

   private boolean benefit;

    private String details;

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
    

    public boolean isBenefit() {
        return benefit;
    }

    public void setBenefit(boolean benefit) {
        this.benefit = benefit;
    }
    
    
    public ArrayList<InvestmentHead> getHeads() {
        ArrayList<InvestmentHead> deps = new InvestmentHeadDB().loadHeads();

        return deps;
    }
    
    


public SelectItem[] getAllHeadsAsOption() {


        ArrayList<InvestmentHead> deps = new InvestmentHeadDB().loadHeads();
        allDepts =new SelectItem[deps.size()];

        InvestmentHead dep = null;
        for(int i=0;i<deps.size();i++)
        {
            dep = deps.get(i);
            SelectItem si = new SelectItem(dep.getCode(), dep.getName());            
            allDepts[i] = si;
        }
        return allDepts;
    }

    @Override
    public String toString()
    {
        return getName();
    }

    public void setAllDepts(ArrayList<InvestmentHead> allDepts) {
        //this.allDepts = allDepts;
    }

    public void update()
    {
     ArrayList<InvestmentHead> data = (ArrayList<InvestmentHead>)dataGrid.getValue();
        for(InvestmentHead it : data)
        {

            System.out.println(it.getCode()+","+it.getName());
        }
        Exception e = new InvestmentHeadDB().update(data);
        if(e==null)
        {
            FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "Updated", ""));
        }
        else
        {
            FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
        }

 } 

    public void save()
    {
        Exception e = new InvestmentHeadDB().save(this);
    }
    public InvestmentHead() {
       
    }

}
