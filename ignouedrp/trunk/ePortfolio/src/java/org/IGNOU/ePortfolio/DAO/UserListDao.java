/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.DAO;

import java.util.Date;
import java.util.List;
import org.IGNOU.ePortfolio.Model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author IGNOU Team
 */
public class UserListDao {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public List<User> UserListByProgrammeIdUserId(int programmeId, String emailId) {
        sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sessionFactory.openSession();
        Transaction t = s.beginTransaction();

        List<User> TestiUlist = null;
        try {
            TestiUlist = s.createQuery("from User where  programmeId='" + programmeId + "' and emailId!='" + emailId + "' and role ='student' ").list();
        } catch (HibernateException HE) {
            System.out.println(HE);
        }
        t.commit();
       s.close();           
        sessionFactory.close();
        return TestiUlist;
    }

    public List<User> UserListByUserId(String userId) {
        sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sessionFactory.openSession();
        Transaction t = s.beginTransaction();
        List<User> usrlist = null;
        try {
            usrlist = s.createCriteria(User.class).add(Restrictions.eq("emailId", userId)).list();
        } catch (HibernateException HE) {
            System.out.println(HE);
        }
        t.commit();
       s.close();            
        sessionFactory.close();
        return usrlist;
    }

    public User UserPictureUpdate(Long registrationId, byte[] picture, String filtype) {
        sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sessionFactory.openSession();
        Transaction t = s.beginTransaction();
        User Ppl = (User) s.load(User.class, registrationId);
        Ppl.setPicture(picture);
        Ppl.setFiletype(filtype);
        s.update(Ppl);
        t.commit();
       s.close();         
        return Ppl;
    }

    public User UserUpdate(long registrationId, String fname, String mname, String lname, String fatherName, String motherName, String otherGuardian, String gender, Date dateOfBirth, String pbirth, String mstatus, Long aadhaarNo, String passportNo, String panNo, Integer activeStatus, String castCategory, String religion, String nationality, String languageKnown) {
        sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sessionFactory.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();
            User usrUpdate = (User) s.load(User.class, registrationId);
            usrUpdate.setFname(fname);
            usrUpdate.setMname(mname);
            usrUpdate.setLname(lname);
            usrUpdate.setFatherName(fatherName);
            usrUpdate.setMotherName(motherName);
            usrUpdate.setOtherGardian(otherGuardian);
            usrUpdate.setGender(gender);
            usrUpdate.setDateOfBirth(dateOfBirth);
            usrUpdate.setPbirth(pbirth);
            usrUpdate.setMstatus(mstatus);
            usrUpdate.setReligion(religion);
            usrUpdate.setNationality(nationality);
            usrUpdate.setAadhaarNo(aadhaarNo);
            usrUpdate.setPassportNo(passportNo);
            usrUpdate.setPanNo(panNo);
            usrUpdate.setCastCategory(castCategory);
            usrUpdate.setLanguageKnown(languageKnown);

            if (null != usrUpdate) {
                s.update(usrUpdate);
            }
            t.commit();
            return usrUpdate;
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
