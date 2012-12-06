/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.DAO;

import java.util.Date;
import java.util.List;
import org.IGNOU.ePortfolio.Model.Testimonials;
import org.IGNOU.ePortfolio.Model.User;
import org.IGNOU.ePortfolio.Model.UserList;
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
public class TestimonialDao {

    private SessionFactory sessionFactory;

    public List<User> ShowTestiUsrList(int instituteId, int programmeId, String role) {
        sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sessionFactory.openSession();
        Transaction t = s.beginTransaction();

        List<User> TestiUlist = null;
        try {
            TestiUlist = s.createQuery("from User where instituteId='" + instituteId + "' and programmeId='" + programmeId + "' and role !='" + role + "'").list();
        } catch (HibernateException HE) {
            System.out.println(HE);
        }
        t.commit();
        s.close();
        sessionFactory.close();
        return TestiUlist;
    }
  

    public Testimonials RequestTestimonial(Testimonials TestiModel, String userByTestiReqTo) throws Exception {
        sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sessionFactory.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();
            UserList ul2 = new UserList();
            ul2.setEmailId(userByTestiReqTo);
            TestiModel.setUserByTestiReqTo(ul2);
            s.save(TestiModel);
            t.commit();
            return TestiModel;
        } catch (Exception e) {
            System.err.println("Exception Occure" + e);
            throw new Exception(e.toString());
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

    public List<Testimonials> SentTestimonialRequest(String userByTestiRequestor) {
        sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sessionFactory.openSession();
        Transaction t = s.beginTransaction();

        List<Testimonials> ReqList = null;
        try {
            ReqList = s.createQuery("from Testimonials where userByTestiRequestor='" + userByTestiRequestor + "' and draft=0").list();
        } catch (HibernateException HE) {
            System.out.println(HE);
        }
        t.commit();
        s.close();
        sessionFactory.close();
        return ReqList;
    }

    public List<Testimonials> DraftTestimonialRequest(String userByTestiRequestor) {
        sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sessionFactory.openSession();
        Transaction t = s.beginTransaction();

        List<Testimonials> ReqList = null;
        try {
            ReqList = s.createQuery("from Testimonials where userByTestiRequestor='" + userByTestiRequestor + "' and draft=1").list();
        } catch (HibernateException HE) {
            System.out.println(HE);
        }
        t.commit();
        s.close();
        sessionFactory.close();
        return ReqList;
    }

    public List<Testimonials> TestimonialRequestToFaculty(String userByTestiReqTo) {
        sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sessionFactory.openSession();
        Transaction t = s.beginTransaction();

        List<Testimonials> ReqList = null;
        try {
            ReqList = s.createQuery("from Testimonials where userByTestiReqTo='" + userByTestiReqTo + "' and draft=0 order  by testiReqDate DESC").list();
        } catch (HibernateException HE) {
            System.out.println(HE);
        }
        t.commit();
        s.close();
        sessionFactory.close();
        return ReqList;
    }

    public List<Testimonials> UpdateReadStatus(Integer testiReqId) {
        sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sessionFactory.openSession();
        Transaction t = null;
        List<Testimonials> ReqList = null;
        try {
            t = s.beginTransaction();
            Testimonials UpdateInfo = (Testimonials) s.load(Testimonials.class, testiReqId);
            UpdateInfo.setReadStatus(Boolean.TRUE);
            if (null != UpdateInfo) {
                s.update(UpdateInfo);
            }
            t.commit();
            ReqList = s.createQuery("from Testimonials where testiReqId='" + testiReqId + "'").list();
        } catch (HibernateException HE) {
            System.out.println(HE);
        }
        s.close();
        sessionFactory.close();
        return ReqList;
    }

    public List<Testimonials> CreateTestimonialfor(Integer testiReqId) {
        sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sessionFactory.openSession();
        Transaction t = s.beginTransaction();
        List<Testimonials> ReqList = null;
        try {
            ReqList = s.createQuery("from Testimonials where testiReqId='" + testiReqId + "'").list();
        } catch (HibernateException HE) {
            System.out.println(HE);
        }
        t.commit();
        s.close();
        sessionFactory.close();
        return ReqList;
    }

    public Testimonials SaveTestimonialReport(Integer testiReqId, Date createDate, Date sentDate, Boolean sent, String report) {
        sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sessionFactory.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();
            Testimonials UpdateInfo = (Testimonials) s.load(Testimonials.class, testiReqId);
            UpdateInfo.setCreateDate(createDate);
            UpdateInfo.setSent(sent);
            UpdateInfo.setSentDate(sentDate);
            UpdateInfo.setReport(report);
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

    public List<Testimonials> STestimonialSentToConcerned(String userByTestiRequestor) {
        sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sessionFactory.openSession();
        Transaction t = s.beginTransaction();
        List<Testimonials> ReqList = null;
        try {
            ReqList = s.createQuery("from Testimonials where userByTestiRequestor='" + userByTestiRequestor + "' and sent=1 order  by testiReqDate DESC").list();
        } catch (HibernateException HE) {
            System.out.println(HE);
        }
        t.commit();
        s.close();
        sessionFactory.close();
        return ReqList;
    }

    public List<Testimonials> FTestimonialSentToConcerned(String userByTestiReqTo) {
        sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sessionFactory.openSession();
        Transaction t = s.beginTransaction();
        List<Testimonials> ReqList = null;
        try {
            ReqList = s.createQuery("from Testimonials where userByTestiReqTo='" + userByTestiReqTo + "' and sent=1 order  by testiReqDate DESC").list();
        } catch (HibernateException HE) {
            System.out.println(HE);
        }
        t.commit();
        s.close();
        sessionFactory.close();
        return ReqList;
    }

    public List<Testimonials> FTestimonialSaved(String userByTestiReqTo) {
        sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sessionFactory.openSession();
        Transaction t = s.beginTransaction();
        List<Testimonials> ReqList = null;
        try {
            ReqList = s.createQuery("from Testimonials where userByTestiReqTo='" + userByTestiReqTo + "' and readStatus=1 and sent=0 order  by createDate DESC").list();
        } catch (HibernateException HE) {
            System.out.println(HE);
        }
        t.commit();
        s.close();
        sessionFactory.close();
        return ReqList;
    }

    public List<Testimonials> FTestimonialEdit(Integer testiReqId) {
        sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sessionFactory.openSession();
        Transaction t = s.beginTransaction();

        List<Testimonials> ReqList = null;
        try {
            ReqList = s.createQuery("from Testimonials where testiReqId='" + testiReqId + "'").list();
        } catch (HibernateException HE) {
            System.out.println(HE);
        }
        t.commit();
        s.close();
        sessionFactory.close();
        return ReqList;
    }

    public Testimonials UpdateTestimonialbyFaculty(Integer testiReqId, Boolean sent, String testiForEmail, String testiReqCc, String testiReqBcc, String report) {
        sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sessionFactory.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();
            Testimonials UpdateInfo = (Testimonials) s.load(Testimonials.class, testiReqId);
            UpdateInfo.setTestiReqCc(testiReqCc);
            UpdateInfo.setTestiReqBcc(testiReqBcc);
            UpdateInfo.setSent(sent);
            UpdateInfo.setSentDate(new Date());
            UpdateInfo.setReport(report);
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

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
