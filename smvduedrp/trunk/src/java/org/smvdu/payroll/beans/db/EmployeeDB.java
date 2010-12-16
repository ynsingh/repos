/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import org.smvdu.payroll.beans.Department;
import org.smvdu.payroll.beans.Designation;
import org.smvdu.payroll.beans.Employee;
import org.smvdu.payroll.beans.EmployeeType;
import org.smvdu.payroll.beans.SalaryGrade;

/**
 *
 * @author Algox
 */
public class EmployeeDB {

    private PreparedStatement ps;
    private ResultSet rs;



    public int getTypeCode(String empCode)
    {
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("select emp_type_code from employee_master where emp_code=?");
            ps.setString(1, empCode);
            rs=ps.executeQuery();
            rs.next();
            int code = rs.getInt(1);
            rs.close();
            ps.close();
            c.close();
            return code;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return -1;
        }
    }







    public Employee loadProfile(String s)  {
        try
        {
            Connection c = new CommonDB().getConnection();
            String q = "select emp_code,emp_name,dept_name,desig_name,"
                    + "emp_type_name,emp_id,emp_bank_accno,emp_pf_accno,emp_pan_no,"
                    + " emp_type_code,emp_salary_grade,grd_name,emp_email,emp_dob,emp_doj,emp_phone"
                    + "  from employee_master "
                    + "left join designation_master on desig_code= emp_desig_code  "
                    + "left join department_master on dept_code = emp_dept_code "
                    + " left join employee_type_master on emp_type_id = emp_type_code "
                    + "left join salary_grade_master on grd_code = emp_salary_grade where emp_code=?";
            ps=c.prepareStatement(q);
            System.err.println(q);
            ps.setString(1, s);
            rs=ps.executeQuery();
            Employee emp =null;
            if(rs.next())
            {
                emp= new Employee();
                emp.setCode(rs.getString(1));
                emp.setName(rs.getString(2));
                Department dept = new Department();
                dept.setName(rs.getString(3));
                emp.setDept(dept.getCode());
                emp.setDeptName(dept.getName());
                Designation desig = new Designation();
                desig.setName(rs.getString(4));
                emp.setDesig(desig.getNumber());
                emp.setDesigName(desig.getName());
                EmployeeType et = new EmployeeType();
                et.setName(rs.getString(5));
                et.setCode(rs.getInt(10));
                emp.setType(et.getCode());
                emp.setTypeName(et.getName());
                emp.setEmpId(rs.getInt(6));
                emp.setBankAccNo(rs.getString(7));
                emp.setPfAccNo(rs.getString(8));
                emp.setPanNo(rs.getString(9));
                SalaryGrade sg = new SalaryGrade();
                sg.setCode(rs.getInt(11));
                sg.setName(rs.getString(12));
                emp.setGrade(sg.getCode());
                emp.setBandName(sg.toString());
                emp.setEmail(rs.getString(13));
                emp.setDob(rs.getString(14));
                emp.setDoj(rs.getString(15));
                emp.setPhone(rs.getString(16));
                System.err.println("Email : "+emp.getEmail() + " Pan No "+emp.getPanNo());
            }
            rs.close();
            ps.close();
            c.close();
            return emp;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;

        }
    }

    public boolean codeExist(String code)
    {
        try
        {
           Connection c = new CommonDB().getConnection();
           ps=c.prepareStatement("select emp_name from employee_master where emp_code=?");
           ps.setString(1, code);
           rs=ps.executeQuery();
           rs.next();
           String s = rs.getString(1);
           rs.close();
           ps.close();
           c.close();
           return true;
        }
        catch(Exception e)
        {
            return false;
        }
    }


    public ArrayList<Employee> loadProfiles(String s)  {
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("select emp_code,emp_name,"
                    + " dept_name,desig_name,emp_type_name,emp_id,grd_name,grd_max,grd_min "
                    + " from employee_master left join designation_master on desig_code= emp_desig_code"
                    + " left join department_master on dept_code = emp_dept_code "
                    + " left join employee_type_master on emp_type_id = emp_type_code left join "
                    + "salary_grade_master on grd_code=emp_salary_grade");
            rs=ps.executeQuery();
            ArrayList<Employee> data = new ArrayList<Employee>();
            int k=1;
            while(rs.next())
            {                
                Employee emp = new Employee();
                emp.setCode(rs.getString(1));
                emp.setName(rs.getString(2));
                Department dept = new Department();
                dept.setName(rs.getString(3));
                emp.setDept(dept.getCode());
                emp.setDeptName(dept.getName());
                Designation desig = new Designation();
                desig.setName(rs.getString(4));
                emp.setDesig(desig.getNumber());
                emp.setDesigName(desig.getName());
                EmployeeType et = new EmployeeType();
                et.setName(rs.getString(5));
                emp.setType(et.getCode());
                emp.setTypeName(et.getName());
                emp.setEmpId(rs.getInt(6));
                emp.setSrNo(k);
                SalaryGrade sg = new SalaryGrade();
                sg.setName(rs.getString(7));
                sg.setMaxValue(rs.getInt(8));
                sg.setMinValue(rs.getInt(9));
                emp.setBandName(sg.toString());
                data.add(emp);
                k++;
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



    public boolean update(Employee emp)   {
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("update employee_master set emp_name=?,"
                    + "emp_dept_code=?,emp_desig_code=?,emp_type_code=?,emp_phone=?,"
                    + "emp_email=?,emp_dob=?,emp_doj=?,emp_bank_accno=?,emp_pf_accno=?,emp_pan_no=?,"
                    + "emp_salary_grade=? where emp_code=?");
            ps.setString(1, emp.getName());
            ps.setInt(2, emp.getDept());
            ps.setInt(3, emp.getDesig());
            ps.setInt(4, emp.getType());
            ps.setString(5, emp.getPhone());
            ps.setString(6, emp.getEmail());
            ps.setString(7, emp.getDob());
            ps.setString(8, emp.getDoj());
            ps.setString(9, emp.getBankAccNo());
            ps.setString(10, emp.getPfAccNo());
            ps.setString(11, emp.getPanNo());
            ps.setInt(12, emp.getGrade());
             ps.setString(13, emp.getCode());
            ps.executeUpdate();
            ps.close();
            c.close();
            return true;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }
    public Exception save(Employee emp)   {
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("insert into employee_master(emp_code,emp_name,"
                    + "emp_dept_code,emp_desig_code,emp_type_code,emp_phone,"
                    + "emp_email,emp_dob,emp_doj,emp_bank_accno,emp_pf_accno,emp_pan_no,"
                    + "emp_salary_grade,emp_gender) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            ps.setString(1, emp.getCode());
            ps.setString(2, emp.getName());
            ps.setInt(3, emp.getDept());
            ps.setInt(4, emp.getDesig());
            ps.setInt(5, emp.getType());
            ps.setString(6, emp.getPhone());
            ps.setString(7, emp.getEmail());
            ps.setString(8, emp.getDob());
            ps.setString(9, emp.getDoj());
            ps.setString(10, emp.getBankAccNo());
            ps.setString(11, emp.getPfAccNo());
            ps.setString(12, emp.getPanNo());
            ps.setInt(13, emp.getGrade());
            ps.setBoolean(14, emp.isMale());
            ps.executeUpdate();
            ps.close();
            c.close();
            return null;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return e;
        }
    }

}
