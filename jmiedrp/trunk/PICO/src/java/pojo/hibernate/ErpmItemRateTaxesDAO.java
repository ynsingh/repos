/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 *
 * @author Tanvir Ahmed, Saeed
 */
 
package pojo.hibernate;

import utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Hibernate;
import java.util.List;

public class ErpmItemRateTaxesDAO {

    public void save(ErpmItemRateTaxes itemratetax) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(itemratetax);
            tx.commit();
        } catch (RuntimeException re) {
            if (itemratetax != null) {
                tx.rollback();
            }
            throw re;
        } finally {
            session.close();
        }
    }

    public void delete(ErpmItemRateTaxes itemratetax) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(itemratetax);
            tx.commit();
        } catch (RuntimeException re) {
            if (itemratetax != null) {
                tx.rollback();
            }
            throw re;
        } finally {
            session.close();
        }
    }

    public void update(ErpmItemRateTaxes itemratetax) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(itemratetax);
            tx.commit();
        } catch (RuntimeException re) {
            if (itemratetax != null) {
                tx.rollback();
            }
            throw re;
        } finally {
            session.close();
        }
    }

    public List<ErpmItemRateTaxes>  findByirItemRateId(Integer irItemRateId) {
        Session session = HibernateUtil.getSession();
        try {
            int index = 0;
            session.beginTransaction();
            List<ErpmItemRateTaxes> itemratetaxList  = session.createQuery("Select u from ErpmItemRateTaxes u where u.erpmItemRate.irItemRateId = :irItemRateId").setParameter("irItemRateId", irItemRateId).list();
            for (index = 0; index < itemratetaxList.size(); ++index) {
                Hibernate.initialize(itemratetaxList.get(index).getErpmGenMaster());
                Hibernate.initialize(itemratetaxList.get(index).getErpmItemRate());
            }
            return itemratetaxList;
        } finally {
            session.close();
        }
    }

    public ErpmItemRateTaxes findByirtItemRateTaxesId(Integer irtItemRateTaxesId) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            ErpmItemRateTaxes itemratetax  = (ErpmItemRateTaxes) session.load(ErpmItemRateTaxes.class , irtItemRateTaxesId);
            Hibernate.initialize(itemratetax.getErpmItemRate());
            Hibernate.initialize(itemratetax.getErpmGenMaster());
            return itemratetax;
        } finally {
            session.close();
        }
    }
}

