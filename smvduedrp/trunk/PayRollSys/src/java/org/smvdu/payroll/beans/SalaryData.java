/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans;

import java.util.ArrayList;
//import java.util.List;
//import javax.faces.application.Application;
//import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlPanelGrid;
//import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ValueChangeEvent;
import javax.faces.event.ValueChangeListener;
import org.smvdu.payroll.beans.db.SalaryDataDB;

/**
 *
 * @author Algox
 */
public class SalaryData extends SalaryHead implements ValueChangeListener{

    private int headCode;
    private boolean catagory;
    private String headName;
    private int headValue;
    private Employee employee;
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
    

    private HtmlPanelGrid grid;

    public HtmlPanelGrid getGrid() {
        return grid;
    }

    public void setGrid(HtmlPanelGrid grid) {
        this.grid = grid;
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
