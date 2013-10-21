
package pojo.hibernate;

import utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Hibernate;
import java.util.List;
/**
 *
 * @author Tanvir Ahmed, Saeed 
 */
public class ErpmIndentMasterDAO  {

    
    public void save(ErpmIndentMaster erpmindtmast) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(erpmindtmast);
            tx.commit();
        } catch (RuntimeException re) {
            if (erpmindtmast != null) {
                tx.rollback();
            }
            throw re;
        } finally {
            session.close();
        }
    }

    public void update(ErpmIndentMaster erpmindtmast) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(erpmindtmast);
            tx.commit();
        } catch (RuntimeException re) {
            if (erpmindtmast != null) {
                tx.rollback();
            }
            throw re;
        } finally {
            session.close();
        }
    }

    public void delete(ErpmIndentMaster erpmindtmast) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(erpmindtmast);
            tx.commit();
        } catch (RuntimeException re) {
            if (erpmindtmast != null) {
                tx.rollback();
            }
            throw re;
        } finally {
            session.close();
        }
    }

    public List<ErpmIndentMaster> findIndentsForUserDepartments(Integer erpmuId) {
        Session session = HibernateUtil.getSession();
        try {
            int index = 0;
            String SQL = "Select u from ErpmIndentMaster u "
                    +"where u.departmentmaster.dmId in "
                    + "(Select d.departmentmaster.dmId "
                    + "from Erpmuserrole d "
                    + "where d.erpmusers.erpmuId = :erpmuId)";
            session.beginTransaction();
            List<ErpmIndentMaster> erpmindtmastList  = session.createQuery(SQL).setParameter("erpmuId",erpmuId).list();
            for (index = 0; index < erpmindtmastList.size(); ++index) {
                Hibernate.initialize(erpmindtmastList.get(index).getDepartmentmaster());
                Hibernate.initialize(erpmindtmastList.get(index).getErpmusers());
                Hibernate.initialize(erpmindtmastList.get(index).getBudgetheadmaster());
            }
            return erpmindtmastList;
        } finally {
            session.close();
        }
    }

    public List<ErpmIndentMaster> findIndentsForUser(Integer erpmuId, String erpmuName) {
        Session session = HibernateUtil.getSession();
        try {
            int index = 0;
            String SQL = "Select u from ErpmIndentMaster u "
                    +"where u.erpmusers.erpmuId = :erpmuId"
                    + " or u.indtIndentId in "
                    + " (Select v.wftWorkId from Workflowtransaction v "
                    + " where v.wftDestinationEmail = :erpmuName)";
            session.beginTransaction();
            List<ErpmIndentMaster> erpmindtmastList  = session.createQuery(SQL)
                                                                .setParameter("erpmuId",erpmuId)
                                                                .setParameter("erpmuName",erpmuName).list();
            for (index = 0; index < erpmindtmastList.size(); ++index) {
                Hibernate.initialize(erpmindtmastList.get(index).getDepartmentmaster());
                Hibernate.initialize(erpmindtmastList.get(index).getErpmusers());
                Hibernate.initialize(erpmindtmastList.get(index).getBudgetheadmaster());
            }
            return erpmindtmastList;
        } finally {
            session.close();
        }
    }

    public ErpmIndentMaster findIndentMasterId(Short indtIndentId) {
        Session session = HibernateUtil.getSession();
        try {

            session.beginTransaction();
            List<ErpmIndentMaster> erpmindtmast  = session.createQuery("Select u from ErpmIndentMaster u where u.indtIndentId = :indtIndentId").setParameter("indtIndentId",indtIndentId).list();
            Hibernate.initialize(erpmindtmast.get(0).getErpmGenMasterByIndtCurrencyId());
            return erpmindtmast.get(0);
        } finally {
            session.close();
        }
    }

//     public ErpmIndentMaster  findIndentMasterIds(short indtIndentId) {
//        Session session = HibernateUtil.getSession();
//        try {
//            session.beginTransaction();
//            ErpmIndentMaster  erpmindtmast  = (ErpmIndentMaster ) session.load(ErpmIndentMaster .class , indtIndentId);
//            Hibernate.initialize(erpmindtmast); //.getImName());
//
//            return erpmindtmast;
//        } finally {
//            session.close();
//        }
//    }


     public ErpmIndentMaster findSimIdbyIndentId(Short indtIndentId) {
        Session session = HibernateUtil.getSession();
        try {

            session.beginTransaction();
            List<ErpmIndentMaster> erpmindtmast  = session.createQuery("Select u from ErpmIndentMaster u where u.indtIndentId = :indtIndentId").setParameter("indtIndentId",indtIndentId).list();
            Hibernate.initialize(erpmindtmast.get(0).getInstitutionmaster());
            Hibernate.initialize(erpmindtmast.get(0).getSubinstitutionmaster());
            Hibernate.initialize(erpmindtmast.get(0).getDepartmentmaster());
            Hibernate.initialize(erpmindtmast.get(0).getErpmusers());
            Hibernate.initialize(erpmindtmast.get(0).getDepartmentalBudgetAllocation());
            return erpmindtmast.get(0);
        } finally {
            session.close();
        }
    }

      public List<ErpmIndentMaster> findApprovedIndents(String FromDate, String ToDate, Integer userId, String currency) {
        Session session = HibernateUtil.getSession();
        try {
            int index = 0;
            String SQL = "Select u from ErpmIndentMaster u where "
                + " u.indtIndentDate >= str_to_date(:FromDate,'%d-%m-%Y') and "
                + " u.indtIndentDate <= str_to_date(:ToDate,'%d-%m-%Y') and "
                + " u.erpmGenMasterByIndtCurrencyId.erpmgmEgmDesc = :currency and"
                + " u.institutionmaster.imId in (Select w.institutionmaster.imId from Erpmuserrole w where w.erpmusers.erpmuId = :userId) and "
                + " u.indtIndentId in (Select distinct(v.wftWorkId) "
                + " from Workflowtransaction v "
                + " where v.erpmGenMaster.erpmgmEgmId = 82) ";
            session.beginTransaction();
            List<ErpmIndentMaster> indentList  = session.createQuery(SQL)
                                                     .setParameter("FromDate", FromDate)
                                                     .setParameter("ToDate", ToDate)
                                                     .setParameter("currency",currency)
                                                     .setParameter("userId", userId).list();
            for (index = 0; index < indentList.size(); ++index) {
                Hibernate.initialize(indentList.get(index).getInstitutionmaster());
                Hibernate.initialize(indentList.get(index).getSubinstitutionmaster());
                Hibernate.initialize(indentList.get(index).getDepartmentmaster());
                Hibernate.initialize(indentList.get(index).getDepartmentalBudgetAllocation());
                Hibernate.initialize(indentList.get(index).getErpmGenMasterByIndtCurrencyId());
                Hibernate.initialize(indentList.get(index).getErpmGenMasterByIndtStatus());
                Hibernate.initialize(indentList.get(index).getErpmusers());
                Hibernate.initialize(indentList.get(index).getBudgetheadmaster());
                Hibernate.initialize(indentList.get(index).getWorkflowmaster());

            }
            return indentList;
        } finally {
            session.close();
        }
    }

}
