/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.AdminDAO;
import com.myapp.struts.hbm.*;
import java.util.*;
import com.myapp.struts.hbm.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Transaction;
import org.hibernate.Session;
import org.hibernate.Query;
import org.hibernate.criterion.Projections;
/**
 * Developed By : Kedar Kumar
 * Modified By  : 18-Feb-2011
 * Use to Check the Multiple occurance of Library_Id in Library POJO
 */
public class SubLibraryDAO {


 
   static Query query;
   public static boolean Delete(String library_id,String sublibrary_id) {
      Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;


        try
        {
            tx = (Transaction) session.beginTransaction();
            Query query = session.createQuery("Delete From  SubLibrary where id.libraryId =:library_id and id.sublibraryId= :sublibrary_id  ");
            query.setString("library_id", library_id);
            query.setString("sublibrary_id", sublibrary_id);

              query.executeUpdate();
            tx.commit();



        }
        catch (Exception ex)
        {
             return false;

       //  System.out.println(ex.toString());

        }
        finally
        {
          //session.close();
        }
   return true;


}

public static SubLibrary getMainSubLibraryId(String library_id,String sublibrary_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
      Query query1;
        try {
            session.beginTransaction();
       query1= session.createQuery("FROM  SubLibrary  WHERE id.libraryId =:library_id and id.sublibraryId=:sublibrary_id");
            query1.setString("library_id", library_id);
            query1.setString("sublibrary_id", sublibrary_id);
            

           
        }
        finally {
      //      session.close();
        }
         return (SubLibrary) query1.uniqueResult();

}

public static List<SubLibrary> getAllSubLibrary(String library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            Query query1 = session.createQuery("FROM  SubLibrary  WHERE id.libraryId =:library_id");
            query1.setString("library_id", library_id);


            return (List<SubLibrary>) query1.list();
        }
        finally {
         //   session.close();
        }

}


public static SubLibrary getLibName(String library_id,String sublibrary_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            Query query1 = session.createQuery("FROM  SubLibrary  WHERE id.libraryId =:library_id and id.sublibraryId=:sublibrary_id");
            query1.setString("library_id", library_id);
            query1.setString("sublibrary_id",sublibrary_id);

            return (SubLibrary) query1.uniqueResult();
        }
        finally {
          //  session.close();
        }

}

public static SubLibrary getSubLibraryId(String library_id,String sublibrary_name) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            Query query1 = session.createQuery("FROM  SubLibrary  WHERE id.libraryId =:library_id and sublibName=:sublibrary_name");
            query1.setString("library_id", library_id);
            query1.setString("sublibrary_name",sublibrary_name);

            return (SubLibrary) query1.uniqueResult();
        }
        finally {
          //  session.close();
        }

}

public static List<SubLibrary> searchAccessibleSubLib(String library_id,String sublibrary_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            Query query1 = session.createQuery("FROM  SubLibrary  WHERE id.libraryId =:library_id and id.sublibraryId=:sublibrary_id or id.sublibraryId=:library_id");
            query1.setString("library_id", library_id);
            query1.setString("sublibrary_id", sublibrary_id);

            return (List<SubLibrary>) query1.list();
        }
        finally {
            //session.close();
        }

}

public static List<SubLibrary> searchSubLib(String library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            Query query1 = session.createQuery("FROM  SubLibrary  WHERE id.libraryId =:library_id");
            query1.setString("library_id", library_id);
           

            return (List<SubLibrary>) query1.list();
        }
        finally {
           session.close();
        }

}
public static List<SubLibrary> searchSubLib(String library_id,String sublibrary_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            Query query1 = session.createQuery("FROM  SubLibrary  WHERE id.sublibraryId !=:sublibrary_id and id.libraryId=:library_id");
            query1.setString("library_id", library_id);
            query1.setString("sublibrary_id", sublibrary_id);

            return (List<SubLibrary>) query1.list();
        }
        finally {
       //     session.close();
        }

}

  public static SubLibrary searchLibraryName(String sublibrary_name) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            Query query1 = session.createQuery("FROM  SubLibrary  WHERE sublibName=:sublibraryname");
           
            query1.setString("sublibraryname",sublibrary_name);

            return (SubLibrary) query1.uniqueResult();
        }
        finally {
      //      session.close();
        }

}
public static  boolean update(SubLibrary obj)
{
         Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.update(obj);
            tx.commit();
        }
        catch (RuntimeException e) {

                tx.rollback();
                return false;

        }

   return true;

}


public static  boolean insert(SubLibrary obj)
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
             return false;

       //  System.out.println(ex.toString());

        }
        finally
        {
          //session.close();
        }
   return true;

}

}
