/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
   /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
 /**
 *
 * @author SajidAziz
 */
package pojo.hibernate;

import utils.BaseDAO;
import java.util.List;
//import java.util.*;
import utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Hibernate;

public class ErpmItemRateDetailsDAO extends BaseDAO {

public void save(ErpmItemRateDetails itemratedet) {
	Session session = HibernateUtil.getSessionPicoFactory();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            //getSession().save(itemratedet);
            //commitTransaction();
            session.save(itemratedet);
	   tx.commit();	
        }
        /*catch (RuntimeException re) {
            re.printStackTrace();
            throw re;    }*/
	catch (RuntimeException re) {
            if (itemratedet != null) {
                tx.rollback();
            }
            throw re;
        } finally {
            session.close();
        }

    }
public void delete(ErpmItemRateDetails itemratedet) {
	Session session = HibernateUtil.getSessionPicoFactory();
        Transaction tx = null;
        try {
            tx=session.beginTransaction();
            session.delete(itemratedet);
            tx.commit();
        }
	catch (RuntimeException re) {
            if (itemratedet != null) {
                tx.rollback();
            }
            throw re;
        } finally {
            session.close();
        }
        /*catch (RuntimeException re) {
            re.printStackTrace();
            throw re;
        }*/
    }
 public void update(ErpmItemRateDetails itemratedet) {
	Session session = HibernateUtil.getSessionPicoFactory();
        Transaction tx = null;
        try {
            tx=session.beginTransaction();
            session.update(itemratedet);
            tx.commit();
        }
	catch (RuntimeException re) {
            if (itemratedet != null) {
                tx.rollback();
            }
            throw re;
        } finally {
            session.close();
        }
        /*catch (RuntimeException re) {
            re.printStackTrace();
            throw re;
        }*/
    }

public List<ErpmItemRateDetails> findAll() {
	Session session = HibernateUtil.getSessionPicoFactory();
        Transaction tx = null;
	try{	
        tx=session.beginTransaction();
        //List<ErpmItemRateDetails> list = getSession().createQuery("from ErpmItemRateDetails").list();
        List<ErpmItemRateDetails> list = session.createQuery("from ErpmItemRateDetails").list();
        //commitTransaction();
        return list;
	}
            catch (RuntimeException re) {
                throw re;
            }
            finally {
                session.close();
            }

    }

public ErpmItemRateDetails findByirdItemRateDetailsId(Integer irdItemRateDetailsId) {
	Session session = HibernateUtil.getSessionPicoFactory();
        Transaction tx = null;
	try{	
        tx=session.beginTransaction();
        //ErpmItemRateDetails ItemRatedetails  = (ErpmItemRateDetails) getSession().load(ErpmItemRateDetails.class , irdItemRateDetailsId);
        ErpmItemRateDetails ItemRatedetails  = (ErpmItemRateDetails) session.load(ErpmItemRateDetails.class , irdItemRateDetailsId);
        //commitTransaction();
        return ItemRatedetails;
	}
            catch (RuntimeException re) {
                throw re;
            }
            finally {
                session.close();
            }

    }



public ErpmItemRateDetails findIndentRateDetailId(Integer irdItemRateDetailsId) {
	Session session = HibernateUtil.getSessionPicoFactory();
        Transaction tx = null;
	try{	
        tx=session.beginTransaction();
        List<ErpmItemRateDetails> itemratedet  = session.createQuery("Select u from ErpmItemRateDetails u where u.erpmItemRate.irItemRateId = :irdItemRateDetailsId").setParameter("irdItemRateDetailsId",irdItemRateDetailsId).list();
        //commitTransaction();
        return itemratedet.get(0);
	}
            catch (RuntimeException re) {
                throw re;
            }
            finally {
                session.close();
            }

    }
}
