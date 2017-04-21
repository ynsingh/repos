/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans.ext.attendance.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.faces.context.FacesContext;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.smvdu.payroll.Hibernate.HibernateUtil;
import org.smvdu.payroll.beans.Employee;
import org.smvdu.payroll.beans.SessionMaster;
import org.smvdu.payroll.beans.db.CommonDB;
import org.smvdu.payroll.beans.ext.attendance.EmployeeLeave;
import org.smvdu.payroll.module.attendance.LoggedEmployee;
import org.smvdu.payroll.beans.UserInfo;
import org.smvdu.payroll.beans.ext.attendance.EmpLeaveMaster;
import org.smvdu.payroll.beans.ext.attendance.EmpLeaveRecord;

/**
 *
 * Copyright (c) 2010 - 2011 SMVDU, Katra.
 * Copyright (c) 2014 - 2017 ETRG, IITK.
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
*  Modification : April 2017, Om Prakash (omprakashkgp@gmail.com)
* 
*/
public class EmployeeLeaveDB {
    private PreparedStatement ps;
    private ResultSet rs;
    private final UserInfo userBean;
    private HibernateUtil helper;
    private org.hibernate.Session sess;

    
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
                    + "el_approval_date, el_approval_status, el_org_id, el_Reasonforleave, "
                    + "el_ContractNo, el_Reporting_offcr, el_Covering_offcr ) values(?,?,?,?,?,?,?,?,?,?,?,?,?)");
            ps.setInt(1, empcode);
            ps.setString(2, el.getDateFrom());
            ps.setString(3, el.getDateTo());
            ps.setInt(4, el.getCount());
            ps.setInt(5, el.getLeaveTypeCode());
            ps.setString(6, el.getAppliedDate());
            ps.setString(7, null);
            ps.setInt(8,0);
            ps.setInt(9, userBean.getUserOrgCode());
            ps.setString(10, el.getReasonfleave());
            ps.setString(11, el.getContractno());
            ps.setString(12, el.getReportingoff());
            ps.setString(13, el.getCoveringoff());
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
                emp.setDeptName(rs.getString(4));
                emp.setDesigName(rs.getString(5));
                el.setEmployee(emp);
                el.setDateFrom(rs.getString(6));
                el.setDateTo(rs.getString(7));
                el.setCount(rs.getInt(8));
                el.setLeaveTypeName(rs.getString(9));
                el.setAppliedDate(rs.getString(10));
                if(rs.getInt(11)== 1)
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
            String q="select el_id, el_emp_code, el_count, lt_name, el_approval_status from employee_leave_master "
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
    /**
     * This method has been created for display the list of all requested pending leave. 
     */ 
       public ArrayList<EmployeeLeave> getAllLeaveDetails()  {
        try
        {
               
            Connection c = new CommonDB().getConnection();
            String q="select el_id, el_emp_code, emp_name, el_count,el_quota_type, lt_name, lq_count, el_approval_status, el_date_from, el_date_to, el_Covering_offcr, el_applied_date,"
                    + " el_approval_date, el_Commnts from employee_leave_master left join employee_master on emp_code = el_emp_code"
                    + " left join leave_type_master on lt_id=el_quota_type left join "
                    + "leave_quota_master on lq_leave_type=el_quota_type and lq_org_id=el_org_id and lq_emp_type=emp_type_code"
                    + " where el_approval_status='"+0+"' and el_org_id='"+userBean.getUserOrgCode()+"' ";
            
            ps=c.prepareStatement(q);
            rs = ps.executeQuery();
            ArrayList<EmployeeLeave> data = new ArrayList<EmployeeLeave>();
            int k=1;
            while(rs.next())
            {
                EmployeeLeave el = new EmployeeLeave();
                el.setId(rs.getInt(1));
                el.setEmpCode(rs.getString(2));
                Employee emp = new Employee();
                emp.setName(rs.getString(3));
                el.setEmployee(emp);
                el.setCount(rs.getInt(4));
                el.setLeaveTypeCode(rs.getInt(5));
                el.setLeaveTypeName(rs.getString(6));
                el.setLeaveValue(rs.getInt(7));
                if(rs.getInt(8)== 1)
                {    
                    el.setActiveStatus("Approved");
                }
                else{
                    el.setActiveStatus("Not Approved");
                
                }
                el.setDateFrom(rs.getString(9));
                el.setDateTo(rs.getString(10));
                el.setCoveringoff(rs.getString(11));
                el.setAppliedDate(rs.getString(12));
                el.setApprovaldate(rs.getString(13));
                el.setComments(rs.getString(14));
                el.setSrNo(k);
                data.add(el);
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
   
    /*
     * This mathod has been created for accept the Leave request of employee. 
     */
    public boolean updateLeave(ArrayList<EmployeeLeave> empl)
    {
        boolean b=false;
        sess = helper.getSessionFactory().openSession();
        Transaction tx;
        try{
            for(EmployeeLeave el : empl)
            {   
                b= new EmployeeLeaveDB().checkappliedLeave(el.getEmpCode(), el.getLeaveTypeName(), el.getCount());
                if(b==true)
                {    
                    tx = sess.beginTransaction();
                    String cdate = userBean.getCurrentDate();
                    EmpLeaveMaster emp = new EmpLeaveMaster();
                    emp.setEmpId(el.getId());
                    Query query1 = sess.createQuery("update EmpLeaveMaster set approvaldate ='" +cdate+ "', approvalstatus='" +1+ "', comments='" +el.getComments()+ "' "
                                + " where empId='"+el.getId()+"' and empcode='" +el.getEmpCode()+ "'and orgid='" +userBean.getUserOrgCode()+ "'");
                   
                    query1.executeUpdate();
                    tx.commit();
                }
                else
                {
                    return false;
                }
            }
            return b;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return false;
        }
        finally{
            sess.close();
        }
    }

   /*
    * This mathod has been created for reject the Leave request of employee. 
    */
    public boolean rejectLeave(ArrayList<EmployeeLeave> empl)
    {
        boolean b = false;
        sess = helper.getSessionFactory().openSession();
        Transaction tx;
        try{
            for(EmployeeLeave el : empl)
            {   
                b= new EmployeeLeaveDB().checkappliedLeave(el.getEmpCode(), el.getLeaveTypeName(), el.getCount());
                if(b==true)
                {    
                    tx = sess.beginTransaction();
                    String cdate = userBean.getCurrentDate();
                    EmpLeaveMaster emp = new EmpLeaveMaster();
                    emp.setEmpId(el.getId());
                    Query query1 = sess.createQuery("update EmpLeaveMaster set approvaldate ='" +cdate+ "', approvalstatus='" +0+ "', comments='" +el.getComments()+ "' "
                                + " where empId='"+el.getId()+"' and empcode='" +el.getEmpCode()+ "'and orgid='" +userBean.getUserOrgCode()+ "'");
                   
                    query1.executeUpdate();
                    tx.commit();
                }
                else
                {
                    return false;
                }
            }
            return b;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }
        finally{
              sess.close();
        }
    }
    
    /*
    * This mathod has been created for insert and update leave record of employee
    * according his employee code , org code 
    */
    public void updateLeaveTypeValue(EmployeeLeave empl){
    
        try{
             int b = getEmpOldLeaveValue(empl.getEmpCode(), empl.getLeaveTypeCode(), CurrentSessionName());
             if(b<1){
                    sess = helper.getSessionFactory().openSession();
                    sess.beginTransaction();
                    String csName=CurrentSessionName();
                    EmpLeaveRecord emplr = new EmpLeaveRecord();
                    emplr.setElrEmpCode(empl.getEmpCode());
                    emplr.setElrleaveid(empl.getLeaveTypeCode());
                    emplr.setElrcount(empl.getCount());
                    emplr.setElrFyear(csName);
                    sess.saveOrUpdate(emplr);
                    sess.beginTransaction().commit();
             }
             else{
                   int leavevalue = empl.getCount()+b;
                   sess = helper.getSessionFactory().openSession();
                   sess.beginTransaction();
                   String csName=CurrentSessionName();
                   Query query2 = sess.createQuery("update EmpLeaveRecord set elrcount ='"+leavevalue+"' where elrEmpCode ='"+empl.getEmpCode()+"' and "
                    + " elrleaveid='"+empl.getLeaveTypeCode()+"' and elrFyear ='"+csName+"'");
               
                   query2.executeUpdate();
                   sess.beginTransaction().commit();
            }
        }
        catch(Exception ex){
            sess.getTransaction().rollback();
            ex.printStackTrace();
        }
        finally{
            sess.close();
        }
    }
    /*
    * This method has been created for get the employee leave value taken in past.
    */
    public int getEmpOldLeaveValue(String empcode, int emplid, String fyear)
    {
        try{
                sess = helper.getSessionFactory().openSession();
                sess.beginTransaction();
                Query query = sess.createQuery("select elrcount from EmpLeaveRecord where elrEmpCode='"+empcode+"' and elrleaveid='"+emplid+"' and elrFyear='"+fyear+"'");
                List <Object> lvalue = query.list();
                for(Object obj: lvalue){
                    int leavevalue= obj.hashCode();
                    sess.getTransaction().commit();
                    return leavevalue;
                }
                return 0;
            }
        catch(Exception ex)
        {   
            sess.getTransaction().rollback();
            ex.printStackTrace();
            return 0;
        }   
  
    }
    /*
    * this method has been created for get the current session name.
    */
    public String CurrentSessionName()
    {
        try{ 
                sess = helper.getSessionFactory().openSession();
                sess.beginTransaction();
                Query query = sess.createQuery("select name from SessionMaster where current='"+1+"' and orgcode='"+userBean.getUserOrgCode()+"'");
                List <Object> sname = query.list();
               for(Object obg: sname){
                      String sesname = obg.toString();
                sess.getTransaction().commit();               
                return sesname;
               }
               return null;
             }
        catch(Exception ex)
        {
            sess.getTransaction().rollback();
            ex.printStackTrace();
            return null;
        }   
    }
    
    public ArrayList<EmployeeLeave> getLeaveAppData()
    {
        LoggedEmployee ec= (LoggedEmployee)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("LoggedEmployee");
        try
          {
            Connection c = new CommonDB().getConnection();
            String q="select el_id, el_date_from, el_date_to, el_count, el_quota_type, lt_name, el_applied_date, el_approval_date, el_approval_status,"
                    + " el_reasonforleave, el_ContractNo, el_Reporting_offcr, el_covering_offcr from employee_leave_master "
                    + " left join leave_type_master on lt_id=el_quota_type where el_emp_code='"+ec.getProfile().getCode()+"' and el_approval_status='"+1+"'";
            
            ps=c.prepareStatement(q);
            rs = ps.executeQuery();
            ArrayList<EmployeeLeave> data = new ArrayList<EmployeeLeave>();
            int k=1;
            while(rs.next())
            {     
                EmployeeLeave el = new EmployeeLeave();
                el.setId(rs.getInt(1));
                el.setDateFrom(rs.getString(2));
                el.setDateTo(rs.getString(3));
                el.setCount(rs.getInt(4));
                el.setLeaveTypeCode(rs.getInt(5));
                el.setLeaveTypeName(rs.getString(6));
                el.setAppliedDate(rs.getString(7));
                el.setApprovaldate(rs.getString(8));
                //el.setActiveStatus(rs.getString(8));
                el.setActiveStatus("Approved");
                el.setReasonfleave(rs.getString(10));
                el.setContractno(rs.getString(11));
                el.setReportingoff(rs.getString(12));
                el.setCoveringoff(rs.getString(13));
                el.setSrNo(k);
                data.add(el);
                k++;
            }
            return data;
          }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
     }
                                  
    public ArrayList<EmployeeLeave> getLeavePnData()
    {
        LoggedEmployee ec= (LoggedEmployee)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("LoggedEmployee");
        try
          {
            Connection c = new CommonDB().getConnection();
            String q="select el_id, el_date_from, el_date_to, el_count, el_quota_type, lt_name, el_applied_date, el_approval_date, el_approval_status,"
                    + " el_reasonforleave, el_ContractNo, el_Reporting_offcr, el_covering_offcr from employee_leave_master "
                    + " left join leave_type_master on lt_id=el_quota_type where el_emp_code='"+ec.getProfile().getCode()+"' and el_approval_status='"+0+"'";
            
            ps=c.prepareStatement(q);
            rs = ps.executeQuery();
            ArrayList<EmployeeLeave> data = new ArrayList<EmployeeLeave>();
            int k=1;
            while(rs.next())
            {     
                EmployeeLeave el = new EmployeeLeave();
                el.setId(rs.getInt(1));
                el.setDateFrom(rs.getString(2));
                el.setDateTo(rs.getString(3));
                el.setCount(rs.getInt(4));
                el.setLeaveTypeCode(rs.getInt(5));
                el.setLeaveTypeName(rs.getString(6));
                el.setAppliedDate(rs.getString(7));
                el.setApprovaldate(rs.getString(8));
                //el.setActiveStatus(rs.getString(8));
                el.setActiveStatus("Not Approved");
                el.setReasonfleave(rs.getString(10));
                el.setContractno(rs.getString(11));
                el.setReportingoff(rs.getString(12));
                el.setCoveringoff(rs.getString(13));
                el.setSrNo(k);
                data.add(el);
                k++;
            }
            return data;
          }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
     }
                                  
}
