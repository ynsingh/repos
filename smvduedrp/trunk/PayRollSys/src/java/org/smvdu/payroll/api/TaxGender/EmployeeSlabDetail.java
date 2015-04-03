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
 //   public class EmployeeSlabDetail extends TaxSlabHeadBean {
public class EmployeeSlabDetail implements Serializable {

    /** Creates a new instance of EmployeeSlabDetail */
    public EmployeeSlabDetail() {
    }
   
   
    private int id;                 // this is the primary key of the database table
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    private int orgCode;            // this is used to store the values of orgCode in the database table
    public int getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(int orgCode) {
        this.orgCode = orgCode;
    }
    
    private Gender gendercode;      // this is used to store the values of gendercode in the database table
    public Gender getGendercode() {
        return gendercode;
    }

    public void setGendercode(Gender gendercode) {
        this.gendercode = gendercode;
    }

    private TaxSlabHeadBean slabCode;         // this is used to store the values of slabCode in the database table
    public TaxSlabHeadBean getSlabCode() {
        return slabCode;
    }

    public void setSlabCode(TaxSlabHeadBean slabCode) {
        this.slabCode = slabCode;
    }
        
    private int code;
    
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    
    private String slabeName = new String();  // this is used to store the Slab Name .
  
    public String getSlabeName() {
            return slabeName;
    }
     
     public void setSlabeName(String slabeName)
    {
        this.slabeName = slabeName;
    }
    
    private String name;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
    private SelectItem[] items;             // this is responsile for the dropdown of the gender.
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
    
    
    private boolean slabSelected;       // this is responsible for the checkboxes
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
    
    
    
}
