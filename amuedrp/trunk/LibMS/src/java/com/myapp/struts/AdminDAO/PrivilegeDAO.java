/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.AdminDAO;
import com.myapp.struts.hbm.*;
import com.myapp.struts.hbm.HibernateUtil;
import java.util.List;
import org.hibernate.Transaction;
import org.hibernate.Session;
import org.hibernate.Query;
import org.hibernate.transform.Transformers;
/**
 * Developed By : Kedar Kumar
 * Modified By  : 21-Feb-2011
 * Use to Check the Multiple occurance of Library_Id in Library POJO
 */
public class PrivilegeDAO {


   static  Integer maxNewRegId;
   static Query query;


  public static  boolean update(Privilege obj)
{
         Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            if(obj!=null)
            session.update(obj);
            
            tx.commit();
        }
        catch (RuntimeException e) {

                tx.rollback();
                return false;

        }
        finally{
        session.close();
        }

   return true;

}



 public static  Privilege getPrivilege(String library_id,String sublibrary_id,String staff_id)
{
        Session session = HibernateUtil.getSessionFactory().openSession();
Privilege obj=null;
        try
        {
            session.beginTransaction();
            query = session.createQuery("FROM  Privilege  WHERE id.libraryId =:library_id and sublibraryId =:sublibrary_id and id.staffId =:staff_id");
            query.setString("library_id", library_id);
            query.setString("sublibrary_id", sublibrary_id);
            query.setString("staff_id", staff_id);
obj= ( Privilege) query.uniqueResult();
        }
        catch(Exception e)
        {
        System.out.println(e.toString());
        }
        finally
        {
           session.close();
        }
       
return obj;
}
 

public static  boolean insert(Privilege obj)
{
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;


        try
        {
            tx = (Transaction) session.beginTransaction();

            session.save(obj);
            tx.commit();



        }
        catch (Exception ex)
        {
            tx.rollback();
         System.out.println(ex.toString());
            return false;

       

        }
        finally
        {
      session.close();
        }
   return true;

}
public static Privilege searchStaffLogin(String staff_id,String library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
Privilege obj=null;
        try {
            session.beginTransaction();
          query = session.createQuery("FROM  Privilege WHERE id.staffId =:staff_id and id.libraryId=:library_id");
            query.setString("staff_id", staff_id);
            query.setString("library_id", library_id);
           obj= ( Privilege) query.uniqueResult();
        }
        catch(Exception e){
        System.out.println(e);
        }
        finally {
       session.close();
        }
return obj;
}
public static boolean DeleteStaffPrivilege(String staff_id,String library_id,String sublibrary_id) {
      Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;


        try
        {
            tx = (Transaction) session.beginTransaction();
            Query query = session.createQuery("Delete From  Privilege where id.libraryId =:library_id and id.staffId =:staff_id and sublibraryId= :sublibrary_id  ");
            query.setString("staff_id", staff_id);
            query.setString("library_id", library_id);
            query.setString("sublibrary_id", sublibrary_id);

            query.executeUpdate();

            Query query1 = session.createQuery("Delete From  AcqPrivilege where id.libraryId =:library_id and id.staffId =:staff_id and sublibraryId= :sublibrary_id  ");
            query1.setString("staff_id", staff_id);
            query1.setString("library_id", library_id);
            query1.setString("sublibrary_id", sublibrary_id);

            query1.executeUpdate();

            Query query2 = session.createQuery("Delete From  SerPrivilege where id.libraryId =:library_id and id.staffId =:staff_id and sublibraryId= :sublibrary_id  ");
            query2.setString("staff_id", staff_id);
            query2.setString("library_id", library_id);
            query2.setString("sublibrary_id", sublibrary_id);

            query2.executeUpdate();

            Query query3 = session.createQuery("Delete From  CatPrivilege where id.libraryId =:library_id and id.staffId =:staff_id and sublibraryId= :sublibrary_id  ");
            query3.setString("staff_id", staff_id);
            query3.setString("library_id", library_id);
            query3.setString("sublibrary_id", sublibrary_id);

            query3.executeUpdate();

              Query query4 = session.createQuery("Delete From  CirPrivilege where id.libraryId =:library_id and id.staffId =:staff_id and sublibraryId= :sublibrary_id  ");
            query4.setString("staff_id", staff_id);
            query4.setString("library_id", library_id);
            query4.setString("sublibrary_id", sublibrary_id);

            query4.executeUpdate();




            tx.commit();



        }
        catch (Exception ex)
        {
            System.out.println(ex);
            tx.rollback();
             return false;

       //  System.out.println(ex.toString());

        }
        finally
        {
       session.close();
        }
   return true;


}
public static boolean DeleteStaff(String staff_id,String library_id,String sublibrary_id) {
      Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;


        try
        {
            tx = (Transaction) session.beginTransaction();
             query = session.createQuery("Delete From  Privilege where id.libraryId =:library_id and id.staffId =:staff_id and sublibraryId= :sublibrary_id  ");
            query.setString("staff_id", staff_id);
            query.setString("library_id", library_id);
            query.setString("sublibrary_id", sublibrary_id);

            query.executeUpdate();





            tx.commit();



        }
        catch (Exception ex)
        {
            System.out.println(ex);
            tx.rollback();
             return false;

       //  System.out.println(ex.toString());

        }
        finally
        {
      session.close();
        }
   return true;


}

public static boolean DeleteStaff(String staff_id,String library_id) {
      Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;


        try
        {
            tx = (Transaction) session.beginTransaction();
            query = session.createQuery("Delete From  Privilege where id.libraryId =:library_id and id.staffId =:staff_id ");
            query.setString("staff_id", staff_id);
            query.setString("library_id", library_id);


            query.executeUpdate();
            tx.commit();



        }
        catch (Exception ex)
        {
            tx.rollback();
            System.out.println(ex);
             return false;

       //  System.out.println(ex.toString());

        }
        finally
        {
     session.close();
        }
   return true;


}

 public static  List getPrivilege1(String library_id,String sublibrary_id,String staff_id)
{
        Session session = HibernateUtil.getSessionFactory().openSession();
List list=null;
        try
        {
            session.beginTransaction();
            query = session.createSQLQuery("select * from  privilege p WHERE p.library_id =:library_id and p.sublibrary_id =:sublibrary_id and p.staff_id =:staff_id");
            
            query.setString("library_id", library_id);
            query.setString("sublibrary_id", sublibrary_id);
            query.setString("staff_id", staff_id);
            query.setResultTransformer(Transformers.TO_LIST);
            list=(List) query.list();
        }
        catch(Exception e)
        {
        System.out.println(e.toString());
        }
        finally
        {
      session.close();
            
        }
return list;

}

}
