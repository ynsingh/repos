/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans.composite;

import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIData;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.smvdu.payroll.beans.SalaryTypeMaster;
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
* Contributors: Members of ERP Team @ SMVDU, Katra
* Modified Date: 13 Nov 2014, IITK (palseema30@gmail.com, kishore.shuklak@gmail.com)
*
*/
public class SalaryHeadController {
    
    
    public static final int TDS_CODE = 13;

    public SalaryHeadController()
    {
        heads = new SalaryHeadDB().loadAllHeads();
    }

    private SelectItem[] asItem;

    public SelectItem[] getAsItem() {
        
        return asItem;
    }

    public void setAsItem(SelectItem[] asItem) {
        this.asItem = asItem;
    }

    
    private SelectItem[] category;

    public SelectItem[] getCategory() {
        ArrayList<SalaryTypeMaster> types = new SalaryHeadDB().getTypes();
        category = new SelectItem[types.size()];
        SalaryTypeMaster st = null;
        for(int i=0;i<types.size();i++)
        {
            st = types.get(i);
            SelectItem si = new SelectItem(st.getCode(), st.getName());
            category[i] = si;
        }
        return category;
    }

    public void setCategory(SelectItem[] category) {
        this.category = category;
    }
        
    private ArrayList<SalaryHead> heads;
    private UIData dataGrid;

    public UIData getDataGrid() {
        return dataGrid;
    }

    public void setDataGrid(UIData dataGrid) {
        this.dataGrid = dataGrid;
    }

    public ArrayList<SalaryHead> getHeads() {
        return heads;
    }

    public void setHeads(ArrayList<SalaryHead> heads) {
        this.heads = heads;
    }

    public void delete(int id)
    {
        //System.out.println("Got delete request for "+id);
    }

    public void update()
    {
        ArrayList<SalaryHead> hs = (ArrayList<SalaryHead>)dataGrid.getValue();
        for(SalaryHead sh : hs)
        {
            FacesContext fc = FacesContext.getCurrentInstance();
            if (sh.getSHCode().matches("^[a-zA-Z0-9\\s]*$") == false) {
            FacesMessage message = new FacesMessage();
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            message.setSummary("Please Enter Valid Code. No Special Characters are Allowed ");
            //message.setDetail("First Name Must Be At Least Three Charecter ");
            fc.addMessage("", message);
            return;
            }
            if (sh.getName().trim().matches("^[a-zA-Z\\s]*$") == false) {
            FacesMessage message = new FacesMessage();
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            message.setSummary("Plz Enter Valid Salary Head Name. No Special Characters are Allowed");
            //message.setDetail("First Name Must Be At Least Three Charecter ");
            fc.addMessage("", message);
            return;
        }
            if (sh.getAlias().matches("^[a-zA-Z\\s]*$") == false) {
            FacesMessage message = new FacesMessage();
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            message.setSummary("Please Enter Valid Short Name. No Special Characters are Allowed ");
            //message.setDetail("First Name Must Be At Least Three Charecter ");
            fc.addMessage("", message);
            return;
            }
            //System.out.println("Name : "+sh.getName()+""+sh.isType());
        }
        new SalaryHeadDB().update(heads);
        FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "Salary Heads Updated", ""));
    }

}
