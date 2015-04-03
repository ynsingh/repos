/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.api.TaxGender;

import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIData;
import javax.faces.context.FacesContext;


/**
 *
 * @author ERP
 */
public class EmployeeSlabHeadConttroler {

    /** Creates a new instance of EmployeeSlabHeadConttroler */
    


    private UIData dataGrid;
    private int genderCode;
    public int getGenderCode() {
        return genderCode;
    }

    private ArrayList<EmployeeSlabDetail> loadGenderSlabValue = new ArrayList<EmployeeSlabDetail>();
    public ArrayList<EmployeeSlabDetail> getLoadGenderSlabValue() {
        // We R here
        System.out.println("DAta Should Be Write Here : Code : "+this.getGenderCode());
        loadGenderSlabValue = new EmployeeSlabDetailDB().loadEmployeeSlabDetails(this.getGenderCode());
        dataGrid.setValue(loadGenderSlabValue);
        return loadGenderSlabValue;
    }

    public void setLoadGenderSlabValue(ArrayList<EmployeeSlabDetail> loadGenderSlabValue) {
        this.loadGenderSlabValue = loadGenderSlabValue;
    }
    public void setGenderCode(int genderCode) {
        this.genderCode = genderCode;
    }

    public UIData getDataGrid() {
        return dataGrid;
    }

    public void setDataGrid(UIData dataGrid) {
        this.dataGrid = dataGrid;
    }

    public void populate()  {
        System.err.println("Reloading ... "+genderCode);
        getLoadGenderSlabValue();
    }

    public void saveGenSlabDetail()
    {
        try
        {
            ArrayList<EmployeeSlabDetail> copyEmpSlDetails = new ArrayList<EmployeeSlabDetail>();
            ArrayList<EmployeeSlabDetail> empSlDetails = (ArrayList<EmployeeSlabDetail>) dataGrid.getValue();
            for(EmployeeSlabDetail empsd : empSlDetails)
            {
                System.out.println("DAta Should Be Write Here : "+empsd.getSlabCode()+" : "+empsd.getSlabeName());
                if(empsd.isSlabSelected())
                {
                    copyEmpSlDetails.add(empsd); 
                }
            }
            Exception ex = new EmployeeSlabDetailDB().save(copyEmpSlDetails, this.getGenderCode());
            FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "Selection Updated", ""));
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    public EmployeeSlabHeadConttroler() {
        
    }
    public void loadGenderSlab()
    {
        try
        {
            getLoadGenderSlabValue();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
