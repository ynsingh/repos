/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.opac;
import  java.sql.*;

/**
 *
 * @author Dushyant
 */
public class MyConnection {
    static Connection connection=null;
    static
    {
    try{
        String driver="com.mysql.jdbc.Driver";
        String url="jdbc:mysql://localhost:3307/libms";
        Class.forName(driver).newInstance();
        connection=DriverManager.getConnection(url, "root", "");
        }
        catch(Exception e)
        {
         }
    }
    
    public static Connection getMyConnection()
    {
        
        return connection;
        }

}
