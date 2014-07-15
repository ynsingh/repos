/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.DAO;

import java.util.Date;
import java.util.List;
import org.IGNOU.ePortfolio.Model.Course;
import org.IGNOU.ePortfolio.Model.ActivitiesSubmission;
import org.IGNOU.ePortfolio.Model.GradeTypeDetailsMaster;
import org.IGNOU.ePortfolio.Model.GradeTypeMaster;
import org.IGNOU.ePortfolio.Model.GradeValue;
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
 * @author IGNOU Team
 * @version 1
 * @since 12-05-2012
 */
public class GradeTypeDao {

    private SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
    private Session s;

    @SuppressWarnings("unchecked")
    public List<GradeTypeMaster> GradeTypeMasterList() {
        s = sessionFactory.openSession();
        Transaction t = s.beginTransaction();
        List<GradeTypeMaster> GradeTypelist = null;
        try {
            GradeTypelist = s.createQuery("from GradeTypeMaster").list();
        } catch (HibernateException HE) {
            System.out.println(HE);
        }
        t.commit();
        s.close();
        sessionFactory.close();
        return GradeTypelist;
    }

    @SuppressWarnings("unchecked")
    public List<GradeTypeDetailsMaster> GradeTypeDetailsMasterByGradeTypeId(int gtId) {
        s = sessionFactory.openSession();
        Transaction t = s.beginTransaction();
        List<GradeTypeDetailsMaster> GTDetailslist = null;
        try {
            GTDetailslist = s.createQuery("from GradeTypeDetailsMaster where gt_id='" + gtId + "'").list();
        } catch (HibernateException HE) {
            System.out.println(HE);
        }
        t.commit();
        s.close();
        sessionFactory.close();
        return GTDetailslist;
    }

    @SuppressWarnings("unchecked")
    public List<GradeValue> GradeValueJsonListByCourseIdUserId(int courseId, String user_id) {
        s = sessionFactory.openSession();
        Transaction t = s.beginTransaction();
        List<GradeValue> GTDetailslist = null;
        try {
            GTDetailslist = s.createQuery("from GradeValue where course_id='" + courseId + "' and faculty_id='" + user_id + "'").list();
        } catch (HibernateException HE) {
            System.out.println(HE);
        }
        t.commit();
        s.close();
        sessionFactory.close();
        return GTDetailslist;
    }

    public GradeValue GradeValueSave(String user_id, Integer gtdId, String str, Integer courseId) {
        s = sessionFactory.openSession();
        Transaction t = null;
        GradeValue gv = new GradeValue();
        try {
            t = s.beginTransaction();
            Course c = new Course();
            c.setCourseId(courseId);
            UserList u = new UserList();
            u.setEmailId(user_id);
            GradeTypeDetailsMaster g = new GradeTypeDetailsMaster();
            g.setGtdId(gtdId);
            gv.setCourse(c);
            gv.setUser(u);
            gv.setGradeDate(new Date());
            gv.setGradeValue(str);
            gv.setGradeTypeDetailsMaster(g);
            s.save(gv);
            t.commit();
            return gv;
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
    public List<GradeTypeMaster> GradeTypeMasterListByGradeTypeId(int gtId) {
        s = sessionFactory.openSession();
        Transaction t = s.beginTransaction();
        List<GradeTypeMaster> GradeTypelist = null;
        try {
            GradeTypelist = s.createQuery("from GradeTypeMaster where gtId='" + gtId + "'").list();
        } catch (HibernateException HE) {
            System.out.println(HE);
        }
        t.commit();
        s.close();
        sessionFactory.close();
        return GradeTypelist;
    }

    @SuppressWarnings("unchecked")
    public List<GradeValue> GradeValueListByGradeTypeIdUserId(String facultyId, int gtdId) {
        s = sessionFactory.openSession();
        Transaction t = s.beginTransaction();
        List<GradeValue> GradeTypelist = null;
        try {
            GradeTypelist = s.createQuery("from GradeValue where user='" + facultyId + "' and gradeTypeDetailsMaster='" + gtdId + "'").list();
        } catch (HibernateException HE) {
            System.out.println(HE);
        }
        t.commit();
        s.close();
        sessionFactory.close();
        return GradeTypelist;
    }

    @SuppressWarnings("unchecked")
    public List<GradeTypeDetailsMaster> GradeTypeDetailsMasterListByGradeTypeMasterId(int gradeTypeMasterId) {
        s = sessionFactory.openSession();
        Transaction t = s.beginTransaction();
        List<GradeTypeDetailsMaster> gtdList = null;
        try {
            gtdList = s.createQuery("from GradeTypeDetailsMaster where gradeTypeMaster='" + gradeTypeMasterId + "'").list();
        } catch (HibernateException HE) {
            System.out.println(HE);
        }
        t.commit();
        s.close();
        sessionFactory.close();
        return gtdList;
    }

    @SuppressWarnings("unchecked")
    public ActivitiesSubmission EvidenceSubmissionMarksUpdate(int submissionId, String gradesObtained, String facultyComment, String facultyAttachment) {
        s = sessionFactory.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();
            ActivitiesSubmission UpdateEmpInfo = (ActivitiesSubmission) s.load(ActivitiesSubmission.class, submissionId);
            UpdateEmpInfo.setGradesObtained(gradesObtained);
            UpdateEmpInfo.setFacultyComment(facultyComment);
            UpdateEmpInfo.setFacultyAttachment(facultyAttachment);
            if (null != UpdateEmpInfo) {
                s.update(UpdateEmpInfo);
            }
            t.commit();
            return UpdateEmpInfo;
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
    public List<GradeValue> GradeValueListByUserId(String UserId) {
        s = sessionFactory.openSession();
        Transaction t = s.beginTransaction();
        List<GradeValue> GradeTypelist = null;
        try {
            GradeTypelist = s.createQuery("from GradeValue where user='" + UserId + "'").list();
        } catch (HibernateException HE) {
            System.out.println(HE);
        }
        t.commit();
        s.close();
        sessionFactory.close();
        return GradeTypelist;
    }

    @SuppressWarnings("unchecked")
    public GradeValue GradeValueUpdate(int gradeValId, Integer courseId, String facultyId, Integer gtdId, String gradeValue, Date gradeDate) {
        s = sessionFactory.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();
            GradeTypeDetailsMaster g = new GradeTypeDetailsMaster();
            g.setGtdId(gtdId);
            GradeValue UpdateInfo = (GradeValue) s.load(GradeValue.class, gradeValId);
            UpdateInfo.setGradeTypeDetailsMaster(g);
            UpdateInfo.setGradeValue(gradeValue);
            UpdateInfo.setGradeDate(gradeDate);
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
