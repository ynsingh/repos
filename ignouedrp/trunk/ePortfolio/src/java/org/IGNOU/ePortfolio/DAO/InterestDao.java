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
import org.IGNOU.ePortfolio.Model.ProfileInterest;
import org.IGNOU.ePortfolio.Model.ProfileInterestList;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

 /**
     * Added on 1-Dec-2011
     * @param ProfileInterestModel 
     * @return Profile Interest Model
     * @author IGNOU Team
     * This is the function to Insert Profile Interest Information into database.
     **/
public class InterestDao {

    @SuppressWarnings("unchecked")
    public ProfileInterest saveInterestInfo(ProfileInterest profileInterest) {
        SessionFactory sf = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sf.openSession();
        Transaction t = s.beginTransaction();
        s.save(profileInterest);
        t.commit();
        s.close();
        sf.close();
        return profileInterest;
    }
    
    @SuppressWarnings({"unchecked"})
    public List<ProfileInterestList> InterestList(String user_id) {
        SessionFactory sf = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sf.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();

            List<ProfileInterestList> InterestListList = null;
            try {
                InterestListList = s.createQuery("from ProfileInterestList where user_id='" + user_id + "'").list();
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
            sf.close();
        }
    }
    
    @SuppressWarnings("unchecked")
    public List<ProfileInterestList> EditInterest(long interestId) {
        SessionFactory sf = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sf.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();

            List<ProfileInterestList> editInterestList = null;
            try {
                editInterestList = s.createQuery("from ProfileInterestList where interestId='" + interestId + "'").list();
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
            sf.close();
        }
    }
    
    
    @SuppressWarnings("unchecked")
    public ProfileInterestList DeleteInterestInfo(long interestId) {
        SessionFactory sf = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sf.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();
            ProfileInterestList DeleteInterest = (ProfileInterestList) s.load(ProfileInterestList.class, interestId);
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
            sf.close();
        }
    }
    
    @SuppressWarnings("unchecked")
    public ProfileInterestList UpdateInterest(long interestId, String userId, String acadInterest, String persInterest, String techInterest, String reserInterst, String myHobbies) {
        SessionFactory sf = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sf.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();

            ProfileInterestList UpdateIntInfo = (ProfileInterestList) s.load(ProfileInterestList.class, interestId);
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
            sf.close();
        }
    }
}
