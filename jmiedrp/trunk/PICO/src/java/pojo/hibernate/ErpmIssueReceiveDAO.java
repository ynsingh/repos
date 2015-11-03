/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pojo.hibernate;

/**
 *
 * @author erp01
 */

import utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Hibernate;
import java.util.List;


public class ErpmIssueReceiveDAO  {

     public void save(ErpmIssueReceive ir) {
        //Session session = HibernateUtil.getSession();
        Session session = HibernateUtil.getSessionPicoFactory();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(ir);
            tx.commit();
        } catch (RuntimeException re) {
            if (ir != null) {
                tx.rollback();
            }
            throw re;
        } finally {
            session.close();
        }
    }

    public void update(ErpmIssueReceive ir) {
        Session session = HibernateUtil.getSessionPicoFactory();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(ir);
            tx.commit();
        } catch (RuntimeException re) {
            if (ir != null) {
                tx.rollback();
            }
            throw re;
        } finally {
            session.close();
        }
    }

     public void delete(ErpmIssueReceive ir) {
        Session session = HibernateUtil.getSessionPicoFactory();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(ir);
            tx.commit();
        } catch (RuntimeException re) {
            if (ir != null) {
                tx.rollback();
            }
            throw re;
        } finally {
            session.close();
        }
    }

    public ErpmIssueReceive findByErpmisrId(Integer isrId) {
        Session session = HibernateUtil.getSessionPicoFactory();
        try {
            session.beginTransaction();
            ErpmIssueReceive erpmIsr = (ErpmIssueReceive) session.load(ErpmIssueReceive.class, isrId);
            Hibernate.initialize(erpmIsr.getInstitutionmaster());
            Hibernate.initialize(erpmIsr.getSubinstitutionmaster());
            Hibernate.initialize(erpmIsr.getDepartmentmaster());
            Hibernate.initialize(erpmIsr.getCommitteemaster());
            Hibernate.initialize(erpmIsr.getEmployeemaster());
            Hibernate.initialize(erpmIsr.getErpmIssueMaster());

            return erpmIsr;
        } finally {
            session.close();
        }
    }

    public List<ErpmIssueReceive> findReceiveItemsForUserDepartments(Integer erpmuId) {
        Session session = HibernateUtil.getSessionPicoFactory();
        try {
            int index = 0;

            String SQL = "Select u from ErpmIssueReceive u "
                    +"where u.departmentmaster.dmId in "
                    + "(Select d.departmentmaster.dmId from Erpmuserrole d where d.erpmusers.erpmuId = :erpmuId)";
            session.beginTransaction();

            List<ErpmIssueReceive> list  = session.createQuery(SQL).setParameter("erpmuId",erpmuId).list();

            for (index = 0; index < list.size(); ++index) {
                Hibernate.initialize(list.get(index).getEmployeemaster());
                Hibernate.initialize(list.get(index).getErpmIssueMaster());
                Hibernate.initialize(list.get(index).getCommitteemaster());
            }
            return list;
        } finally {
            session.close();
        }
    }

     
}
