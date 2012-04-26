/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.systemsetupDAO;

import com.myapp.struts.hbm.*;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

/**
 *
 * @author EdRP-05
 */
public class DocumentCategoryDAO {
    public static boolean insert(DocumentCategory obj) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;


        try {
            tx = (Transaction) session.beginTransaction();

            session.save(obj);
            tx.commit();



        } catch (Exception ex) {
            ex.printStackTrace();
            tx.rollback();
            return false;

           

        } finally {
           session.close();
        }
        return true;

    }
        public static boolean update(DocumentCategory obj) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.update(obj);
            tx.commit();
        } catch (RuntimeException e) {
e.printStackTrace();
            tx.rollback();
            return false;

        }

        return true;

    }
         public static List<DocumentCategory> ListbookType(String library_id,String sublibrary_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<DocumentCategory> obj=null;
        try {
            session.beginTransaction();
            Query query = session.createQuery("Select documentCategoryName,id.documentCategoryId From  DocumentCategory where id.libraryId =:library_id and id.sublibraryId=:sublibrary_id");
            query.setString("library_id", library_id);
            query.setString("sublibrary_id", sublibrary_id);
            query.setResultTransformer(Transformers.TO_LIST);
            obj= (List<DocumentCategory>)  query.list();
session.getTransaction().commit();
       
        }
        catch(Exception e){
        e.printStackTrace();
        }
        finally {
            session.close();
        }
        return obj;
    }


         public static List<DocumentDetails> searchDocumentDetailByDocumentCategory(String library_id, String sublibrary_id, String doc_category_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
List<DocumentDetails> obj=null;
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(DocumentDetails.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.sublibraryId", sublibrary_id))
                    .add(Restrictions.eq("bookType", doc_category_id)));
           obj= (List<DocumentDetails>) criteria.list();
session.getTransaction().commit();

        }  catch(Exception e){
        e.printStackTrace();
        }
        finally {
            session.close();
        }
        return obj;
    }


        public static boolean delete(String library_id,String sublibrary_id,String location_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;


        try {
            tx = (Transaction) session.beginTransaction();
            Query query = session.createQuery("Delete From DocumentCategory  where id.libraryId = :library_id and id.sublibraryId = :sublibraryId and id.documentCategoryId= :documentCategoryId");
           query.setString("library_id", library_id);
            query.setString("sublibraryId", sublibrary_id);
            query.setString("documentCategoryId", location_id);
            query.executeUpdate();
            tx.commit();



        }  catch(Exception e){
        e.printStackTrace();
        tx.rollback();
        return false;
        }
        finally {
            session.close();
        }
       return true;
    }

    public static DocumentCategory searchDocumentCategory(String library_id, String sublibrary_id, String doc_category_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
 DocumentCategory obj=null;
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(DocumentCategory.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.sublibraryId", sublibrary_id))
                    .add(Restrictions.eq("id.documentCategoryId", doc_category_id)));
           obj=(DocumentCategory) criteria.uniqueResult();
session.getTransaction().commit();

        }  catch(Exception e){
        e.printStackTrace();
        }
        finally {
            session.close();
        }
        return obj;
    }

    public static List<DocumentCategory> searchDocumentCategory(String library_id, String sublibrary_id)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
 List<DocumentCategory> obj=null;
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(DocumentCategory.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.sublibraryId", sublibrary_id))
                   );
           obj= (List<DocumentCategory>) criteria.list();
session.getTransaction().commit();

        }  catch(Exception e){
        e.printStackTrace();
        }
        finally {
            session.close();
        }
        return obj;
    }

    public static List<DocumentCategory> listdoccategory(String library_id,String sublibrary_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
 List<DocumentCategory> obj=null;
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(DocumentCategory.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.sublibraryId",sublibrary_id))
                   );
           obj= (List<DocumentCategory>) criteria.list();
session.getTransaction().commit();

        }  catch(Exception e){
        e.printStackTrace();
        }
        finally {
            session.close();
        }
        return obj;
    }


    public static DocumentCategory searchDocumentCategoryByName(String library_id, String sublibrary_id, String doc_category_name) {
        Session session = HibernateUtil.getSessionFactory().openSession();
DocumentCategory obj=null;
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(DocumentCategory.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.sublibraryId", sublibrary_id))
                    .add(Restrictions.eq("documentCategoryName", doc_category_name)));
            obj= (DocumentCategory) criteria.uniqueResult();
session.getTransaction().commit();

        }  catch(Exception e){
        e.printStackTrace();
        }
        finally {
            session.close();
        }
        return obj;
    }

    public static List<DocumentCategory> listdoccategory1(String library_id,String sublibrary_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String status="NotIssuable";
List<DocumentCategory> obj=null;
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(DocumentCategory.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.ne("issueCheck", status))
                    .add(Restrictions.eq("id.sublibraryId",sublibrary_id))
                   );
           obj= (List<DocumentCategory>) criteria.list();
session.getTransaction().commit();

        }  catch(Exception e){
        e.printStackTrace();
        }
        finally {
            session.close();
        }
        return obj;
    }

}
