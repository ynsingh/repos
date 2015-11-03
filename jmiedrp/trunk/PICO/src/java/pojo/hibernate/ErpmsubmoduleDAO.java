
package pojo.hibernate;

import java.util.List;
import utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Hibernate;

/**
 *@author <a href="mailto:jaivirpal@gmail.com">Jaivir Singh</a>2015
 */ 

public class ErpmsubmoduleDAO  {

    public void save(Erpmsubmodule erpmsm) {
        Session session = HibernateUtil.getSessionPicoFactory();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(erpmsm);
            tx.commit();
        }
        catch (RuntimeException re) {
            if(erpmsm != null)
                tx.rollback();
            throw re;
        }
        finally {
            session.close();
        }
    }


     public void update(Erpmsubmodule erpmsm) {
        Session session = HibernateUtil.getSessionPicoFactory();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(erpmsm);
            tx.commit();
        }
        catch (RuntimeException re) {
            if(erpmsm != null)
                tx.rollback();
            throw re;
        }
        finally {
            session.close();
        }
    }

    public void delete(Erpmsubmodule erpmsm) {
        Session session = HibernateUtil.getSessionPicoFactory();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(erpmsm);
            tx.commit();
        }
        catch (RuntimeException re) {
            if(erpmsm != null)
                tx.rollback();
            throw re;
        }
        finally {
            session.close();
        }
    }

    public List<Erpmsubmodule> findAll() {
        Session session = HibernateUtil.getSessionPicoFactory();

        try {
            session.beginTransaction();
            List<Erpmsubmodule> list = session.createQuery("from Erpmsubmodule").list();
            for (int i=0; i< list.size(); ++i) {
                Hibernate.initialize(list.get(i).getErpmmodule());
            }
            return list;
        }
        finally {
            session.close();
            }
        }

    public List<Erpmsubmodule> findByModuleId(Byte erpmmId) {
        Session session = HibernateUtil.getSessionPicoFactory();
        try {
            session.beginTransaction();
            List<Erpmsubmodule> list = session.createQuery("select e from Erpmsubmodule e where e.erpmmodule.erpmmId = :erpmmId order by esmOrder").setParameter("erpmmId", erpmmId).list();
            return list;
        }
        finally {
            session.close();
            }
        }

    public List<Erpmsubmodule> findAllParentMenu() {
        Session session = HibernateUtil.getSessionPicoFactory();
        try {
            session.beginTransaction();
            List<Erpmsubmodule> list = session.createQuery("from Erpmsubmodule u where u.esmName != 'Exit' AND  u.esmName != 'Welcome' order by esmOrder").list();
//            List<Erpmsubmodule> list = session.createQuery("select e from Erpmsubmodule e where e.esmName !=  order by esmOrder").setParameter("erpmmId", erpmmId).list();
            return list;
        }
        finally {
            session.close();
            }
        }
    
    public Erpmsubmodule findBySubmoduleId(Integer erpmSubModuleId) {
        Session session = HibernateUtil.getSessionPicoFactory();
        try {
            session.beginTransaction();
	        Erpmsubmodule erpmsm  =  new Erpmsubmodule();
	        erpmsm  = (Erpmsubmodule) session.load(Erpmsubmodule.class ,erpmSubModuleId );
                Hibernate.initialize(erpmsm);
	        return erpmsm;
        }
        finally {
            session.close();
            }
        }
    
}


