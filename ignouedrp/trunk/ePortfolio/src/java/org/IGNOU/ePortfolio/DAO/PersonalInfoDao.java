/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.DAO;

import java.util.Date;
import java.util.List;
import org.IGNOU.ePortfolio.Model.PersonalInfo;
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
 */
public class PersonalInfoDao {

    private SessionFactory sf = new AnnotationConfiguration().configure().buildSessionFactory();
    private Session s;

//    @SuppressWarnings("unchecked")
//    public List<PersonalInfo> PersonalInfoList(String user_id) {
//        s = getSf().openSession();
//        Transaction t = s.beginTransaction();
//        List<PersonalInfo> personallist = null;
//        try {
//            personallist = s.createQuery("from PersonalInfo where emailId='" + user_id + "'").list();
//        } catch (HibernateException HE) {
//            System.out.println(HE);
//        }
//        t.commit();
//       s.close();
//        getSf().close();
//        return personallist;
//    }
    @SuppressWarnings("unchecked")
    public List<PersonalInfo> PersonalInfoEdit(Long personalInfoId) {
        s = getSf().openSession();
        Transaction t = s.beginTransaction();
        List<PersonalInfo> personallist = null;
        try {
            personallist = s.createQuery("from PersonalInfo where personalInfoId='" + personalInfoId + "'").list();
        } catch (HibernateException HE) {
            System.out.println(HE);
        }
        t.commit();
        s.close();
        getSf().close();
        return personallist;
    }

    @SuppressWarnings("unchecked")
    public PersonalInfo PersonalInfoUpdate(long personalInfoId, String emailId, String firstName, String lastName, String fatherName, String motherName, String otherGuardian, String gender, Date dateOfBirth, String pbirth, String mstatus, Long aadhaarNo, String passportNo, String panNo, Integer activeStatus, String castCategory, String religion, String nationality, String languageKnown) {
        s = getSf().openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();
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
            getSf().close();
        }
    }

    /**
     * @return the sf
     */
    public SessionFactory getSf() {
        return sf;
    }
}
