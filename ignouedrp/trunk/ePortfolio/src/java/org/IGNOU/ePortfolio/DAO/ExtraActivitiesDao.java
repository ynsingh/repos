/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.DAO;

import java.util.List;
import org.IGNOU.ePortfolio.Model.ExtraActivities;
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
 * @version 1
 * @since 29-02-2012 Last Modified on 01-03-2012 by IGNOU Team
 */
public class ExtraActivitiesDao {

    private SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
    private Session s;

    public ExtraActivities ExtraActivitiesSave(ExtraActivities ExtModel) {
        s = sessionFactory.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();
            s.save(ExtModel);
            t.commit();
            return ExtModel;
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

    public List<ExtraActivities> ExtraActivitiesListByUserId(String user_id) {
        s = sessionFactory.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();
            List<ExtraActivities> eaList = null;
            try {
                eaList = s.createQuery("from ExtraActivities where userId='" + user_id + "'").list();
            } catch (HibernateException HE) {
                System.out.println(HE);
            }
            t.commit();
            return eaList;
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

    public ExtraActivities ExtraActivitiesDelete(long activitiesId) {
        s = sessionFactory.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();
            ExtraActivities DeleteInfo = (ExtraActivities) s.load(ExtraActivities.class, activitiesId);
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

    public List<ExtraActivities> ExtraActivitiesListByActivityId(long activitiesId) {
        s = sessionFactory.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();
            List<ExtraActivities> GovernanceList = null;
            try {
                GovernanceList = s.createQuery("from ExtraActivities where activitiesId='" + activitiesId + "'").list();
            } catch (HibernateException HE) {
                System.out.println(HE);
            }
            t.commit();
            return GovernanceList;
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

    public ExtraActivities ExtraActivitiesUpdate(long activitiesId, String userId, String organizationName, String cause, String role, String tfrom, String tto, String description) {
        s = sessionFactory.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();
            ExtraActivities UpdateExpInfo = (ExtraActivities) s.load(ExtraActivities.class, activitiesId);
            UpdateExpInfo.setCause(cause);
            UpdateExpInfo.setDescription(description);
            UpdateExpInfo.setOrganizationName(organizationName);
            UpdateExpInfo.setRole(role);
            UpdateExpInfo.setTfrom(tfrom);
            UpdateExpInfo.setTto(tto);
            if (null != UpdateExpInfo) {
                s.update(UpdateExpInfo);
            }
            t.commit();
            return UpdateExpInfo;
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
