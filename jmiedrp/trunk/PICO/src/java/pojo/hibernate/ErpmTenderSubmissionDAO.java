package pojo.hibernate;

import utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;
import org.hibernate.Hibernate;

public class ErpmTenderSubmissionDAO {

    public void save(ErpmTenderSubmission erpmtsb) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(erpmtsb);
            tx.commit();
        } catch (RuntimeException re) {
            if (erpmtsb != null) {
                tx.rollback();
            }
            throw re;
        } finally {
            session.close();
        }
    }

    public void update(ErpmTenderSubmission erpmtsb) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(erpmtsb);
            tx.commit();
        } catch (RuntimeException re) {
            if (erpmtsb != null) {
                tx.rollback();
            }
            throw re;
        } finally {
            session.close();
        }
    }

    public void delete(ErpmTenderSubmission erpmtsb) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(erpmtsb);
            tx.commit();
        } catch (RuntimeException re) {
            if (erpmtsb != null) {
                tx.rollback();
            }
            throw re;
        } finally {
            session.close();
        }
    }

    public List<ErpmTenderSubmission> findAll() {
        Session session = HibernateUtil.getSession();
        try {
            int index = 0;
            session.beginTransaction();
            List<ErpmTenderSubmission> list = session.createQuery("from ErpmTenderSubmission").list();
            for (index = 0; index < list.size(); ++index) {
                Hibernate.initialize(list.get(index).getInstitutionmaster());
                Hibernate.initialize(list.get(index).getSubinstitutionmaster());
                Hibernate.initialize(list.get(index).getDepartmentmaster());
                Hibernate.initialize(list.get(index).getErpmTenderMaster());
                Hibernate.initialize(list.get(index).getErpmGenMaster());
                Hibernate.initialize(list.get(index).getTsbCompanyName());

            }

            return list;
        } finally {
            session.close();
        }
    }

    public List< ErpmTenderSubmission> findByImId(Short imId) {
        Session session = HibernateUtil.getSession();
        try {
            int index = 0;
            session.beginTransaction();
            List< ErpmTenderSubmission> erpmtsbList = session.createQuery("Select u from  ErpmTenderSubmission u where u.institutionmaster.imId = :imId").setParameter("imId", imId).list();
            for (index = 0; index < erpmtsbList.size(); ++index) {
                Hibernate.initialize(erpmtsbList.get(index).getErpmGenMaster());
            }
            return erpmtsbList;
        } finally {
            session.close();
        }
    }

    public ErpmTenderSubmission findByErpmtsbId(Integer tsbTsbId) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            ErpmTenderSubmission erpmtsb = new ErpmTenderSubmission();
            erpmtsb = (ErpmTenderSubmission) session.load(ErpmTenderSubmission.class, tsbTsbId);
            Hibernate.initialize(erpmtsb);
            Hibernate.initialize(erpmtsb.getErpmTenderMaster());
            Hibernate.initialize(erpmtsb.getInstitutionmaster());
            Hibernate.initialize(erpmtsb.getSubinstitutionmaster());
            Hibernate.initialize(erpmtsb.getDepartmentmaster());
            Hibernate.initialize(erpmtsb.getErpmGenMaster());
        return erpmtsb;
        }
        finally {
            session.close();
        }
    }
public ErpmTenderSubmission findByTenderSubmissionId(Integer tsbTsbId) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
        List<ErpmTenderSubmission> List = session.createQuery("Select u from ErpmTenderSubmission u where u.tsbTsbId = :tsbTsbId").setParameter("tsbTsbId", tsbTsbId).list();
            Hibernate.initialize(List);
        return List.get(0);
        }
        finally {
            session.close();
        }
    }
}