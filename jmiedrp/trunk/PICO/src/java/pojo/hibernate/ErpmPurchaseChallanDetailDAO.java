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
 * @author Tanvir Ahmed and Sajid
 */

public class ErpmPurchaseChallanDetailDAO {

    public void save(ErpmPurchasechallanDetail PCDetail) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(PCDetail);
            tx.commit();
        }
        catch (RuntimeException re) {
            if(PCDetail != null)
                tx.rollback();
            throw re;
        }
        finally {
            session.close();
        }
    }

    public void delete(ErpmPurchasechallanDetail PCDetail) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(PCDetail);
            tx.commit();
        }
        catch (RuntimeException re) {
            if(PCDetail != null)
                tx.rollback();
            throw re;
        }
        finally {
            session.close();
        }
    }

    public void update(ErpmPurchasechallanDetail PCDetail) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(PCDetail);
            tx.commit();
        }
        catch (RuntimeException re) {
            if(PCDetail != null)
                tx.rollback();
            throw re;
        }
        finally {
            session.close();
        }
    }

    public List<ErpmPurchasechallanDetail> findBypcmPcmId(Integer pcmPcmId) {
        Session session = HibernateUtil.getSession();
        try {

            session.beginTransaction();
           List<ErpmPurchasechallanDetail> podetailslist  = session.createQuery("Select u from ErpmPurchasechallanDetail u where u.erpmPurchasechallanMaster.pcmPcmId = :pcmPcmId").setParameter("pcmPcmId", pcmPcmId).list();
           for(int index = 0; index < podetailslist.size(); ++index) {
                Hibernate.initialize(podetailslist.get(index).getErpmItemMaster());
            }
            return podetailslist;
        }
        finally {
            session.close();
            }
    }

     public ErpmPurchasechallanDetail findBypcdPcdId(Integer pcdPcdId) {
        Session session = HibernateUtil.getSession();
        try {

            session.beginTransaction();
            List<ErpmPurchasechallanDetail> PCDetail  = session.createQuery("Select u from ErpmPurchasechallanDetail u where u.pcdPcdId = :pcdPcdId").setParameter("pcdPcdId",pcdPcdId).list();
             for(int index = 0; index < PCDetail.size(); ++index) {
                Hibernate.initialize(PCDetail.get(index).getErpmItemMaster());
                Hibernate.initialize(PCDetail.get(index).getErpmPurchasechallanMaster());
            }
           return PCDetail.get(0);
        }
        finally {
            session.close();
            }
    }

      public ErpmPurchasechallanDetail findByPCDetailsID(Integer pcdPcdId) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            ErpmPurchasechallanDetail PCDetail  = (ErpmPurchasechallanDetail) session.load(ErpmPurchasechallanDetail.class , pcdPcdId);
            Hibernate.initialize(PCDetail.getErpmItemMaster());
            Hibernate.initialize(PCDetail.getErpmPurchasechallanMaster());

            return PCDetail;
        }
        finally {
            session.close();
            }
    }

      public List<ErpmPurchasechallanDetail>  findBy_pomPoMasterId_ItemId_chalanId(Integer pomPoMasterId, Integer itemId, Integer pcdPcdId) {
        Session session = HibernateUtil.getSession();
        try {

            session.beginTransaction();
           List<ErpmPurchasechallanDetail> Browselist  = session.createQuery("Select u from ErpmPurchasechallanDetail u where u.pcdPcdId != :pcdPcdId and u.erpmItemMaster.erpmimId = :itemId and u.erpmPurchasechallanMaster.erpmPoMaster.pomPoMasterId = :pomPoMasterId").setParameter("itemId", itemId).setParameter("pomPoMasterId", pomPoMasterId).setParameter("pcdPcdId", pcdPcdId).list();
           for(int index = 0; index < Browselist.size(); ++index) {
                Hibernate.initialize(Browselist.get(index));
            }
            return Browselist;
        }
        finally {
            session.close();
            }
    }

      public ErpmPurchasechallanDetail findBypcmPcmId_n_ItemId(Integer pcmPcmId, Integer ItemId) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
           List<ErpmPurchasechallanDetail> chadetails  = session.createQuery("Select u from ErpmPurchasechallanDetail u where u.erpmPurchasechallanMaster.pcmPcmId = :pcmPcmId and u.erpmItemMaster.erpmimId = :ItemId").setParameter("pcmPcmId", pcmPcmId).setParameter("ItemId", ItemId).list();
            Hibernate.initialize(chadetails);


            return chadetails.get(0);
        }
        finally {
            session.close();
            }
    }

}
