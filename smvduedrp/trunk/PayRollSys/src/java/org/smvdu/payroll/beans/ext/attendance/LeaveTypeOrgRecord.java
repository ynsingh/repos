/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.smvdu.payroll.beans.ext.attendance;

import java.io.Serializable;

/**
 *
 * @author yuvraj
 */



public class LeaveTypeOrgRecord implements Serializable {
    
    private int id;
    private LeaveType leaveId;
    private int orgcode;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LeaveType getLeaveId() {
        return leaveId;
    }

    public void setLeaveId(LeaveType leaveId) {
        this.leaveId = leaveId;
    }
    

    public int getOrgcode() {
        return orgcode;
    }

    public void setOrgcode(int orgcode) {
        this.orgcode = orgcode;
    }
    

    
}
