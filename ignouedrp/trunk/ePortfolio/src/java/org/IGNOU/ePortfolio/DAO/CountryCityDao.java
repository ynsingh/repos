/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.DAO;

import java.util.List;
import org.IGNOU.ePortfolio.Model.City;
import org.IGNOU.ePortfolio.Model.Country;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 *
 * @author Amit
 */
public class CountryCityDao {

    private SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
    private Session s;

    @SuppressWarnings("unchecked")
    public List<Country> CountryList() {
        s = sessionFactory.openSession();
        Transaction t = s.beginTransaction();

        List<Country> countrylist = null;
        try {
            countrylist = s.createQuery("from Country").list();
            t.commit();
            return countrylist;
        } catch (Throwable ex) {
            //Log the Exception
            t.rollback();
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        } finally {
            s.close();
            sessionFactory.close();
        }

    }

    @SuppressWarnings("unchecked")
    public List<City> CityList(String countryCode) {
        s = sessionFactory.openSession();
        Transaction t = s.beginTransaction();

        List<City> citylist = null;
        try {
            citylist = s.createQuery("from City where countryCode='" + countryCode + "'").list();
            t.commit();
            return citylist;
        } catch (HibernateException ex) {
            //Log the Exception
            t.rollback();
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        } finally {
            s.close();
            sessionFactory.close();
        }
    }
}
