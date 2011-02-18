/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans;

import java.util.ArrayList;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import org.smvdu.payroll.beans.db.DepartmentDB;
import javax.faces.model.SelectItem;

/**
 *
 * @author Algox
 */
public class Department extends BaseBean implements Converter{
    public void save() {
        new DepartmentDB().save(getName());
        
    }

    private SelectItem[] arrayAsItem;

    public SelectItem[] getArrayAsItem() {

        ArrayList<Department> departments = new DepartmentDB().loadDepartments();
        arrayAsItem = new SelectItem[departments.size()];
        Department dp = null;
        for(int i=0;i<departments.size();i++)
        {
            dp = departments.get(i);
            SelectItem si = new SelectItem(dp.getCode(), dp.getName());
            arrayAsItem[i] = si;
        }
        return arrayAsItem;
    }

    public void setArrayAsItem(SelectItem[] arrayAsItem) {
        this.arrayAsItem = arrayAsItem;
    }
        



    

    @Override
    public String toString()
    {
        return getName();
    }

    
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        System.err.println("Got String "+string);
        Department dept =  new DepartmentDB().convert(string);
        System.err.println("Got Object Name "+dept.getName()+",Code "+dept.getCode());
        return dept;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        System.out.println("Object class Dept: "+o.getClass().getSimpleName());
        BaseBean bb = (BaseBean)o;
        return String.valueOf(bb.getName());
    }
   
}
