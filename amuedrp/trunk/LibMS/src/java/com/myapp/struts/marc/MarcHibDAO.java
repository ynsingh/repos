/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.marc;

import org.hibernate.Session;
import org.hibernate.Transaction;
import com.myapp.struts.hbm.Biblio;
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
        System.out.println("inside HIb DAO for marc Entry");
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


    public void update(Biblio biblio) {
        Session session = MarcHibernateUtil.getSessionFactory().openSession();
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
        Session session = MarcHibernateUtil.getSessionFactory().openSession();
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
                 Session session = MarcHibernateUtil.getSessionFactory().openSession();
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
    Session session = MarcHibernateUtil.getSessionFactory().openSession();

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
    Session session = MarcHibernateUtil.getSessionFactory().openSession();

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
    Session session = MarcHibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            Query query = session.createQuery("from Biblio where id.bibId= :bib_id and (id.marctag=100 or id.marctag=110 or id.marctag=130)");
            query.setString("bib_id", bib_id);
//            query.setString("eee", eee);
//            query.setString("eee1", eee1);
//            query.setString("eee2", eee2);
            System.out.println("I'm in getdata4update2 in marchibdao.");
            return (List<Biblio>) query.list();
        }
        finally {
            session.close();
        }
     }

     public List<Biblio> getdataforupdate3(String bib_id)
    {
    Session session = MarcHibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            Query query = session.createQuery("from Biblio where id.bibId= :bib_id and (id.marctag=210 or id.marctag=245 or id.marctag=250 or id.marctag=256 or id.marctag=260 or id.marctag=263)");
            query.setString("bib_id", bib_id);
//
            System.out.println("I'm in getdata4update3 in marchibdao.");
            return (List<Biblio>) query.list();
        }
        finally {
            session.close();
        }
     }

     public List<Biblio> getdataforupdate4(String bib_id)
    {
    Session session = MarcHibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            Query query = session.createQuery("from Biblio where id.bibId= :bib_id and (id.marctag=300 or id.marctag=306 or id.marctag=336)");
            query.setString("bib_id", bib_id);
//
            System.out.println("I'm in getdata4update4 in marchibdao.");
            return (List<Biblio>) query.list();
        }
        finally {
            session.close();
        }
     }

      public List<Biblio> getdataforupdate44(String bib_id)
    {
    Session session = MarcHibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            Query query = session.createQuery("from Biblio where id.bibId= :bib_id and (id.marctag=490)");
            query.setString("bib_id", bib_id);
//
            System.out.println("I'm in getdata4update4 in marchibdao.");
            return (List<Biblio>) query.list();
        }
        finally {
            session.close();
        }
     }

      public List<Biblio> getdataforupdate5(String bib_id)
    {
    Session session = MarcHibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            Query query = session.createQuery("from Biblio where id.bibId= :bib_id and (id.marctag=500 or id.marctag=502 or id.marctag=504 or id.marctag=505 or id.marctag=520 or id.marctag=546)");
            query.setString("bib_id", bib_id);
//
            System.out.println("I'm in getdata4update5 in marchibdao.");
            return (List<Biblio>) query.list();
        }
        finally {
            session.close();
        }
     }


       public List<Biblio> getdataforupdate6(String bib_id)
    {
    Session session = MarcHibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            Query query = session.createQuery("from Biblio where id.bibId= :bib_id and (id.marctag=600 or id.marctag=650 )");
            query.setString("bib_id", bib_id);
//
            System.out.println("I'm in getdata4update6 in marchibdao.");
            return (List<Biblio>) query.list();
        }
        finally {
            session.close();
        }
     }

        public List<Biblio> getdataforupdate7(String bib_id)
    {
    Session session = MarcHibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            Query query = session.createQuery("from Biblio where id.bibId= :bib_id and (id.marctag=700 or id.marctag=740 )");
            query.setString("bib_id", bib_id);
//
            System.out.println("I'm in getdata4update7 in marchibdao.");
            return (List<Biblio>) query.list();
        }
        finally {
            session.close();
        }
     }


         public List<Biblio> getdataforupdate8(String bib_id)
    {
    Session session = MarcHibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            Query query = session.createQuery("from Biblio where id.bibId= :bib_id and (id.marctag=800 or id.marctag=830 or id.marctag=850 or id.marctag=852 or id.marctag=856)");
            query.setString("bib_id", bib_id);
//
            System.out.println("I'm in getdata4update7 in marchibdao.");
            return (List<Biblio>) query.list();
        }
        finally {
            session.close();
        }
     }

       public List<Biblio> searchDoc1(int bib_id,String library_id, String sub_library_id) {
        Session session = MarcHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            Criteria criteria = session.createCriteria(Biblio.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("sublibraryId", sub_library_id))
                    .add(Restrictions.eq("id.bibId", bib_id))
                    );
            return (List<Biblio>)criteria.list();
        } finally {
            session.close();
        }
    }
         public boolean deleteMarcBiblio(String bibid){
         Session session = MarcHibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
          tx=  session.beginTransaction();
            Query query = session.createQuery("delete from Biblio where id.bibId= :bibid");
            query.setString("bibid", bibid);
            query.executeUpdate();
            tx.commit();
        }
        finally {
            session.close();
        }
        return true;
     }

        public int isMarcDataExist(String title){
            Session session = MarcHibernateUtil.getSessionFactory().openSession();

       
            session.beginTransaction();
            Query query = session.createQuery("select count(*) from Biblio where $a=:title");
            query.setString("title", title);
//
            System.out.println("I'm in isMarcDataExist in marchibdao.");
            return Integer.parseInt(String.valueOf(query.uniqueResult()));
        }

}
