/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.DAO;

import java.util.List;
import org.IGNOU.ePortfolio.Model.StudentExchange;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 * Hibernate Utility class with a convenient method to get Session Factory object.
 *
 * @author IGNOU Team
 * @version 1
 * @since 27 December 2011
 */
public class StudentExchangeDao {
    
    private SessionFactory sessionFactory;
    
    public StudentExchange saveExchangeInfo(StudentExchange ExchangeModel) {
        setSessionFactory(new AnnotationConfiguration().configure().buildSessionFactory());
        Session s = getSessionFactory().openSession();
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
            getSessionFactory().close();
        }
    }
    
    public List<StudentExchange> ShowExchangeInfo(String user_id) {
        setSessionFactory(new AnnotationConfiguration().configure().buildSessionFactory());
        Session s = getSessionFactory().openSession();
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
            getSessionFactory().close();
        }
    }
    
    public StudentExchange DeleteExchangeInfo(long studentExchangeId) {
        setSessionFactory(new AnnotationConfiguration().configure().buildSessionFactory());
        Session s = getSessionFactory().openSession();
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
            getSessionFactory().close();
        }
    }
    
    public List<StudentExchange> EditExchangeInfo(long studentExchangeId) {
        setSessionFactory(new AnnotationConfiguration().configure().buildSessionFactory());
        Session s = getSessionFactory().openSession();
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
            getSessionFactory().close();
        }
    }

    public StudentExchange UpdateExchangeInfo(long studentExchangeId, String programmeType, String nameUniversity, String role, String programmeTheme, String venue, String state, String country, String durationFrom, String durationTo, String degreeLevel, String degraeeName, String researchColl, String url, String description, String userId, String ifOther) {
        setSessionFactory(new AnnotationConfiguration().configure().buildSessionFactory());
        Session s = getSessionFactory().openSession();
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
            getSessionFactory().close();
        }
    }
    /*
    
    public Governance DeleteGovernance(long governanceId) {
    setSessionFactory(new AnnotationConfiguration().configure().buildSessionFactory());
    Session s = getSessionFactory().openSession();
    Transaction t = null;
    try {
    t = s.beginTransaction();
    Governance DeleteInfo = (Governance) s.load(Governance.class, governanceId);
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
    getSessionFactory().close();
    }
    }
    
    public List<Governance> EditGovernanceInfo(long governanceId) {
    setSessionFactory(new AnnotationConfiguration().configure().buildSessionFactory());
    Session s = getSessionFactory().openSession();
    Transaction t = null;
    try {
    t = s.beginTransaction();
    List<Governance> GovernanceList = null;
    try {
    GovernanceList = s.createQuery("from Governance where governanceId='" + governanceId + "'").list();
    } catch (HibernateException HE) {
    System.out.println(HE);
    }
    t.commit();
    return GovernanceList;
    } catch (Throwable ex) {
    //Log the Exception
    t.rollback();
    System.err.println("Initial SessionFactory creation failed." + ex);
    throw new ExceptionInInitializerError(ex);
    } finally {
    s.close();
    getSessionFactory().close();
    }
    }
    
    public Governance UpdateGovernance(long governanceId, String userId, String nameCommittee, String nameUniversity, String durationFrom, String durationTo, String responsibility, String url, String summary) {
    setSessionFactory(new AnnotationConfiguration().configure().buildSessionFactory());
    Session s = getSessionFactory().openSession();
    Transaction t = null;
    try {
    t = s.beginTransaction();
    Governance UpdateInfo = (Governance) s.load(Governance.class, governanceId);
    UpdateInfo.setGovernanceId(governanceId);
    UpdateInfo.setDurationFrom(durationFrom);
    UpdateInfo.setDurationTo(durationTo);
    UpdateInfo.setNameCommittee(nameCommittee);
    UpdateInfo.setNameUniversity(nameUniversity);
    UpdateInfo.setResponsibility(responsibility);
    UpdateInfo.setSummary(summary);
    UpdateInfo.setUrl(url);
    UpdateInfo.setUserId(userId);
    
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
    getSessionFactory().close();
    }
    }
    
     */

    /**
     * @return the sessionFactory
     */
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /**
     * @param sessionFactory the sessionFactory to set
     */
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
