/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.DAO;

import java.util.List;
import org.IGNOU.ePortfolio.Model.ProfileContact;
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
public class ContactDao {

    private SessionFactory sessionFactory;

//    public ContactInfo saveContInfo(ContactInfo cModel) {
//        SessionFactory sf = new AnnotationConfiguration().configure().buildSessionFactory();
//        Session s = sf.openSession();
//        Transaction t = null;
//        try {
//            t = s.beginTransaction();
//            s.save(cModel);
//            t.commit();
//            return cModel;
//        } catch (Throwable ex) {
//            //Log the Exception
//            t.rollback();
//            System.err.println("Initial SessionFactory creation failed." + ex);
//            throw new ExceptionInInitializerError(ex);
//        } finally {
//            s.close();
//            sf.close();
//        }
//    }
//
//    public ContactInfo saveInfo(String user_id, String ccountry, String cstate, String ccity, String caddress1, String caddress2, Integer cpinZip, Integer cphoneNo, Integer cmobileNo, Integer cfaxNo, String cemailId, String cwebsite) {
//        SessionFactory sf = new AnnotationConfiguration().configure().buildSessionFactory();
//        Session s = sf.openSession();
//        Transaction t = null;
//        try {
//            t = s.beginTransaction();
//            ContactInfo ci = new ContactInfo();
//            ci.setCaddress1(caddress1);
//            ci.setCaddress2(caddress2);
//            ci.setCcity(ccity);
//            ci.setCcountry(ccountry);
//            ci.setCemailId(cemailId);
//            ci.setCfaxNo(cfaxNo);
//            ci.setUserId(user_id);
//            ci.setCmobileNo(cmobileNo);
//            ci.setCphoneNo(cphoneNo);
//            ci.setCpinZip(cpinZip);
//            ci.setCstate(cstate);
//            ci.setCwebsite(cwebsite);
//            s.save(ci);
//            t.commit();
//            return ci;
//        } catch (Throwable ex) {
//            //Log the Exception
//            t.rollback();
//            System.err.println("Initial SessionFactory creation failed." + ex);
//            throw new ExceptionInInitializerError(ex);
//        } finally {
//            s.close();
//            sf.close();
//        }
//    }
    @SuppressWarnings("unchecked")
    public List<ProfileContact> ContactList(String user_id) {
        sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sessionFactory.openSession();
        Transaction t = s.beginTransaction();

        List<ProfileContact> contactlist = null;
        try {
            contactlist = s.createQuery("from ProfileContact where userId='" + user_id + "'").list();
            t.commit();
            return contactlist;
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
    public ProfileContact UpdateContact(long contactInfo_id, String user_id, String address1, String address2, String city, String state, String country, Integer pin, Long hTelephone, Long oTelephone, Long mobile, Long fax, String email1, String email2, String email3, String oWebsite, String pWebsite) {
        sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sessionFactory.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();
            ProfileContact UpdateContactList = (ProfileContact) s.load(ProfileContact.class, contactInfo_id);
            UpdateContactList.setAddress1(address1);
            UpdateContactList.setAddress2(address2);
            UpdateContactList.setCity(city);
            UpdateContactList.setCountry(country);
            UpdateContactList.setEmail1(email1);
            UpdateContactList.setEmail2(email2);
            UpdateContactList.setEmail3(email3);
            UpdateContactList.setFaxNo(fax);
            UpdateContactList.setHTelephone(hTelephone);
            UpdateContactList.setMobileNo(mobile);
            UpdateContactList.setOTelephone(oTelephone);
            UpdateContactList.setOwebsite(oWebsite);
            UpdateContactList.setPin(pin);
            UpdateContactList.setPwebsite(pWebsite);
            UpdateContactList.setState(state);
            if (null != UpdateContactList) {
                s.update(UpdateContactList);
            }
            t.commit();
            return UpdateContactList;
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
