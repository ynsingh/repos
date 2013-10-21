/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo.hibernate;

import java.math.BigDecimal;
import utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;
import org.hibernate.Hibernate;
import utils.BaseDAO;

/**
 *
 * Author : Tanvir Ahmed & Saeed-uz-Zama
 */
public class ErpmPurchaseinvoiceMasterDAO {

    public void save(ErpmPurchaseinvoiceMaster erpmpim) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(erpmpim);
            tx.commit();
        } catch (RuntimeException re) {
            if (erpmpim != null) {
                tx.rollback();
            }
            throw re;
        } finally {
            session.close();
        }
    }

    public void update(ErpmPurchaseinvoiceMaster pibm) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(pibm);
            tx.commit();
        } catch (RuntimeException re) {
            if (pibm != null) {
                tx.rollback();
            }
            throw re;
        } finally {
            session.close();
        }
    }

    public void delete(ErpmPurchaseinvoiceMaster pibmtarget) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(pibmtarget);
            tx.commit();
        } catch (RuntimeException re) {
            if (pibmtarget != null) {
                tx.rollback();
            }
            throw re;
        } finally {
            session.close();
        }
    }

    public List<ErpmPurchaseinvoiceMaster> findByImId(Short imId) {
        Session session = HibernateUtil.getSession();
        try {
            int index;
            session.beginTransaction();
            List<ErpmPurchaseinvoiceMaster> list = session.createQuery("Select u from ErpmPurchaseinvoiceMaster u where u.institutionmaster.imId = :imId").setParameter("imId", imId).list();
            for (index = 0; index < list.size(); ++index) {
                Hibernate.initialize(list.get(index).getInstitutionmaster());
                Hibernate.initialize(list.get(index).getErpmPoMaster());
                Hibernate.initialize(list.get(index).getSubinstitutionmaster());
                Hibernate.initialize(list.get(index).getDepartmentmaster());
                Hibernate.initialize(list.get(index).getSuppliermaster());
                Hibernate.initialize(list.get(index).getErpmPurchasechallanMaster());
            }

            return list;
        } finally {
            session.close();
        }
    }

    public ErpmPurchaseinvoiceMaster findByErpmId(Integer pibmid) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            ErpmPurchaseinvoiceMaster pibm = new ErpmPurchaseinvoiceMaster();
            pibm = (ErpmPurchaseinvoiceMaster) session.load(ErpmPurchaseinvoiceMaster.class, pibmid);
            Hibernate.initialize(pibm);

            return pibm;
        } finally {
            session.close();
        }
    }

    public ErpmPurchaseinvoiceMaster findpimPimId(Integer pimPimId) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            List<ErpmPurchaseinvoiceMaster> list = session.createQuery("Select u from ErpmPurchaseinvoiceMaster u where u.pimPimId = :pimPimId").setParameter("pimPimId", pimPimId).list();
            if (list.size()>0) {
                Hibernate.initialize(list.get(0).getDepartmentmaster());
                Hibernate.initialize(list.get(0).getErpmPoMaster());
                Hibernate.initialize(list.get(0).getErpmPurchasechallanMaster());
                Hibernate.initialize(list.get(0).getInstitutionmaster());
                Hibernate.initialize(list.get(0).getSubinstitutionmaster());
                Hibernate.initialize(list.get(0).getSuppliermaster());
            }
            return list.get(0);
        } finally {
            session.close();
        }
    }

    public List<ErpmPurchaseinvoiceMaster> findByImId_SimId_SmId_SupplierInvoiceNo_pimPimId(Short imId, Integer simId, Integer smId, String pimSupplierInvoiceNo, Integer pimPimId) {
        Session session = HibernateUtil.getSession();
        try {

            session.beginTransaction();
            List<ErpmPurchaseinvoiceMaster> list = session.createQuery("Select u from ErpmPurchaseinvoiceMaster u where u.institutionmaster.imId = :imId and u.subinstitutionmaster.simId=:simId and u.suppliermaster.smId=:smId and u.pimSupplierInvoiceNo=:pimSupplierInvoiceNo and u.pimPimId !=:pimPimId").setParameter("imId", imId).setParameter("simId", simId).setParameter("smId", smId).setParameter("pimSupplierInvoiceNo", pimSupplierInvoiceNo).setParameter("pimPimId", pimPimId).list();
            for (int index = 0; index < list.size(); ++index) {
                Hibernate.initialize(list.get(index));
            }
            return list;
        } finally {
            session.close();
        }
    }

    public List<ErpmPurchaseinvoiceMaster> findBySupplierName_ChallanNo(Short imId, Integer simId, Integer pimPimId, Integer smId, Integer pcmPcmId) {
        Session session = HibernateUtil.getSession();
        try {
            int index = 0;
            session.beginTransaction();
            List<ErpmPurchaseinvoiceMaster> list = session.createQuery("Select u from ErpmPurchaseinvoiceMaster u where u.institutionmaster.imId = :imId and u.subinstitutionmaster.simId=:simId and  u.suppliermaster.smId = :smId And u.erpmPurchasechallanMaster.pcmPcmId = :pcmPcmId And u.pimPimId != :pimPimId").setParameter("imId", imId).setParameter("simId", simId).setParameter("pcmPcmId", pcmPcmId).setParameter("smId", smId).setParameter("pimPimId", pimPimId).list();
            for (index = 0; index < list.size(); ++index) {
                Hibernate.initialize(list.get(index));

            }
            return list;
        } finally {
            session.close();
        }
    }

    public List<ErpmPurchaseinvoiceMaster> findBySupplierName_SupplierInvoiceNo(Short imId, Integer simId, Integer pimPimId, Integer smId, String pimSupplierInvoiceNo) {
        Session session = HibernateUtil.getSession();
        try {
            int index = 0;
            session.beginTransaction();
            List<ErpmPurchaseinvoiceMaster> list = session.createQuery("Select u from ErpmPurchaseinvoiceMaster u where u.institutionmaster.imId = :imId and u.subinstitutionmaster.simId=:simId and u.suppliermaster.smId = :smId And u.pimSupplierInvoiceNo = :pimSupplierInvoiceNo And u.pimPimId != :pimPimId").setParameter("imId", imId).setParameter("simId", simId).setParameter("pimSupplierInvoiceNo", pimSupplierInvoiceNo).setParameter("smId", smId).setParameter("pimPimId", pimPimId).list();
            for (index = 0; index < list.size(); ++index) {
                Hibernate.initialize(list.get(index));

            }
            return list;
        } finally {
            session.close();
        }
    }
}
