/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.DAO;

import java.util.List;
import org.IGNOU.ePortfolio.Model.ActivitiesComments;
import org.IGNOU.ePortfolio.Model.Allowcomment;
import org.IGNOU.ePortfolio.Model.Evidence;
import org.IGNOU.ePortfolio.Model.EvidenceSubmission;
import org.IGNOU.ePortfolio.Model.UserList;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 *
 * @author IGNOU Team
 */
public class CommentDao {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @SuppressWarnings("unchecked")
    public List<EvidenceSubmission> EvidenceCrTskUsList(Integer courseId, Integer evidenceId) {
        sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sessionFactory.openSession();
        Transaction t = s.beginTransaction();
        List<EvidenceSubmission> EvidCommentInfo = null;
        try {
            EvidCommentInfo = s.createQuery("from EvidenceSubmission where courseId='" + courseId + "'and evidenceId='" + evidenceId + "' and submitted='1' ").list();
        } catch (HibernateException HE) {
            System.out.println(HE);
        }
        t.commit();
        s.close();
        sessionFactory.close();
        return EvidCommentInfo;
    }

    public ActivitiesComments saveComment(int evidenceId, String SubmitorID, UserList userByCommentorId, int SubmitionId, String comment, String commentorFilePath, Double rating) {
        sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sessionFactory.openSession();
        Transaction t = null;
        ActivitiesComments CModel = new ActivitiesComments();
        try {
            t = s.beginTransaction();
            UserList u = new UserList();
            u.setEmailId(SubmitorID);
            CModel.setUserBySubmitorId(u);
            CModel.setUserByCommentorId(userByCommentorId);
            Evidence evi = new Evidence();
            evi.setEvidenceId(evidenceId);
            CModel.setEvidence(evi);
            EvidenceSubmission eviSub = new EvidenceSubmission();
            eviSub.setSubmissionId(SubmitionId);
            CModel.setEvidenceSubmission(eviSub);
            CModel.setComment(comment);
            CModel.setRating(rating);
            CModel.setCommentorFilePath(commentorFilePath);
            s.save(CModel);
            t.commit();
            return CModel;
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

    public Allowcomment saveAllowedComment(Allowcomment AcModel) {
        sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sessionFactory.openSession();
        Transaction t = null;

        try {
            t = s.beginTransaction();

            s.save(AcModel);
            t.commit();
            return AcModel;
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

    //update evidence submission table with list of users 
    @SuppressWarnings("unchecked")
    public EvidenceSubmission UpdateAllowedStudent(int submissionId, Boolean canComment, String listStudent) {
        sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sessionFactory.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();

            EvidenceSubmission UpdateAllowedstlist = (EvidenceSubmission) s.load(EvidenceSubmission.class, submissionId);
            UpdateAllowedstlist.setCanComment(canComment);
            UpdateAllowedstlist.setListStudent(listStudent);
            if (null != UpdateAllowedstlist) {
                s.update(UpdateAllowedstlist);
            }
            t.commit();
            return UpdateAllowedstlist;
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
