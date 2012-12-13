/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.DAO;

import java.util.List;
import org.IGNOU.ePortfolio.Model.TalkLecture;
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
 */
public class TalkLectureDao {

    private SessionFactory sessionFactory;

    public TalkLecture saveTLInfo(TalkLecture TLModel) {
        sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = getSessionFactory().openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();
            s.save(TLModel);
            t.commit();
            return TLModel;
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

    public List<TalkLecture> ShowTL(String user_id) {
        sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = getSessionFactory().openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();
            List<TalkLecture> TLListList = null;
            try {
                TLListList = s.createQuery("from TalkLecture where userId='" + user_id + "'").list();
            } catch (HibernateException HE) {
                System.out.println(HE);
            }
            t.commit();
            return TLListList;
        } catch (Throwable ex) { //Log theException
            t.rollback();
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        } finally {
            s.close();
            getSessionFactory().close();
        }
    }

    public List<TalkLecture> EditTL(long talkLectureId) {
        sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = getSessionFactory().openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();
            List<TalkLecture> TLListList = null;
            try {
                TLListList = s.createQuery("from TalkLecture where talkLectureId='" + talkLectureId + "'").list();
            } catch (HibernateException HE) {
                System.out.println(HE);
            }
            t.commit();
            return TLListList;
        } catch (Throwable ex) { //Log theException
            t.rollback();
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        } finally {
            s.close();
            getSessionFactory().close();
        }
    }

    public TalkLecture UpdateTL(long talkLectureId, String userId, String eventType, String nameUniversity, String address, String nameEvent, String lectureTopic, String deleveredOn, String timeFrom, String timeTo, String level, String url, String description) {
        sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = getSessionFactory().openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();
            TalkLecture UpdateInfo = (TalkLecture) s.load(TalkLecture.class, talkLectureId);
            UpdateInfo.setAddress(address);
            UpdateInfo.setDeleveredOn(deleveredOn);
            UpdateInfo.setDescription(description);
            UpdateInfo.setEventType(eventType);
            UpdateInfo.setLectureTopic(lectureTopic);
            UpdateInfo.setLevel(level);
            UpdateInfo.setNameEvent(nameEvent);
            UpdateInfo.setNameUniversity(nameUniversity);
            UpdateInfo.setTimeFrom(timeFrom);
            UpdateInfo.setTimeTo(timeTo);
            UpdateInfo.setUrl(url);
            if (null != UpdateInfo) {
                s.update(UpdateInfo);
            }
            t.commit();
            return UpdateInfo;
        } catch (Throwable ex) { //Log the Exception t.rollback();
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        } finally {
            s.close();
            getSessionFactory().close();
        }
    }

    public TalkLecture DeleteTL(long talkLectureId) {
        sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = getSessionFactory().openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();
            TalkLecture DeleteInfo = (TalkLecture) s.load(TalkLecture.class, talkLectureId);
            if (DeleteInfo != null) {
                s.delete(DeleteInfo);
            }
            t.commit();
            return DeleteInfo;
        } catch (Throwable ex) { //Log the Exception 
            t.rollback();
            System.err.println("Initial   SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        } finally {
            s.close();
            getSessionFactory().close();
        }
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}