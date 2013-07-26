/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.DAO;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.IGNOU.ePortfolio.Model.Events;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author Amit
 */
public class EventsDao {

    private SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
    private Session s;

    public Events EventsSave(Events EModel) {
        s = sessionFactory.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();
            s.save(EModel);
            t.commit();
            return EModel;
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
    public List<Events> EventsList() {
        s = sessionFactory.openSession();
        Transaction t = s.beginTransaction();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String Today = sdf.format(new Date());
        List<Events> EventInfolist = null;
        try {
            EventInfolist = s.createQuery("from Events where postponed='0' and eventDateTo >= '" + Today + "'").list();
        } catch (HibernateException HE) {
            System.out.println(HE);
        }
        t.commit();
        s.close();
        sessionFactory.close();
        return EventInfolist;
    }

    @SuppressWarnings("unchecked")
    public List<Events> EventsAnnounceList() {
        s = sessionFactory.openSession();
        Transaction t = s.beginTransaction();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String Today = sdf.format(new Date());
        List<Events> EventInfolist = null;
        try {
            EventInfolist = s.createQuery("from Events where postponed='0'  and eventDisplayDate<='" + Today + "' and eventDateTo>='" + Today + "'").list();
            // EventInfolist = s.createQuery("from Events where postponed='0'  and " + Today + " between  eventDisplayDate and eventDateTo").list();
        } catch (HibernateException HE) {
            System.out.println(HE);
        }
        t.commit();
        s.close();
        sessionFactory.close();
        return EventInfolist;
    }

    @SuppressWarnings("unchecked")
    public List<Events> EventsPostponedList() {
        s = sessionFactory.openSession();
        Transaction t = s.beginTransaction();
        List<Events> EventInfolist = null;
        try {
            EventInfolist = s.createQuery("from Events where postponed='1'").list();
        } catch (HibernateException HE) {
            System.out.println(HE);
        }
        t.commit();
        s.close();
        sessionFactory.close();
        return EventInfolist;
    }

    @SuppressWarnings("unchecked")
    public List<Events> EventsArchivedList() {
        s = sessionFactory.openSession();
        Transaction t = s.beginTransaction();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String Today = sdf.format(new Date());
        List<Events> EventInfolist = null;
        try {
            EventInfolist = s.createQuery("from Events where postponed='0' and eventDateTo < '" + Today + "'").list();
        } catch (HibernateException HE) {
            System.out.println(HE);
        }
        t.commit();
        s.close();
        sessionFactory.close();
        return EventInfolist;
    }

    @SuppressWarnings("unchecked")
    public List<Events> EventEditByEventId(long eventsId) {
        s = sessionFactory.openSession();
        Transaction t = s.beginTransaction();
        List<Events> EventInfolist = null;
        try {
            EventInfolist = s.createQuery("from Events where eventsId='" + eventsId + "'").list();
        } catch (HibernateException HE) {
            System.out.println(HE);
        }
        t.commit();
        s.close();
        sessionFactory.close();
        return EventInfolist;
    }

    public Events EventsUpdate(long eventsId, String eventTitle, Date eventDateFrom, Date eventDateTo, Date createDate, Date eventDisplayDate, String venue, String address, String city, String state, String country, Integer pincode, Long phone, String emailId, String website, String description, Boolean postponed, String postponedReason) {
        s = sessionFactory.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();
            Events UpdateInfo = (Events) s.load(Events.class, eventsId);
            UpdateInfo.setAddress(address);
            UpdateInfo.setCity(city);
            UpdateInfo.setCountry(country);
            //UpdateInfo.setCreateDate(createDate);
            UpdateInfo.setDescription(description);
            UpdateInfo.setEventDateFrom(eventDateFrom);
            UpdateInfo.setEventDateTo(eventDateTo);
            UpdateInfo.setEventDisplayDate(eventDisplayDate);
            UpdateInfo.setEventTitle(eventTitle);
            UpdateInfo.setPhone(phone);
            UpdateInfo.setPincode(pincode);
            UpdateInfo.setState(state);
            UpdateInfo.setVenue(venue);
            UpdateInfo.setWebsite(website);
            UpdateInfo.setPostponed(postponed);
            UpdateInfo.setPostponedReason(postponedReason);
            UpdateInfo.setEmailId(emailId);
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

    public Events EventsDelete(long eventsId) {
        s = sessionFactory.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();
            Events DeleteInfo = (Events) s.load(Events.class, eventsId);
            if (DeleteInfo != null) {
                s.delete(DeleteInfo);
            }
            t.commit();
            return DeleteInfo;
        } catch (HibernateException HE) {
            System.err.println("Hibernate Exception Occure" + HE);
            throw new HibernateException(HE.toString());
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
