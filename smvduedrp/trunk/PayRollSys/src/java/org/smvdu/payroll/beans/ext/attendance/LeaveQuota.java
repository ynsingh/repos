/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans.ext.attendance;

/**
 *
 * @author Algox
 */
public class LeaveQuota {
    private int empType;
    private String empTypename;
    private int leaveType;
    private String leaveTypeName;
    private int count;
   
    
    
    

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getEmpType() {
        return empType;
    }

    public void setEmpType(int empType) {
        this.empType = empType;
    }

    public String getEmpTypename() {
        return empTypename;
    }

    public void setEmpTypename(String empTypename) {
        this.empTypename = empTypename;
    }

    public int getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(int leaveType) {
        this.leaveType = leaveType;
    }

    public String getLeaveTypeName() {
        return leaveTypeName;
    }

    public void setLeaveTypeName(String leaveTypeName) {
        this.leaveTypeName = leaveTypeName;
    }

   

}
