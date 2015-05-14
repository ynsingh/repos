/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans.setup;

import java.io.Serializable;

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
// public class EmployeeTypeSalaryHead extends SalaryHead{
public class EmployeeTypeSalaryHead implements Serializable{
    
    private int id;                 // this is the primary key of the database table
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    private EmployeeType empTypeCode;       // this represents st_code field of the database table

    public EmployeeType getEmpTypeCode() {
        return empTypeCode;
    }

    public void setEmpTypeCode(EmployeeType empTypeCode) {
        this.empTypeCode = empTypeCode;
    }
    
    private SalaryHead salaryHeadCode;         // this represents st_sal_code field of the database table

    public SalaryHead getSalaryHeadCode() {
        return salaryHeadCode;
    }

    public void setSalaryHeadCode(SalaryHead salaryHeadCode) {
        this.salaryHeadCode = salaryHeadCode;
    }
    
    private int orgcode;

    public int getOrgcode() {
        return orgcode;
    }

    public void setOrgcode(int orgcode) {
        this.orgcode = orgcode;
    }
    
 //---------------------------------------------------------------------------------------------------------------------------------------
 //---------------------------------------------------------------------------------------------------------------------------------------
    
    
    private String salaryHeadName;

    public String getSalaryHeadName() {
        return salaryHeadName;
    }

    public void setSalaryHeadName(String salaryHeadName) {
        this.salaryHeadName = salaryHeadName;
    }
            
    private boolean selected;

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

}
