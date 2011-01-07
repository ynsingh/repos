/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts;
import java.sql.*;

/**
 *
 * @author Dushyant
 */
public class DuplicateEntry  {
    static Connection con;
    static ResultSet rst;
    public static boolean checkDuplicateEntry(String table_name,String column_name,String value)
    {

        try
        {
        con=MyConnection.getMyConnection();
        PreparedStatement stmt=con.prepareStatement("select * from "+table_name+" where "+column_name+"='"+value+"'");
        rst=stmt.executeQuery();
        if(rst.next())
         return true;
        else
            return false;
        }
        catch(Exception e)
        {
        System.out.println(e);
        return false;
        }

    }

       public static boolean checkDuplicateEntry(String table_name,String column_name,String value,String library_id)
    {

        try
        {
        con=MyConnection.getMyConnection();
        PreparedStatement stmt=con.prepareStatement("select * from "+table_name+" where "+column_name+"='"+value+"' and library_id='"+library_id+"'");
        rst=stmt.executeQuery();
        if(rst.next())
         return true;
        else
            return false;
        }
        catch(Exception e)
        {
        System.out.println(e);
        return false;
        }

    }

}
