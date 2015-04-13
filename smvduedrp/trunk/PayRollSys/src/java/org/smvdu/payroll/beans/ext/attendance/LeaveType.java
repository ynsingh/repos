/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans.ext.attendance;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIData;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.smvdu.payroll.beans.ext.attendance.db.LeaveTypeDB;

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
*  Modified Date: 7 AUG 2014, IITK (palseema30@gmail.com, kishore.shuklak@gmail.com)
*/
public class LeaveType implements Serializable{
    
public LeaveType()
    {
         
    }

    private int code;
    private String name;
    //private float value;
    private int value;
    private boolean checked;
    private SelectItem[] items;
    private UIData dataGrid;
    private ArrayList<LeaveType> values;
    private int srNo;
    /*public LeaveType()
    {
        values = new LeaveTypeDB().getAll();
        //heads = new SalaryHeadDB().loadAllHeads();
    }*/

    /*public int getSrNo() {
        return srNo;
    }

    public void setSrNo(int srNo) {
        this.srNo = srNo;
    }*/
    
    
    /*public LeaveType ()
    {
       this.code =code;
       this.name =name;
       this.value = value;
    }*/
    public LeaveType(LeaveData ld)
    {
       
        //this.setName(sd.getHeadName());
        code = ld.getCode();
        name = ld.getName();
        value =ld.getValue();
        //checked=ld.isChecked();
       
        
    }
    
    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
    
    private LeaveType ltype;

    public LeaveType getLeaveType() {
        return ltype;
    }

    public void setLeaveType(LeaveType ltype) {
        this.ltype = ltype;
    }
    
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /*public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }*/
    
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    

    public UIData getDataGrid() {
        return dataGrid;
    }

    public void setDataGrid(UIData dataGrid) {
        this.dataGrid = dataGrid;
    }

    public SelectItem[] getItems() {
        //values = new LeaveTypeDB().getAll();
        values = new LeaveTypeDB().loadLeaveType();
        items = new SelectItem[values.size()];
        LeaveType lv = null;
        for(int i=0;i<values.size();i++)
        {
            lv = values.get(i);
            SelectItem si = new SelectItem(lv.code, lv.name);
            items[i] = si;
        }
        return items;
    }

    public void setItems(SelectItem[] items) {
        this.items = items;
    }
    
    public boolean isChecked() {
        //System.out.println("is chdecked====="+checked);
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
    
    
    int currentRecordindex;
    public int getCurrentRecordindex() {
        //System.out.println("current index====="+currentRecordindex);
        return currentRecordindex;
    }
 
    public void setCurrentRecordindex(int currentRecordindex) {
        //System.out.println("current index==inset==="+currentRecordindex);
        this.currentRecordindex = currentRecordindex;
    }
    
    LeaveType editedRecord;
    public LeaveType getEditedRecord() {
       
        return editedRecord;
    }
 
    public void setEditedRecord(LeaveType editedRecord) {
        this.editedRecord = editedRecord;
    }
       

    public ArrayList<LeaveType> getLeaveValues() {
        //values = new LeaveTypeDB().getAll();
        values = new LeaveTypeDB().loadLeaveType();
        //System.out.println("valus=====:=="+values.size());
        dataGrid.setValue(values);  
        return values;
    }
    
    public void setLeaveValues(ArrayList<LeaveType> values) {
        this.values = values;
    }
    
    private Set leaveOrgRecord = new HashSet();

    public Set getLeaveOrgRecord() {
        return leaveOrgRecord;
    }

    public void setLeaveOrgRecord(Set leaveOrgRecord) {
        this.leaveOrgRecord = leaveOrgRecord;
    }
    
    
    public LeaveType(String name)
    {
        this.name = name;
    }
    
    public String toString() {
        return name;
    }
    
     @Override
    public boolean equals(Object obj)
    {
        LeaveType sh = (LeaveType)obj;
        if(this.code==sh.code)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
     
     
    public void save()
    {
        FacesContext fc = FacesContext.getCurrentInstance();
        
        if (this.getName().matches("^[a-zA-Z\\s]*$") == false) {
            FacesMessage message = new FacesMessage();
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            message.setSummary("Please Enter Valid Leave Name. Speacial characters allowed.");
            fc.addMessage("", message);
            return;
        }
        
        if (this.getValue() <= 0) {
            FacesMessage message = new FacesMessage();
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            message.setSummary("Max Limit must be greater than zero");
            fc.addMessage("", message);
            return;
        }
        
        Exception e = new LeaveTypeDB().save(this);
        if(e==null){
            FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "New Leave type Saved", ""));
        }
        else{
            FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Leave Type Alredy Exist", ""));
        }
    }

    
    
    public void deleteLType(){
        
        try
        {
            FacesContext fc = FacesContext.getCurrentInstance();
            FacesMessage message = new FacesMessage();
            
            ArrayList<LeaveType> data = (ArrayList<LeaveType>)dataGrid.getValue();
            
            
            LeaveType lt=data.get(currentRecordindex);
            int currentIndex=lt.getCode();
            boolean b=new LeaveTypeDB().LeaveExists(currentIndex);
            //System.out.println("\nbollena===bb=="+b+"\ncurrentIndex====="+currentIndex);
            if(b==true){
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                message.setSummary(" Leave type is already in used by some institute, so you cannot delete it.");
                fc.addMessage("", message); 
            }
            else{
                //System.out.println("\n in line 173===="+currentIndex+"\n lt===="+lt.getName());
                Exception ex = new LeaveTypeDB().DeleteleaveType(currentIndex, data);
                if(ex == null )
                {
                    message.setSeverity(FacesMessage.SEVERITY_INFO);
                    message.setSummary("Leave type deleted Successfully");
                    fc.addMessage("", message);
                }
            }
           
            
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    
    public void updateRecord(){
        
        try
        {
            FacesContext fc = FacesContext.getCurrentInstance();
            FacesMessage message = new FacesMessage();
            //System.out.println("\n in line 1139==Record=="+editedRecord.getCode());
            Exception ex = new LeaveTypeDB().update(editedRecord); 
            if(ex == null )
            {
                message.setSeverity(FacesMessage.SEVERITY_INFO);
                message.setSummary(" Record Updated Successfuly");
                fc.addMessage("", message);
            }
               
            
        }   
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
   
    public void print()
    {
        try{
            //System.out.println("Printing selection ");
            ArrayList<LeaveType> datacopy = new ArrayList<LeaveType>();
            ArrayList<LeaveType> data = ( ArrayList<LeaveType>)dataGrid.getValue();
            for(LeaveType lt : data)
            {
            
                if(lt.isChecked())
                {
                    datacopy.add(lt);
                } 
            }
            //System.out.println("Seema=inside=="+datacopy);
            new LeaveTypeDB().AddforInstituite(datacopy);
            
            FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "Selection Updated", ""));
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
       
    }
    
         
}
