/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.AcquisitionDao;

import  com.myapp.struts.hbm.*;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import java.util.List;
import org.hibernate.Query;

/**
 *
 * @author maqbool
 */
public class AcqCurrencyDao {

    public  BaseCurrency searchCurrency(String library_id, String base_currency_symbol) {
        Session session = HibernateUtil.getSessionFactory().openSession();
BaseCurrency obj=null;
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(BaseCurrency.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.baseCurrencySymbol", base_currency_symbol)));
            obj= (BaseCurrency) criteria.uniqueResult();
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

    public  BaseCurrency searchCurrency1(String library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
BaseCurrency obj=null;
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(BaseCurrency.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                   );
            obj= (BaseCurrency) criteria.uniqueResult();
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


    public  BaseCurrency getCurrencyByName(String library_id,String base_currency_symbol) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        BaseCurrency obj=null;
        try {
            session.beginTransaction();
            Query query1 = session.createQuery("FROM  BaseCurrency  WHERE id.libraryId =:library_id and id.baseCurrencySymbol=:baseCurrencySymbol");
            query1.setString("library_id", library_id);

            query1.setString("baseCurrencySymbol", base_currency_symbol);

            obj= (BaseCurrency) query1.uniqueResult();
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


public  List<AcqCurrency> getCurrencyList(String library_id,String base_currency_symbol) {
        Session session = HibernateUtil.getSessionFactory().openSession();
       List<AcqCurrency> obj=null;
        try {
            session.beginTransaction();
            Query query1 = session.createQuery("FROM  AcqCurrency  WHERE id.libraryId =:library_id and targetCurrency=:targetCurrency");
            query1.setString("library_id", library_id);

            query1.setString("targetCurrency", base_currency_symbol);

            obj= (List<AcqCurrency>) query1.list();
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


public  List<BaseCurrency> getCurrencyList1(String library_id,String base_currency_symbol) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<BaseCurrency> obj=null;
        try {
            session.beginTransaction();
            Query query1 = session.createQuery("FROM  BaseCurrency  WHERE id.libraryId =:library_id and id.baseCurrencySymbol =:baseCurrencySymbol");
            query1.setString("library_id", library_id);

            query1.setString("baseCurrencySymbol", base_currency_symbol);

          obj=(List<BaseCurrency>) query1.list();
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

    public  void insert(BaseCurrency obj) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;


        try {
            tx = (Transaction) session.beginTransaction();

            session.save(obj);
            tx.commit();



        }
         catch(Exception e){
             tx.rollback();
        e.printStackTrace();
        }
        finally {
            session.close();
        }
       
    }


    public  void update(BaseCurrency obj) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.update(obj);
            tx.commit();
        }  catch(Exception e){
        e.printStackTrace();
        tx.rollback();
        }
        finally {
            session.close();
        }
     
    }


    public  boolean delete(String library_id,String base_currency_symbol) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;


        try {
            tx = (Transaction) session.beginTransaction();
            Query query = session.createQuery("Delete From  BaseCurrency where id.libraryId = :library_id and  id.baseCurrencySymbol=:baseCurrencySymbol");
           query.setString("library_id", library_id);

            query.setString("baseCurrencySymbol", base_currency_symbol);
            query.executeUpdate();
            tx.commit();



        }
         catch(Exception e){
        e.printStackTrace();
        return false;
        }
        finally {
            session.close();
        }
       return true;
    }


}
