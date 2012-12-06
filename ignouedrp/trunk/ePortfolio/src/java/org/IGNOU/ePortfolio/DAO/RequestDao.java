/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.DAO;

import java.util.Date;
import java.util.List;
import org.IGNOU.ePortfolio.Model.PersonalInfo;
import org.IGNOU.ePortfolio.Model.UserList;
import org.IGNOU.ePortfolio.Model.UserPersonalRequest;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author Vinay Kr. Sharma
 */
public class RequestDao {

    private SessionFactory sessionFactory;

    public UserPersonalRequest saveUserRequest(String user_id, String requestType, String reason, String newRecord, Date requestDate, String recordProof, Boolean status) {
        sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sessionFactory.openSession();
        Transaction t = null;
        UserPersonalRequest upr = new UserPersonalRequest();
        try {
            t = s.beginTransaction();
            UserList ul=new UserList();
            ul.setEmailId(user_id);
            upr.setUser(ul);
            upr.setRequestDate(requestDate);
            upr.setRequestType(requestType);
            upr.setReason(reason);
            upr.setNewRecord(newRecord);
            upr.setRecordProof(recordProof);
            upr.setStatus(status);
            s.save(upr);
            t.commit();
            return upr;
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
    public List<UserPersonalRequest> userRequestList() {
        sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sessionFactory.openSession();
        Transaction t = s.beginTransaction();
        List<UserPersonalRequest> Reqlist = null;
        try {
            Reqlist = s.createQuery("from UserPersonalRequest where status='0' order by requestDate DESC").list();
        } catch (HibernateException HE) {
            System.out.println(HE);
        }
        t.commit();
        s.close();
        sessionFactory.close();
        return Reqlist;
    }

    @SuppressWarnings("unchecked")
    public List<UserPersonalRequest> userRequestUpdatedList() {
        sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sessionFactory.openSession();
        Transaction t = s.beginTransaction();
        List<UserPersonalRequest> Reqlist = null;
        try {
            Reqlist = s.createQuery("from UserPersonalRequest where status='1' order by requestDate DESC").list();
        } catch (HibernateException HE) {
            System.out.println(HE);
        }
        t.commit();
        s.close();
        sessionFactory.close();
        return Reqlist;
    }

    @SuppressWarnings("unchecked")
    public List<UserPersonalRequest> RequestInfoList(Integer requestId) {
        sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sessionFactory.openSession();
        Transaction t = s.beginTransaction();
        List<UserPersonalRequest> Reqlist = null;
        try {
            Reqlist = s.createQuery("from UserPersonalRequest where requestId='" + requestId + "'").list();
        } catch (HibernateException HE) {
            System.out.println(HE);
        }
        t.commit();
        s.close();
        sessionFactory.close();
        return Reqlist;
    }

    @SuppressWarnings("unchecked")
    public List<PersonalInfo> EditPersonalInfo(String emailId) {
        sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sessionFactory.openSession();
        Transaction t = s.beginTransaction();
        List<PersonalInfo> personallist = null;
        try {
            personallist = s.createQuery("from PersonalInfo where emailId='" + emailId + "'").list();
        } catch (HibernateException HE) {
            System.out.println(HE);
        }
        t.commit();
        s.close();
        sessionFactory.close();
        return personallist;
    }

    @SuppressWarnings("unchecked")
    public PersonalInfo UpdatePersonalInfo(Integer requestId, String recordArchive, long personalInfoId, String emailId, String firstName, String lastName, String fatherName, String motherName, String otherGuardian, String gender, Date dateOfBirth, String pbirth, String mstatus, Long aadhaarNo, String passportNo, String panNo, Integer activeStatus, String castCategory, String religion, String nationality, String languageKnown) {
        sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sessionFactory.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();
            UserPersonalRequest updateRequest = (UserPersonalRequest) s.load(UserPersonalRequest.class, requestId);
            updateRequest.setStatus(Boolean.TRUE);
            updateRequest.setUpdatedDate(new Date());
            updateRequest.setRecordArchive(recordArchive);
            if (null != updateRequest) {
                s.update(updateRequest);
            }
            PersonalInfo update = (PersonalInfo) s.load(PersonalInfo.class, personalInfoId);
            update.setFirstName(firstName);
            update.setLastName(lastName);
            update.setFatherName(fatherName);
            update.setMotherName(motherName);
            update.setOtherGuardian(otherGuardian);
            update.setDateOfBirth(dateOfBirth);
            update.setAadhaarNo(aadhaarNo);
            update.setPanNo(panNo);
            update.setPassportNo(passportNo);
            update.setPbirth(pbirth);
            update.setGender(gender);
            update.setMstatus(mstatus);
            update.setCastCategory(castCategory);
            update.setReligion(religion);
            update.setNationality(nationality);
            update.setLanguageKnown(languageKnown);
            if (null != update) {
                s.update(update);
            }
            t.commit();
            return update;
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

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
