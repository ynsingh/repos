/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.SerialDAO;

import com.myapp.struts.hbm.HibernateUtil;
import com.myapp.struts.hbm.SerLanguage;
import com.myapp.struts.hbm.SerNewEntry;
import com.myapp.struts.hbm.SerPublisher;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author edrp02
 */
public class SerialDAO {


    public static SerNewEntry getSerBiblio(String library_id, String sub_library_id, String new_serial_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
       SerNewEntry obj=null;

        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(SerNewEntry.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.sublibraryId", sub_library_id))
                    .add(Restrictions.eq("id.newSerialId", new_serial_id)));
            obj= (SerNewEntry) criteria.uniqueResult();
        }
        catch(Exception e)
        {
        e.printStackTrace();
        }
        finally {
            session.close();
        }
        return obj;
    }

public static List getBiblio(String library_id,String sublibrary_id,String search_by, String search_keyword, String sort_by) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List obj=null;

        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(SerNewEntry.class)
                    
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.sublibraryId", sublibrary_id))
                    .add(Restrictions.ilike(search_by,search_keyword+"%"))
                    .addOrder(Property.forName(sort_by).asc());
            obj= (List) criteria.list();
        }   catch(Exception e)
        {
        e.printStackTrace();
        }
        finally {
            session.close();
        }
        return obj;
    }


    public static List searchSerial(String library_id, String sub_library_id,String title) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List obj=null;
        try {
             session.beginTransaction();
            Criteria criteria = session.createCriteria(SerNewEntry.class)
                    .add(Restrictions.conjunction().add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.sublibraryId", sub_library_id))
                    .add(Restrictions.eq("title",title)));
           obj= criteria.list();
        }  catch(Exception e)
        {
        e.printStackTrace();
        }
        finally {
            session.close();
        }
        return obj;
    }



    public static SerNewEntry searchSerialTitle(String library_id, String sub_library_id,String title) {
        Session session = HibernateUtil.getSessionFactory().openSession();
       SerNewEntry obj=null;
        try {
              session.beginTransaction();
            Criteria criteria = session.createCriteria(SerNewEntry.class)
                    .add(Restrictions.conjunction().add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.sublibraryId", sub_library_id))
                    .add(Restrictions.eq("title",title)));
            obj=(SerNewEntry) criteria.uniqueResult();
        }  catch(Exception e)
        {
        e.printStackTrace();
        }
        finally {
            session.close();
        }
        return obj;
    }


  static   Integer id=0;
    public static Integer returnMaxSerialId(String library_id, String sublibrary_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
         


        try {
        session.beginTransaction();
            Criteria criteria = session.createCriteria(SerNewEntry.class);
            Criterion a = Restrictions.eq("id.libraryId", library_id);
            Criterion b = Restrictions.eq("id.sublibraryId", sublibrary_id);
            LogicalExpression le = Restrictions.and(a, b);
            String maxbiblio = (String)criteria.add(le).setProjection(Projections.max("id.newSerialId")).uniqueResult();
            System.out.println("!!!!!!!!!!!!"+maxbiblio);

            if(maxbiblio==null)
            {
             id=0;
            }
            else
            {
             id=Integer.parseInt(maxbiblio);
            }

            if (id == 0) {
                id = 1;
            } else {
                id++;
            }

         
           
        }  catch(Exception e)
        {
        e.printStackTrace();
        }
        finally {
            session.close();
        }
        return id;
    }


    public static void insert1(SerNewEntry obj) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx =(Transaction) session.beginTransaction();
            session.save(obj);
            tx.commit();
        } catch (RuntimeException e) {
           
            tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static SerLanguage searchLanguage(String library_id, String language_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
SerLanguage obj=null;
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(SerLanguage.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.languageId", language_id)));
            obj= (SerLanguage) criteria.uniqueResult();


        }   catch(Exception e)
        {
        e.printStackTrace();
        }
        finally {
            session.close();
        }
        return obj;
    }



   public static SerPublisher searchSerialPubisher(String library_id, String sub_library_id,String pub_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
         SerPublisher  obj=null;

        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(SerPublisher.class)
                    .add(Restrictions.conjunction().add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.sublibraryId", sub_library_id))
                    .add(Restrictions.eq("id.pubId",pub_id)));
            obj= (SerPublisher) criteria.uniqueResult();
        }   catch(Exception e)
        {
        e.printStackTrace();
        }
        finally {
            session.close();
        }
        return obj;
    }


     public static boolean insert(SerLanguage obj) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;


        try {
            tx = (Transaction) session.beginTransaction();

            session.save(obj);
            tx.commit();



        }   catch(Exception e)
        {
        e.printStackTrace();
        tx.rollback();
        return false;
        }
        finally {
            session.close();
        }
        return true;
    }

     public static boolean insert(SerPublisher obj) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;


        try {
            tx = (Transaction) session.beginTransaction();

            session.save(obj);
            tx.commit();



        }  catch(Exception e)
        {
        e.printStackTrace();
        tx.rollback();
        return false;
        }
        finally {
            session.close();
        }
        return true;
    }


     public static boolean update(SerLanguage obj) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.update(obj);
            tx.commit();
        }  catch(Exception e)
        {
        e.printStackTrace();
        tx.rollback();
        return false;
        }
        finally {
            session.close();
        }
        return true;
    }


  public static boolean update(SerPublisher obj) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.update(obj);
            tx.commit();
        }   catch(Exception e)
        {
        e.printStackTrace();
        tx.rollback();
        return false;
        }
        finally {
            session.close();
        }
        return true;
    }



    public static boolean update1(SerNewEntry obj) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.update(obj);
            tx.commit();
        }  catch(Exception e)
        {
        e.printStackTrace();
        tx.rollback();
        return false;
        }
        finally {
            session.close();
        }
        return true;
    }


     public static boolean delete(SerLanguage obj) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.delete(obj);
            tx.commit();
        }   catch(Exception e)
        {
        e.printStackTrace();
        tx.rollback();
        return false;
        }
        finally {
            session.close();
        }
        return true;
    }



     public static boolean delete(SerPublisher obj) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.delete(obj);
            tx.commit();
        }   catch(Exception e)
        {
        e.printStackTrace();
        tx.rollback();
        return false;
        }
        finally {
            session.close();
        }
        return true;
    }


   public static void delete2(String serial_id, String library_id, String sub_library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
       

        try {
            tx = session.beginTransaction();
       
            Query query = session.createQuery("DELETE FROM SerNewEntry  WHERE  id.newSerialId = :newSerialId and id.libraryId = :libraryId and id.sublibraryId = :sublibraryId");

            query.setString("newSerialId", serial_id);
            query.setString("libraryId", library_id);
            query.setString("sublibraryId", sub_library_id);
            query.executeUpdate();
            tx.commit();
            
        }   catch(Exception e)
        {
        e.printStackTrace();
        tx.rollback();

        }
        finally {
            session.close();
        }
        
    }

}
