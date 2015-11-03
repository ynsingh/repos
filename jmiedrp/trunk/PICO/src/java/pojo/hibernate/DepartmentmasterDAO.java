package pojo.hibernate;

import utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Hibernate;
import java.util.List;

public class DepartmentmasterDAO {

    public void save(Departmentmaster dm) {
        //Session session = HibernateUtil.getSession();
        Session session = HibernateUtil.getSessionPicoFactory();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(dm);
            tx.commit();
        } catch (RuntimeException re) {
            if (dm != null) {
                tx.rollback();
            }
            throw re;
        } finally {
            session.close();
        }
    }

    public void update(Departmentmaster dm) {
        Session session = HibernateUtil.getSessionPicoFactory();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(dm);
            tx.commit();
        } catch (RuntimeException re) {
            if (dm != null) {
                tx.rollback();
            }
            throw re;
        } finally {
            session.close();
        }
    }

    public void delete(Departmentmaster dm) {
        Session session = HibernateUtil.getSessionPicoFactory();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(dm);
            tx.commit();
        } catch (RuntimeException re) {
            if (dm != null) {
                tx.rollback();
            }
            throw re;
        } finally {
            session.close();
        }
    }

    public List<Departmentmaster> findAll() {
        Session session = HibernateUtil.getSessionPicoFactory();
        try {
            session.beginTransaction();
            List<Departmentmaster> list = session.createQuery("from Departmentmaster").list();
            for (int index = 0; index < list.size(); ++index) {
                Hibernate.initialize(list.get(index).getInstitutionmaster());
                Hibernate.initialize(list.get(index).getSubinstitutionmaster());
                Hibernate.initialize(list.get(index).getCountrymaster());
                Hibernate.initialize(list.get(index).getStatemaster());
            }
            return list;
        } finally {
            session.close();
        }
    }

    public Departmentmaster findByDmId(Integer dmId) {
        Session session = HibernateUtil.getSessionPicoFactory();
        try {
            session.beginTransaction();
            Departmentmaster dm = (Departmentmaster) session.load(Departmentmaster.class, dmId);

            Hibernate.initialize(dm.getInstitutionmaster());
            Hibernate.initialize(dm.getSubinstitutionmaster());
            Hibernate.initialize(dm.getCountrymaster());
            Hibernate.initialize(dm.getStatemaster());

            return dm;
        } finally {
            session.close();
        }
    }

    public List<Departmentmaster> findBydmSimId(Integer simId) {
        Session session = HibernateUtil.getSessionPicoFactory();
        try {
            session.beginTransaction();
            List<Departmentmaster> list = session.createQuery("select u from Departmentmaster u where u.subinstitutionmaster.simId = :simId order by u.dmName").setParameter("simId", simId).list();            
            for (int index=0; index < list.size(); ++index) {
                    Hibernate.initialize(list.get(index).getInstitutionmaster());
                    Hibernate.initialize(list.get(index).getSubinstitutionmaster());
                    Hibernate.initialize(list.get(index).getCountrymaster());
                    Hibernate.initialize(list.get(index).getStatemaster());
            }
            return list;
        } finally {
            session.close();
        }
    }

    public List<Departmentmaster> findByImId(Short imId) {
        Session session = HibernateUtil.getSessionPicoFactory();
        try {
            session.beginTransaction();
            List<Departmentmaster> list = session.createQuery("select u from Departmentmaster u where u.institutionmaster.imId = :imId").setParameter("imId", imId).list();
            for (int index = 0; index < list.size(); ++index) {
                Hibernate.initialize(list.get(index).getInstitutionmaster());
                Hibernate.initialize(list.get(index).getSubinstitutionmaster());
                Hibernate.initialize(list.get(index).getCountrymaster());
                Hibernate.initialize(list.get(index).getStatemaster());
            }
            return list;
        } finally {
            session.close();
        }
    }

    public Departmentmaster findDeptByDMShortName(String dmShortName) {
        Session session = HibernateUtil.getSessionPicoFactory();
        try {
            session.beginTransaction();
            List<Departmentmaster> dmList = session.createQuery("select distinct(u) from Departmentmaster u where u.dmShortName = :dmShortName")
                    .setParameter("dmShortName", dmShortName)
                    .list();
            Hibernate.initialize(dmList.get(0).getInstitutionmaster());
            Hibernate.initialize(dmList.get(0).getSubinstitutionmaster());
            Hibernate.initialize(dmList.get(0).getCountrymaster());
            Hibernate.initialize(dmList.get(0).getStatemaster());

            return dmList.get(0);
        } finally {
            session.close();
        }
    }

    public List<Departmentmaster> findDeptByName(String dmName) {
        Session session = HibernateUtil.getSessionPicoFactory();
        try {
            session.beginTransaction();
            List<Departmentmaster> dmList = session.createQuery("select distinct(u) from Departmentmaster u where u.dmName = :dmName")
                    .setParameter("dmName", dmName)
                    .list();
            for (int index = 0; index < dmList.size(); ++index) {
                Hibernate.initialize(dmList.get(index).getInstitutionmaster());
                Hibernate.initialize(dmList.get(index).getSubinstitutionmaster());
                Hibernate.initialize(dmList.get(index).getCountrymaster());
                Hibernate.initialize(dmList.get(index).getStatemaster());
            }
            return dmList;
        } finally {
            session.close();
        }
    }

    public List<Departmentmaster> findDeptByShortName(String dmShortName) {
        Session session = HibernateUtil.getSessionPicoFactory();
        try {
            session.beginTransaction();
            List<Departmentmaster> dmList = session.createQuery("select distinct(u) from Departmentmaster u where u.dmShortName = :dmShortName")
                    .setParameter("dmShortName", dmShortName)
                    .list();
            for (int index = 0; index < dmList.size(); ++index) {
                Hibernate.initialize(dmList.get(index).getInstitutionmaster());
                Hibernate.initialize(dmList.get(index).getSubinstitutionmaster());
                Hibernate.initialize(dmList.get(index).getCountrymaster());
                Hibernate.initialize(dmList.get(index).getStatemaster());
            }
            return dmList;
        } finally {
            session.close();
        }
    }

    public List<Departmentmaster> findDepartmentForUser(Integer erpmuId, Integer simId) {
        Session session = HibernateUtil.getSessionPicoFactory();
        try {
            session.beginTransaction();
            List<Departmentmaster> dmList = session.createQuery("select distinct(u) from Departmentmaster u, Erpmuserrole r where r.erpmusers.erpmuId = :erpmuId and r.departmentmaster.dmId = u.dmId and u.subinstitutionmaster.simId =:simId order by u.dmName")
                    .setParameter("erpmuId", erpmuId)
                    .setParameter("simId", simId).list();

            for (int index = 0; index < dmList.size(); ++index) {
                Hibernate.initialize(dmList.get(index).getInstitutionmaster());
                Hibernate.initialize(dmList.get(index).getSubinstitutionmaster());
                Hibernate.initialize(dmList.get(index).getCountrymaster());
                Hibernate.initialize(dmList.get(index).getStatemaster());
            }

            return dmList;
        } finally {
            session.close();
        }
    }

    public List<Departmentmaster> findDepartmentForAdmin(Short imId) {
        Session session = HibernateUtil.getSessionPicoFactory();
        try {
            session.beginTransaction();
            List<Departmentmaster> dmList = session.createQuery("select u from Departmentmaster u where u.institutionmaster.imId =:imId")
                    .setParameter("imId", imId)
                    .list();
            for (int index = 0; index < dmList.size(); ++index) {
                Hibernate.initialize(dmList.get(index).getInstitutionmaster());
                Hibernate.initialize(dmList.get(index).getSubinstitutionmaster());
                Hibernate.initialize(dmList.get(index).getCountrymaster());
                Hibernate.initialize(dmList.get(index).getStatemaster());
            }

            return dmList;
        } finally {
            session.close();
        }
    }

    public String findDefaultDepartment(Integer dmId) {
        Session session = HibernateUtil.getSessionPicoFactory();
        try {
            session.beginTransaction();
            List<Departmentmaster> dm = session.createQuery("select u from Departmentmaster u where u.dmId = :dmId")
                    .setParameter("dmId", dmId).list();

            return dm.get(0).getDmName();
        } finally {
            session.close();
        }
    }

    public List<Departmentmaster> findAllDepartmentsForUser(Integer erpmuId) {
        String SQL = "select distinct(u) from Departmentmaster u, Erpmuserrole r where r.erpmusers.erpmuId = :erpmuId and r.departmentmaster.dmId = u.dmId";
        Session session = HibernateUtil.getSessionPicoFactory();
        try {
            session.beginTransaction();
            List<Departmentmaster> dmList = session.createQuery(SQL).
                    setParameter("erpmuId", erpmuId).list();
            for (int index = 0; index < dmList.size(); ++index) {
                Hibernate.initialize(dmList.get(index).getInstitutionmaster());
                Hibernate.initialize(dmList.get(index).getSubinstitutionmaster());
                Hibernate.initialize(dmList.get(index).getCountrymaster());
                Hibernate.initialize(dmList.get(index).getStatemaster());
            }

            return dmList;
        } finally {
            session.close();
        }
    }

    public String findDepartmentShortName(Integer dmId) {
        Session session = HibernateUtil.getSessionPicoFactory();
        try {
            session.beginTransaction();
            String dmShortName = session.createQuery("select u.dmShortName from Departmentmaster u where u.dmId = :dmId")
                    .setParameter("dmId", dmId)
                    .uniqueResult()
                    .toString();
            return dmShortName;

        } finally {
            session.close();
        }
    }
}
