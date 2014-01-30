/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.DAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.IGNOU.ePortfolio.Model.Journal;
import org.IGNOU.ePortfolio.Model.JournalAuthor;
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
 * @since 27-02-2012
 */
public class JournalDao {

    private SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
    private Session s;

    public Journal JournalSave(Journal JModel) {
        s = sessionFactory.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();
            s.save(JModel);

            JournalAuthor JA = new JournalAuthor();
            for (int i = 0; i < JModel.getFname().size(); i++) {
                JA.setJournal(JModel);
                JA.setFname(JModel.getFname().get(i));
                JA.setLname(JModel.getLname().get(i));
                s.save(JA);
                s.flush();
                s.clear();
            }
            t.commit();
            return JModel;
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

    public List<Journal> JournalListByUserId(String user_id) {
        s = sessionFactory.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();
            List<Journal> JList = null;

            try {
                JList = s.createQuery("from Journal where userId='" + user_id + "'").list();
            } catch (HibernateException HE) {
                System.out.println(HE);
            }
            t.commit();
            return JList;
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

    public List<Journal> JournalListByJournalId(long journalId) {
        s = sessionFactory.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();
            List<Journal> JList = null;
            try {
                JList = s.createQuery("from Journal where journalId='" + journalId + "'").list();
            } catch (HibernateException HE) {
                System.out.println(HE);
            }
            t.commit();
            return JList;
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

    public Journal JournalDeleteByJournalId(long journalId) {
        s = sessionFactory.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();
            Journal DeleteInfo = (Journal) s.load(Journal.class, journalId);
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

    public Journal JournalUpdate(long journalId, String userId, String confType, String researchArea, String asssProject, String projectName, String paperTitle, Integer noCoauthor, String journalName, Integer volumeNo, Integer serialNo, String issnNo, Integer pfrom, Integer pto, String date, String impactFactor, String avgCitagtionIndex, String scopus, String language, String affiliation, String url, String summary, String key1, String key2, String key3, String key4, String key5, String key6, Set<JournalAuthor> journalAuthors, ArrayList<String> fname, ArrayList<String> lname) {
        s = sessionFactory.openSession();
        Transaction t = null;
        try {
            t = s.beginTransaction();
            Journal UpdateInfo = (Journal) s.load(Journal.class, journalId);
            UpdateInfo.setAffiliation(affiliation);
            UpdateInfo.setAsssProject(asssProject);
            UpdateInfo.setAvgCitagtionIndex(avgCitagtionIndex);
            UpdateInfo.setConfType(confType);
            UpdateInfo.setDate(date);
            UpdateInfo.setImpactFactor(impactFactor);
            UpdateInfo.setIssnNo(issnNo);
            UpdateInfo.setJournalName(journalName);
            UpdateInfo.setKey1(key1);
            UpdateInfo.setKey2(key2);
            UpdateInfo.setKey3(key3);
            UpdateInfo.setKey4(key4);
            UpdateInfo.setKey5(key5);
            UpdateInfo.setKey6(key6);
            UpdateInfo.setLanguage(language);
            UpdateInfo.setNoCoauthor(noCoauthor);
            UpdateInfo.setPaperTitle(paperTitle);
            UpdateInfo.setPfrom(pfrom);
            UpdateInfo.setProjectName(projectName);
            UpdateInfo.setPto(pto);
            UpdateInfo.setResearchArea(researchArea);
            UpdateInfo.setScopus(scopus);
            UpdateInfo.setSerialNo(serialNo);
            UpdateInfo.setSummary(summary);
            UpdateInfo.setUrl(url);
            UpdateInfo.setVolumeNo(volumeNo);
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
