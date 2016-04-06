/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.smvdu.payroll.beans.composite;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIData;
import javax.faces.context.FacesContext;
import org.smvdu.payroll.beans.setup.TaxCalculationType;
import org.smvdu.payroll.beans.db.TaxCalculationTypeDB;
/**
 *
 * @author shikhar
 */
public class TaxCalculationTypeController {
    
    private TaxCalculationType taxCalcType;
    private UIData dataGrid;
       
    public TaxCalculationTypeController(){
        
    }
     
   public TaxCalculationType getTaxCalcType(){
        taxCalcType=new TaxCalculationTypeDB().loadCalculationType();
        dataGrid.setValue(taxCalcType.getCalcType());
        //System.out.println("taccalctype===="+taxCalcType+"dataGrid===="+dataGrid);
        return taxCalcType;
    }

    public void setTaxCalcType(TaxCalculationType taxCalcType) {
        this.taxCalcType = taxCalcType;
    }
   

    public UIData getDataGrid() {
        return dataGrid;
    }

    public void setDataGrid(UIData dataGrid) {
        this.dataGrid = dataGrid;
    }
   
    public void update(){
        
    try{
        FacesContext fc = FacesContext.getCurrentInstance();
        FacesMessage message = new FacesMessage();
        TaxCalculationType data= (TaxCalculationType)dataGrid.getValue();
        Exception exm = new TaxCalculationTypeDB().update(data);
                if(exm == null)
                {
                        message.setSeverity(FacesMessage.SEVERITY_INFO);
                        message.setSummary("Tax Calculation Type updated sucessfully...");
                        fc.addMessage("", message);
                }
                else
                {
                    message.setSeverity(FacesMessage.SEVERITY_ERROR);
                    message.setSummary("Setup Not Updated.....PleaseTry Again");
                    fc.addMessage(""+exm , message);
                }
                
        }//try
        catch(Exception exm)
        {
            exm.printStackTrace();
        }
    }   
}
