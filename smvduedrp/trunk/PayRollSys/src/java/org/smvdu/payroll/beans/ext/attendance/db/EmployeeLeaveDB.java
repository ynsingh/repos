/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans.ext.attendance.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import org.smvdu.payroll.beans.Employee;
import org.smvdu.payroll.beans.db.CommonDB;
import org.smvdu.payroll.beans.ext.attendance.EmployeeLeave;

/**
 *
 * @author Algox
 */
public class EmployeeLeaveDB {
    private PreparedStatement ps;
    private ResultSet rs;

    public void save(EmployeeLeave el)
    {
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("insert into employee_leave_master(el_emp_code,"
                    + "el_date_from,el_date_to,el_count,el_quota_type) values(?,?,?,?,?)");
            ps.setInt(1, el.getEmpId());
            ps.setString(2, el.getDateFrom());
            ps.setString(3, el.getDateTo());
            ps.setInt(4, el.getCount());
            ps.setInt(5, el.getLeaveTypeCode());
            ps.executeUpdate();
            ps.close();
            c.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public ArrayList<EmployeeLeave> loadLeaves()   {
        try
        {
            Connection c = new CommonDB().getConnection();
            String q = "select el_id,emp_code,emp_name,dept_name,desig_name,"
                    + "date_format(el_date_from,'%d-%M-%y'),date_format(el_date_to,'%d-%M-%y'),"
                    + "el_count,lt_name from employee_leave_master "
                    + "left join employee_master on emp_id=el_emp_code left join "
                    + "department_master on dept_code = emp_dept_code left join "
                    + "designation_master on desig_code = emp_desig_code left join"
                    + " leave_type_master on lt_id =el_quota_type";

            ps=c.prepareStatement(q);
            rs = ps.executeQuery();
            ArrayList<EmployeeLeave> data = new ArrayList<EmployeeLeave>();
            while(rs.next())
            {
                EmployeeLeave el = new EmployeeLeave();
                el.setId(rs.getInt(1));
                Employee emp = new Employee();
                emp.setCode(rs.getString(2));
                emp.setName(rs.getString(3));
                emp.setDeptName(rs.getString(4));
                emp.setDesigName(rs.getString(5));
                el.setEmployee(emp);
                el.setDateFrom(rs.getString(6));
                el.setDateTo(rs.getString(7));
                el.setCount(rs.getInt(8));
                el.setLeaveTypeName(rs.getString(9));
                data.add(el);
            }
            rs.close();
            ps.close();
            c.close();
            return data;

        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

}
