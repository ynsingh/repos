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
 * @author wml3
 * @author <a href="mailto:jaivirpal@gmail.com">Jaivir Singh</a>2015
 */
public class ErpmTenderScheduleDetailDAO {

    public void save(ErpmTenderScheduleDetail tscd) {
        Session session = HibernateUtil.getSessionPicoFactory();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(tscd);
            tx.commit();
        } catch (RuntimeException re) {
            if (tscd != null) {
                tx.rollback();
            }
            throw re;
        } finally {
            session.close();
        }
    }

    public void update(ErpmTenderScheduleDetail tscd) {
        Session session = HibernateUtil.getSessionPicoFactory();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(tscd);
            tx.commit();
        } catch (RuntimeException re) {
            if (tscd != null) {
                tx.rollback();
            }
            throw re;
        } finally {
            session.close();
        }
    }

    public void delete(ErpmTenderScheduleDetail tenschdlDet) {
        Session session = HibernateUtil.getSessionPicoFactory();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(tenschdlDet);
            tx.commit();
        } catch (RuntimeException re) {
            if (tenschdlDet != null) {
                tx.rollback();
            }
            throw re;
        } finally {
            session.close();
        }
    }

    public List<ErpmTenderScheduleDetail> findAll() {
        Session session = HibernateUtil.getSessionPicoFactory();
        try {
            int index = 0;
            session.beginTransaction();
            List<ErpmTenderScheduleDetail> list = session.createQuery("select u from ErpmTenderScheduleDetail u").list();
            for (index = 0; index < list.size(); ++index) {
                Hibernate.initialize(list.get(index).getErpmGenMaster());
                Hibernate.initialize(list.get(index).getErpmTenderSchedule());

            }
            return list;
        } finally {
            session.close();
        }
    }

    public List<ErpmTenderScheduleDetail> findbytscTscId(Integer tscTscId) {
        Session session = HibernateUtil.getSessionPicoFactory();
        try {
            int index = 0;
            session.beginTransaction();
            List<ErpmTenderScheduleDetail> list = session.createQuery("Select u from ErpmTenderScheduleDetail u where u.erpmTenderSchedule.tscTscId = :tscTscId").setParameter("tscTscId", tscTscId).list();
            for (index = 0; index < list.size(); ++index) {
                Hibernate.initialize(list.get(index).getErpmGenMaster());
                Hibernate.initialize(list.get(index).getErpmTenderSchedule());

            }
            return list;
        } finally {
            session.close();
        }
    }



    public ErpmTenderScheduleDetail findBytscdTscdId(Integer tscdTscdId) {
        Session session = HibernateUtil.getSessionPicoFactory();
        try {
//            int index = 0;
            session.beginTransaction();
            ErpmTenderScheduleDetail tenscdDet = (ErpmTenderScheduleDetail) session.load(ErpmTenderScheduleDetail.class, tscdTscdId);
            Hibernate.initialize(tenscdDet); //.getImName());
            Hibernate.initialize(tenscdDet.getErpmTenderSchedule());
            Hibernate.initialize(tenscdDet.getErpmTenderSchedule().getErpmTenderMaster());//.getImName());
            Hibernate.initialize(tenscdDet.getErpmGenMaster()); //.getImName());

            return tenscdDet;
        } finally {
            session.close();
        }
    }

    public List<ErpmTenderScheduleDetail> findByTenderNo_ScheduleNO (Integer tmTmId, Integer tscdTscdId ,int tscdScheduleNo) {
        Session session = HibernateUtil.getSessionPicoFactory();
        try {
            int index = 0;
            session.beginTransaction();
            List<ErpmTenderScheduleDetail> list = session.createQuery("Select u from ErpmTenderScheduleDetail u where u.erpmTenderSchedule.erpmTenderMaster.tmTmId = :tmTmId And u.tscdScheduleNo=:tscdScheduleNo And u.tscdTscdId != :tscdTscdId").setParameter("tmTmId", tmTmId).setParameter("tscdTscdId", tscdTscdId).setParameter("tscdScheduleNo", tscdScheduleNo).list();
            for (index = 0; index < list.size(); ++index) {
                Hibernate.initialize(list.get(index).getErpmTenderSchedule());
               
            }
            return list;
        } finally {
            session.close();
        }
    }

    public List<ErpmTenderScheduleDetail> findByTenderNo_ScheduleType(Integer tmTmId, Integer tscdTscdId ,Integer erpmgmEgmId) {
        Session session = HibernateUtil.getSessionPicoFactory();
        try {
            int index = 0;
            session.beginTransaction();
            List<ErpmTenderScheduleDetail> list = session.createQuery("Select u from ErpmTenderScheduleDetail u where u.erpmTenderSchedule.erpmTenderMaster.tmTmId = :tmTmId And u.erpmGenMaster.erpmgmEgmId=:erpmgmEgmId And u.tscdTscdId != :tscdTscdId").setParameter("tmTmId", tmTmId).setParameter("tscdTscdId", tscdTscdId).setParameter("erpmgmEgmId", erpmgmEgmId).list();
            for (index = 0; index < list.size(); ++index) {
                 Hibernate.initialize(list.get(index).getErpmTenderSchedule());
                Hibernate.initialize(list.get(index).getErpmGenMaster());

            }
            return list;
        } finally {
            session.close();
        }
    }

    public List<ErpmTenderScheduleDetail> findByTenderId(Integer tmTmId) {
        Session session = HibernateUtil.getSessionPicoFactory();
        try {
            int index = 0;
            session.beginTransaction();
            List<ErpmTenderScheduleDetail> list = session.createQuery("Select u from ErpmTenderScheduleDetail u where u.erpmTenderSchedule.erpmTenderMaster.tmTmId = :tmTmId").setParameter("tmTmId", tmTmId).list();
            for (index = 0; index < list.size(); ++index) {
                 Hibernate.initialize(list.get(index).getErpmTenderSchedule());
                 Hibernate.initialize(list.get(index).getErpmTenderSchedule().getErpmTenderMaster());
                Hibernate.initialize(list.get(index).getErpmGenMaster());

            }
            return list;
        } finally {
            session.close();
        }
    }

}
