/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans;

import java.util.ArrayList;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.model.SelectItem;
import org.smvdu.payroll.beans.db.DesignationDB;

/**
 *
 * @author Algox
 */
public class Designation  extends BaseBean implements Converter{
   
    public void save()
    {
        new DesignationDB().save(getName());
    }


     private SelectItem[] arrayAsItem;

    public SelectItem[] getArrayAsItem() {

        ArrayList<Designation> designations = new DesignationDB().loadDesignations();
        arrayAsItem = new SelectItem[designations.size()];
        Designation dp = null;
        for(int i=0;i<designations.size();i++)
        {
            dp = designations.get(i);
            SelectItem si = new SelectItem(dp.getCode(), dp.getName());
            arrayAsItem[i] = si;
        }
        return arrayAsItem;
    }

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
       return new DesignationDB().convert(string);
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        System.out.println("Object class Desig : "+o.getClass().getSimpleName());
        return o.toString();
    }
}
