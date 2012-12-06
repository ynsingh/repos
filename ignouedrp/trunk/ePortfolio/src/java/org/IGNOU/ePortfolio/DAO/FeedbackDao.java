/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.

 * 
 *  Copyright (c) 2011 eGyankosh, IGNOU, New Delhi.
 *  All Rights Reserved.
 *
 *  Redistribution and use in source and binary forms, with or
 *  without modification, are permitted provided that the following
 *  conditions are met:
 *
 *  Redistributions of source code must retain the above copyright
 *  notice, this  list of conditions and the following disclaimer.
 *
 *  Redistribution in binary form must reproducuce the above copyright
 *  notice, this list of conditions and the following disclaimer in
 *  the documentation and/or other materials provided with the
 *  distribution.
 *
 *
 *  THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 *  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 *  OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 *  DISCLAIMED.  IN NO EVENT SHALL eGyankosh, IGNOU OR ITS CONTRIBUTORS BE LIABLE
 *  FOR ANY DIRECT, INDIRECT, INCIDENTAL,SPECIAL, EXEMPLARY, OR
 *  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT
 *  OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR
 *  BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 *  WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 *  OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 *  EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 *
 *  Contributors: Members of eGyankosh, IGNOU, New Delhi.
 *
 */
package org.IGNOU.ePortfolio.DAO;

import java.util.List;
import org.IGNOU.ePortfolio.Model.Feedback;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 * Hibernate Utility class with a convenient method to get Session Factory object.
 * @version 1
 * @author IGNOU Team
 */
public class FeedbackDao {

    private SessionFactory sessionFactory;

    /**
     * 
     * @param FeedbackModel 
     * @return SUCCESS
     * This function is used to Insert the feed back information into database
     * 
     */
    public Feedback addInfo(Feedback FeedbackModel) {
        sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sessionFactory.openSession();
        Transaction t = null;
        try {
            // Create the SessionFactory from standard (hibernate.cfg.xml) 
            // config file.

            t = s.beginTransaction();
            s.save(FeedbackModel);
            t.commit();
            return FeedbackModel;

        } catch (Throwable ex) {
            // Log the exception.
            t.rollback();
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        } finally {
            s.close();
            sessionFactory.close();
        }
    }
    @SuppressWarnings({"unchecked"})
    public List<Feedback> FeedbackList() {
        SessionFactory sf = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sf.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();

            List<Feedback> FeedbackListList = null;
            try {
                FeedbackListList = s.createQuery("from Feedback where archive='0'").list();
            } catch (HibernateException HE) {
                System.out.println(HE);
            }
            t.commit();
            return FeedbackListList;
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
   
   @SuppressWarnings({"unchecked"})
    public List<Feedback> ArchiveFeedbackList() {
        SessionFactory sf = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sf.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();

            List<Feedback> FeedbackListList = null;
            try {
                FeedbackListList = s.createQuery("from Feedback where archive='1'").list();
            } catch (HibernateException HE) {
                System.out.println(HE);
            }
            t.commit();
            return FeedbackListList;
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
    public Feedback DeleteFeedback(long feedbackId) {
        SessionFactory sf = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sf.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();
            Feedback DeleteFeedback = (Feedback) s.load(Feedback.class, feedbackId);
            if (DeleteFeedback != null) {
                s.delete(DeleteFeedback);
            }
            t.commit();
            return DeleteFeedback;
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
    public List<Feedback> ReplyFeedback(long feedbackId) {
        SessionFactory sf = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sf.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();

            List<Feedback> replyfeedbacklist = null;
            try {
                replyfeedbacklist = s.createQuery("from Feedback where feedbackId='" + feedbackId + "'").list();
            } catch (HibernateException HE) {
                System.out.println(HE);
            }
            t.commit();
            return replyfeedbacklist;
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
    public Feedback ArchiveFeedback(long feedbackId ) {
        SessionFactory sf = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sf.openSession();
        Transaction t = null;
        Feedback fb=new Feedback();
        
        try {
            t = s.beginTransaction();
             Feedback fbList = (Feedback) s.load(Feedback.class, feedbackId);
             fbList.setArchive(true);
             s.update(fbList);
             t.commit();
             return fb;
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
    /*
     * This function used to get Session factory object
     */

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}