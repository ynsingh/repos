
package pojo.hibernate;

import utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;


public class ErpmmoduleDAO  {
    public void save(Erpmmodule erpmm) {
Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(erpmm);
            tx.commit();
        }
        catch (RuntimeException re) {
            if(erpmm != null)
                tx.rollback();
            throw re;
        }
        finally {
            session.close();
        }    }

     public void update(Erpmmodule erpmm) {
Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(erpmm);
            tx.commit();
        }
        catch (RuntimeException re) {
            if(erpmm != null)
                tx.rollback();
            throw re;
        }
        finally {
            session.close();
        }    }

    public void delete(Erpmmodule erpmm) {
Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(erpmm);
            tx.commit();
        }
        catch (RuntimeException re) {
            if(erpmm != null)
                tx.rollback();
            throw re;
        }
        finally {
            session.close();
        }    }

    public List<Erpmmodule> findAll() {
        Session session = HibernateUtil.getSession();

        try {
            session.beginTransaction();
        List<Erpmmodule> list = session.createQuery("from Erpmmodule").list();
        return list;
        }
        finally {
            session.close();
            }
    }

}





//package pojo.hibernate;
//
//import utils.BaseDAO;
//import java.util.List;
//
//public class ErpmmoduleDAO  extends BaseDAO {
//    public void save(Erpmmodule erpmm) {
//        try {
//            beginTransaction();
//            getSession().save(erpmm);
//            commitTransaction();
//        }
//        catch (RuntimeException re) {
//            re.printStackTrace();
//            throw re;
//        }
//    }
//
//     public void update(Erpmmodule erpmm) {
//        try {
//            beginTransaction();
//            getSession().update(erpmm);
//            commitTransaction();
//        }
//        catch (RuntimeException re) {
//            re.printStackTrace();
//            throw re;
//        }
//    }
//
//    public void delete(Erpmmodule erpmm) {
//        try {
//            beginTransaction();
//            getSession().delete(erpmm);
//            commitTransaction();
//        }
//        catch (RuntimeException re) {
//            re.printStackTrace();
//            throw re;
//        }
//    }
//
//    public List<Erpmmodule> findAll() {
//        beginTransaction();
//        List<Erpmmodule> list = getSession().createQuery("from Erpmmodule").list();
//        commitTransaction();
//        return list;
//    }
//
//}