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

/**
 *
 * @author Algox
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
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    private ArrayList<EmployeeLeave> leaveData;

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
