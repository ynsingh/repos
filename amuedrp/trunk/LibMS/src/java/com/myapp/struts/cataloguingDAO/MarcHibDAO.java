/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.cataloguingDAO;


import org.hibernate.Session;
import org.hibernate.Transaction;
import com.myapp.struts.hbm.Biblio;
import com.myapp.struts.hbm.BiblioTemp;
import com.myapp.struts.hbm.HibernateUtil;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
/**
 *
 * @author zeeshan
 */
public class MarcHibDAO {

    public void insert(Biblio biblio){
      //  System.out.println("inside HIb DAO for marc Entry");
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
        public void inserttemp(BiblioTemp biblio){
      //  System.out.println("inside HIb DAO for marc Entry");
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
       public List<Integer> searchDoc1(String library_id, String sub_library_id) {
          {
    Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();


            Query query = session.createQuery("select distinct id.bibId from Biblio where id.libraryId= :lib_id and sublibraryId = :sub_id");
            query.setString("lib_id", library_id);
            query.setString("sub_id", sub_library_id);
            return (List<Integer>) query.list();
        }
        finally {
            session.close();
        }
     }
       }
              public List<Biblio> searchBiblioId(String library_id, String sub_library_id, int bib_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            Criteria criteria = session.createCriteria(Biblio.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.bibId", bib_id))
                    .add(Restrictions.eq("sublibraryId", sub_library_id)));
            return (List<Biblio>)criteria.list();
        } finally {
            session.close();
        }
    }
       public List<Integer> searchReposBiblio(String library_id, String sub_library_id) {
      {
    Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();


            Query query = session.createQuery("select distinct id.bibId from BiblioTemp where id.libraryId= :lib_id and sublibraryId = :sub_id");
            query.setString("lib_id", library_id);
            query.setString("sub_id", sub_library_id);
            return (List<Integer>) query.list();
        }
        finally {
            session.close();
        }
     }
       }
        public List<BiblioTemp> searchReposBibliobyid(String library_id, String sub_library_id, int bib_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            Criteria criteria = session.createCriteria(BiblioTemp.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.bibId", bib_id))
                    .add(Restrictions.eq("sublibraryId", sub_library_id)));
            return (List<BiblioTemp>)criteria.list();
        } finally {
            session.close();
        }
    }
       public void update(Biblio biblio) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.update(biblio);
            tx.commit();
        } catch (RuntimeException e) {
           
            tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

     public Integer returnMaxBiblioId(String library_id, String sublibrary_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            Criteria criteria = session.createCriteria(Biblio.class);
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
     public Integer returnMaxBiblioIdTemp(String library_id, String sublibrary_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            Criteria criteria = session.createCriteria(BiblioTemp.class);
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
     public List<Biblio> getSpecificMarc(){
//    Session session = MarcHibernateUtil.getSessionFactory().openSession();
//
//        try {
//            session.beginTransaction();
//            Query query = session.createQuery("FROM Biblio");
//
//            System.out.println("I'm in getMarc dao.");
//            return (List<Biblio>) query.list();
//        }
//        finally {
//            session.close();
//        }
//
                 Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            Criteria criteria = session.createCriteria(Biblio.class)
            .add(Restrictions.between("id.marctag", "0", "100"));
            return criteria.list();
        } finally {
            session.close();
        }
    }
     

     public List<Biblio> getdataforupdate(String title)
    {
    Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            Query query = session.createQuery("from Biblio where id.bibId=(select id.bibId from Biblio where $a=:title) and (id.marctag=020 or id.marctag=022 or id.marctag=100 or id.marctag=245)");
            query.setString("title", title);
            System.out.println("I'm in getdata4update in marchibdao.");
            return (List<Biblio>) query.list();
        }
        finally {
            session.close();
        }
     }

public List<Biblio> getdataforupdate1(String bibid, String eee)
    {
    Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();

            Query query = session.createQuery("from Biblio where id.bibId= :bib_id and (id.marctag=020 or id.marctag=022 or id.marctag=041 or id.marctag=043 or id.marctag= :eee)");
            query.setString("bib_id", bibid);
            query.setString("eee",eee);
            System.out.println("I'm in getdata4update1 in marchibdao.");
            return (List<Biblio>) query.list();
        }
        finally {
            session.close();
        }
     }


     public List<Biblio> getdataforupdate2(String bib_id)
    {
    Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();


            Query query = session.createQuery("from Biblio where id.bibId= :bib_id and (id.marctag=100 or id.marctag=110 or id.marctag=130");
            query.setString("bib_id", bib_id);

            System.out.println("I'm in getdata4update2 in marchibdao.");
            return (List<Biblio>) query.list();
        }
        finally {
            session.close();
        }
     }
}
