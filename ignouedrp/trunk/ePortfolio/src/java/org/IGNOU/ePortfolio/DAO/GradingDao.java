/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.DAO;

import java.util.List;
import org.IGNOU.ePortfolio.Model.EvidenceSubmission;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 * Hibernate Utility class with a convenient method to get Session Factory object.
 *
 * @author IGNOU Team
 */
public class GradingDao {

    private SessionFactory sf;

    @SuppressWarnings("unchecked")
    public List<EvidenceSubmission> TaskSubmitedList(int evidenceId) {
        sf = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sf.openSession();
        Transaction t = s.beginTransaction();
        List<EvidenceSubmission> userlist = null;
        try {
            userlist = s.createQuery("from EvidenceSubmission where evidence='" + evidenceId + "'").list();
        } catch (HibernateException HE) {
            System.out.println(HE);
        }
        t.commit();
        s.close();
        sf.close();
        return userlist;
    }

    @SuppressWarnings("unchecked")
    public List<EvidenceSubmission> getUserList(int submissionId) {
        sf = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sf.openSession();
        Transaction t = s.beginTransaction();
        List<EvidenceSubmission> userlist = null;
        try {
            userlist = s.createQuery("from EvidenceSubmission where submissionId='" + submissionId + "'").list();
        } catch (HibernateException HE) {
            System.out.println(HE);
        }
        t.commit();
        s.close();
        sf.close();
        return userlist;
    }

    /**
     * @return the sf
     */
    public SessionFactory getSf() {
        return sf;
    }
}
