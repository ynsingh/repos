/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.DAO;

import java.util.List;
import org.IGNOU.ePortfolio.Model.Institute;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author IGNOU Team
 */
public class InstituteDAO {

    private SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
    private Session s;

    @SuppressWarnings("unchecked")
    public List<Institute> InstituteList() {
        s = sessionFactory.openSession();
        Transaction t = s.beginTransaction();

        List<Institute> Institutelist;
        try {
            Institutelist = s.createQuery("from Institute").list();
            t.commit();
            return Institutelist;
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
    public List<Institute> InstituteListByInstituteId(int instituteId) {
        s = sessionFactory.openSession();
        Transaction t = s.beginTransaction();
        List<Institute> institutelist = null;
        try {
            institutelist = s.createQuery("from Institute where instituteId='" + instituteId + "'").list();
            t.commit();
            return institutelist;
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
    public List<Institute> InstituteListByUserId(String user_id) {
        s = sessionFactory.openSession();
        Transaction t = s.beginTransaction();

        List<Institute> Institutelist = null;
        try {
            Institutelist = s.createQuery("from Institute where instituteId=(select instituteId from User where emailId='" + user_id + "')").list();
            t.commit();
            return Institutelist;
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

    public Institute InstituteSave(Institute InstModel) throws Exception {
        s = sessionFactory.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();
            s.save(InstModel);
            t.commit();
            return InstModel;
        } catch (Exception e) {
            System.err.println("Exception Occure" + e);
            throw new Exception(e.toString());
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
