/**
 *
 * @author sknaqvi
 * @author <a href="mailto:jaivirpal@gmail.com">Jaivir Singh</a>2015
 */

package pojo.hibernate;

import utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Hibernate;
import java.util.List;

public class InstitutionroleprivilegesDAO {

    public void save(Institutionroleprivileges irp) {
        Session session = HibernateUtil.getSessionPicoFactory();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(irp);
            tx.commit();
        }
        catch (RuntimeException re) {
            if(irp != null)
                tx.rollback();
            throw re;
        }
        finally {
            session.close();
        }
    }

    public void update(Institutionroleprivileges irp) {
        Session session = HibernateUtil.getSessionPicoFactory();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(irp);
            tx.commit();
        }
        catch (RuntimeException re) {
            if(irp != null)
                tx.rollback();
            throw re;
        }
        finally {
            session.close();
        }
    }

    public void delete(Institutionroleprivileges irp) {
        Session session = HibernateUtil.getSessionPicoFactory();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(irp);
            tx.commit();
        }
        catch (RuntimeException re) {
            if(irp != null)
                tx.rollback();
            throw re;
        }
        finally {
            session.close();
        }
    }

    public List<Institutionroleprivileges> findAll() {                      
        Session session = HibernateUtil.getSessionPicoFactory();          
        try {
            session.beginTransaction();
            List<Institutionroleprivileges> list = session.createQuery("select u from Institutionroleprivileges u").list();        
            return list;
        }
        finally {
            session.close();
            }
    }


    //The following function retrieves the Institution Role Privilege record by iupID (The primary key)
    public Institutionroleprivileges findByIupId(Short iupId) {
        Session session = HibernateUtil.getSessionPicoFactory();          
        try {
            session.beginTransaction();
            List<Institutionroleprivileges> list = session.createQuery("select u from Institutionroleprivileges u where u.iupId = :iupId")
                                                            .setParameter("iupId", iupId)
                                                            .list();
            Hibernate.initialize(list.get(0).getErpmmodule());
            Hibernate.initialize(list.get(0).getErpmprogram());
            Hibernate.initialize(list.get(0).getInstitutionuserroles());
            
            return list.get(0);
        }
        finally {
            session.close();
        }
    }

    //The following function prepares a list of Institution Role Privileges for the given Institutional Role
    public List<Institutionroleprivileges> findByiurId(Integer iurId) {                       
        Session session = HibernateUtil.getSessionPicoFactory();          
        try {
            session.beginTransaction();
            List<Institutionroleprivileges> list = session.createQuery("select u from Institutionroleprivileges u where u.institutionuserroles.iurId = :iurId")
                                                            .setParameter("iurId", iurId)
                                                            .list();
            for(int index=0;index<list.size();++index) {
                Hibernate.initialize(list.get(index).getErpmmodule());
                Hibernate.initialize(list.get(index).getErpmprogram());                
            }
            return list;
        }
        finally {
            session.close();
        }
    }

    //The following function returns the instittution role Id contained in IUP_ID
    public Integer findInstitutionRoleForUserProfile(Short iupId) {        
        Session session = HibernateUtil.getSessionPicoFactory();          
        try {
            session.beginTransaction();
            List<Institutionroleprivileges> list  = session.createQuery("select u.institutionuserroles.iurId from Institutionroleprivileges u where u.iupId = :iupId)")
                                                            .setParameter("iupId", iupId).list();        
            return list.get(0).getInstitutionuserroles().getIurId();        
        }
        finally {
            session.close();
        }
    }

    public Integer CountRecordsForInstitutionRole(Integer iurId) {        
        Session session = HibernateUtil.getSessionPicoFactory();          
        try {
            session.beginTransaction();
            String list = session.createQuery("select count(u) from Institutionroleprivileges u where u.institutionuserroles.iurId = :iurId")
                                                            .setParameter("iurId", iurId)
                                                            .uniqueResult().toString();        
            return Integer.parseInt(list);
        }
        finally {
            session.close();
        }
    }
}
