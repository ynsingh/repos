/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans.composite;

import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIData;
import javax.faces.context.FacesContext;
import org.smvdu.payroll.beans.setup.SalaryGrade;
import org.smvdu.payroll.beans.db.SalaryGradeDB;

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
* Modified Date: 03 Nov 2014, IITK (palseema30@gmail.com, kishore.shuklak@gmail.com)
*
*/
    public class SalaryGradeController {


    public SalaryGradeController()
    {
        grades = new SalaryGradeDB().load();
    }

    private UIData dataGrid;
    private ArrayList<SalaryGrade> grades;

    public ArrayList<SalaryGrade> getGrades() {
        return grades;
    }

    public void setGrades(ArrayList<SalaryGrade> grades) {
        this.grades = grades;
    }
    

    public UIData getDataGrid() {
        return dataGrid;
    }

    public void setDataGrid(UIData dataGrid) {
        this.dataGrid = dataGrid;
    }



    public void save()
    {
        ArrayList<SalaryGrade> grades = (ArrayList<SalaryGrade>)dataGrid.getValue();
        for(SalaryGrade sg : grades)
        {
            FacesContext fc = FacesContext.getCurrentInstance();
            if((sg.getName().matches("^[a-zA-Z0-9\\s]*$") == false) || (sg.getName().equals(""))) {
                FacesMessage message = new FacesMessage();
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                message.setSummary("Please Enter Valid Pay Band Name. No Special Characters are Allowed");
                fc.addMessage("", message);
                return;
            }
           // System.out.println("Code : "+sg.getCode()+"Name : "+sg.getName()+", Max : "+sg.getMaxValue()+", Min : "+sg.getMinValue());
        }
       Exception e =  new SalaryGradeDB().update(grades);
       if (e==null)
        FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "Grades Updated", ""));
       else
        FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Grade Already Exist", ""));
    }

}
