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

import java.util.List;
import org.IGNOU.ePortfolio.Model.User;
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
 * @version 1 modified by IGNOU Team on 16 dec 2011
 */
public class RegistrationDao {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @SuppressWarnings("unchecked")
    public User UserSaveByRegModel(User stRegModel) {
        sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sessionFactory.openSession();
        try {
        } catch (Exception e) {
        }
        Transaction t = s.beginTransaction();
        s.save(stRegModel);
        s.getTransaction().commit();
        s.close();
        sessionFactory.close();
        return stRegModel;
    }

    public boolean UserUpdate(long registrationId, String emailId, String uuid) {
        sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sessionFactory.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();
            List<User> UserList = null;
            try {
                UserList = s.createQuery("from User where  uuid='" + uuid + "' and emailId='" + emailId + "'").list();
            } catch (HibernateException HE) {
                System.out.println(HE);
            }
            if (!UserList.isEmpty() && UserList != null && UserList.iterator().next().getEmailVerify().equals(false)) {
                User usrUpdate = (User) s.load(User.class, registrationId);
                usrUpdate.setEmailVerify(Boolean.TRUE);
                if (null != usrUpdate) {
                    s.update(usrUpdate);
                }
                t.commit();
                return true;
            } else {
                return false;
            }
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
