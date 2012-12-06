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
 */
public class InstituteDao {

    private SessionFactory sf;

    @SuppressWarnings("unchecked")
    public List<Institute> InstituteList() {
        sf = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sf.openSession();
        Transaction t = s.beginTransaction();

        List<Institute> Institutelist = null;
        try {
            Institutelist = s.createQuery("from Institute").list();
            t.commit();
            return Institutelist;
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
    public List<Institute> UserInstitute(String user_id) {
        sf = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sf.openSession();
        Transaction t = s.beginTransaction();

        List<Institute> Institutelist = null;
        try {
            Institutelist = s.createQuery("from Institute where instituteId=(select instituteId from User where emailId='" + user_id + "')").list();
            t.commit();
            return Institutelist;
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

    public List<Department> DepartmentList(Integer instituteId) {
        sf = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sf.openSession();
        Transaction t = s.beginTransaction();

        List<Department> Deptlist = null;
        try {
            Deptlist = s.createQuery("from Department where institute='" + instituteId + "'").list();
            t.commit();
            return Deptlist;
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

    public List<Programme> DeptProgrammeList(Integer departmentId, String user_id) {
        sf = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sf.openSession();
        Transaction t = s.beginTransaction();
        List<Programme> Programmelist = null;
        try {
            Programmelist = s.createQuery("from Programme where department='" + departmentId + "'").list();
            t.commit();
            return Programmelist;
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

    public List<Programme> RegUserDeptProgrammeList(Integer departmentId) {
        sf = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sf.openSession();
        Transaction t = s.beginTransaction();
        List<Programme> Programmelist = null;
        try {
            Programmelist = s.createQuery("from Programme where department='" + departmentId + "'").list();
            t.commit();
            return Programmelist;
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

    public List<Programme> ProgrammeList(Integer instituteId) {
        sf = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sf.openSession();
        Transaction t = s.beginTransaction();

        List<Programme> Programmelist = null;
        try {
            Programmelist = s.createQuery("from Programme where institute='" + instituteId + "'").list();
            t.commit();
            return Programmelist;
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

    public List<Programme> ProgrammeDetailsList(Integer programmeId) {
        sf = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sf.openSession();
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
            sf.close();
        }
    }

    public List<Course> CourseList(Integer programmeId) {
        sf = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sf.openSession();
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
            sf.close();
        }
    }
}
