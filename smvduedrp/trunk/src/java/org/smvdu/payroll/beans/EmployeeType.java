/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans;

import java.util.ArrayList;
import javax.faces.model.SelectItem;
import org.smvdu.payroll.beans.db.EmployeeTypeDB;

/**
 *
 * @author Algox
 */
public class EmployeeType {
    
    private String name;


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
    

    private ArrayList<EmployeeType> allTypes;

    public ArrayList<EmployeeType> getAllTypes() {

        allTypes = new EmployeeTypeDB().loadTypes();
        return allTypes;
    }

    public String toString()
    {
        return name;
    }

    public void setAllTypes(ArrayList<EmployeeType> allTypes) {
        this.allTypes = allTypes;
    }

    public void save()
    {
        new EmployeeTypeDB().save(name);
        name=null;
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
        name = string.toUpperCase();
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
