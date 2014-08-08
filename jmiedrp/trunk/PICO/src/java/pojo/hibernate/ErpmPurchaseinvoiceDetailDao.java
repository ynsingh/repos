/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo.hibernate;

//import java.math.BigDecimal;
import utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;
import org.hibernate.Hibernate;
//import utils.BaseDAO;

/**
 *
 * @author Saeed
 */
public class ErpmPurchaseinvoiceDetailDao {

    public void save(ErpmPurchaseinvoiceDetail erpmpid) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(erpmpid);
            tx.commit();
        } catch (RuntimeException re) {
            if (erpmpid != null) {
                tx.rollback();
            }
            throw re;
        } finally {
            session.close();
        }
    }

    public void update(ErpmPurchaseinvoiceDetail erpmpid) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(erpmpid);
            tx.commit();
        } catch (RuntimeException re) {
            if (erpmpid != null) {
                tx.rollback();
            }
            throw re;
        } finally {
            session.close();
        }
    }

    public void delete(ErpmPurchaseinvoiceDetail erpmpid) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(erpmpid);
            tx.commit();
        } catch (RuntimeException re) {
            if (erpmpid != null) {
                tx.rollback();
            }
            throw re;
        } finally {
            session.close();
        }
    }

    public ErpmPurchaseinvoiceDetail findByErpId(Integer pidPidId) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            ErpmPurchaseinvoiceDetail erpmpid = new ErpmPurchaseinvoiceDetail();
            erpmpid = (ErpmPurchaseinvoiceDetail) session.load(ErpmPurchaseinvoiceDetail.class, pidPidId);
            Hibernate.initialize(erpmpid.getErpmItemMaster());
            Hibernate.initialize(erpmpid.getErpmPurchaseinvoiceMaster());
            Hibernate.initialize(erpmpid.getErpmPurchaseinvoiceMaster().getErpmPoMaster());

            return erpmpid;
        } finally {
            session.close();
        }
    }

    public List<ErpmPurchaseinvoiceDetail> findBypimId(Integer pimPimId) {
        Session session = HibernateUtil.getSession();
        try {

            session.beginTransaction();
            List<ErpmPurchaseinvoiceDetail> pimlist = session.createQuery("Select u from ErpmPurchaseinvoiceDetail u where u.erpmPurchaseinvoiceMaster.pimPimId = :pimPimId").setParameter("pimPimId", pimPimId).list();
            for (int index = 0; index < pimlist.size(); index++) {
                Hibernate.initialize(pimlist.get(index).getErpmItemMaster());
                Hibernate.initialize(pimlist.get(index).getErpmPurchaseinvoiceMaster().getErpmPoMaster());
            }
            return pimlist;
        } finally {
            session.close();
        }
    }

    public List<ErpmPurchaseinvoiceDetail> findBy_pomPoMasterId_ItemId_pidPidId(Integer pomPoMasterId, Integer itemId, Integer pidPidId) {
        Session session = HibernateUtil.getSession();
        try {

            session.beginTransaction();
            List<ErpmPurchaseinvoiceDetail> Browselist = session.createQuery("Select u from ErpmPurchaseinvoiceDetail u where u.pidPidId != :pidPidId and u.erpmItemMaster.erpmimId = :itemId and u.erpmPurchaseinvoiceMaster.erpmPoMaster.pomPoMasterId = :pomPoMasterId").setParameter("itemId", itemId).setParameter("pomPoMasterId", pomPoMasterId).setParameter("pidPidId", pidPidId).list();
            for (int index = 0; index < Browselist.size(); ++index) {
                Hibernate.initialize(Browselist.get(index));
            }
            return Browselist;
        } finally {
            session.close();
        }
    }

    public List<ErpmPurchaseinvoiceDetail> findBy_pcmPcmId_ItemId_pidPidId(Integer pcmPcmId, Integer itemId, Integer pidPidId) {
        Session session = HibernateUtil.getSession();
        try {

            session.beginTransaction();
            List<ErpmPurchaseinvoiceDetail> Browselist = session.createQuery("Select u from ErpmPurchaseinvoiceDetail u where u.pidPidId != :pidPidId and u.erpmItemMaster.erpmimId = :itemId and u.erpmPurchaseinvoiceMaster.erpmPurchasechallanMaster.pcmPcmId = :pcmPcmId").setParameter("itemId", itemId).setParameter("pcmPcmId", pcmPcmId).setParameter("pidPidId", pidPidId).list();
            for (int index = 0; index < Browselist.size(); ++index) {
                Hibernate.initialize(Browselist.get(index));
            }
            return Browselist;
        } finally {
            session.close();
        }
    }
}
