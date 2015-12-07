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
import org.smvdu.payroll.api.EncryptionUtil;
import org.smvdu.payroll.beans.UserGroup;
import org.smvdu.payroll.beans.UserInfo;
import org.smvdu.payroll.user.changePassword;

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
    
    /** This method is used for change password
     * @param pass(user password)
     * @param userName 
     * @return true
     */
    public boolean editPass(String pass,String userName)   {
        try
        {
            pass= new EncryptionUtil().createDigest("MD5",pass);
            String changepassinDb=new changePassword().changePaswordInLoginDB(pass, userName);
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
                pass=new EncryptionUtil().createDigest("MD5",pass);
                pst1 = connectLogin.prepareStatement("select id, status from edrpuser where username='"+user+"' and password='"+pass+"' and status='"+1+"'");
                ResultSet rst1;
                rst1 = pst1.executeQuery();
                pst1.clearParameters();
                if(rst1.next() == true)
                {
                    int userId = rst1.getInt(1);
                    k = 1; 
                    //System.out.println("User is varified in LoginDB and Value of K is changed to 1");
                    LastLoginStatusUpdate(userId);
                }
                else
                {
                    System.out.println("UserName or Password is invalid in LoginDB");
                }
            }
            else
            {
                //System.out.println("LoginDb does not Exist");
                //System.out.println("Varifying Username Password in user_master");
                pass=new EncryptionUtil().createDigest("MD5",pass);
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
                //System.out.println("UserId of the user In User_master is :"+userId);
                PreparedStatement pst2;
                pst2 = connectPl.prepareStatement("select role_id from user_roles where user_id='"+userId+"'");
                ResultSet rst2;
                rst2 = pst2.executeQuery();
                ArrayList<Integer> totalRoles = new ArrayList<Integer>();
                //System.out.println("ArrayList is created for containing all the roles for the user");
                while(rst2.next())
                {
                    totalRoles.add(rst2.getInt(1));
                    //System.out.println("Value added in the Role Array is :" +rst2.getInt(1));
                }
                System.out.println("Total size of the role array is :" +totalRoles.size());
                if(totalRoles.contains(3))
                {
                    info.setUserRole("Super");
                    info.setUserRoleId(3);
                    System.out.println("User is found to be the System Admin and Values of k is changed to 2");
                    k=2; // Admin role exist in the user_role table for user;
                    return k;
                }
                else if(totalRoles.contains(4) && totalRoles.contains(6))
                {
                    System.out.println("User is found to be an Institute Admin and Employee and Values of k is changed to 3");
                    //info.setUserRole("4,6");
                    info.setUserRoleId(4);
                    info.setUserRoleId(6);
                    k=3; 
                    return k;
                }        
                /*else if(totalRoles.contains(4))
                {
                    info.setUserRole("Master User");
                    info.setUserRoleId(4);
                    System.out.println("User is found to be an Institute Admin and Values of k is changed to 3");
                    k=3; // Institute Admin Role Exist in the user_role table for the user;
                    return k;
                }*/
                else if(totalRoles.contains(6))
                {
                    info.setUserRole("Employee");
                    info.setUserRoleId(6);
                    System.out.println("User is found to be a Normal Employee and Values of k is changed to 4");
                    //System.out.println("info==role="+info.getUserRoleId());
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
            //System.out.println("Updating LastVisitedComponent in UserLastStatus");
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
            pst = connection.prepareStatement("select id from edrpuser where username='"+email+"' and status = '"+1+"'");
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
    
     public int getUserIDfromUserName (String UserName){
        try
        {
            //int userId = 0;
            Connection connection = new CommonDB().getConnection();
            PreparedStatement pst;
            ResultSet rst;
            pst = connection.prepareStatement("select user_id from user_master where user_name='"+UserName+"' ");
            rst = pst.executeQuery();
            rst.next();
            int userId = rst.getInt(1);
            //System.out.println("userID==in  getUserIDfromUserName =="+userId+":"+UserName);
           
            rst.close();
            pst.close();
            connection.close();
            return userId;
            
        }
        catch(Exception ex){
            ex.printStackTrace();
            return -1;
        }
     
     }
      public ArrayList<Integer> getuserRole(String userName,int orgId){   
        
        try
        {
            
            ArrayList<Integer> userRoles = new ArrayList<Integer>();
            Connection cn = new CommonDB().getConnection();
            PreparedStatement pst;
            int uid=getUserIDfromUserName(userName);
            //System.out.println("userid==in user roles method==="+uid +":"+userName);
            pst  = cn.prepareStatement("select role_id from user_roles where user_id = '"+uid+"' and  org_id='"+orgId+"'");
       
            ResultSet rst;
            rst = pst.executeQuery();
            while(rst.next())
            {
                UserInfo uroles = new UserInfo();
                uroles.setUserRoleId(rst.getInt(1));
                userRoles.add(uroles.getUserRoleId());
                //System.out.println(" user roles method==lst="+uroles.getUserRoleId()+":"+rst.getInt(1));
            }
            rst.close();
            pst.close();
            cn.close();
            return userRoles;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return null;
        } 
    }
      
      public String getEmpCodefromUserName (String UserName, int orgCode){
        try
        {
            Connection connection = new CommonDB().getConnection();
            PreparedStatement pst;
            ResultSet rst;
            pst = connection.prepareStatement("select emp_code from employee_master where emp_email='"+UserName+"' and emp_org_code='"+orgCode+"' ");
            rst = pst.executeQuery();
            //System.out.println("userID==in userrole==="+UserName);
            rst.next();
            String empCode = rst.getString(1);
            //System.out.println("userID==in rmployeecode from username==="+UserName+":"+orgCode+":"+empCode);
            rst.close();
            pst.close();
            connection.close();
            return empCode;
            
        }
        catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
     
     }
     public ArrayList<Integer> getuserTotalRole(String userName){   
        
        try
        {
            
            ArrayList<Integer> userRoles = new ArrayList<Integer>();
            Connection cn = new CommonDB().getConnection();
            PreparedStatement pst;
            int uid=getUserIDfromUserName(userName);
            //System.out.println("userid==in user getuserTotalRole method==="+uid +":"+userName);
            pst  = cn.prepareStatement("select role_id from user_roles where user_id = '"+uid+"'");
       
            ResultSet rst;
            rst = pst.executeQuery();
            while(rst.next())
            {
                UserInfo uroles = new UserInfo();
                uroles.setUserRoleId(rst.getInt(1));
                userRoles.add(uroles.getUserRoleId());
                //System.out.println(" user getuserTotalRole method==lst="+uroles.getUserRoleId()+":"+rst.getInt(1));
            }
            rst.close();
            pst.close();
            cn.close();
            return userRoles;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return null;
        } 
    } 
     public ArrayList<UserInfo> getuserTotalOrg(String userName){   
        
        try
        {
            ArrayList<UserInfo> insDetail = new ArrayList<UserInfo>();
            Connection cn = new CommonDB().getConnection();
            PreparedStatement pst;
            int uid=getUserIDfromUserName(userName);
            //System.out.println("userid==in user getuserTotalOrg method==="+uid +":"+userName);
            pst  = cn.prepareStatement("select distinct org_id from user_roles where user_id = '"+uid+"' ");
          
            ResultSet rst;
            rst = pst.executeQuery();
            while(rst.next())
            {
                UserInfo ins = new UserInfo();
                ins.setUserOrgCode(rst.getInt(1));
                insDetail.add(ins);
                //System.out.println(" user total org==="+ins.getUserOrgCode());
            }
            //System.out.println(" user total org==="+insDetail);
            rst.close();
            pst.close();
            cn.close();
            return insDetail;
         
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return null;
        } 
    } 
     public int getuserOrg(String userName){   
        
        try
        {
            Connection cn = new CommonDB().getConnection();
            PreparedStatement pst;
            ResultSet rst;
            int uid=getUserIDfromUserName(userName);
            //System.out.println("userid==in getuserOrg method==="+uid +":"+userName);
            pst  = cn.prepareStatement("select org_id from user_roles where user_id ='"+uid+"' ");
            rst = pst.executeQuery();
            rst.next();
            int orgcode=rst.getInt(1);
            //System.out.println("userid==in getuserOrg==="+orgcode);
            rst.close();
            pst.close();
            cn.close();
            return orgcode;
         
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return -1;
        } 
    } 
   public  int getRoleExists(String userName ,int orgId){   
        
        try
        {
            Connection cn = new CommonDB().getConnection();
            PreparedStatement pst;
            ResultSet rst;
            int uid=getUserIDfromUserName(userName);
            //System.out.println("userid==in getuserOrg method==="+uid +":"+userName);
            pst  = cn.prepareStatement("select role_id from user_roles where user_id ='"+uid+"' and org_id = '"+orgId+"' ");
            rst = pst.executeQuery();
            rst.next();
            int roleid=rst.getInt(1);
            //System.out.println("userid==in get active link===="+orgId+":----"+uid+":"+userName+":"+roleid);
            rst.close();
            pst.close();
            cn.close();
            return roleid;
         
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return -1;
        } 
    } 
   
               
}
