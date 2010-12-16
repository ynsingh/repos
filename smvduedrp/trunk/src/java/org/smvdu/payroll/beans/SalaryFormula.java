/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans;

import java.util.ArrayList;
import org.smvdu.payroll.beans.db.SalaryFormulaDB;

/**
 *
 * @author Algox
 */
public class SalaryFormula  {
    
    private String name;
    private String formula;


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
    

    
    private int salCode = 0;

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
        new SalaryFormulaDB().save(this);
    }
   

   



    public SalaryFormula() {
        super();
        // TODO Auto-generated constructor stub
    }

    
}
