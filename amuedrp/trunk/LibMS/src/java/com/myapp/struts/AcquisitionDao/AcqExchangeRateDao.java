/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.AcquisitionDao;

import  com.myapp.struts.hbm.*;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.criterion.Property;

/**
 *
 * @author maqbool
 */
public class AcqExchangeRateDao {

   public static AcqCurrency searchSourceCurrency1(String library_id, String scurrency,String date) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(AcqCurrency.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("systemDate", date))
                    .add(Restrictions.eq("sourceCurrency",scurrency)));
            return (AcqCurrency) criteria.uniqueResult();


        } finally {
            session.close();
        }
    }
 
    public static AcqCurrency searchSourceCurrency(String library_id, int convrsion_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(AcqCurrency.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.conversionId",convrsion_id)));
            return (AcqCurrency) criteria.uniqueResult();


        } finally {
            session.close();
        }
    }



    public static AcqCurrency searchSourceCurrency1(String library_id, String scurrency) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(AcqCurrency.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("sourceCurrency",scurrency)));
            return (AcqCurrency) criteria.uniqueResult();


        } finally {
            session.close();
        }
    }


   public static AcqCurrency searchCurrency1(String library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(AcqCurrency.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                   );
            return (AcqCurrency) criteria.uniqueResult();


        } finally {
            session.close();
        }
    }

 public static AcqCurrency getCurrencyBySourceName(String library_id,int convrsion_id ) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            session.beginTransaction();
            Query query1 = session.createQuery("FROM  AcqCurrency  WHERE id.libraryId =:library_id and id.conversionId=:conversionId");
            query1.setString("library_id", library_id);

            query1.setInteger("conversionId",convrsion_id);

            return (AcqCurrency) query1.uniqueResult();
        }
        finally {
            session.close();
        }

}


  public static AcqCurrency getCurrencyBySourceName1(String library_id,String scurrency ) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            session.beginTransaction();
            Query query1 = session.createQuery("FROM  AcqCurrency  WHERE id.libraryId =:library_id and sourceCurrency =:sourceCurrency");
            query1.setString("library_id", library_id);

            query1.setString("sourceCurrency",scurrency);

            return (AcqCurrency) query1.uniqueResult();
        }
        finally {
            session.close();
        }

}




  public static void insert(AcqCurrency obj) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;


        try {
            tx = (Transaction) session.beginTransaction();

            session.save(obj);
            tx.commit();



        } catch (Exception ex) {
       //     return false;

            //  System.out.println(ex.toString());

        } finally {
            //session.close();
        }
      //  return true;

    }


   public static void update(AcqCurrency obj) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.update(obj);
            tx.commit();
        } catch (RuntimeException e) {

            tx.rollback();
        //    return false;

        }

   //     return true;

    }


    public static boolean delete(String library_id,int conversion_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;


        try {
            tx = (Transaction) session.beginTransaction();
            Query query = session.createQuery("Delete From  AcqCurrency where id.libraryId = :library_id and id.conversionId=:conversionId");
           query.setString("library_id", library_id);

            query.setInteger("conversionId", conversion_id);
            query.executeUpdate();
            tx.commit();



        } catch (Exception ex) {
            System.out.println(ex);
            return false;

            //  System.out.println(ex.toString());

        } finally {
            // session.close();
        }
        return true;

    }

    public Integer returnMaxConversionId(String library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
         Transaction tx=session.beginTransaction();
        try {
            Criteria criteria = session.createCriteria(AcqCurrency.class);
            Criterion a = Restrictions.eq("id.libraryId", library_id);
           
            
            Integer maxbiblio = (Integer) criteria.add(a).setProjection(Projections.max("id.conversionId")).uniqueResult();
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
