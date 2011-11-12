/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans;

import org.smvdu.payroll.beans.setup.SalaryHead;
import java.util.ArrayList;

import javax.faces.event.AbortProcessingException;
import javax.faces.event.ValueChangeEvent;
import javax.faces.event.ValueChangeListener;
import org.smvdu.payroll.beans.db.SalaryDataDB;

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
public class SalaryData extends SalaryHead implements ValueChangeListener{

    private int headCode;
    private boolean catagory;
    private String headName;
    private int headValue;
    private Employee employee;


    public SalaryData()
    {

    }
    public SalaryData(SalaryHead sh)
    {
        setName(sh.getName());
        setNumber(sh.getNumber());
        setAlias(sh.getAlias());
        setFormula(sh.getFormula());
        setCatagory(sh.isUnder());
        setScalable(sh.isScalable());
        setHeadValue(sh.getDefaultValue());
        
    }


    public boolean isCatagory() {
        return catagory;
    }

    public void setCatagory(boolean catagory) {
        this.catagory = catagory;
    }

    
    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
    

    

    public int getHeadCode() {
        return headCode;
    }

    public void setHeadCode(int headCode) {
        this.headCode = headCode;
    }

    public String getHeadName() {
        return headName;
    }

    public void setHeadName(String headName) {
        this.headName = headName;
    }

    public int getHeadValue() {
        return headValue;
    }

    public void setHeadValue(int headValue) {
        this.headValue = headValue;
    }

    private ArrayList<SalaryData> allData= null;

    public ArrayList<SalaryData> getAllData() {
        allData = new SalaryDataDB().loadRawData();
        return allData;
    }


    public void print()
    {
  /*      Application app = FacesContext.getCurrentInstance().getApplication();
        List list = grid.getChildren();
        for(int i=0;i<list.size();i++)
        {
            try
            {
                HtmlInputText it = (HtmlInputText)app.createComponent(HtmlInputText.COMPONENT_TYPE);
                int x = (Integer)it.getValue();
                System.out.println("Value : "+x);
            }
            catch(Exception e)
            {

            }
        }
    */    
    }
    public void setAllData(ArrayList<SalaryData> allData) {
        this.allData = allData;
    }

    @Override
    public void processValueChange(ValueChangeEvent event) throws AbortProcessingException {        
        System.err.println(event.getOldValue()+" New value "+event.getNewValue());
    }

}
