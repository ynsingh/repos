/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.DAO;

import java.util.List;
import org.IGNOU.ePortfolio.Model.Course;
import org.IGNOU.ePortfolio.Model.Department;
import org.IGNOU.ePortfolio.Model.Institute;
import org.IGNOU.ePortfolio.Model.Programme;
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
public class ProgrammeCourseDao {

    private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    public List<Institute> univList(int instituteId) {
        sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sessionFactory.openSession();
        Transaction t = s.beginTransaction();
        List<Institute> institutelist = null;
        try {
            institutelist = s.createQuery("from Institute where instituteId='" + instituteId + "'").list();
            t.commit();
            return institutelist;
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
    public List<Programme> CourseProList(int programmeId) {
        sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sessionFactory.openSession();
        Transaction t = s.beginTransaction();
        List<Programme> Programmelist = null;
        try {
            Programmelist = s.createQuery("from Programme where programmeId='" + programmeId + "'").list();
            t.commit();
            return Programmelist;
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
    public List<Course> CorProInsList(int courseId) {
        sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sessionFactory.openSession();
        Transaction t = s.beginTransaction();
        List<Course> Courcelist = null;
        try {
            Courcelist = s.createQuery("from Course where courseId='" + courseId + "'").list();
            t.commit();
            return Courcelist;
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


    public Programme RegDeptProgramme(int departmentId, int instituteId, String programmeName, String programmeCode, Integer duration, String overview) {
        sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sessionFactory.openSession();
        Transaction t = null;
        Institute inst = new Institute();
        Department dept = new Department();
        try {
            t = s.beginTransaction();
            inst.setInstituteId(instituteId);
            dept.setDepartmentId(departmentId);
            Programme ProModel = new Programme();
            ProModel.setDepartment(dept);
            ProModel.setDuration(duration);
            ProModel.setInstitute(inst);
            ProModel.setOverview(overview);
            ProModel.setProgrammeCode(programmeCode);
            ProModel.setProgrammeName(programmeName);
            s.save(ProModel);
            t.commit();
            return ProModel;
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
    /*
     * Course Dao
     */

    public Course RegProCourse(Integer programmeId, String courseCode, String courseName) {
        sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sessionFactory.openSession();
        Transaction t = null;
        Programme pro = new Programme();
        try {
            t = s.beginTransaction();
            pro.setProgrammeId(programmeId);
            Course CourseModel = new Course();
            CourseModel.setCourseCode(courseCode);
            CourseModel.setCourseName(courseName);
            CourseModel.setProgramme(pro);
            s.save(CourseModel);
            t.commit();
            return CourseModel;
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

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
