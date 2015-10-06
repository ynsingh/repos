/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans;

import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIData;
import javax.faces.context.FacesContext;
import org.smvdu.payroll.beans.db.SalaryFormulaDB;
import org.smvdu.payroll.beans.setup.SalaryHead;

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
    private int salCode;
    private String fields;
    private int number;
   // private String sfCode;
    //private int shval;
    //private String effectiveDate;
    //private String entryDate;

    public int getNumber(){
        return number;
    }
    
    public void setNumber(int number) {        
        this.number = number;
    }
    
    
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    private int orgcode;

    public int getOrgcode() {
        return orgcode;
    }

    public void setOrgcode(int orgcode) {
        this.orgcode = orgcode;
    }
    
    private SalaryHead salaryHead;

    public SalaryHead getSalaryHead() {
        return salaryHead;
    }

    public void setSalaryHead(SalaryHead salaryHead) {
        this.salaryHead = salaryHead;
    }

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

    
    
     @Override
    public String toString()
    {
        return name;
    }

   /* public int getSalVal() {
        return shval;
    }

    public void setSalVal(int shval) {
        this.shval = shval;
    }

    public String getSFCode() {
        return sfCode;
    }

    public void setSFCode(String sfCode) {
        this.sfCode = sfCode;

    }


    public String getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;

    }

     public String getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(String entryDate) {
        this.entryDate = entryDate;

    }*/

      int currentRecordindex;
    public int getCurrentRecordindex() {

        return currentRecordindex;
    }

    public void setCurrentRecordindex(int currentRecordindex) {

        this.currentRecordindex = currentRecordindex;
    }

    SalaryFormula editedRecord;
    public SalaryFormula getEditedRecord() {

        return editedRecord;
    }

    public void setEditedRecord(SalaryFormula editedRecord) {
        this.editedRecord = editedRecord;
    }

    public void save(){
        /*ArrayList<SalaryFormula> sds =(ArrayList<SalaryFormula>) data.getValue();
        System.out.println("sds===="+sds);
        for(SalaryFormula sd : sds)
        {
            System.err.println(sd.getName()+" : "+sd.getFormula());
        }*/
        /*This method is used to add and update formula of selected head
        * @param editedRecord (SalaryFomula) deatil of selected Head
        * @see SalaryFormulaDB().save method
        * @return boolean
        */
        boolean sf = new SalaryFormulaDB().save(editedRecord);
        if(sf){
            FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, " Formula Updated successfully ", ""));
        }
        else{
            FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, " Formula  not Updated ", ""));
            
        }

    }
   
    
}
