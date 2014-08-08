/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author afreen
 */
package pojo.hibernate;

import utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Hibernate;
import java.util.List;

public class DepartmentalBudgetAllocationDAO {

    public void save(DepartmentalBudgetAllocation dba) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(dba);
            tx.commit();
        } catch (RuntimeException re) {
            if (dba != null) {
                tx.rollback();
            }
            throw re;
        } finally {
            session.close();
        }
    }

    public void update(DepartmentalBudgetAllocation dba) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(dba);
            tx.commit();
        } catch (RuntimeException re) {
            if (dba != null) {
                tx.rollback();
            }
            throw re;
        } finally {
            session.close();
        }
    }

    public void delete(DepartmentalBudgetAllocation dba) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(dba);
            tx.commit();
        } catch (RuntimeException re) {
            if (dba != null) {
                tx.rollback();
            }
            throw re;
        } finally {
            session.close();
        }
    }

    public List<DepartmentalBudgetAllocation> findAll() {
        Session session = HibernateUtil.getSession();
        try {
            int index = 0;
            session.beginTransaction();
            List<DepartmentalBudgetAllocation> list = session.createQuery("select u from DepartmentalBudgetAllocation u").list();
            for (index = 0; index < list.size(); ++index) {
                Hibernate.initialize(list.get(index).getInstitutionmaster());
                Hibernate.initialize(list.get(index).getSubinstitutionmaster());
                Hibernate.initialize(list.get(index).getDepartmentmaster());
                Hibernate.initialize(list.get(index).getBudgetheadmaster());
//                Hibernate.initialize(list.get(index).getErpmIndentMasters());
            }
            return list;
        } finally {
            session.close();
        }
    }

    public DepartmentalBudgetAllocation findByDbaId(Integer dbaId) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            DepartmentalBudgetAllocation dba = (DepartmentalBudgetAllocation) session.load(DepartmentalBudgetAllocation.class, dbaId);
            Hibernate.initialize(dba); //.getImName());

            return dba;
        } finally {
            session.close();
        }
    }

    public List<DepartmentalBudgetAllocation> findByImId(Short imId) {
        Session session = HibernateUtil.getSession();
        try {
            int index = 0;
            session.beginTransaction();
            List<DepartmentalBudgetAllocation> list = session.createQuery("Select u from DepartmentalBudgetAllocation u where u.institutionmaster.imId = :imId").setParameter("imId", imId).list();
            for (index = 0; index < list.size(); ++index) {
                Hibernate.initialize(list.get(index).getInstitutionmaster());
                Hibernate.initialize(list.get(index).getSubinstitutionmaster());
                Hibernate.initialize(list.get(index).getDepartmentmaster());
                Hibernate.initialize(list.get(index).getBudgetheadmaster());
            }
            return list;
        } finally {
            session.close();
        }
    }

    public List<DepartmentalBudgetAllocation> findBySimId(Integer SimId) {
        Session session = HibernateUtil.getSession();
        try {
            int index = 0;
            session.beginTransaction();
            List<DepartmentalBudgetAllocation> list = session.createQuery("select u from DepartmentalBudgetAllocation  u where u.subinstitutionmaster.simId = :SimId").setParameter("SimId", SimId).list();
            for (index = 0; index < list.size(); ++index) {
                Hibernate.initialize(list.get(index).getInstitutionmaster());
                Hibernate.initialize(list.get(index).getSubinstitutionmaster());
                Hibernate.initialize(list.get(index).getDepartmentmaster());
                Hibernate.initialize(list.get(index).getBudgetheadmaster());
            }
            return list;
        } finally {
            session.close();
        }
    }

    public List<DepartmentalBudgetAllocation> findForUserSubinstitution(Integer erpmuId) {
        Session session = HibernateUtil.getSession();
        try {
            int index = 0;
            session.beginTransaction();
            List<DepartmentalBudgetAllocation> list = session.createQuery("select u from DepartmentalBudgetAllocation  u where u.subinstitutionmaster.simId in (Select s.subinstitutionmaster.simId from Erpmuserrole s where s.erpmusers.erpmuId = :erpmuId)").setParameter("erpmuId", erpmuId).list();
            for (index = 0; index < list.size(); ++index) {
                Hibernate.initialize(list.get(index).getInstitutionmaster());
                Hibernate.initialize(list.get(index).getSubinstitutionmaster());
                Hibernate.initialize(list.get(index).getDepartmentmaster());
                Hibernate.initialize(list.get(index).getBudgetheadmaster());
            }
            return list;
        } finally {
            session.close();
        }
    }

    public List<DepartmentalBudgetAllocation> findByDMId(Integer dmId) {
        Session session = HibernateUtil.getSession();
        try {
            int index = 0;
            session.beginTransaction();
            List<DepartmentalBudgetAllocation> list = session.createQuery("select distinct(u) from DepartmentalBudgetAllocation u where u.departmentmaster.dmId = :dmId").setParameter("dmId", dmId).list();
            for (index = 0; index < list.size(); ++index) {
                Hibernate.initialize(list.get(index).getInstitutionmaster());
                Hibernate.initialize(list.get(index).getSubinstitutionmaster());
                Hibernate.initialize(list.get(index).getDepartmentmaster());
                Hibernate.initialize(list.get(index).getBudgetheadmaster());
            }
            return list;
        } finally {
            session.close();
        }
    }

    public List<DepartmentalBudgetAllocation> findForUserDepartments(Integer erpmuId) {
        Session session = HibernateUtil.getSession();
        try {
            int index = 0;
            session.beginTransaction();
            List<DepartmentalBudgetAllocation> list = session.createQuery("select distinct(u) from DepartmentalBudgetAllocation u where u.departmentmaster.dmId in (Select d.departmentmaster.dmId from Erpmuserrole d where d.erpmusers.erpmuId = :erpmuId)").setParameter("erpmuId", erpmuId).list();
            for (index = 0; index < list.size(); ++index) {
                Hibernate.initialize(list.get(index).getInstitutionmaster());
                Hibernate.initialize(list.get(index).getSubinstitutionmaster());
                Hibernate.initialize(list.get(index).getDepartmentmaster());
                Hibernate.initialize(list.get(index).getBudgetheadmaster());
            }
            return list;
        } finally {
            session.close();
        }
    }

    public DepartmentalBudgetAllocation findForDepartmentBybhmId(short bhmId, Integer dmId) {
        Session session = HibernateUtil.getSession();
        try {
            int index = 0;
            session.beginTransaction();
            List<DepartmentalBudgetAllocation> dba = session.createQuery("Select u from DepartmentalBudgetAllocation u where u.budgetheadmaster.bhmId = :bhmId and u.departmentmaster.dmId = :dmId")
                    .setParameter("bhmId", bhmId)
                    .setParameter("dmId", dmId).list();
            for (index = 0; index < dba.size(); ++index) {
                Hibernate.initialize(dba.get(index).getDepartmentmaster());

            }
            return dba.get(0);
        } finally {
            session.close();
        }
    }
}
