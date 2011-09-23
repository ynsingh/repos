package com.myapp.struts.marc;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.List;
import org.hibernate.Query;
import com.myapp.struts.hbm.*;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.transform.Transformers;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author zeeshan
 */
public class NewMARCDAO {
     public void insert(Editmarc editmarc){
        System.out.println("inside HIb DAO for marc Entry");
    Session session = MarcHibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(editmarc);
            tx.commit();
        }
        catch (RuntimeException e) {
            if(editmarc != null)
                tx.rollback();
            throw e;
        }
        finally {
            session.close();
        }
    }

     public boolean deleteMARC(String tag){
         Session session = MarcHibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
          tx=  session.beginTransaction();
            Query query = session.createQuery("delete from Editmarc where id.tagnumber= :tag");
            query.setString("tag", tag);
            query.executeUpdate();
            tx.commit();
        }
        finally {
            session.close();
        }
        return true;
     }
      public void insertBiblio(Customizedbiblio biblio){
        System.out.println("inside New Marc DAO for Customized marc Entry!");
    Session session = MarcHibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(biblio);
            tx.commit();
        }
        catch (RuntimeException e) {
            if(biblio != null)
                tx.rollback();
            throw e;
        }
        finally {
            session.close();
        }
    }

     public List<String> show(){
     Session session = MarcHibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            Query query = session.createSQLQuery("select distinct tagnumber,tagname from editmarc where tagnumber < 100");
 
 query.setResultTransformer(Transformers.TO_LIST);

            return (List<String>) query.list();
        }
        finally {
            session.close();
        }
     }

      public List<String> show1(){
     Session session = MarcHibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            Query query = session.createSQLQuery("select distinct tagnumber,tagname from editmarc where tagnumber between 100 AND 200");

             query.setResultTransformer(Transformers.TO_LIST);

            return (List<String>) query.list();
        }
        finally {
            session.close();
        }
     }

       public List<String> show2(){
     Session session = MarcHibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            Query query = session.createSQLQuery("select distinct tagnumber,tagname from editmarc where tagnumber between 200 AND 300");

             query.setResultTransformer(Transformers.TO_LIST);

            return (List<String>) query.list();
        }
        finally {
            session.close();
        }
     }

        public List<String> show3(){
     Session session = MarcHibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            Query query = session.createSQLQuery("select distinct tagnumber,tagname from editmarc where tagnumber between 300 AND 400");

             query.setResultTransformer(Transformers.TO_LIST);

            return (List<String>) query.list();
        }
        finally {
            session.close();
        }
     }

         public List<String> show4(){
     Session session = MarcHibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            Query query = session.createSQLQuery("select distinct tagnumber,tagname from editmarc where tagnumber between 400 AND 500");

             query.setResultTransformer(Transformers.TO_LIST);

            return (List<String>) query.list();
        }
        finally {
            session.close();
        }
     }

          public List<String> show5(){
     Session session = MarcHibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            Query query = session.createSQLQuery("select distinct tagnumber,tagname from editmarc where tagnumber between 500 AND 600");

             query.setResultTransformer(Transformers.TO_LIST);

            return (List<String>) query.list();
        }
        finally {
            session.close();
        }
     }

           public List<String> show6(){
     Session session = MarcHibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            Query query = session.createSQLQuery("select distinct tagnumber,tagname from editmarc where tagnumber between 600 AND 700");

             query.setResultTransformer(Transformers.TO_LIST);

            return (List<String>) query.list();
        }
        finally {
            session.close();
        }
     }

            public List<String> show7(){
     Session session = MarcHibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            Query query = session.createSQLQuery("select distinct tagnumber,tagname from editmarc where tagnumber between 700 AND 800");

             query.setResultTransformer(Transformers.TO_LIST);

            return (List<String>) query.list();
        }
        finally {
            session.close();
        }
     }

             public List<String> show8(){
     Session session = MarcHibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            Query query = session.createSQLQuery("select distinct tagnumber,tagname from editmarc where tagnumber between 800 AND 900");

             query.setResultTransformer(Transformers.TO_LIST);

            return (List<String>) query.list();
        }
        finally {
            session.close();
        }
     }

              public List<String> show9(){
     Session session = MarcHibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            Query query = session.createSQLQuery("select distinct tagnumber,tagname from editmarc where tagnumber > 900");

             query.setResultTransformer(Transformers.TO_LIST);

            return (List<String>) query.list();
        }
        finally {
            session.close();
        }
     }

    public List<Editmarc> getMarc(String tagno){
Session session = MarcHibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            Query query = session.createQuery("FROM Editmarc WHERE tagnumber = :id ");
            query.setString("id", tagno);
            System.out.println("I'm in getMarc dao.");
            return (List<Editmarc>) query.list();
        }
        finally {
            session.close();
        }
}

    public Integer returnMaxBiblioId(String library_id, String sublibrary_id) {
        Session session = MarcHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            Criteria criteria = session.createCriteria(Customizedbiblio.class);
            Criterion a = Restrictions.eq("id.libraryId", library_id);
            Criterion b = Restrictions.eq("sublibraryId", sublibrary_id);
            LogicalExpression le = Restrictions.and(a, b);
            Integer maxbiblio = (Integer) criteria.add(le).setProjection(Projections.max("id.bibId")).uniqueResult();
            if (maxbiblio == null) {
                maxbiblio = 1;
            } else {
                maxbiblio++;
            }

            return maxbiblio;
        } finally {
            session.close();
        }
    }

}
