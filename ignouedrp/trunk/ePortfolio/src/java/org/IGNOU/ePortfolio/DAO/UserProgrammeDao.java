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
 * Hibernate Utility class with a convenient method to get Session Factory object.
 *
 * @author IGNOU Team
 */
public class UserProgrammeDao {

    private SessionFactory sessionFactory=new AnnotationConfiguration().configure().buildSessionFactory();
    private Session s;
   
    @SuppressWarnings("unchecked")
    public List<User> UserListByUserId(String user_id) {
        s = sessionFactory.openSession();
        Transaction t = s.beginTransaction();
        List<User> UserProlist = null;
        try {
            UserProlist = s.createQuery("from User where emailId='" + user_id + "'").list();
        } catch (HibernateException HE) {
            System.out.println(HE);
        }
        t.commit();
        s.close();
        sessionFactory.close();
        return UserProlist;
    }
   
}
