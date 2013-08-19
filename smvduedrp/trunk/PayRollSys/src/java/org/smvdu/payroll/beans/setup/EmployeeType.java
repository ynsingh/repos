/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans.setup;

import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIData;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.smvdu.payroll.beans.db.EmployeeTypeDB;

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
public class EmployeeType implements Serializable{
    
    private String name;
    private boolean pfApplies;

    public boolean isPfApplies() {
        return pfApplies;
    }

    public void setPfApplies(boolean pfApplies) {
        this.pfApplies = pfApplies;
    }
    

    private UIData dataGrid;

    public UIData getDataGrid() {
        return dataGrid;
    }

    public void setDataGrid(UIData dataGrid) {
        this.dataGrid = dataGrid;
    }
    

    private SelectItem[] items;

    public SelectItem[] getItems() {
        ArrayList<EmployeeType> myTypes=new EmployeeTypeDB().loadTypes();
        items = new SelectItem[myTypes.size()];
        for(int i=0;i<myTypes.size();i++)
        {
            EmployeeType et =myTypes.get(i);
            SelectItem si =new SelectItem(et.code, et.name);
            items[i] = si;
        }
        return items;
    }

    public void setItems(SelectItem[] items) {
        this.items = items;
    }
    private int code;
    private String message;
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void update()
    {
        ArrayList<EmployeeType> types = (ArrayList<EmployeeType>)dataGrid.getValue();
        for(EmployeeType et : types)
        {
            System.out.println("Name : "+et.getName()+", Code : "+et.getCode());
        }
        FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "Types Updated", ""));
    }

    private ArrayList<EmployeeType> allTypes;

    public ArrayList<EmployeeType> getAllTypes() {

        allTypes = new EmployeeTypeDB().loadTypes();
        return allTypes;
    }

    @Override
    public String toString()
    {
        return name;
    }

    public void setAllTypes(ArrayList<EmployeeType> allTypes) {
        this.allTypes = allTypes;
    }

    public void save()
    {
         FacesContext fc = FacesContext.getCurrentInstance();
        if (this.getName().matches("^[a-zA-Z\\s]*$") == false) {
            FacesMessage message = new FacesMessage();
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            message.setSummary("Plz Enter Valid Name.No speacial characters allowed.");
            //message.setDetail("First Name Must Be At Least Three Charecter ");
            fc.addMessage("", message);
            return;
        }
        new EmployeeTypeDB().save(name,pfApplies);
        name=null;
        FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "New Type Saved", ""));
    }

    /**
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * @param string
     */
    public void setName(String string) {
        name = string;
    }

    /**
     * @return
     */
    public int getCode() {
        return code;
    }

    /**
     * @param i
     */
    public void setCode(int i) {
        code = i;
    }

   
   
}
