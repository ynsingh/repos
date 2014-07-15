/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.DAO;

import java.util.List;
import org.IGNOU.ePortfolio.Model.ReviewCommittee;
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
 */
public class ReviewCommitteeDao {

    private SessionFactory sessionFactory  = new AnnotationConfiguration().configure().buildSessionFactory();
    private Session s;
    public ReviewCommittee saveRCInfo(ReviewCommittee RCModel) {
        s = sessionFactory.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();
            s.save(RCModel);
            t.commit();
            return RCModel;
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

    public List<ReviewCommittee> ShowRC(String user_id) {
       s = sessionFactory.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();
            List<ReviewCommittee> RCListList = null;
            try {
                RCListList = s.createQuery("from ReviewCommittee where userId='" + user_id + "'").list();
            } catch (HibernateException HE) {
                System.out.println(HE);
            }
            t.commit();
            return RCListList;
        } catch (Throwable ex) { //Log theException
            t.rollback();
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        } finally {
            s.close();
            sessionFactory.close();
        }
    }

    public List<ReviewCommittee> EditRC(long reviewCommitteeId) {
        s = sessionFactory.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();
            List<ReviewCommittee> RCListList = null;
            try {
                RCListList = s.createQuery("from ReviewCommittee where reviewCommitteeId='" + reviewCommitteeId + "'").list();
            } catch (HibernateException HE) {
                System.out.println(HE);
            }
            t.commit();
            return RCListList;
        } catch (Throwable ex) { //Log theException
            t.rollback();
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        } finally {
            s.close();
            sessionFactory.close();
        }
    }

    public ReviewCommittee UpdateRC(long reviewCommitteeId, String userId, String committeeType, String role, String committeeName, String date, String frequency, String url, String review, byte[] minutesFile) {
       s = sessionFactory.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();
            ReviewCommittee UpdateInfo = (ReviewCommittee) s.load(ReviewCommittee.class, reviewCommitteeId);
            UpdateInfo.setCommitteeName(committeeName);
            UpdateInfo.setCommitteeType(committeeType);
            UpdateInfo.setDate(date);
            UpdateInfo.setFrequency(frequency);
            UpdateInfo.setMinutesFile(minutesFile);
            UpdateInfo.setReview(review);
            //  UpdateInfo.setReviewCommitteeId(reviewCommitteeId);
            UpdateInfo.setRole(role);
            UpdateInfo.setUrl(url);
            //           UpdateInfo.setUserId(userId);
            if (null != UpdateInfo) {
                s.update(UpdateInfo);
            }
            t.commit();
            return UpdateInfo;
        } catch (Throwable ex) { //Log the Exception t.rollback();
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        } finally {
            s.close();
            sessionFactory.close();
        }
    }

    public ReviewCommittee DeleteRC(long reviewCommitteeId) {
       s = sessionFactory.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();
            ReviewCommittee DeleteInfo = (ReviewCommittee) s.load(ReviewCommittee.class, reviewCommitteeId);
            if (DeleteInfo != null) {
                s.delete(DeleteInfo);
            }
            t.commit();
            return DeleteInfo;
        } catch (Throwable ex) { //Log the Exception 
            t.rollback();
            System.err.println("Initial   SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        } finally {
            s.close();
            sessionFactory.close();
        }
    }

}
