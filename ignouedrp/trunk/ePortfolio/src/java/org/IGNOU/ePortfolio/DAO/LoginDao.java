/**
 *
 * Copyright (c) 2011 eGyankosh, IGNOU, New Delhi. All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.
 *
 * Redistribution in binary form must reproducuce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 *
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED WARRANTIES,
 * INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 * FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL eGyankosh,
 * IGNOU OR ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL,SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA,
 * OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 *
 * Contributors: Members of eGyankosh, IGNOU, New Delhi.
 *
 */
package org.IGNOU.ePortfolio.DAO;

import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.IGNOU.ePortfolio.Model.ActivitiesAnnounce;
import org.IGNOU.ePortfolio.Model.Logs;
import org.IGNOU.ePortfolio.Model.PersonalInfo;
import org.IGNOU.ePortfolio.Model.User;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
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
 * @since
 */
public class LoginDao implements Serializable {

    private static final long serialVersionUID = 1L;
    final Logger logger = Logger.getLogger(this.getClass());
    private SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
    private Session s;

    public boolean FindUser(String email_id, String password, String role) {
        // sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        s = sessionFactory.openSession();
        Query qr = s.createQuery("from User  where email_id='" + email_id + "' and password='" + password + "' and role='" + role + "'");
        @SuppressWarnings("unchecked")
        Iterator<User> it = qr.iterate();
        while (it.hasNext()) {
            s.close();
            sessionFactory.close();
            //t.commit();
            return true;
        }
        s.close();
        sessionFactory.close();
        //t.commit();
        return false;
    }

    /**
     * Remote Authentication and Storing Value
     *
     * @author IGNOU Team
     * @version 1
     * @since 21-06-2012
     */
    @SuppressWarnings("unchecked")
    public User UserSave(String emailId, String role, String fname, String lname, int InstituteId, int ProgrammeId) {
        // sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        s = sessionFactory.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();
            User u = new User();
            /*Setting Value in User*/
            u.setEmailId(emailId);
            u.setFname(fname);
            u.setLname(lname);
            u.setRole(role);
            u.setInstituteId(InstituteId);
            u.setProgrammeId(ProgrammeId);
            s.save(u);
            /*Setting Value in Personal Information*/
            PersonalInfo pi = new PersonalInfo();
            pi.setFirstName(fname);
            pi.setLastName(lname);
            pi.setEmailId(emailId);
            s.save(pi);
            t.commit();
            return u;
        } catch (Throwable ex) {
            //Log the Exception
            t.rollback();
            logger.error(ex);
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        } finally {
            s.close();
            sessionFactory.close();
        }
    }

//    public Logs LogLoginSave(String user_id, Date loginTime, String ip) {
//        sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
//        s = sessionFactory.openSession();
//        Transaction t = null;
//        try {
//            t = s.beginTransaction();
//            Logs l = new Logs();
//            l.setUserId(user_id);
//            l.setLoginTime(loginTime);
//            l.setIp(ip);
//            s.save(l);
//            t.commit();
//            return l;
//        } catch (Throwable ex) {
//            Log the Exception
//            t.rollback();
//            logger.error(ex);
//            System.err.println("Initial SessionFactory creation failed." + ex);
//            throw new ExceptionInInitializerError(ex);
//        } finally {
//           s.close();
//            sessionFactory.close();
//        }
//    }
    public Logs logOutUpdate(long logId) {
        // sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        s = sessionFactory.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();
            Logs UpdateInfo = (Logs) s.load(Logs.class, logId);
            UpdateInfo.setLogoutTime(new Date());
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

    @SuppressWarnings("unchecked")
    public List<Logs> logList(String user_id, String ip) {
        s = sessionFactory.openSession();
        Transaction t = null;
        List<Logs> logInfo = null;
        try {
            t = s.beginTransaction();
            Logs l = new Logs();
            l.setUserId(user_id);
            l.setLoginTime(new Date());
            l.setIp(ip);
            s.save(l);
            t.commit();
            s.flush();
            s = sessionFactory.openSession();
            t = s.beginTransaction();
            logInfo = s.createQuery("from Logs where userId='" + user_id + "' and ip='" + ip + "' and logId='" + l.getLogId() + "'").list();
        } catch (HibernateException HE) {
            System.out.println(HE);
        }
        t.commit();
        s.close();
        sessionFactory.close();
        return logInfo;
    }
}
