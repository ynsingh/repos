/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pojo.hibernate;

//import java.math.BigDecimal;
import utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;
import org.hibernate.Hibernate;
//import utils.BaseDAO;
/**
 *
 * @author erp01
 */
public class ErpmPurchaseinvoiceTaxesDAO {
  
//     public void save(ErpmPurchaseinvoiceTaxes pitax) {
//        try {
//            beginTransaction();
//            getSession().save(pitax);
//            commitTransaction();
//            }
//        catch (RuntimeException re) {
//            re.printStackTrace();
//            throw re;
//        }
// }
    public void save(ErpmPurchaseinvoiceTaxes pitax) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(pitax);
            tx.commit();
        }
        catch (RuntimeException re) {
            if(pitax != null)
                tx.rollback();
            throw re;
        }
        finally {
            session.close();
        }
    }


//    public void update(ErpmPurchaseinvoiceTaxes pitax ) {
//        try {
//            beginTransaction();
//            getSession().update(pitax);
//            commitTransaction();
//        }
//        catch (RuntimeException re) {
//            re.printStackTrace();
//            throw re;
//        }
//    }

//    public void delete(ErpmPurchaseinvoiceTaxes pitax) {
//        try {
//            beginTransaction();
//            getSession().delete(pitax);
//            commitTransaction();
//        }
//        catch (RuntimeException re) {
//            re.printStackTrace();
//            throw re;
//        }
//    }


    public void delete(ErpmPurchaseinvoiceTaxes pitax) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(pitax);
            tx.commit();
        }
        catch (RuntimeException re) {
            if(pitax != null)
                tx.rollback();
            throw re;
        }
        finally {
            session.close();
        }
    }


    
//    public List<ErpmPurchaseinvoiceTaxes>  findBypidPidId(Integer pidPidId) {
//        beginTransaction();
//        List<ErpmPurchaseinvoiceTaxes> piTaxlist  = getSession().createQuery("Select u from ErpmPurchaseinvoiceTaxes u where u.erpmPurchaseinvoiceDetail.pidPidId = :pidPidId").setParameter("pidPidId", pidPidId).list();
//        commitTransaction();
//       return piTaxlist;
//   }


     public List<ErpmPurchaseinvoiceTaxes>  findBypidPidId(Integer pidPidId) {
        Session session = HibernateUtil.getSession();
        try {

            session.beginTransaction();
          List<ErpmPurchaseinvoiceTaxes> piTaxlist  = session.createQuery("Select u from ErpmPurchaseinvoiceTaxes u where u.erpmPurchaseinvoiceDetail.pidPidId = :pidPidId").setParameter("pidPidId", pidPidId).list();
           for(int index = 0; index < piTaxlist.size(); index++) {
                Hibernate.initialize(piTaxlist);
            }
            return piTaxlist;
        }
        finally {
            session.close();
            }
    }
}
