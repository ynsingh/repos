/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.smvdu.payroll.beans.ext.attendance;

import java.io.Serializable;

/**
 *  Manages Attendance in database.
 *  Copyright (c) 2010 - 2011 SMVDU, Katra.
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
 * 
 *  Contributors: Members of ERP Team @ SMVDU, Katra
 *
 * @author Om Prakash(omprakashkgp@gmail.com), IITK 
 */

public class EmpLeaveMaster implements Serializable {

    public EmpLeaveMaster() {
    }
    
    private int empId;
    private String empcode;
    private String datefrom;
    private String dateto;
    private int count;
    private int quatatype;
    private String applieddate;
    private String approvaldate;
    private int approvalstatus;
    private int orgid;
    private String reasonforleave;
    private String contractno;
    private String reportingoff;
    private String coveringoff;
    private String comments;

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getEmpcode() {
        return empcode;
    }

    public void setEmpcode(String empcode) {
        this.empcode = empcode;
    }

    public String getDatefrom() {
        return datefrom;
    }

    public void setDatefrom(String datefrom) {
        this.datefrom = datefrom;
    }

    public String getDateto() {
        return dateto;
    }

    public void setDateto(String dateto) {
        this.dateto = dateto;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getQuatatype() {
        return quatatype;
    }

    public void setQuatatype(int quatatype) {
        this.quatatype = quatatype;
    }

    public String getApplieddate() {
        return applieddate;
    }

    public void setApplieddate(String applieddate) {
        this.applieddate = applieddate;
    }

    public String getApprovaldate() {
        return approvaldate;
    }

    public void setApprovaldate(String approvaldate) {
        this.approvaldate = approvaldate;
    }

    public int getApprovalstatus() {
        return approvalstatus;
    }

    public void setApprovalstatus(int approvalstatus) {
        this.approvalstatus = approvalstatus;
    }

    public int getOrgid() {
        return orgid;
    }

    public void setOrgid(int orgid) {
        this.orgid = orgid;
    }

    public String getReasonforleave() {
        return reasonforleave;
    }

    public void setReasonforleave(String reasonforleave) {
        this.reasonforleave = reasonforleave;
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
        
}
