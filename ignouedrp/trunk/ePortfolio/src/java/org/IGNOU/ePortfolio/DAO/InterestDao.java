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
 *  Contributors: Members of eGyankosh, IGNOU, New Delhi.
 */

import java.util.List;
import org.IGNOU.ePortfolio.Model.ProfileInterest;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 * Added on 1-Dec-2011
 *
 * @param ProfileInterestModel
 * @return Profile Interest Model
 * @author IGNOU Team This is the function to Insert Profile Interest
 * Information into database.
 *
 */
public class InterestDao {

    private SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
    private Session s;

    @SuppressWarnings("unchecked")
    public ProfileInterest ProfileInterestSave(ProfileInterest profileInterest) {
        s = sessionFactory.openSession();
        Transaction t = s.beginTransaction();
        s.save(profileInterest);
        t.commit();
        s.close();
        sessionFactory.close();
        return profileInterest;
    }

    @SuppressWarnings({"unchecked"})
    public List<ProfileInterest> ProfileInterestByUserId(String user_id) {
        s = sessionFactory.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();

            List<ProfileInterest> InterestListList = null;
            try {
                InterestListList = s.createQuery("from ProfileInterest where user_id='" + user_id + "'").list();
            } catch (HibernateException HE) {
                System.out.println(HE);
            }
            t.commit();
            return InterestListList;
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
    public List<ProfileInterest> ProfileInterestByIntrestId(long interestId) {
        s = sessionFactory.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();

            List<ProfileInterest> editInterestList = null;
            try {
                editInterestList = s.createQuery("from ProfileInterest where interestId='" + interestId + "'").list();
            } catch (HibernateException HE) {
                System.out.println(HE);
            }
            t.commit();
            return editInterestList;
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
    public ProfileInterest ProfileInterestDelete(long interestId) {
        s = sessionFactory.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();
            ProfileInterest DeleteInterest = (ProfileInterest) s.load(ProfileInterest.class, interestId);
            if (DeleteInterest != null) {
                s.delete(DeleteInterest);
            }
            t.commit();
            return DeleteInterest;
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
    public ProfileInterest ProfileInterestUpdate(long interestId, String userId, String acadInterest, String persInterest, String techInterest, String reserInterst, String myHobbies) {
        s = sessionFactory.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();

            ProfileInterest UpdateIntInfo = (ProfileInterest) s.load(ProfileInterest.class, interestId);
            UpdateIntInfo.setAcadInterest(acadInterest);
            UpdateIntInfo.setPersInterest(persInterest);
            UpdateIntInfo.setTechInterest(techInterest);
            UpdateIntInfo.setReserInterst(reserInterst);
            UpdateIntInfo.setMyHobbies(myHobbies);
            if (null != UpdateIntInfo) {
                s.update(UpdateIntInfo);
            }
            t.commit();
            return UpdateIntInfo;
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
