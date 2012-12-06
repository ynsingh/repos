/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.DAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.IGNOU.ePortfolio.Model.SeminarsWorkshops;
import org.IGNOU.ePortfolio.Model.SeminarsWorkshopsAuthor;
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
 * @since 24-02-2012
 */
public class SeminarsWorkshopsDao {

    private SessionFactory sessionFactory;

    public SeminarsWorkshops saveSW(SeminarsWorkshops SWModel) {
        sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sessionFactory.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();
            s.save(SWModel);
            SeminarsWorkshopsAuthor SWA = new SeminarsWorkshopsAuthor();
            for (int i = 0; i < SWModel.getFname().size(); i++) {
                SWA.setSeminarsWorkshops(SWModel);
                SWA.setFname(SWModel.getFname().get(i));
                SWA.setLname(SWModel.getLname().get(i));
                s.save(SWA);
                s.flush();
                s.clear();
            }
            t.commit();
            return SWModel;
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

    public List<SeminarsWorkshops> ShowSW(String user_id) {
        sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sessionFactory.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();
            List<SeminarsWorkshops> SWList = null;

            try {
                SWList = s.createQuery("from SeminarsWorkshops where userId='" + user_id + "'").list();
            } catch (HibernateException HE) {
                System.out.println(HE);
            }
            t.commit();
            return SWList;
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

    public List<SeminarsWorkshops> EditSW(long seminarsWorkshopsId) {
        sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sessionFactory.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();
            List<SeminarsWorkshops> BCList = null;
            try {
                BCList = s.createQuery("from SeminarsWorkshops where seminarsWorkshopsId='" + seminarsWorkshopsId + "'").list();
            } catch (HibernateException HE) {
                System.out.println(HE);
            }
            t.commit();
            return BCList;
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

    public SeminarsWorkshops DeleteSW(long seminarsWorkshopsId) {
        sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = getSessionFactory().openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();
            SeminarsWorkshops DeleteInfo = (SeminarsWorkshops) s.load(SeminarsWorkshops.class, seminarsWorkshopsId);
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

    public SeminarsWorkshops UpdateSW(Long seminarsWorkshopsId, String userId, String swType, String swName, String DFrom, String DTo, String venue, String state, String country, String swRole, String perType, String paperTitle, Integer noCoauthors, String areaThemeTopic, String sourceFunding, Long amountFunded, String language, String url, String abstract_, Set<SeminarsWorkshopsAuthor> seminarsWorkshopsAuthors, ArrayList<String> fname, ArrayList<String> lname) {
        setSessionFactory(new AnnotationConfiguration().configure().buildSessionFactory());
        Session s = getSessionFactory().openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();
            SeminarsWorkshops UpdateInfo = (SeminarsWorkshops) s.load(SeminarsWorkshops.class, seminarsWorkshopsId);
            UpdateInfo.setAbstract_(abstract_);
            UpdateInfo.setAmountFunded(amountFunded);
            UpdateInfo.setAreaThemeTopic(areaThemeTopic);
            UpdateInfo.setCountry(country);
            UpdateInfo.setDFrom(DFrom);
            UpdateInfo.setDTo(DTo);
            UpdateInfo.setLanguage(language);
            UpdateInfo.setNoCoauthors(noCoauthors);
            UpdateInfo.setPaperTitle(paperTitle);
            UpdateInfo.setPerType(perType);
            UpdateInfo.setSourceFunding(sourceFunding);
            UpdateInfo.setState(state);
            UpdateInfo.setSwName(swName);
            UpdateInfo.setSwRole(swRole);
            UpdateInfo.setSwType(swType);
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

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
