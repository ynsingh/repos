/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.DAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.IGNOU.ePortfolio.Model.Conference;
import org.IGNOU.ePortfolio.Model.ConferenceAuthors;
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
 * @since 28-02-2012
 */
public class ConferenceDao {

    private SessionFactory sessionFactory;

    public Conference saveConf(Conference CModel) {
        sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sessionFactory.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();
            s.save(CModel);

            ConferenceAuthors CA = new ConferenceAuthors();
            for (int i = 0; i < CModel.getFname().size(); i++) {
                CA.setConference(CModel);
                CA.setFname(CModel.getFname().get(i));
                CA.setLname(CModel.getLname().get(i));
                s.save(CA);
                s.flush();
                s.clear();
            }
            t.commit();
            return CModel;
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

    public List<Conference> ShowConf(String user_id) {
        sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sessionFactory.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();
            List<Conference> ConfList = null;

            try {
                ConfList = s.createQuery("from Conference where userId='" + user_id + "'").list();
            } catch (HibernateException HE) {
                System.out.println(HE);
            }
            t.commit();
            return ConfList;
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
    public List<ConferenceAuthors> ShowConfAuthor(ArrayList<Long> conferenceAuthorsId) {
    sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
    Session s = sessionFactory.openSession();
    Transaction t = null;
    try {
    t = s.beginTransaction();
    List<ConferenceAuthors> ConfAuthoList = null;
    try {
    ConfAuthoList = s.createQuery("from ConferenceAuthors where conferenceAuthorsId in(" + conferenceAuthorsId + ")").list();
    } catch (HibernateException HE) {
    System.out.println(HE);
    }
    t.commit();
    return ConfAuthoList;
    } catch (Throwable ex) {
    //Log the Exception
    t.rollback();
    System.err.println("Initial SessionFactory creation failed." + ex);
    throw new ExceptionInInitializerError(ex);
    } finally {
    s.close();
    sessionFactory.close();
    }
    }*/

    public List<Conference> EditConf(long conferenceId) {
        sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sessionFactory.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();
            List<Conference> ConfList = null;
            try {
                ConfList = s.createQuery("from Conference where conferenceId='" + conferenceId + "'").list();
            } catch (HibernateException HE) {
                System.out.println(HE);
            }
            t.commit();
            return ConfList;
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

    public Conference DeleteConf(long conferenceId) {
        sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = getSessionFactory().openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();
            Conference DeleteInfo = (Conference) s.load(Conference.class, conferenceId);
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

    public Conference UpdateConf(long conferenceId, String userId, String confType, String researchArea, String assoProject, String projectName, String role, String presentationType, String paperTitle, Integer noCoauthor, Integer pfrom, Integer pto, String conferenceName, String dfrom, String dto, String orgName, String venue, String state, String country, String language, String url, String affiliation, String abstract_, String key1, String key2, String key3, String key4, String key5, String key6, Set<ConferenceAuthors> conferenceAuthorses, ArrayList<String> fname, ArrayList<String> lname) {
        setSessionFactory(new AnnotationConfiguration().configure().buildSessionFactory());
        Session s = getSessionFactory().openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();
            Conference UpdateInfo = (Conference) s.load(Conference.class, conferenceId);
            UpdateInfo.setAffiliation(affiliation);
            UpdateInfo.setLanguage(language);
            UpdateInfo.setNoCoauthor(noCoauthor);
            UpdateInfo.setUrl(url);
            UpdateInfo.setAbstract_(abstract_);
            UpdateInfo.setAssoProject(assoProject);
            UpdateInfo.setConfType(confType);
            UpdateInfo.setConferenceName(conferenceName);
            UpdateInfo.setCountry(country);
            UpdateInfo.setDfrom(dfrom);
            UpdateInfo.setDto(dto);
            UpdateInfo.setKey1(key1);
            UpdateInfo.setKey2(key2);
            UpdateInfo.setKey3(key3);
            UpdateInfo.setKey4(key4);
            UpdateInfo.setKey5(key5);
            UpdateInfo.setKey6(key6);
            UpdateInfo.setOrgName(orgName);
            UpdateInfo.setVenue(venue);
            UpdateInfo.setState(state);
            UpdateInfo.setRole(role);
            UpdateInfo.setResearchArea(researchArea);
            UpdateInfo.setPto(pto);
            UpdateInfo.setProjectName(projectName);
            UpdateInfo.setPresentationType(presentationType);
            UpdateInfo.setPfrom(pfrom);
            UpdateInfo.setPaperTitle(paperTitle);

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
