/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.AcquisitionDao;

import com.myapp.struts.hbm.AcqVendor;
import com.myapp.struts.hbm.HibernateUtil;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author maqbool
 */
public class VendorDAO {
 public void delete2(String vendor_id, String library_id, String sub_library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
      

        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("DELETE FROM AcqVendor  WHERE  id.vendorId = :vendorId and id.libraryId = :libraryId and id.subLibraryId = :subLibraryId");

            query.setString("vendorId", vendor_id);
            query.setString("libraryId", library_id);
            query.setString("subLibraryId", sub_library_id);
            query.executeUpdate();
            tx.commit();
            
        }
        catch(Exception e){
        e.printStackTrace();
        tx.rollback();
        }
        finally {
              session.close();
        }
    }

      public AcqVendor search1VendorNo(String vendor_id, String library_id, String sub_library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        AcqVendor obj=null;
        try{
        session.beginTransaction();
        Criteria criteria = session.createCriteria(AcqVendor.class).add(Restrictions.conjunction().add(Restrictions.eq("id.libraryId", library_id)).add(Restrictions.eq("id.subLibraryId", sub_library_id)).add(Restrictions.eq("id.vendorId", vendor_id)));
        
        obj= (AcqVendor) criteria.uniqueResult();
        }
        catch(Exception e){
        e.printStackTrace();
        }
        finally
        {
        session.close();
        }
        return obj;
    }



 public static List<AcqVendor> searchDoc5(String library_id, String sub_library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
         List<AcqVendor> obj=null;
        try {
             session.beginTransaction();
            Criteria criteria = session.createCriteria(AcqVendor.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.subLibraryId", sub_library_id)));                    
            obj= criteria.list();
        }
        catch(Exception e){
        e.printStackTrace();
        }
        finally {
            session.close();
        }
        return obj;
    }


 

  public static List<String> getCurrencyList(String library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
         List<String>  obj=null;

        try {
            session.beginTransaction();
             Query query = session.createQuery("select distinct sourceCurrency FROM AcqCurrency  WHERE  id.libraryId = :libraryId");

             query.setString("libraryId", library_id);
             obj=query.list();
        }
        catch(Exception e){
        e.printStackTrace();
        }
        finally {
            session.close();
        }
        return obj;
    }

     public AcqVendor getBiblioByvendorid(String library_id, String sub_library_id, String vendor_no) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        AcqVendor obj=null;

        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(AcqVendor.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.subLibraryId", sub_library_id))
                    .add(Restrictions.eq("id.vendorId", vendor_no)));
            obj=(AcqVendor) criteria.uniqueResult();
        }
        catch(Exception e){
        e.printStackTrace();
        }
        finally {
            session.close();
        }
        return obj;
    }

      public AcqVendor search2VendorId(String vendor_id, String library_id, String sub_library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
       AcqVendor obj=null;
       try{

        session.beginTransaction();
        Criteria criteria = session.createCriteria(AcqVendor.class).add(Restrictions.conjunction().add(Restrictions.eq("id.libraryId", library_id)).add(Restrictions.eq("id.subLibraryId", sub_library_id)).add(Restrictions.eq("id.vendorId", vendor_id)));
        
        obj=(AcqVendor) criteria.uniqueResult();
       }
       catch(Exception e){
        e.printStackTrace();
        }
        finally {
            session.close();
        }
        return obj;


    }


     public static List<AcqVendor> searchVendor(String library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
         List<AcqVendor> obj=null;
        try {
             session.beginTransaction();
            Criteria criteria = session.createCriteria(AcqVendor.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    );
            obj= criteria.list();
        } catch(Exception e){
        e.printStackTrace();
        }
        finally {
            session.close();
        }
        return obj;
    }

}
