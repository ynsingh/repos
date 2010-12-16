/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans;

import java.util.ArrayList;
import org.smvdu.payroll.beans.db.SalaryHeadDB;

/**
 *
 * @author Algox
 */
public class WorkingSalary {

    public ArrayList<SalaryHead> loadEmpHeads(int sg)
    {
        System.err.println("Loading Applicable Salary Heads ... for "+sg);
        ArrayList<SalaryHead> data = new SalaryHeadDB().loadSelectedHeads(sg);
        if(data==null)
        {
            data = new ArrayList<SalaryHead>();
        }
        return data;
    }

    public ArrayList<SalaryHead> loadEmpHeadsDeduct(int sg)
    {
        //System.err.println("Loading Applicable Salary Heads ... for "+sg.getName());
        ArrayList<SalaryHead> data = new SalaryHeadDB().loadSelectedDeductionHeads(sg);
        if(data==null)
        {
            data = new ArrayList<SalaryHead>();
        }
        return data;
    }
    public ArrayList<SalaryHead> loadEmpHeadsIncome(int sg)
    {
        
        ArrayList<SalaryHead> data = new SalaryHeadDB().loadSelectedIncomeHeads(sg);
        if(data==null)
        {
            data = new ArrayList<SalaryHead>();
        }
        return data;
    }



    public void save(ArrayList<SalaryHead> heads, int code)
    {
        new SalaryHeadDB().updateDefault(heads, code);
    }

}
