/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.DAO;

import java.util.List;
import org.IGNOU.ePortfolio.Model.ActivitiesSubmission;
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
public class TaskActivityDAO {

    private SessionFactory sessionFactory=new AnnotationConfiguration().configure().buildSessionFactory();
    private Session s;
   
    @SuppressWarnings("unchecked")
    public List<ActivitiesSubmission> EvidenceSubmissionListGradeNotNullByUserId(String userId) {
        s = sessionFactory.openSession();
        Transaction t = s.beginTransaction();
        List<ActivitiesSubmission> EvidStdList = null;
        try {
            EvidStdList = s.createQuery("from ActivitiesSubmission where user='" + userId + "' and gradesObtained!=null  ORDER BY evidence_id DESC").list();
        } catch (HibernateException HE) {
            System.out.println(HE);
        }
        t.commit();
        s.close();
        sessionFactory.close();
        return EvidStdList;
    }

}
