/**
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

import org.IGNOU.ePortfolio.Model.User;
import java.util.Iterator;
import org.IGNOU.ePortfolio.Model.PersonalInfo;
import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 * Hibernate Utility class with a convenient method to get Session Factory object.
 *
 * @author IGNOU Team
 * @version 1
 * @since 
 */
public class LoginDao {
    
    public boolean FindUser(String email_id, String password, String role) {
        SessionFactory sf = new AnnotationConfiguration().configure().buildSessionFactory();
        Session session = sf.openSession();
        //Transaction t = session.beginTransaction();
         Query qr = session.createQuery("from User  where email_id='" + email_id + "' and password='" + password + "' and role='" + role + "'");
        @SuppressWarnings("unchecked")
        Iterator<User> it = qr.iterate();
        while (it.hasNext()) {
            session.close();
            sf.close();
            //t.commit();
            return true;
        }
        session.close();
        sf.close();
        //t.commit();
        return false;
    }

    /**
     * Remote Authentication and Storing Value
     * @author IGNOU Team
     * @version 1
     * @since 21-06-2012
     */
    @SuppressWarnings("unchecked")
    public User saveUserInfo(String emailId, String role, String fname, String lname, int InstituteId, int ProgrammeId) {
        SessionFactory sf = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sf.openSession();
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
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        } finally {
            s.close();
            sf.close();
        }
    }
}
