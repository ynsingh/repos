/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.DAO;

import java.util.List;
import org.IGNOU.ePortfolio.Model.Governance;
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
 * @since 25 December 2011
 */
public class GovernanceDao {

    private SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
    private Session s;

    public Governance GovernanceSave(Governance GovernanceModel) {
        s = sessionFactory.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();
            s.save(GovernanceModel);
            t.commit();
            return GovernanceModel;
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

    public List<Governance> GovernanceListByUserId(String user_id) {
        s = sessionFactory.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();
            List<Governance> GovernanceList = null;
            try {
                GovernanceList = s.createQuery("from Governance where userId='" + user_id + "'").list();
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

    public Governance GovernanceDelete(long governanceId) {
        s = sessionFactory.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();
            Governance DeleteInfo = (Governance) s.load(Governance.class, governanceId);
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

    public List<Governance> GovernanceListByGovernanceId(long governanceId) {
        s = sessionFactory.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();
            List<Governance> GovernanceList = null;
            try {
                GovernanceList = s.createQuery("from Governance where governanceId='" + governanceId + "'").list();
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

    public Governance GovernanceUpdate(long governanceId, String userId, String nameCommittee, String nameUniversity, String durationFrom, String durationTo, String responsibility, String url, String summary, String address) {
        s = sessionFactory.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();
            Governance UpdateInfo = (Governance) s.load(Governance.class, governanceId);
            UpdateInfo.setGovernanceId(governanceId);
            UpdateInfo.setDurationFrom(durationFrom);
            UpdateInfo.setDurationTo(durationTo);
            UpdateInfo.setNameCommittee(nameCommittee);
            UpdateInfo.setNameUniversity(nameUniversity);
            UpdateInfo.setResponsibility(responsibility);
            UpdateInfo.setSummary(summary);
            UpdateInfo.setUrl(url);
            UpdateInfo.setUserId(userId);
            UpdateInfo.setAddress(address);
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
