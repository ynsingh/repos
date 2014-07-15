/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.DAO;

import java.util.List;
import org.IGNOU.ePortfolio.Model.StudentExchange;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 * Hibernate Utility class with a convenient method to get Session Factory object.
 *
 * @author IGNOU Team
 * @version 1
 * @since 27 December 2011
 */
public class StudentExchangeDao {
    
    private SessionFactory sessionFactory=new AnnotationConfiguration().configure().buildSessionFactory();
    private Session s;
    public StudentExchange StudentExchangeSave(StudentExchange ExchangeModel) {
        s = sessionFactory.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();
            s.save(ExchangeModel);
            t.commit();
            return ExchangeModel;
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
    
    public List<StudentExchange> StudentExchangeListByUserId(String user_id) {
        s = sessionFactory.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();
            List<StudentExchange> ProgrammeList = null;
            try {
                ProgrammeList = s.createQuery("from StudentExchange where userId='" + user_id + "'").list();
            } catch (HibernateException HE) {
                System.out.println(HE);
            }
            t.commit();
            return ProgrammeList;
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
    
    public StudentExchange StudentExchangeDelete(long studentExchangeId) {
        s = sessionFactory.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();
            StudentExchange DeleteInfo = (StudentExchange) s.load(StudentExchange.class, studentExchangeId);
            if (DeleteInfo != null) {
                s.delete(DeleteInfo);
            }
            t.commit();
            return DeleteInfo;
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
    
    public List<StudentExchange> StudentExchangeEdit(long studentExchangeId) {
        s = sessionFactory.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();
            List<StudentExchange> ProgrammeList = null;
            try {
                ProgrammeList = s.createQuery("from StudentExchange where studentExchangeId='" + studentExchangeId + "'").list();
            } catch (HibernateException HE) {
                System.out.println(HE);
            }
            t.commit();
            return ProgrammeList;
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

    public StudentExchange StudentExchangeUpdate(long studentExchangeId, String programmeType, String nameUniversity, String role, String programmeTheme, String venue, String state, String country, String durationFrom, String durationTo, String degreeLevel, String degraeeName, String researchColl, String url, String description, String userId, String ifOther) {
         s = sessionFactory.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();
            StudentExchange UpdateInfo = (StudentExchange) s.load(StudentExchange.class, studentExchangeId);
            UpdateInfo.setCountry(country);
            UpdateInfo.setDegraeeName(degraeeName);
            UpdateInfo.setDegreeLevel(degreeLevel);
            UpdateInfo.setDescription(description);
            UpdateInfo.setDurationFrom(durationFrom);
            UpdateInfo.setDurationTo(durationTo);
            UpdateInfo.setIfOther(ifOther);
            UpdateInfo.setNameUniversity(nameUniversity);
            UpdateInfo.setProgrammeTheme(programmeTheme);
            UpdateInfo.setProgrammeType(programmeType);
            UpdateInfo.setResearchColl(researchColl);
            UpdateInfo.setRole(role);
            UpdateInfo.setState(state);
            UpdateInfo.setUrl(url);
            UpdateInfo.setVenue(venue);
            
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
