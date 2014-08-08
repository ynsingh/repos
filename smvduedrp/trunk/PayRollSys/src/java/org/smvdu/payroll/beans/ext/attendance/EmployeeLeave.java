/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans.ext.attendance;


import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIData;
import javax.faces.context.FacesContext;
import org.smvdu.payroll.beans.Employee;
import org.smvdu.payroll.beans.db.CommonDB;
import org.smvdu.payroll.module.attendance.LoggedEmployee;
import org.smvdu.payroll.beans.ext.attendance.db.EmployeeLeaveDB;

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

public class EmployeeLeave implements Serializable{

    public EmployeeLeave()
    {

    }
    
    private int id;
    private int empId;
    private String dateFrom;
    private String dateTo;
    private int count;
    private Employee employee;
    private int leaveTypeCode;
    private String leaveTypeName;
    private String applieddate;
    private String approvaldate;
    private int status;
    private String activestatus;
    private boolean inactivestatus;
    private String code;
    private boolean selected;
    
    
     public EmployeeLeave(EmployeeLeaveData empld)
     {
            id = empld.getId();
            code=empld.getEmpCode();
            dateFrom=empld.getDateFrom();
            dateTo=empld.getDateTo();
            count=empld.getCount();
            employee=empld.getEmployee();
            leaveTypeName=empld.getLeaveTypeName();
            applieddate=empld.getAppliedDate();
            activestatus=empld.getActiveStatus();
            
        
        //checked=ld.isChecked();

    }

    public int getEmpId() {
        LoggedEmployee ec= (LoggedEmployee)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("LoggedEmployee");
        if(ec!=null&&ec.getProfile()!=null)
        {
            empId = ec.getProfile().getEmpId();
        }
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    private UIData dataGrid;
   
        
    public UIData getDataGrid() {
        return dataGrid;
    }

    public void setDataGrid(UIData dataGrid) {
        this.dataGrid = dataGrid;
    }
    
    private ArrayList<EmployeeLeave> leaveData;
    private ArrayList<EmployeeLeave> singleLeaveData;

    public ArrayList<EmployeeLeave> getSingleLeaveData() {
        return singleLeaveData;
    }

    public void setSingleLeaveData(ArrayList<EmployeeLeave> singleLeaveData) {
        this.singleLeaveData = singleLeaveData;
    }
    

    public ArrayList<EmployeeLeave> getLeaveData() {
        //leaveData = new EmployeeLeaveDB().loadLeaves();
        leaveData = new EmployeeLeaveDB().getAllLeaveData();
        //System.out.println("empleave Data======="+leaveData);
        dataGrid.setValue(leaveData);  
        return leaveData;
    }

    public void setLeaveData(ArrayList<EmployeeLeave> leaveData) {
        this.leaveData = leaveData;
    }
    
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLeaveTypeCode() {
        return leaveTypeCode;
    }

    public void setLeaveTypeCode(int leaveTypeCode) {
        this.leaveTypeCode = leaveTypeCode;
    }

    public String getLeaveTypeName() {
        return leaveTypeName;
    }

    public void setLeaveTypeName(String leaveTypeName) {
        this.leaveTypeName = leaveTypeName;
    }
    
    public String getAppliedDate() {
        return applieddate;
    }

    public void setAppliedDate(String applieddate) {
        this.applieddate = applieddate;
    }

    public String getApprovalDate() {
        return approvaldate;
    }

    public void setApprovalDate(String approvaldate) {
        this.approvaldate = approvaldate;
    }
    
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
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
    
    
    public String getActiveStatus() {
        
        return activestatus;
    }

    public void setActiveStatus(String activestatus) {
        this.activestatus = activestatus;
    }
   
    public String getEmpCode() {
        return code;
    }

    public void setEmpCode(String code) {
        this.code = code;
    }

    public void save()
    {
        //this.count = new CommonDB().getDateDiff(dateTo, dateFrom);
        this.count = new CommonDB().getDateDiff(dateFrom, dateTo);
        //System.out.println("count======"+count);
        if(count<0)
        {
            FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Dates are wrong", ""));
            return;
        }
        new EmployeeLeaveDB().save(this);
        FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "Leave Saved", ""));
    }
    
    public void acceptRequest(){
           
        try
        {
            ArrayList<EmployeeLeave> datacopy = new ArrayList<EmployeeLeave>();
            ArrayList<EmployeeLeave> data = ( ArrayList<EmployeeLeave>)dataGrid.getValue();
            for(EmployeeLeave empl : data)
            {
                if(empl.isSelected())
                {
                    datacopy.add(empl);
               
                }
            
            } 
            boolean b = new EmployeeLeaveDB().Accept(datacopy);
            //System.out.print("b==in==="+b);
            if(b==true)
            {
                 
                FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "Selection Updated", ""));
            }
            else{
              
                 FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, " Sorry suffcient leave count is not available, so leave can not approved", ""));
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    
    public EmployeeLeave(int id)
    {
        this.id = id;
    }
    
     public int   Integer(){
        return id;
    }
    
     @Override
    public boolean equals(Object obj)
    {
        EmployeeLeave sh = (EmployeeLeave)obj;
        if(this.id==sh.id)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

}
