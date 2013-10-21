/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 *
 * @author Tanvir Ahmed
 */

package pojo.hibernate;


import java.math.BigDecimal;
import utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;
import org.hibernate.Hibernate;
import utils.BaseDAO;

public class ErpmPurchaseChallanMasterDAO {

    public void save(ErpmPurchasechallanMaster PChallanMast) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(PChallanMast);
            tx.commit();
        }
        catch (RuntimeException re) {
            if(PChallanMast != null)
                tx.rollback();
            throw re;
        }
        finally {
            session.close();
        }
    }

    public void delete(ErpmPurchasechallanMaster PChallanMast) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(PChallanMast);
            tx.commit();
        }
        catch (RuntimeException re) {
            if(PChallanMast != null)
                tx.rollback();
            throw re;
        }
        finally {
            session.close();
        }
    }

    public void update(ErpmPurchasechallanMaster PChallanMast) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(PChallanMast);
            tx.commit();
        }
        catch (RuntimeException re) {
            if(PChallanMast != null)
                tx.rollback();
            throw re;
        }
        finally {
            session.close();
        }
    }

     public ErpmPurchasechallanMaster findBypcmPcmId(Integer pcmPcmId) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            ErpmPurchasechallanMaster PChallanMast  = (ErpmPurchasechallanMaster) session.load(ErpmPurchasechallanMaster.class , pcmPcmId);
            Hibernate.initialize(PChallanMast.getErpmPoMaster());
            Hibernate.initialize(PChallanMast.getErpmPoMaster().getSuppliermaster());
            Hibernate.initialize(PChallanMast.getErpmPoMaster().getDepartmentmaster());
            Hibernate.initialize(PChallanMast.getInstitutionmaster());
            Hibernate.initialize(PChallanMast.getSubinstitutionmaster());
            Hibernate.initialize(PChallanMast.getDepartmentmaster());
            return PChallanMast;
        }
        finally {
            session.close();
            }
    }

     public ErpmPurchasechallanMaster findByPcmId(Integer pcmPcmId) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            List<ErpmPurchasechallanMaster> PChallanMast  = session.createQuery("Select u from ErpmPurchasechallanMaster u where u.pcmPcmId = :pcmPcmId").setParameter("pcmPcmId",pcmPcmId).list();
                Hibernate.initialize(PChallanMast.get(0).getErpmPoMaster());
                Hibernate.initialize(PChallanMast.get(0).getErpmPoMaster().getSuppliermaster());
                Hibernate.initialize(PChallanMast.get(0).getErpmPoMaster().getDepartmentmaster());
                Hibernate.initialize(PChallanMast.get(0).getInstitutionmaster());
                Hibernate.initialize(PChallanMast.get(0).getSubinstitutionmaster());
                Hibernate.initialize(PChallanMast.get(0).getDepartmentmaster());
           return PChallanMast.get(0);
        }
        finally {
            session.close();
            }
    }

      public List<ErpmPurchasechallanMaster > findByImId(Short imId) {
        Session session = HibernateUtil.getSession();
        try {
           session.beginTransaction();
           List<ErpmPurchasechallanMaster>  list  =  session.createQuery("Select u from ErpmPurchasechallanMaster  u where u.institutionmaster.imId = :imId").setParameter("imId", imId).list();
           for(int index = 0; index < list.size(); ++index) {
                Hibernate.initialize(list.get(index));
            }
            return list;
        }
        finally {
            session.close();
            }
    }

       public List<ErpmPurchasechallanMaster > findBySupplierId(Integer smId) {
        Session session = HibernateUtil.getSession();
        try {
           int index;
           session.beginTransaction();
           List<ErpmPurchasechallanMaster>  list  =  session.createQuery("Select u from ErpmPurchasechallanMaster  u where u.erpmPoMaster.suppliermaster.smId = :smId").setParameter("smId", smId).list();
           for (index = 0; index < list.size(); ++index) {
               Hibernate.initialize(list);
           }
           return list;
        }
        finally {
            session.close();
            }
    }

        public List<String> poListChallan(Short imId, Integer uid) {
        Session session = HibernateUtil.getSession();
        try {
          session.beginTransaction();
          List<String> list =  session.createQuery("Select new map(u.pcmPcmId as pcmPcmId,u.institutionmaster.imShortName as imShortName,u.subinstitutionmaster.simShortName as simShortName,u.departmentmaster.dmShortName as dmShortName,u.erpmPoMaster.suppliermaster.smName as smName,u.pcmChallanNo as challanNo,u.pcmRecvDate as RecvDate,u.pcmChallanDate as ChallanDate, concat(u.erpmPoMaster.departmentmaster.dmShortName,'/', year(u.erpmPoMaster.pomPoDate),'/', u.erpmPoMaster.pomPoNo) as pono) from ErpmPurchasechallanMaster u where u.institutionmaster.imId = :imId and u.departmentmaster.dmId in "
                    + "(Select d.departmentmaster.dmId from Erpmuserrole d where d.erpmusers.erpmuId = :uid)").setParameter("imId", imId).setParameter("uid", uid).list();
          for (int index = 0; index < list.size(); ++index) {
               Hibernate.initialize(list);
           }
            return list;
        }
        finally {
            session.close();
        }
    }

// public List<ErpmPurchasechallanMaster> findPOForUserDepartments__(Integer erpmuId) {
//        beginTransaction();
//        String SQL = "Select u from ErpmPurchasechallanMaster u "
//                    +"where u.departmentmaster.dmId in "
//                    + "(Select d.departmentmaster.dmId from Erpmuserrole d where d.erpmusers.erpmuId = :erpmuId)";
//
//        List<ErpmPurchasechallanMaster> POlist  = getSession().createQuery(SQL).setParameter("erpmuId",erpmuId).list();
//        commitTransaction();
//        return POlist;
//
//    }

}