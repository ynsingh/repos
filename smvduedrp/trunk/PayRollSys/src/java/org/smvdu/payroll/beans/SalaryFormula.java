/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans;

import java.util.ArrayList;
import java.util.List;
import javax.faces.component.UIData;
import org.richfaces.model.SortOrder;
import org.richfaces.model.impl.SimpleGridDataModel;
import org.smvdu.payroll.beans.db.SalaryFormulaDB;

/**
 *
 * @author Algox
 */
public class SalaryFormula  extends SimpleGridDataModel{


    public SalaryFormula()
    {
        
    }
    
    private String name;
    private String formula;
    private int salCode = 0;
    private String fields;

    public String getFields() {
        return fields;
    }

    public void setFields(String fields) {
        this.fields = fields;
    }
    
    private UIData data;

    public UIData getData() {
        return data;
    }

    public void setData(UIData data) {
        this.data = data;
    }
    

    private ArrayList<SalaryFormula> formulas ;
    public String getFormula() {
       return formula;
    }

    public ArrayList<SalaryFormula> getFormulas() {
        formulas = new SalaryFormulaDB().loadFormula();
        return formulas;
    }

    public void setFormulas(ArrayList<SalaryFormula> formulas) {
        this.formulas = formulas;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }
    

    
    

    public int getSalCode() {
        return salCode;
    }

    public void setSalCode(int salCode) {
        this.salCode = salCode;
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

    public void save()
            {
        ArrayList<SalaryFormula> sds =(ArrayList<SalaryFormula>) data.getValue();
        for(SalaryFormula sd : sds)
        {
            System.err.println(sd.getName()+" : "+sd.getFormula());
        }
        new SalaryFormulaDB().save(data);
    }
   

   




    @Override
    public List loadData(int i, int i1, SortOrder so) {
        return formulas;
    }

    @Override
    public int getRowCount() {
        return formulas.size();
    }

    @Override
    public Object getWrappedData() {
        return new SalaryData();
    }

    @Override
    public void setWrappedData(Object o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    
}
