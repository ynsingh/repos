/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import org.smvdu.payroll.beans.EmployeeTypeSalaryHead;
import org.smvdu.payroll.beans.SalaryHead;

/**
 *
 * @author Algox
 */
public class EmployeeTypeSalaryHeadDB {

    private PreparedStatement ps;
    private ResultSet rs;
    public void save(ArrayList<EmployeeTypeSalaryHead> data,int type)
    {
       try
       {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("delete from emp_salary_head_master where st_code=?");
            ps.setInt(1, type);
            ps.executeUpdate();
            ps.close();
            ps=c.prepareStatement("insert into emp_salary_head_master values(?,?)");
            for(EmployeeTypeSalaryHead sh : data)
            {
                ps.setInt(1, type);
                ps.setInt(2, sh.getNumber());
                ps.executeUpdate();
                ps.clearParameters();
            }
            ps.close();
            c.close();
       }
       catch(Exception e)
       {
           e.printStackTrace();
       }
    }

   public ArrayList<EmployeeTypeSalaryHead> loadHeads(int type) {
       ArrayList<EmployeeTypeSalaryHead> allheads = new ArrayList<EmployeeTypeSalaryHead>();
       ArrayList<SalaryHead> selected = new SalaryHeadDB().loadAppliedHeads(type);
       for(SalaryHead sh : new SalaryHeadDB().loadAllHeads())
       {
           EmployeeTypeSalaryHead esh = new EmployeeTypeSalaryHead();
           esh.setNumber(sh.getNumber());
           esh.setCalculationType(sh.isCalculationType());
           esh.setUnder(sh.isUnder());
           esh.setName(sh.getName());
           if(selected.contains(sh))
           {
               esh.setSelected(true);
           }
           allheads.add(esh);
       }
       return allheads;
       
    }

}
