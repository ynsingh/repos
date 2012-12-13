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
import org.IGNOU.ePortfolio.Model.PersonalInfo;
import org.hibernate.Session;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;

/**
 * Hibernate Utility class with a convenient method to get Session Factory object.
 *
 * @author IGNOU Team
 * @version 1
 * modified by IGNOU Team on 16 dec 2011 
 */
public class RegistrationDao {

//    @SuppressWarnings("unchecked")
//    public StudentRegistration saveInfo(StudentRegistration stRegModel) {
//        SessionFactory sf = new AnnotationConfiguration().configure().buildSessionFactory();
//        Session session = sf.openSession();
//        session.beginTransaction();
//        
//        User user = new User();
//        user.setEmailId(stRegModel.getEmailId());
//        user.setPassword(stRegModel.getPassword());
//        user.setRole(stRegModel.getRole());
//        user.setFname(stRegModel.getFname());
//        user.setLname(stRegModel.getLname());
//        user.setProgrammeId(Integer.parseInt(stRegModel.getPrograme()));
//        user.setInstituteId(Integer.parseInt(stRegModel.getUnivName()));
//        user.setUnivRegNo(stRegModel.getUnivRegNo());
//        session.save(user);
//        
//        PersonalInfo pi = new PersonalInfo();
//        pi.setEmailId(stRegModel.getEmailId());
//        pi.setFirstName(stRegModel.getFname()+" "+stRegModel.getMname());
//        pi.setLastName(stRegModel.getLname());
//        session.save(pi);
//        
//        session.save(stRegModel);
//        session.getTransaction().commit();
//        session.close();
//        sf.close();
//        return stRegModel;
//    }
     @SuppressWarnings("unchecked")
    public User saveRegistration(User stRegModel) {
        SessionFactory sf = new AnnotationConfiguration().configure().buildSessionFactory();
        Session session = sf.openSession();
        session.beginTransaction();
        
        PersonalInfo pi = new PersonalInfo();
        pi.setEmailId(stRegModel.getEmailId());
        pi.setFirstName(stRegModel.getFname());
        pi.setLastName(stRegModel.getLname());
        session.save(stRegModel);
        session.save(pi);
        
        session.getTransaction().commit();
        session.close();
        sf.close();
        return stRegModel;
    }
}