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
import org.smvdu.payroll.beans.composite.OrgController;

/**
*
*  Copyright (c) 2010 - 2011.2014 SMVDU, Katra.
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
*  Contributors: Members of ERP Team @ SMVDU, Katra,IITkanpur.
*  Modified Date: 26 feb 2014, IITK (palseema@rediffmail.com, kshuklak@rediffmail.com)
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
   
//    public int validate(String user,String pass,int userCode,UserInfo info)  {
//        int k=0;
//        String loginDB = "logindb";
//        boolean dbExist = new CommonDB().checkLoginDBExists();
//        System.out.println("connection to database is :" +dbExist);
//        PreparedStatement pst1;
//        PreparedStatement pst2;
//        
////        if(dbExist){
////            try{
////                Connection connectLogin = new CommonDB().getLoginDBConnection();
////
////                pst1 = connectLogin.prepareStatement("select username, password, status from users where username='"+user+"' and password='"+pass+"' and status='"+1+"'");
////                ResultSet rst1;
////                rst1 = pst1.executeQuery();
////                pst1.clearParameters();
////                if(rst1.next() == true)
////                {
////                    Connection c = new CommonDB().getConnection();
////                    UserDB ud = new UserDB();
////               //     int userId = ud.getUserId(user, ());
////                    
////                 //   pst2 = c.prepareStatement("select role_id from user_roles where user_id='"+userId+"'");
////              //      ResultSet rst2;
////                    rst2 = pst2.executeQuery();
////                    ArrayList<Integer> totalRoles = new ArrayList<Integer>();
////                    while(rst2.next())
////                    {
////                       totalRoles.add(rst2.getInt(1));                    
////                    }
////                    
////                    if(totalRoles.contains(1))
////                    {
////                      return 1;
////                    }
////                    else if(totalRoles.contains(2))
////                    {
////                        return 2;
////                    }
////                    else
////                    {
////                        return 3;
////                    }
////                    
////                }
////            }   
////            
////            catch(Exception e)
////            {
////                e.printStackTrace();
////                return -1;
////            }
////            
////        }
////        else{
//            
//            String empCode =null;
//            PreparedStatement pst;
//            
//            try
//            {
//
//                Connection c = new CommonDB().getConnection();
//                if(userCode == 0){
//                    pst = c.prepareStatement("select user_id,admin_pass,flag from admin_records where user_id='"+user+"' and admin_pass='"+pass+"' and flag='"+1+"'");;
//                    ResultSet rst;
//                    rst = pst.executeQuery();
//                    if(rst.next() == true)
//                    {
//                        k=2;
//                        //return 2;
//                    }
//                    pst.close();
//                    rst.close();
//                }
//                else
//                {
//                    ps=c.prepareStatement("select user_task_id,user_grp_id,grp_name,user_org_code from user_task_group "
//                                + "left join user_group_master "
//                                + "on grp_id =user_grp_id where user_task_id='"+user+"' and user_task_password='"+pass+"' and user_task_flag = '"+1+"' and user_org_code = '"+userCode+"'");
//                                /*System.out.println("select user_id,user_grp_id,grp_name from user_master "
//                                + "left join user_group_master "
//                                + "on grp_id =user_grp_id where user_name='"+user+"' and user_pass='"+pass+"' and flag = '"+1+"'");*/
//                    rs = ps.executeQuery();
//                    if(rs.next())
//                    {
//                        UserGroup ug =new UserGroup();
//                        ug.setId(rs.getInt(2));
//                        ug.setName(rs.getString(3));
//                        info.setUserGroup(ug);
//                        info.setUserId(rs.getInt(1));
//                        if(empCode==null)
//                        {
//                            info.setProfileActive(false);
//                        }
//                        else
//                        {   
//                            Employee emp = new EmployeeDB().loadProfile(empCode,userBean.getUserOrgCode());
//                            if(emp!=null)
//                            {
//                                info.setProfile(emp);
//                                info.setProfileActive(true);
//                            }
//                        }
//                        k=3;
//                        //return 3;
//                    }
//                    ps.close();
//                    rs.close();
//                    ps=c.prepareStatement("select user_id,user_grp_id,grp_name from user_master "
//                            + "left join user_group_master "
//                            + "on grp_id =user_grp_id where user_name='"+user+"' and user_pass='"+pass+"' and flag = '"+1+"' and user_org_id = '"+userCode+"'");
//                            /*ps.setString(1, user);
//                            ps.setString(2, pass);
//                            ps.setBoolean(3, true);*/
//                    rs=ps.executeQuery();
//                    //boolean b =rs.next();
//                    if(rs.next())
//                    {
//                    //int k = 1;
//                        UserGroup ug =new UserGroup();
//                        ug.setId(rs.getInt(2));
//                        ug.setName(rs.getString(3));
//                        info.setUserGroup(ug);
//                        info.setUserId(rs.getInt(1));
//                        rs.close();
//                        ps.close();
//                        c.close();
//                        if(empCode==null)
//                        {
//                            info.setProfileActive(false);
//                        }
//                        else
//                        {
//                            Employee emp = new EmployeeDB().loadProfile(empCode,userBean.getUserOrgCode());
//                            if(emp!=null)
//                            {
//                                info.setProfile(emp);
//                                info.setProfileActive(true);
//                            }
//                        }
//                        k=1;
//                    }//if
//                }
//                return k;
//            }
//            catch(Exception e)
//            {
//                e.printStackTrace();
//                return -1;
//            }
//        }   
//        
    
    
    public int validate(String user,String pass,UserInfo info){
        System.out.println("Initializing k = 0");
        int k=0;
        Connection connectLogin = new CommonDB().getLoginDBConnection();
        Connection connectPl = new CommonDB().getConnection();
         try{
            boolean LoginDbExist = new CommonDB().checkLoginDBExists();
            PreparedStatement pst1;
            if(LoginDbExist)
            {  
                System.out.println("Varifying Username Password in LoginDB");
                pst1 = connectLogin.prepareStatement("select id, status from bgasuser where username='"+user+"' and password='"+pass+"' and status='"+1+"'");
                ResultSet rst1;
                rst1 = pst1.executeQuery();
                pst1.clearParameters();
                if(rst1.next() == true)
                {
                    int userId = rst1.getInt(1);
                    k = 1; 
                    System.out.println("User is varified in LoginDB and Value of K is changed to 1");
                    LastLoginStatusUpdate(userId);
                }
                else
                {
                    System.out.println("UserName or Password is invalid in LoginDB");
                }
            }
            else
            {
                System.out.println("LoginDb does not Exist");
                System.out.println("Varifying Username Password in user_master");
                pst1 = connectPl.prepareStatement("select user_id, user_name, user_pass, flag from user_master where user_name='"+user+"' and user_pass='"+pass+"' and flag='"+1+"'");
                ResultSet rst1;
                rst1 = pst1.executeQuery();
                pst1.clearParameters();
                if(rst1.next() == true)
                {
                    k = 1; 
                    System.out.println("User is varified in user_master and Value of K is changed to 1");
                }
                else
                {
                    System.out.println("UserName or Password is invalid in User Master");
                }
            }
            
            if(k==1)
            {
                System.out.println("Verifying Role Of the User...");
                UserDB ud = new UserDB();
                int userId = ud.getUserId(user);
                System.out.println("UserId of the user In User_master is :"+userId);
                PreparedStatement pst2;
                pst2 = connectPl.prepareStatement("select role_id from user_roles where user_id='"+userId+"'");
                ResultSet rst2;
                rst2 = pst2.executeQuery();
                ArrayList<Integer> totalRoles = new ArrayList<Integer>();
                System.out.println("ArrayList is created for containing all the roles for the user");
                while(rst2.next())
                {
                    totalRoles.add(rst2.getInt(1));
                    System.out.println("Value added in the Role Array is :" +rst2.getInt(1));
                }
                System.out.println("Total size of the role array is :" +totalRoles.size());
                if(totalRoles.contains(3))
                {
                    System.out.println("User is found to be the System Admin and Values of k is changed to 2");
                    k=2; // Admin role exist in the user_role table for user;
                    return k;
                }
                else if(totalRoles.contains(4))
                {
                    System.out.println("User is found to be an Institute Admin and Values of k is changed to 3");
                    k=3; // Institute Admin Role Exist in the user_role table for the user;
                    return k;
                }
                else if(totalRoles.contains(6))
                {
                    System.out.println("User is found to be a Normal Employee and Values of k is changed to 4");
                    k=4; // Institute Admin Role Exist in the user_role table for the user;
                    return k;
                }
            }
            else
            {
                System.out.println("K is  0 that means UserName or Password is invalid either in LoginDB or in user_master");
            }
            
            return k;   
        }
        catch(Exception e)
            {
                e.printStackTrace();
                return -1;
            }
        
    }
    
    public void LastLoginStatusUpdate(int userId) {
        try{
            int userid =  CheckUserExistInLoginStatus(userId);
            String currentDate = new CommonDB().getDate();
            
            Connection connection = new CommonDB().getLoginDBConnection();
            if(!(userid > 0))
            {
                System.out.println("Inserting records into UserLastStatus");
                PreparedStatement pst;
                ResultSet rst;
                pst = connection.prepareStatement("insert into userlaststatus (userid, lastusedlang, lastlogincomponent, lastlogindate, status) values(?,?,?,?,?)");
                pst.setInt(1, userId);
                pst.setString(2, "English");
                pst.setString(3, "payroll");
                pst.setString(4, currentDate);
                pst.setInt(5, 1);

                pst.executeUpdate();
                pst.close();
                connection.close();
            }
            else
            {
                System.out.println("Updating records into UserLastStatus");
                PreparedStatement pst;
                pst=connection.prepareStatement("update userlaststatus set lastlogincomponent=?, lastlogindate=? where userid=?");
                pst.setString(1, "payroll");
                pst.setString(2, currentDate);
                pst.setInt(3, 1);
                pst.executeUpdate();
                pst.close();
                connection.close();
            }    
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
            
    }
    
    public void LastLogoutStatusUpdate(int userId){
        try{
            System.out.println("Updating LastVisitedComponent in UserLastStatus");
            Connection connection = new CommonDB().getLoginDBConnection();
            PreparedStatement pst;
            pst=connection.prepareStatement("update userlaststatus set lastvisitedcomponent=? where userid=?");
            pst.setString(1, "payroll");
            pst.setInt(2, userId);
            pst.executeUpdate();
            pst.close();
            connection.close();            
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
    }
    

    public int getOrgcode(String email)    {
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("select user_org_id from user_master where user_name=?");
            ps.setString(1, email);
            rs=ps.executeQuery();
            rs.next();
            int s = rs.getInt(1);
            rs.close();
            ps.close();
            c.close();
            return s;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return -1;
        }
    }
    public String getpassword(String email)    {
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("select user_pass from user_master where user_name=?");
            ps.setString(1, email);
            rs=ps.executeQuery();
            rs.next();
            String s = rs.getString(1);
            rs.close();
            ps.close();
            c.close();
            return s;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    
    public int getUserId(String email)    {
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("select user_id from user_master where user_name=?");
            ps.setString(1, email);
            rs=ps.executeQuery();
            rs.next();
            int id = rs.getInt(1);
            rs.close();
            ps.close();
            c.close();
            return id;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return -1;
        }
    }
    
    
    /*
    * Check if user's Email Address exist in user_master table;  
    *
    */
    
    public int CheckUserExistInUserMaster(String email)
    {
        try
        {
            int userId = 0;
            Connection connection = new CommonDB().getConnection();
            PreparedStatement pst;
            ResultSet rst;
            pst = connection.prepareStatement("select user_id from user_master where user_name='"+email+"' and flag = '"+1+"'");
            rst = pst.executeQuery();
            while(rst.next()){
                userId = rst.getInt(1);
            }
            return userId;
            
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return -1;
        }
    }
    
    public int CheckUserExistInLoginDB(String email)
    {
        try
        {
            int userId = 0;
            Connection connection = new CommonDB().getLoginDBConnection();
            PreparedStatement pst;
            ResultSet rst;
            pst = connection.prepareStatement("select id from bgasuser where username='"+email+"' and status = '"+1+"'");
            rst = pst.executeQuery();
            while(rst.next()){
                userId = rst.getInt(1);
            }
            return userId;
            
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return -1;
        }
        
    }
    
    public int CheckUserExistInLoginStatus(int id){
        try
        {
            int userId = 0;
            Connection connection = new CommonDB().getLoginDBConnection();
            PreparedStatement pst;
            ResultSet rst;
            pst = connection.prepareStatement("select id from userlaststatus where userid='"+id+"' and status = '"+1+"'");
            rst = pst.executeQuery();
            while(rst.next()){
                userId = rst.getInt(1);
            }
            return userId;
            
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return -1;
        }
     
     }     
        
}
