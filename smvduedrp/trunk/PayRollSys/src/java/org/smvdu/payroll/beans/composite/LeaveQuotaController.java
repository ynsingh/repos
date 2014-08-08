/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans.composite;


import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIData;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.smvdu.payroll.beans.ext.attendance.LeaveQuota;
import org.smvdu.payroll.beans.ext.attendance.db.LeaveQuotaDB;

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
*  Contributors: Members of ERP Team @ SMVDU, Katra, IITKanpur
* Modified Date: 7 AUG 2014, IITK (palseema30@gmail.com, kishore.shuklak@gmail.com)
*/
public class LeaveQuotaController  {

    private ArrayList<LeaveQuota> quotas;
    private SelectItem[] itemAsArray;
    private UIData dataGrid;
    private int empType;
    private int balancecount;
    private String code;

    public SelectItem[] getItemAsArray() {
        //quotas = new LeaveQuotaDB().getQuota(empType);
        quotas = new LeaveQuotaDB().getAllSelected(empType);
        itemAsArray = new SelectItem[quotas.size()];
        LeaveQuota lq = null;
        for(int i=0;i<quotas.size();i++)
        {
           lq = quotas.get(i);
           SelectItem si = new SelectItem(lq.getLeaveType(), lq.getLeaveTypeName());
           itemAsArray[i] = si;
        }
        return itemAsArray;
    }

    public void setItemAsArray(SelectItem[] itemAsArray) {
        this.itemAsArray = itemAsArray;
    }

    

    public int getEmpType() {
        return empType;
    }

    public void setEmpType(int empType) {
        this.empType = empType;
    }
    

     public void populate()
    {
        getQuotas();
    }


    public void update()
    {
        ArrayList<LeaveQuota> datacopy = new ArrayList<LeaveQuota>();
        ArrayList<LeaveQuota>tabledata= new LeaveQuotaDB().getQuota(empType);
        ArrayList<LeaveQuota> data = (ArrayList<LeaveQuota>)dataGrid.getValue();
        //System.out.println("array list=in controller=="+data);
        for(LeaveQuota lq:data)
        {
            if(lq.isSelected())
                {
                    datacopy.add(lq);
                    //System.out.println("Name in controller: "+lq.getLeaveTypeName()+" Value : "+lq.getCount());
                } 
            
        }
        new LeaveQuotaDB().update(datacopy, empType);
        FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "Selection Updated", ""));
    }

    public UIData getDataGrid() {
        return dataGrid;
    }

    public void setDataGrid(UIData dataGrid) {
        this.dataGrid = dataGrid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    
    public ArrayList<LeaveQuota> getQuotas() {
        //quotas = new LeaveQuotaDB().getQuota(empType);
        if(empType==0){
            quotas = new LeaveQuotaDB().getAllSelected(empType);
        }
        else{
            quotas = new LeaveQuotaDB().loadAllData(empType);
        }
        //System.out.println("selected====="+empType);
        //System.out.println("selected==1234==="+quotas);
        for(LeaveQuota lq:quotas)
        {
            //System.out.println("selected====="+lq.getLeaveTypeName());
        }
        if(quotas!=null&&!quotas.isEmpty())
        {
            dataGrid.setValue(quotas);
        }
        return quotas;
    }

    public void setQuotas(ArrayList<LeaveQuota> quotas) {
        this.quotas = quotas;
    }
    private ArrayList<LeaveQuota> allotedquota;
    public ArrayList<LeaveQuota> getAllotedQuota() {
         //quotas = new LeaveQuotaDB().getQuota(empType);
         allotedquota = new LeaveQuotaDB().getCombinedData(code);
        /*for(LeaveQuota lq: allotedquota)
        {
            System.out.println(lq.getLeaveTypeName());
        }
        if(allotedquota!=null&&!allotedquota.isEmpty())
        {
            dataGrid.setValue(allotedquota);
        }*/
        dataGrid.setValue(allotedquota);
        return allotedquota;
    }

    public void setAllotedQuota(ArrayList<LeaveQuota> allotedquota) {
        this.allotedquota = allotedquota;
    }
    
    public void loadleaveDetail() {
        try{
        
            //allotedquota = new LeaveQuotaDB().getAllotedQuota(code);
            allotedquota = new LeaveQuotaDB().getCombinedData(code);
            if((allotedquota.isEmpty()) && (!code.equals("null"))){
         
                FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "Leave Record of employee is not exist", ""));
          
            }
       
        }    
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public int getBalanceCount() {
        return balancecount;
    }

    public void setBalanceCount(int balancecount) {
        this.balancecount = balancecount;
    }
    
      
}
