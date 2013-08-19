/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.api.TaxGender;

import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.model.SelectItem;


/**
 *
 * @author ERP
 */
public class EmployeeSlabDetail extends TaxSlabHeadBean{

    /** Creates a new instance of EmployeeSlabDetail */
    public EmployeeSlabDetail() {
    }
    private SelectItem[] items;
    private String name;
    private int code;
    private String slabeName = new String();
     public String getSlabeName()
    {
        return slabeName;
    }
     public void setSlabeName(String slabeName)
    {
        this.slabeName = slabeName;
    }
    private int slabCode;
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
    public SelectItem[] getItems() {
         ArrayList<EmployeeSlabDetail> myTypes=new EmployeeSlabDetailDB().loadGenderDetail();
         System.out.println("DAta Should Be Write Here");
        items = new SelectItem[myTypes.size()];
        for(int i=0;i<myTypes.size();i++)
        {
            EmployeeSlabDetail esd =myTypes.get(i);
            SelectItem si =new SelectItem(esd.code, esd.name);
            items[i] = si;
        }
        return items;
    }

    public void setItems(SelectItem[] items) {
        this.items = items;
    }
    private boolean slabSelected;

    public boolean isSlabSelected() {
        return slabSelected;
    }

    public void setSlabSelected(boolean slabSelected) {
        this.slabSelected = slabSelected;
    }
     @Override
    public String toString()
    {
        return name;
    }
     public int getSlabCode()
    {
         return slabCode;
     }
     public void setSlabCode(int slabCode)
    {
         this.slabCode = slabCode;
     }
}
