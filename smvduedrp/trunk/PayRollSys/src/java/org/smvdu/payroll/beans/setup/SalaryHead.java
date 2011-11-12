/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans.setup;

import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.smvdu.payroll.beans.SalaryData;
import org.smvdu.payroll.beans.SalaryTypeMaster;
import org.smvdu.payroll.beans.db.SalaryHeadDB;

/**
 * salary head is a single salary component like BASIC, DA etc.
 *  type determines wheather it is Income or Deduction and processingType
 * determines Consolidate or Formula based processing.
 * Salary Data, that represents an employees Salary details for a month
 * is composed of an array of salary heads.
 * FormulaProcessor operates on Salary heads to calculate Formula related Fields.
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
public class SalaryHead implements Serializable {




    public SalaryHead()
    {

    }
    /**
     * 
     * @param sd : Salary data an be mapped to salary heads.
     * Both are almost same except for backward compatibility.
     * Avoid using Salarydata , as it is planned to be deprecated in v2
     */



     public SalaryHead(SalaryData sd)
    {
       
        //this.setName(sd.getHeadName());
        name = sd.getHeadName();
        under = sd.isCatagory();
        number = sd.getHeadCode();
        defaultValue = sd.getHeadValue();
        formula = sd.getFormula();
        alias= sd.getAlias();
        scalable = sd.isScalable();
        display = sd.isDisplay();
    }

    public static final int BASIC_SALARY=1;

    private int defaultValue;
    private int number;
    private String name;
    private String alias;
    private String formula;
    private boolean special;
    private boolean type;
    private boolean display;
    private boolean processType; // Regular or scheduled

    public boolean isProcessType() {
        return processType;
    }

    public void setProcessType(boolean processType) {
        this.processType = processType;
    }

    private String processTypeName;

    public String getProcessTypeName() {
        if(!processType)
        {
            processTypeName = "Regular";
        }
 else
        {
            processTypeName = "Scheduled";
 }
        return processTypeName;
    }

    public void setProcessTypeName(String processTypeName) {
        this.processTypeName = processTypeName;
    }
    
    
    
    private int typeCode;
    private SalaryTypeMaster salaryType;

    public SalaryTypeMaster getSalaryType() {
        return salaryType;
    }

    public void setSalaryType(SalaryTypeMaster salaryType) {
        this.salaryType = salaryType;
    }

    public int getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(int typeCode) {
        this.typeCode = typeCode;
    }
    

    public boolean isDisplay() {
        return display;
    }

    public void setDisplay(boolean display) {
        this.display = display;
    }
    
    
   
    public SalaryHead(String name)
    {
        this.name = name;
    }

    public boolean isType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }
    

    public boolean isSpecial() {
        return special;
    }

    public void setSpecial(boolean special) {
        this.special = special;
    }
    

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

    public void update()
    {
        String code = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("code");
        System.err.println("Got delete Code "+code);
    }

    public void delete()
    {
        String code = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("code");
        System.err.println("Got delete Code "+code);
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
