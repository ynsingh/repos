/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans;

import java.util.ArrayList;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ValueChangeEvent;
import javax.faces.event.ValueChangeListener;
import org.smvdu.payroll.beans.db.SalaryDataDB;

/**
 *
 * @author Algox
 */
public class SalaryData implements ValueChangeListener{

    private int headCode;
    private String headName;
    private int headValue;
    private Employee employee;

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
        
        
    }
    public void setAllData(ArrayList<SalaryData> allData) {
        this.allData = allData;
    }

    @Override
    public void processValueChange(ValueChangeEvent event) throws AbortProcessingException {        
        System.err.println(event.getOldValue()+" New value "+event.getNewValue());
    }

}
