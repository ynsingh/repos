/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo.hibernate;
import pojo.hibernate.ErpmTenderSubmissionFiles;
import utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;
import org.hibernate.Hibernate;

/**
 *@author <a href="mailto:jaivirpal@gmail.com">Jaivir Singh</a>2015
 */ 


public class ErpmTenderSubmissionFilesDao   {
 public void save(ErpmTenderSubmissionFiles erpmtsf) {
      Session session = HibernateUtil.getSessionPicoFactory();
        Transaction tx = null;
        try {
            tx=session.beginTransaction();
           session.save(erpmtsf);
            tx.commit();
            }
        catch (RuntimeException re) {
          
            if (erpmtsf != null) {
                 tx.rollback();
            }
            throw re;
        } finally {
            session.close();
        }
    
        

 }
  public void update(ErpmTenderSubmissionFiles erpmtsf) {
        Session session = HibernateUtil.getSessionPicoFactory();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(erpmtsf);
            tx.commit();
        } catch (RuntimeException re) {
            if (erpmtsf != null) {
                tx.rollback();
            }
            throw re;
        } finally {
            session.close();
        }
    }
  
public List< ErpmTenderSubmissionFiles> getListofFile(Integer tsbTsbId) {
        Session session = HibernateUtil.getSessionPicoFactory();
        try {
           
            session.beginTransaction();
            List< ErpmTenderSubmissionFiles> list = session.createQuery("Select u from  ErpmTenderSubmissionFiles u where u.erpmTenderSubmission.tsbTsbId = :tsbTsbId").setParameter("tsbTsbId", tsbTsbId).list();
           for (int index = 0; index < list.size(); ++index) {
                Hibernate.initialize(list.get(index).getErpmTenderSubmission());
                Hibernate.initialize(list.get(index).getTsfFileName());
                Hibernate.initialize(list.get(index).getTsfFileRemarks());
                Hibernate.initialize(list.get(index).getTsfFileStream());
            }
            return list;
        } finally {
            session.close();
        }
    }
       
          public void DeleteSubmissionFile(ErpmTenderSubmissionFiles erpmtsb) {
               Session session = HibernateUtil.getSessionPicoFactory();
               Transaction tx = null;
        try {
            tx=session.beginTransaction();
            session.delete(erpmtsb);
            tx.commit();
        }
        catch (RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
    }
             public ErpmTenderSubmissionFiles findByerpmtsfId(Integer tsbTsbId) {
                  Session session = HibernateUtil.getSessionPicoFactory();
                  Transaction tx = null;
                  tx=session.beginTransaction();
                  int index = 0;
                  ErpmTenderSubmissionFiles erpmtsf = new ErpmTenderSubmissionFiles();
                  erpmtsf = (ErpmTenderSubmissionFiles) session.load(ErpmTenderSubmissionFiles.class, tsbTsbId);
         
                  Hibernate.initialize(erpmtsf);
           
        tx.commit();
        return erpmtsf;


    }
    }

   
 

