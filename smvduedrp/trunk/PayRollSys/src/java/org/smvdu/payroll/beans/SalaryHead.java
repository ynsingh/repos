/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans;

import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.smvdu.payroll.beans.db.SalaryHeadDB;

/**
 *
 * @author Algox
 */
public class SalaryHead implements Serializable {

    private int defaultValue;
    private int number;
    private String name;
    private String alias;
    private String formula;

    private boolean scalable;

    public boolean isScalable() {
        return scalable;
    }

    public void setScalable(boolean scalable) {
        this.scalable = scalable;
    }
    

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }
    

    private int empType;

    public int getEmpType() {
        return empType;
    }

    @Override
    public boolean equals(Object obj)
    {
        SalaryHead sh = (SalaryHead)obj;
        if(this.number==sh.number)
        {
            return true;
        }
 else
        {
            return false;
 }
    }

    public void setEmpType(int empType) {
        //System.err.println("Emptype : "+empType);
        this.empType = empType;
    }


    public void populate()
    {
        System.err.println("Emptype : "+empType);
        getSelected();
    }
    


    private String underString;
    private boolean calculationType;
    private String calculationString;
    private SelectItem[] items;
    private ArrayList<SalaryHead> heads;

    public int getDefaultValue() {
        return defaultValue;
    }
    public void setDefaultValue(int defaultValue) {
        this.defaultValue = defaultValue;
    }
    public SelectItem[] getItems() {
        ArrayList<SalaryHead> allheads = new SalaryHeadDB().loadAllHeads();
        items = new SelectItem[allheads.size()];
        for(int i=0;i<allheads.size();i++ )
        {
            SalaryHead sh = allheads.get(i);
            SelectItem si = new SelectItem(sh.number, sh.name);
            items[i] = si;
        }
        return items;
    }
    public void print()   {
        if(items==null)
        {
            System.err.println(" >> No Selection");
            return;
        }
         for(SelectItem si : items)
        {
            System.err.print(si.getLabel()+""+si.getValue());
        }
    }
    public void setItems(String[] items) {
         for(String s : items)
         {
             System.err.println(s);
         }
       
    }
    public String toString() {
        return name;
    }
    public String getCalculationString() {
        if(calculationType)
        {
            return "Consolidated";
        }
        else
        {
            return "Formula";
        }
    }
    public String getUnderString() {
       if(under)
        {
            return "Earning";
        }
 else
        {
            return "Deduction";
 }
    }
    public ArrayList<SalaryHead> getSelected() {        
        return  new SalaryHeadDB().loadSelectedHeads(number);
    }
    public ArrayList<SalaryHead> getHeads() {
        return  new SalaryHeadDB().loadAllHeads();
    }
    public void setHeads(ArrayList<SalaryHead> heads) {
        
        this.heads = heads;
    }
    public boolean isCalculationType() {
        return calculationType;
    }
    public void save()   {
        Exception e = new SalaryHeadDB().save(this);
        if(e==null)
        {
            FacesContext.getCurrentInstance().addMessage("", new FacesMessage("Salary Head Saved : "+name, "Data Saved."));
        }
    }
    public void setCalculationType(boolean calculationType) {
        this.calculationType = calculationType;
    }
    public String getAlias() {
        return alias;
    }
    public void setAlias(String alias) {
        this.alias = alias;
    }
    public boolean isUnder() {
        return under;
    }
    public void setUnder(boolean under) {
        this.under = under;
    }
    private  boolean under;
    private String error = " <font color='green'>* Salary Head name Cannot be empty </font>";
    private String message ="";
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
    public String getName() {
        return name;
    }
    public void setName(String string) {
        name = string;
    }
    public int getNumber() {
        return number;
    }
    public void setNumber(int i) {        
        number = i;
    }

    
}
