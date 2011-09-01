/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo.hibernate;

import utils.BaseDAO;
import java.util.List;

/**
 *
 * @author afreen
 */
public class SupplierAddressDAO extends BaseDAO {

    public void save(SupplierAddress supad) {
        try {
            beginTransaction();
            getSession().save(supad);
            commitTransaction();
        } catch (RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
    }

    public void update(SupplierAddress erpmsm) {
        try {
            beginTransaction();
            getSession().update(erpmsm);
            commitTransaction();
        } catch (RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
    }

    public void delete(SupplierAddress erpmsm) {
        try {
            beginTransaction();
            getSession().delete(erpmsm);
            commitTransaction();
        } catch (RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
    }

    public void deleteList(List<SupplierAddress> saList) {
        try {
            beginTransaction();
            getSession().delete(saList);
            commitTransaction();
        } catch (RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
    }

    public void deleteSupplierAddresses(Integer smId) {
        beginTransaction();
        getSession().createQuery("delete from SupplierAddress u where u.suppliermaster.smId = :smId").setParameter("smId", smId);
        commitTransaction();
        return ;

    }

    public List<SupplierAddress> findAll() {
        beginTransaction();
        List<SupplierAddress> list = getSession().createQuery("from SupplierAddress ").list();
        commitTransaction();
        return list;
    }

    public List<SupplierAddress> findBySupplierId(Integer smId) {
        beginTransaction();
        List<SupplierAddress> saList = getSession().createQuery("Select u from SupplierAddress u where u.suppliermaster.smId = :smId").setParameter("smId", smId).list();
        commitTransaction();
        return saList;
    }

    public SupplierAddress findErpmSMId(Integer smId) {
        beginTransaction();
        List<SupplierAddress> saList = getSession().createQuery("Select u from SupplierAddress  u where u.suppliermaster.smId = :smId").setParameter("smId", smId).list();
        commitTransaction();
        return saList.get(0);
    }

    public SupplierAddress findErpmAdIdSMId(Integer smId, Integer adId) {
        beginTransaction();
        List<SupplierAddress> saList = getSession().createQuery("select u from SupplierAddress  u where  u.suppliermaster.smId = :smId and u.supAdId != :adId ").setParameter("smId", smId).setParameter("adId", adId).list();
        commitTransaction();
        return saList.get(0);

    }

    public SupplierAddress findErpmAdId2SMId(Integer smId, Integer adId, Integer adId2) {
        beginTransaction();
        List<SupplierAddress> saList = getSession().createQuery("select u from SupplierAddress  u where  u.suppliermaster.smId = :smId and u.supAdId != :adId and u.supAdId != :adId2  ").setParameter("smId", smId).setParameter("adId", adId).setParameter("adId2", adId2).list();
        commitTransaction();
        return saList.get(0);

    }

    public int findNoOfSMId(Integer smId) {
        beginTransaction();
        List<SupplierAddress> saList = getSession().createQuery("select u from SupplierAddress  u where  u.suppliermaster.smId = :smId   ").setParameter("smId", smId).list();
        commitTransaction();
        return saList.size();

    }

    public SupplierAddress findByErpmADId(Integer adId) {
        beginTransaction();
        SupplierAddress supad = (SupplierAddress) getSession().load(SupplierAddress.class, adId);
        commitTransaction();
        return supad;
    }
}
