/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.AcquisitionDao;

import com.myapp.struts.hbm.AcqCurrency;
import com.myapp.struts.hbm.AcqVendor;
import com.myapp.struts.hbm.HibernateUtil;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author maqbool
 */
public class VendorDAO {
 public void delete2(String vendor_id, String library_id, String sub_library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        //Object acqdocentry = null;

        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("DELETE FROM AcqVendor  WHERE  id.vendorId = :vendorId and id.libraryId = :libraryId and id.subLibraryId = :subLibraryId");

            query.setString("vendorId", vendor_id);
            query.setString("libraryId", library_id);
            query.setString("subLibraryId", sub_library_id);
            query.executeUpdate();
            tx.commit();
            
        } finally {
            //  session.close();
        }
    }

      public AcqVendor search1VendorNo(String vendor_id, String library_id, String sub_library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(AcqVendor.class).add(Restrictions.conjunction().add(Restrictions.eq("id.libraryId", library_id)).add(Restrictions.eq("id.subLibraryId", sub_library_id)).add(Restrictions.eq("id.vendorId", vendor_id)));
        //session.close();
        return (AcqVendor) criteria.uniqueResult();
    }



 public static List<AcqVendor> searchDoc5(String library_id, String sub_library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
         Transaction tx=session.beginTransaction();
        try {
            Criteria criteria = session.createCriteria(AcqVendor.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.subLibraryId", sub_library_id)));                    
            return criteria.list();
        } finally {
            session.close();
        }
    }


 

  public static List<String> getCurrencyList(String library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
         Transaction tx=session.beginTransaction();
        try {
             Query query = session.createQuery("select distinct sourceCurrency FROM AcqCurrency  WHERE  id.libraryId = :libraryId");

            /*Criteria criteria = session.createCriteria(AcqCurrency.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Project
                  );

             * */
             query.setString("libraryId", library_id);
              return query.list();
        } finally {
           // session.close();
        }
    }

     public AcqVendor getBiblioByvendorid(String library_id, String sub_library_id, String vendor_no) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            Criteria criteria = session.createCriteria(AcqVendor.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.subLibraryId", sub_library_id))
                    .add(Restrictions.eq("id.vendorId", vendor_no)));
            return (AcqVendor) criteria.uniqueResult();
        } finally {
            session.close();
        }
    }

      public AcqVendor search2VendorId(String vendor_id, String library_id, String sub_library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(AcqVendor.class).add(Restrictions.conjunction().add(Restrictions.eq("id.libraryId", library_id)).add(Restrictions.eq("id.subLibraryId", sub_library_id)).add(Restrictions.eq("id.vendorId", vendor_id)));
        //session.close();
        return (AcqVendor) criteria.uniqueResult();
    }


     public static List<AcqVendor> searchVendor(String library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
         Transaction tx=session.beginTransaction();
        try {
            Criteria criteria = session.createCriteria(AcqVendor.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    );
            return criteria.list();
        } finally {
           // session.close();
        }
    }
}
