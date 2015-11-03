/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo.hibernate;

import java.util.List;
import utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Hibernate;

/**
 *
 * @author afreen
 * @author <a href="mailto:jaivirpal@gmail.com">Jaivir Singh</a>2015
 */
public class SupplierAddressDAO {

    public void save(SupplierAddress supad) {
       Session session = HibernateUtil.getSessionPicoFactory();
        Transaction tx = null;
         try {
             tx = session.beginTransaction();
            session.save(supad);
             tx.commit();
        }
        catch (RuntimeException re) {
            if(supad != null)
                tx.rollback();
            throw re;
        }
         finally {
            session.close();
        }
    }

    public void update(SupplierAddress erpmsm) {
        Session session = HibernateUtil.getSessionPicoFactory();
        Transaction tx = null;
         try {
             tx = session.beginTransaction();
            session.update(erpmsm);
             tx.commit();
        }
        catch (RuntimeException re) {
            if(erpmsm != null)
                tx.rollback();
            throw re;
        }
         finally {
            session.close();
        }
    }

    public void delete(SupplierAddress erpmsm) {
        Session session = HibernateUtil.getSessionPicoFactory();
        Transaction tx = null;
         try {
             tx = session.beginTransaction();
            session.delete(erpmsm);
             tx.commit();
        }
        catch (RuntimeException re) {
            if(erpmsm != null)
                tx.rollback();
            throw re;
        }
         finally {
            session.close();
        }
    }

    public void deleteList(List<SupplierAddress> saList) {
        Session session = HibernateUtil.getSessionPicoFactory();
         try {
       session.beginTransaction();
           session.delete(saList);
            
         }
        finally {
            session.close();
            }
    }

    public void deleteSupplierAddresses(Integer smId) {
         Session session = HibernateUtil.getSessionPicoFactory();
         try {
       session.beginTransaction();
        session.createQuery("delete from SupplierAddress u where u.suppliermaster.smId = :smId").setParameter("smId", smId);
        
        return ;
 }
        finally {
            session.close();
            }
    }

    public List<SupplierAddress> findAll() {
       Session session = HibernateUtil.getSessionPicoFactory();
         try {
       session.beginTransaction();
        List<SupplierAddress> list = session.createQuery("from SupplierAddress ").list();
        
        return list;
        }
        finally {
            session.close();
            }
    }

    public List<SupplierAddress> findBySupplierId(Integer smId) {
       Session session = HibernateUtil.getSessionPicoFactory();
         try {
       session.beginTransaction();
        List<SupplierAddress> saList = session.createQuery("Select u from SupplierAddress u where u.suppliermaster.smId = :smId").setParameter("smId", smId).list();
        for(int index = 0; index < saList.size(); ++index) {
            Hibernate.initialize(saList.get(index).getCountrymaster());
            Hibernate.initialize(saList.get(index).getStatemaster());
            Hibernate.initialize(saList.get(index).getSuppliermaster());
        }

        return saList;
         }
        finally {
            session.close();
            }
    }

    public SupplierAddress findErpmSMId(Integer smId) {
         Session session = HibernateUtil.getSessionPicoFactory();
         try {
       session.beginTransaction();
        List<SupplierAddress> saList = session.createQuery("Select u from SupplierAddress  u where u.suppliermaster.smId = :smId").setParameter("smId", smId).list();

        return saList.get(0);
         }
        finally {
            session.close();
            }
    }

    public SupplierAddress findErpmAdIdSMId(Integer smId, Integer adId) {
        Session session = HibernateUtil.getSessionPicoFactory();
         try {
       session.beginTransaction();
        List<SupplierAddress> saList = session.createQuery("select u from SupplierAddress  u where  u.suppliermaster.smId = :smId and u.supAdId != :adId ").setParameter("smId", smId).setParameter("adId", adId).list();
        return saList.get(0);
}
        finally {
            session.close();
            }
    }

    public SupplierAddress findErpmAdId2SMId(Integer smId, Integer adId, Integer adId2) {
        Session session = HibernateUtil.getSessionPicoFactory();
         try {
       session.beginTransaction();
        List<SupplierAddress> saList = session.createQuery("select u from SupplierAddress  u where  u.suppliermaster.smId = :smId and u.supAdId != :adId and u.supAdId != :adId2  ").setParameter("smId", smId).setParameter("adId", adId).setParameter("adId2", adId2).list();
        return saList.get(0);
}
        finally {
            session.close();
            }
    }

    public int findNoOfSMId(Integer smId) {
       Session session = HibernateUtil.getSessionPicoFactory();
         try {
       session.beginTransaction();
        List<SupplierAddress> saList = session.createQuery("select u from SupplierAddress  u where  u.suppliermaster.smId = :smId   ").setParameter("smId", smId).list();
        return saList.size();
        }
        finally {
            session.close();
            }

    }

    public SupplierAddress findByErpmADId(Integer adId) {
        Session session = HibernateUtil.getSessionPicoFactory();
         try {
       session.beginTransaction();
        SupplierAddress supad = (SupplierAddress) session.load(SupplierAddress.class, adId);
        Hibernate.initialize(supad.getSuppliermaster());
        Hibernate.initialize(supad.getCountrymaster());
        return supad;
         }
        finally {
            session.close();
            }
    }
}
