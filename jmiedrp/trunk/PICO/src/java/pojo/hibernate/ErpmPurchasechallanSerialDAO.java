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
 * @author erp05
 */
public class ErpmPurchasechallanSerialDAO{

     public void save(ErpmPurchasechallanSerial PCSerial) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(PCSerial);
            tx.commit();
        }
        catch (RuntimeException re) {
            if(PCSerial != null)
                tx.rollback();
            throw re;
        }
        finally {
            session.close();
        }
    }

     public void delete(ErpmPurchasechallanSerial PCSerial) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(PCSerial);
            tx.commit();
        }
        catch (RuntimeException re) {
            if(PCSerial != null)
                tx.rollback();
            throw re;
        }
        finally {
            session.close();
        }
    }

     public void update(ErpmPurchasechallanSerial PCSerial) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(PCSerial);
            tx.commit();
        }
        catch (RuntimeException re) {
            if(PCSerial != null)
                tx.rollback();
            throw re;
        }
        finally {
            session.close();
        }
    }
}
