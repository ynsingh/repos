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

    private SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
    private Session s;

    @SuppressWarnings("unchecked")
    public List<ProfileContact> ContactList(String user_id) {
        s = sessionFactory.openSession();
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
    public ProfileContact ContactUpdate(long contactInfo_id, String user_id, String address1, String address2, String city, String state, String country, Integer pin, Long hTelephone, Long oTelephone, Long mobile, Long fax, String email1, String email2, String email3, String oWebsite, String pWebsite) {
        s = sessionFactory.openSession();
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

}
