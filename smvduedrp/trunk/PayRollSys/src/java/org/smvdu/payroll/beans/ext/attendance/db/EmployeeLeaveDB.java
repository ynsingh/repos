/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans.ext.attendance.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.faces.context.FacesContext;
import org.smvdu.payroll.beans.Employee;
import org.smvdu.payroll.beans.db.CommonDB;
import org.smvdu.payroll.beans.ext.attendance.EmployeeLeave;
import org.smvdu.payroll.module.attendance.LoggedEmployee;
import org.smvdu.payroll.beans.UserInfo;

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
*  Contributors: Members of ERP Team @ SMVDU, Katra, IITKanpur
*  Modified Date: 7 AUG 2014, IITK (palseema30@gmail.com, kishore.shuklak@gmail.com)
*/
public class EmployeeLeaveDB {
    private PreparedStatement ps;
    private ResultSet rs;
    private final UserInfo userBean;
    
    public EmployeeLeaveDB(){
        
              userBean = (UserInfo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserBean");
    }
  
    public void save(EmployeeLeave el)
    {
        try
        {
            int empcode=getEmpCode(el.getEmpId());
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("insert into employee_leave_master(el_emp_code,"
                    + "el_date_from, el_date_to, el_count, el_quota_type, el_applied_date,"
                    + "el_approval_date, el_approval_status, el_org_id) values(?,?,?,?,?,?,?,?,?)");
            //ps.setInt(1, Integer.parseInt(el.getEmpCode()));
            ps.setInt(1, empcode);
            ps.setString(2, el.getDateFrom());
            ps.setString(3, el.getDateTo());
            ps.setInt(4, el.getCount());
            ps.setInt(5, el.getLeaveTypeCode());
            ps.setString(6, el.getAppliedDate());
            ps.setString(7, null);
            ps.setInt(8,0);
            ps.setInt(9, userBean.getUserOrgCode());
            //System.out.println("empcodefor ===="+el.getEmpCode());
            ps.executeUpdate();
            ps.close();
            c.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

     public ArrayList<EmployeeLeave> loadMyLeaves()   {
        try
        {
           LoggedEmployee ec= (LoggedEmployee)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("LoggedEmployee");
            Connection c = new CommonDB().getConnection();
            String q = "select el_id,emp_code,emp_name,dept_name,desig_name,"
                    + "date_format(el_date_from,'%d-%M-%y'),date_format(el_date_to,'%d-%M-%y'),"
                    + "el_count,lt_name from employee_leave_master "
                    + "left join employee_master on emp_id=el_emp_code left join "
                    + "department_master on dept_code = emp_dept_code left join "
                    + "designation_master on desig_code = emp_desig_code left join"
                    + " leave_type_master on lt_id =el_quota_type where el_emp_code=?";


            ps=c.prepareStatement(q);
            ps.setInt(1, ec.getProfile().getEmpId());
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

    public ArrayList<EmployeeLeave> loadLeaves()   {
        try
        {
            Connection c = new CommonDB().getConnection();
            String q ="select el_id, el_emp_code, emp_name, dept_name, desig_name,"
                    + "date_format(el_date_from,'%d-%M-%y'),date_format(el_date_to,'%d-%M-%y'),"
                    + "el_count,lt_name, date_format(el_applied_date,'%d-%M-%y'), el_approval_status from employee_leave_master "
                    + "left join employee_master on emp_code = el_emp_code left join "
                    + "department_master on dept_code = emp_dept_code left join "
                    + "designation_master on desig_code = emp_desig_code left join "
                    + "leave_type_master on lt_id=el_quota_type where el_org_id='"+userBean.getUserOrgCode()+"'";

            ps=c.prepareStatement(q);
            rs = ps.executeQuery();
            ArrayList<EmployeeLeave> data = new ArrayList<EmployeeLeave>();
            while(rs.next())
            {
                EmployeeLeave el = new EmployeeLeave();
                el.setId(rs.getInt(1));
                el.setEmpCode(rs.getString(2));
                Employee emp = new Employee();
                emp.setName(rs.getString(3));
                //System.out.println("empleave Data=======empdeptName==="+rs.getString(11));
                emp.setDeptName(rs.getString(4));
                emp.setDesigName(rs.getString(5));
                el.setEmployee(emp);
                //System.out.println("empleave Data=======empdeptName=11==="+el.getEmployee().getName());
                el.setDateFrom(rs.getString(6));
                el.setDateTo(rs.getString(7));
                el.setCount(rs.getInt(8));
                el.setLeaveTypeName(rs.getString(9));
                el.setAppliedDate(rs.getString(10));
                if(rs.getInt(11)== 1)
                {    
                el.setActiveStatus("Approved");
                //System.out.println("empleave Data==rs===in if condition======="+rs.getInt(11));
                }
                else{
                el.setActiveStatus("Not Approved");
                //System.out.println("empleave Data==rs==in else condition======="+rs.getInt(11));
                }
                //el.setStatus(rs.getInt(11));
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
    
    public boolean Accept(ArrayList<EmployeeLeave> empl)
    {
        boolean b=false;
        try
        {
            String Cdate=userBean.getCurrentDate();
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("update  employee_leave_master set el_approval_date=?, el_approval_status=? "
                    + " where el_id =? and el_emp_code=? and el_org_id =?");
            for(EmployeeLeave el: empl){  
                b= new EmployeeLeaveDB().checkappliedLeave(el.getEmpCode(), el.getLeaveTypeName(), el.getCount());
                
                if(b==true)
                {
                    ps.setString(1,Cdate);
                    ps.setInt(2,1);
                    ps.setInt(3,el.getId());
                    ps.setString(4,el.getEmpCode());
                    ps.setInt(5, userBean.getUserOrgCode());
                    ps.executeUpdate();
                    ps.clearParameters();
                    
                }
                else
                {
                   b=false; 
                }
                     
            }
            ps.close();
            c.close();
            return b;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }
    
    public ArrayList<EmployeeLeave> getAllSelected()  {
        try
        {
                
            Connection c = new CommonDB().getConnection();
            String q="select el_id, el_emp_code,el_count,lt_name, el_approval_status from employee_leave_master "
                    + "left join leave_type_master on lt_id=el_quota_type "
                    + "where el_approval_status=1 and el_org_id='"+userBean.getUserOrgCode()+"' ";
            
            ps=c.prepareStatement(q);
            rs = ps.executeQuery();
            ArrayList<EmployeeLeave> data = new ArrayList<EmployeeLeave>();
            //int k = 1;
            while(rs.next())
            {
                EmployeeLeave el = new EmployeeLeave();
                el.setId(rs.getInt(1));
                el.setEmpCode(rs.getString(2));
                el.setCount(rs.getInt(3));
                el.setLeaveTypeName(rs.getString(4));
                if(rs.getInt(5)== 1)
                {    
                    el.setActiveStatus("Approved");
                }
                else{
                    el.setActiveStatus("Not Approved");
                
                }
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
   
     public ArrayList<EmployeeLeave> getAllLeaveData()  {
        try
        {
            ArrayList<EmployeeLeave> allltypes = new ArrayList<EmployeeLeave>();
            ArrayList<EmployeeLeave> selected=new EmployeeLeaveDB().getAllSelected();
            for(EmployeeLeave data : loadLeaves())
            {
                EmployeeLeave el = new EmployeeLeave();
                
                el.setId(data.getId());
                el.setEmpCode(data.getEmpCode());
                el.setEmployee(data.getEmployee());
                el.setDateFrom(data.getDateFrom());
                el.setDateTo(data.getDateTo());
                el.setCount(data.getCount());
                el.setLeaveTypeName(data.getLeaveTypeName());
                el.setAppliedDate(data.getAppliedDate());
                el.setActiveStatus(data.getActiveStatus());
                               
               allltypes.add(el);
           }
           return  allltypes;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    } 
     
   public int getEmpCode(int empid) {
        try {
            Connection c = new CommonDB().getConnection();
            ps = c.prepareStatement("select emp_code from employee_master "
                    + "where emp_id=?");
            ps.setInt(1, empid);
            rs = ps.executeQuery();
            rs.next();
            int code = rs.getInt(1);
            rs.close();
            ps.close();
            c.close();
            return code;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    } 
   
   public boolean checkappliedLeave(String code, String leavetypename, int count) {
        try {
            int leavetypecode=getLeaveTypeCode(leavetypename);
            int empballeave= new LeaveQuotaDB().empballeave(leavetypecode,code);
            //System.out.print("\n empballeave=check=="+empballeave);
            if(count <= empballeave){
               return true; 
            }
            else{
            return false;
            }
        } catch (Exception e) {
            return false;
        }
    }
   
   public int getLeaveTypeCode(String leavetypename) {
        try {
            Connection c = new CommonDB().getConnection();
            ps = c.prepareStatement("select lt_id from leave_type_master "
                    + " where lt_name=? ");
            ps.setString(1, leavetypename);
            rs = ps.executeQuery();
            rs.next();
            int leavecode = rs.getInt(1);
            rs.close();
            ps.close();
            c.close();
            return leavecode;
        }
        catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
   

}
