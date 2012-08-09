package com.myapp.struts.marc;

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

public class NewMARCDAO {
     public void insert(Editmarc editmarc){
    Session session = HibernateUtil.getSessionFactory().openSession();
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
         Session session = HibernateUtil.getSessionFactory().openSession();
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
        Session session = HibernateUtil.getSessionFactory().openSession();
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
     Session session = HibernateUtil.getSessionFactory().openSession();
        List<String> obj=null;
        try {
            session.beginTransaction();
            Query query = session.createSQLQuery("select distinct tagnumber,tagname from editmarc where tagnumber < 100");
             query.setResultTransformer(Transformers.TO_LIST);

           obj= query.list();
           session.getTransaction().commit();
        }
        finally {
            session.close();
        }
        return obj;
     }

      public List<String> show1(){
     Session session = HibernateUtil.getSessionFactory().openSession();
        List<String> obj=null;
        try {
            session.beginTransaction();
            Query query = session.createSQLQuery("select distinct tagnumber,tagname from editmarc where tagnumber between 100 AND 200");

             query.setResultTransformer(Transformers.TO_LIST);

            obj= query.list();
            session.getTransaction().commit();
        }
        finally {
            session.close();
        }
        return obj;
     }


       public List<String> show2(){
     Session session = HibernateUtil.getSessionFactory().openSession();
  List<String> obj=null;
        try {
            session.beginTransaction();
            Query query = session.createSQLQuery("select distinct tagnumber,tagname from editmarc where tagnumber between 200 AND 300");

             query.setResultTransformer(Transformers.TO_LIST);

           obj= query.list();
            session.getTransaction().commit();
        }
        finally {
            session.close();
        }
        return obj;
     }

        public List<String> show3(){
     Session session = HibernateUtil.getSessionFactory().openSession();
 List<String> obj=null;
        try {
            session.beginTransaction();
            Query query = session.createSQLQuery("select distinct tagnumber,tagname from editmarc where tagnumber between 300 AND 400");

             query.setResultTransformer(Transformers.TO_LIST);

            obj= query.list();
            session.getTransaction().commit();
        }
        finally {
            session.close();
        }
        return obj;
     }

         public List<String> show4(){
     Session session = HibernateUtil.getSessionFactory().openSession();
List<String> obj=null;
        try {
            session.beginTransaction();
            Query query = session.createSQLQuery("select distinct tagnumber,tagname from editmarc where tagnumber between 400 AND 500");

             query.setResultTransformer(Transformers.TO_LIST);

              obj= query.list();
            session.getTransaction().commit();
        }
        finally {
            session.close();
        }
        return obj;
     }

          public List<String> show5(){
     Session session = HibernateUtil.getSessionFactory().openSession();
List<String> obj=null;
        try {
            session.beginTransaction();
            Query query = session.createSQLQuery("select distinct tagnumber,tagname from editmarc where tagnumber between 500 AND 600");

             query.setResultTransformer(Transformers.TO_LIST);
   obj= query.list();
            session.getTransaction().commit();
        }
        finally {
            session.close();
        }
        return obj;
     }

           public List<String> show6(){
     Session session = HibernateUtil.getSessionFactory().openSession();
List<String> obj=null;
        try {
            session.beginTransaction();
            Query query = session.createSQLQuery("select distinct tagnumber,tagname from editmarc where tagnumber between 600 AND 700");

             query.setResultTransformer(Transformers.TO_LIST);

               obj= query.list();
            session.getTransaction().commit();
        }
        finally {
            session.close();
        }
        return obj;
     }

            public List<String> show7(){
     Session session = HibernateUtil.getSessionFactory().openSession();
List<String> obj=null;
        try {
            session.beginTransaction();
            Query query = session.createSQLQuery("select distinct tagnumber,tagname from editmarc where tagnumber between 700 AND 800");

             query.setResultTransformer(Transformers.TO_LIST);

              obj= query.list();
            session.getTransaction().commit();
        }
        finally {
            session.close();
        }
        return obj;
     }

             public List<String> show8(){
     Session session = HibernateUtil.getSessionFactory().openSession();
List<String> obj=null;
        try {
            session.beginTransaction();
            Query query = session.createSQLQuery("select distinct tagnumber,tagname from editmarc where tagnumber between 800 AND 900");

             query.setResultTransformer(Transformers.TO_LIST);

             obj= query.list();
            session.getTransaction().commit();
        }
        finally {
            session.close();
        }
        return obj;
     }
              public List<String> show9(){
     Session session = HibernateUtil.getSessionFactory().openSession();
List<String> obj=null;
        try {
            session.beginTransaction();
            Query query = session.createSQLQuery("select distinct tagnumber,tagname from editmarc where tagnumber > 900");

             query.setResultTransformer(Transformers.TO_LIST);

             obj= query.list();
            session.getTransaction().commit();
        }
        finally {
            session.close();
        }
        return obj;
     }

    public List<Editmarc> getMarc(String tagno){
Session session = HibernateUtil.getSessionFactory().openSession();
List<Editmarc> obj=null;
        try {
            session.beginTransaction();
            Query query = session.createQuery("FROM Editmarc WHERE tagnumber = :id ");
            query.setString("id", tagno);
            System.out.println("I'm in getMarc dao.");
           obj= (List<Editmarc>) query.list();
           session.getTransaction().commit();
        }
        finally {
            session.close();
        }
        return obj;
}

    public Integer returnMaxBiblioId(String library_id, String sublibrary_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
         Integer maxbiblio=null;
        try {
             session.beginTransaction();
            Criteria criteria = session.createCriteria(Customizedbiblio.class);
            Criterion a = Restrictions.eq("id.libraryId", library_id);
            Criterion b = Restrictions.eq("sublibraryId", sublibrary_id);
            LogicalExpression le = Restrictions.and(a, b);
             maxbiblio = (Integer) criteria.add(le).setProjection(Projections.max("id.bibId")).uniqueResult();
            if (maxbiblio == null) {
                maxbiblio = 1;
            } else {
                maxbiblio++;
            }
            session.getTransaction().commit();
          
        } finally {
            session.close();
        }
          return maxbiblio;
    }

}
