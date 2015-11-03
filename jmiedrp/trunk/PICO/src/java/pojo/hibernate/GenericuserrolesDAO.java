/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author sknaqvi
 * @author <a href="mailto:jaivirpal@gmail.com">Jaivir Singh</a>2015
 */

package pojo.hibernate;

import org.hibernate.Session;
import utils.HibernateUtil;
import org.hibernate.Transaction;
import org.hibernate.Hibernate;
import java.util.List;
//import sun.management.snmp.jvminstr.JvmThreadInstanceEntryImpl.ThreadStateMap.Byte0;

public class GenericuserrolesDAO {

    public void save(Genericuserroles gur) {
        Session session = HibernateUtil.getSessionPicoFactory();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(gur);
            tx.commit();
        }
        catch (RuntimeException re) {
            if(gur != null)
                tx.rollback();
            throw re;
        }
        finally {
            session.close();
        }
    }

     public void update(Genericuserroles gur) {
        Session session = HibernateUtil.getSessionPicoFactory();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(gur);
            tx.commit();
        }
        catch (RuntimeException re) {
            if(gur != null)
                tx.rollback();
            throw re;
        }
        finally {
            session.close();
        }
    }

    public void delete(Genericuserroles gur) {
        Session session = HibernateUtil.getSessionPicoFactory();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(gur);
            tx.commit();
        }
        catch (RuntimeException re) {
            if(gur != null)
                tx.rollback();
            throw re;
        }
        finally {
            session.close();
        }
    }

    public List<Genericuserroles> findAll() {
        Session session = HibernateUtil.getSessionPicoFactory();
        try {
            session.beginTransaction();            
            List<Genericuserroles> list = session.createQuery("from Genericuserroles").list();
            return list;
        }
        finally {
            session.close();
            }
    }

    public String RetrieveRoleDescription(Byte gurId) {       
        Session session = HibernateUtil.getSessionPicoFactory();
        try {
            session.beginTransaction();            
            List<Genericuserroles> list = session.createQuery("from Genericuserroles u where u.gurId = :gurId").setParameter("gurId", gurId).list();
            return list.get(0).getGurDescription();
        }
        finally {
            session.close();
            }
    }

    public Byte RetrieveRoleId(String role) {                        
        Session session = HibernateUtil.getSessionPicoFactory();
        try {
            session.beginTransaction();            
            String roleId = session.createQuery("select u.gurId from Genericuserroles u where u.gurRoleName = :role").setParameter("role", role).list().get(0).toString();
            return  Byte.parseByte(roleId);
        }
        finally {
            session.close();
            }        
    }

    public Genericuserroles findByName(String roleName) {
        String query = "Select u from Genericuserroles u where u.gurRoleName = :roleName";
        Session session = HibernateUtil.getSessionPicoFactory();
        try {
            
            session.beginTransaction();            
            Genericuserroles genericUserRoles = (Genericuserroles) session.createQuery(query)
                                                                           .setParameter("roleName", roleName)
                                                                           .list().get(0);
            return genericUserRoles;
        }
        finally {
            session.close();
            }
    }
}
