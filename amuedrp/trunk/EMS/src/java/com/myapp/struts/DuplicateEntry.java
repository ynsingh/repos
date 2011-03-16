/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts;
import com.myapp.struts.hbm.HibernateUtil;
import java.sql.*;
import java.util.List;
import org.hibernate.*;

/**
 *
 * @author Dushyant
 */
public class DuplicateEntry  {
    static Connection con;
    static ResultSet rst;
    public static boolean checkDuplicateEntry(String table_name,String column_name,String value)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            Query query = session.createSQLQuery("select * from "+table_name+" where "+column_name+"='"+value+"'");
             List list = query.list();
            if (list.isEmpty()) return true;
            else return false;
        }
        finally {
            session.close();
        }
        

    }

       public static boolean checkDuplicateEntry(String table_name,String column_name,String value,String institute_id)
    {
    Session session = HibernateUtil.getSessionFactory().openSession();
        try
        {
       session.beginTransaction();
        Query query = session.createSQLQuery("select * from "+table_name+" where "+column_name+"='"+value+"' and institute_id='"+institute_id+"'");
        List list = query.list();
        if(list.isEmpty())
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
