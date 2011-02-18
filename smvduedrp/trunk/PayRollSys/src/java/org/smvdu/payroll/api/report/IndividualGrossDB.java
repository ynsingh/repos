/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.api.report;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import org.smvdu.payroll.beans.db.CommonDB;
import org.smvdu.payroll.beans.db.SalaryHeadDB;

/**
 *
 * @author Algox
 */
public class IndividualGrossDB {

    private PreparedStatement ps;
    private ResultSet rs;



    public static void main(String[] args)
    {
       
    }


    public Integer[][] fetchSalaryMatrix(String empCode) {
        ArrayList<String> sals = new SalaryHeadDB().getApplicableHeads(empCode);
        Integer[][] dataset = new Integer[sals.size()][12];
        String startDate = "2010-4-1";
        int dateOffset = 0;
        try
        {
            Connection c = new CommonDB().getConnection();
            for(int i=0;i<12;i++)
            {                
                ps=c.prepareStatement("select sd_amount,sd_date from salary_head_master "
                + " left join salary_data on sd_head_code =sh_id and sd_emp_code=?"
                + " and month(sd_date)=(select month(adddate(?,?)) ) and year(sd_date) = "
                        + "(select year(adddate(?,?)) )"
                + " where sh_id in (select st_sal_code from emp_salary_head_master "
                + "where st_code =  (select emp_type_code from employee_master "
                + "where emp_code=?) ) order by sh_id ");
                ps.setString(1, empCode);
                ps.setString(2, startDate);
                ps.setInt(3, dateOffset);
                ps.setString(4, startDate);
                ps.setInt(5, dateOffset);
                ps.setString(6, empCode);
                rs=ps.executeQuery();
                int k=0;                
                while(rs.next())
                {
                   dataset[k][i] = rs.getInt(1);
                   k++;
                }
                rs.close();
                ps.close();
                dateOffset+=31;
            }            
            c.close();
            System.out.println("Total Rows : "+dataset.length);
            return dataset;
            
        }

        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }

    }

}
