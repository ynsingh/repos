/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pojo.hibernate;

import java.util.List;
import utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Hibernate;
/**
 *
 * @author erp02
 * @author <a href="mailto:jaivirpal@gmail.com">Jaivir Singh</a>2015
 */
public class ViewStockReceivedDAO {
    public void save(ViewStockReceived viid) {
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

    public void update(ViewStockReceived viid) {
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

    public void delete(ViewStockReceived viid) {
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

    public List<ViewStockReceived> findAll() {
         Session session = HibernateUtil.getSessionPicoFactory();
         try {
       session.beginTransaction();
        List<ViewStockReceived> list = session.createQuery("select u from ViewStockReceived u").list();
        
        return list;
        }
        finally {
            session.close();
            }
    }

//     public List<ViewStockReceived> findByEimIdfromView(Integer eimId) {
//        beginTransaction();
//        List<ViewIssueIndentDetail> list = getSession().createQuery("Select u from ViewIssueIndentDetail u where u.ismId = :eimId").setParameter("eimId", eimId).list();
//        commitTransaction();
//        return list;
//    }
 public ViewStockReceived findByeidId(Integer vsrid) {
         Session session = HibernateUtil.getSessionPicoFactory();
         try {
       session.beginTransaction();
        ViewStockReceived esrId  = (ViewStockReceived) session.load(ViewStockReceived.class , vsrid);
        
        return esrId;
        }
        finally {
            session.close();
            }
    }

 public List<ViewStockReceived> findByPCDetailId(Integer pcDetailId) {
        Session session = HibernateUtil.getSessionPicoFactory();
         try {
       session.beginTransaction();
        List<ViewStockReceived> list = session.createQuery("Select u from ViewStockReceived u where u.stChallanDetId = :pcDetailId").setParameter("pcDetailId", pcDetailId).list();
        
        return list;
        }
        finally {
            session.close();
            }
    }

 public ViewStockReceived findByPCDId(Integer pcDetailId) {
         Session session = HibernateUtil.getSessionPicoFactory();
         try {
       session.beginTransaction();
        List<ViewStockReceived> list = session.createQuery("Select u from ViewStockReceived u where u.stChallanDetId = :pcDetailId").setParameter("pcDetailId", pcDetailId).list();
        
        return list.get(0);
        }
        finally {
            session.close();
            }
    }

        public ViewStockReceived findByVSRId(Integer stId) {
        Session session = HibernateUtil.getSessionPicoFactory();
         try {
       session.beginTransaction();
        List<ViewStockReceived> list = session.createQuery("Select u from ViewStockReceived u where u.stId = :stId").setParameter("stId", stId).list();
        
        return list.get(0);
        }
        finally {
            session.close();
            }
    }


 public List<ViewStockReceived> findByInvoiceNO_n_ItemId(String stInvoiceNo, int stItemId) {
         Session session = HibernateUtil.getSessionPicoFactory();
         try {
       session.beginTransaction();
        List<ViewStockReceived> stkreclist  = session.createQuery("Select u from ViewStockReceived u where u.stInvoiceNo = :stInvoiceNo and u.stItemId = :stItemId").setParameter("stInvoiceNo", stInvoiceNo).setParameter("stItemId", stItemId).list();
        
        return stkreclist;
        }
        finally {
            session.close();
            }
    }
}
