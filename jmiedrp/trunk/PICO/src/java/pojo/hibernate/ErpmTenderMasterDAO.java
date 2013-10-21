/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo.hibernate;

/**
 *
 * @author FarazAhmad, Saeed
 */
import java.util.List;
import utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Hibernate;

public class ErpmTenderMasterDAO {

    private Object erpmuId;

    public void save(ErpmTenderMaster erpmtendermaster) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(erpmtendermaster);
            tx.commit();
        } catch (RuntimeException re) {
            if (erpmtendermaster != null) {
                tx.rollback();
            }
            throw re;
        } finally {
            session.close();
        }
    }

    public void delete(ErpmTenderMaster erpmicm) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(erpmicm);
            tx.commit();
        } catch (RuntimeException re) {
            if (erpmicm != null) {
                tx.rollback();
            }
            throw re;
        } finally {
            session.close();
        }
    }

    public void update(ErpmTenderMaster erpmtm) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(erpmtm);
            tx.commit();
        } catch (RuntimeException re) {
            if (erpmtm != null) {
                tx.rollback();
            }
            throw re;
        } finally {
            session.close();
        }
    }

    public List<ErpmTenderMaster> findAll() {
        Session session = HibernateUtil.getSession();
        try {
            int index = 0;
            session.beginTransaction();
            List<ErpmTenderMaster> list = session.createQuery("select u from ErpmTenderMaster u").list();
            for (index = 0; index < list.size(); ++index) {
                Hibernate.initialize(list.get(index).getInstitutionmaster());
                Hibernate.initialize(list.get(index).getDepartmentmaster());
                Hibernate.initialize(list.get(index).getSubinstitutionmaster());
                Hibernate.initialize(list.get(index).getErpmGenMasterByTmTypeId());
                Hibernate.initialize(list.get(index).getErpmGenMasterByTmStatusId());
            }
            return list;
        } finally {
            session.close();
        }
    }

    public List<ErpmTenderMaster> findByImId(Short ImId) {
        Session session = HibernateUtil.getSession();
        try {
            int index = 0;
            session.beginTransaction();
            List<ErpmTenderMaster> erpmTenderMasterList = session.createQuery("Select u from ErpmTenderMaster u where u.institutionmaster.imId = :ImId").setParameter("ImId", ImId).list();
            for (index = 0; index < erpmTenderMasterList.size(); ++index) {
                Hibernate.initialize(erpmTenderMasterList.get(index).getInstitutionmaster());
                Hibernate.initialize(erpmTenderMasterList.get(index).getDepartmentmaster());
                Hibernate.initialize(erpmTenderMasterList.get(index).getSubinstitutionmaster());
                Hibernate.initialize(erpmTenderMasterList.get(index).getErpmGenMasterByTmTypeId());
                Hibernate.initialize(erpmTenderMasterList.get(index).getErpmGenMasterByTmStatusId());
            }
            return erpmTenderMasterList;
        } finally {
            session.close();
        }
    }

    public ErpmTenderMaster findByTenderMasterId(Integer erpmtenderId) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            ErpmTenderMaster erpmtender = (ErpmTenderMaster) session.load(ErpmTenderMaster.class, erpmtenderId);
            Hibernate.initialize(erpmtender);
            return erpmtender;
        } finally {
            session.close();
        }
    }

    public List<ErpmTenderMaster> findBydmId(Short dmId) {
        Session session = HibernateUtil.getSession();
        try {
            int index = 0;
            session.beginTransaction();
            List<ErpmTenderMaster> erpmTenderMasterList = session.createQuery("Select u from ErpmTenderMaster u where u.departmentmaster.dmId = :dmId").setParameter("dmId", dmId).list();
            for (index = 0; index < erpmTenderMasterList.size(); ++index) {
                Hibernate.initialize(erpmTenderMasterList.get(index).getDepartmentmaster());
                // Hibernate.initialize(erpmTenderMasterList.get(index).getErpmTenderMaster());
            }

            return erpmTenderMasterList;

        } finally {
            session.close();
        }
    }

    public List<ErpmTenderMaster> findForUserTender(Integer erpmId) {
        String SQL = "Select u from ErpmTenderMaster u where u.erpmGenMaster.erpmgmEgmId";
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            List<ErpmTenderMaster> list = session.createQuery(SQL).setParameter("erpmId", erpmuId).list();
            for (int index = 0; index < list.size(); ++index) {
                Hibernate.initialize(list.get(index).getErpmGenMasterByTmStatusId());
                Hibernate.initialize(list.get(index).getErpmGenMasterByTmTypeId());
//                Hibernate.initialize(list.get(index).getErpmGenMasterBySmOwnershipType());
//                Hibernate.initialize(list.get(index).getErpmGenMasterBySmSupplierType());
            }
            return list;
        } finally {
            session.close();
        }
    }
}
