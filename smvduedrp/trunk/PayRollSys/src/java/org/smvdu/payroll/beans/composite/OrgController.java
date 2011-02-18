/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans.composite;

import java.util.ArrayList;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.smvdu.payroll.beans.Org;
import org.smvdu.payroll.beans.UserInfo;
import org.smvdu.payroll.beans.db.OrgProfileDB;

/**
 *
 * @author Algox
 */
public class OrgController {

    private int orgCode;
    private ArrayList<Org> allOrgs;
    private SelectItem[] items;

    private Org currentOrg;

    public Org getCurrentOrg() {
        UserInfo ui = (UserInfo)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserBean");
        currentOrg = new OrgProfileDB().loadOrgProfile(ui.getUserOrgCode());
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
