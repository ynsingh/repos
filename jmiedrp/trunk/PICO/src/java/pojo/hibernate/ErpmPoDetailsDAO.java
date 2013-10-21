/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pojo.hibernate;

import java.math.BigDecimal;
import java.util.Date;
import utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Hibernate;
import java.util.List;
//import utils.BaseDAO;

/**
 *
 * @author kazim
 */

public class ErpmPoDetailsDAO {

    public void save(ErpmPoDetails podetail) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(podetail);
            tx.commit();
        } catch (RuntimeException re) {
            if (podetail != null) {
                tx.rollback();
            }
            throw re;
        } finally {
            session.close();
        }
    }

    public void delete(ErpmPoDetails podetail) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(podetail);
            tx.commit();
        } catch (RuntimeException re) {
            if (podetail != null) {
                tx.rollback();
            }
            throw re;
        } finally {
            session.close();
        }
    }

    public void update(ErpmPoDetails podetail) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(podetail);
            tx.commit();
        } catch (RuntimeException re) {
            if (podetail != null) {
                tx.rollback();
            }
            throw re;
        } finally {
            session.close();
        }
    }

   
    public List<ErpmPoDetails>  findBypomPoMasterId(Integer pomPoMasterId) {
        Session session = HibernateUtil.getSession();
        try {
            int index = 0;

            session.beginTransaction();
           List<ErpmPoDetails> podetailslist  = session.createQuery("Select u from ErpmPoDetails u where u.erpmPoMaster.pomPoMasterId = :pomPoMasterId").setParameter("pomPoMasterId", pomPoMasterId).list();
            for (index = 0; index < podetailslist.size(); ++index) {
                Hibernate.initialize(podetailslist.get(index).getErpmItemMaster());
                if (podetailslist.get(index).getErpmIndentDetail()!=null)
                    Hibernate.initialize(podetailslist.get(index).getErpmIndentDetail().getErpmIndentMaster());    //  Here is the problem
                Hibernate.initialize(podetailslist.get(index).getErpmPoMaster().getInstitutionmaster());
            }
            return podetailslist;
        } finally {
            session.close();
        }
    }
//  public List<ErpmPoDetails>  findBypomPoMasterId(Integer pomPoMasterId) {
//        beginTransaction();
//        List<ErpmPoDetails> podetailslist  = getSession().createQuery("Select u from ErpmPoDetails u where u.erpmPoMaster.pomPoMasterId = :pomPoMasterId").setParameter("pomPoMasterId", pomPoMasterId).list();
//        commitTransaction();
//       return podetailslist;
//   }

    public List<ErpmPoDetails>  findItemListByPoMasterId(Integer pomPoMasterId) {
        Session session = HibernateUtil.getSession();
        try {
            int index = 0;

            session.beginTransaction();
           List<ErpmPoDetails> podetailslist  = session.createQuery("Select u from ErpmPoDetails u where u.erpmPoMaster.pomPoMasterId = :pomPoMasterId").setParameter("pomPoMasterId", pomPoMasterId).list();
            for (index = 0; index < podetailslist.size(); ++index) {
                Hibernate.initialize(podetailslist.get(index).getErpmItemMaster());
                Hibernate.initialize(podetailslist.get(index).getErpmIndentDetail());
                Hibernate.initialize(podetailslist.get(index).getErpmPoMaster().getInstitutionmaster());
            }
            return podetailslist;
        } finally {
            session.close();
        }
    }

    public ErpmPoDetails findByPODetailsID(Integer podPodetailsId) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            ErpmPoDetails podetail  = (ErpmPoDetails) session.load(ErpmPoDetails.class , podPodetailsId);
            Hibernate.initialize(podetail.getErpmPoMaster());
            Hibernate.initialize(podetail.getErpmIndentDetail());
            if (podetail.getErpmIndentDetail()!=null) {
                Hibernate.initialize(podetail.getErpmIndentDetail().getErpmIndentMaster());
                Hibernate.initialize(podetail.getErpmIndentDetail().getErpmItemRate());
                Hibernate.initialize(podetail.getErpmIndentDetail().getErpmItemRate().getErpmGenMasterByIrCurrencyId());
            }
            Hibernate.initialize(podetail.getErpmItemMaster());
            Hibernate.initialize(podetail.getErpmItemMaster().getErpmGenMaster());
            Hibernate.initialize(podetail.getErpmItemRate());
            if (podetail.getErpmItemRate()!=null) {
                Hibernate.initialize(podetail.getErpmItemRate().getErpmGenMasterByIrCurrencyId());
            }
            return podetail;
        } finally {
            session.close();
        }
    }

    public BigDecimal  findQtyOfItemInPO(Integer pomPoMasterId, Integer erpmimId) {
        Session session = HibernateUtil.getSession();
        try {

             String SQL =  "Select sum(u.podQuantity)+0 from ErpmPoDetails u where "
                     + "u.erpmPoMaster.pomPoMasterId = :pomPoMasterId and "
                     + "u.erpmItemMaster.erpmimId = :erpmimId";
            session.beginTransaction();
            BigDecimal qty   = new BigDecimal(session.createQuery(SQL)
                                                         .setParameter("pomPoMasterId", pomPoMasterId)
                                                         .setParameter("erpmimId", erpmimId).uniqueResult().toString());
             Hibernate.initialize(qty);

            return qty;
        } finally {
            session.close();
        }
    }

     public ErpmPoDetails findBy_pomPoMasterId_ItemId(Integer pomPoMasterId, Integer itemId) {
        Session session = HibernateUtil.getSession();
        try {

            session.beginTransaction();
            List<ErpmPoDetails> list  = session.createQuery("Select u from ErpmPoDetails u where u.erpmPoMaster.pomPoMasterId = :pomPoMasterId and u.erpmItemMaster.erpmimId = :itemId").setParameter("itemId", itemId).setParameter("pomPoMasterId", pomPoMasterId).list();
             Hibernate.initialize(list);

            return list.get(0);
        } finally {
            session.close();
        }
    }

   }
