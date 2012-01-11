/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.hbm;


import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;

/**
 * Hibernate Utility class with a convenient method to get Session Factory object.
 *
 * @author Client
 */
public class HibernateUtil {


   //Static Session Factory
	    private static org.hibernate.SessionFactory sessionFactory;

	    private HibernateUtil() {}

	    static {

	        //Creates the SessionFactory based on the XML Configuration
	        Configuration configs = new Configuration();
	        sessionFactory = configs.configure().buildSessionFactory();

	    }

	    public static SessionFactory getSessionFactory() {
	        return sessionFactory;
	    }

	    public Session openSession() {
	        return sessionFactory.openSession();
	    }

	    public Session getCurrentSession() {
	        return sessionFactory.getCurrentSession();
	    }

	    public static void close() {
	        if (sessionFactory != null)
	            sessionFactory.close();
	        sessionFactory = null;
	    }

}
//
//import org.hibernate.SessionFactory;
//import org.hibernate.cfg.*;
//
//public class HibernateUtil {
//  private static final SessionFactory sessionFactory;
//  static {
//  try {
//  // Create the SessionFactory from hibernate.cfg.xml
//  sessionFactory = new  AnnotationConfiguration().configure().buildSessionFactory();
//  } catch (Throwable ex) {
//  // Make sure you log the exception, as it might be swallowed
//  System.err.println("Initial SessionFactory creation failed." + ex);
//  throw new ExceptionInInitializerError(ex);
//  }
//  }
//
//  public static SessionFactory getSessionFactory() {
//  return sessionFactory;
//  }
//}