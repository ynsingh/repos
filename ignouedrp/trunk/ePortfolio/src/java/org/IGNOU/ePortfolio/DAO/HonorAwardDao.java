package org.IGNOU.ePortfolio.DAO;
/*
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

import java.util.List;
import org.IGNOU.ePortfolio.Model.ProfileHonorAward;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @version 1
 * @author IGNOU Team
 * @since 13-Oct-2011
 */
public class HonorAwardDao {

    private SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
    private Session s;

    public ProfileHonorAward ProfileHonorAwardSave(ProfileHonorAward HonorModel) throws Exception {
        s = sessionFactory.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();
            s.save(HonorModel);
            t.commit();
            return HonorModel;
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

    @SuppressWarnings("unchecked")
    public List<ProfileHonorAward> ProfileHonorAwardListByUserId(String userId) throws Exception {
        s = sessionFactory.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();
            List<ProfileHonorAward> HonorList = null;
            try {
                HonorList = s.createQuery("from ProfileHonorAward where user_id='" + userId + "'").list();
            } catch (HibernateException HE) {
                System.err.println("Hibernate Exception Occure" + HE);
                throw new HibernateException(HE.toString());
            }
            t.commit();
            return HonorList;

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

    @SuppressWarnings("unchecked")
    public List<ProfileHonorAward> ProfileHonorAwardByHonorAwardId(long honorAwardId) throws Exception {
        s = sessionFactory.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();
            List<ProfileHonorAward> HonorList = null;
            try {
                HonorList = s.createQuery("from ProfileHonorAward where honorAwardId='" + honorAwardId + "'").list();
            } catch (HibernateException HE) {
                System.err.println("Hibernate Exception Occure" + HE);
                throw new HibernateException(HE.toString());
            }
            t.commit();
            return HonorList;

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

    public ProfileHonorAward ProfileHonorAwardUpdate(long honorAwardId, String userId, String haTitle, String issuer, String haDate, String haDescription) {
        s = sessionFactory.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();
            ProfileHonorAward UpdateInfo = (ProfileHonorAward) s.load(ProfileHonorAward.class, honorAwardId);
            UpdateInfo.setHaDate(haDate);
            UpdateInfo.setHaDescription(haDescription);
            UpdateInfo.setHaTitle(haTitle);
            UpdateInfo.setIssuer(issuer);
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

    public ProfileHonorAward ProfileHonorAwardDelete(long honorAwardId) {
        s = sessionFactory.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();
            ProfileHonorAward DeleteInfo = (ProfileHonorAward) s.load(ProfileHonorAward.class, honorAwardId);
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
