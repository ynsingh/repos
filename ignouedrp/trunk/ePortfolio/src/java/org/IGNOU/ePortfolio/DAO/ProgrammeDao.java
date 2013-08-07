/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.DAO;

import java.util.Date;
import java.util.List;
import org.IGNOU.ePortfolio.Model.Department;
import org.IGNOU.ePortfolio.Model.Institute;
import org.IGNOU.ePortfolio.Model.Programme;
import org.IGNOU.ePortfolio.Model.UserList;
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
public class ProgrammeDao {

    private SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
    private Session s;

    public List<Programme> ProgrammeListByDepartmentIdUserId(Integer departmentId, String user_id) {
        s = sessionFactory.openSession();
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
            sessionFactory.close();
        }
    }

    public List<Programme> ProgrammeListByDepartmentId(Integer departmentId) {
        s = sessionFactory.openSession();
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
            sessionFactory.close();
        }
    }

    public List<Programme> ProgrammeListByInstituteId(Integer instituteId) {
        s = sessionFactory.openSession();
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
            sessionFactory.close();
        }
    }

    public List<Programme> ProgrammeListByProgrammeId(int programmeId) {
        s = sessionFactory.openSession();
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

    public Programme ProgrammeSave(int departmentId, int instituteId, String programmeName, String programmeCode, Integer duration, String overview, String ProgrammeCrator, Date programmeCreatorDate) {
        s = sessionFactory.openSession();
        Transaction t = null;
        Institute inst = new Institute();
        Department dept = new Department();
        UserList u = new UserList();
        try {
            t = s.beginTransaction();
            inst.setInstituteId(instituteId);
            dept.setDepartmentId(departmentId);
            u.setEmailId(ProgrammeCrator);
            Programme ProModel = new Programme();
            ProModel.setDepartment(dept);
            ProModel.setDuration(duration);
            ProModel.setInstitute(inst);
            ProModel.setOverview(overview);
            ProModel.setProgrammeCode(programmeCode);
            ProModel.setProgrammeName(programmeName);
            ProModel.setProgrammeCreator(u);
            ProModel.setProgrammeCreateDate(programmeCreatorDate);
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
}
