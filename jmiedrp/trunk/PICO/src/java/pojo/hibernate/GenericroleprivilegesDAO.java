/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author sknaqvi
 * @author <a href="mailto:jaivirpal@gmail.com">Jaivir Singh</a>2015
 */

package pojo.hibernate;

import utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class GenericroleprivilegesDAO  {

    public void save(Genericroleprivileges grp) {
        Session session = HibernateUtil.getSessionPicoFactory();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(grp);
            tx.commit();
        }
        catch (RuntimeException re) {
            if(grp != null)
                tx.rollback();
            throw re;
        }
        finally {
            session.close();
        }
    }

     public void update(Genericroleprivileges grp) {
        Session session = HibernateUtil.getSessionPicoFactory();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(grp);
            tx.commit();
        }
        catch (RuntimeException re) {
            if(grp != null)
                tx.rollback();
            throw re;
        }
        finally {
            session.close();
        }
    }

    public void delete(Genericroleprivileges grp) {
        Session session = HibernateUtil.getSessionPicoFactory();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(grp);
            tx.commit();
        }
        catch (RuntimeException re) {
            if(grp != null)
                tx.rollback();
            throw re;
        }
        finally {
            session.close();
        }
    }

    public List<Genericroleprivileges> RetrievePrivilegesForGenericRole(Byte gurId) {
        Session session = HibernateUtil.getSessionPicoFactory();
        try {
            session.beginTransaction();
            List<Genericroleprivileges> plist = session.createQuery("from Genericroleprivileges u where u.genericuserroles.gurId = :gurId")
                                                  .setParameter("gurId", gurId).list();
            return plist;
        }
        finally {
            session.close();
        }
    }
    
    //The following method checks, if a Program identified by erpmId 
    //has been assigned Generic privileges identified by gurId
    public boolean CheckGenericPrivileges(Short erpmpId, Byte gurId) {
        String query = "Select u from Genericroleprivileges u where " +
                        "u.erpmprogram.erpmpId = :erpmpId and " +
                        "u.genericuserroles.gurId = :gurId";
        
        Session session = HibernateUtil.getSessionPicoFactory();
        try {
            session.beginTransaction();
            List<Genericroleprivileges> plist = session.createQuery(query)
                                                  .setParameter("erpmpId", erpmpId)
                                                  .setParameter("gurId", gurId).list();
            if(plist.size() > 0 )
                return true;
            else
                return false;
        }
        finally {
            session.close();
        }
        
    }

}
