/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans.setup;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIData;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.smvdu.payroll.beans.Employee;
import org.smvdu.payroll.beans.db.EmployeeDB;
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
      
    private int code;
    
    public int getCode() {
        return code;
    }
    
    public void setCode(int code) {
        this.code = code;
    }
     
    private String empTypeCode;
    
     public String getEmpTypeCode() {
        return empTypeCode;
    }

    public void setEmpTypeCode(String emptypecode) {
        this.empTypeCode = emptypecode;
    }
	
    private String name;

    public String getName() {
        return name;
    }
    
    public void setName(String string) {
        name = string;
    }
    
    private String nickname;
    
    public String getNickname() {
        return nickname;
    }


    public void setNickname(String string) {
        nickname = string;
    }

    private boolean pfApplies;
    
    public boolean isPfApplies() {
        return pfApplies;
    }

    public void setPfApplies(boolean pfApplies) {
        this.pfApplies = pfApplies;
    }
    
    
    private int maxpf;
    
    public int getMaxpf() {
        return maxpf;
    }


    public void setMaxpf(int mpf) {
        this.maxpf = mpf;
    }

    private int orgcode;

    public int getOrgcode() {
        return orgcode;
    }

    public void setOrgcode(int orgcode) {
        this.orgcode = orgcode;
    }
    
    private String message;
    
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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

    private ArrayList<EmployeeType> allTypes;

    public ArrayList<EmployeeType> getAllTypes() {

        allTypes = new EmployeeTypeDB().loadTypes();
        dataGrid.setValue(allTypes); 
        return allTypes;
    }
    
    private Set empTypeSalaryCode = new HashSet();

    public Set getEmpTypeSalaryCode() {
        return empTypeSalaryCode;
    }

    public void setEmpTypeSalaryCode(Set empTypeSalaryCode) {
        this.empTypeSalaryCode = empTypeSalaryCode;
    }
    
    
    
    @Override
    public String toString()
    {
        return name;
    }


    public void setAllTypes(ArrayList<EmployeeType> allTypes) {
        this.allTypes = allTypes;
    }
    
    // This method is used for Saving the values in the database

    public void save()
    {
         FacesContext fc = FacesContext.getCurrentInstance();
          
         if (this.getEmpTypeCode().matches("^[a-zA-Z0-9\\s]*$") == false) {
                FacesMessage message = new FacesMessage();
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                message.setSummary("Please Enter Valid Code. No Special Characters are Allowed");
                fc.addMessage("", message);
                return;
            }
        
            if (this.getName().matches("^[a-zA-Z0-9\\s]*$") == false) {
                FacesMessage message = new FacesMessage();
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                message.setSummary("Please Enter Valid Name. No Special Charecters are Allowed");
                fc.addMessage("", message);
                return;
            }

            if (this.getNickname().matches("^[a-zA-Z0-9\\s]*$") == false) {
                FacesMessage message = new FacesMessage();
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                message.setSummary("Please Enter Valid Nick Name. No Special Charecters are Allowed");
                fc.addMessage("", message);
                return;
            }
      
           
     /*     if (this.getMaxpf().matches("^[0-9]*$") == false){
                FacesMessage message = new FacesMessage();
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                message.setSummary("Please Enter Valid PF Limit. Only Numeric Values are Allowed");
                //message.setDetail("First Name Must Be At Least Three Charecter ");
                fc.addMessage("", message);
                return;
            }*/
              
        Exception e = new EmployeeTypeDB().save(this);
        if(e==null)
        {
            FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "New Employee Type Saved", ""));
        }
        else
        {
            FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Employee Type Already Exist", ""));
        }
    
    }
}
