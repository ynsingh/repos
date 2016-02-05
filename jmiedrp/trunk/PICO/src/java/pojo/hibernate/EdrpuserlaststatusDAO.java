
package pojo.hibernate;

/**
 *
 *@author <a href="mailto:jaivirpal@gmail.com">Jaivir Singh</a>2016 
 */
import utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import utils.ExceptionLogUtil;

public class EdrpuserlaststatusDAO  {



    
    public void save(Edrpuserlaststatus edrpuser) {
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


    public void update(Edrpuserlaststatus edrpuser) {
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


    public void delete(Edrpuserlaststatus edrpuser) {
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

}
