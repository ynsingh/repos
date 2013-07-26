/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.DAO;

import org.IGNOU.ePortfolio.Model.ActivitiesComments;
import org.IGNOU.ePortfolio.Model.Allowcomment;
import org.IGNOU.ePortfolio.Model.Evidence;
import org.IGNOU.ePortfolio.Model.EvidenceSubmission;
import org.IGNOU.ePortfolio.Model.UserList;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 *
 * @author IGNOU Team
 */
public class CommentDao {

    private SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
    private Session s;


    public ActivitiesComments CommantSave(int evidenceId, String SubmitorID, UserList userByCommentorId, int SubmitionId, String comment, String commentorFilePath, Double rating) {
        s = sessionFactory.openSession();
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

    public Allowcomment AllowedCommentSave(Allowcomment AcModel) {
        s = sessionFactory.openSession();
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
    public EvidenceSubmission EvidenceSubmissionUpdateWithAllowedStudent(int submissionId, Boolean canComment, String listStudent) {
        s = sessionFactory.openSession();
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
