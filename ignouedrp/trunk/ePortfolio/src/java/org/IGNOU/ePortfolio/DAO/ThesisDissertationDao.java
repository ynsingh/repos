/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.DAO;

import java.util.List;
import org.IGNOU.ePortfolio.Model.ThesisDissertation;
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
public class ThesisDissertationDao {

    private SessionFactory sessionFactory=new AnnotationConfiguration().configure().buildSessionFactory();
    private Session s;
   
    public ThesisDissertation ThesisDissertationSave(ThesisDissertation TDModel) {
        s = sessionFactory.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();
            s.save(TDModel);
            t.commit();
            return TDModel;
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

    public List<ThesisDissertation> ThesisDissertationListByUserId(String user_id) {
         s = sessionFactory.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();
            List<ThesisDissertation> TDListList = null;
            try {
                TDListList = s.createQuery("from ThesisDissertation where userId='" + user_id + "'").list();
            } catch (HibernateException HE) {
                System.out.println(HE);
            }
            t.commit();
            return TDListList;
        } catch (Throwable ex) { //Log theException
            t.rollback();
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        } finally {
            s.close();
            sessionFactory.close();
        }
    }

    public List<ThesisDissertation> ThesisDissertationEdit(long thesisDissertationId) {
         s = sessionFactory.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();
            List<ThesisDissertation> RCListList = null;
            try {
                RCListList = s.createQuery("from ThesisDissertation where thesisDissertationId='" + thesisDissertationId + "'").list();
            } catch (HibernateException HE) {
                System.out.println(HE);
            }
            t.commit();
            return RCListList;
        } catch (Throwable ex) { //Log theException
            t.rollback();
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        } finally {
            s.close();
            sessionFactory.close();
        }
    }

    public ThesisDissertation ThesisDissertationUpdate(long thesisDissertationId, String userId, String reportType, String programme, String other, String department, String nameUniversity, String cityState, String country, String thesisType, String thesisTitle, String startDate, String endDate, String outcome, String url, String abstract_) {
         s = sessionFactory.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();
            ThesisDissertation UpdateInfo = (ThesisDissertation) s.load(ThesisDissertation.class, thesisDissertationId);
            UpdateInfo.setAbstract_(abstract_);
            UpdateInfo.setCityState(cityState);
            UpdateInfo.setCountry(country);
            UpdateInfo.setDepartment(department);
            UpdateInfo.setEndDate(endDate);
            UpdateInfo.setNameUniversity(nameUniversity);
            UpdateInfo.setOther(other);
            UpdateInfo.setOutcome(outcome);
            UpdateInfo.setProgramme(programme);
            UpdateInfo.setReportType(reportType);
            UpdateInfo.setStartDate(startDate);
            UpdateInfo.setThesisTitle(thesisTitle);
            UpdateInfo.setThesisType(thesisType);
            UpdateInfo.setUrl(url);
            if (null != UpdateInfo) {
                s.update(UpdateInfo);
            }
            t.commit();
            return UpdateInfo;
        } catch (Throwable ex) { //Log the Exception t.rollback();
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        } finally {
            s.close();
            sessionFactory.close();
        }
    }

    public ThesisDissertation ThesisDissertationDelete(long thesisDissertationId) {
        s = sessionFactory.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();
            ThesisDissertation DeleteInfo = (ThesisDissertation) s.load(ThesisDissertation.class, thesisDissertationId);
            if (DeleteInfo != null) {
                s.delete(DeleteInfo);
            }
            t.commit();
            return DeleteInfo;
        } catch (Throwable ex) { //Log the Exception 
            t.rollback();
            System.err.println("Initial   SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        } finally {
            s.close();
            sessionFactory.close();
        }
    }

}
