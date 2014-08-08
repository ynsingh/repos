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
public class ErpmStockReceivedDAO {

// public void save(ErpmStockReceived esr) {
//        try {
//            beginTransaction();
//            getSession().save(esr);
//            commitTransaction();
//            }
//        catch (RuntimeException re) {
//            re.printStackTrace();
//            throw re;
//
//        }
//
// }
    public void save(ErpmStockReceived esr) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(esr);
            tx.commit();
        } catch (RuntimeException re) {
            if (esr != null) {
                tx.rollback();
            }
            throw re;
        } finally {
            session.close();
        }
    }

//    public void update(ErpmStockReceived esr ) {
//        try {
//            beginTransaction();
//            getSession().update(esr);
//            commitTransaction();
//        }
//        catch (RuntimeException re) {
//            re.printStackTrace();
//            throw re;
//        }
//    }
    public void update(ErpmStockReceived esr) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(esr);
            tx.commit();
        } catch (RuntimeException re) {
            if (esr != null) {
                tx.rollback();
            }
            throw re;
        } finally {
            session.close();
        }
    }

//    public void delete(ErpmStockReceived esr) {
//        try {
//            beginTransaction();
//            getSession().delete(esr);
//            commitTransaction();
//        }
//        catch (RuntimeException re) {
//            re.printStackTrace();
//            throw re;
//        }
//    }
    public void delete(ErpmStockReceived esr) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(esr);
            tx.commit();
        } catch (RuntimeException re) {
            if (esr != null) {
                tx.rollback();
            }
            throw re;
        } finally {
            session.close();
        }
    }

//    public List<ErpmTempOpeningStock> findAll() {
//        beginTransaction();
//        List<ErpmTempOpeningStock> list = getSession().createQuery("select u from ErpmTempOpeningStock u").list();
//        commitTransaction();
//        return list;
//    }
//     public ErpmTempOpeningStock findBytosId(Integer empId)
//    {
//        beginTransaction();
//        ErpmTempOpeningStock  tos  = (ErpmTempOpeningStock) getSession().load(ErpmTempOpeningStock.class , empId);
//        commitTransaction();
//        return tos;
//    }
// public List<ErpmStockReceived> findItemSerialNoList(char returntype,int dmId) {
//        beginTransaction();
//        // List<ErpmStockReceived> list = getSession().createQuery("select u from ErpmStockReceived u, ErpmIssueSerialDetai s where s.erpmStockReceived.stId = u.stid and u.departmentid = :dmId").setParameter("dmId", dmId).list();
//                    List<ErpmStockReceived> list = getSession().createQuery("select u from ErpmStockReceived u, ErpmIssueSerialDetail s where s.erpmIssueDetail.erpmIssueMaster.ismIssueType = :returntype and s.erpmStockReceived = u.stId and u.departmentmaster.dmId = :dmId").setParameter("returntype", returntype).setParameter("dmId", dmId).list();
//                      // List<ErpmStockReceived> list = getSession().createQuery("select u from ErpmStockReceived u  where u.departmentmaster.dmId = :dmId and u.ErpmItemMaster.").setParameter("dmId", dmId).list();
//                     // List<ErpmStockReceived> list = getSession().createQuery("select u from ErpmStockReceived u, ErpmIssueSerialDetai s where s.erpmIssueDetail.erpmIssueMaster.ismIssueType = :returntype").setParameter("returntype", returntype).list();
//       // List<ErpmStockReceived> list = getSession().createQuery("select distinct(u) from ErpmStockReceived u, ErpmIssueSerialDetai r where r.erpmIssueDetail.erpmIssueMaster.ismIssueType = :returntype and r.subinstitutionmaster.simId = u.simId and u.institutionmaster.imId = :ImId").setParameter("erpmuId", erpmuId).setParameter("ImId",ImId).list();
//
//      //  List<ErpmStockReceived> list = getSession().createQuery("select u from ErpmStockReceived u").list();
//
//        commitTransaction();
//        return list;
//    }
//    public ErpmStockReceived findByesrId(Integer esrId) {
//        beginTransaction();
//        ErpmStockReceived esrId1  = (ErpmStockReceived) getSession().load(ErpmStockReceived.class , esrId);
//        commitTransaction();
//        return esrId1;
//    }
    public ErpmStockReceived findByesrId(Integer esrId) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            ErpmStockReceived esrId1 = (ErpmStockReceived) session.load(ErpmStockReceived.class, esrId);
            Hibernate.initialize(esrId1);
            Hibernate.initialize(esrId1.getErpmGenMaster());
            Hibernate.initialize(esrId1.getErpmItemMaster());
            return esrId1;
        } finally {
            session.close();
        }
    }

    public List<ErpmStockReceived> findRemainingStockAsc(Integer itemId, Integer dmId, Integer isdId) {
        Session session = HibernateUtil.getSession();
        try {

            session.beginTransaction();
            List<ErpmStockReceived> list = session.createQuery("Select u from ErpmStockReceived u where u.erpmItemMaster.erpmimId = :itemId and u.departmentmaster.dmId=:dmId and u.stId not in (Select s.erpmStockReceived.stId from ErpmIssueSerialDetail s where s.erpmIssueDetail.isdId = :isdId) order by u.stInStockSince asc, u.stStockSerialNo asc").setParameter("itemId", itemId).setParameter("dmId", dmId).setParameter("isdId", isdId).list();
            for (int index = 0; index < list.size(); index++) {
                Hibernate.initialize(list.get(index).getInstitutionmaster());
                Hibernate.initialize(list.get(index).getSubinstitutionmaster());
                Hibernate.initialize(list.get(index).getDepartmentmaster());
            }
            return list;
        } finally {
            session.close();
        }
    }

//    public List<ErpmStockReceived> findByItemIdAs(Integer itemId, Integer dmId) {
//        beginTransaction();
////        List<ErpmStockReceived> list = getSession().createQuery("Select u from ErpmStockReceived u where u.erpmItemMaster.erpmimId = :itemId and u.departmentmaster.dmId=:dmId order by u.stInStockSince asc").setParameter("itemId", itemId).setParameter("dmId", dmId).list();
////        NEW HQL in replacement for the above HQL for getting the list of unissued / available items for issue
////        List<ErpmStockReceived> list = getSession().createQuery("Select u from ErpmStockReceived u where u.erpmItemMaster.erpmimId = :itemId and u.departmentmaster.dmId=:dmId u.stId NOT IN (SELECT m.erpmStockReceived.stId FROM ErpmIssueSerialDetail m WHERE m.issdReturned = 0) order by u.stInStockSince asc").setParameter("itemId", itemId).setParameter("dmId", dmId).list();
//         List<ErpmStockReceived> list = getSession().createQuery("Select u from ErpmStockReceived u where u.erpmItemMaster.erpmimId = :itemId and u.departmentmaster.dmId=:dmId and u.stId NOT IN (SELECT m.erpmStockReceived.stId FROM ErpmIssueSerialDetail m WHERE m.issdReturned = 0) order by u.stInStockSince asc, u.stStockSerialNo asc").setParameter("itemId", itemId).setParameter("dmId", dmId).list();
//        commitTransaction();
//        return list;
//    }
    public List<ErpmStockReceived> findByItemIdAsc(Integer itemId, Integer dmId) {
        Session session = HibernateUtil.getSession();
        try {

            session.beginTransaction();
            List<ErpmStockReceived> list = session.createQuery("Select u from ErpmStockReceived u where u.erpmItemMaster.erpmimId = :itemId and u.departmentmaster.dmId=:dmId and u.stId NOT IN (SELECT m.erpmStockReceived.stId FROM ErpmIssueSerialDetail m WHERE m.issdReturned = 0) order by u.stInStockSince asc, u.stStockSerialNo asc").setParameter("itemId", itemId).setParameter("dmId", dmId).list();
            for (int index = 0; index < list.size(); index++) {
                Hibernate.initialize(list.get(index).getInstitutionmaster());
                Hibernate.initialize(list.get(index).getSubinstitutionmaster());
                Hibernate.initialize(list.get(index).getDepartmentmaster());

            }
            return list;
        } finally {
            session.close();
        }
    }

//    public List<ErpmStockReceived> findByItemIdDesc(Integer itemId, Integer dmId) {
//        beginTransaction();
////        List<ErpmStockReceived> list = getSession().createQuery("Select u from ErpmStockReceived u where u.erpmItemMaster.erpmimId = :itemId and u.departmentmaster.dmId=:dmId order by u.stInStockSince desc").setParameter("itemId", itemId).setParameter("dmId", dmId).list();
////        NEW HQL in replacement for the above HQL for getting the list of unissued / available items for issue
//        List<ErpmStockReceived> list = getSession().createQuery("Select u from ErpmStockReceived u where u.erpmItemMaster.erpmimId = :itemId and u.departmentmaster.dmId=:dmId and u.stId NOT IN (SELECT m.erpmStockReceived.stId FROM ErpmIssueSerialDetail m WHERE m.issdReturned = 0) order by u.stInStockSince desc, u.stStockSerialNo desc").setParameter("itemId", itemId).setParameter("dmId", dmId).list();
//        commitTransaction();
//        return list;
//    }
    public List<ErpmStockReceived> findByItemIdDesc(Integer itemId, Integer dmId) {
        Session session = HibernateUtil.getSession();
        try {

            session.beginTransaction();
            List<ErpmStockReceived> list = session.createQuery("Select u from ErpmStockReceived u where u.erpmItemMaster.erpmimId = :itemId and u.departmentmaster.dmId=:dmId and u.stId NOT IN (SELECT m.erpmStockReceived.stId FROM ErpmIssueSerialDetail m WHERE m.issdReturned = 0) order by u.stInStockSince desc, u.stStockSerialNo desc").setParameter("itemId", itemId).setParameter("dmId", dmId).list();
            for (int index = 0; index < list.size(); index++) {
                Hibernate.initialize(list.get(index));
                 Hibernate.initialize(list.get(index).getInstitutionmaster());
                 Hibernate.initialize(list.get(index).getSubinstitutionmaster());
                 Hibernate.initialize(list.get(index).getDepartmentmaster());

            }
            return list;
        } finally {
            session.close();
        }
    }

//  public ErpmStockReceived findbystid(Integer stId) {
//        beginTransaction();
//        List<ErpmStockReceived> list  =  getSession().createQuery("Select u from ErpmStockReceived u where u.stId = :stId").setParameter("stId", stId).list();
//        commitTransaction();
//        return list.get(0);
//   }
    public ErpmStockReceived findbystid(Integer stId) {
        Session session = HibernateUtil.getSession();
        try {

            session.beginTransaction();
            List<ErpmStockReceived> list = session.createQuery("Select u from ErpmStockReceived u where u.stId = :stId").setParameter("stId", stId).list();
            for (int index = 0; index < list.size(); ++index) {
                //Hibernate.initialize(list.get(index));
                Hibernate.initialize(list.get(index).getErpmGenMaster());
                Hibernate.initialize(list.get(index).getErpmItemMaster());

            }
            return list.get(0);
        } finally {
            session.close();
        }

    }

//   public ErpmStockReceived findbyStackSerialNo(Integer StackSerialNo) {
//       String var=StackSerialNo.toString();
//        beginTransaction();
//        List<ErpmStockReceived> list  =  getSession().createQuery("Select u from ErpmStockReceived u where u.stId = :StackSerialNo").setParameter("StackSerialNo", StackSerialNo).list();
//        commitTransaction();
//        return list.get(0);
//   }
   // public ErpmStockReceived findbyStackSerialNo(Integer StackSerialNo) {
     public ErpmStockReceived findbyStockSerialNo(Integer StackSerialNo) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            List<ErpmStockReceived> list = session.createQuery("Select u from ErpmStockReceived u where u.stId = :StackSerialNo").setParameter("StackSerialNo", StackSerialNo).list();
            Hibernate.initialize(list);

            return list.get(0);
        } finally {
            session.close();
        }
    }

//    public List<ErpmStockReceived> findByItemId(Integer itemId) {
//        beginTransaction();
//        List<ErpmStockReceived> list = getSession().createQuery("Select u from ErpmStockReceived u where u.erpmItemMaster.erpmimId = :itemId").setParameter("itemId", itemId).list();
//        commitTransaction();
//        return list;
//    }
//    public List<ErpmStockReceived> findBySerialNo(String serialNo) {
//        beginTransaction();
//        List<ErpmStockReceived> list = getSession().createQuery("Select u from ErpmStockReceived u where u.stStockSerialNo = :serialNo").setParameter("serialNo", serialNo).list();
//        commitTransaction();
//        return list;
//    }
//    public List<ErpmStockReceived> findFullSerialNoByItemId(Integer itemId) {
//        beginTransaction();
//        //.stId, u.stStockSerialNo as FullStockSerialNo
//        List<ErpmStockReceived> list = getSession().createQuery("Select u.stStockSerialNo from ErpmStockReceived u where u.erpmItemMaster.erpmimId = :itemId").setParameter("itemId", itemId).list();
//        commitTransaction();
//        return list;
//    }
//    public Integer findMaxSrNo(Integer itemId, Short imId, Integer simId, Integer dmId) {
//        beginTransaction();
//
//        List<ErpmStockReceived> listRec = getSession().createQuery("select u from ErpmStockReceived u where u.erpmItemMaster.erpmimId = :itemId and u.institutionmaster.imId =:imId and" +
//                " u.subinstitutionmaster.simId =:simId and u.departmentmaster.dmId =:dmId").setParameter("itemId", itemId).setParameter("imId", imId).setParameter("simId", simId).setParameter("dmId", dmId).list();
//        if(listRec.size()>0)
//        {
//        String list = getSession().createQuery("Select max(cast(u.stStockSerialNo as int)) from ErpmStockReceived u where u.erpmItemMaster.erpmimId = :itemId and u.institutionmaster.imId =:imId and " +
//                "u.subinstitutionmaster.simId =:simId and u.departmentmaster.dmId =:dmId").setParameter("itemId", itemId).setParameter("imId", imId).setParameter("simId", simId).setParameter("dmId", dmId).uniqueResult().toString();
//       commitTransaction();
//        return Integer.parseInt(list);
//        }
//        else{
//            return 0;
//        }
//
//    }
    public Integer findMaxSrNo(Integer itemId, Short imId, Integer simId, Integer dmId) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            List<ErpmStockReceived> listRec = session.createQuery("select u from ErpmStockReceived u where u.erpmItemMaster.erpmimId = :itemId and u.institutionmaster.imId =:imId and"
                    + " u.subinstitutionmaster.simId =:simId and u.departmentmaster.dmId =:dmId").setParameter("itemId", itemId).setParameter("imId", imId).setParameter("simId", simId).setParameter("dmId", dmId).list();
            Hibernate.initialize(listRec);

            if (listRec.size() > 0) {
                String list = session.createQuery("Select max(cast(u.stStockSerialNo as int)) from ErpmStockReceived u where u.erpmItemMaster.erpmimId = :itemId and u.institutionmaster.imId =:imId and "
                        + "u.subinstitutionmaster.simId =:simId and u.departmentmaster.dmId =:dmId").setParameter("itemId", itemId).setParameter("imId", imId).setParameter("simId", simId).setParameter("dmId", dmId).uniqueResult().toString();

                Hibernate.initialize(list);

                return Integer.parseInt(list);
            } else {
                return 0;
            }
        } finally {
            session.close();
        }

    }


     public List<ErpmStockReceived> findPCDId(Integer pcdPcdId) {
        Session session = HibernateUtil.getSession();
        try {

            session.beginTransaction();
            List<ErpmStockReceived> list = session.createQuery("Select u from ErpmStockReceived u where u.stChallanDetId = :pcdPcdId").setParameter("pcdPcdId",pcdPcdId).list();
            for (int index = 0; index < list.size(); index++) {
                Hibernate.initialize(list.get(index));

            }
            return list;
        } finally {
            session.close();
        }
    }

     public List<ErpmStockReceived> findStId_ImId_SimId(Short imId , Integer simId) {
        Session session = HibernateUtil.getSession();
        try {

            session.beginTransaction();
            List<ErpmStockReceived> list = session.createQuery("Select u from ErpmStockReceived u where u.institutionmaster.imId = :imId and u.subinstitutionmaster.simId = :simId ").setParameter("imId", imId).setParameter("simId", simId).list();
            for (int index = 0; index < list.size(); index++) {
                Hibernate.initialize(list.get(index).getErpmItemMaster());

            }
            return list;
        } finally {
            session.close();
        }
    }

}
