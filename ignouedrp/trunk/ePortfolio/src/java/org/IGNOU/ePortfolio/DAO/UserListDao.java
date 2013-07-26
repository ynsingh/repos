/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.DAO;

import java.util.List;
import org.IGNOU.ePortfolio.Model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
/**
 *
 * @author IGNOU Team
 */
public class UserListDao {
    private SessionFactory sessionFactory;
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
    public List<User> UserListByProgrammeIdUserId(int programmeId,String emailId) {
        sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sessionFactory.openSession();
        Transaction t = s.beginTransaction();

        List<User> TestiUlist = null;
        try {
            TestiUlist = s.createQuery("from User where  programmeId='" + programmeId + "' and emailId!='" + emailId + "' and role ='student' ").list();
        } catch (HibernateException HE) {
            System.out.println(HE);
        }
        t.commit();
        s.close();
        sessionFactory.close();
        return TestiUlist;
    }

}
