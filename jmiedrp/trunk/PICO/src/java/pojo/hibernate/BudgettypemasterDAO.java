/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo.hibernate;

import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtil;

/**
 *
 * @author kazim
 */
public class BudgettypemasterDAO {

    public void save(Budgettypemaster btm) {
        //Session session = HibernateUtil.getSession();
        Session session = HibernateUtil.getSessionPicoFactory();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(btm);
            tx.commit();
        } catch (RuntimeException re) {
            if (btm != null) {
                tx.rollback();
            }
            throw re;
        } finally {
            session.close();
        }
    }

    public void update(Budgettypemaster btm) {
        Session session = HibernateUtil.getSessionPicoFactory();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(btm);
            tx.commit();
        } catch (RuntimeException re) {
            if (btm != null) {
                tx.rollback();
            }
            throw re;
        } finally {
            session.close();
        }
    }

    public void delete(Budgettypemaster btm) {
        Session session = HibernateUtil.getSessionPicoFactory();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(btm);
            tx.commit();
        } catch (RuntimeException re) {
            if (btm != null) {
                tx.rollback();
            }
            throw re;
        } finally {
            session.close();
        }
    }

    public List<Budgettypemaster> findAll() {
        Session session = HibernateUtil.getSessionPicoFactory();
        try {

            session.beginTransaction();
            List<Budgettypemaster> list = session.createQuery("select u from Budgettypemaster u").list();
//           
            return list;
        } finally {
            session.close();
        }
    }

    public Budgettypemaster findBybtmId(Byte btmId) {
        Session session = HibernateUtil.getSessionPicoFactory();
        try {
            session.beginTransaction();
            Budgettypemaster btm = (Budgettypemaster) session.load(Budgettypemaster.class, btmId);
            Hibernate.initialize(btmId);

            return btm;
        } finally {
            session.close();
        }
    }
}
