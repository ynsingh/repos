/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo.hibernate;

/**
 *
 * @author manauwar, Tanvir Ahmed, Arpan
 */
import utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Hibernate;
import java.util.List;

public class ErpmIssueSerialDetailDAO {

    public void save(ErpmIssueSerialDetail eisd) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(eisd);
            tx.commit();
        } catch (RuntimeException re) {
            if (eisd != null) {
                tx.rollback();
            }
            throw re;
        } finally {
            session.close();
        }
    }

    public void update(ErpmIssueSerialDetail eisd) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(eisd);
            tx.commit();
        } catch (RuntimeException re) {
            if (eisd != null) {
                tx.rollback();
            }
            throw re;
        } finally {
            session.close();
        }
    }

    public void delete(ErpmIssueSerialDetail eisd) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(eisd);
            tx.commit();
        } catch (RuntimeException re) {
            if (eisd != null) {
                tx.rollback();
            }
            throw re;
        } finally {
            session.close();
        }
    }

    public List<ErpmIssueSerialDetail> findByIssueDetID(Integer IsdId) {
        Session session = HibernateUtil.getSession();
        try {
            int index = 0;

            String SQL = "SELECT u FROM ErpmIssueSerialDetail u WHERE u.erpmIssueDetail.isdId=:IsdId";
            session.beginTransaction();
            List<ErpmIssueSerialDetail> list = session.createQuery(SQL).setParameter("IsdId", IsdId).list();
            for (index = 0; index < list.size(); ++index) {
                Hibernate.initialize(list.get(index).getErpmStockReceived());
                Hibernate.initialize(list.get(index).getErpmStockReceived().getErpmItemMaster());
                Hibernate.initialize(list.get(index).getErpmStockReceived().getInstitutionmaster());
                Hibernate.initialize(list.get(index).getErpmStockReceived().getSubinstitutionmaster());
                Hibernate.initialize(list.get(index).getErpmStockReceived().getDepartmentmaster());
            }
            return list;
        } finally {
            session.close();
        }
    }

    public List<ErpmIssueSerialDetail> findSerialNoByIssueDetID(Integer IsdId) {
        Session session = HibernateUtil.getSession();
        try {
            int index = 0;

            String SQL = "SELECT s FROM ErpmIssueSerialDetail s WHERE s.issdReceived = TRUE and s.erpmIssueDetail.isdId=:IsdId";
            session.beginTransaction();
            List<ErpmIssueSerialDetail> list = session.createQuery(SQL).setParameter("IsdId", IsdId).list();
            for (index = 0; index < list.size(); ++index) {
                Hibernate.initialize(list.get(index).getErpmStockReceived());
                Hibernate.initialize(list.get(index).getErpmStockReceived().getErpmItemMaster());
                Hibernate.initialize(list.get(index).getErpmStockReceived().getInstitutionmaster());
                Hibernate.initialize(list.get(index).getErpmStockReceived().getSubinstitutionmaster());
                Hibernate.initialize(list.get(index).getErpmStockReceived().getDepartmentmaster());
            }
            return list;
        } finally {
            session.close();
        }
    }

    public ErpmIssueSerialDetail findByErpmissdId(Integer issdId) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            ErpmIssueSerialDetail erpmIssd = (ErpmIssueSerialDetail) session.load(ErpmIssueSerialDetail.class, issdId);
            Hibernate.initialize(erpmIssd);
            Hibernate.initialize(erpmIssd.getErpmIssueDetail());
            Hibernate.initialize(erpmIssd.getErpmStockReceived());
            return erpmIssd;
        } finally {
            session.close();
        }
    }

    public Integer CountReceiveQty(Integer IsdId) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            String SQL = ("SELECT count(s) FROM ErpmIssueSerialDetail s WHERE s.issdReceived = TRUE and s.erpmIssueDetail.isdId=:IsdId");
            String list = session.createQuery(SQL).setParameter("IsdId", IsdId).uniqueResult().toString();
            Hibernate.initialize(list); //.getImName());

            return Integer.parseInt(list);
        } finally {
            session.close();
        }
    }

    public ErpmIssueSerialDetail findByeisdId(Integer eisdId) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            ErpmIssueSerialDetail eisd1 = (ErpmIssueSerialDetail) session.load(ErpmIssueSerialDetail.class, eisdId);
            Hibernate.initialize(eisd1);
            return eisd1;
        } finally {
            session.close();
        }
    }

    public List<ErpmIssueSerialDetail> findByEidId(Integer eid) {
        Session session = HibernateUtil.getSession();
        try {
            int index = 0;
            session.beginTransaction();
            List<ErpmIssueSerialDetail> list = session.createQuery("select u from ErpmIssueSerialDetail u where u.erpmIssueDetail.isdId = :eid").setParameter("eid", eid).list();
            for (index = 0; index < list.size(); ++index) {
                Hibernate.initialize(list.get(index).getErpmStockReceived());
                Hibernate.initialize(list.get(index).getErpmStockReceived().getInstitutionmaster());
                Hibernate.initialize(list.get(index).getErpmStockReceived().getSubinstitutionmaster());
                Hibernate.initialize(list.get(index).getErpmStockReceived().getDepartmentmaster());

            }
            return list;
        } finally {
            session.close();
        }
    }

    public Integer findCountByIssueDetailId(Integer eid) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            String list = session.createQuery("select count(u) from ErpmIssueSerialDetail u where u.erpmIssueDetail.isdId = :eid").setParameter("eid", eid).uniqueResult().toString();

            Hibernate.initialize(list); //.getImName());

            return Integer.parseInt(list);
        } finally {
            session.close();
        }
    }

    public Integer findCountByIssueDetailIdAndIssueSerialRecieveId(Integer eid, Integer esr) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            String list = session.createQuery("select count(u) from ErpmIssueSerialDetail u where u.erpmIssueDetail.isdId = :eid and u.erpmStockReceived.stId = :esr").setParameter("eid", eid).setParameter("esr", esr).uniqueResult().toString();
//            Hibernate.initialize(list); //.getImName());
            return Integer.parseInt(list);
        } finally {
            session.close();
        }
    }

    public Integer findCountByIssueSerialDetailId(Integer esd) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            String list = session.createQuery("select count(u) from ErpmIssueSerialDetail u where u.issdId = :esd").setParameter("esd", esd).uniqueResult().toString();

//            Hibernate.initialize(list);

            return Integer.parseInt(list);
        } finally {
            session.close();
        }
    }

    public List<ErpmIssueSerialDetail> findListByerpmStockReceivedId(Integer stId) {
        Session session = HibernateUtil.getSession();
        try {
            int index = 0;
            session.beginTransaction();
            List<ErpmIssueSerialDetail> list = session.createQuery("Select u from ErpmIssueSerialDetail u where u.erpmStockReceived.stId = :stId").setParameter("stId", stId).list();
            for (index = 0; index < list.size(); ++index) {
                Hibernate.initialize(list.get(index).getErpmStockReceived());
                Hibernate.initialize(list.get(index).getErpmIssueDetail());

            }
            return list;
        } finally {
            session.close();
        }
    }

    public ErpmIssueSerialDetail findbyissid(Integer issId) {
        Session session = HibernateUtil.getSession();
        try {

            session.beginTransaction();
            List<ErpmIssueSerialDetail> list = session.createQuery("Select u from ErpmIssueSerialDetail u where u.issdId = :issId").setParameter("issId", issId).list();
            Hibernate.initialize(list.get(0).getErpmIssueDetail());
            Hibernate.initialize(list.get(0).getErpmIssueDetail().getErpmIssueMaster());

            return list.get(0);
        } finally {
            session.close();
        }
    }

    public List<ErpmIssueSerialDetail> findListBydmIdReturnTypeissdReturn(char returntype, int dmId, Boolean bool) {
        Session session = HibernateUtil.getSession();
        try {
            int index = 0;
            session.beginTransaction();
            List<ErpmIssueSerialDetail> list = session.createQuery("Select u from ErpmIssueSerialDetail u where u.issdReturned = :bool and u.erpmIssueDetail.erpmIssueMaster.departmentmasterByIsmFromDepartmentId.dmId=:dmId and u.erpmIssueDetail.erpmIssueMaster.ismIssueType=:returntype").setParameter("bool", bool).setParameter("dmId", dmId).setParameter("returntype", returntype).list();
            for (index = 0; index < list.size(); ++index) {
                Hibernate.initialize(list.get(index).getErpmStockReceived());
            }
            return list;
        } finally {
            session.close();
        }
    }

    public ErpmIssueSerialDetail findbystId(Integer stId) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            List<ErpmIssueSerialDetail> list = session.createQuery("Select u from ErpmIssueSerialDetail u where u.erpmStockReceived.stId = :stId").setParameter("stId", stId).list();
            Hibernate.initialize(list.get(0).getErpmIssueDetail());
            Hibernate.initialize(list.get(0).getErpmIssueDetail().getErpmIssueMaster());
            Hibernate.initialize(list.get(0).getErpmStockReceived());
            Hibernate.initialize(list.get(0).getErpmStockReceived().getErpmItemMaster());
            return list.get(0);
        } finally {
            session.close();
        }
    }

    public Integer findByStockSerialId(Integer stId) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            List<ErpmIssueSerialDetail> list = session.createQuery("select u from ErpmIssueSerialDetail u where u.erpmStockReceived.stId = :stId").setParameter("stId", stId).list();
            Hibernate.initialize(list);
            if (list.size() > 0) {
                return 1;
            } else {
                return 0;
            }
        } finally {
            session.close();
        }
    }
}