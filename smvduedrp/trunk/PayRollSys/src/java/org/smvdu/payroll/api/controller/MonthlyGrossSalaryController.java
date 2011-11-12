/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.api.controller;

import java.util.ArrayList;
import javax.faces.context.FacesContext;
import org.smvdu.payroll.api.MatrixTransformer;
import org.smvdu.payroll.api.report.IndividualGrossDB;
import org.smvdu.payroll.beans.Employee;
import org.smvdu.payroll.beans.UserInfo;
import org.smvdu.payroll.beans.db.EmployeeDB;
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
public class MonthlyGrossSalaryController {

    private String dateFrom;
    private String dateTo;
    private String empCode;

    private int orgCode;

    public MonthlyGrossSalaryController()
    {
          LoggedEmployee le = (LoggedEmployee) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("LoggedEmployee");
        if(le==null)
        {
            UserInfo uf = (UserInfo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserBean");
            orgCode = uf.getUserOrgCode();
        }
 else
        {
            orgCode = le.getUserOrgCode();
 }
    }


    private Employee employee;

    public Employee getEmployee() {
        employee= new EmployeeDB().loadProfile(empCode,orgCode);
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
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

    public String getEmpCode() {
        LoggedEmployee ec= (LoggedEmployee)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("LoggedEmployee");
        if(ec!=null)
        {
            empCode= ec.getProfile().getCode();
            getSalaryMatrix();
        }
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }
    private ArrayList<GrossData> salaryMatrix;

    public ArrayList<GrossData> getSalaryMatrix() {


        salaryMatrix = MatrixTransformer.transform(new IndividualGrossDB().fetchSalaryMatrix(empCode),empCode);
        return salaryMatrix;
    }

    public void setSalaryMatrix(ArrayList<GrossData> salaryMatrix) {
        this.salaryMatrix = salaryMatrix;
    }

    

    
    

}
