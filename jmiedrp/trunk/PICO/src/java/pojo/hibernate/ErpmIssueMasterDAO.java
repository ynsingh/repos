/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo.hibernate;

/**
 *
 * @author Tanvir, manauwar
 */
import utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Hibernate;
import java.util.List;
import utils.BaseDAO;

public class ErpmIssueMasterDAO extends BaseDAO {

    public void save(ErpmIssueMaster eim) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(eim);
            tx.commit();
        } catch (RuntimeException re) {
            if (eim != null) {
                tx.rollback();
            }
            throw re;
        } finally {
            session.close();
        }
    }

    public void update(ErpmIssueMaster eim) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(eim);
            tx.commit();
        } catch (RuntimeException re) {
            if (eim != null) {
                tx.rollback();
            }
            throw re;
        } finally {
            session.close();
        }
    }

    public void delete(ErpmIssueMaster eim) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(eim);
            tx.commit();
        } catch (RuntimeException re) {
            if (eim != null) {
                tx.rollback();
            }
            throw re;
        } finally {
            session.close();
        }
    }

//    public List<ErpmIssueMaster> findAll() {
//        Session session = HibernateUtil.getSession();
//        try {
//            int index = 0;
//            session.beginTransaction();
//            List<ErpmIssueMaster> list = session.createQuery("select u from ErpmIssueMaster u").list();
//            for (index = 0; index < list.size(); ++index) {
//                Hibernate.initialize(list.get(index).getCommitteemaster());
//                Hibernate.initialize(list.get(index).getErpmIndentMaster());
//            }
//            return list;
//        } finally {
//            session.close();
//        }
//    }

    public ErpmIssueMaster findByeimId(Integer eimId) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            ErpmIssueMaster eimId1 = (ErpmIssueMaster) session.load(ErpmIssueMaster.class, eimId);
            Hibernate.initialize(eimId1.getInstitutionmasterByIsmFromInstituteId());
            Hibernate.initialize(eimId1.getSubinstitutionmasterByIsmFromSubinstituteId());
            Hibernate.initialize(eimId1.getDepartmentmasterByIsmFromDepartmentId());
            Hibernate.initialize(eimId1.getEmployeemasterByIsmFromEmployeeId());
            Hibernate.initialize(eimId1.getInstitutionmasterByIsmToInstituteId());
            Hibernate.initialize(eimId1.getSubinstitutionmasterByIsmToSubinstituteId());
            Hibernate.initialize(eimId1.getDepartmentmasterByIsmToDepartmentId());
            Hibernate.initialize(eimId1.getEmployeemasterByIsmToEmployeeId());
            Hibernate.initialize(eimId1.getCommitteemaster());
            Hibernate.initialize(eimId1.getSuppliermaster());
            return eimId1;
        } finally {
            session.close();
        }
    }

    public ErpmIssueMaster findByEimId(Integer eimId) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            List<ErpmIssueMaster> list = session.createQuery("Select u from ErpmIssueMaster u where u.ismId = :eimId").setParameter("eimId", eimId).list();
            Hibernate.initialize(list.get(0).getInstitutionmasterByIsmFromInstituteId());
            Hibernate.initialize(list.get(0).getSubinstitutionmasterByIsmFromSubinstituteId());
            Hibernate.initialize(list.get(0).getDepartmentmasterByIsmFromDepartmentId());
            Hibernate.initialize(list.get(0).getErpmIndentMaster());
            return list.get(0);
        } finally {
            session.close();
        }
    }

    public ErpmIssueMaster findCurrentIssueIdByIndent(Short indentId, Integer empId, Integer deptId, String issueNo) {

        Session session = HibernateUtil.getSession();
        try {
            int index = 0;
            session.beginTransaction();
            List<ErpmIssueMaster> list = session.createQuery("Select u from ErpmIssueMaster u where u.erpmIndentMaster.indtIndentId = :indentId and u.departmentmasterByIsmFromDepartmentId.dmId = :deptId and u.employeemasterByIsmFromEmployeeId.empId = :empId and u.ismIssueNo = :issueNo").setParameter("indentId", indentId).setParameter("deptId", deptId).setParameter("empId", empId).setParameter("issueNo", issueNo).list();
            for (index = 0; index < list.size(); ++index) {
                Hibernate.initialize(list.get(index).getDepartmentmasterByIsmFromDepartmentId());
                Hibernate.initialize(list.get(index).getErpmIndentMaster());
            }
            return list.get(0);
        } finally {
            session.close();
        }

    }

    public ErpmIssueMaster findCurrentIssueId(Integer empId, Integer deptId, String issueNo) {

        Session session = HibernateUtil.getSession();
        try {
            int index = 0;
            session.beginTransaction();
            List<ErpmIssueMaster> list = session.createQuery("Select u from ErpmIssueMaster u where u.departmentmasterByIsmFromDepartmentId.dmId = :deptId and u.employeemasterByIsmFromEmployeeId.empId = :empId and u.ismIssueNo = :issueNo").setParameter("deptId", deptId).setParameter("empId", empId).setParameter("issueNo", issueNo).list();
            for (index = 0; index < list.size(); ++index) {
                Hibernate.initialize(list.get(index).getDepartmentmasterByIsmFromDepartmentId());
                Hibernate.initialize(list.get(index).getEmployeemasterByIsmFromEmployeeId());
            }
            return list.get(0);
        } finally {
            session.close();
        }

    }

    public List<ErpmIssueMaster> findByEmpId(Integer empId) {
        Session session = HibernateUtil.getSession();
        try {
            int index = 0;
            session.beginTransaction();
            List<ErpmIssueMaster> list = session.createQuery("Select u from ErpmIssueMaster u where u.employeemasterByIsmToEmployeeId.empId = :empId").setParameter("empId", empId).list();
            for (index = 0; index < list.size(); ++index) {
                Hibernate.initialize(list.get(index).getEmployeemasterByIsmToEmployeeId());

            }
            return list;
        } finally {
            session.close();
        }
    }

    public List<ErpmIssueMaster> findByInstitutionId(Short instId) {
        Session session = HibernateUtil.getSession();
        try {
            int index = 0;
            session.beginTransaction();
            List<ErpmIssueMaster> list = session.createQuery("Select u from ErpmIssueMaster u where u.institutionmasterByIsmFromInstituteId.imId = :instId").setParameter("instId", instId).list();
            for (index = 0; index < list.size(); ++index) {
                Hibernate.initialize(list.get(index).getErpmIndentMaster());
            }
            return list;
        } finally {
            session.close();
        }
    }

    public List<ErpmIssueMaster> findByCompId(Integer committeeId) {
        Session session = HibernateUtil.getSession();
        try {
            int index = 0;
            session.beginTransaction();
            List<ErpmIssueMaster> list = session.createQuery("Select u from ErpmIssueMaster u where u.committeemaster.committeeId = :committeeId").setParameter("committeeId", committeeId).list();
            for (index = 0; index < list.size(); ++index) {
                Hibernate.initialize(list.get(index).getCommitteemaster());
            }
            return list;
        } finally {
            session.close();
        }
    }

    public List<ErpmIssueMaster> findIssueMasterListBydmIdandReturnType(char returntype, int dmId) {
        Session session = HibernateUtil.getSession();
        try {
            int index = 0;
            session.beginTransaction();
            List<ErpmIssueMaster> eimList = session.createQuery("Select u from ErpmIssueMaster u where u.departmentmasterByIsmFromDepartmentId.dmId = :dmId and u.ismIssueType = :returntype").setParameter("dmId", dmId).setParameter("returntype", returntype).list();
            for (index = 0; index < eimList.size(); ++index) {
                Hibernate.initialize(eimList.get(index).getDepartmentmasterByIsmFromDepartmentId());

            }
            return eimList;
        } finally {
            session.close();
        }
    }

    public Float findLedgerValue(int instId, int subInstId, int deptId, int itemId) {
        Session session = HibernateUtil.getSession();
        try {

            String Query = "SELECT (sum(Recd_Quantity)-(sum(Issue_Quantity)+sum(WriteOff_Quantity))) as Quantity FROM view_item_ledger "
                    + "where IM_ID = :instId AND SIM_ID = :subInstId AND DM_ID = :deptId AND Item_ID = :itemId";
            session.beginTransaction();
            Float countRecords = Float.parseFloat(session.createSQLQuery(Query).setParameter("instId", instId).setParameter("subInstId", subInstId).setParameter("deptId", deptId).setParameter("itemId", itemId).uniqueResult().toString());
            return countRecords;
        } finally {
            session.close();
        }
    }
}
