/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.DAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.IGNOU.ePortfolio.Model.Inventor;
import org.IGNOU.ePortfolio.Model.Patent;
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
 * @version 1
 * @since 18 January 2012
 */
public class PatentDao {

    private SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
    private Session s;

    public Patent PatentSave(Patent pModel) {
        s = sessionFactory.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();
            s.save(pModel);

            Inventor invent = new Inventor();
            for (int i = 0; i < pModel.getFname().size(); i++) {
                invent.setPatent(pModel);
                invent.setAddress(pModel.getAddress().get(i));
                invent.setFname(pModel.getFname().get(i));
                invent.setLname(pModel.getLname().get(i));
                s.save(invent);
                s.flush();
                s.clear();
            }
            t.commit();
            return pModel;
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

    public List<Patent> PatentListByUserId(String user_id) {
        s = sessionFactory.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();
            List<Patent> PatentList = null;

            try {
                PatentList = s.createQuery("from Patent where userId='" + user_id + "'").list();
            } catch (HibernateException HE) {
                System.out.println(HE);
            }
            t.commit();
            return PatentList;
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

    public List<Patent> PatentEdit(long patentId) {
        s = sessionFactory.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();
            List<Patent> PatentList = null;

            try {
                //  List<String> pat = s.createQuery("from Patent where userId='" + user_id + "'").list();
                PatentList = s.createQuery("from Patent where patentId='" + patentId + "'").list();
                //    InventorList.set(patentIds, pat);
            } catch (HibernateException HE) {
                System.out.println(HE);
            }
            t.commit();
            return PatentList;
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

    public Patent PatentDelete(long patentId) {
        s = sessionFactory.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();
            Patent DeleteInfo = (Patent) s.load(Patent.class, patentId);
            if (DeleteInfo != null) {
                s.delete(DeleteInfo);
            }
            t.commit();
            return DeleteInfo;
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

    public Patent PatentUpdate(Long patentId, String userId, String patentType, String country, String patentTitle, String assignee, Integer applNo, String field, String patentDate, Integer patentNo, String affiliation, String language, String url, String abstract_, Integer api, ArrayList<String> fname, ArrayList<String> lname, ArrayList<String> address, Set<Inventor> inventors) {
        s = sessionFactory.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();
            Patent UpdateInfo = (Patent) s.load(Patent.class, patentId);
            UpdateInfo.setAbstract_(abstract_);
            UpdateInfo.setAffiliation(affiliation);
            UpdateInfo.setApi(api);
            UpdateInfo.setApplNo(applNo);
            UpdateInfo.setAssignee(assignee);
            UpdateInfo.setCountry(country);
            UpdateInfo.setField(field);
            UpdateInfo.setInventors(inventors);
            UpdateInfo.setLanguage(language);
            UpdateInfo.setPatentDate(patentDate);
            UpdateInfo.setPatentNo(patentNo);
            UpdateInfo.setPatentTitle(patentTitle);
            UpdateInfo.setUrl(url);
            UpdateInfo.setPatentType(patentType);
            if (null != UpdateInfo) {
                s.update(UpdateInfo);
            }
            t.commit();
            return UpdateInfo;
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
