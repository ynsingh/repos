/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.systemsetupDAO;
import com.myapp.struts.hbm.*;
import java.util.*;
import com.myapp.struts.hbm.HibernateUtil;
import org.hibernate.Transaction;
import org.hibernate.Session;
import org.hibernate.Query;
/**
 *
 * @author edrp01
 */
public class NoticeDAO {
    
    static Query query;

    public static  boolean insert(Notices obj)
{
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;


        try
        {
            tx = (Transaction) session.beginTransaction();

            session.save(obj);
            tx.commit();

            return true;

        }
        catch (Exception ex)
        {
             return false;

       //  System.out.println(ex.toString());

        }
        finally
        {
          session.close();
        }


}

public static Notices getNoticeName(String library_id,String notice_id,String sub_lib) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            session.beginTransaction();
            Query query = session.createQuery("FROM  Notices  WHERE id.libraryId =:library_id and id.noticeId = :noticeId and id.sublibraryId=:sublibraryId");
            query.setString("library_id", library_id);
            query.setString("noticeId",notice_id);
            query.setString("sublibraryId", sub_lib);
            return (Notices) query.uniqueResult();
        }
        finally {
            session.close();
        }

}


public static  boolean update(Notices obj)
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

public static  boolean Delete(Notices obj)
{
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.delete(obj);
            tx.commit();
        }
        catch (RuntimeException e) {
               // System.out.println("FacultyDAO.Delete():*****"+e);
                tx.rollback();
                return false;

        }
        finally
        {
          session.close();
        }

   return true;

}
public static List<Notices> searchNotices(String library_id,String sub_lib) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            Query query1 = session.createQuery("FROM  Notices  WHERE id.libraryId=:library_id and id.sublibraryId=:sublibraryId");
            query1.setString("library_id", library_id);

             query1.setString("sublibraryId", sub_lib);
            return (List<Notices>) query1.list();
        }
        finally {
            session.close();
        }

}


}
