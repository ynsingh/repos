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


public class InstitutionuserroleDAO {

    public void save(Institutionuserroles iur) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(iur);
            tx.commit();
        }
        catch (RuntimeException re) {
            if(iur != null)
                tx.rollback();
            throw re;
        }
        finally {
            session.close();
        }
    }

    public void update(Institutionuserroles iur) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(iur);
            tx.commit();
        }
        catch (RuntimeException re) {
            if(iur != null)
                tx.rollback();
            throw re;
        }
        finally {
            session.close();
        }
    }

    public void delete(Institutionuserroles iur) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(iur);
            tx.commit();
        }
        catch (RuntimeException re) {
            if(iur != null)
                tx.rollback();
            throw re;
        }
        finally {
            session.close();
        }
    }

    public List<Institutionuserroles> findAll() {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            List<Institutionuserroles> list = session.createQuery("select u from Institutionuserroles u").list();
            return list;
        }
        finally {
            session.close();
            }
    }

    public Institutionuserroles findByIURId(Integer IUR_ID) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Institutionuserroles iur  =  (Institutionuserroles) session.load(Institutionuserroles.class , IUR_ID);
            Hibernate.initialize(iur.getInstitutionmaster());
            
            return iur;
        }
        finally {
            session.close();
            }
    }

    public List<Institutionuserroles> findByInstitutionId(Short IUR_IM_ID) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            List<Institutionuserroles> iurList  =  session.createQuery("select u from Institutionuserroles u where u.institutionmaster.imId = :iurimid ").
                                                            setParameter("iurimid",IUR_IM_ID).list();
            return iurList;
        }
        finally {
            session.close();
            }
    }

    public Integer  copyGenericRolePrivileges(Byte gurId, Integer iurId, Short imId) {
        Session session = HibernateUtil.getSession();
        try {
            Integer i = session.createSQLQuery("call copy_genericroleprivileges(:gurId,:iurId, :imId)")
                                .setParameter("gurId", gurId)
                                .setParameter("iurId", iurId)
                                .setParameter("imId", imId)
                                .executeUpdate();
            return i;
        }
        finally {
            session.close();
            }
    }

    public Institutionuserroles findInstitutionAdministrator(Short imId) {
        Session session = HibernateUtil.getSession();
        try {
            List<Institutionuserroles> iur  =  session.createQuery("select u from Institutionuserroles u where u.institutionmaster.imId = :imId and upper(u.iurName) = 'ADMINISTRATOR'").
                                                        setParameter("imId",imId).list();
            return iur.get(0);
        }
        finally {
            session.close();
            }
    }
}

