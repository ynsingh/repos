package pojo.hibernate;

import utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

/**
 *@author <a href="mailto:jaivirpal@gmail.com">Jaivir Singh</a>2015
 */ 

public class StatemasterDAO {

    public void save(Statemaster state) {
        Session session = HibernateUtil.getSessionPicoFactory();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(state);
            tx.commit();
        }
        catch (RuntimeException re) {
            if(state != null)
                tx.rollback();
            throw re;
        }
        finally {
            session.close();
        }
    }

    public List<Statemaster> findAll() {
        Session session = HibernateUtil.getSessionPicoFactory();
        try {
            session.beginTransaction();
            List<Statemaster> list = session.createQuery("from Statemaster").list();
            return list;
        }
        finally {
            session.close();
            }
        }
    

    public List<Statemaster> findByCountryId(Byte countryId) {
        Session session = HibernateUtil.getSessionPicoFactory();
        try {
            session.beginTransaction();
            List<Statemaster> smList = session.createQuery("Select u from Statemaster u where u.countrymaster.countryId = :countryId").setParameter("countryId", countryId).list();
            return smList;
        }
        finally {
            session.close();
            }
        }

    public List<Statemaster> findByCountryName(String countryName) {        
        Session session = HibernateUtil.getSessionPicoFactory();
        try {
            session.beginTransaction();
            List<Statemaster> smList = session.createQuery("Select u from Statemaster u where u.countrymaster.countryName = :countryName").setParameter("countryName", countryName).list();
            return smList;
        }
        finally {
            session.close();
            }
        }

    }
//
//package pojo.hibernate;
//
//import utils.BaseDAO;
//import java.util.List;
//
//public class StatemasterDAO extends BaseDAO {
//
//    public void save(Statemaster state) {
//        try {
//            beginTransaction();
//            getSession().save(state);
//            commitTransaction();
//        } catch (RuntimeException re) {
//            re.printStackTrace();
//            throw re;
//        }
//    }
//
//    public List<Statemaster> findAll() {
//        beginTransaction();
//        List<Statemaster> list = getSession().createQuery("from Statemaster").list();
//        commitTransaction();
//        return list;
//    }
//
//    public List<Statemaster> findByCountryId(Byte countryId) {
//        beginTransaction();
//        List<Statemaster> smList = getSession().createQuery("Select u from Statemaster u where u.countrymaster.countryId = :countryId").setParameter("countryId", countryId).list();
//        commitTransaction();
//        return smList;
//    }
//
//    public List<Statemaster> findByCountryName(String countryName) {
//        beginTransaction();
//        List<Statemaster> smList = getSession().createQuery("Select u from Statemaster u where u.countrymaster.countryName = :countryName").setParameter("countryName", countryName).list();
//        commitTransaction();
//        return smList;
//    }
//
//}
