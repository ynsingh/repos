

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pojo.hibernate;

/**
 *
 * @author kazim
 * @author <a href="mailto:jaivirpal@gmail.com">Jaivir Singh</a>2015
 */
import utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Hibernate;
import java.util.List;

public class InstitutionmasterDAO {

    public void save(Institutionmaster im) {
        Session session = HibernateUtil.getSessionPicoFactory();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(im);
            tx.commit();
        }
        catch (RuntimeException re) {
            if(im != null)
                tx.rollback();
            throw re;
        }
        finally {
            session.close();
        }
    }

    public void update(Institutionmaster im) {
        Session session = HibernateUtil.getSessionPicoFactory();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(im);
            tx.commit();
        }
        catch (RuntimeException re) {
            if(im != null)
                tx.rollback();
            throw re;
        }
        finally {
            session.close();
        }
    }

    public void delete(Institutionmaster im) {
        Session session = HibernateUtil.getSessionPicoFactory();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(im);
            tx.commit();
        }
        catch (RuntimeException re) {
            if(im != null)
                tx.rollback();
            throw re;
        }
        finally {
            session.close();
        }
    }

    public List<Institutionmaster> findAll() {
        Session session = HibernateUtil.getSessionPicoFactory();
        try {
            int index = 0;
            session.beginTransaction();
            List<Institutionmaster> list = session.createQuery("select u from Institutionmaster u").list();
            for (index=0; index < list.size(); ++index) {
                Hibernate.initialize(list.get(index).getErpmGenMaster());
                Hibernate.initialize(list.get(index).getCountrymaster());
                Hibernate.initialize(list.get(index).getStatemaster());
            }
            return list;
        }
        finally {
            session.close();
            }
        }


    public Institutionmaster findByImId(Short imId) {
        Session session = HibernateUtil.getSessionPicoFactory();          
        try {
            session.beginTransaction();
            Institutionmaster im  = (Institutionmaster) session.load(Institutionmaster.class , imId);            
            Hibernate.initialize(im); //.getImName());

            return im;
        }
        finally {
            session.close();
            }
        }
    
    
    public List<Institutionmaster> findInstForUser(Integer erpmuId) {        
        Session session = HibernateUtil.getSessionPicoFactory();
        try {
            session.beginTransaction();
            List<Institutionmaster> imList = session.createQuery("select distinct(u) from Institutionmaster u, Erpmuserrole r where r.erpmusers.erpmuId = :erpmuId and r.institutionmaster.imId = u.imId").setParameter("erpmuId", erpmuId).list();
            return imList;
        }
        finally {
            session.close();
            }
        }

/*public List<Institutionmaster> findForUser(Integer erpmuId) {
        Session session = HibernateUtil.getSession();

        try {
            session.beginTransaction();
            List<Institutionmaster> imList = session.createQuery("select distinct(u) from Institutionmaster u, Erpmuserrole r where r.erpmusers.erpmuId = :erpmuId and r.institutionmaster.imId = u.imId").setParameter("erpmuId", erpmuId).list();
            return imList;
        }
        finally {
            session.close();
            }
        }
*/
    public Institutionmaster findInstByIMShortName(String imShortName) {
        Session session = HibernateUtil.getSessionPicoFactory();
        try {
            session.beginTransaction();
            List<Institutionmaster> imList = session.createQuery("select distinct(u) from Institutionmaster u where u.imShortName = :imShortName").setParameter("imShortName", imShortName).list();
            return imList.get(0);

        }
        finally {
            session.close();
            }
        }

    public Institutionmaster findInstByIMFullName(String imName) {
        Session session = HibernateUtil.getSessionPicoFactory();
        try {
            session.beginTransaction();
            List<Institutionmaster> imList = session.createQuery("select distinct(u) from Institutionmaster u where u.imName = :imName").setParameter("imName",imName).list();
            return imList.get(0);
        }
        finally {
            session.close();
            }
        }

    public String findDefaultInstByID(Short imId) {
        Session session = HibernateUtil.getSessionPicoFactory();
        try {
            session.beginTransaction();
            List<Institutionmaster> imList = session.createQuery("select u from Institutionmaster u where u.imId = :imId").setParameter("imId",imId).list();
            return imList.get(0).getImName();
        }
        finally {
            session.close();
            }
        }


    public List<Institutionmaster> findInstByIMName(String imName) {
        Session session = HibernateUtil.getSessionPicoFactory();
        try {
            session.beginTransaction();
        List<Institutionmaster> imList = session.createQuery("select distinct(u) from Institutionmaster u where u.imName = :imName").setParameter("imName",imName).list();
        return imList;
        }
        finally {
            session.close();
            }
    }

    public List<Institutionmaster> findInstByShortName(String imShortName) {
        Session session = HibernateUtil.getSessionPicoFactory();
        try {
            session.beginTransaction();
        List<Institutionmaster> imList = session.createQuery("select distinct(u) from Institutionmaster u where u.imShortName = :imShortName").setParameter("imShortName", imShortName).list();
        return imList;
        }
        finally {
            session.close();
            }
    } 
}
