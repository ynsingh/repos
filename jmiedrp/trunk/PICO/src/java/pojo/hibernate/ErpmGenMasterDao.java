package pojo.hibernate;

import utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Hibernate;


import java.util.List;

public class ErpmGenMasterDao {

    public void save(ErpmGenMaster erpmgm) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(erpmgm);
            tx.commit();
        }
        catch (RuntimeException re) {
            if(erpmgm != null)
                tx.rollback();
            throw re;
        }
        finally {
            session.close();
        }
    }

     public void update(ErpmGenMaster erpmgm) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(erpmgm);
            tx.commit();
        }
        catch (RuntimeException re) {
            if(erpmgm != null)
                tx.rollback();
            throw re;
        }
        finally {
            session.close();
        }
    }

    public void delete(ErpmGenMaster erpmgm) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(erpmgm);
            tx.commit();
        }
        catch (RuntimeException re) {
            if(erpmgm != null)
                tx.rollback();
            throw re;
        }
        finally {
            session.close();
        }
    }

    public List<ErpmGenMaster> findAll() {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            List<ErpmGenMaster> list = session.createQuery("from ErpmGenMaster").list();
            return list;

        }
        finally {
            session.close();
            }
    }

    public ErpmGenMaster findByErpmGmId(int erpmgmEgmId) {                
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            ErpmGenMaster erpmgm  = (ErpmGenMaster) session.load(ErpmGenMaster.class , erpmgmEgmId);
            Hibernate.initialize(erpmgm.getErpmGenCtrl());
            return erpmgm;

        }
        finally {
            session.close();
            }
        }

    public List<ErpmGenMaster> findByErpmGmType(short erpmgmEgmType) {       
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            List<ErpmGenMaster> erpmgmlist  = session.createQuery("Select u from ErpmGenMaster u where u.erpmGenCtrl.erpmgcGenType = :erpmgmEgmType order by u.erpmgmEgmDesc")
                                                     .setParameter("erpmgmEgmType",erpmgmEgmType)
                                                     .list();
            for (int index=0; index < erpmgmlist.size(); ++index)
                Hibernate.initialize(erpmgmlist.get(index).getErpmGenCtrl());
            return erpmgmlist;

        }
        finally {
            session.close();
            }
    }


    public Integer findDuplicateGeneralMasterEntry(short erpmgcGenType, String erpmgmEgmDesc) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Integer matchingRecords  = Integer.parseInt(session.createQuery("Select count(u) from ErpmGenMaster u.erpmGenCtrl.erpmgcGenType = :erpmgcGenType and upper(u.erpmgmEgmDesc) = upper(:erpmgmEgmDesc)").setParameter("erpmgcGenType",erpmgcGenType).setParameter("erpmgmEgmDesc",erpmgmEgmDesc).list().get(0).toString());    return matchingRecords;
        }
        finally {
            session.close();
            }
        }

    public ErpmGenMaster findByErpmGmDesc(String erpmgmEgmDesc) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            List<ErpmGenMaster> erpmgmlist  = session.createQuery("Select u from ErpmGenMaster u where u.erpmgmEgmDesc = :erpmgmEgmDesc").setParameter("erpmgmEgmDesc",erpmgmEgmDesc).list();
            return erpmgmlist.get(0);
        }
        finally {
            session.close();
            }
        }


    public int findDefaultCurrency(String erpmgmEgmDesc) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            List<ErpmGenMaster> erpmgmlist  = session.createQuery("Select u from ErpmGenMaster u where u.erpmgmEgmDesc = :erpmgmEgmDesc").setParameter("erpmgmEgmDesc",erpmgmEgmDesc).list();
            return erpmgmlist.get(0).getErpmgmEgmId();
        }
        finally {
            session.close();
            }
        }
    
    
    public List<ErpmGenMaster> findErpmGmDescByWFActions(int wfawfdId) {        
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            List<ErpmGenMaster> erpmgmlist  = session.createQuery("SELECT u FROM ErpmGenMaster u, Workflowactions b where u.erpmgmEgmId = b.erpmGenMaster and b.workflowdetail.wfdId = :wfawfdId").setParameter("wfawfdId",wfawfdId).list();
            return erpmgmlist;
        }
        finally {
            session.close();
            }
        }

      public List<ErpmGenMaster> findByExpiryType(Short EgmType) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
        List<ErpmGenMaster> erpmgmlist  = session.createQuery("SELECT distinct(u) FROM ErpmGenMaster u where u.erpmGenCtrl.erpmgcGenType =:EgmType").setParameter("EgmType",EgmType).list();
        return erpmgmlist;
          }
        finally {
            session.close();
            }
    }
}