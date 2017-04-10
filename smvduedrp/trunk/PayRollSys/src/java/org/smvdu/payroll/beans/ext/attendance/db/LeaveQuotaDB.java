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
import org.smvdu.payroll.beans.db.CommonDB;
import org.smvdu.payroll.beans.UserInfo;
import org.smvdu.payroll.beans.ext.attendance.LeaveQuota;

/**
*
*  Copyright (c) 2010 - 2011 SMVDU, Katra.
*  Copyright (c) 2014 - 2017 ETRG, IITK.
*  All Rights Reserved.
*  Redistribution and use in source and binary forms, with or 
*  without modification, are permitted provided that the following 
*  conditions are met: 
*  Redistributions of source code must retain the above copyright 
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
*  Modification : March 2017, Om Prakash (omprakashkgp@gmail.com) 
*/

public class LeaveQuotaDB {
    private PreparedStatement ps;
    private ResultSet rs;
    private final UserInfo userBean;


    
    
    public LeaveQuotaDB()   {
        //info = (ActiveProfile)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ActiveProfile");

        userBean = (UserInfo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserBean");

    }
    

    public void update(ArrayList<LeaveQuota> data,int type)  {
        try
        {
            Connection c = new CommonDB().getConnection();
            ArrayList<LeaveQuota> allltypes=new ArrayList<LeaveQuota>(getQuota(type));
            ArrayList<LeaveQuota> diff = new ArrayList<LeaveQuota>(data);
            diff.removeAll(allltypes);
             
            if(diff.size()!=0){
                for(LeaveQuota lq : diff)
                {
                   
                   ps = c.prepareStatement("insert into leave_quota_master values(?,?,?,?)");
                   ps.setInt(1, type);
                   ps.setInt(2, lq.getLeaveType());
                   ps.setInt(3, lq.getCount());
                   ps.setInt(4, userBean.getUserOrgCode());
                   ps.executeUpdate();
                   ps.clearParameters();
            
                }
            }
            else{
                for(LeaveQuota lq : data)
                {
                    ps=c.prepareStatement("update leave_quota_master set lq_count=? where lq_emp_type=? and lq_leave_type=? and lq_org_id=? ");
                    ps.setInt(1, lq.getCount());
                    ps.setInt(2, type);
                    ps.setInt(3,lq.getLeaveType());
                    ps.setInt(4, userBean.getUserOrgCode());
                    ps.executeUpdate();
                    ps.clearParameters();
                    
                }   
            }
            ps.close();
            c.close();
           
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public ArrayList<LeaveQuota> getQuota(int type)  {
        try
        {
            String q = "select lq_leave_type,lt_name,lq_count from leave_quota_master left"
                    + " join leave_type_master on lq_leave_type = lt_id left join leavetype_org_record on ltr_leave_id = lt_id where lq_emp_type=? and ltr_org_id='"+userBean.getUserOrgCode()+"' ";
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement(q);
            ps.setInt(1, type);
            rs = ps.executeQuery();
            ArrayList<LeaveQuota> data = new ArrayList<LeaveQuota>();
            while(rs.next())
            {
                LeaveQuota lq = new LeaveQuota();
                lq.setLeaveType(rs.getInt(1));
                lq.setLeaveTypeName(rs.getString(2));
                lq.setCount(rs.getInt(3));
                    data.add(lq);
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
        
    public ArrayList<LeaveQuota> loadAllData(int type) {
       
       ArrayList<LeaveQuota> allltypes = new ArrayList<LeaveQuota>();
       ArrayList<LeaveQuota> selected=new ArrayList<LeaveQuota>(getQuota(type));
       ArrayList<LeaveQuota>allleave=new ArrayList<LeaveQuota>(getAllSelected(0));
       allleave.removeAll(selected);
       LeaveQuota lquota = new LeaveQuota();
       for(LeaveQuota sh : allleave)
       { 
           lquota = new LeaveQuota();
           lquota.setLeaveType(sh.getLeaveType());
           lquota.setLeaveTypeName(sh.getLeaveTypeName());
           lquota.setCount(sh.getCount());
           allltypes.add(lquota);
       }
       for(LeaveQuota selh : selected){
           lquota = new LeaveQuota();
           lquota.setSelected(true);
           lquota.setLeaveType(selh.getLeaveType());
           lquota.setLeaveTypeName(selh.getLeaveTypeName());
           lquota.setCount(selh.getCount());
           allltypes.add(lquota);
           
       }   
       return allltypes;
       
    }
    
    public ArrayList<LeaveQuota> getAllSelected(int type)  {
        try
        {
            
        
            Connection c = new CommonDB().getConnection();
            String q="select lt_id,lt_name, lt_value from leavetype_org_record "
                    + "left join leave_type_master on ltr_leave_id = lt_id where ltr_org_id='"+userBean.getUserOrgCode()+"'";
            
            ps=c.prepareStatement(q);
            //ps.setInt(1, 0);
            rs = ps.executeQuery();
            ArrayList<LeaveQuota> data = new ArrayList<LeaveQuota>();
            //int k = 1;
            while(rs.next())
            {
                LeaveQuota lv = new LeaveQuota();
                lv.setLeaveType(rs.getInt(1));
                lv.setLeaveTypeName(rs.getString(2));
                lv.setCount(rs.getInt(3));
                //lv.setSrNo(k);
                data.add(lv);
                //k++;
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
    
    public ArrayList<LeaveQuota> getAll()  {
        try
        {
            
        
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("select * from leave_type_master");
            rs =ps.executeQuery();
            ArrayList<LeaveQuota> data = new ArrayList<LeaveQuota>();
            //int k = 1;
            while(rs.next())
            {
                LeaveQuota lv = new LeaveQuota();
                lv.setLeaveType(rs.getInt(1));
                lv.setLeaveTypeName(rs.getString(2));
                lv.setCount(rs.getInt(3));
                //lv.setSrNo(k);
                data.add(lv);
                //k++;
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
    
     public ArrayList<LeaveQuota> getAllotedQuota(String empcode)  {
        try
        {
            
            int type=EmployeeType(empcode);
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("select lt_name,lq_leave_type,lq_count from leave_quota_master "
                    + "left join leave_type_master on lq_leave_type = lt_id where lq_emp_type='"+type+"' ");
            rs = ps.executeQuery();
            ArrayList<LeaveQuota> data = new ArrayList<LeaveQuota>();
            while(rs.next())
            {
                LeaveQuota lq = new LeaveQuota();
                lq.setLeaveTypeName(rs.getString(1));
                lq.setLeaveType(rs.getInt(2));
                lq.setCount(rs.getInt(3));
                data.add(lq);
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
     public int EmployeeType(String code) {
        try {
            Connection c = new CommonDB().getConnection();
            ps = c.prepareStatement("select emp_type_code from employee_master where "
                    + "emp_code=? and emp_org_code=?");
            ps.setString(1, code);
            ps.setInt(2, userBean.getUserOrgCode());
            rs = ps.executeQuery();
            rs.next();
            int s = rs.getInt(1);
            rs.close();
            ps.close();
            c.close();
            return s;
        } catch (Exception e) {
            return -1;
        }
    }
    
    public ArrayList<LeaveQuota> getCombinedData(String empcode)  {
        try
        {
           
           ArrayList<LeaveQuota> allltypes = getAllotedQuota(empcode);
           ArrayList<LeaveQuota>combineData=new ArrayList<LeaveQuota>();
           for(LeaveQuota lq: allltypes){
               LeaveQuota lquota = new LeaveQuota();
               lquota.setLeaveTypeName(lq.getLeaveTypeName());
               lquota.setCount(lq.getCount());
               int balleave=empballeave(lq.getLeaveType(), empcode);
               lquota.setBalanceCount(balleave);
               combineData.add(lquota);
           }
            
           return combineData;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    public int LeaveValue(int leavetype) {
        try {
            Connection c = new CommonDB().getConnection();
            ps = c.prepareStatement("select lq_count from leave_quota_master "
                    + "left join leavetype_org_record on ltr_leave_id=lq_leave_type where lq_leave_type='"+leavetype+"' and ltr_org_id='"+userBean.getUserOrgCode()+"' ");
            rs = ps.executeQuery();
            rs.next();
            int s = rs.getInt(1);
            rs.close();
            ps.close();
            c.close();
            return s;
        } catch (Exception e) {
            return -1;
        }
    }
    
    public int Emptotalleave(int leavetype, String empcode) {
        try {
            int totalleave = 0;
            Connection c = new CommonDB().getConnection();
            ps = c.prepareStatement("select el_count from employee_leave_master "
                    + "where el_quota_type='"+leavetype+"' and el_emp_code='"+empcode+"' and el_approval_status='"+1+"' ");
            rs = ps.executeQuery();
            while(rs.next()){
                int s= rs.getInt(1);
                totalleave=s+totalleave;
            }
            rs.close();
            ps.close();
            c.close();
            return totalleave;
        } catch (Exception e) {
            return -1;
        }
    }
    public int empballeave(int leavetype, String empcode) {
        try {
            
            int Allotedleave=LeaveValue(leavetype);
            int availedleave=Emptotalleave(leavetype,empcode);
            int balanceleave=0;
            if(Allotedleave!=0 && Allotedleave >= availedleave){
                balanceleave=Allotedleave-availedleave;
            }
            return balanceleave;
        }
        catch (Exception e) {
            return -1;
        }
    }
        
}
