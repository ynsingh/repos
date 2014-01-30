/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.DAO;

import java.util.List;
import org.IGNOU.ePortfolio.Model.MediaPublication;
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
 * @since 16 February 2012
 */
public class MediaPublicationDao {

    private SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
    private Session s;

    public MediaPublication MediaPublicationSave(MediaPublication MPMOdel) {
        s = sessionFactory.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();
            s.save(MPMOdel);
            t.commit();
            return MPMOdel;
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

    public List<MediaPublication> MediaPublicationListByUserId(String user_id) {
        s = sessionFactory.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();
            List<MediaPublication> MPList = null;
            try {
                MPList = s.createQuery("from MediaPublication where userId='" + user_id + "'").list();
            } catch (HibernateException HE) {
                System.out.println(HE);
            }
            t.commit();
            return MPList;
        } catch (Throwable ex) { //Log theException
            t.rollback();
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        } finally {
            s.close();
            sessionFactory.close();
        }
    }

    public List<MediaPublication> MediaPublicationListByMPId(long mpId) {
        s = sessionFactory.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();
            List<MediaPublication> MPList = null;
            try {
                MPList = s.createQuery("from MediaPublication where mpId='" + mpId + "'").list();
            } catch (HibernateException HE) {
                System.out.println(HE);
            }
            t.commit();
            return MPList;
        } catch (Throwable ex) { //Log theException
            t.rollback();
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        } finally {
            s.close();
            sessionFactory.close();
        }
    }

    public MediaPublication MediaPublicationUpdate(long mpId, String userId, String typeOfMedia, String titleOfMedia, String titleOfArticle, String pubDate, String url, String summary, Integer api) {
        s = sessionFactory.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();
            MediaPublication UpdateInfo = (MediaPublication) s.load(MediaPublication.class, mpId);
            UpdateInfo.setPubDate(pubDate);
            UpdateInfo.setSummary(summary);
            UpdateInfo.setTitleOfArticle(titleOfArticle);
            UpdateInfo.setTitleOfMedia(titleOfMedia);
            UpdateInfo.setTypeOfMedia(typeOfMedia);
            UpdateInfo.setUrl(url);
            if (null != UpdateInfo) {
                s.update(UpdateInfo);
            }
            t.commit();
            return UpdateInfo;
        } catch (Throwable ex) { //Log the Exception
            t.rollback();
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        } finally {
            s.close();
            sessionFactory.close();
        }
    }

    public MediaPublication MediaPublicationDelete(long mpId) {
        s = sessionFactory.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();
            MediaPublication DeleteInfo = (MediaPublication) s.load(MediaPublication.class, mpId);
            if (DeleteInfo != null) {
                s.delete(DeleteInfo);
            }
            t.commit();
            return DeleteInfo;
        } catch (Throwable ex) { //Log the Exception
            t.rollback();
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        } finally {
            s.close();
            sessionFactory.close();
        }
    }
}
