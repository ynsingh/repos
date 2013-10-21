/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

//package utils;

//import org.hibernate.Session;
//import org.hibernate.cfg.Configuration;
//import org.hibernate.SessionFactory;

/**
 * Hibernate Utility class with a convenient method to get Session Factory object.
 *
 * @author Client
 */
//public class HibernateUtil {

   //Static Session Factory
//	    private static org.hibernate.SessionFactory sessionFactory;

//	    private HibernateUtil() {}

//	    static {

	        //Creates the SessionFactory based on the XML Configuration
//	        Configuration configs = new Configuration();
//	        sessionFactory = configs.configure().buildSessionFactory();

//	    }

//	    public static SessionFactory getSession() {
//	        return sessionFactory;
//	    }

//	    public Session openSession() {
//	        return sessionFactory.openSession();
//	    }

//	    public Session getCurrentSession() {
//	        return sessionFactory.getCurrentSession();
//	    }

//	    public static void close() {
//	        if (sessionFactory != null)
//	            sessionFactory.close();
//	        sessionFactory = null;
//	    }

//}

//------------------------ The following Code is in use on September 06, 2012 ========================================

//package utils;
//
//import org.hibernate.cfg.AnnotationConfiguration;
//import org.hibernate.SessionFactory;
//import org.hibernate.Session;
//
//public class HibernateUtil {
//
//    private static final SessionFactory sessionFactory;
//
//    static {
//        try {
//            sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
//        }
//        catch (Throwable ex) {
//            System.err.println("Initial SessionFactory creation failed." + ex);
//            throw new ExceptionInInitializerError(ex);
//        }
//    }
//
//    public static SessionFactory getSessionFactory() {
//        return sessionFactory;
//    }
//
//public static Session getSession() {
//        return sessionFactory.openSession();
//    }
//
//}

//=== Following is the new code

package utils;

//import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.Session;

public class HibernateUtil {

    private static   SessionFactory sessionFactory;

    public static void createSessionFactory() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    public static Session getSession() {
        return sessionFactory.openSession();
    }

}

