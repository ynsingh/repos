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
public class AcqCurrencyDao {

    public static BaseCurrency searchCurrency(String library_id, String base_currency_symbol) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(BaseCurrency.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                    .add(Restrictions.eq("id.baseCurrencySymbol", base_currency_symbol)));
            return (BaseCurrency) criteria.uniqueResult();


        } finally {
            session.close();
        }
    }

    public static BaseCurrency searchCurrency1(String library_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(BaseCurrency.class)
                    .add(Restrictions.conjunction()
                    .add(Restrictions.eq("id.libraryId", library_id))
                   );
            return (BaseCurrency) criteria.uniqueResult();


        } finally {
            session.close();
        }
    }


    public static BaseCurrency getCurrencyByName(String library_id,String base_currency_symbol) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            session.beginTransaction();
            Query query1 = session.createQuery("FROM  BaseCurrency  WHERE id.libraryId =:library_id and id.baseCurrencySymbol=:baseCurrencySymbol");
            query1.setString("library_id", library_id);

            query1.setString("baseCurrencySymbol", base_currency_symbol);

            return (BaseCurrency) query1.uniqueResult();
        }
        finally {
            session.close();
        }

}

public static List<AcqCurrency> getCurrencyList(String library_id,String base_currency_symbol) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            session.beginTransaction();
            Query query1 = session.createQuery("FROM  AcqCurrency  WHERE id.libraryId =:library_id and targetCurrency=:targetCurrency");
            query1.setString("library_id", library_id);

            query1.setString("targetCurrency", base_currency_symbol);

            return (List<AcqCurrency>) query1.list();
        }
        finally {
            session.close();
        }

}

public static List<BaseCurrency> getCurrencyList1(String library_id,String base_currency_symbol) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            session.beginTransaction();
            Query query1 = session.createQuery("FROM  BaseCurrency  WHERE id.libraryId =:library_id and id.baseCurrencySymbol =:baseCurrencySymbol");
            query1.setString("library_id", library_id);

            query1.setString("baseCurrencySymbol", base_currency_symbol);

            return (List<BaseCurrency>) query1.list();
        }
        finally {
            session.close();
        }

}
    public static void insert(BaseCurrency obj) {
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

    public static void update(BaseCurrency obj) {
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


    public static boolean delete(String library_id,String base_currency_symbol) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;


        try {
            tx = (Transaction) session.beginTransaction();
            Query query = session.createQuery("Delete From  BaseCurrency where id.libraryId = :library_id and  id.baseCurrencySymbol=:baseCurrencySymbol");
           query.setString("library_id", library_id);

            query.setString("baseCurrencySymbol", base_currency_symbol);
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


}
