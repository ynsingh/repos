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

    public List<ErpmTenderMaster> findTender(Short ImId, Integer tenType, Integer tenState) {
        Session session = HibernateUtil.getSession();
        try {
            int index = 0;
            session.beginTransaction();
            List<ErpmTenderMaster> erpmTenderMasterList1;

            if (ImId == 0) {
                if (tenType != 0) {
                    if(tenState == 1)
                    	erpmTenderMasterList1 = session.createQuery("Select u from ErpmTenderMaster u where u.erpmGenMasterByTmTypeId.erpmgmEgmId = :tenType").setParameter("tenType", tenType).list();
                    else if(tenState == 2)
                        erpmTenderMasterList1 = session.createQuery("Select u from ErpmTenderMaster u where u.erpmGenMasterByTmTypeId.erpmgmEgmId = :tenType and (u.erpmGenMasterByTmStatusId.erpmgmEgmId <> 105 and u.erpmGenMasterByTmStatusId.erpmgmEgmId <> 106 and u.erpmGenMasterByTmStatusId.erpmgmEgmId <> 107)").setParameter("tenType", tenType).list();
                    else
                       erpmTenderMasterList1 = session.createQuery("Select u from ErpmTenderMaster u where u.erpmGenMasterByTmTypeId.erpmgmEgmId = :tenType and (u.erpmGenMasterByTmStatusId.erpmgmEgmId = 105 or u.erpmGenMasterByTmStatusId.erpmgmEgmId = 106 or u.erpmGenMasterByTmStatusId.erpmgmEgmId = 107)").setParameter("tenType", tenType).list();
                } else {
                    if(tenState == 1)
                    erpmTenderMasterList1 = session.createQuery("Select u from ErpmTenderMaster u").list();
                    else if(tenState == 2)
                        erpmTenderMasterList1 = session.createQuery("Select u from ErpmTenderMaster u where (u.erpmGenMasterByTmStatusId.erpmgmEgmId <> 105 and u.erpmGenMasterByTmStatusId.erpmgmEgmId <> 106 and u.erpmGenMasterByTmStatusId.erpmgmEgmId <> 107)").list();
                    else
                    erpmTenderMasterList1 = session.createQuery("Select u from ErpmTenderMaster u where (u.erpmGenMasterByTmStatusId.erpmgmEgmId = 105 or  u.erpmGenMasterByTmStatusId.erpmgmEgmId = 106 or u.erpmGenMasterByTmStatusId.erpmgmEgmId = 107)").list();
                }
            } else {
                if (tenType != 0) {
                    if(tenState == 1)
                    erpmTenderMasterList1 = session.createQuery("Select u from ErpmTenderMaster u where u.erpmGenMasterByTmTypeId.erpmgmEgmId = :tenType and u.institutionmaster.imId = :ImId").setParameter("tenType", tenType).setParameter("ImId", ImId).list();
                    else if(tenState==2)
                        erpmTenderMasterList1 = session.createQuery("Select u from ErpmTenderMaster u where u.erpmGenMasterByTmTypeId.erpmgmEgmId = :tenType and u.institutionmaster.imId = :ImId and (u.erpmGenMasterByTmStatusId.erpmgmEgmId <> 105 and u.erpmGenMasterByTmStatusId.erpmgmEgmId <> 106 and u.erpmGenMasterByTmStatusId.erpmgmEgmId <> 107)").setParameter("tenType", tenType).setParameter("ImId", ImId).list();
                    else
                      erpmTenderMasterList1 = session.createQuery("Select u from ErpmTenderMaster u where u.erpmGenMasterByTmTypeId.erpmgmEgmId = :tenType and u.institutionmaster.imId = :ImId and (u.erpmGenMasterByTmStatusId.erpmgmEgmId = 105 or u.erpmGenMasterByTmStatusId.erpmgmEgmId = 106 or u.erpmGenMasterByTmStatusId.erpmgmEgmId = 107)").setParameter("tenType", tenType).setParameter("ImId", ImId).list();
                } else {
                    if(tenState == 1)
                    erpmTenderMasterList1 = session.createQuery("Select u from ErpmTenderMaster u where u.institutionmaster.imId = :ImId").setParameter("ImId", ImId).list();
                    else if(tenState == 2)
                     erpmTenderMasterList1 = session.createQuery("Select u from ErpmTenderMaster u where u.institutionmaster.imId = :ImId and (u.erpmGenMasterByTmStatusId.erpmgmEgmId <> 105 and u.erpmGenMasterByTmStatusId.erpmgmEgmId <> 106 and u.erpmGenMasterByTmStatusId.erpmgmEgmId <> 107)").setParameter("ImId", ImId).list();
                    else
                        erpmTenderMasterList1 = session.createQuery("Select u from ErpmTenderMaster u where u.institutionmaster.imId = :ImId and (u.erpmGenMasterByTmStatusId.erpmgmEgmId = 105 or u.erpmGenMasterByTmStatusId.erpmgmEgmId = 106 or u.erpmGenMasterByTmStatusId.erpmgmEgmId = 107)").setParameter("ImId", ImId).list();
                }
            }
            List<ErpmTenderMaster> erpmTenderMasterList = erpmTenderMasterList1;
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
            Hibernate.initialize(erpmtender.getInstitutionmaster());
            Hibernate.initialize(erpmtender.getSubinstitutionmaster());
            Hibernate.initialize(erpmtender.getDepartmentmaster());
            return erpmtender;
        } finally {
            session.close();
        }
    }
}
