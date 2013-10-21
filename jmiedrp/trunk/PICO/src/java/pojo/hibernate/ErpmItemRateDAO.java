/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author SajidAziz, Tanvir Ahmed, Saeed
 */
package pojo.hibernate;

import java.util.Date;
import utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Hibernate;
import java.util.List;

/**
 *
 * @author dell
 */
public class ErpmItemRateDAO {

    public void save(ErpmItemRate itemrate) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(itemrate);
            tx.commit();
        } catch (RuntimeException re) {
            if (itemrate != null) {
                tx.rollback();
            }
            throw re;
        } finally {
            session.close();
        }
    }

    public void delete(ErpmItemRate itemrate) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(itemrate);
            tx.commit();
        } catch (RuntimeException re) {
            if (itemrate != null) {
                tx.rollback();
            }
            throw re;
        } finally {
            session.close();
        }
    }

    public void update(ErpmItemRate itemrate) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(itemrate);
            tx.commit();
        } catch (RuntimeException re) {
            if (itemrate != null) {
                tx.rollback();
            }
            throw re;
        } finally {
            session.close();
        }
    }

 public ErpmItemRate findByirItemRateId(Integer irItemRateId) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            ErpmItemRate ItemRate = (ErpmItemRate) session.load(ErpmItemRate.class, irItemRateId);
            Hibernate.initialize(ItemRate.getInstitutionmaster());
            Hibernate.initialize(ItemRate.getErpmItemMaster());
            Hibernate.initialize(ItemRate.getSuppliermaster());
            Hibernate.initialize(ItemRate.getErpmGenMasterByIrCurrencyId());
            
            return ItemRate;
        } finally {
            session.close();
        }
    }

public ErpmItemRate findItemRateId(Integer irItemRateId) {
        Session session = HibernateUtil.getSession();
        try {

            session.beginTransaction();
            List<ErpmItemRate> itemrate = session.createQuery("Select u from ErpmItemRate u where u.irItemRateId = :irItemRateId").setParameter("irItemRateId", irItemRateId).list();
            Hibernate.initialize(itemrate.get(0));
            Hibernate.initialize(itemrate.get(0).getInstitutionmaster());
            Hibernate.initialize(itemrate.get(0).getSuppliermaster());
            Hibernate.initialize(itemrate.get(0).getErpmGenMasterByIrCurrencyId());
            Hibernate.initialize(itemrate.get(0).getErpmGenMasterByIrWarrantyStartsFromId());            
            return itemrate.get(0);
        } finally {
            session.close();
        }
    }

    public List<ErpmItemRate> findItemRatesForInstitutionAndItem(Short imId, Integer erpmimId) {
        Session session = HibernateUtil.getSession();
        try {
            int index = 0;
            String SQL = "Select u from ErpmItemRate u "
                + "where u.institutionmaster.imId = :imId and u.erpmItemMaster.erpmimId = :erpmimId ";
            session.beginTransaction();
            List<ErpmItemRate> itemRateList = session.createQuery(SQL).setParameter("imId", imId).setParameter("erpmimId", erpmimId).list();
            for (index = 0; index < itemRateList.size(); ++index) {
                Hibernate.initialize(itemRateList.get(index).getErpmItemMaster());
                Hibernate.initialize(itemRateList.get(index).getSuppliermaster());
                Hibernate.initialize(itemRateList.get(index).getErpmGenMasterByIrCurrencyId());
            }
            return itemRateList;
        } finally {
            session.close();
        }
    }

//Find items with given item code whose prices have beeen approved in the given date range
 public List<ErpmItemRate> findItemApprovedItems(Integer ErpmimId, Date irdWefDate, Integer erpmgmEgmId, Integer smId) {
        Session session = HibernateUtil.getSession();
        try {
            String SQL = "Select u from ErpmItemRate u "
                + "where u.erpmItemMaster.erpmimId = :ErpmimId and "
                + "u.irdWefDate <= :irdWefDate and "
                + "u.irdWetDate >= :irdWefDate and "
                + "u.erpmGenMasterByIrCurrencyId.erpmgmEgmId = :erpmgmEgmId and "
                + "u.suppliermaster.smId = :smId";
            session.beginTransaction();
            List<ErpmItemRate> itemRateList = session.createQuery(SQL).setParameter("ErpmimId", ErpmimId).setParameter("irdWefDate", irdWefDate).
                                              setParameter("erpmgmEgmId", erpmgmEgmId).setParameter("smId", smId).list();

            return itemRateList;
        } finally {
            session.close();
        }
    }

 public List<ErpmItemRate> findApprovedItemRatesForToday(Integer ErpmimId, Date irdWefDate, String currency, Integer quantity) {
        Session session = HibernateUtil.getSession();
        try {
            int index = 0;
            String SQL = "Select u from ErpmItemRate u "
                + "where u.erpmItemMaster.erpmimId = :ErpmimId and "
                + "u.irdWefDate <= :irdWefDate and "
                + "u.irdWetDate >= :irdWefDate and "
                + "u.erpmGenMasterByIrCurrencyId.erpmgmEgmDesc = :currency and "
                + "u.irMinQty <= :quantity and "
                + "u.irMaxQty >= :quantity "
                + "order by u.irdRate";
            session.beginTransaction();
            List<ErpmItemRate> itemRateList = session.createQuery(SQL).
                setParameter("ErpmimId", ErpmimId).
                setParameter("currency", currency).
                setParameter("quantity", quantity).
                setTimestamp("irdWefDate", irdWefDate).list();
            for (index = 0; index < itemRateList.size(); ++index) {
                Hibernate.initialize(itemRateList.get(index).getErpmItemMaster());
                Hibernate.initialize(itemRateList.get(index).getSuppliermaster());

            }
            return itemRateList;
        } finally {
            session.close();
        }
    }
}
