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
 * @version 2 Modified by IGNOU Team on 11-01-2012
 */
public class ProfileContactDao {

    private SessionFactory sf;

    public ProfileContact saveContactInfo(ProfileContact ContactModel) {
        sf = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sf.openSession();
        Transaction t = s.beginTransaction();
        s.save(ContactModel);
        t.commit();
        return ContactModel;
    }

    public SessionFactory getSessionFactory() {
        return sf;
    }
}
