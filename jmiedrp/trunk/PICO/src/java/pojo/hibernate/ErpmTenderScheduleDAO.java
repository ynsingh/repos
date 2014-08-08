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
 * @author Saeed
 */
public class ErpmTenderScheduleDAO {

    public void save(ErpmTenderSchedule tsc) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(tsc);
            tx.commit();
        } catch (RuntimeException re) {
            if (tsc != null) {
                tx.rollback();
            }
            throw re;
        } finally {
            session.close();
        }
    }

    public void update(ErpmTenderSchedule tenschdl) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(tenschdl);
            tx.commit();
        } catch (RuntimeException re) {
            if (tenschdl != null) {
                tx.rollback();
            }
            throw re;
        } finally {
            session.close();
        }
    }

    public void delete(ErpmTenderSchedule tenschdl) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(tenschdl);
            tx.commit();
        } catch (RuntimeException re) {
            if (tenschdl != null) {
                tx.rollback();
            }
            throw re;
        } finally {
            session.close();
        }
    }

//    public List<ErpmTenderSchedule> findAll() {
    public List<ErpmTenderSchedule> findByImId(Short ImId) {    
        Session session = HibernateUtil.getSession();
        try {
            int index = 0;
            session.beginTransaction();
           // List<ErpmTenderSchedule> list = session.createQuery("select u from ErpmTenderSchedule u").list();
           List<ErpmTenderSchedule> list = session.createQuery("Select u from ErpmTenderSchedule u where u.institutionmaster.imId = :ImId").setParameter("ImId", ImId).list();
            for (index = 0; index < list.size(); ++index) {
                Hibernate.initialize(list.get(index).getInstitutionmaster());
                Hibernate.initialize(list.get(index).getSubinstitutionmaster());
                Hibernate.initialize(list.get(index).getDepartmentmaster());
              //  Hibernate.initialize(list.get(index).getErpmTenderScheduleDetails());
                Hibernate.initialize(list.get(index).getErpmTenderMaster());

            }
            return list;
        } finally {
            session.close();
        }
    }

    public ErpmTenderSchedule findBytscTscId(Integer tscTscId) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            ErpmTenderSchedule tenscd = (ErpmTenderSchedule) session.load(ErpmTenderSchedule.class, tscTscId);
            //Hibernate.initialize(tenscd); //.getImName());
            Hibernate.initialize(tenscd.getInstitutionmaster());
            Hibernate.initialize(tenscd.getSubinstitutionmaster());
            Hibernate.initialize(tenscd.getDepartmentmaster()); 
            Hibernate.initialize(tenscd.getErpmTenderMaster());

            return tenscd;
        } finally {
            session.close();
        }
    }



    public List<ErpmTenderSchedule> findByTenderMasterId(Integer TenderMasterId, Integer TenderScheduleId) {
        Session session = HibernateUtil.getSession();
        try {
            int index = 0;
            session.beginTransaction();
            List<ErpmTenderSchedule> list = session.createQuery("Select u from ErpmTenderSchedule u where u.erpmTenderMaster.tmTmId = :TenderMasterId And u.tscTscId != :TenderScheduleId").setParameter("TenderMasterId", TenderMasterId).setParameter("TenderScheduleId", TenderScheduleId).list();
            for (index = 0; index < list.size(); ++index) {
                Hibernate.initialize(list.get(index).getErpmTenderMaster());

            }
            return list;
        } finally {
            session.close();
        }
    }

    public ErpmTenderSchedule findByTenderMasterId(Integer TenderMasterId) {
        Session session = HibernateUtil.getSession();
        try {
           
            session.beginTransaction();
            List<ErpmTenderSchedule> teno = session.createQuery("Select u from ErpmTenderSchedule u where u.erpmTenderMaster.tmTmId = :TenderMasterId ").setParameter("TenderMasterId", TenderMasterId).list();
            
             Hibernate.initialize(teno.get(0).getErpmTenderMaster());   
            return teno.get(0);
        } finally {
            session.close();
        }
    }

}
