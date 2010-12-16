/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans;

import java.util.ArrayList;
import javax.faces.model.SelectItem;

import org.smvdu.payroll.beans.db.DepartmentDB;

/**
 *
 * @author Algox
 */
public class Department  {


    private String  selectionLength = "Selection Length : ";
    private SelectItem[] selectedItems;
    private SelectItem[] allDepts;
    private String name;
    
    public SelectItem[] getSelectedItems() {

        ArrayList<Department> deps = new DepartmentDB().loadDepartments();
        selectedItems =new SelectItem[deps.size()];
        Department dep = null;
        for(int i=0;i<deps.size();i++)
        {
            dep = deps.get(i);
            SelectItem si = new SelectItem(dep.code, dep.name);
            selectedItems[i] = si;
        }
        return selectedItems;
    }

    public String getSelectionLength() {
        return selectionLength;
    }

    public void setSelectionLength(String selectionLength) {
        //this.selectionLength = selectionLength;
    }

    public String print()
    {
        int[] da = new int[selectedItems.length];
        for(int i=0;i<selectedItems.length;i++)
        {
            SelectItem si = selectedItems[i];
            da[i] = (Integer)si.getValue();
            System.out.println("Value : "+si.getValue());
        }
        new TempDB().save(da);
        return "case1";
        //this.selectedItems = selectedItems;
    }

    public void setSelectedItems(SelectItem[] selectedItems) {
        int[] da = new int[selectedItems.length];
        for(int i=0;i<selectedItems.length;i++)
        {
            SelectItem si = selectedItems[i];
            da[i] = (Integer)si.getValue();
            System.out.println("Value : "+si.getValue());
        }
        new TempDB().save(da);
        this.selectedItems = selectedItems;

        selectionLength+= this.selectedItems.length;
    }



    

    public void setAllDepts(SelectItem[] allDepts) {
        this.allDepts = allDepts;
    }

    public void setDepartments(ArrayList<Department> departments) {
        this.departments = departments;
    }

    private ArrayList<Department> departments;

    private int code;
    private String message ="";
    private String error = " <font color='green'>* Department name Cannot be empty </font>";

    public ArrayList<Department> getDepartments() {
        ArrayList<Department> deps = new DepartmentDB().loadDepartments();
        return deps;
    }
    public SelectItem[] getAllDepts() {


        ArrayList<Department> deps = new DepartmentDB().loadDepartments();
        allDepts =new SelectItem[deps.size()];
        Department dep = null;
        for(int i=0;i<deps.size();i++)
        {
            dep = deps.get(i);
            SelectItem si = new SelectItem(dep.code, dep.name);
            
            allDepts[i] = si;
        }
        return allDepts;
    }

    @Override
    public String toString()
    {
        return name;
    }

    public void setAllDepts(ArrayList<Department> allDepts) {
        //this.allDepts = allDepts;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
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
    public int getNumber() {
        return code;
    }

    /**
     * @param i
     */
    public void setNumber(int i) {
        code = i;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public void save()
    {
        new DepartmentDB().save(name);
        name=null;
    }
    public Department() {
       
    }

}
