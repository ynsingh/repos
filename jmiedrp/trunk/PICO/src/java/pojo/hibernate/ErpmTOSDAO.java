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
 * @author farah
 */

public class ErpmTOSDAO  {


// public void save(ErpmTempOpeningStock tos) {
//        try {
//            beginTransaction();
//            getSession().save(tos);
//            commitTransaction();
//            }
//        catch (RuntimeException re) {
//            re.printStackTrace();
//            throw re;
//
//        }
//
// }


    public void save(ErpmTempOpeningStock tos) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(tos);
            tx.commit();
        }
        catch (RuntimeException re) {
            if(tos != null)
                tx.rollback();
            throw re;

        }
        finally {
            session.close();
        }
    }


//    public void update(ErpmTempOpeningStock tos ) {
//        try {
//            beginTransaction();
//            getSession().update(tos);
//            commitTransaction();
//        }
//        catch (RuntimeException re) {
//            re.printStackTrace();
//            throw re;
//        }
//    }


      public void update(ErpmTempOpeningStock tos) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(tos);
            tx.commit();
        }
        catch (RuntimeException re) {
            if(tos != null)
                tx.rollback();
            throw re;
        }
        finally {
            session.close();
        }
    }

//    public void delete(ErpmTempOpeningStock tos) {
//        try {
//            beginTransaction();
//            getSession().delete(tos);
//            commitTransaction();
//        }
//        catch (RuntimeException re) {
//            re.printStackTrace();
//            throw re;
//        }
//    }

       public void delete(ErpmTempOpeningStock tos) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(tos);
            tx.commit();
        }
        catch (RuntimeException re) {
            if(tos != null)
                tx.rollback();
            throw re;
        }
        finally {
            session.close();
        }
    }

       
//    public List<ErpmTempOpeningStock> findAll() {
//        beginTransaction();
//        List<ErpmTempOpeningStock> list = getSession().createQuery("select u from ErpmTempOpeningStock u").list();
//        commitTransaction();
//        return list;
//    }


//          public List<ErpmTempOpeningStock> findAll() {
    public List<ErpmTempOpeningStock> findByImId(Short imId) {                 Session session = HibernateUtil.getSession();
        try {

            session.beginTransaction();
//            List<ErpmTempOpeningStock> list = session.createQuery("select u from ErpmTempOpeningStock u").list();
            List<ErpmTempOpeningStock> list = session.createQuery("select u from ErpmTempOpeningStock u where u.institutionmaster = :imId").setParameter(imId, imId).list();            
            for(int index = 0; index < list.size(); index++) {
                Hibernate.initialize(list.get(index).getErpmItemMaster());
                Hibernate.initialize(list.get(index).getSuppliermaster());
            }
            return list;
        }
        finally {
            session.close();
            }
    }

    public ErpmTempOpeningStock findBytosId(Integer tosId) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            ErpmTempOpeningStock tos = (ErpmTempOpeningStock)session.load(ErpmTempOpeningStock.class ,tosId);
            Hibernate.initialize(tos);

            return tos;
        }
        finally {
            session.close();
            }
    }

   public  List<ErpmTempOpeningStock> findByUser_NamesOnly(String erpmuName) {
        Session session = HibernateUtil.getSession();
        try {

            session.beginTransaction();
            List <ErpmTempOpeningStock> erpmuserList  =  session.createQuery("select u from ErpmTempOpeningStock u where u.tosBatchId=:erpmuName ").setParameter("erpmuName",erpmuName).list();
            for(int index = 0; index < erpmuserList.size(); index++) {
                Hibernate.initialize(erpmuserList.get(index).getErpmItemMaster());
                Hibernate.initialize(erpmuserList.get(index).getDepartmentmaster());
                Hibernate.initialize(erpmuserList.get(index).getErpmGenMaster());
                Hibernate.initialize(erpmuserList.get(index).getErpmItemMaster());
                Hibernate.initialize(erpmuserList.get(index).getInstitutionmaster());
                Hibernate.initialize(erpmuserList.get(index).getSubinstitutionmaster());
                Hibernate.initialize(erpmuserList.get(index).getSuppliermaster());
            }
            return erpmuserList;
        }
        finally {
            session.close();
            }
    }
 
}
