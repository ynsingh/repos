/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.smvdu.payroll.api.emporder;

import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.component.UIData;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.smvdu.payroll.beans.Employee;
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
public class OrderController implements Serializable{
    
    
    private UIData masterGrid;
    private String parent;    
    private ArrayList<Employee> children;
    private UIData dataGrid;

    public UIData getMasterGrid() {
        return masterGrid;
    }

    public void setMasterGrid(UIData masterGrid) {
        this.masterGrid = masterGrid;
    }

    
    public void addMember()
    {
        ArrayList<Employee> data = (ArrayList<Employee>)masterGrid.getValue();
        for(Employee emp : data)
        {
            System.out.println(emp.getName()+emp.isSelected());
        }
    }
    
    private String page;

    public String getPage() {
        page ="TeamTask.jsf";
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

   
    
    
    
    public void load()
    {
        children = new OrderDB().load(parent);
        
    }


    private SelectItem[] asItem;

    public SelectItem[] getAsItem() {
        children = new OrderDB().load(parent);
        asItem = new SelectItem[children.size()];
        Employee emp = null;
        for(int i=0;i<children.size();i++)
        {
            emp = children.get(i);
            SelectItem si = new SelectItem(emp.getCode(), emp.getName());
            asItem[i] =si;
        }
        return asItem;
    }

    public void setAsItem(SelectItem[] asItem) {
        this.asItem = asItem;
    }


    public ArrayList<Employee> getChildren() {
        
//        dataGrid = new HtmlDataGrid();
//        dataGrid.setValue(children);
        
        LoggedEmployee bean = (LoggedEmployee) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("LoggedEmployee");
        if(bean!=null)
        {
            parent = bean.getProfile().getCode();
            children = new OrderDB().load(parent);
        }
        
        
        return children;
    }

    public void setChildren(ArrayList<Employee> children) {
        this.children = children;
    }

    public UIData getDataGrid() {
        return dataGrid;
    }

    public void setDataGrid(UIData dataGrid) {
        this.dataGrid = dataGrid;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }
    
   
    
}
