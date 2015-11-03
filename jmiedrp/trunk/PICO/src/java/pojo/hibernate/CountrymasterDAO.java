/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


package pojo.hibernate;

import utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

/**
 *
 * @author afreen
 */

public class CountrymasterDAO  {


  public void save(Countrymaster con) {
        //Session session = HibernateUtil.getSession();
        Session session = HibernateUtil.getSessionPicoFactory();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(con);
            tx.commit();
        }
        catch (RuntimeException re) {
            if(con != null)
                tx.rollback();
            throw re;
        }
        finally {
            session.close();
        }
    }

    public List<Countrymaster> findAll() {        
        
        Session session = HibernateUtil.getSessionPicoFactory();

        try {
            session.beginTransaction();
            List<Countrymaster> list = session.createQuery("from Countrymaster").list();
            
            return list;
        }
        finally {
            session.close();
            }
        }
    

    public Byte findCountry (String countryName) {
        Session session = HibernateUtil.getSessionPicoFactory();

        try {
            session.beginTransaction();
            List<Countrymaster> list = session.createQuery("Select u from Countrymaster u where u.countryName = :countryName").setParameter("countryName",countryName).list();

            return list.get(0).getCountryId();
        }
        finally {
            session.close();
            }
        }
    }

//
//
///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//
//
//package pojo.hibernate;
//
//import utils.BaseDAO;
//import java.util.List;
//
///**
// *
// * @author afreen
// */
//
//public class CountrymasterDAO extends BaseDAO {
//
//
//  public void save(Countrymaster cun) {
//        try {
//            beginTransaction();
//            getSession().save(cun);
//            commitTransaction();
//        }
//        catch (RuntimeException re) {
//            re.printStackTrace();
//            throw re;
//        }
//    }
//
//    public List<Countrymaster> findAll() {
//        beginTransaction();
//        List<Countrymaster> list = getSession().createQuery("from Countrymaster").list();
//        commitTransaction();
//        return list;
//    }
//
//public Byte findCountry (String countryName) {
//        beginTransaction();
//        List<Countrymaster> list = getSession().createQuery("Select u from Countrymaster u where u.countryName = :countryName").setParameter("countryName",countryName).list();
//        commitTransaction();
//        return list.get(0).getCountryId();
//    }
//
//}
