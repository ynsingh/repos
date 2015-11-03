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


public class ViewIssueIndentDetailDAO {
 public void save(ViewIssueIndentDetail viid) {
        Session session = HibernateUtil.getSessionPicoFactory();
        Transaction tx = null;
         try {
             tx = session.beginTransaction();
            session.save(viid);
           tx.commit();
        }
        catch (RuntimeException re) {
            if(viid != null)
                tx.rollback();
            throw re;
        }
         finally {
            session.close();
        }
    }

    public void update(ViewIssueIndentDetail viid) {
        Session session = HibernateUtil.getSessionPicoFactory();
        Transaction tx = null;
         try {
             tx = session.beginTransaction();
            session.update(viid);
            tx.commit();
        }
        catch (RuntimeException re) {
            if(viid != null)
                tx.rollback();
            throw re;
        }
         finally {
            session.close();
        }
    }

    public void delete(ViewIssueIndentDetail viid) {
        Session session = HibernateUtil.getSessionPicoFactory();
        Transaction tx = null;
         try {
             tx = session.beginTransaction();
            session.delete(viid);
           tx.commit();
        }
        catch (RuntimeException re) {
            if(viid != null)
                tx.rollback();
            throw re;
        }
         finally {
            session.close();
        }
    }

//    public List<ViewIssueIndentDetail> findAll() {
//       Session session = HibernateUtil.getSession();
//         try {
//       session.beginTransaction();
//        List<ViewIssueIndentDetail> list = session.createQuery("select u from ViewIssueIndentDetail u").list();
//       
//        return list;
//         }
//        finally {
//            session.close();
//            }
//    }

//     public List<ViewIssueIndentDetail> findByEimIdfromView(Integer eimId) {
//       Session session = HibernateUtil.getSession();
//         try {
//       session.beginTransaction();
//        List<ViewIssueIndentDetail> list = session.createQuery("Select u from ViewIssueIndentDetail u where u.ismId = :eimId").setParameter("eimId", eimId).list();
//       
//        return list;
//         }
//        finally {
//            session.close();
//            }
//    }
    
 public ViewIssueIndentDetail findByeidId(Integer eidId) {
       Session session = HibernateUtil.getSessionPicoFactory();
         try {
       session.beginTransaction();
        ViewIssueIndentDetail eidId1  = (ViewIssueIndentDetail) session.load(ViewIssueIndentDetail.class , eidId);
        Hibernate.initialize(eidId1);
        return eidId1;
         }
        finally {
            session.close();
            }
    }
}
