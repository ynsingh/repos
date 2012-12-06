/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.DAO;

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
 * @version 1
 * @since 21-04-2012
 */
public class InstituteRegDao {

    private SessionFactory sf;

    public Institute AddInfo(Institute InstModel) throws Exception {
        sf = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = getSf().openSession();
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
            getSf().close();
        }
    }

    /**
     * @return the sf
     */
    public SessionFactory getSf() {
        return sf;
    }
}
