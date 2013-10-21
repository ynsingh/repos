/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pojo.hibernate;

/**
 *
 * @author tanvir, saeed, manauwar
 */

import java.math.BigDecimal;
import utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Hibernate;
import java.util.List;
import org.hibernate.SessionFactory;
import utils.BaseDAO;


public class ErpmIssueDetailDAO extends BaseDAO{

    public void save(ErpmIssueDetail eid) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(eid);
            tx.commit();
        } catch (RuntimeException re) {
            if (eid != null) {
                tx.rollback();
            }
            throw re;
        } finally {
            session.close();
        }
    }

    public void update(ErpmIssueDetail eid) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.merge(eid);
            tx.commit();
        } catch (RuntimeException re) {
            if (eid != null) {
                tx.rollback();
            }
            throw re;
        } finally {
            session.close();
        }
    }


     public void delete(ErpmIssueDetail eid) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(eid);
            tx.commit();
        } catch (RuntimeException re) {
            if (eid != null) {
                tx.rollback();
            }
            throw re;
        } finally {
            session.close();
        }
    }

//     public List<ErpmIssueDetail> findAll() {
//        Session session = HibernateUtil.getSession();
//        try {
//            int index = 0;
//            session.beginTransaction();
//            List<ErpmIssueDetail> list = session.createQuery("select u from ErpmIssueDetail u").list();
//            for (index = 0; index < list.size(); ++index) {
//                Hibernate.initialize(list.get(index).getErpmIssueMaster());
//                Hibernate.initialize(list.get(index).getErpmItemMaster());
//            }
//            return list;
//        } finally {
//            session.close();
//        }
//    }

     public List<ErpmIssueDetail> findByEimId(Integer eimId) {
        Session session = HibernateUtil.getSession();
        try {
            int index = 0;
            session.beginTransaction();
            List<ErpmIssueDetail> list = session.createQuery("Select u from ErpmIssueDetail u where u.erpmIssueMaster.ismId = :eimId").setParameter("eimId", eimId).list();
            for (index = 0; index < list.size(); ++index) {
                Hibernate.initialize(list.get(index).getErpmItemMaster());
                Hibernate.initialize(list.get(index).getErpmItemMaster().getErpmGenMaster());
            }
            return list;
        } finally {
            session.close();
        }
    }

     public List<ErpmIssueDetail> findByIssueMastId(Integer issueMastId ) {
        Session session = HibernateUtil.getSession();
        try {
            int index = 0;
            session.beginTransaction();
            List<ErpmIssueDetail> list = session.createQuery("select u from ErpmIssueDetail u where u.erpmIssueMaster.ismId = :issueMastId").setParameter("issueMastId", issueMastId).list();
            for (index = 0; index < list.size(); ++index) {
                Hibernate.initialize(list.get(index).getErpmItemMaster());
                Hibernate.initialize(list.get(index).getErpmItemMaster().getErpmGenMaster());
            }
            return list;
        } finally {
            session.close();
        }
    }

     public ErpmIssueDetail findByeidId(Integer eidId) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            ErpmIssueDetail eidId1  = (ErpmIssueDetail) session.load(ErpmIssueDetail.class , eidId);
            Hibernate.initialize(eidId1.getErpmItemMaster());
            Hibernate.initialize(eidId1.getErpmIssueMaster());
            Hibernate.initialize(eidId1.getErpmIssueMaster().getErpmIndentMaster());
            return eidId1;
        } finally {
            session.close();
        }
    }

     public ErpmIssueDetail findisdId(Integer isdId) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            List<ErpmIssueDetail> list  = session.createQuery("Select u from ErpmIssueDetail u where u.isdId = :isdId").setParameter("isdId",isdId).list();
            Hibernate.initialize(list.get(0).getErpmItemMaster());
            Hibernate.initialize(list.get(0).getErpmIssueMaster());
            return list.get(0);
        } finally {
            session.close();
        }
    }

      public List<ErpmIssueDetail> findByEimIdfromView(Integer eimId) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            List<ErpmIssueDetail> list = session.createQuery("Select u from ViewIssueIndentDetail u where u.ismId = :eimId").setParameter("eimId", eimId).list();
            Hibernate.initialize(list); //.getImName());

            return list;
        } finally {
            session.close();
        }
    }

      
//     public Integer findCountByIssueMasterAndItemId(Integer ismId, Integer itemId){
//         beginTransaction();
//        String list = getSession().createQuery("select count(u) from ErpmIssueDetail u where u.erpmIssueMaster.ismId = :ismId and u.erpmItemMaster.erpmimId = :itemId").setParameter("ismId", ismId).setParameter("itemId", itemId).uniqueResult().toString();
//        commitTransaction();
//        return Integer.parseInt(list);
//    }


        public Integer findCountByIssueMasterAndItemId(Integer ismId, Integer itemId) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            String list = session.createQuery("select count(u) from ErpmIssueDetail u where u.erpmIssueMaster.ismId = :ismId and u.erpmItemMaster.erpmimId = :itemId").setParameter("ismId", ismId).setParameter("itemId", itemId).uniqueResult().toString();
            return Integer.parseInt(list);
        } finally {
            session.close();
        }
    }

     public ErpmIssueDetail findByisdId(Integer isdId) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            List<ErpmIssueDetail> list  = session.createQuery("Select u from ErpmIssueDetail u where u.isdId = :isdId").setParameter("isdId",isdId).list();
            Hibernate.initialize(list);
            return list.get(0);
        } finally {
            session.close();
        }
    }

}
