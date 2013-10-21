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

/**
 *
 * @author erp03
 */
public class ErpmIssueReturnDetailDAO {

    public void save(ErpmIssueReturnDetail erpmird) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(erpmird);
            tx.commit();
        } catch (RuntimeException re) {
            if (erpmird != null) {
                tx.rollback();
            }
            throw re;
        } finally {
            session.close();
        }
    }

    public void update(ErpmIssueReturnDetail erpmird) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(erpmird);
            tx.commit();
        } catch (RuntimeException re) {
            if (erpmird != null) {
                tx.rollback();
            }
            throw re;
        } finally {
            session.close();
        }
    }

    public void delete(ErpmIssueReturnDetail erpmird) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(erpmird);
            tx.commit();
        } catch (RuntimeException re) {
            if (erpmird != null) {
                tx.rollback();
            }
            throw re;
        } finally {
            session.close();
        }
    }

    public List<ErpmIssueReturnDetail> findListByirmId(Integer irmId) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            List<ErpmIssueReturnDetail> list = session.createQuery("Select u from ErpmIssueReturnDetail u where u.erpmIssueReturnMaster.irmId = :irmId").setParameter("irmId", irmId).list();
            for (int index = 0; index < list.size(); ++index) {
                Hibernate.initialize(list.get(index).getErpmStockReceived());
                Hibernate.initialize(list.get(index).getErpmIssueMaster());
                Hibernate.initialize(list.get(index).getErpmItemMaster());
            }
            return list;
        } finally {
            session.close();
        }
    }
}
