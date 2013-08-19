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
import org.smvdu.payroll.beans.Employee;
import org.smvdu.payroll.beans.UserGroup;
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
*  Contributors: Members of ERP Team @ SMVDU, Katra
*
 */
public class UserDB {

    private PreparedStatement ps;
    private ResultSet rs;
    private UserInfo userBean;


    

    public UserDB() {
        userBean = (UserInfo)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserBean");
    }
    public ArrayList<UserInfo> loadAll()    {
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("select user_id,user_name,grp_id,grp_name "
                    + ",user_profile_id from user_master left join user_group_master on "
                    + " user_grp_id = grp_id where user_org_id=?");
            ps.setInt(1, userBean.getUserOrgCode());
            rs=ps.executeQuery();
            ArrayList<UserInfo> data = new ArrayList<UserInfo>();
            while(rs.next())
            {
                UserInfo ui = new UserInfo();
                ui.setUserId(rs.getInt(1));
                ui.setUserName(rs.getString(2));
                UserGroup ug = new UserGroup();
                ug.setId(rs.getInt(3));
                ug.setName(rs.getString(4));
                ui.setUserGroup(ug);
                if(rs.getInt(5)>0)
                {
                    ui.setProfileActive(true);
                }
 else
                ui.setProfileActive(false);
                data.add(ui);
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
    public void save(UserInfo ui)    {
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("insert into user_master(user_name,user_pass,"
                    + "user_org_id,user_grp_id,user_profile_id,flag) values(?,?,?,?,?,?)");
            ps.setString(1, ui.getUserName());
            ps.setString(2, ui.getPassword());
            ps.setInt(3, ui.getUserOrgCode());
            ps.setInt(4, ui.getGroupCode());
            ps.setInt(5, ui.getProfileId());
            ps.setInt(6,0); 
            ps.executeUpdate();
            ps.close();
            c.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public boolean editPass(String pass,int x)   {
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("update user_master set user_pass=? where user_id=?");
            ps.setString(1, pass);
            ps.setInt(2, x);
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
   
    public int validate(String user,String pass,int userCode,UserInfo info)  {
        String empCode =null;
        try
        {
            Connection c = new CommonDB().getConnection();
            PreparedStatement pst;
            pst = c.prepareStatement("select user_id,admin_pass,flag from admin_records where user_id='"+user+"' and admin_pass='"+pass+"' and flag='"+1+"'");;
            ResultSet rst;
            rst = pst.executeQuery();
            if(rst.next() == true)
            {
                return 2;
            }
            pst.close();
            rst.close();
            ps=c.prepareStatement("select user_task_id,user_grp_id,grp_name,user_org_code from user_task_group "
                    + "left join user_group_master "
                    + "on grp_id =user_grp_id where user_task_id='"+user+"' and user_task_password='"+pass+"' and user_task_flag = '"+1+"' and user_org_code = '"+userCode+"'");
            /*System.out.println("select user_id,user_grp_id,grp_name from user_master "
                    + "left join user_group_master "
                    + "on grp_id =user_grp_id where user_name='"+user+"' and user_pass='"+pass+"' and flag = '"+1+"'");*/
            rs = ps.executeQuery();
            if(rs.next())
            {
                UserGroup ug =new UserGroup();
            ug.setId(rs.getInt(2));
            ug.setName(rs.getString(3));
            info.setUserGroup(ug);
            info.setUserId(rs.getInt(1));
            if(empCode==null)
            {
                info.setProfileActive(false);
            }
            else
            {
                Employee emp = new EmployeeDB().loadProfile(empCode,userBean.getUserOrgCode());
                if(emp!=null)
                {
                    info.setProfile(emp);
                    info.setProfileActive(true);
                }
            }
                return 3;
            }
            pst.close();
            rst.close();
            ps=c.prepareStatement("select user_id,user_grp_id,grp_name from user_master "
                    + "left join user_group_master "
                    + "on grp_id =user_grp_id where user_name='"+user+"' and user_pass='"+pass+"' and flag = '"+1+"'");
            /*ps.setString(1, user);
            ps.setString(2, pass);
            ps.setBoolean(3, true);*/
            rs=ps.executeQuery();
            boolean b =rs.next();
            
            System.out.println(b);
            int k = 1;
            UserGroup ug =new UserGroup();
            ug.setId(rs.getInt(2));
            ug.setName(rs.getString(3));
            info.setUserGroup(ug);
            info.setUserId(rs.getInt(1));
            rs.close();
            ps.close();
            c.close();
            if(empCode==null)
            {
                info.setProfileActive(false);
            }
            else
            {
                Employee emp = new EmployeeDB().loadProfile(empCode,userBean.getUserOrgCode());
                if(emp!=null)
                {
                    info.setProfile(emp);
                    info.setProfileActive(true);
                }
            }
            return k;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return -1;
        }
    }

}
