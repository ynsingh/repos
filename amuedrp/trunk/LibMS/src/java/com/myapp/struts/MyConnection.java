/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts;
import  java.sql.*;
import javax.sql.DataSource;
import javax.naming.*;

public class MyConnection {
    static Connection connection=null;
    static DataSource ds=null;
    static
    {


      


        try

       {
                 Context ctx=new InitialContext();
                if(ctx==null)
                    throw new RuntimeException("JNDI");

                    ds=(DataSource)ctx.lookup("java:/comp/env/jdbc/TestDB");
                    connection=ds.getConnection();





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
