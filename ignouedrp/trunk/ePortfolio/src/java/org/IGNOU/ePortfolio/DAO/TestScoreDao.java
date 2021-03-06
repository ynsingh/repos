

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
package org.IGNOU.ePortfolio.DAO;

import java.util.List;
import org.IGNOU.ePortfolio.Model.ProfileTest;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 * Hibernate Utility class with a convenient method to get Session Factory object.
 * @version 1
 * @since  12-Oct-2011
 * @author IGNOU Team
 */
public class TestScoreDao {

    private SessionFactory sf=new AnnotationConfiguration().configure().buildSessionFactory();
    private Session s;
   
    public ProfileTest ProfileTestSave(ProfileTest TestModel) throws Exception {
         s = sf.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();
            s.save(TestModel);
            t.commit();
            return TestModel;
        } catch (Exception e) {
            System.err.println("Exception Occure" + e);
            throw new Exception(e.toString());
        }catch (Throwable ex) {
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
    public List<ProfileTest> ProfileTestListByUserId(String userId) {
         s = sf.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();
            List<ProfileTest> TestScoreList = null;
            try {
                TestScoreList = s.createQuery("from ProfileTest where userId='" + userId + "'").list();
            } catch (HibernateException HE) {
                System.out.println(HE);
            }
            t.commit();
            return TestScoreList;
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
    public List<ProfileTest> ProfileTestEdit(long testId) {
       s = sf.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();
            List<ProfileTest> TestScoreList = null;
            try {
                TestScoreList = s.createQuery("from ProfileTest where testId='" + testId + "'").list();
            } catch (HibernateException HE) {
                System.out.println(HE);
            }
            t.commit();
            return TestScoreList;
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

    public ProfileTest ProfileTestUpdate(long testId, String userId, String tname, Integer score, String tdate, String tdescription) {
         s = sf.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();
            ProfileTest UpdateInfo = (ProfileTest) s.load(ProfileTest.class, testId);
            UpdateInfo.setScore(score);
            UpdateInfo.setTdate(tdate);
            UpdateInfo.setTdescription(tdescription);
            UpdateInfo.setTname(tname);
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
            sf.close();
        }
    }

    public ProfileTest ProfileTestDelete(long testId) {
         s = sf.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();
            ProfileTest DeleteInfo = (ProfileTest) s.load(ProfileTest.class, testId);
            if (DeleteInfo != null) {
                s.delete(DeleteInfo);
            }
            t.commit();
            return DeleteInfo;
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
