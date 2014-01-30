/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.DAO;

import java.util.List;
import org.IGNOU.ePortfolio.Model.UserList;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author Vinay
 */
public class FacultyGroupDao {

    private SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
    private Session s;

    @SuppressWarnings("unchecked")
    public List<UserList> FacultyListByEmailId(String emailId) {
        s = sessionFactory.openSession();
        Transaction t = s.beginTransaction();
        List<UserList> MyFacultyListList = null;
        try {
            MyFacultyListList = s.createQuery("from UserList as ul where ul.role='faculty' and ul.programme=(select u.programmeId from User u where u.emailId='" + emailId + "')").list();
        } catch (HibernateException HE) {
            System.out.println(HE);
        }
        t.commit();
        s.close();
        sessionFactory.close();
        return MyFacultyListList;
    }
}
