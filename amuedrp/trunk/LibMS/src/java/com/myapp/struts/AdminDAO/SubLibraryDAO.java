/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.AdminDAO;
import com.myapp.struts.hbm.*;
import java.util.*;
import com.myapp.struts.hbm.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
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
            query = session.createQuery("Delete From  SubLibrary where id.libraryId =:library_id and id.sublibraryId= :sublibrary_id  ");
            query.setString("library_id", library_id);
            query.setString("sublibrary_id", sublibrary_id);

              query.executeUpdate();
            tx.commit();



        }
       catch (HibernateException e) {
	                tx.rollback();
	                e.printStackTrace();
	            }
        finally
        {
       session.close();
        }
   return true;


}

public static SubLibrary getMainSubLibraryId(String library_id,String sublibrary_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
      SubLibrary sublib=null;
        try {
            session.beginTransaction();
       query= session.createQuery("FROM  SubLibrary  WHERE id.libraryId =:library_id and id.sublibraryId=:sublibrary_id");
            query.setString("library_id", library_id);
            query.setString("sublibrary_id", sublibrary_id);
            sublib=(SubLibrary) query.uniqueResult();
session.getTransaction().commit();
           
        }
        catch (HibernateException e) {

	                e.printStackTrace();
	            }
        finally {
    session.close();
        }
         return sublib;

}

public static List<SubLibrary> getAllSubLibrary(String library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
List<SubLibrary> obj=null;
        try {
            session.beginTransaction();
             query = session.createQuery("FROM  SubLibrary  WHERE id.libraryId =:library_id");
            query.setString("library_id", library_id);


           obj=(List<SubLibrary>) query.list();
           session.getTransaction().commit();
        }
         catch (HibernateException e) {

	                e.printStackTrace();
	            }
        finally {
        session.close();
        }
 return obj;
}


public static SubLibrary getLibName(String library_id,String sublibrary_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
SubLibrary obj=null;
        try {
            session.beginTransaction();
            query = session.createQuery("FROM  SubLibrary  WHERE id.libraryId =:library_id and id.sublibraryId=:sublibrary_id");
            query.setString("library_id", library_id);
            query.setString("sublibrary_id",sublibrary_id);

           obj=(SubLibrary) query.uniqueResult();
           session.getTransaction().commit();
        }
        catch (HibernateException e) {

	                e.printStackTrace();
	            }
        finally {
         session.close();
        }
 return obj;
}

public static SubLibrary getSubLibraryId(String library_id,String sublibrary_name) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        SubLibrary sublib=null;
        try {
            session.beginTransaction();
            query = session.createQuery("FROM  SubLibrary  WHERE id.libraryId =:library_id and sublibName=:sublibrary_name");
            query.setString("library_id", library_id);
            query.setString("sublibrary_name",sublibrary_name);

           sublib=(SubLibrary) query.uniqueResult();
           session.getTransaction().commit();
        }
          catch (HibernateException e) {

	                e.printStackTrace();
	            }
        finally {
          HibernateUtil.getSessionFactory().close();
        }
 return sublib;
}

public static List<SubLibrary> searchAccessibleSubLib(String library_id,String sublibrary_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
List<SubLibrary> sublib=null;
        try {
            session.beginTransaction();
             query = session.createQuery("FROM  SubLibrary  WHERE id.libraryId =:library_id and id.sublibraryId=:sublibrary_id or id.sublibraryId=:library_id");
            query.setString("library_id", library_id);
            query.setString("sublibrary_id", sublibrary_id);
sublib=(List<SubLibrary>) query.list();
session.getTransaction().commit();
           
        }
          catch (HibernateException e) {

	                e.printStackTrace();
	            }
        finally {
          session.close();
        }
 return sublib;
}

public static List<SubLibrary> searchSubLib(String library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
List<SubLibrary> sublib=null;
        try {
            session.beginTransaction();
            query = session.createQuery("FROM  SubLibrary  WHERE id.libraryId =:library_id");
            query.setString("library_id", library_id);
           

            sublib=(List<SubLibrary>) query.list();
            session.getTransaction().commit();
        }
        catch (HibernateException e) {

	                e.printStackTrace();
	            }
        finally {
           session.close();
        }
return sublib;
}
public static List<SubLibrary> searchSubLib(String library_id,String sublibrary_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
List<SubLibrary> sublib=null;
        try {
            session.beginTransaction();
            query = session.createQuery("FROM  SubLibrary  WHERE id.sublibraryId !=:sublibrary_id and id.libraryId=:library_id");
            query.setString("library_id", library_id);
            query.setString("sublibrary_id", sublibrary_id);
sublib=(List<SubLibrary>) query.list();
session.getTransaction().commit();
            
        }
         catch (HibernateException e) {

	                e.printStackTrace();
	            }
        finally {
            session.close();
        }
return sublib;
}

  public static SubLibrary searchLibraryName(String sublibrary_name) {
        Session session = HibernateUtil.getSessionFactory().openSession();
SubLibrary sub=null;
        try {
            session.beginTransaction();
             query = session.createQuery("FROM  SubLibrary  WHERE sublibName=:sublibraryname");
           
            query.setString("sublibraryname",sublibrary_name);

       sub=(SubLibrary) query.uniqueResult();
       session.getTransaction().commit();
        }
        catch (HibernateException e) {

	                e.printStackTrace();
	            }
        finally {
            session.close();
        }
return sub;
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
        finally{
                 session.close();
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
         catch (RuntimeException e) {

                tx.rollback();
                return false;

        }
        finally
        {
          session.close();
        }
   return true;

}

}
