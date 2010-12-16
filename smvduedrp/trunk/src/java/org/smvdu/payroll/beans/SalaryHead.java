/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans;

import java.util.ArrayList;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;
import javax.faces.event.ValueChangeEvent;
import javax.faces.event.ValueChangeListener;
import javax.faces.model.SelectItem;
import org.smvdu.payroll.beans.db.SalaryHeadDB;

/**
 *
 * @author Algox
 */
public class SalaryHead implements ActionListener,ValueChangeListener {


    private int defaultValue;

    public int getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(int defaultValue) {
        this.defaultValue = defaultValue;
    }
    private SelectItem[] items;

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
    
    private String name;
    private String alias;
    public String toString() {
        return name;
    }


    private String underString;
    private String calculationString;

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


    private ArrayList<SalaryHead> heads;

    public ArrayList<SalaryHead> getHeads() {
        return  new SalaryHeadDB().loadAllHeads();
    }

    public void setHeads(ArrayList<SalaryHead> heads) {
        
        this.heads = heads;
    }

    private boolean calculationType;

    public boolean isCalculationType() {
        return calculationType;
    }


    public void save()
    {
        new SalaryHeadDB().save(this);
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

    private int number;

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
        name = string;
    }

    /**
     * @return
     */
    public int getNumber() {
        return number;
    }

    /**
     * @param i
     */
    public void setNumber(int i) {
        number = i;
    }

    /**
     *
     */
    public SalaryHead() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    public void processAction(ActionEvent event) throws AbortProcessingException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void processValueChange(ValueChangeEvent event) throws AbortProcessingException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
