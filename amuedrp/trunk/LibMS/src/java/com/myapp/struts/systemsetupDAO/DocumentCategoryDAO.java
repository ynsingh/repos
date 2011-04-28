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
            return false;

            //  System.out.println(ex.toString());

        } finally {
            //session.close();
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

            tx.rollback();
            return false;

        }

        return true;

    }
         public static List<DocumentCategory> ListbookType(String library_id,String sublibrary_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("Select documentCategoryName,id.documentCategoryId From  DocumentCategory where id.libraryId =:library_id and id.sublibraryId=:sublibrary_id");
            query.setString("library_id", library_id);
            query.setString("sublibrary_id", sublibrary_id);
            query.setResultTransformer(Transformers.TO_LIST);
            return (List<DocumentCategory>)  query.list();

        //    List results = session.createCriteria(Cat.class).setProjection(Projections.projectionList().add(Projections.rowCount()).add(Projections.avg("weight")).add(Projections.max("weight")).add(Projections.groupProperty("color"))).list();


         /*   Criteria criteria = session.createCriteria(BookCategory.class)
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .setProjection(Projections.projectionList())
                    .add(Projections.distinct(Property.forName("id.bookType"))
                    .add(Projections.distinct(Property.forName("detail")));*/
            //   criteria.setProjection(projList);

/*Criteria criteria=session.createCriteria(BookCategory.class)
        .add(Restrictions.eq("id.libraryId", library_id));

ProjectionList p=Projections.projectionList();
p.add(Projections.property("id.bookType"));
p.add(Projections.property("detail"));
criteria.setProjection(Projections.distinct(p));*/

            // .add(Restrictions.conjunction()

            // List<BookCategory> doclist =  criteria.setProjection(Projections.groupProperty("detail")).list();
         //   return (List<BookCategory>) criteria.list();
        } finally {
            session.close();
        }
    }


         public static List<DocumentDetails> searchDocumentDetailByDocumentCategory(String library_id, String sublibrary_id, String doc_category_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(DocumentDetails.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.sublibraryId", sublibrary_id))
                    .add(Restrictions.eq("bookType", doc_category_id)));
            return (List<DocumentDetails>) criteria.list();


        } finally {
            session.close();
        }
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



        } catch (Exception ex) {
            System.out.println(ex);
            return false;

            //  System.out.println(ex.toString());

        } finally {
            // session.close();
        }
        return true;

    }
    public static DocumentCategory searchDocumentCategory(String library_id, String sublibrary_id, String doc_category_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(DocumentCategory.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.sublibraryId", sublibrary_id))
                    .add(Restrictions.eq("id.documentCategoryId", doc_category_id)));
            return (DocumentCategory) criteria.uniqueResult();


        } finally {
            session.close();
        }
    }
    public static List<DocumentCategory> searchDocumentCategory(String library_id, String sublibrary_id)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(DocumentCategory.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.sublibraryId", sublibrary_id))
                   );
            return (List<DocumentCategory>) criteria.list();


        } finally {
           // session.close();
        }
    }
    public static List<DocumentCategory> listdoccategory(String library_id,String sublibrary_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(DocumentCategory.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.sublibraryId",sublibrary_id))
                   );
            return (List<DocumentCategory>) criteria.list();


        } finally {
           
        }
}

    public static DocumentCategory searchDocumentCategoryByName(String library_id, String sublibrary_id, String doc_category_name) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(DocumentCategory.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.sublibraryId", sublibrary_id))
                    .add(Restrictions.eq("documentCategoryName", doc_category_name)));
            return (DocumentCategory) criteria.uniqueResult();


        } finally {
            session.close();
        }
    }
    public static List<DocumentCategory> listdoccategory1(String library_id,String sublibrary_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String status="NotIssuable";

        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(DocumentCategory.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.ne("issueCheck", status))
                    .add(Restrictions.eq("id.sublibraryId",sublibrary_id))
                   );
            return (List<DocumentCategory>) criteria.list();


        } finally {

        }
}
}
