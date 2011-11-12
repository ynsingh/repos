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
public class DefaultSalaryDataController {

    private int typecode;
    private ArrayList<SalaryHead> heads;
    private UIData grid;

    public UIData getGrid() {
        return grid;
    }

    public void populate()
    {
        heads = new SalaryHeadDB().loadSelectedHeads(typecode);
        grid.setValue(heads);
    }

    public void setGrid(UIData grid) {
        this.grid = grid;
    }

    public ArrayList<SalaryHead> getHeads() {
        return heads;
    }

    public void setHeads(ArrayList<SalaryHead> heads) {
        this.heads = heads;
    }

    public int getTypecode() {
        return typecode;
    }

    public void setTypecode(int typecode) {
        this.typecode = typecode;
        heads = new SalaryHeadDB().loadSelectedHeads(typecode);
    }


    public void update()
    {
        System.err.println("Updating default data... ");
        ArrayList<SalaryHead> hs = (ArrayList<SalaryHead>)grid.getValue();
        for(SalaryHead sh : hs)
        {
            System.err.println("Name : "+sh.getName()+", Value : "+sh.getDefaultValue());
        }
        new SalaryHeadDB().updateDefault(hs, typecode);
        FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO,"Default Values Updated",""));
    }

}
