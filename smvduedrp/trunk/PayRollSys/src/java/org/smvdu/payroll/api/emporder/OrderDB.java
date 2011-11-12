/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.smvdu.payroll.api.emporder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.faces.context.FacesContext;
import org.smvdu.payroll.beans.Employee;
import org.smvdu.payroll.beans.UserInfo;
import org.smvdu.payroll.beans.db.CommonDB;
import org.smvdu.payroll.beans.setup.Department;
import org.smvdu.payroll.beans.setup.Designation;
import org.smvdu.payroll.beans.setup.EmployeeType;
import org.smvdu.payroll.beans.setup.SalaryGrade;

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
public class OrderDB {
    
    private PreparedStatement ps;
    private ResultSet rs;
    private int orgCode;
    
    
    public OrderDB()
    {
        UserInfo ui = (UserInfo)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserBean");
        orgCode = ui.getUserOrgCode();
    }
    
    
    
    public ArrayList<Employee> load(String parent)
    {
         try
        {
            Connection c = new CommonDB().getConnection();
            String q= "select emp_code,emp_name,"
                    + " dept_name,desig_name,emp_type_name,emp_id,grd_name,grd_max,"
                    + "grd_min,emp_phone,emp_email,emp_org_code,emp_father,emp_basic   from "
                    + " emp_hirarchy_master left join employee_master on emp_id = hr_chield_id"
                    + " left join designation_master on desig_code= "
                    + "emp_desig_code left join department_master on dept_code = "
                    + "emp_dept_code  left join employee_type_master on emp_type_id "
                    + "= emp_type_code left join salary_grade_master on grd_code=emp_salary_grade "
                    + " and emp_org_code=? where hr_parent_id=?";
            System.out.println("QUARY : "+q);
            ps=c.prepareStatement(q);
            ps.setInt(1, orgCode);
            ps.setString(2, parent);
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
    
    public void update(String parent,ArrayList<Integer> children)
    {
        try
        {
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
}
