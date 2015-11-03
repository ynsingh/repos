/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pojo.hibernate;

/**
 *
 * @author erp02
 * @author <a href="mailto:jaivirpal@gmail.com">Jaivir Singh</a>2015
 */
import java.util.List;
import utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Hibernate;



public class ViewIssueSerialDetailDAO {
 public void save(ViewIssueSerialDetail visd) {
       Session session = HibernateUtil.getSessionPicoFactory();
        Transaction tx = null;
         try {
             tx = session.beginTransaction();
            session.save(visd);
             tx.commit();
        }
        catch (RuntimeException re) {
            if(visd != null)
                tx.rollback();
            throw re;
        }
         finally {
            session.close();
        }
    }

    public void update(ViewIssueSerialDetail visd) {
       Session session = HibernateUtil.getSessionPicoFactory();
        Transaction tx = null;
         try {
             tx = session.beginTransaction();
            session.update(visd);
             tx.commit();
        }
        catch (RuntimeException re) {
            if(visd != null)
                tx.rollback();
            throw re;
        }
         finally {
            session.close();
        }
    }

    public void delete(ViewIssueSerialDetail visd) {
       Session session = HibernateUtil.getSessionPicoFactory();
        Transaction tx = null;
         try {
             tx = session.beginTransaction();
            session.delete(visd);
             tx.commit();
        }
        catch (RuntimeException re) {
            if(visd != null)
                tx.rollback();
            throw re;
        }
         finally {
            session.close();
        }
    }

    public List<ViewIssueSerialDetail> findAll() {
        Session session = HibernateUtil.getSessionPicoFactory();
         try {
       session.beginTransaction();
        List<ViewIssueSerialDetail> list = session.createQuery("select u from ViewIssueSerialDetail u").list();
       
        return list;
         }
        finally {
            session.close();
            }
    }

     public List<ViewIssueSerialDetail> findByEimIdfromView(Integer eimId) {
         Session session = HibernateUtil.getSessionPicoFactory();
         try {
       session.beginTransaction();
        List<ViewIssueSerialDetail> list = session.createQuery("Select u from ViewIssueSerialDetail u where ((u.issdId = 0 AND u.isdReceivedQuantity > 0 AND u.isdReceivedQuantity > u.isdReturnedQuantity) OR (u.issdId > 0 AND u.issdReceived = 1 AND u.issdReturned = 0)) AND u.isdIsmId = :eimId").setParameter("eimId", eimId).list();
        
        return list;
         }
        finally {
            session.close();
            }
    }
 public ViewIssueSerialDetail findByeidId(Integer eidId) {
         Session session = HibernateUtil.getSessionPicoFactory();
         try {
       session.beginTransaction();
        ViewIssueSerialDetail eidId1  = (ViewIssueSerialDetail) session.load(ViewIssueSerialDetail.class , eidId);
        
        return eidId1;
         }
        finally {
            session.close();
            }
    }
  public ViewIssueSerialDetail findByissdId(Long issdId) {
      
        Session session = HibernateUtil.getSessionPicoFactory();
         try {

         issdId=new Long(20);
       session.beginTransaction();
        List<ViewIssueSerialDetail> list = session.createQuery("Select u from ViewIssueSerialDetail u where u.issdId = :issdId").setParameter("issdId", issdId).list();
        
        return list.get(0);
         }
        finally {
            session.close();
            }
    }
  

}
