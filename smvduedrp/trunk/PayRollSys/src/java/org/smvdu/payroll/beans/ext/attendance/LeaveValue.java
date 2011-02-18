/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans.ext.attendance;

import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIData;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.smvdu.payroll.beans.ext.attendance.db.LeaveValueDB;

/**
 *
 * @author Algox
 */
public class LeaveValue {

    private int code;
    private String name;
    private float value;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    private SelectItem[] items;
    private UIData dataGrid;
    private ArrayList<LeaveValue> values;

    public UIData getDataGrid() {
        return dataGrid;
    }

    public void setDataGrid(UIData dataGrid) {
        this.dataGrid = dataGrid;
    }

    public SelectItem[] getItems() {
        values = new LeaveValueDB().getAll();
        items = new SelectItem[values.size()];
        LeaveValue lv = null;
        for(int i=0;i<values.size();i++)
        {
            lv = values.get(i);
            SelectItem si = new SelectItem(lv.code, lv.name);
            items[i] = si;
        }
        return items;
    }

    public void setItems(SelectItem[] items) {
        this.items = items;
    }

    public ArrayList<LeaveValue> getValues() {
        values = new LeaveValueDB().getAll();
        return values;
    }

    public void update()
    {
        ArrayList<LeaveValue> data = (ArrayList<LeaveValue>)dataGrid.getValue();
        for(LeaveValue lv : data)
        {
            System.out.println(lv.name);
        }
    }
    public void save()
    {
        new LeaveValueDB().save(this);
        FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "New Value Saved", ""));
    }

    public void setValues(ArrayList<LeaveValue> values) {
        this.values = values;
    }
    

}
