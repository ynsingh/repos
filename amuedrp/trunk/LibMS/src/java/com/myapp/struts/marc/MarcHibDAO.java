/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.marc;

import org.hibernate.Session;
import org.hibernate.Transaction;
import com.myapp.struts.hbm.Biblio;
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
        System.out.println("inside HIb DAO for marc Entry");
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
          //  throw e;
        }
        finally {
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
            Integer maxbiblio=null;
        try {
             session.beginTransaction();
            Criteria criteria = session.createCriteria(Biblio.class);
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

     public List<Biblio> getSpecificMarc(){
        Session session = HibernateUtil.getSessionFactory().openSession();
List<Biblio> obj=null;
        try {
                    session.beginTransaction();
            Criteria criteria = session.createCriteria(Biblio.class)
            .add(Restrictions.between("id.marctag", "0", "100"));
            obj=(List<Biblio>)criteria.list();
            session.getTransaction().commit();
        } finally {
            session.close();
        }
        return obj;
    }
     public List<Biblio> getdataforupdate1(String title,String library_id, String sub_libraryId)
    {
    Session session = HibernateUtil.getSessionFactory().openSession();
List<Biblio> obj=null;
        try {
            session.beginTransaction();
            Query query = session.createQuery("from Biblio where id.bibId=(select id.bibId from Biblio where $a=:title) and (id.marctag=020 or id.marctag=022 or id.marctag=100 or id.marctag=245) and id.libraryId='"+library_id+"' and sublibraryId='"+sub_libraryId+"'");
            query.setString("title", title);
            System.out.println("I'm in getdata4update in marchibdao.");
     obj=(List<Biblio>)query.list();
            session.getTransaction().commit();
        } finally {
            session.close();
        }
        return obj;
    }


     public List<Biblio> getdataforupdate(String bib_id1,String library_id,String sub_library_id)
    {
        int bib_id=Integer.parseInt(bib_id1);
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Biblio> obj=null;
   try{
       session.beginTransaction();
       Criteria criteria = session.createCriteria(Biblio.class)
                .add(Restrictions.conjunction()
                .add(Restrictions.eq("id.libraryId", library_id))
                .add(Restrictions.eq("sublibraryId", sub_library_id))
                .add(Restrictions.eq("id.bibId", bib_id))
                );
        obj=(List<Biblio>)criteria.list();
            session.getTransaction().commit();
        } finally {
            session.close();
        }
        return obj;
    }

      public List<Biblio> getdataforupdate44(String bib_id)
    {
    Session session = HibernateUtil.getSessionFactory().openSession();
List<Biblio> obj=null;
        try {
            session.beginTransaction();
            Query query = session.createQuery("from Biblio where id.bibId= :bib_id and (id.marctag=490)");
            query.setString("bib_id", bib_id);
            obj=(List<Biblio>)query.list();
            session.getTransaction().commit();
        } finally {
            session.close();
        }
        return obj;
    }

       public List<Biblio> searchDoc1(int bib_id,String library_id, String sub_library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Biblio> obj=null;
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(Biblio.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("sublibraryId", sub_library_id))
                    .add(Restrictions.eq("id.bibId", bib_id))
                    );
            obj=(List<Biblio>)criteria.list();
            session.getTransaction().commit();
        } finally {
            session.close();
        }
        return obj;
    }

    public Biblio searchMarcCall(int bibid,String library_id, String sub_library_id, String data) {
        Session session = HibernateUtil.getSessionFactory().openSession();
    Biblio obj=null;
        try {
                session.beginTransaction();
            Criteria criteria = session.createCriteria(Biblio.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("sublibraryId", sub_library_id))
                    .add(Restrictions.ne("id.bibId", bibid))
                    .add(Restrictions.eq("id.marctag", "082"))
                    .add(Restrictions.eq("$a", data))
                    );
            obj=(Biblio) criteria.uniqueResult();
            session.getTransaction().commit();
        } finally {
            session.close();
        }
        return obj;
    }
         public boolean deleteMarcBiblio(String bibid, String libid,String sublib){
         Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
          tx=  session.beginTransaction();
            Query query = session.createQuery("delete from Biblio where id.bibId= :bibid and library_id= :libid and sublibrary_id= :sublib");
            query.setString("bibid", bibid);
                query.setString("libid", libid);
            query.setString("sublib", sublib);
            query.executeUpdate();
            tx.commit();
        }
        finally {
            session.close();
        }
        return true;
     }

        public int isMarcDataExist(String title, String libid,String sublib){
            Session session = HibernateUtil.getSessionFactory().openSession();
        int x=0;
       try{
            session.beginTransaction();
            Query query = session.createQuery("select count(*) from Biblio where $a=:title  and library_id= :libid and sublibrary_id= :sublib");
           query.setString("title", title);
           query.setString("libid", libid);
           query.setString("sublib", sublib);
            x=Integer.parseInt(String.valueOf(query.uniqueResult()));
            session.getTransaction().commit();
       }
       finally
       {
       session.close();
       }
       return x;
        }

}
