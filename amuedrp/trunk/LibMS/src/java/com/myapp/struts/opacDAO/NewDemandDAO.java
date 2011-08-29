/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.opacDAO;

import com.myapp.struts.hbm.HibernateUtil;
import com.myapp.struts.hbm.*;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author edrp02
 */
public class NewDemandDAO {
  Criterion criterion;

  public static Notices ViewNotice(String library_id,String sublibrary_id,String notice_id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        System.out.println("LibraryID="+library_id+" SublibraryId="+sublibrary_id+" noticeId="+notice_id);
        try {
            session.beginTransaction();
            Query query = session.createQuery("FROM Notices where id.libraryId = :library_id and id.sublibraryId = :sublibrary_id and id.noticeId = :notice_id");
            query.setString("library_id",library_id );
            query.setString("sublibrary_id",sublibrary_id);
            query.setString("notice_id",notice_id);
            return (Notices) query.uniqueResult();
        }
        catch(Exception e){
        System.out.println("Notices NewDemandDAO Action "+e);
        return null;
        }
        finally {
            session.close();
        }



}


    public static List Notice(String library_id,String sub_lib)
    {
        //int count=1;
        Session hsession=HibernateUtil.getSessionFactory().openSession();
        try
        {
         hsession.beginTransaction();
         Criteria criteria = hsession.createCriteria(Notices.class);
         if(library_id!=null)
         {
         if(!library_id.equalsIgnoreCase("all"))
         criteria.add(Restrictions.eq("id.libraryId",library_id));
         if(!sub_lib.equalsIgnoreCase("all"))
         criteria.add(Restrictions.eq("id.sublibraryId",sub_lib));
         return  criteria.list();
         }
         return null;
         

        }
        catch(Exception e)
        {
            System.out.println("Error***** OpacSearchDAO.SimpleSearch():"+e);
            return null;
        }
        finally
        {
           hsession.close();
        }
   }





    public static List NewArrival(String library_id,String sub_lib,String year1,String year2,String cat)
    {
        //int count=1;
        Session hsession=HibernateUtil.getSessionFactory().openSession();
        try
        {
         hsession.beginTransaction();
         Criteria criteria = hsession.createCriteria(BibliographicDetails.class);
         if(!library_id.equalsIgnoreCase("all"))
         criteria.add(Restrictions.eq("id.libraryId",library_id));
         if(!sub_lib.equalsIgnoreCase("all"))
         criteria.add(Restrictions.eq("id.sublibraryId",sub_lib));

         if(!cat.equalsIgnoreCase("all"))
         criteria.add(Restrictions.eq("documentType",cat));
System.out.println(year1  +  " <  "+year2);
         if(year1!=null){

         criteria.add(Restrictions.gt("dateAcquired",year1));
         }


         return criteria.list();

        }
        catch(Exception e)
        {
            System.out.println("Error***** OpacSearchDAO.SimpleSearch():"+e);
            return null;
        }
        finally
        {
           hsession.close();
        }
   }


  public static  boolean insert2(Feedback obj)
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


    public static  boolean insert(Demandlist obj)
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

       public static  boolean insert1(Reservationlist obj)
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
     public static Demandlist getDemandList(String library_id,String sublibrary_id,String memid,String title,String status) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            System.out.println("Call");
            session.beginTransaction();
            Query query = session.createQuery("FROM Demandlist where id.libraryId = :library_id and id.sublibraryId=:sublibrary_id and id.memId=:mem_id and id.title=:title and status=:status");
            query.setString("library_id",library_id);
            query.setString("sublibrary_id",sublibrary_id);
            query.setString("mem_id",memid);
            query.setString("title",title);
            query.setString("status",status);

            return  (Demandlist)query.uniqueResult();
        }
        finally {
            session.close();
        }

     }


       public static List getMaxReservationId(String library_id,String sublibrary_id,String memId) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            session.beginTransaction();
            Query query = session.createQuery("SELECT Max(id.requestId)FROM Reservationlist where id.libraryId = :library_id and id.sublibraryId = :sublibrary and id.memid = :memId ");
            query.setString("library_id",library_id );
            query.setString("sublibrary",sublibrary_id );
            query.setString("memId",memId );
            return  query.list();
        }
        finally {
            session.close();
        }



}


}
