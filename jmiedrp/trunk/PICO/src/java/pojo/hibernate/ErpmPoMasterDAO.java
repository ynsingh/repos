/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

 /*
  * @author SajidAziz
  */

package pojo.hibernate;
import java.util.Date;
import utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Hibernate;
import java.util.List;
//import utils.BaseDAO;
//public class ErpmPoMasterDAO extends BaseDAO {
public class ErpmPoMasterDAO {

public void save(ErpmPoMaster pomaster) {
        Session session = HibernateUtil.getSessionPicoFactory();
        Transaction tx = null;
        try {
        /*    beginTransaction();
            getSession().save(pomaster);
            commitTransaction();
        }
        catch (RuntimeException re) {
            re.printStackTrace();
            throw re;    }*/
            tx = session.beginTransaction();
            session.save(pomaster);
            tx.commit();
        } catch (RuntimeException re) {
            if (pomaster != null) {
                tx.rollback();
            }
            throw re;
        } finally {
            session.close();
        }
    }

    public void delete(ErpmPoMaster pomaster) {
        Session session = HibernateUtil.getSessionPicoFactory();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(pomaster);
            tx.commit();
        } catch (RuntimeException re) {
            if (pomaster != null) {
                tx.rollback();
            }
            throw re;
        } finally {
            session.close();
        }
    }

    public void update(ErpmPoMaster pomaster) {
        Session session = HibernateUtil.getSessionPicoFactory();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(pomaster);
            tx.commit();
        } catch (RuntimeException re) {
            if (pomaster != null) {
                tx.rollback();
            }
            throw re;
        } finally {
            session.close();
        }
    }

    public ErpmPoMaster findBypomPoMasterId(Integer pomPoMasterId) {
        Session session = HibernateUtil.getSessionPicoFactory();
        try {
            session.beginTransaction();
            //ErpmPoMaster pomaster  = (ErpmPoMaster) getSession().load(ErpmPoMaster.class , pomPoMasterId);
            ErpmPoMaster pomaster  = (ErpmPoMaster) session.load(ErpmPoMaster.class , pomPoMasterId);
            Hibernate.initialize(pomaster.getInstitutionmaster());
            Hibernate.initialize(pomaster.getSubinstitutionmaster());
            Hibernate.initialize(pomaster.getDepartmentmaster());
            Hibernate.initialize(pomaster.getErpmGenMasterByPomCurrencyId());
            Hibernate.initialize(pomaster.getSuppliermaster());
            Hibernate.initialize(pomaster.getErpmGenMasterByPomPaymentModeId());
            Hibernate.initialize(pomaster.getErpmusersByPomApprovedById());
            return pomaster;
        } finally {
            session.close();
        }
    }

    public ErpmPoMaster findByPoMasterId(Integer pomPoMasterId) {
        Session session = HibernateUtil.getSessionPicoFactory();
        try {

            session.beginTransaction();
            List<ErpmPoMaster> pomaster  = session.createQuery("Select u from ErpmPoMaster u where u.pomPoMasterId = :pomPoMasterId").setParameter("pomPoMasterId",pomPoMasterId).list();
            Hibernate.initialize(pomaster.get(0).getInstitutionmaster());
            Hibernate.initialize(pomaster.get(0).getSubinstitutionmaster());
            Hibernate.initialize(pomaster.get(0).getDepartmentmaster());
            Hibernate.initialize(pomaster.get(0).getSuppliermaster());
            Hibernate.initialize(pomaster.get(0).getErpmusersByPomApprovedById());
            Hibernate.initialize(pomaster.get(0).getErpmGenMasterByPomCurrencyId());
            Hibernate.initialize(pomaster.get(0).getErpmGenMasterByPomCurrencyId());

            return pomaster.get(0);
        } finally {
            session.close();
        }
    }

    public List<ErpmPoMaster> findPOForUserDepartments(Integer erpmuId) {
        Session session = HibernateUtil.getSessionPicoFactory();
        try {
            int index = 0;
            String SQL = "Select u from ErpmPoMaster u "
                    +"where u.departmentmaster.dmId in "
                    + "(Select d.departmentmaster.dmId from Erpmuserrole d where d.erpmusers.erpmuId = :erpmuId)";
            session.beginTransaction();
           List<ErpmPoMaster> POlist  = session.createQuery(SQL).setParameter("erpmuId",erpmuId).list();
//           Hibernate.initialize(POlist);
            for (index = 0; index < POlist.size(); ++index) {
                Hibernate.initialize(POlist.get(index).getDepartmentmaster());
                Hibernate.initialize(POlist.get(index).getSuppliermaster());
                Hibernate.initialize(POlist.get(index).getErpmGenMasterByPomCurrencyId());
            }
            return POlist;
        } finally {
            session.close();
        }
    }

    public Integer findlastPOForDeptInCurrentYear(Integer dmId, Date poDate) {
        Session session = HibernateUtil.getSessionPicoFactory();
        try {
            String SQL  =   " Select if(max(u.pomPoNo),max(u.pomPoNo),0) + 1 from ErpmPoMaster u "
                    +   " where u.departmentmaster.dmId = :dmId and year(u.pomPoDate) = year(:poDate) ";
            session.beginTransaction();
            Integer lastPONumber = Integer.parseInt(session.createQuery(SQL).setParameter("dmId",dmId).setParameter("poDate",poDate).uniqueResult().toString());
            Hibernate.initialize(lastPONumber);
            return lastPONumber;

        } finally {
            session.close();
        }
    }

     public ErpmPoMaster findByPONumber(String dmShortName, Integer poYear, Integer PON){
        Session session = HibernateUtil.getSessionPicoFactory();
        try {

            String SQL  =   " Select u from ErpmPoMaster u  "
                    +   " where u.departmentmaster.dmShortName = :dmShortName and "
                    +   " year(u.pomPoDate) = :poYear and "
                    +   " u.pomPoNo = :PON";
            session.beginTransaction();
            ErpmPoMaster pom = (ErpmPoMaster) session.createQuery(SQL).setParameter("dmShortName",dmShortName)
                                                            .setParameter("poYear",poYear)
                                                            .setParameter("PON",PON)
                                                            .uniqueResult();
            Hibernate.initialize(pom.getErpmGenMasterByPomCurrencyId());
            Hibernate.initialize(pom.getSuppliermaster());
            Hibernate.initialize(pom.getDepartmentmaster());
            Hibernate.initialize(pom.getInstitutionmaster());
            Hibernate.initialize(pom.getSubinstitutionmaster());
            Hibernate.initialize(pom.getErpmusersByPomApprovedById());

            return pom;
        } finally {
            session.close();
        }
    }

     // This method is to get List of Full PO No., used in Purchase Challan and Invoice
    public List<String> poList(Short imId) {
        Session session = HibernateUtil.getSessionPicoFactory();
        try {
            int index = 0;
            String SQL = "Select new map(u.pomPoMasterId as poid, "
                    + " concat(u.departmentmaster.dmShortName,'/', year(u.pomPoDate),'/', pomPoNo) as pono) "
                    + " from ErpmPoMaster u where u.institutionmaster.imId = :imId "
                    + " and u.pomPoMasterId not in "
                    + " (Select coalesce(m.erpmPoMaster.pomPoMasterId,0, m.erpmPoMaster.pomPoMasterId) from ErpmPurchasechallanMaster m) "
                    + " and u.pomPoMasterId not in "
                    + " (Select coalesce(n.erpmPoMaster.pomPoMasterId,0, n.erpmPoMaster.pomPoMasterId) from ErpmPurchaseinvoiceMaster n) ";

            session.beginTransaction();
           List<String> list =  session.createQuery(SQL).setParameter("imId", imId).list();
           for (index = 0; index < list.size(); ++index) {
               Hibernate.initialize(list.get(index));
           }

            return list;
        } finally {
            session.close();
        }
    }

     public List<String> poList2(Short imId) {
        Session session = HibernateUtil.getSessionPicoFactory();
        try {
            int index = 0;
            session.beginTransaction();
           List<String> list =  session.createQuery("Select new map(u.pomPoMasterId as poid, concat(u.departmentmaster.dmShortName,'/', year(u.pomPoDate),'/', pomPoNo) as pono) from ErpmPoMaster u where u.institutionmaster.imId = :imId").setParameter("imId", imId).list();
           for (index = 0; index < list.size(); ++index) {
               Hibernate.initialize(list.get(index));
           }

            return list;
        } finally {
            session.close();
        }
    }

public List<ErpmPoMaster> findBySupplierId(Integer smId) {
        Session session = HibernateUtil.getSessionPicoFactory();
        try {
            int index = 0;
            String SQL = "Select u from ErpmPoMaster u where u.suppliermaster.smId = :smId"

                    + " and u.pomPoMasterId not in "
                    + " (Select coalesce(m.erpmPoMaster.pomPoMasterId,0, m.erpmPoMaster.pomPoMasterId) from ErpmPurchasechallanMaster m) "
                    + " and u.pomPoMasterId not in "
                    + " (Select coalesce(n.erpmPoMaster.pomPoMasterId,0, n.erpmPoMaster.pomPoMasterId) from ErpmPurchaseinvoiceMaster n) ";

            session.beginTransaction();
//            List<ErpmPoMaster> list  =  session.createQuery("Select u from ErpmPoMaster u where u.suppliermaster.smId = :smId").setParameter("smId", smId).list();
            List<ErpmPoMaster> list  =  session.createQuery(SQL).setParameter("smId", smId).list();            
            for (index = 0; index < list.size(); ++index) {

//               Hibernate.initialize(list.get(index));
               Hibernate.initialize(list.get(index).getDepartmentmaster());
           }

            return list;
        } finally {
            session.close();
        }
    }
 
}
