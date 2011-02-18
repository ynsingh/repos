/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.smvdu.payroll.api.report;

import java.util.ArrayList;
import org.richfaces.component.UIDataTable;
import org.smvdu.payroll.beans.SalaryHead;
import org.smvdu.payroll.beans.db.SalaryHeadDB;



/**
 *
 * @author Algox
 */
public class MonthlyGrossController {

    private int typeCode;

    private UIDataTable table;

    public UIDataTable getTable() {

        return table;
    }

    public void setTable(UIDataTable table) {
        this.table = table;
    }





    public int getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(int typeCode) {
        this.typeCode = typeCode;
    }

    private ArrayList<String> heads;

    public ArrayList<String> getHeads() {
        heads = new SalaryHeadDB().getApplicableHeads("EMP001");
        return heads;
    }

    public void setHeads(ArrayList<String> heads) {
        this.heads = heads;
    }


    private ArrayList<SalaryHead> applicableHeads;

    public ArrayList<SalaryHead> getApplicableHeads() {
        System.out.println("Loading applicable heads ...");
        applicableHeads = new SalaryHeadDB().loadAppliedHeads(1);
        System.out.println("applicable heads ..."+applicableHeads.size());
        for(SalaryHead sh : applicableHeads)
        {
            System.out.println(sh.getName());
        }
        return applicableHeads;
    }

    public void setApplicableHeads(ArrayList<SalaryHead> applicableHeads) {
        this.applicableHeads = applicableHeads;
    }


   
    

    

    
}
