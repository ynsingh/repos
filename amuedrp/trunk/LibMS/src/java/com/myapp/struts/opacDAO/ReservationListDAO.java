/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.opacDAO;

import com.myapp.struts.hbm.HibernateUtil;
import com.myapp.struts.hbm.*;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author edrp02
 */
public class ReservationListDAO {


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
            ex.printStackTrace();
            tx.rollback();
             return false;

       

        }
        finally
        {
          session.close();
        }


}

   
       
       public static Reservationlist getRequestDetail(String library_id,String sublibrary_id,String mem_id,String accession_no,String status) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Reservationlist obj=null;
        try {
            session.beginTransaction();
            Query query = session.createQuery("FROM Reservationlist where id.libraryId = :library_id and id.sublibraryId=:sublibrary_id and id.memid=:mem_id and accessionNo=:accession_no and status=:status");

            query.setString("library_id",library_id );
            query.setString("sublibrary_id",sublibrary_id );
            query.setString("mem_id",mem_id);
            query.setString("accession_no",accession_no);
            query.setString("status",status);
            obj=  (Reservationlist)query.uniqueResult();
        }
        catch(Exception ex){
        ex.printStackTrace();

        }
        finally {
            session.close();
        }
return obj;


}



     public static List<Reservationlist> getMemberDetail(String library_id,String sublibrary_id,String memid) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        List<Reservationlist>  obj=null;
        try {
            session.beginTransaction();
            Query query = session.createQuery("FROM Reservationlist where id.libraryId = :library_id and id.sublibraryId=:sublibrary_id and id.memid=:mem_id");
            query.setString("library_id",library_id);
            query.setString("sublibrary_id",sublibrary_id);
            query.setString("mem_id",memid);


            obj=  (List<Reservationlist>)query.list();
        }
        catch(Exception e){
        e.printStackTrace();
        }
        finally {
        session.close();
        }
    return obj;
     }



}
