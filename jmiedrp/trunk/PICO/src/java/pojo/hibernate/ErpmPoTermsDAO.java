/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
 package pojo.hibernate;

import utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;
import org.hibernate.Hibernate;
import utils.BaseDAO;


/**
 *
 * @author dell
 */
public class ErpmPoTermsDAO extends BaseDAO {

    public void save(ErpmPoTerms epoterms) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(epoterms);
            tx.commit();
        }
        catch (RuntimeException re) {
            if(epoterms != null)
                tx.rollback();
            throw re;
        }
        finally {
            session.close();
        }
    }

     public void delete(ErpmPoTerms epoterms) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(epoterms);
            tx.commit();
        }
        catch (RuntimeException re) {
            if(epoterms != null)
                tx.rollback();
            throw re;
        }
        finally {
            session.close();
        }
    }

      public void update(ErpmPoTerms epoterms) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(epoterms);
            tx.commit();
        }
        catch (RuntimeException re) {
            if(epoterms != null)
                tx.rollback();
            throw re;
        }
        finally {
            session.close();
        }
    }

      public ErpmPoTerms findBypotPotIds(Integer potPotId){
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            ErpmPoTerms terms  = (ErpmPoTerms) session.load(ErpmPoTerms.class , potPotId);
            Hibernate.initialize(terms.getErpmGenMaster());
            return terms;
        }
        finally {
            session.close();
            }
  }

       public List<ErpmPoTerms>  findByPOMasterId(Integer pomPoMasterId) {
        Session session = HibernateUtil.getSession();
        try {
            
            session.beginTransaction();
           List<ErpmPoTerms> epoterms  = getSession().createQuery("Select u from ErpmPoTerms u where u.erpmPoMaster.pomPoMasterId = :pomPoMasterId").setParameter("pomPoMasterId", pomPoMasterId ).list();
            for(int index = 0; index < epoterms.size(); ++index) {
                Hibernate.initialize(epoterms.get(index).getErpmGenMaster());
                Hibernate.initialize(epoterms.get(index).getPotTermsDescription());

            }
            return epoterms;
        }
        finally {
            session.close();
            }
    }
}
