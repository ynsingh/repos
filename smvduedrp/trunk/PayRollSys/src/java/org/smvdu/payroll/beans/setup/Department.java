/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans.setup;

import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import org.smvdu.payroll.beans.db.DepartmentDB;
import javax.faces.model.SelectItem;
import org.smvdu.payroll.beans.BaseBean;

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
public class Department extends BaseBean implements Converter,Serializable{
    public void save() {
        FacesContext fc = FacesContext.getCurrentInstance();
        if (this.getName().matches("^[a-zA-Z\\s]*$") == false) {
            FacesMessage message = new FacesMessage();
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            message.setSummary("Plz Enter Valid Department Name.No speacial characters allowed.");
            //message.setDetail("First Name Must Be At Least Three Charecter ");
            fc.addMessage("", message);
            return;
        }
        Exception e = new DepartmentDB().save(getName());
         if(e==null)
        {
            FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "Department saved"+getName(), ""));
        }
 else
        {
            FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Department already Exist : "+getName(), ""));
        }
        
    }

    private SelectItem[] arrayAsItem;

    public SelectItem[] getArrayAsItem() {

        ArrayList<Department> departments = new DepartmentDB().loadDepartments();
        arrayAsItem = new SelectItem[departments.size()];
        Department dp = null;
        for(int i=0;i<departments.size();i++)
        {
            dp = departments.get(i);
            SelectItem si = new SelectItem(dp.getCode(), dp.getName());
            arrayAsItem[i] = si;
        }
        return arrayAsItem;
    }

    public void setArrayAsItem(SelectItem[] arrayAsItem) {
        this.arrayAsItem = arrayAsItem;
    }
        


    private int empCount;

    public int getEmpCount() {
        return empCount;
    }

    public void setEmpCount(int empCount) {
        this.empCount = empCount;
    }
    

    

    @Override
    public String toString()
    {
        return getName();
    }

    
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        System.err.println("Got String "+string);
        Department dept =  new DepartmentDB().convert(string);
        System.err.println("Got Object Name "+dept.getName()+",Code "+dept.getCode());
        return dept;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        System.out.println("Object class Dept: "+o.getClass().getSimpleName());
        BaseBean bb = (BaseBean)o;
        return String.valueOf(bb.getName());
    }
   
}
