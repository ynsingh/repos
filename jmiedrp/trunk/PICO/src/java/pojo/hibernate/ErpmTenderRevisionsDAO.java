
package pojo.hibernate;

import utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Hibernate;
import java.util.List;

/**
 *
 * @author wml3
 */
public class ErpmTenderRevisionsDAO {

    public void save(ErpmTenderRevisions etr) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(etr);
            tx.commit();
        } catch (RuntimeException re) {
            if (etr != null) {
                tx.rollback();
            }
            throw re;
        } finally {
            session.close();
        }
    }

    public void update(ErpmTenderRevisions etr) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(etr);
            tx.commit();
        } catch (RuntimeException re) {
            if (etr != null) {
                tx.rollback();
            }
            throw re;
        } finally {
            session.close();
        }
    }

    public void delete(ErpmTenderRevisions etr) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(etr);
            tx.commit();
        } catch (RuntimeException re) {
            if (etr != null) {
                tx.rollback();
            }
            throw re;
        } finally {
            session.close();
        }
    }

     public ErpmTenderRevisions findByTRId(Integer etrId) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            ErpmTenderRevisions etr  = (ErpmTenderRevisions) session.load(ErpmTenderRevisions.class , etrId);
            Hibernate.initialize(etr.getErpmGenMaster());
            Hibernate.initialize(etr.getErpmTenderMaster());
            Hibernate.initialize(etr.getSubinstitutionmaster());
            Hibernate.initialize(etr.getDepartmentmaster());
            return etr;
        }
        finally {
            session.close();
            }
        }

     public ErpmTenderRevisions findByTRId1(Integer etrId) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
           List<ErpmTenderRevisions> list = session.createQuery("select u from ErpmTenderRevisions u where u.trTrId = :etrId").setParameter("etrId", etrId).list();
            for (int index = 0; index < list.size(); ++index) {
                Hibernate.initialize(list.get(index).getErpmGenMaster());
                Hibernate.initialize(list.get(index).getSubinstitutionmaster());
                Hibernate.initialize(list.get(index).getDepartmentmaster());
                Hibernate.initialize(list.get(index).getErpmTenderMaster());

            }
            return list.get(0);
        }
        finally {
            session.close();
            }
        }



     public List<ErpmTenderRevisions> findAll() {
        Session session = HibernateUtil.getSession();
        try {
            int index = 0;
            session.beginTransaction();
            List<ErpmTenderRevisions> list = session.createQuery("select u from ErpmTenderRevisions u").list();
            for (index = 0; index < list.size(); ++index) {
                Hibernate.initialize(list.get(index).getErpmGenMaster());
                Hibernate.initialize(list.get(index).getSubinstitutionmaster());
                Hibernate.initialize(list.get(index).getDepartmentmaster());
                Hibernate.initialize(list.get(index).getErpmTenderMaster());

            }
            return list;
        }
        finally {
            session.close();
        }
    }

     public List<ErpmTenderRevisions> findByImId(Short imId) {
        Session session = HibernateUtil.getSession();
        try {
            int index = 0;
            session.beginTransaction();
            List<ErpmTenderRevisions> list = session.createQuery("select u from ErpmTenderRevisions u where u.institutionmaster.imId = :imId").setParameter("imId", imId).list();
            for (index = 0; index < list.size(); ++index) {
                Hibernate.initialize(list.get(index).getErpmGenMaster());
                Hibernate.initialize(list.get(index).getSubinstitutionmaster());
                Hibernate.initialize(list.get(index).getDepartmentmaster());
                Hibernate.initialize(list.get(index).getErpmTenderMaster());

            }
            return list;
       }
        finally {
            session.close();
        }
    }

      public Boolean findByRevisionNoAndTenderNo(Integer tenderId, int revisionNo) {
        Session session = HibernateUtil.getSession();
        try {
            int index = 0;
            session.beginTransaction();
            List<ErpmTenderRevisions> list = session.createQuery("select u from ErpmTenderRevisions u where u.erpmTenderMaster.tmTmId =:tenderId and u.trRevisionNo =:revisionNo").setParameter("tenderId", tenderId).setParameter("revisionNo", revisionNo).list();
            for (index = 0; index < list.size(); ++index) {
               
                Hibernate.initialize(list.get(index).getErpmTenderMaster());

            }
            if(list.isEmpty()==true)
            return true;
            else
                return false;
        }
        finally {
            session.close();
        }
    }
}
