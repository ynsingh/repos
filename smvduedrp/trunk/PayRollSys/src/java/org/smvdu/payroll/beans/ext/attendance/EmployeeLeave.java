/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans.ext.attendance;

import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.smvdu.payroll.beans.Employee;
import org.smvdu.payroll.beans.db.CommonDB;
import org.smvdu.payroll.beans.ext.attendance.db.EmployeeLeaveDB;
import org.smvdu.payroll.module.attendance.LoggedEmployee;

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
public class EmployeeLeave {

    private int id;
    private int empId;
    private String dateFrom;
    private String dateTo;
    private int count;
    private Employee employee;
    private int leaveTypeCode;
    private String leaveTypeName;

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

    private ArrayList<EmployeeLeave> leaveData;
    private ArrayList<EmployeeLeave> singleLeaveData;

    public ArrayList<EmployeeLeave> getSingleLeaveData() {
        return singleLeaveData;
    }

    public void setSingleLeaveData(ArrayList<EmployeeLeave> singleLeaveData) {
        this.singleLeaveData = singleLeaveData;
    }
    



    public ArrayList<EmployeeLeave> getLeaveData() {
        leaveData = new EmployeeLeaveDB().loadLeaves();
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


    public void save()
    {
        this.count = new CommonDB().getDateDiff(dateTo, dateFrom);
        if(count<0)
        {
            FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Dates are wrong", ""));
            return;
        }
        new EmployeeLeaveDB().save(this);
        FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "Leave Saved", ""));
    }
    

}
