/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.taxslabs;

import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIData;
import javax.faces.context.FacesContext;
import org.smvdu.payroll.beans.db.TaxSlabDB;


/**
 *
 * @author smvdu
 */
public class TaxTypeController {

    /** Creates a new instance of TaxTypeController */
    public TaxTypeController()
    {
        types = new TaxSlabDB().load();
    }
   private UIData datagrid;
   private ArrayList<TaxType> types;

    public UIData getDatagrid() {
        return datagrid;
    }

    public void setDatagrid(UIData datagrid) {
        this.datagrid = datagrid;
    }

    public ArrayList<TaxType> getTypes() {
        return types;
    }

    public void setTypes(ArrayList<TaxType> types) {
        this.types = types;
    }

public void update()
    {
        ArrayList<TaxType> data = (ArrayList<TaxType>)datagrid.getValue();
        for(TaxType tt : data)
        {
            System.out.println(tt.getCode()+","+tt.getTaxpercent());
        }
        Exception e = new TaxSlabDB().update(data);
        if(e==null)
        {
            FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "Updated", ""));
        }
        else
        {
            FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(),""));
        }
    }

}
