/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.DAO;

import org.IGNOU.ePortfolio.Model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 * Hibernate Utility class with a convenient method to get Session Factory object.
 *
 * @author Vinay Kr. Sharma
 */
public class ResetLoginPasswordDao {

    private SessionFactory sessionFactory=new AnnotationConfiguration().configure().buildSessionFactory();;
    private Session s;
    
    public User UserUpdateByRegistrationIdPassword(long registrationId, String passwordField) {
      
       s = sessionFactory.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();
            User UpdateInfo = (User) s.load(User.class, registrationId);
            UpdateInfo.setPassword(passwordField);
            if (null != UpdateInfo) {
                s.update(UpdateInfo);
            }
            t.commit();
            return UpdateInfo;
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

}
