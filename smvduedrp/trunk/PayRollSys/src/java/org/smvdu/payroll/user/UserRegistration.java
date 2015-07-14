/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.smvdu.payroll.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.smvdu.payroll.beans.db.CommonDB;
import org.smvdu.payroll.beans.db.UserDB;

/**
 *
 * @author Seema Pal
 */
public class UserRegistration {
    
    public Exception EmployeeRegistration(String emailId,String password,String phoneno,String firstname,String lastname,String address,int orgId)
    {
       
        Connection connection = new CommonDB().getConnection();
        Connection connectionLogin = new CommonDB().getLoginDBConnection();
        try{
            PreparedStatement pst = null; 
            PreparedStatement pst1 = null; 
            PreparedStatement pst2 = null; 
            PreparedStatement pst3 = null; 
            PreparedStatement pst4 = null; 
            //ResultSet rst = null;
            UserDB ud = new UserDB();
            int userid = ud.CheckUserExistInUserMaster(emailId);
            //System.out.println("User in user_master - " +userid);
            if(!(userid > 0)){
                pst = connection.prepareStatement("insert into user_master(user_name,user_pass,user_profile_id,flag) values('"+emailId+"','"+password+"','"+0+"','"+1+"')");
                pst.executeUpdate();
                pst.clearParameters();
                pst.close();
                //System.out.println("Records inserted into user_master for "+emailId);
           
                boolean dbExist = new CommonDB().checkLoginDBExists();
                if(dbExist){
                    System.out.println("Login Database exist");
                    int id = ud.CheckUserExistInLoginDB(emailId);
                    if(!(id > 0)){
                        //System.out.println("User does not exist in login database");
                        String component = "payroll";
                        pst1 = connectionLogin.prepareStatement("insert into bgasuser(username,password,email,componentreg,mobile,status) values('"+emailId+"','"+password+"','"+emailId+"','"+component+"','"+phoneno+"','"+1+"')");
                        pst1.executeUpdate();
                        pst1.clearParameters();
                        pst1.close();
                        //System.out.println("Records inserted into bgasuser of LoginDB for "+emailId);    
                        int userIdInLoginDB = ud.CheckUserExistInLoginDB(emailId);
                        //System.out.println("User Now exist in Login Database exist with userid " +userIdInLoginDB);
                    
                        //System.out.println("User Now exist in Login Database exist with userid " +userIdInLoginDB);
                        pst2 = connectionLogin.prepareStatement("insert into userprofile(userid,firstname,lastname,address,status) values('"+userIdInLoginDB+"','"+firstname+"','"+lastname+"','"+address+"','"+1+"')");
                        pst2.executeUpdate();
                        pst2.clearParameters();
                        pst2.close();
                        System.out.println("Records inserted into userprofile of LoginDB for "+emailId);
                    
                    }
                    else
                    {
                         System.out.println("Entry already Exist in users of LoginDB for - "+emailId);
                         ArrayList<String> totalComponents = getTotalComponent(emailId);
                         String components = null;
                                 /*
                                *   write code for retrieving component details. split that details saprating by commma into
                                *   an arraylist.
                                *   if that arraylist contain payroll. than do nothing otherwise insert payroll into that
                                *   column.
                                */
                                
                         boolean flag = totalComponents.contains("payroll");
                         if(flag){
                            System.out.println("User is already Registered with payroll system");
                            System.out.println("Do nothing in LoginDB");
                         }
                         else{
                            //System.out.println("User is first time Registering in Payroll SO insert 'payroll' in Componentreg field in LoginDB");
                            components = components.concat(",payroll");
                            pst3 = connectionLogin.prepareStatement("update bgasuser set componentreg=? where username=?");
                            pst3.setString(1,components);
                            pst3.setString(2, emailId);
                            pst3.executeUpdate();
                            pst3.clearParameters();
                            pst3.close();
                            System.out.println("payroll is inserted in bgas user in componentreg field");
                         }      
                                    
                     }//close else part if user exists in userLogindb           
                }//close if condition userLogin db exists     
            } //close if condtion when user is not exists in user master table
            
           /*User already exists in user_master table;*/
            else{
                System.out.println("Entry already Exist in user_master for - "+emailId);
                System.out.println("Do nothing in user master table");
            }
            int UserId = ud.getUserId(emailId);
            //System.out.println("Records inserted into user_roles as Employee for '"+emailId+"' and OrgId is :'"+orgId+"'" );
            pst4 = connection.prepareStatement("insert into user_roles(user_id, role_id, org_id) values('"+UserId+"','"+6+"','"+orgId+"')");
            pst4.executeUpdate();
            pst4.clearParameters();
            pst4.close();
            connection.close();
            connectionLogin.close();        
            return null;  
       } 
        catch (SQLException e)
        {
            if (connection != null || connectionLogin != null)
            {
                try
                {
                    connection.rollback();
                    connectionLogin.rollback();
                } // rollback on error
                catch (SQLException i)
                {
                }
            }
            e.printStackTrace();
        } 
        finally
        {
            if (connection != null)
            {
                try
                {
                  connection.close();
                
                } catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }
            return null;
        }
           
    }
    public ArrayList<String> getTotalComponent(String emailId){  
        Connection connL = new CommonDB().getLoginDBConnection();
        ArrayList<String> totalComponents = new ArrayList<String>();
        PreparedStatement pst = null; 
        ResultSet rst;
        try
        {
            pst = connL.prepareStatement("select componentreg from bgasuser where username='"+emailId+"'");
            rst = pst.executeQuery();
            rst.next();
            String components = rst.getString(1);
            for (String componentName : components.split(",")){
                totalComponents.add(componentName);
                //System.out.println("User is already Registered with "+componentName);
            }
            rst.close();
            pst.close();
            connL.close();
            return totalComponents;
            
        }
         catch(Exception ex)
        {
            ex.printStackTrace();
            return null;
        } 
    }    
    
}
