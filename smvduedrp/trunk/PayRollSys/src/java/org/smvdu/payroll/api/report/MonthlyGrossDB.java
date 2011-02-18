/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.api.report;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.smvdu.payroll.beans.Employee;
import org.smvdu.payroll.beans.db.CommonDB;
import org.smvdu.payroll.beans.db.EmployeeDB;

/**
 *
 * @author Algox
 */
public class MonthlyGrossDB {
    private PreparedStatement ps;
    private ResultSet rs;

//    public int[][] getMonthlyRoll(int typeCode,String date)
//    {
//        String[] ds = date.split("-");
//        int year = Integer.parseInt(ds[0]);
//        int month = Integer.parseInt(ds[0]);
//        try
//        {
//            Connection c = new CommonDB().getConnection();
//            for(Employee emp : new EmployeeDB().loadProfiles(" where emp_type_code = "+typeCode))
//            {
//            ps=c.prepareStatement("select sd_amount from salary_head_master "
//                + " left join salary_data on sd_head_code =sh_id and sd_emp_code=?"
//                + " and month(sd_date)=? and year(sd_date) =? "
//                + " where sh_id in (select st_sal_code from emp_salary_head_master "
//                + "where st_code =  (select emp_type_code from employee_master "
//                + "where emp_code=?) ) order by sh_id ");
//
//            }
//
//        }
//        catch(Exception e)
//        {
//
//        }
//    }



}
