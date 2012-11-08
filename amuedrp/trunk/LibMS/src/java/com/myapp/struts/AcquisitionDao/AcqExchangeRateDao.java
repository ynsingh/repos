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
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.Query;

/**
 *
 * @author maqbool
 */
public class AcqExchangeRateDao {

   public  AcqCurrency searchSourceCurrency1(String library_id, String scurrency,String date) {
        Session session = HibernateUtil.getSessionFactory().openSession();
AcqCurrency obj=null;
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(AcqCurrency.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("systemDate", date))
                    .add(Restrictions.eq("sourceCurrency",scurrency)));
           obj= (AcqCurrency) criteria.uniqueResult();
session.getTransaction().commit();

        }
        catch(Exception e){

        e.printStackTrace();
        }
        finally {
            session.close();
        }
        return obj;
    }
 
    public  AcqCurrency searchSourceCurrency(String library_id, int convrsion_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
AcqCurrency obj=null;
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(AcqCurrency.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.conversionId",convrsion_id)));
            obj=(AcqCurrency) criteria.uniqueResult();
session.getTransaction().commit();

        }
        catch(Exception e){
        e.printStackTrace();
        }

        finally {
            session.close();
        }
        return obj;
    }



    public  AcqCurrency searchSourceCurrency1(String library_id, String scurrency) {
        Session session = HibernateUtil.getSessionFactory().openSession();
AcqCurrency obj=null;
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(AcqCurrency.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("sourceCurrency",scurrency)));
           obj= (AcqCurrency) criteria.uniqueResult();

session.getTransaction().commit();
        }
        catch(Exception e){
        e.printStackTrace();
        }
        finally {
            session.close();
        }
        return obj;
    }


   public  AcqCurrency searchCurrency1(String library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
AcqCurrency obj=null;
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(AcqCurrency.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                   );
            obj= (AcqCurrency) criteria.uniqueResult();

session.getTransaction().commit();
        }
        catch(Exception e){
        e.printStackTrace();
        }
        finally {
            session.close();
        }
        return obj;
    }

 public  AcqCurrency getCurrencyBySourceName(String library_id,int convrsion_id ) {
        Session session = HibernateUtil.getSessionFactory().openSession();
      AcqCurrency obj=null;
        try {
            session.beginTransaction();
            Query query1 = session.createQuery("FROM  AcqCurrency  WHERE id.libraryId =:library_id and id.conversionId=:conversionId");
            query1.setString("library_id", library_id);

            query1.setInteger("conversionId",convrsion_id);

           obj= (AcqCurrency) query1.uniqueResult();
           session.getTransaction().commit();
        }
       catch(Exception e){
        e.printStackTrace();
        }
        finally {
            session.close();
        }
        return obj;
    }



  public  AcqCurrency getCurrencyBySourceName1(String library_id,String scurrency ) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        AcqCurrency obj=null;
        try {
            session.beginTransaction();
            Query query1 = session.createQuery("FROM  AcqCurrency  WHERE id.libraryId =:library_id and sourceCurrency =:sourceCurrency");
            query1.setString("library_id", library_id);

            query1.setString("sourceCurrency",scurrency);

            obj= (AcqCurrency) query1.uniqueResult();
            session.getTransaction().commit();
        }
       catch(Exception e){
        e.printStackTrace();
        }
        finally {
            session.close();
        }
      return obj;
    }


  public  void insert(AcqCurrency obj) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;


        try {
            tx = (Transaction) session.beginTransaction();

            session.save(obj);
            tx.commit();



        }catch(Exception e){
        e.printStackTrace();
        tx.rollback();
        }
        finally {
            session.close();
        }
        
    }


   public  void update(AcqCurrency obj) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.update(obj);
            tx.commit();
        }catch(Exception e){
        e.printStackTrace();
        tx.rollback();
        }
        finally {
            session.close();
        }
     
    }

    public  boolean delete(String library_id,int conversion_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;


        try {
            tx = (Transaction) session.beginTransaction();
            Query query = session.createQuery("Delete From  AcqCurrency where id.libraryId = :library_id and id.conversionId=:conversionId");
           query.setString("library_id", library_id);

            query.setInteger("conversionId", conversion_id);
            query.executeUpdate();
            tx.commit();



        } catch(Exception e){
        e.printStackTrace();
        tx.rollback();
        return false;
        
        }
        finally {
            session.close();
        }
        return true;
    }

    public Integer returnMaxConversionId(String library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
       Integer maxbiblio =null;
        try {
              session.beginTransaction();
            Criteria criteria = session.createCriteria(AcqCurrency.class);
            Criterion a = Restrictions.eq("id.libraryId", library_id);
           
            
             maxbiblio = (Integer) criteria.add(a).setProjection(Projections.max("id.conversionId")).uniqueResult();
            if (maxbiblio == null) {
                maxbiblio = 1;
            } else {
                maxbiblio++;
            }
            session.getTransaction().commit();
           
        } catch(Exception e){
        e.printStackTrace();
        }
        finally {
            session.close();
        }
      return maxbiblio;
    }



}
