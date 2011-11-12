/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans.composite;

import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIData;
import javax.faces.context.FacesContext;
import org.smvdu.payroll.beans.setup.SalaryHead;
import org.smvdu.payroll.beans.db.SalaryHeadDB;

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
public class DefaultSalaryData {

    private int grade;
    private ArrayList<SalaryHead> salaryData;
    private UIData dataGrid;

    public UIData getDataGrid() {
        return dataGrid;
    }

    public void setDataGrid(UIData dataGrid) {
        this.dataGrid = dataGrid;
    }
    public void print()
    {
       ArrayList<SalaryHead> data = (ArrayList<SalaryHead>) dataGrid.getValue();
       for(SalaryHead sd : data)
       {
           System.out.println(sd.getName()+" : "+sd.getNumber()+" Value : "+sd.getDefaultValue());
       }
       System.out.println("Saving Data ...");
       new SalaryHeadDB().updateDefault(data, grade);
       FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "DefaultSalary Updated", "Default Salary Updated"));
    }

    public void populate()
    {
        salaryData = new SalaryHeadDB().loadSelectedHeads(grade);
        System.err.println(salaryData.size());
    }
    

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
        System.err.println("New Grade : "+grade);
    }

    public ArrayList<SalaryHead> getSalaryData() {
        return salaryData;
    }

    public void setSalaryData(ArrayList<SalaryHead> salaryData) {
        this.salaryData = salaryData;
    }

}
