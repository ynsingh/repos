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
 *  Copyright (c) 2010 - 2011 SMVDU, Katra.
 *  Copyright (c) 2014 - 2017 ETRG, IITK.
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
*  Modification :(Leave Management inst. admin), April 2017, Om Prakash (omprakashkgp@gmail.com)
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
    private int leaveValue;
    private String applieddate;
    private String approvaldate;
    private int status;
    private String activestatus;
    private boolean inactivestatus;
    private String code;
    private boolean selected;
    private String reasonfleave;
    private String contractno;
    private String reportingoff;
    private String coveringoff;
    private String comments;
    private int srNo;
    private int orgId;
    private String empCode;
    
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
    private ArrayList<EmployeeLeave> allLeaveDetails;

    public ArrayList<EmployeeLeave> getAllLeaveDetails() {
        allLeaveDetails = new EmployeeLeaveDB().getAllLeaveDetails();
        dataGrid.setValue(allLeaveDetails);  

        return allLeaveDetails;
    }

    public void setAllLeaveDetails(ArrayList<EmployeeLeave> allLeaveDetails) {
        this.allLeaveDetails = allLeaveDetails;
    }
    
    
    public ArrayList<EmployeeLeave> getSingleLeaveData() {
        return singleLeaveData;
    }

    public void setSingleLeaveData(ArrayList<EmployeeLeave> singleLeaveData) {
        this.singleLeaveData = singleLeaveData;
    }
    

    public ArrayList<EmployeeLeave> getLeaveData() {
        
        leaveData = new EmployeeLeaveDB().getAllLeaveData();
        dataGrid.setValue(leaveData);  
        return leaveData;
    }

    public void setLeaveData(ArrayList<EmployeeLeave> leaveData) {
        this.leaveData = leaveData;
    }

    public String getReasonfleave() {
        return reasonfleave;
    }

    public void setReasonfleave(String reasonfleave) {
        this.reasonfleave = reasonfleave;
    }

    public String getContractno() {
        return contractno;
    }

    public void setContractno(String contractno) {
        this.contractno = contractno;
    }

    public String getReportingoff() {
        return reportingoff;
    }

    public void setReportingoff(String reportingoff) {
        this.reportingoff = reportingoff;
    }

    public String getCoveringoff() {
        return coveringoff;
    }

    public void setCoveringoff(String coveringoff) {
        this.coveringoff = coveringoff;
    }

    public String getComments() {
             return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
            
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getLeaveValue() {
        String csName=new EmployeeLeaveDB().CurrentSessionName();
        int b = new EmployeeLeaveDB().getEmpOldLeaveValue(empCode, leaveTypeCode, csName);
        leaveValue =leaveValue-b;
        return leaveValue;
    }

    public void setLeaveValue(int leaveValue) {
        this.leaveValue = leaveValue;
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

    public String getApprovaldate() {
         return approvaldate;
    }

    public void setApprovaldate(String approvaldate) {
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
         return currentRecordindex;
    }
 
    public void setCurrentRecordindex(int currentRecordindex) {
        this.currentRecordindex = currentRecordindex;
    }
    
    
    public String getActiveStatus() {
            return activestatus;
    }   

    public void setActiveStatus(String activestatus) {
        this.activestatus = activestatus;
    }
   
    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    public int getSrNo() {
        return srNo;
    }

    public void setSrNo(int srNo) {
        this.srNo = srNo;
    }

    public int getOrgId() {
        return orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    public String getApplieddate() {
        return applieddate;
    }

    public void setApplieddate(String applieddate) {
        this.applieddate = applieddate;
    }

    
    
    public void save()
    {
        this.count = new CommonDB().getDateDiff(dateFrom, dateTo);
        
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
    
    public void updateLeaveReq(){
      try
        {
            ArrayList<EmployeeLeave> dataup = new ArrayList<EmployeeLeave>();
            ArrayList<EmployeeLeave> data = ( ArrayList<EmployeeLeave>)dataGrid.getValue();
            for(EmployeeLeave empl : data)
            {
                if(empl.isSelected())
                {
                    dataup.add(empl);
               
                    if(empl.getCount()<=empl.getLeaveValue())
                    {    
                        new EmployeeLeaveDB().updateLeaveTypeValue(empl);
                    }
                    if(empl.getCount()>empl.getLeaveValue())
                    {
                        FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, " Sorry suffcient leave count is not available, so leave can not approved", ""));
                        return;
                    }    
                } 
            } 
            boolean b = new EmployeeLeaveDB().updateLeave(dataup);
            
            if(b==true)
            {
                 
                FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "Leave Updated", ""));
            }
            else{
              
                 FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, " Select the checkbox ", ""));
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public void rejectLeaveReq(){
            ArrayList<EmployeeLeave> dataLeave = new ArrayList<EmployeeLeave>();
            ArrayList<EmployeeLeave> data = ( ArrayList<EmployeeLeave>)dataGrid.getValue();
            for(EmployeeLeave empl : data)
            {
                if(empl.isSelected())
                {
                    dataLeave.add(empl);

                }
  
            } 
        boolean b = new EmployeeLeaveDB().rejectLeave(dataLeave);
        if(b==true){    
            FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, " Leave Rejected ", ""));
        }
      else
        {
            FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, " Select the checkbox ", ""));
            return;
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
