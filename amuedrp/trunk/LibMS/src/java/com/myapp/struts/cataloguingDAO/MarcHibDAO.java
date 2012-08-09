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
import org.hibernate.criterion.Order;
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
            e.printStackTrace();
        }
        finally {
            session.close();
        }
    }
        public void inserttemp(BiblioTemp biblio){
     
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
            e.printStackTrace();
        }
        finally {
            session.close();
        }
    }
       public List<Integer> searchDoc1(String library_id, String sub_library_id,String title) 
          {
    Session session = HibernateUtil.getSessionFactory().openSession();
List<Integer> obj=null;
        try {
            session.beginTransaction();


            Query query = session.createQuery("select distinct id.bibId from Biblio where id.libraryId= :lib_id and sublibraryId = :sub_id and $a= :title");
            query.setString("lib_id", library_id);
            query.setString("sub_id", sub_library_id);
            query.setString("title", title);
            obj= (List<Integer>) query.list();
            session.getTransaction().commit();
        }
        catch (RuntimeException e) {

            e.printStackTrace();
        }
        finally {
            session.close();


     }
        return obj;
       }
       public List<Integer> searchdataBiblioTemp(String library_id, String sub_library_id)
          {
            Session session = HibernateUtil.getSessionFactory().openSession();
            List<Integer> obj=null;
        try {
            session.beginTransaction();
            Query query = session.createQuery("select distinct id.bibId from BiblioTemp where id.libraryId= :lib_id and sublibraryId = :sub_id");
            query.setString("lib_id", library_id);
            query.setString("sub_id", sub_library_id);
            obj=(List<Integer>) query.list();
            session.getTransaction().commit();
        }
        catch (RuntimeException e) {

            e.printStackTrace();
        }
        finally {
            session.close();


     }
        return obj;
       }
        public List<Integer> searchDoc2(String library_id, String sub_library_id) 
          {
            Session session = HibernateUtil.getSessionFactory().openSession();
            List<Integer> obj=null;
        try {
            session.beginTransaction();
            Query query = session.createQuery("select distinct id.bibId from Biblio  where id.libraryId= :lib_id and sublibraryId = :sub_id");
            query.setString("lib_id", library_id);
            query.setString("sub_id", sub_library_id);
            obj=(List<Integer>) query.list();
            session.getTransaction().commit();
        }
        catch (RuntimeException e) {

            e.printStackTrace();
        }
        finally {
            session.close();


     }
        return obj;
       }
              public List<Biblio>  searchBiblioId(String library_id, String sub_library_id, int bib_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
         List<Biblio> obj=null;
        
        try {
             session.beginTransaction();

            Criteria criteria = session.createCriteria(Biblio.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.bibId", bib_id))
                    .add(Restrictions.eq("sublibraryId", sub_library_id))).addOrder(Order.asc("id.marctag"));
            obj= (List<Biblio>)criteria.list();
            session.getTransaction().commit();
        } catch (RuntimeException e) {

            e.printStackTrace();
        }
        finally {
            session.close();


     }
        return obj;
       }
                public List<BiblioTemp>  searchBiblioIdTemp(String library_id, String sub_library_id, int bib_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
         List<BiblioTemp> obj=null;

        try {
             session.beginTransaction();

            Criteria criteria = session.createCriteria(BiblioTemp.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.bibId", bib_id))
                    .add(Restrictions.eq("sublibraryId", sub_library_id))).addOrder(Order.asc("id.marctag"));
            obj= (List<BiblioTemp>)criteria.list();
            session.getTransaction().commit();
        } catch (RuntimeException e) {

            e.printStackTrace();
        }
        finally {
            session.close();


     }
        return obj;
       }
         public Biblio searchopacBiblioId(String library_id, String sub_library_id, String call_no) {
        Session session = HibernateUtil.getSessionFactory().openSession();
       Biblio obj=null;
        try {

         session.beginTransaction();
            Criteria criteria = session.createCriteria(Biblio.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.marctag", "082"))
                    .add(Restrictions.eq("$a", call_no))
                    .add(Restrictions.eq("sublibraryId", sub_library_id)));
           obj= (Biblio)criteria.uniqueResult();
           session.getTransaction().commit();
        }  catch (RuntimeException e) {

            e.printStackTrace();
        }
        finally {
            session.close();


     }
        return obj;
       }
  

       public List<Integer> searchReposBiblio(String library_id, String sub_library_id) 
      {
    Session session = HibernateUtil.getSessionFactory().openSession();
 List<Integer> obj=null;
        try {
            session.beginTransaction();


            Query query = session.createQuery("select distinct id.bibId from BiblioTemp where id.libraryId= :lib_id and sublibraryId = :sub_id");
            query.setString("lib_id", library_id);
            query.setString("sub_id", sub_library_id);
            obj= (List<Integer>) query.list();
            session.getTransaction().commit();
        }
        catch (RuntimeException e) {

            e.printStackTrace();
        }
        finally {
            session.close();


     }
        return obj;
       }
        public List<BiblioTemp> searchReposBibliobyid(String library_id, String sub_library_id, int bib_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
      List<BiblioTemp> obj=null;

        try {

        session.beginTransaction();
            Criteria criteria = session.createCriteria(BiblioTemp.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.bibId", bib_id))
                    .add(Restrictions.eq("sublibraryId", sub_library_id)));
            obj= (List<BiblioTemp>)criteria.list();
            session.getTransaction().commit();
        } catch (RuntimeException e) {

            e.printStackTrace();
        }
        finally {
            session.close();


     }
        return obj;
       }
        public List<BiblioTemp> isMarcDataExist2(String code,String controlfield,String subfield, String libid,String sublib){
            Session session = HibernateUtil.getSessionFactory().openSession();
            List<BiblioTemp> x=null;
            try{
            session.beginTransaction();
            Query query = session.createQuery("from BiblioTemp where $"+code+" =:controlfield and id.marctag=:tag and  library_id= :libid and sublibrary_id= :sublib");
            query.setString("controlfield", controlfield);
            query.setString("libid", libid);
            query.setString("sublib", sublib);
            query.setString("tag", subfield);
            x=(List<BiblioTemp>)(query.list());
             session.getTransaction().commit();
        } catch (RuntimeException e) {

            e.printStackTrace();
        }
        finally {
            session.close();


     }
        return x;
        }

        public BiblioTemp isMarcDataExist1(String controlfield,String subfield, String libid,String sublib){
            Session session = HibernateUtil.getSessionFactory().openSession();
            BiblioTemp x=null;
            try{
            session.beginTransaction();
            Query query = session.createQuery("from BiblioTemp where $a=:controlfield and id.marctag=:tag and  library_id= :libid and sublibrary_id= :sublib");
            query.setString("controlfield", controlfield);
            query.setString("libid", libid);
            query.setString("sublib", sublib);
            query.setString("tag", subfield);
            x=(BiblioTemp)(query.uniqueResult());
             session.getTransaction().commit();
        } catch (RuntimeException e) {

            e.printStackTrace();
        }
        finally {
            session.close();


     }
        return x;
        }

        public BiblioTemp DuplicateMarcDataExist(String call_no, String libid,String sublib){
            Session session = HibernateUtil.getSessionFactory().openSession();
            BiblioTemp x=null;
            try{
            session.beginTransaction();
            Query query = session.createQuery("from BiblioTemp where $a=:call_no and id.marctag='082' and library_id= :libid and sublibrary_id= :sublib");
            query.setString("call_no", call_no);
            query.setString("libid", libid);
            query.setString("sublib", sublib);
            
            x=(BiblioTemp)(query.uniqueResult());
             session.getTransaction().commit();
        } catch (RuntimeException e) {

            e.printStackTrace();
        }
        finally {
            session.close();


     }
        return x;
        }


        public int isMarcDataExistTemp(String title, String libid,String sublib){
            Session session = HibernateUtil.getSessionFactory().openSession();


            session.beginTransaction();
            Query query = session.createQuery("select count(*) from BiblioTemp where $a=:title and library_id= :libid and sublibrary_id= :sublib");
            query.setString("title", title);
            query.setString("libid", libid);
            query.setString("sublib", sublib);
//
            System.out.println("I'm in isMarcDataExist in marchibdao.");
            return Integer.parseInt(String.valueOf(query.uniqueResult()));
        }
        public int isMarcDataExist(String title, String libid,String sublib){
            Session session = HibernateUtil.getSessionFactory().openSession();


            session.beginTransaction();
            Query query = session.createQuery("select count(*) from Biblio where $a=:title and library_id= :libid and sublibrary_id= :sublib");
            query.setString("title", title);
            query.setString("libid", libid);
            query.setString("sublib", sublib);
//
            System.out.println("I'm in isMarcDataExist in marchibdao.");
            return Integer.parseInt(String.valueOf(query.uniqueResult()));
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
        e.printStackTrace();
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
          
        } catch (RuntimeException e) {

            e.printStackTrace();
        }
        finally {
            session.close();


     }
       return maxbiblio;
       }
     public Integer returnMaxBiblioIdTemp(String library_id, String sublibrary_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Integer maxbiblio=null;

        try {
            session.beginTransaction();

            Criteria criteria = session.createCriteria(BiblioTemp.class);
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
           
        }  catch (RuntimeException e) {

            e.printStackTrace();
        }
        finally {
            session.close();


     }
        return maxbiblio;
       }
     public List<Biblio> getSpecificMarc(){
 List<Biblio> obj=null;
                 Session session = HibernateUtil.getSessionFactory().openSession();
      
        try {
              session.beginTransaction();

            Criteria criteria = session.createCriteria(Biblio.class)
            .add(Restrictions.between("id.marctag", "0", "100"));
            obj= criteria.list();
            session.getTransaction().commit();
        }  catch (RuntimeException e) {

            e.printStackTrace();
        }
        finally {
            session.close();


     }
        return obj;
       }

     public List<Biblio> getdataforupdate(String title)
    {
    Session session = HibernateUtil.getSessionFactory().openSession();
 List<Biblio> obj=null;
        try {
            session.beginTransaction();
            Query query = session.createQuery("from Biblio where id.bibId=(select id.bibId from Biblio where $a=:title) and (id.marctag=020 or id.marctag=022 or id.marctag=100 or id.marctag=245)");
            query.setString("title", title);
            System.out.println("I'm in getdata4update in marchibdao.");
           obj=(List<Biblio>) query.list();
           session.getTransaction().commit();
        }
         catch (RuntimeException e) {

            e.printStackTrace();
        }
        finally {
            session.close();


     }
        return obj;
       }

public List<Biblio> getdataforupdate1(String bibid, String eee)
    {
    Session session = HibernateUtil.getSessionFactory().openSession();
 List<Biblio> obj=null;
        try {
            session.beginTransaction();

            Query query = session.createQuery("from Biblio where id.bibId= :bib_id and (id.marctag=020 or id.marctag=022 or id.marctag=041 or id.marctag=043 or id.marctag= :eee)");
            query.setString("bib_id", bibid);
            query.setString("eee",eee);
            System.out.println("I'm in getdata4update1 in marchibdao.");
            obj= (List<Biblio>) query.list();
            session.getTransaction().commit();
        }
       catch (RuntimeException e) {

            e.printStackTrace();
        }
        finally {
            session.close();


     }
        return obj;
       }

     public List<Biblio> getdataforupdate2(String bib_id)
    {
    Session session = HibernateUtil.getSessionFactory().openSession();
 List<Biblio> obj=null;
        try {
            session.beginTransaction();


            Query query = session.createQuery("from Biblio where id.bibId= :bib_id and (id.marctag=100 or id.marctag=110 or id.marctag=130");
            query.setString("bib_id", bib_id);

            System.out.println("I'm in getdata4update2 in marchibdao.");
            obj= (List<Biblio>) query.list();
            session.getTransaction().commit();
        }
        catch (RuntimeException e) {

            e.printStackTrace();
        }
        finally {
            session.close();


     }
        return obj;
       }
}
