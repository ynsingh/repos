/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans.composite;

import java.util.ArrayList;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.smvdu.payroll.beans.setup.Org;
import org.smvdu.payroll.beans.UserInfo;
import org.smvdu.payroll.beans.db.OrgProfileDB;
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
public class OrgController {

    private int orgCode;
    private ArrayList<Org> allOrgs;
    private SelectItem[] items;

    private Org currentOrg;

    public Org getCurrentOrg() {
        
        
        if(currentOrg==null)
        {
            UserInfo ui = (UserInfo)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserBean");
            /*if(ui==null)
            {
                LoggedEmployee le =(LoggedEmployee)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("LoggedEmployee");
                orgCode = le.getUserOrgCode();
            }*/
            //else
            //{
                orgCode = ui.getUserOrgCode();
             //}
                currentOrg = new OrgProfileDB().loadOrgProfile(orgCode);
        }
        return currentOrg;
    }

    public void setCurrentOrg(Org currentOrg) {
        this.currentOrg = currentOrg;
    }
    

    public ArrayList<Org> getAllOrgs() {
        allOrgs = new OrgProfileDB().loadOrgs();
        return allOrgs;
    }

    public void setAllOrgs(ArrayList<Org> allOrgs) {
        this.allOrgs = allOrgs;
    }

    public SelectItem[] getItems() {
        allOrgs = new OrgProfileDB().loadOrgs();
        if(allOrgs==null)
        {
            return null;
        }
        items = new SelectItem[allOrgs.size()];
        Org o = null;
        for(int i=0;i<allOrgs.size();i++)
        {
            o = allOrgs.get(i);
            SelectItem si = new SelectItem(o.getId(), o.getName());
            items[i] = si;
        }
        return items;
    }

    public void setItems(SelectItem[] items) {
        this.items = items;
    }

    public int getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(int orgCode) {
        this.orgCode = orgCode;
    }
    

}
