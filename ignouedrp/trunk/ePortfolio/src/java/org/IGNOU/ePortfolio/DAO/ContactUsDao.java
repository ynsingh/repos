/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.DAO;

import java.util.List;
import org.IGNOU.ePortfolio.Model.ContactUs;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 *
 * @author Amit
 */
public class ContactUsDao {
     private SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
    private Session s;
    
     @SuppressWarnings("unchecked")
    public List<ContactUs> ContactUsList() {
        s = sessionFactory.openSession();
        Transaction t = s.beginTransaction();

        List<ContactUs> contactlist = null;
        try {
            contactlist = s.createQuery("from ContactUs").list();
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
    public  ContactUs ContactUsSave( String contactName, String contactAddress, Long contactOff, Long contactMob, String contactEmail,String aboutUs) {
        s = sessionFactory.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();
            ContactUs cus=new ContactUs();
            cus.setContactName(contactName);
            cus.setContactAddress(contactAddress);
            cus.setContactOff(contactOff);
            cus.setContactMob(contactMob);
            cus.setContactEmail(contactEmail);
            cus.setAboutUs(aboutUs);
            s.save(cus);
            t.commit();
            return cus;
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
    public List<ContactUs> ContactUsEditByContactId(int contactId) {
        s = sessionFactory.openSession();
        Transaction t = s.beginTransaction();
        List<ContactUs> contactlist = null;
        try {
            contactlist = s.createQuery("from ContactUs where contactId='" + contactId + "'").list();
        } catch (HibernateException HE) {
            System.out.println(HE);
        }
        t.commit();
        s.close();
        sessionFactory.close();
        return contactlist;
    }
   
     @SuppressWarnings("unchecked")
    public ContactUs ContactUsUpdate(int contactId,String contactName, String contactAddress, Long contactOff, Long contactMob, String contactEmail,String aboutUs) {
        s = sessionFactory.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();

            ContactUs Updatecontactus = (ContactUs) s.load(ContactUs.class, contactId);
            Updatecontactus.setContactName(contactName);
            Updatecontactus.setContactAddress(contactAddress);
            Updatecontactus.setContactMob(contactMob);
            Updatecontactus.setContactOff(contactOff);
            Updatecontactus.setContactEmail(contactEmail);
            Updatecontactus.setAboutUs(aboutUs);
            if (null != Updatecontactus) {
                s.update(Updatecontactus);
            }
            t.commit();
            return Updatecontactus;
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
