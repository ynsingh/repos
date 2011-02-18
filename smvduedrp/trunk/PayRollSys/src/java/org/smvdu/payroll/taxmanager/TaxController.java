/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.taxmanager;

import java.util.ArrayList;
import javax.faces.component.UIData;

/**
 *
 * @author Algox
 */
public class TaxController {
    
    
    private UIData datagrid;
    private String empId;

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }
    
    private ArrayList<TaxCalculatorBean> taxBeans;

    public UIData getDatagrid() {
        return datagrid;
    }

    public void setDatagrid(UIData datagrid) {
        this.datagrid = datagrid;
    }

    public ArrayList<TaxCalculatorBean> getTaxBeans() {
        taxBeans = new TaxCalculatorDB().loadAnnualStat(empId);
        return taxBeans;
    }

    public void setTaxBeans(ArrayList<TaxCalculatorBean> taxBeans) {
        this.taxBeans = taxBeans;
    }

    



}
