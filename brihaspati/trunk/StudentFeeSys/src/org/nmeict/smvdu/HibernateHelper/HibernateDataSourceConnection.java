/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nmeict.smvdu.HibernateHelper;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author KESU
 */
public class HibernateDataSourceConnection {

    private static final SessionFactory sessionFactory;
    
    static {
        try {
            // Create the SessionFactory from standard (hibernate.cfg.xml) 
            // config file.
            sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
      } catch (Throwable ex) {
            // Log the exception. 
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    public static final ThreadLocal session = new ThreadLocal();
    public static Session currentSession() throws HibernateException
    {
        Session s = (Session) session.get();
        if(s == null)
        {
            s = sessionFactory.openSession();
            session.set(s); 
        }
        return s;
    }
    
    public static void closeSession() throws HibernateException
    {
        Session s = (Session) session.get();
        session.set(null);
        if(s!=null)
        {
            s.close();
        }
    }
    
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}