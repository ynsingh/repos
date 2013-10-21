/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo.hibernate;

import utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Hibernate;
import java.util.List;
//import utils.BaseDAO;

/**
 * extends BaseDAO
 *
 * @author Tanvir Ahmed, Arpan
 */
public class ErpmIssueReturnMasterDAO {

    public void save(ErpmIssueReturnMaster erpmirm) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(erpmirm);
            tx.commit();
        } catch (RuntimeException re) {
            if (erpmirm != null) {
                tx.rollback();
            }
            throw re;
        } finally {
            session.close();
        }
    }

    public void update(ErpmIssueReturnMaster erpmirm) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(erpmirm);
            tx.commit();
        } catch (RuntimeException re) {
            if (erpmirm != null) {
                tx.rollback();
            }
            throw re;
        } finally {
            session.close();
        }
    }

    public void delete(ErpmIssueReturnMaster erpmirm) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(erpmirm);
            tx.commit();
        } catch (RuntimeException re) {
            if (erpmirm != null) {
                tx.rollback();
            }
            throw re;
        } finally {
            session.close();
        }
    }

    public ErpmIssueReturnMaster findByErpmIrmId(int erpirmId) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            ErpmIssueReturnMaster eimId1 = (ErpmIssueReturnMaster) session.load(ErpmIssueReturnMaster.class, erpirmId);
            Hibernate.initialize(eimId1);

            return eimId1;
        } finally {
            session.close();
        }
    }

    public List<ErpmIssueReturnMaster> findReturnIssuedItemsForUserInstitutes(Integer erpmuId) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            List<ErpmIssueReturnMaster> erpmirmList = session.createQuery("Select u from ErpmIssueReturnMaster u where u.departmentmaster.dmId  in (select r.departmentmaster.dmId from Erpmuserrole r where r.erpmusers.erpmuId = :erpmuId)").setParameter("erpmuId", erpmuId).list();
            for (int index = 0; index < erpmirmList.size(); ++index) {
                Hibernate.initialize(erpmirmList.get(index).getInstitutionmaster());
                Hibernate.initialize(erpmirmList.get(index).getSubinstitutionmaster());
                Hibernate.initialize(erpmirmList.get(index).getDepartmentmaster());
            }
            return erpmirmList;
        } finally {
            session.close();
        }
    }

    public ErpmIssueReturnMaster findByErpmirmId(int erpmirmId) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            ErpmIssueReturnMaster eimId1 = (ErpmIssueReturnMaster) session.load(ErpmIssueReturnMaster.class, erpmirmId);
            Hibernate.initialize(eimId1.getInstitutionmaster());
            Hibernate.initialize(eimId1.getSubinstitutionmaster());
            Hibernate.initialize(eimId1.getDepartmentmaster());
            Hibernate.initialize(eimId1.getEmployeemaster());
            return eimId1;
        } finally {
            session.close();
        }
    }

    public List<ErpmIssueSerialDetail> findItemserialnoForUser(char returntype, int erpmisdId) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            List<ErpmIssueSerialDetail> ErpmisdList = session.createQuery("select u from ErpmIssueSerialDetail u").list();
            for (int index = 0; index < ErpmisdList.size(); ++index) {
                Hibernate.initialize(ErpmisdList.get(index).getErpmStockReceived());
            }
            return ErpmisdList;
        } finally {
            session.close();
        }
    }

    public List<ErpmIssueMaster> findIssuemasterlist(char returntype, int eimId) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            List<ErpmIssueMaster> ErpmimList = session.createQuery("select u from ErpmIssueMaster u").list();
            return ErpmimList;
        } finally {
            session.close();
        }
    }

    public List<ErpmIssueMaster> findIssuemasterlistfromDmId(int dmid) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            List<ErpmIssueMaster> ErpmimList = session.createQuery("select u from ErpmIssueMaster u").list();
            return ErpmimList;
        } finally {
            session.close();
        }
    }

    public List<ErpmIssueSerialDetail> findItemserialnoForUserfromDmId(int dmid) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            List<ErpmIssueSerialDetail> ErpmisdList = session.createQuery("select u.erpmStockReceived from ErpmIssueSerialDetail u").list();
            for (int index = 0; index < ErpmisdList.size(); ++index) {
                Hibernate.initialize(ErpmisdList.get(index).getErpmStockReceived());
            }
            return ErpmisdList;
        } finally {
            session.close();
        }
    }
}