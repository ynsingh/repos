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
import org.smvdu.payroll.beans.db.InvestmentTypeDB;
import org.smvdu.payroll.beans.setup.InvestmentType;

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
*  Modified Date: 27 Sep 2013, IITK (palseema@rediffmail.com, kshuklak@rediffmail.com)
*
 */
public class InvestmentTypeController {
    

    private UIData dataGrid;
    private ArrayList<InvestmentType> types;
    
    public InvestmentTypeController()
    {
        types = new InvestmentTypeDB().load();
    }
     
    public UIData getDataGrid() {
        return dataGrid;
    }

    public void setDataGrid(UIData dataGrid) {
        this.dataGrid = dataGrid;
    }
    
    private SelectItem[] asItems;

    public SelectItem[] getAsItems() {
        types = new InvestmentTypeDB().load();
        asItems = new SelectItem[types.size()];
        int k=0;
        for(InvestmentType it : types)
        {
            SelectItem si = new SelectItem(it.getCode(), it.getName());
            asItems[k] = si;
            k++;
        }
        return asItems;
    }

    public void setAsItems(SelectItem[] asItems) {
        this.asItems = asItems;
    }
    
    public ArrayList<InvestmentType> getTypes() {
       // types = new InvestmentTypeDB().load();
        return types;
    }

    public void setTypes(ArrayList<InvestmentType> insttypes) {
        this.types = insttypes;
    }
    
    public void update()
    {
        ArrayList<InvestmentType> datas = (ArrayList<InvestmentType>)dataGrid.getValue();
        for(InvestmentType it : datas)
        {
            System.out.println("ic====="+it.getName());
        }
        Exception e = new InvestmentTypeDB().update(datas);
        if(e == null)
        {
           FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "Updated", ""));
        }
        else
        {
          FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
        }
    }
    
}
