/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.DAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.IGNOU.ePortfolio.Model.Consultancy;
import org.IGNOU.ePortfolio.Model.ConsultancyNature;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author IGNOU Team
 * @version 2
 * @since 25 December 2011 Modified by IGNOU Team at 15 February 2012
 */
public class ConsultancyDao {

    private SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
    private Session s;
    /*
     * The method "ConsultancySave" is used to insert Value into Database.
     */

    public Consultancy ConsultancySave(Consultancy ConsultancyModel) {
        s = sessionFactory.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();
            s.save(ConsultancyModel);
            ConsultancyNature cn = new ConsultancyNature();
            for (int i = 0; i < ConsultancyModel.getNameConsultancy().size(); i++) {
                cn.setConsultancy(ConsultancyModel);
                cn.setNameConsultancy(ConsultancyModel.getNameConsultancy().get(i));
                cn.setNatureWork(ConsultancyModel.getNatureWork().get(i));
                s.save(cn);
                s.flush();
                s.clear();
            }
            t.commit();
            return ConsultancyModel;
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

    public List<Consultancy> ConsultancyListByUserId(String user_id) {
        s = sessionFactory.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();
            List<Consultancy> ConsultancyList = null;
            try {
                ConsultancyList = s.createQuery("from Consultancy where user_id='" + user_id + "'").list();
            } catch (HibernateException HE) {
                System.out.println(HE);
            }
            t.commit();
            return ConsultancyList;
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

    public Consultancy ConsultancyDeleteByConsultancyId(long consultancyId) {
        s = sessionFactory.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();
            Consultancy DeleteInfo = (Consultancy) s.load(Consultancy.class, consultancyId);
            if (DeleteInfo != null) {
                s.delete(DeleteInfo);
            }
            t.commit();
            return DeleteInfo;
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

    public List<Consultancy> ConsultancyEdit(long consultancyId) {
        s = sessionFactory.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();
            List<Consultancy> ConsultancyList = null;
            try {
                ConsultancyList = s.createQuery("from Consultancy where consultancyId='" + consultancyId + "'").list();
            } catch (HibernateException HE) {
                System.out.println(HE);
            }
            t.commit();
            return ConsultancyList;
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

    public Consultancy ConsultancyUpdate(ArrayList<Long> CNatureId, Long consultancyId, String userId, String clientName, String DFrom, String DTo, Integer noOfConsultancy, String revenue, String service, String url, String summary, ArrayList<String> nameConsultancy, ArrayList<String> natureWork, Set<ConsultancyNature> consultancyNatures) {
        s = sessionFactory.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();
            Consultancy UpdateInfo = (Consultancy) s.load(Consultancy.class, consultancyId);
            UpdateInfo.setClientName(clientName);
            UpdateInfo.setDFrom(DFrom);
            UpdateInfo.setDTo(DTo);
            UpdateInfo.setNoOfConsultancy(noOfConsultancy);
            UpdateInfo.setRevenue(revenue);
            UpdateInfo.setService(service);
            UpdateInfo.setSummary(summary);
            UpdateInfo.setUrl(url);
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
