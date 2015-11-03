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
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class EdrpusersDAO  {



    
    public void save(Edrpusers edrpuser) {
	//SessionFactory sessionl = new Configuration().configure("login.cfg.xml").buildSessionFactory();
	//Session session=sessionl.openSession();	
        Session session = HibernateUtil.getSessionLoginFactory();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(edrpuser);
            tx.commit();
        }
        catch (RuntimeException re) {
            if(edrpuser != null)
                tx.rollback();
            throw re;
        }
        finally {
            session.close();
        }
    }


    public void update(Edrpusers edrpuser) {
	//SessionFactory sessionl = new Configuration().configure("login.cfg.xml").buildSessionFactory();
	//Session session=sessionl.openSession();	
        Session session = HibernateUtil.getSessionLoginFactory();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(edrpuser);
            tx.commit();
        }
        catch (RuntimeException re) {
            if(edrpuser != null)
                tx.rollback();
            throw re;
        }
        finally {
            session.close();
        }
    }


    public void delete(Edrpusers edrpuser) {
	//SessionFactory sessionl = new Configuration().configure("login.cfg.xml").buildSessionFactory();
	//Session session=sessionl.openSession();	
        Session session = HibernateUtil.getSessionLoginFactory();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(edrpuser);
            tx.commit();
        }
        catch (RuntimeException re) {
            if(edrpuser != null)
                tx.rollback();
            throw re;
        }
        finally {
            session.close();
        }
    }


    public List<Edrpusers> findAll() {
	//SessionFactory sessionl = new Configuration().configure("login.cfg.xml").buildSessionFactory();
	//Session session=sessionl.openSession();	
        Session session = HibernateUtil.getSessionLoginFactory();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            List<Edrpusers> list = session.createQuery("select u from Edrpusers u").list();
		Hibernate.initialize(list);
            return list;
        }
        catch (RuntimeException re) {
                throw re;
        }
        finally {
            session.close();
            }
        }
      public List<Edrpusers> RetrieveUser(String username, String password){

	//SessionFactory sessionl = new Configuration().configure("login.cfg.xml").buildSessionFactory();
	//Session session=sessionl.openSession();	
		
		Session session = HibernateUtil.getSessionLoginFactory();
            //Session session = HibernateUtil.getSession();
            try {
		session.beginTransaction();
                List<Edrpusers> list  = session.createQuery("select u from Edrpusers u where u.edrpuName=:username and u.edrpuPassword=:password ").
                                               setParameter("username",username).setParameter("password",password).list();

                return list;
            }
            finally {
                session.close();
            }
     }
}
