package pojo.hibernate;

import java.util.List;
import utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Hibernate;

public class ErpmItemCategoryMasterDao {

    public void save(ErpmItemCategoryMaster erpmicm) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(erpmicm);
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

    public void update(ErpmItemCategoryMaster erpmicm) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(erpmicm);
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

    public void delete(ErpmItemCategoryMaster erpmicm) {
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

    public List<ErpmItemCategoryMaster> findAll() {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            List<ErpmItemCategoryMaster> list = session.createQuery("from ErpmItemCategoryMaster").list();
            return list;

        } finally {
            session.close();
        }
    }

    public ErpmItemCategoryMaster findByErpmicmItemId(Integer erpmicmItemId) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            ErpmItemCategoryMaster erpmicm = (ErpmItemCategoryMaster) session.load(ErpmItemCategoryMaster.class, erpmicmItemId);
            return erpmicm;
        } finally {
            session.close();
        }
    }

    public List<ErpmItemCategoryMaster> findByErpmicmItemLevel(Short erpmicmItemLevel) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            List<ErpmItemCategoryMaster> erpmicmList = session.createQuery("Select u from ErpmItemCategoryMaster u where u.erpmicmItemLevel = :erpmicmItemLevel").setParameter("erpmicmItemLevel", erpmicmItemLevel).list();
            return erpmicmList;
        } finally {
            session.close();
        }
    }

    public List<ErpmItemCategoryMaster> findByerpmItemCategoryMaster(Integer erpmItemCategoryMaster) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            List<ErpmItemCategoryMaster> erpmicmList = session.createQuery("Select u from ErpmItemCategoryMaster u where u.erpmItemCategoryMaster.erpmicmItemId = :erpmItemCategoryMaster").setParameter("erpmItemCategoryMaster", erpmItemCategoryMaster).list();
            return erpmicmList;

        } finally {
            session.close();
        }
    }

    public List<ErpmItemCategoryMaster> findByerpmItemCategoryMasterforDepreciationMethod(Integer erpmicmItemId) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            List<ErpmItemCategoryMaster> erpmicmList = session.createQuery("Select u from ErpmItemCategoryMaster u where u.erpmicmItemId = :erpmicmItemId").setParameter("erpmicmItemId", erpmicmItemId).list();
            return erpmicmList;

        } finally {
            session.close();
        }
    }

    public ErpmItemCategoryMaster findByErpmId(Integer studiD) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            ErpmItemCategoryMaster erpmp = (ErpmItemCategoryMaster) session.load(ErpmItemCategoryMaster.class, studiD);
            Hibernate.initialize(erpmp.getErpmItemCategoryMaster());

            return erpmp;
        } finally {
            session.close();
        }
    }

    public List<ErpmItemCategoryMaster> findByImId(Short ImId) {
        Session session = HibernateUtil.getSession();
        try {
            int index = 0;
            session.beginTransaction();
            List<ErpmItemCategoryMaster> erpmicmList = session.createQuery("Select u from ErpmItemCategoryMaster u where u.institutionmaster.imId = :ImId").setParameter("ImId", ImId).list();
            for (index = 0; index < erpmicmList.size(); ++index) {
                Hibernate.initialize(erpmicmList.get(index).getInstitutionmaster());
                Hibernate.initialize(erpmicmList.get(index).getErpmItemCategoryMaster());
            }

            return erpmicmList;

        } finally {
            session.close();
        }
    }

    public List<ErpmItemCategoryMaster> findParentCategoryMaster(Short erpmicmItemLevel) {
        Integer val = erpmicmItemLevel - 1;
        Short OneObj = val.shortValue();
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            List<ErpmItemCategoryMaster> erpmicmList = session.createQuery("Select u from ErpmItemCategoryMaster u where u.erpmicmItemLevel = :OneObj").setParameter("OneObj", OneObj).list();
            return erpmicmList;
        } finally {
            session.close();
        }

    }
}