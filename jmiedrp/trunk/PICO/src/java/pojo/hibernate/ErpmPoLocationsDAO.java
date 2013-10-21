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
 * @author kazim
 */
public class ErpmPoLocationsDAO extends BaseDAO {

    public void save(ErpmPoLocations polocation) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(polocation);
            tx.commit();
        }
        catch (RuntimeException re) {
            if(polocation != null)
                tx.rollback();
            throw re;
        }
        finally {
            session.close();
        }
    }

    public void delete(ErpmPoLocations polocation) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(polocation);
            tx.commit();
        }
        catch (RuntimeException re) {
            if(polocation != null)
                tx.rollback();
            throw re;
        }
        finally {
            session.close();
        }
    }

    public void update(ErpmPoLocations polocation) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(polocation);
            tx.commit();
        }
        catch (RuntimeException re) {
            if(polocation != null)
                tx.rollback();
            throw re;
        }
        finally {
            session.close();
        }
    }

    public ErpmPoLocations findBypoLocationsId(Integer poLocationsId) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            ErpmPoLocations poLocation  = (ErpmPoLocations) session.load(ErpmPoLocations.class , poLocationsId);
            Hibernate.initialize(poLocation.getDepartmentmaster());
            Hibernate.initialize(poLocation.getErpmItemMaster());
            return poLocation;
        }
        finally {
            session.close();
            }
    }

    public List<ErpmPoLocations> findByPO(Integer pomPoMasterId) {
        Session session = HibernateUtil.getSession();
        try {
            String SQL = "Select u from ErpmPoLocations u where u.erpmPoMaster.pomPoMasterId = :pomPoMasterId";
            session.beginTransaction();
            List<ErpmPoLocations> poLocations  = session.createQuery(SQL).setParameter("pomPoMasterId", pomPoMasterId).list();
            for(int index = 0; index < poLocations.size(); ++index) {
                Hibernate.initialize(poLocations.get(index).getDepartmentmaster());
            }
            return poLocations;
        }
        finally {
            session.close();
            }

    }

    public BigDecimal  findDistributedQty(Integer pomPoMasterId, Integer erpmimId) {
        Session session = HibernateUtil.getSession();
        try {
            String SQL =    "Select sum(v.qty)+0 from ErpmPoLocations v where "
                     +  "v.erpmPoMaster.pomPoMasterId = :pomPoMasterId and "
                     +  "v.erpmItemMaster.erpmimId = :erpmimId ";
            session.beginTransaction();
            BigDecimal qty   = new BigDecimal(session.createQuery(SQL)
                                                         .setParameter("pomPoMasterId", pomPoMasterId)
                                                         .setParameter("erpmimId", erpmimId)
                                                         .uniqueResult().toString());
            Hibernate.initialize(qty);
           return qty;
        }
        finally {
            session.close();
            }
    }

     public BigDecimal  findDistributedQtyMinusCurrent(Integer pomPoMasterId, Integer erpmimId, Integer poLocationsId ) {
        Session session = HibernateUtil.getSession();
        try {
            String SQL =    "Select sum(v.qty)+0 from ErpmPoLocations v where "
                     +  "v.erpmPoMaster.pomPoMasterId = :pomPoMasterId and "
                     +  "v.erpmItemMaster.erpmimId = :erpmimId and "
                     +  "v.poLocationsId != :poLocationsId";
            session.beginTransaction();
            BigDecimal qty   = new BigDecimal(session.createQuery(SQL)
                                                         .setParameter("pomPoMasterId", pomPoMasterId)
                                                         .setParameter("erpmimId", erpmimId)
                                                         .setParameter("poLocationsId", poLocationsId).uniqueResult().toString());
            Hibernate.initialize(qty);
           return qty;
        }
        finally {
            session.close();
            }

    }
}
