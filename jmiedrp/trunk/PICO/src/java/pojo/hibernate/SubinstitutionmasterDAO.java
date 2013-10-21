/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo.hibernate;

/**
 *
 * @author kazim
 */
import utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Hibernate;
import java.util.List;

public class SubinstitutionmasterDAO {

    public void save(Subinstitutionmaster sim) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(sim);
            tx.commit();
        } catch (RuntimeException re) {
            if (sim != null) {
                tx.rollback();
            }
            throw re;
        } finally {
            session.close();
        }
    }

    public void update(Subinstitutionmaster sim) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(sim);
            tx.commit();
        } catch (RuntimeException re) {
            if (sim != null) {
                tx.rollback();
            }
            throw re;
        } finally {
            session.close();
        }
    }

    public void delete(Subinstitutionmaster sim) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(sim);
            tx.commit();
        } catch (RuntimeException re) {
            if (sim != null) {
                tx.rollback();
            }
            throw re;
        } finally {
            session.close();
        }
    }

    public List<Subinstitutionmaster> findAll() {
        Session session = HibernateUtil.getSession();
        try {
            int index = 0;
            session.beginTransaction();
            List<Subinstitutionmaster> list = session.createQuery("select u from Subinstitutionmaster u").list();
            for (index = 0; index < list.size(); ++index) {
                Hibernate.initialize(list.get(index).getInstitutionmaster());
                Hibernate.initialize(list.get(index).getCountrymaster());
                Hibernate.initialize(list.get(index).getStatemaster());
            }
            return list;
        } finally {
            session.close();
        }
    }

    public Subinstitutionmaster findBySimId(Integer simId) {
        Session session = HibernateUtil.getSession();
        try {
            Subinstitutionmaster sim = new Subinstitutionmaster();
            session.beginTransaction();
            sim = (Subinstitutionmaster) session.load(Subinstitutionmaster.class, simId);
            Hibernate.initialize(sim.getInstitutionmaster());

            return sim;
        } finally {
            session.close();
        }
    }

    public List<Subinstitutionmaster> findBysimImId(short simImId) {
        Session session = HibernateUtil.getSession();
        try {
            List<Subinstitutionmaster> list = session.createQuery("select u from Subinstitutionmaster u where u.institutionmaster.imId = :simImId").setParameter("simImId", simImId).list();
            for (int index = 0; index < list.size(); ++index) {
                Hibernate.initialize(list.get(index).getInstitutionmaster());
                Hibernate.initialize(list.get(index).getCountrymaster());
                Hibernate.initialize(list.get(index).getStatemaster());
            }
            return list;
        } finally {
            session.close();
        }

    }

    public Subinstitutionmaster findInstBySIMShortName(String simShortName) {
        Session session = HibernateUtil.getSession();
        try {
            List<Subinstitutionmaster> simList = session.createQuery("select distinct(u) from Subinstitutionmaster u where u.simShortName = :simShortName").setParameter("simShortName", simShortName).list();
            return simList.get(0);
        } finally {
            session.close();
        }
    }

    public List<Subinstitutionmaster> findSubInstByName(String simName) {
        Session session = HibernateUtil.getSession();
        try {
            List<Subinstitutionmaster> simList = session.createQuery("select distinct(u) from Subinstitutionmaster u where u.simName = :simName").setParameter("simName", simName).list();
            for (int index = 0; index < simList.size(); ++index) {
                Hibernate.initialize(simList.get(index).getInstitutionmaster());
                Hibernate.initialize(simList.get(index).getCountrymaster());
                Hibernate.initialize(simList.get(index).getStatemaster());
            }
            return simList;
        } finally {
            session.close();
        }
    }

    public List<Subinstitutionmaster> findSubInstByShortName(String simShortName) {
        Session session = HibernateUtil.getSession();
        try {
            List<Subinstitutionmaster> simList = session.createQuery("select distinct(u) from Subinstitutionmaster u where u.simShortName = :simShortName").setParameter("simShortName", simShortName).list();
            for (int index = 0; index < simList.size(); ++index) {
                Hibernate.initialize(simList.get(index).getInstitutionmaster());
                Hibernate.initialize(simList.get(index).getCountrymaster());
                Hibernate.initialize(simList.get(index).getStatemaster());
            }
            return simList;
        } finally {
            session.close();
        }
    }

    public List<Subinstitutionmaster> findSubInstForUser(Integer erpmuId, short ImId) {
        Session session = HibernateUtil.getSession();
        try {
            List<Subinstitutionmaster> simList = session.createQuery("select distinct(u) from Subinstitutionmaster u, Erpmuserrole r where r.erpmusers.erpmuId = :erpmuId and r.subinstitutionmaster.simId = u.simId and u.institutionmaster.imId = :ImId").setParameter("erpmuId", erpmuId).setParameter("ImId", ImId).list();
            for (int index = 0; index < simList.size(); ++index) {
                Hibernate.initialize(simList.get(index).getInstitutionmaster());
                Hibernate.initialize(simList.get(index).getCountrymaster());
                Hibernate.initialize(simList.get(index).getStatemaster());
            }
            return simList;
        } finally {
            session.close();
        }
    }

    public List<Subinstitutionmaster> findSubInstForAdmin(short ImId) {
        Session session = HibernateUtil.getSession();
        try {
            List<Subinstitutionmaster> simList = session.createQuery("select u from Subinstitutionmaster u where u.institutionmaster.imId = :ImId").setParameter("ImId", ImId).list();
            for (int index = 0; index < simList.size(); ++index) {
                Hibernate.initialize(simList.get(index).getInstitutionmaster());
                Hibernate.initialize(simList.get(index).getCountrymaster());
                Hibernate.initialize(simList.get(index).getStatemaster());
            }
            return simList;

        } finally {
            session.close();
        }
    }

    public String findDefaultSubInsitute(Integer simId) {
        Session session = HibernateUtil.getSession();
        try {
            List<Subinstitutionmaster> imList = session.createQuery("select u from Subinstitutionmaster u where u.simId = :simId").setParameter("simId", simId).list();
            return imList.get(0).getSimName();
        } finally {
            session.close();
        }
    }

    public List<Subinstitutionmaster> findAllSubInstForUser(Integer erpmuId) {
        Session session = HibernateUtil.getSession();
        try {
            String SQL = "select distinct(u) from Subinstitutionmaster u, Erpmuserrole r where r.erpmusers.erpmuId = :erpmuId and r.subinstitutionmaster.simId = u.simId";
            List<Subinstitutionmaster> simList = session.createQuery(SQL).
                    setParameter("erpmuId", erpmuId).list();
            return simList;
        } finally {
            session.close();
        }
    }
}
