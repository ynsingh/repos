/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans.setup;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
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
 *     Copyright (c) 2014 - 2017 ETRG, IITK. 
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
*  Modified Date: 13 Nov 2014, IITK (palseema30@gmail.com, kishore.shuklak@gmail.com)
*  Last Modification :(Salary Processing with Budgets), January 2017, Manorama Pal (palseema30@gmail.com).
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


    
    private int number;
    
    public int getNumber() {
        return number;
    }
    
    public void setNumber(int i) {        
        this.number = i;
    }
    
    
    private String shcode;
    
    public String getShcode() {
        return shcode;
    }
    
    public void setShcode(String shcode) {
        this.shcode = shcode;
    }
    
    
    private String name;
    
    public String getName() {
        return name;
    }
    
    public void setName(String string) {
        this.name = string;
    }
    
    
    private  boolean under;
    
    public boolean isUnder() {
        return under;
    }
    
    public void setUnder(boolean under) {
        this.under = under;
    }
   
    
    private String alias;
    
    public String getAlias() {
        return alias;
    }
    
    public void setAlias(String alias) {
        this.alias = alias;
    }
    
    private boolean calculationType;
    
    public boolean isCalculationType() {
        return calculationType;
    }
    
    public void setCalculationType(boolean calculationType) {
        this.calculationType = calculationType;
    }
    
        
    private String formula;
    
    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }
    
    
    private boolean scalable;

    public boolean isScalable() {
        return scalable;
    }

    public void setScalable(boolean scalable) {
        this.scalable = scalable;
    }
    
    
    private boolean special;
    
    public boolean isSpecial() {
        return special;
    }

    public void setSpecial(boolean special) {
        this.special = special;
    }
    
    
    private boolean type;
    
    public boolean isType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }
    
  
    private boolean display;
    
    public boolean isDisplay() {
        return display;
    }

    public void setDisplay(boolean display) {
        this.display = display;
    }
    
    private SalaryTypeMaster typeCode;

    public SalaryTypeMaster getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(SalaryTypeMaster typeCode) {
        this.typeCode = typeCode;
    }
    
    
    private int typeCodeForDropDown;

    public int getTypeCodeForDropDown() {
        return typeCodeForDropDown;
    }

    public void setTypeCodeForDropDown(int typeCodeForDropDown) {
        this.typeCodeForDropDown = typeCodeForDropDown;
    }
    
    private String bgasLedger;
    public String getBgasLedger(){
        System.out.println("bgasledger==="+bgasLedger);
        return bgasLedger;
    }

    public void setBgasLedger(String bgasLedger) {

        this.bgasLedger = bgasLedger;
    }
    
    private boolean processType; // Regular or scheduled
    
    public boolean isProcessType() {
        return processType;
    }

    public void setProcessType(boolean processType) {
        this.processType = processType;
    }
    
    
    private String ledgerCode;
    
    public String getLedgerCode() {
        return ledgerCode;
    }
    
    public void setLedgerCode(String ledgercode) {
        this.ledgerCode = ledgercode;
    }
 
    private int orgcode;

    public int getOrgcode() {
        return orgcode;
    }

    public void setOrgcode(int orgcode) {
        this.orgcode = orgcode;
    }
    
    
    

//--------------------------------------------------------------------------------------------------------------------------------------
//--------------------------------------------------------------------------------------------------------------------------------------
    

    private Set defaultSalary = new HashSet();

    public Set getDefaultSalary() {
        return defaultSalary;
    }

    public void setDefaultSalary(Set defaultSalary) {
        this.defaultSalary = defaultSalary;
    }
    
    
    private Set salaryFormula = new HashSet();

    public Set getSalaryFormula() {
        return salaryFormula;
    }

    public void setSalaryFormula(Set salaryFormula) {
        this.salaryFormula = salaryFormula;
    }
    
    private Set employeeTypeSalary = new HashSet();

    public Set getEmployeeTypeSalary() {
        return employeeTypeSalary;
    }

    public void setEmployeeTypeSalary(Set employeeTypeSalary) {
        this.employeeTypeSalary = employeeTypeSalary;
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
    
    private SalaryTypeMaster salaryType;

    public SalaryTypeMaster getSalaryType() {
        return salaryType;
    }

    public void setSalaryType(SalaryTypeMaster salaryType) {
        this.salaryType = salaryType;
    }

    public SalaryHead(String name)
    {
        this.name = name;
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
        this.empType = empType;
    }


    public void populate()
    {
        getSelected();
    }
    


    private String underString;
    
    private String calculationString;
    private SelectItem[] items;
    private ArrayList<SalaryHead> heads;
    
    private int defaultValue;

    public int getDefaultValue() {
        return defaultValue;
    }
    public void setDefaultValue(int defaultValue) {
        this.defaultValue = defaultValue;
    }
    public SelectItem[] getItems() {
        ArrayList<SalaryHead> allheads = new SalaryHeadDB().loadAllHeads();
        items = new SelectItem[allheads.size()];
        SalaryHead sh=null;
        for(int i=0;i<allheads.size();i++ )
        {
            sh = allheads.get(i);
            SelectItem si = new SelectItem(sh.number,sh.name);
            items[i] = si;
        }
        return items;
    }
    public void print()   {
        if(items==null)
        {
            return;
        }
        for(SelectItem si : items)
        {
        }
    }
    public void setItems(String[] items) {
         for(String s : items)
         {
            // System.err.println(s);
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
       System.out.println("number of emptype====="+number);
        return  new SalaryHeadDB().loadSelectedHeads(number);
        
    }
    public ArrayList<SalaryHead> getHeads() {
       
        return  new SalaryHeadDB().loadAllHeads();
        
    }
    public void setHeads(ArrayList<SalaryHead> heads) {
        
        this.heads = heads;
    }
    
    public void save()   {
         FacesContext fc = FacesContext.getCurrentInstance();
        if (this.getShcode().matches("^[a-zA-Z0-9\\s]*$") == false) {
            FacesMessage message = new FacesMessage();
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            message.setSummary("Please Enter Valid Code. No Special Characters are Allowed ");
            //message.setDetail("First Name Must Be At Least Three Charecter ");
            fc.addMessage("", message);
            return;
            } 
        if (this.getName().matches("^[a-zA-Z\\s]*$") == false) {
            FacesMessage message = new FacesMessage();
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            message.setSummary("Plz Enter Valid Salary Head Name");
            //message.setDetail("First Name Must Be At Least Three Charecter ");
            fc.addMessage("", message);
            return;
        }
        if (this.getAlias().matches("^[a-zA-Z\\s]*$") == false) {
            FacesMessage message = new FacesMessage();
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            message.setSummary("Plz Enter Valid Short Name");
            fc.addMessage("", message);
            return;
        }
        Exception e = new SalaryHeadDB().save(this);
        if(e==null)
        {
            FacesContext.getCurrentInstance().addMessage("", new FacesMessage("Salary Head Saved : "+name, "Data Saved."));
        }
        else{
            FacesContext.getCurrentInstance().addMessage("", new FacesMessage("Somthing went wrong"));
        }
    }
    
    
    
    
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
    }

    public void delete()
    {
        String code = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("code");
       
    }


    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    
    private String ledgerName;
    
    public String getLedgerName() {
        return ledgerName;
    }
    
    public void setLedgerName(String ledgername) {
        this.ledgerName = ledgername;
    }
    
    String coaformat=null;
    public String getCoaFormat(){
        System.out.println("coaformat======heads====="+coaformat);
        return coaformat;
    }

    public void setCoaFormat(String coaformat) {
       this.coaformat = coaformat;
    }
    
    
   
}
