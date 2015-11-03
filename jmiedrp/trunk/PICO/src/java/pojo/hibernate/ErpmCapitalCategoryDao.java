package pojo.hibernate;

import utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;


import java.util.List;
import org.hibernate.Hibernate;

public class ErpmCapitalCategoryDao {

    public void save(ErpmCapitalCategory erpmcc) {
        //Session session = HibernateUtil.getSession();
        Session session = HibernateUtil.getSessionPicoFactory();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(erpmcc);
            tx.commit();
        } catch (RuntimeException re) {
            if (erpmcc != null) {
                tx.rollback();
            }
            throw re;
        } finally {
            session.close();
        }
    }

    public void update(ErpmCapitalCategory erpmcc) {
        Session session = HibernateUtil.getSessionPicoFactory();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(erpmcc);
            tx.commit();
        } catch (RuntimeException re) {
            if (erpmcc != null) {
                tx.rollback();
            }
            throw re;
        } finally {
            session.close();
        }
    }

    public void delete(ErpmCapitalCategory erpmcc) {
        Session session = HibernateUtil.getSessionPicoFactory();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(erpmcc);
            tx.commit();
        } catch (RuntimeException re) {
            if (erpmcc != null) {
                tx.rollback();
            }
            throw re;
        } finally {
            session.close();
        }
    }

    public List<ErpmCapitalCategory> findAll() {
        Session session = HibernateUtil.getSessionPicoFactory();
        try {
            int index = 0;
            session.beginTransaction();
            List<ErpmCapitalCategory> list = session.createQuery("select u from ErpmCapitalCategory u").list();
            for (index = 0; index < list.size(); ++index) {
                Hibernate.initialize(list.get(index).getInstitutionmaster());


            }
            return list;
        } finally {
            session.close();
        }
    }

    public ErpmCapitalCategory findByErpmccId(Integer erpmccId) {
        Session session = HibernateUtil.getSessionPicoFactory();
        try {
            session.beginTransaction();
            ErpmCapitalCategory erpmcc = (ErpmCapitalCategory) session.load(ErpmCapitalCategory.class, erpmccId);
            Hibernate.initialize(erpmcc);

            return erpmcc;
        } finally {
            session.close();
        }
    }

    public List<ErpmCapitalCategory> findByImId(Short imId) {
        Session session = HibernateUtil.getSessionPicoFactory();
        try {
            int index = 0;
            session.beginTransaction();
            List<ErpmCapitalCategory> list = session.createQuery("Select u from ErpmCapitalCategory u where u.institutionmaster.imId = :imId").setParameter("imId", imId).list();
            for (index = 0; index < list.size(); ++index) {
                Hibernate.initialize(list.get(index).getInstitutionmaster());
            }
            return list;
        } finally {
            session.close();
        }
    }

    public List<ErpmCapitalCategory> findForUser(Integer erpmuId) {
        Session session = HibernateUtil.getSessionPicoFactory();
        try {
            int index = 0;
            session.beginTransaction();
            List<ErpmCapitalCategory> list = session.createQuery("Select u from ErpmCapitalCategory u where u.institutionmaster.imId  in (select r.institutionmaster.imId from Erpmuserrole r where r.erpmusers.erpmuId = :erpmuId)").setParameter("erpmuId", erpmuId).list();
            for (index = 0; index < list.size(); ++index) {
                Hibernate.initialize(list.get(index).getInstitutionmaster());
            }
            return list;
        } finally {
            session.close();
        }
    }

    public Integer findDuplicateCC(Short imId, String erpmccDesc) {
        Session session = HibernateUtil.getSessionPicoFactory();
        try {

            session.beginTransaction();
            Integer matchingRecords = Integer.parseInt(session.createQuery("Select count(u)  from ErpmCapitalCategory u where u.institutionmaster.imId = :imId and upper(u.ermccDesc) = upper(:erpmccDesc)").setParameter("imId", imId).setParameter("erpmccDesc", erpmccDesc).list().get(0).toString());

            return matchingRecords;
        } finally {
            session.close();
        }
    }
}
