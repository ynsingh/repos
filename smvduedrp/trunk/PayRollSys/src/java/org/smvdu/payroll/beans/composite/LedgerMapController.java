/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans.composite;

/**
*  Copyright (c) 2010, 2011, 2014 SMVDU Katra.
*  Copyright (c) 2014 - 2017 ETRG, IITK.
*  All Rights Reserved.
** Redistribution and use in source and binary forms, with or 
*  without modification, are permitted provided that the following 
*  conditions are met: 
** Redistributions of source code must retain the above copyright 
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
*  Contributors: Members of ERP Team @ SMVDU, Katra, IITKanpur
*  @author Manorama Pal ( palseema30@gmail.com)
*/

import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIData;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import org.smvdu.payroll.beans.db.LedgerMapDB;
import org.smvdu.payroll.beans.setup.SalaryHead;
import org.smvdu.payroll.beans.db.SalaryHeadDB;


public class LedgerMapController implements Serializable{
    
    private ArrayList<SalaryHead> heads;
    private UIData dataGrid;
    private String coaformat=null; //default value
    
    public LedgerMapController()
    {
        //heads = new SalaryHeadDB().loadAllHeads(); 
        heads = new LedgerMapDB().loadExpLedgers_Heads(coaformat,heads);
       
    }
    
    public UIData getDataGrid() {
        return dataGrid;
    }

    public void setDataGrid(UIData dataGrid) {
        this.dataGrid = dataGrid;
    }

    public void populate()
    {
        heads = new LedgerMapDB().loadExpLedgers_Heads(coaformat,heads);
               
    }
    
    public void coaformatChanged(ValueChangeEvent e){
	//assign new value to coaFormat
        coaformat = e.getNewValue().toString();
        //dataGrid.setValue(coaformat);
               
    }            
    
    public ArrayList<SalaryHead> getHeads() {
        return heads;
    }

    public void setHeads(ArrayList<SalaryHead> heads) {
        this.heads = heads;
    }
    
    
    public String getCoaFormat(){
        return coaformat;
    }

    public void setCoaFormat(String coaformat) {
       this.coaformat = coaformat;
    }
    
   private String bgasLedger;
    public String getBgasLedger() {
        ArrayList<SalaryHead> bgasledgers = new LedgerMapDB().loadExpLedgersfromBGAS();
        SalaryHead bgasch=null;
        for(int i=0;i<bgasledgers.size();i++)
        {
             bgasch = bgasledgers.get(i);
            bgasLedger=bgasch.getLedgerName()+"-"+bgasch.getLedgerCode();
        }
        return bgasLedger;
    }
    public void setBgasLedger(String bgasLedger) {

        this.bgasLedger = bgasLedger;
    } 
   
    private SelectItem[] arrayAsItem;

    public SelectItem[] getArrayAsItem() {
        
        ArrayList<SalaryHead> bgasledgers = new LedgerMapDB().loadExpLedgersfromBGAS();
        arrayAsItem = new SelectItem[bgasledgers.size()];
        SalaryHead bledgrs= null;
        for(int i=0;i<bgasledgers.size();i++)
        {
            bledgrs = bgasledgers.get(i);
            String namecode=bledgrs.getLedgerName()+"-"+bledgrs.getLedgerCode();
            SelectItem si = new SelectItem(namecode);
            arrayAsItem[i] = si;
        }
        return arrayAsItem;
    }

    public void setArrayAsItem(SelectItem[] arrayAsItem) {
        this.arrayAsItem = arrayAsItem;
    }
        
    public void mapLedgers()
    {
        String code = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("coaformat");
        ArrayList<SalaryHead> hs = (ArrayList<SalaryHead>)dataGrid.getValue();
        boolean mlh=new LedgerMapDB().mapLedgers(hs);
        System.out.println("hs===="+hs);
        //if(mlh == false && coaformat == null){
            FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "Mapping of ledger and salary head done successfully . load the chart of account to view", ""));
        //}
        /*else{
            FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "Ledger is already mapped , use the Update button to change the Ledger by loading the chart of Account", ""));
        }*/
    }
   
            
}
