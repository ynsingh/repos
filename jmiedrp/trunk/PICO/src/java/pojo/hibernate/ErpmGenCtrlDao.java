/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo.hibernate;

/**
 *
 * @author sknaqvi
 */
import utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import org.hibernate.Hibernate;

public class ErpmGenCtrlDao {

    public void save(ErpmGenCtrl erpmgctrl) {
        //Session session = HibernateUtil.getSession();
        Session session = HibernateUtil.getSessionPicoFactory();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(erpmgctrl);
            tx.commit();
        } catch (RuntimeException re) {
            if (erpmgctrl != null) {
                tx.rollback();
            }
            throw re;
        } finally {
            session.close();
        }
    }

    public void update(ErpmGenCtrl erpmgctrl) {
        Session session = HibernateUtil.getSessionPicoFactory();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(erpmgctrl);
            tx.commit();
        } catch (RuntimeException re) {
            if (erpmgctrl != null) {
                tx.rollback();
            }
            throw re;
        } finally {
            session.close();
        }
    }

    public void delete(ErpmGenCtrl erpmgctrl) {
        Session session = HibernateUtil.getSessionPicoFactory();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(erpmgctrl);
            tx.commit();
        } catch (RuntimeException re) {
            if (erpmgctrl != null) {
                tx.rollback();
            }
            throw re;
        } finally {
            session.close();
        }
    }

    public List<ErpmGenCtrl> findAll() {
        Session session = HibernateUtil.getSessionPicoFactory();
        try {
            int index = 0;
            session.beginTransaction();
            List<ErpmGenCtrl> list = session.createQuery("select u from ErpmGenCtrl u").list();
            for (index = 0; index < list.size(); ++index) {
                Hibernate.initialize(list.get(index).getErpmGenMasters());
            }
            return list;
        } finally {
            session.close();
        }
    }
}
