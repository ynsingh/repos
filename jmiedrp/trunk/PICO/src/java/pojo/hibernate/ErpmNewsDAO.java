/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Arpan, Tanvir Ahmed, Saeed
 */

package pojo.hibernate;


import java.util.Date;
import utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Hibernate;
import java.util.List;
//import utils.BaseDAO;

//public class ErpmNewsDAO extends BaseDAO {
public class ErpmNewsDAO {

    public void save(ErpmNews erpmNews) {
        Session session = HibernateUtil.getSessionPicoFactory();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(erpmNews);
            tx.commit();
        } catch (RuntimeException re) {
            if (erpmNews != null) {
                tx.rollback();
            }
            throw re;
        } finally {
            session.close();
        }
    }

    public void update(ErpmNews erpmNews) {
        Session session = HibernateUtil.getSessionPicoFactory();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(erpmNews);
            tx.commit();
        } catch (RuntimeException re) {
            if (erpmNews != null) {
                tx.rollback();
            }
            throw re;
        } finally {
            session.close();
        }
    }

    public void delete(ErpmNews erpmNews) {
        Session session = HibernateUtil.getSessionPicoFactory();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(erpmNews);
            tx.commit();
        } catch (RuntimeException re) {
            if (erpmNews != null) {
                tx.rollback();
            }
            throw re;
        } finally {
            session.close();
        }
    }

    public ErpmNews findByNewsId(Integer imId) {
        Session session = HibernateUtil.getSessionPicoFactory();
        try {
            session.beginTransaction();
            ErpmNews nw  = new ErpmNews ();
  //          nw  = (ErpmNews) getSession().load(ErpmNews.class , imId);
            nw  = (ErpmNews) session.load(ErpmNews.class , imId);          
            Hibernate.initialize(nw);
            return nw;
        } finally {
            session.close();
        }
    }


    public List<ErpmNews> findForUser(Integer erpmuId) {
        Session session = HibernateUtil.getSessionPicoFactory();
        try {
//            int index = 0;
/*            session.beginTransaction();
           List<ErpmNews> imList = getSession().createQuery("select distinct(u) from ErpmNews u where u.erpmusers.erpmuId = :erpmuId Order By u.newsPublishDate desc").setParameter("erpmuId",erpmuId).list();*/
            session.beginTransaction();
            List<ErpmNews> imList = session.createQuery("select distinct(u) from ErpmNews u where u.erpmusers.erpmuId = :erpmuId Order By u.newsPublishDate desc").setParameter("erpmuId",erpmuId).list();
           Hibernate.initialize(imList);

//            for (index = 0; index < imList.size(); ++index) {
//                Hibernate.initialize(imList.get(index)..getErpmItemMaster());
//            }
           return imList;
        } finally {
            session.close();
        }
    }

    public List<ErpmNews> findbyDate(Date date) {
        Session session = HibernateUtil.getSessionPicoFactory();
        try {
//            int index = 0;
            session.beginTransaction();
//            List<ErpmNews> newsList = getSession().createQuery("select u from ErpmNews u where u.newsExpiryDate>= :date").setParameter("date", date).list();
            List<ErpmNews> newsList = session.createQuery("select u from ErpmNews u where u.newsExpiryDate>= :date").setParameter("date", date).list();            
            Hibernate.initialize(newsList);

            return newsList;
        } finally {
            session.close();
        }
    }
}
