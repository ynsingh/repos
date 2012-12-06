/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.DAO;

import java.util.Date;
import java.util.List;
import org.IGNOU.ePortfolio.Model.Course;
import org.IGNOU.ePortfolio.Model.EvidenceSubmission;
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

    private SessionFactory sf;

    @SuppressWarnings("unchecked")
    public List<GradeTypeMaster> GradeTypeList() {
        sf = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sf.openSession();
        Transaction t = s.beginTransaction();
        List<GradeTypeMaster> GradeTypelist = null;
        try {
            GradeTypelist = s.createQuery("from GradeTypeMaster").list();
        } catch (HibernateException HE) {
            System.out.println(HE);
        }
        t.commit();
        s.close();
        sf.close();
        return GradeTypelist;
    }

    @SuppressWarnings("unchecked")
    public List<GradeTypeDetailsMaster> GradeTypeDetailsList(int gtId) {
        sf = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sf.openSession();
        Transaction t = s.beginTransaction();
        List<GradeTypeDetailsMaster> GTDetailslist = null;
        try {
            GTDetailslist = s.createQuery("from GradeTypeDetailsMaster where gt_id='" + gtId + "'").list();
        } catch (HibernateException HE) {
            System.out.println(HE);
        }
        t.commit();
        s.close();
        sf.close();
        return GTDetailslist;
    }

    @SuppressWarnings("unchecked")
    public List<GradeValue> GradeTypeJList(int courseId, String user_id) {
        sf = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sf.openSession();
        Transaction t = s.beginTransaction();
        List<GradeValue> GTDetailslist = null;
        try {
            GTDetailslist = s.createQuery("from GradeValue where course_id='" + courseId + "' and faculty_id='" + user_id + "'").list();
        } catch (HibernateException HE) {
            System.out.println(HE);
        }
        t.commit();
        s.close();
        sf.close();
        return GTDetailslist;
    }

    public GradeValue saveVal(String user_id, Integer gtdId, String str,  Integer courseId) {
        sf = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = getSf().openSession();
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
            getSf().close();
        }
    }

    @SuppressWarnings("unchecked")
    public List<GradeValue> CheckGradeVal(String facultyId, Integer gtdId, Integer instituteId, Integer programmeId, Integer courseId) {
        sf = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sf.openSession();
        Transaction t = s.beginTransaction();
        List<GradeValue> GradeValuelist = null;
        try {
            GradeValuelist = s.createQuery("from GradeValue where facultyId='" + facultyId + "'and gtdId='" + gtdId + "' and instituteId='" + instituteId + "' and programmeId='" + programmeId + "' and courseId='" + courseId + "'").list();
        } catch (HibernateException HE) {
            System.out.println(HE);
        }
        t.commit();
        s.close();
        sf.close();
        return GradeValuelist;
    }

    @SuppressWarnings("unchecked")
    public List<GradeTypeMaster> GTList(int gtId) {
        sf = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sf.openSession();
        Transaction t = s.beginTransaction();
        List<GradeTypeMaster> GradeTypelist = null;
        try {
            GradeTypelist = s.createQuery("from GradeTypeMaster where gtId='" + gtId + "'").list();
        } catch (HibernateException HE) {
            System.out.println(HE);
        }
        t.commit();
        s.close();
        sf.close();
        return GradeTypelist;
    }

    @SuppressWarnings("unchecked")
    public List<GradeTypeMaster> GTVal(int gtId) {
        sf = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sf.openSession();
        Transaction t = s.beginTransaction();
        List<GradeTypeMaster> GradeTypelist = null;
        try {
            GradeTypelist = s.createQuery("from GradeTypeMaster where gtId='" + gtId + "'").list();
        } catch (HibernateException HE) {
            System.out.println(HE);
        }
        t.commit();
        s.close();
        sf.close();
        return GradeTypelist;
    }

    @SuppressWarnings("unchecked")
    public List<GradeValue> PopulateGradeVal(String facultyId, int gtdId) {
        sf = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sf.openSession();
        Transaction t = s.beginTransaction();
        List<GradeValue> GradeTypelist = null;
        try {
            GradeTypelist = s.createQuery("from GradeValue where user='" + facultyId + "' and gradeTypeDetailsMaster='" + gtdId + "'").list();
        } catch (HibernateException HE) {
            System.out.println(HE);
        }
        t.commit();
        s.close();
        sf.close();
        return GradeTypelist;
    }

    @SuppressWarnings("unchecked")
    public List<GradeTypeDetailsMaster> GTDMInfo(int gradeTypeMaster) {
        sf = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sf.openSession();
        Transaction t = s.beginTransaction();
        List<GradeTypeDetailsMaster> gtdList = null;
        try {
            gtdList = s.createQuery("from GradeTypeDetailsMaster where gradeTypeMaster='" + gradeTypeMaster + "'").list();
        } catch (HibernateException HE) {
            System.out.println(HE);
        }
        t.commit();
        s.close();
        sf.close();
        return gtdList;
    }

    @SuppressWarnings("unchecked")
    public EvidenceSubmission UpdateActivitiesMarks(int submissionId, String gradesObtained, String facultyComment, String facultyAttachment) {
        sf = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sf.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();
            EvidenceSubmission UpdateEmpInfo = (EvidenceSubmission) s.load(EvidenceSubmission.class, submissionId);
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
            sf.close();
        }
    }

    @SuppressWarnings("unchecked")
    public List<GradeValue> GVInfo(String facultyId) {
        sf = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sf.openSession();
        Transaction t = s.beginTransaction();
        List<GradeValue> gvList = null;
        try {
            gvList = s.createQuery("from GradeValue where facultyId='" + facultyId + "'").list();
        } catch (HibernateException HE) {
            System.out.println(HE);
        }
        t.commit();
        s.close();
        sf.close();
        return gvList;
    }


    @SuppressWarnings("unchecked")
    public List<GradeTypeMaster> getActivityGradeSetup(int courseId, String user_id) {
        sf = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sf.openSession();
        Transaction t = s.beginTransaction();
        List<GradeTypeMaster> gvList = null;
        try {
            gvList = s.createQuery("from GradeTypeMaster as gtm  where gtm.gtId=(select gtdm.gradeTypeMaster.gtId from GradeTypeDetailsMaster as gtdm where gtdm.gtdId=(select gv.gtdId from GradeValue as gv where gv.facultyId='" + user_id + "' and gv.courseId='" + courseId + "'))").list();
        } catch (HibernateException HE) {
            System.out.println(HE);
        }
        t.commit();
        s.close();
        sf.close();
        return gvList;
    }

    @SuppressWarnings("unchecked")
    public List<GradeValue> PopulateGradeSetuplist(String facultyId) {
        sf = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sf.openSession();
        Transaction t = s.beginTransaction();
        List<GradeValue> GradeTypelist = null;
        try {
            GradeTypelist = s.createQuery("from GradeValue where user='" + facultyId + "'").list();
        } catch (HibernateException HE) {
            System.out.println(HE);
        }
        t.commit();
        s.close();
        sf.close();
        return GradeTypelist;
    }

    @SuppressWarnings("unchecked")
    public GradeValue UpdateGradeSetupValue(int gradeValId, Integer courseId, String facultyId, Integer gtdId, String gradeValue, Date gradeDate) {
        sf = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sf.openSession();
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
            sf.close();
        }
    }

    /**
     * @return the sf
     */
    public SessionFactory getSf() {
        return sf;
    }
}
