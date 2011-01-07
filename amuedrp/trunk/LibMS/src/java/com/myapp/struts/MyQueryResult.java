/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts;
import com.myapp.struts.opac.*;
import com.myapp.struts.MyConnection;
import java.sql.*;
/**
 *
 * @author Faraz
 */
public class MyQueryResult {
static Connection conn;
    static PreparedStatement stmt;
static ResultSet rst;
public static ResultSet getMyExecuteQuery(String query)
    {
    conn = MyConnection.getMyConnection();
    if(conn!=null)
    {
       try{
        stmt = conn.prepareStatement(query);
        rst = stmt.executeQuery();
        }catch(Exception e){
        System.out.println(e);
        rst=null;
        return rst;
        }
    }
    return rst;
    }


public static int getMyExecuteUpdate (String query)
    {
int i=0;
    conn = MyConnection.getMyConnection();
    if(conn!=null)
    {
       try{
        stmt = conn.prepareStatement(query);
        i = stmt.executeUpdate();
        }catch(Exception e){
         System.out.println(e);
        }
    }
    return i;
}

}
