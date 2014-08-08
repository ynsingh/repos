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
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.smvdu.payroll.beans.db.CommonDB;
import org.smvdu.payroll.beans.ext.attendance.LeaveType;
import org.smvdu.payroll.beans.ext.attendance.LeaveValue;
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
*  Contributors: Members of ERP Team @ SMVDU, Katra, IITKanpur
* * Modified Date: 4 AUG 2014, IITK (palseema30@gmail.com, kishore.shuklak@gmail.com)
*
 */
public class LeaveTypeDB {

    private PreparedStatement ps;
    private ResultSet rs;
    
    private final UserInfo userBean;
    private ActiveProfile info;

    public LeaveTypeDB()   {
        //info = (ActiveProfile)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ActiveProfile");

        userBean = (UserInfo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserBean");


    }
    
    public Exception update(LeaveType ltype)  {
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("update leave_type_master set lt_name=?, "
                                 + "lt_value=? where lt_id=?");
                ps.setString(1, ltype.getName());
                //ps.setFloat(2, lv.getValue());
                ps.setInt(2, ltype.getValue());
                ps.setInt(3, ltype.getCode());
                ps.executeUpdate();
                ps.clearParameters();
                  
                //System.out.println("update====leave type==="+ltype.getName()+"\n===value=="+ltype.getValue()+"\n code"+ltype.getCode());
           
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
    public void save(LeaveType lt)  {
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("insert into leave_type_master(lt_name, lt_value) values(?,?)");
            ps.setString(1, lt.getName());
            //ps.setFloat(2, lt.getValue());
            ps.setInt(2, lt.getValue());
            ps.executeUpdate();
            c.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public ArrayList<LeaveType> getAll()  {
        try
        {
            
        
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("select * from leave_type_master");
            rs =ps.executeQuery();
            ArrayList<LeaveType> data = new ArrayList<LeaveType>();
            //int k = 1;
            while(rs.next())
            {
                LeaveType lv = new LeaveType();
                lv.setCode(rs.getInt(1));
                lv.setName(rs.getString(2));
                lv.setValue(rs.getInt(3));
                //lv.setSrNo(k);
                data.add(lv);
                //System.out.println("in LeaveTypeDB==="+data);;
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
    
    public Exception DeleteleaveType(int currentIndex, ArrayList<LeaveType> ltype){
          try{
                Connection connection = new CommonDB().getConnection();
                
                for(LeaveType lt :ltype )
                {
                    if(lt.getCode()== currentIndex){
                                          
                        //System.out.println("\n in line 132==deleterecordordcurrentindex=="+lt.getCode());
               
                        ps = connection.prepareStatement("delete from leave_type_master where lt_id='"+lt.getCode()+"' and lt_name = '"+lt.getName()+"' ");
                        ps.executeUpdate();
                        ps.clearParameters();
                    }
                }    
               
                ps.close();
                connection.close(); 
                return null;   
          }
          catch(Exception ex)
          {
            ex.printStackTrace();
            return ex;
        } 
       }      

    public void AddforInstituite (ArrayList<LeaveType> data)  {
        try
        {
            
            Connection c = new CommonDB().getConnection();
            ArrayList<LeaveType> diff = new ArrayList<LeaveType>(data);
            ArrayList<LeaveType> selected = new LeaveTypeDB().getAllappliedLeave();
            diff.removeAll(selected);
            ArrayList<LeaveType> diff2 = new ArrayList<LeaveType>(selected);
            diff2.removeAll(data);
            //System.out.println("data===in DB method===="+ps);
            for(LeaveType lt : diff)
            {
                ps=c.prepareStatement("insert into leavetype_org_record(ltr_leave_id, ltr_org_id) values(?,?)");
                ps.setInt(1, lt.getCode());
                ps.setInt(2,userBean.getUserOrgCode());
                ps.executeUpdate();
                ps.clearParameters();
            }   
            for(LeaveType dt : diff2)
            {
                ps=c.prepareStatement("delete from  leavetype_org_record where ltr_leave_id=? and ltr_org_id=?");
                ps.setInt(1, dt.getCode());
                ps.setInt(2,userBean.getUserOrgCode());
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
    
    public ArrayList<LeaveType> getAllappliedLeave()  {
        try
        {
            Connection c = new CommonDB().getConnection();
            
            ps=c.prepareStatement("select ltr_leave_id, lt_name, lt_value from leavetype_org_record "
                    +"left join leave_type_master on lt_id = ltr_leave_id "
                    + "where ltr_org_id='"+userBean.getUserOrgCode()+"'");
            rs =ps.executeQuery();
            ArrayList<LeaveType> data = new ArrayList<LeaveType>();
            while(rs.next())
            {
                LeaveType lv = new LeaveType();
                lv.setCode(rs.getInt(1));
                lv.setName(rs.getString(2));
                lv.setValue(rs.getInt(3));
                data.add(lv);
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
    public ArrayList<LeaveType> loadLeaveType() {
       try{ 
            ArrayList<LeaveType> allLtypes = new ArrayList<LeaveType>();
            ArrayList<LeaveType> selected = new LeaveTypeDB().getAllappliedLeave();
            //System.out.println("Hello World===selection===="+selected.size()+"\nselection=="+selected);
            for(LeaveType sh : new LeaveTypeDB().getAll())
            {
                LeaveType lt =new LeaveType();
                lt.setCode(sh.getCode());
                lt.setName(sh.getName());
                lt.setValue(sh.getValue());
                for(LeaveType ss : selected){
                
                    if(selected.contains(sh))
                    {
                    
                        //System.out.println("Hello World===inside===="+lt.getName());
                        lt.setChecked(true);
                    }
               
                }
                allLtypes.add(lt);
            }    
                
            return allLtypes;
       }
       catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
       
    }
    
    public boolean LeaveExists ( int leaveid){
        try{
            
            Connection c = new CommonDB().getConnection();
            ps = c.prepareStatement("select ltr_leave_id from leavetype_org_record"
                    + " where ltr_leave_id='"+leaveid+"'");
            rs = ps.executeQuery();
            rs.next();
            int s = rs.getInt(1);
            //System.out.println("ssss======"+s);
            rs.close();
            ps.close();
            c.close();
            return true;
         
        }
        catch(Exception ex)
        {
            return false;
        }
    }


}
