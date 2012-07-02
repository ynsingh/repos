/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts;
import com.myapp.struts.hbm.*;
import java.util.List;
import org.hibernate.*;



/**
 *
 * @author Dushyant
 */
public class DuplicateEntry  {
    

    

       public static boolean checkDuplicateEntry(String table_name,String column_name,String value,String library_id,String sublibrary_id)
    {
    Session session = HibernateUtil.getSessionFactory().openSession();
        try
        {
       session.beginTransaction();
       if(column_name.equals("memid"))
           column_name="memId";
        Query query = session.createSQLQuery("select * from "+table_name+" where "+column_name+"='"+value+"' and library_id='"+library_id+"' and sublibrary_id='"+sublibrary_id+"'");
        //query.executeUpdate();
        List list = query.list();
        if(list==null)
        {
            return true;
        }
            if(list.isEmpty())
         return true;
        
        }
        catch(Exception e)
        {
        System.out.println(e);
        
        }
        return false;
    }

   public static boolean checkDuplicateEntry1(String table_name,String column_name,String value,String library_id,String sublibrary_id)
    {
    Session session = HibernateUtil.getSessionFactory().openSession();
        try
        {
       session.beginTransaction();
       if(column_name.equals("memid"))
           column_name="memId";
        Query query = session.createSQLQuery("select * from "+table_name+" where "+column_name+"=:value and library_id=:library_id1 and sublibrary_id=:sublibrary_id1");
        query.setString("value", value);
        query.setString("library_id1", library_id);
        query.setString("sublibrary_id1", sublibrary_id);
       
        CirMemberDetail list = (CirMemberDetail)query.uniqueResult();
        System.out.println("list="+list);
        if(list==null)
        {
            return true;
        }
            

        }
        catch(Exception e)
        {
        System.out.println(e);

        }
        return false;
    }
   
}
