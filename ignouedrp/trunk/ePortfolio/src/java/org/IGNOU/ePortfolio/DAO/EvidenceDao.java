/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.DAO;

import java.util.Date;
import java.util.List;
import org.IGNOU.ePortfolio.Model.ActivitiesComments;
import org.IGNOU.ePortfolio.Model.Course;
import org.IGNOU.ePortfolio.Model.Evidence;
import org.IGNOU.ePortfolio.Model.EvidenceSubmission;
import org.IGNOU.ePortfolio.Model.GradeValue;
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
public class EvidenceDao {

    private SessionFactory sessionFactory;

    public Evidence saveEvidence(String evTitle, String instruction, String myAttachFileName, String user_id, Date openDate, Date closeDate, Date lastAcceptDate, Boolean addDateSchedule, Boolean addAnnoOdate, Boolean addtoGradebook, Boolean saveEvid, int coursesId, int gradeId) {
        sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sessionFactory.openSession();
        Transaction t = null;
        Evidence e = new Evidence();
        try {
            t = s.beginTransaction();
            e.setEvTitle(evTitle);
            e.setAddAnnoOdate(addAnnoOdate);
            e.setAddDateSchedule(addDateSchedule);
            e.setAddtoGradebook(addtoGradebook);
            e.setCloseDate(closeDate);
            Course c = new Course();
            c.setCourseId(coursesId);
            e.setCourse(c);
            GradeValue g = new GradeValue();
            g.setGradeValId(gradeId);
            e.setGradeValue(g);
            e.setInstruction(instruction);
            e.setLastAcceptDate(lastAcceptDate);
            e.setOpenDate(openDate);
            e.setSaveEvid(saveEvid);
            UserList u = new UserList();
            u.setEmailId(user_id);
            e.setUser(u);
            e.setAssAttach(myAttachFileName);
            e.setCancel(Boolean.FALSE);
            s.save(e);
            t.commit();
            return e;
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

    public EvidenceSubmission saveEvidenceSubmission(int evidenceId, String user_id, String instructions, String attachment, Date subDate, Boolean submitted, Boolean post, Boolean saveDraft) {
        sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sessionFactory.openSession();
        Transaction t = null;
        EvidenceSubmission ev = new EvidenceSubmission();
        try {
            t = s.beginTransaction();
            UserList u = new UserList();
            u.setEmailId(user_id);
            Evidence e = new Evidence();
            e.setEvidenceId(evidenceId);
            ev.setEvidence(e);
            ev.setUser(u);
            ev.setInstructions(instructions);
            ev.setAttachment(attachment);
            ev.setSubDate(new Date());
            ev.setSubmitted(Boolean.TRUE);
            ev.setSaveDraft(saveDraft);

            s.save(ev);
            t.commit();
            return ev;
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
    /*IGNOU Team*/

    @SuppressWarnings("unchecked")
    public List<Evidence> EvidenceDraftList(String facultyId) {
        sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sessionFactory.openSession();
        Transaction t = s.beginTransaction();
        List<Evidence> GradeTypelist = null;
        try {
            GradeTypelist = s.createQuery("from Evidence where faculty_id='" + facultyId + "' and saveEvid='1'").list();
        } catch (HibernateException HE) {
            System.out.println(HE);
        }
        t.commit();
        s.close();
        sessionFactory.close();
        return GradeTypelist;
    }

    @SuppressWarnings("unchecked")
    public List<Evidence> EvidenceReviewInfo(int evidenceId) {
        sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sessionFactory.openSession();
        Transaction t = s.beginTransaction();
        List<Evidence> EvidenceInfo = null;
        try {
            EvidenceInfo = s.createQuery("from Evidence where evidenceId='" + evidenceId + "' ").list();
        } catch (HibernateException HE) {
            System.out.println(HE);
        }
        t.commit();
        s.close();
        sessionFactory.close();
        return EvidenceInfo;
    }

    @SuppressWarnings("unchecked")
    public List<Evidence> EvidenceList(String userId) {
        sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sessionFactory.openSession();
        Transaction t = s.beginTransaction();
        List<Evidence> StudentEvilist = null;
        try {
            StudentEvilist = s.createQuery("from Evidence as e where e.saveEvid!='1' and e.evidenceId not in (select es.evidence.evidenceId from EvidenceSubmission as es where es.user='" + userId + "') and e.course in (select courseId from Course  where programme_id in (select programme.programmeId from UserList where email_id='" + userId + "'))").list();
        } catch (HibernateException HE) {
            System.out.println(HE);
        }
        t.commit();
        s.close();
        sessionFactory.close();
        return StudentEvilist;
    }

    @SuppressWarnings("unchecked")
    public List<Evidence> EvidenceSubmitedList(String userId) {
        sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sessionFactory.openSession();
        Transaction t = s.beginTransaction();
        List<Evidence> GradeTypelist = null;
        try {
            GradeTypelist = s.createQuery("from Evidence as e  where e.evidenceId in (select es.evidence.evidenceId from EvidenceSubmission as es where es.user='" + userId + "')").list();
        } catch (HibernateException HE) {
            System.out.println(HE);
        }
        t.commit();
        s.close();
        sessionFactory.close();
        return GradeTypelist;
    }
     @SuppressWarnings("unchecked")
    public List<EvidenceSubmission> SubmitedEvidenceList(String userId) {
        sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sessionFactory.openSession();
        Transaction t = s.beginTransaction();
        List<EvidenceSubmission> evidencesubmittedlist = null;
        try {
            evidencesubmittedlist = s.createQuery("from EvidenceSubmission as es where es.user='" + userId + "' and saveDraft='0'").list();
        } catch (HibernateException HE) {
            System.out.println(HE);
        }
        t.commit();
        s.close();
        sessionFactory.close();
        return evidencesubmittedlist;
    }

    @SuppressWarnings("unchecked")
    public List<Evidence> FacultyEvidenceList(String facultyId) {
        sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sessionFactory.openSession();
        Transaction t = s.beginTransaction();
        List<Evidence> GradeTypelist = null;
        try {
            GradeTypelist = s.createQuery("from Evidence where user='" + facultyId + "' and saveEvid='0' and cancel='0'").list();
        } catch (HibernateException HE) {
            System.out.println(HE);
        }
        t.commit();
        s.close();
        sessionFactory.close();
        return GradeTypelist;
    }

    @SuppressWarnings("unchecked")
    public List<Evidence> EvidenceInfoList(int evidenceId) {
        sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sessionFactory.openSession();
        Transaction t = s.beginTransaction();
        List<Evidence> EvidInfo = null;
        try {
            EvidInfo = s.createQuery("from Evidence where evidenceId='" + evidenceId + "'").list();
        } catch (HibernateException HE) {
            System.out.println(HE);
        }
        t.commit();
        s.close();
        sessionFactory.close();
        return EvidInfo;
    }

    @SuppressWarnings("unchecked")
    public List<EvidenceSubmission> EviStdSubList(int evidenceId, String userId) {
        sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sessionFactory.openSession();
        Transaction t = s.beginTransaction();
        List<EvidenceSubmission> EvidStdList = null;
        try {
            EvidStdList = s.createQuery("from EvidenceSubmission where evidence='" + evidenceId + "' and user!='" + userId + "'").list();
        } catch (HibernateException HE) {
            System.out.println(HE);
        }
        t.commit();
        s.close();
        sessionFactory.close();
        return EvidStdList;
    }

    @SuppressWarnings("unchecked")
    public List<Evidence> PeerEviList(int instituteId, int programmeId) {
        sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sessionFactory.openSession();
        Transaction t = s.beginTransaction();
        List<Evidence> EvidStdList = null;
        try {
            EvidStdList = s.createQuery("from Evidence where instituteId='" + instituteId + "' and programmeId='" + programmeId + "' order by evidenceId desc").list();
        } catch (HibernateException HE) {
            System.out.println(HE);
        }
        t.commit();
        s.close();
        sessionFactory.close();
        return EvidStdList;
    }

    @SuppressWarnings("unchecked")
    public List<EvidenceSubmission> MySubInfo(int evidenceId, String userId) {
        sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sessionFactory.openSession();
        Transaction t = s.beginTransaction();
        List<EvidenceSubmission> MyEvidSubList = null;
        try {
            MyEvidSubList = s.createQuery("from EvidenceSubmission where user='" + userId + "' and evidence='" + evidenceId + "'").list();
        } catch (HibernateException HE) {
            System.out.println(HE);
        }
        t.commit();
        s.close();
        sessionFactory.close();
        return MyEvidSubList;
    }

    @SuppressWarnings("unchecked")
    public List<EvidenceSubmission> getEviSubmitedGrade(String userId, String evidenceIdList) {
        sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sessionFactory.openSession();
        Transaction t = s.beginTransaction();
        List<EvidenceSubmission> MyEvidSubList = null;
        try {
            MyEvidSubList = s.createQuery("from EvidenceSubmission where user='" + userId + "' and evidence in (" + evidenceIdList + ")").list();
        } catch (HibernateException HE) {
            System.out.println(HE);
        }
        t.commit();
        s.close();
        sessionFactory.close();
        return MyEvidSubList;
    }

    @SuppressWarnings("unchecked")
    public List<ActivitiesComments> PeerCommentInfo(String submitorId, int activityId) {
        sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sessionFactory.openSession();
        Transaction t = s.beginTransaction();
        List<ActivitiesComments> CommentList = null;
        try {
            CommentList = s.createQuery("from ActivitiesComments where userBySubmitorId='" + submitorId + "' and evidence='" + activityId + "'").list();
        } catch (HibernateException HE) {
            System.out.println(HE);
        }
        t.commit();
        s.close();
        sessionFactory.close();
        return CommentList;
    }
    
    @SuppressWarnings("unchecked")
    public List<ActivitiesComments> CommentList(String submitorId, int activityId) {
        sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sessionFactory.openSession();
        Transaction t = s.beginTransaction();
        List<ActivitiesComments> CommentList = null;
        try {
            CommentList = s.createQuery("from ActivitiesComments where userBySubmitorId='" + submitorId + "' and evidence='" + activityId + "'").list();
        } catch (HibernateException HE) {
            System.out.println(HE);
        }
        t.commit();
        s.close();
        sessionFactory.close();
        return CommentList;
    }

    public Evidence UpdateEvi(int evidenceId, String evTitle, Date openDate, Date closeDate, Date lastAcceptDate, String instruction, Boolean addDateSchedule, Boolean addAnnoOdate, Boolean addtoGradebook, String assAttach, Boolean saveEvid, Boolean cancel, String cancelReason) {
        sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sessionFactory.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();
            Evidence UpdateInfo = (Evidence) s.load(Evidence.class, evidenceId);
            UpdateInfo.setAddAnnoOdate(addAnnoOdate);
            UpdateInfo.setAddDateSchedule(addDateSchedule);
            UpdateInfo.setAddtoGradebook(addtoGradebook);
            UpdateInfo.setAssAttach(assAttach);
            UpdateInfo.setCloseDate(closeDate);
            UpdateInfo.setEvTitle(evTitle);
            UpdateInfo.setInstruction(instruction);
            UpdateInfo.setLastAcceptDate(lastAcceptDate);
            UpdateInfo.setOpenDate(openDate);
            UpdateInfo.setSaveEvid(saveEvid);
            UpdateInfo.setCancel(cancel);
            UpdateInfo.setCancelReason(cancelReason);
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


    @SuppressWarnings("unchecked")
    public List<Course> GradeSetUpCourceList(String userId) {
        sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sessionFactory.openSession();
        Transaction t = s.beginTransaction();
        List<Course> GradeTypelist = null;
        try {
            GradeTypelist = s.createQuery("select course from GradeValue as g where g.user='" + userId + "' ").list();
        } catch (HibernateException HE) {
            System.out.println(HE);
        }
        t.commit();
        s.close();
        sessionFactory.close();
        return GradeTypelist;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
