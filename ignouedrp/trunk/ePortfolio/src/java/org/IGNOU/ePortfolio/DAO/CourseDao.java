/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.DAO;

import com.opensymphony.xwork2.ActionContext;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.IGNOU.ePortfolio.Model.Course;
import org.IGNOU.ePortfolio.Model.Programme;
import org.IGNOU.ePortfolio.Model.UserList;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author vinay
 */
public class CourseDao {

    private SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
    private Session s;
    private Map session = ActionContext.getContext().getSession();
    private String role = session.get("role").toString();
    final Logger logger = Logger.getLogger(this.getClass());

    @SuppressWarnings("unchecked")
    public List<Course> CourseListByCourseId(int courseId) {
        s = sessionFactory.openSession();
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

    @SuppressWarnings("unchecked")
    public List<Course> CourseListByProgrammeId(int programmeId) {
        s = sessionFactory.openSession();
        Transaction t = s.beginTransaction();
        List<Course> Courselist = null;
        try {
            Courselist = s.createQuery("from Course where programme='" + programmeId + "'").list();
            t.commit();
            return Courselist;
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
    public List<Course> CourseListByUserId(String user_id) {
        s = sessionFactory.openSession();
        Transaction t = s.beginTransaction();
        List<Course> Courcelist = null;
        try {
            if (role.equals("admin")) {
                Courcelist = s.createQuery("from Course").list();
                logger.warn("All Course List for " + role);
            }
            if (role.equals("student")) {
                Courcelist = s.createQuery("from Course where programme=(select programme.programmeId from UserList where emailId='" + user_id + "')").list();
                logger.warn("Programme Specific Course List for " + role);
            }
            if (role.equals("faculty")) {
                Courcelist = s.createQuery("from Course where programme=(select programme.programmeId from UserList where emailId='" + user_id + "') or coursCreator='" + user_id + "'").list();
                logger.warn("Faculty Specific Course List for " + role);
            }
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

    public Course CourseSave(Integer programmeId, String courseCode, String courseName, String creator, Date create_date) {
        s = sessionFactory.openSession();
        Transaction t = null;
        Programme pro = new Programme();
        UserList u = new UserList();
        try {
            t = s.beginTransaction();
            pro.setProgrammeId(programmeId);
            u.setEmailId(creator);
            Course CourseModel = new Course();
            CourseModel.setCourseCode(courseCode);
            CourseModel.setCourseName(courseName);
            CourseModel.setProgramme(pro);
            CourseModel.setCoursCreator(u);
            CourseModel.setCourceCreatorDate(create_date);
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
}
