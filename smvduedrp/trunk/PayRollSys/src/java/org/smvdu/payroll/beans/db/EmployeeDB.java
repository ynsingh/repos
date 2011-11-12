/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.faces.context.FacesContext;
import org.smvdu.payroll.beans.setup.Department;
import org.smvdu.payroll.beans.setup.Designation;
import org.smvdu.payroll.beans.Employee;
import org.smvdu.payroll.beans.SimpleEmployee;
import org.smvdu.payroll.beans.setup.EmployeeType;
import org.smvdu.payroll.beans.setup.SalaryGrade;
import org.smvdu.payroll.beans.UserInfo;
import org.smvdu.payroll.user.ActiveProfile;

/**
 *
 *  *  Copyright (c) 2010 - 2011 SMVDU, Katra.
*  All Rights Reserved.
**  Redistribution and use in source and binary forms, with or 
*  without modification, are permitted provided that the following 
*  conditions are met: 
**  Redistributions of source code must retain the above copyright 
*  notice, this  list of conditions and the following disclaimer. 
* 
*  Redistribution in binary form must reproduce the above copyright
*  notice, this list of conditions and the following disclaimer in 
*  the documentation and/or other materials provided with the 
*  distribution. 
* 
* 
*  THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED 
*  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES 
*  OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE 
*  DISCLAIMED.  IN NO EVENT SHALL SMVDU OR ITS CONTRIBUTORS BE LIABLE 
*  FOR ANY DIRECT, INDIRECT, INCIDENTAL,SPECIAL, EXEMPLARY, OR 
*  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT 
*  OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR 
*  BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
*  WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE 
*  OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, 
*  EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE. 
* 
* 
*  Contributors: Members of ERP Team @ SMVDU, Katra
*
 */
public class EmployeeDB {
    private int orgCode=0;
    private UserInfo uf =null;
    private int status;
    

   
    private String url;
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    public EmployeeDB()  {
        uf = (UserInfo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserBean");
        orgCode = uf.getUserOrgCode();
    }

    private PreparedStatement ps;
    private ResultSet rs;



   public ArrayList<SimpleEmployee> loadPendingEmployee()
    {
       try
       {
           Connection c = new CommonDB().getConnection();
           ps=c.prepareStatement("select emp_code,emp_name from employee_master where "
                   + " emp_code not in (select es_code from employee_salary_summery where "
                   + "es_month=  ? and es_year=?) and emp_org_code=?");
           ps.setInt(1, uf.getCurrentMonth());
           ps.setInt(2, uf.getCurrentYear());
           ps.setInt(3, orgCode);
           rs = ps.executeQuery();
           ArrayList<SimpleEmployee> data = new ArrayList<SimpleEmployee>();
           while(rs.next())
           {
               SimpleEmployee se = new SimpleEmployee();
               se.setCode(rs.getString(1));
               se.setName(rs.getString(2));
               data.add(se);
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

    public boolean deactivate(String s)
    {
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("update employee_master set emp_active=0 where emp_code=?");
            ps.setString(1, s);
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

    public int getTypeCode(String empCode)    {
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("select emp_type_code from employee_master "
                    + "where emp_code=?");
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
    public Employee loadProfile(String empCode,int orgId)  {
        try
        {
            Connection c = new CommonDB().getConnection();
            String q = "select emp_code,emp_name,dept_name,desig_name,"
                    + "emp_type_name,emp_id,emp_bank_accno,emp_pf_accno,emp_pan_no,"
                    + " emp_type_code,emp_salary_grade,grd_name,emp_email,emp_dob,"
                    + "emp_doj,emp_phone,emp_father,emp_dept_code,emp_desig_code,emp_title,"
                    + "emp_exp,emp_qual,emp_yop,emp_prev_emp,emp_address,emp_basic,grd_gp,emp_active,emp_gender "
                    + "from employee_master "
                    + "left join designation_master on desig_code= emp_desig_code  "
                    + "left join department_master on dept_code = emp_dept_code "
                    + " left join employee_type_master on emp_type_id = emp_type_code "
                    + "left join salary_grade_master on grd_code = emp_salary_grade "
                    + " where emp_code=? and emp_org_code=?";
            ps=c.prepareStatement(q);
            System.err.println(">>>>>>>  "+q + empCode+"Org ID "+orgCode);
            ps.setString(1, empCode);
            ps.setInt(2, orgCode);
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
                emp.setDesig(desig.getCode());
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
                emp.setFatherName(rs.getString(17));
                emp.setDept(rs.getInt(18));
                emp.setDesig(rs.getInt(19));
                emp.setTitle(rs.getString(20));
                emp.setExperience(rs.getInt(21));
                emp.setQualification(rs.getString(22));
                emp.setYearOfPassing(rs.getInt(23));
                emp.setPreviousEmployer(rs.getString(24));
                emp.setAddress(rs.getString(25));
                emp.setCurrentBasic(rs.getInt(26));
                emp.setGradePay(rs.getInt(27));
                if(rs.getInt(28) == 1)
                {
                    emp.setStstus(true);
                }
                else
                {
                    emp.setStstus(false);
                }
                emp.setGender(rs.getInt(29)); 
                this.setStatus(rs.getInt(28)); 
                System.err.println("Grade pay : "+emp.getGradePay()+"Email : "+emp.getEmail() + " Pan No "+emp.getPanNo()+" Status : "+emp.getStstus());
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

    public String getStatusImage(int status)
    {
        try
        {
            if(status == 1)
            {
                return "/img/success.png";
            }
            else
            {
                return "/img/err.png";
            }
        }
        catch(Exception ex)
        {
            System.out.println("Error : "+ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    }
    public boolean codeExist(String code)    {
        try
        {
           Connection c = new CommonDB().getConnection();
           ps=c.prepareStatement("select emp_name from employee_master where "
                   + "emp_code=? and emp_org_code=?");
           ps.setString(1, code);
           ps.setInt(2, orgCode);
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
            String q= "select emp_code,emp_name,"
                    + " dept_name,desig_name,emp_type_name,emp_id,grd_name,grd_max,"
                    + "grd_min,emp_phone,emp_email,emp_org_code,emp_father,emp_basic,"
                    + "EMP_TITLE,date_format(emp_doj,'%M-%y'),emp_active from employee_master left join "
                    + "designation_master on desig_code= emp_desig_code left join"
                    + " department_master on dept_code = emp_dept_code  left join"
                    + " employee_type_master on emp_type_id = emp_type_code left "
                    + "join salary_grade_master on grd_code=emp_salary_grade "
                    + ""+s +" ";
            System.out.println("QUARY : "+q);
            ps=c.prepareStatement(q);
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
                emp.setDesig(desig.getCode());
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
                emp.setPhone(rs.getString(10));
                emp.setEmail(rs.getString(11));
                emp.setFatherName(rs.getString(13));
                emp.setCurrentBasic(rs.getInt(14));
                emp.setTitle(rs.getString(15));
                emp.setDoj(rs.getString(16));
                if(rs.getInt(17) == 1)
                {
                    emp.setButtonValue("Active");
                    emp.setStatusI("/img/Active.png");
                    emp.setEvent(true);
                }
                else
                {
                    emp.setButtonValue("Delete");
                    emp.setStatusI("/img/InActive.png");
                    emp.setEvent(false);
                }
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
                    + "emp_salary_grade=?,emp_basic=?,emp_father=?,emp_title=?,emp_exp=?,emp_qual=?,"
                    + "emp_yop=?,emp_prev_emp=?,emp_address=?,emp_active = ? where emp_code=? and "
                    + "emp_org_code=?");
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
            ps.setInt(13, emp.getCurrentBasic());
            ps.setString(14, emp.getFatherName());
            ps.setString(15, emp.getTitle());
            ps.setInt(16, emp.getExperience());
            ps.setString(17, emp.getQualification());
            ps.setInt(18, emp.getYearOfPassing());
            ps.setString(19, emp.getPreviousEmployer());
            ps.setString(20, emp.getAddress());
            if(emp.getStstus() == true)
            {
                ps.setInt(21,1);
            }
            else
            {
                ps.setInt(21,0);
            }
            ps.setString(22, emp.getCode());
            ps.setInt(23, orgCode);
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



    public Exception delete(int empId)
    {
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("delete from employee_master where emp_id=?");
            ps.setInt(1, empId);
            ps.executeUpdate();
            ps.close();
            c.close();
            return new Exception("Record Deleted");
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return e;
        }
    }


    public Exception save(Employee emp)   {
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("insert into employee_master(emp_code,emp_name,"
                    + "emp_dept_code,emp_desig_code,emp_type_code,emp_phone,"
                    + "emp_email,emp_dob,emp_doj,emp_bank_accno,emp_pf_accno,emp_pan_no,"
                    + "emp_salary_grade,emp_gender,emp_org_code,emp_father,emp_basic,emp_title,"
                    + "emp_exp,emp_qual,emp_yop,emp_prev_emp,emp_address,emp_active) "
                    + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
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
            ps.setInt(15, orgCode);
            ps.setString(16, emp.getFatherName());
            ps.setInt(17, emp.getCurrentBasic());
            ps.setString(18, emp.getTitle());
            ps.setInt(19, emp.getExperience());
            ps.setString(20, emp.getQualification());
            ps.setInt(21, emp.getYearOfPassing());
            ps.setString(22, emp.getPreviousEmployer());
            ps.setString(23, emp.getAddress());
            ps.setInt(25,1);
            ps.executeUpdate();
            ps.close();

            ps = c.prepareStatement("insert into employee_login_master values(?,?,?,?)");
            ps.setString(1, emp.getCode());
            ps.setString(2, emp.getCode());
            ps.setString(3, emp.getCode());
            ps.setInt(4, orgCode);
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
