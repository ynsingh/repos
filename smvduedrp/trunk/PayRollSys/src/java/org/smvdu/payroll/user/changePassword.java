/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.smvdu.payroll.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.smvdu.payroll.beans.db.CommonDB;
import org.smvdu.payroll.beans.db.UserDB;

/**
 *
 * @author guest
 */
public class changePassword {
    /** Method for change password of User in login database
     * @param pass
     * @param userName
     * @return String
     * @throws SQLException 
     */
    public String changePaswordInLoginDB(String pass,String userName){
    try
    {
        Connection c = new CommonDB().getConnection();
        Connection connectionLogin = new CommonDB().getLoginDBConnection();
        boolean dbExist = new CommonDB().checkLoginDBExists();
        if(dbExist){
            //System.out.println("Login Database exist");
            int id =new UserDB().CheckUserExistInLoginDB(userName);
            if(id > 0){
                PreparedStatement pst = null; 
                pst = connectionLogin.prepareStatement("update edrpuser set password=? where username=?");
                pst.setString(1, pass);
                pst.setString(2, userName);
                //System.out.println("Entry  Exist in edrpuser table  for - "+userName+"password===="+pass);
                pst.executeUpdate();
                pst.clearParameters();
                pst.close();
            }
            else{
                System.out.println("Entry not Exist in edrpuser table  for - "+userName);
            }
            
        } 
        PreparedStatement ps = null; 
        ps=c.prepareStatement("update user_master set user_pass=? where user_name=?");
        ps.setString(1, pass);
        ps.setString(2, userName);
        //System.out.println("Entry  in user_master table  for - "+userName+"password==="+pass);
        ps.executeUpdate();
        ps.close();
        c.close();
        connectionLogin.close();
        return "success";
        
    }
    catch(Exception e)
        {
            e.printStackTrace();
            return "fail";
        }
    }

}   
