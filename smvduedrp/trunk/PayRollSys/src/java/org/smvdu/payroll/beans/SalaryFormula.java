/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans;

import java.util.ArrayList;
import javax.faces.component.UIData;
import org.smvdu.payroll.beans.db.SalaryFormulaDB;

/**
 *
 *  *  Copyright (c) 2010 - 2011 SMVDU, Katra.
*  All Rights Reserved.
**  Redistribution and use in source and binary forms, with or 
*  without modification, are permitted provided that the following 
*  conditions are met: 
**  Redistributions of source code must retain the above copyright 
*  notice, this  list of conditions and the following disclaimer. 
* 
*  Redistribution in binary form must reproduce the above copyright
*  notice, this list of conditions and the following disclaimer in 
*  the documentation and/or other materials provided with the 
*  distribution. 
* 
* 
*  THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED 
*  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES 
*  OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE 
*  DISCLAIMED.  IN NO EVENT SHALL SMVDU OR ITS CONTRIBUTORS BE LIABLE 
*  FOR ANY DIRECT, INDIRECT, INCIDENTAL,SPECIAL, EXEMPLARY, OR 
*  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT 
*  OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR 
*  BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
*  WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE 
*  OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, 
*  EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE. 
* 
* 
*  Contributors: Members of ERP Team @ SMVDU, Katra
*
 */
public class SalaryFormula {


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
   
    
}
