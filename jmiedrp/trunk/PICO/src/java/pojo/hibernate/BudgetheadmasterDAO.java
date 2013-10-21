
package pojo.hibernate;

import utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Hibernate;
import java.util.List;

public class BudgetheadmasterDAO  {

    public void save(Budgetheadmaster  bhm) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(bhm);
            tx.commit();
        }
        catch (RuntimeException re) {
            if(bhm != null)
                tx.rollback();
            throw re;
        }
        finally {
            session.close();
        }
    }

    public void update(Budgetheadmaster bhm) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(bhm);
            tx.commit();
        }
        catch (RuntimeException re) {
            if(bhm != null)
                tx.rollback();
            throw re;
        }
        finally {
            session.close();
        }
    }

    public void delete(Budgetheadmaster  bhm) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(bhm);
            tx.commit();
        }
        catch (RuntimeException re) {
            if(bhm != null)
                tx.rollback();
            throw re;
        }
        finally {
            session.close();
        }
    }

    public List<Budgetheadmaster > findAll() {        
        Session session = HibernateUtil.getSession();
        try {
            int index = 0;
            List<Budgetheadmaster> list = session.createQuery("select u from Budgetheadmaster  u").list();
            for (index=0; index < list.size(); ++index)
                Hibernate.initialize(list.get(index).getInstitutionmaster());
            return list;
        }
        finally {
            session.close();
            }

    }

    public Budgetheadmaster  findBybhmId(short bhmId)
    {
        Session session = HibernateUtil.getSession();
        try {
            Budgetheadmaster  bhm  = (Budgetheadmaster ) session.load(Budgetheadmaster .class , bhmId);
            Hibernate.initialize(bhm);
            return bhm;
        }
        finally {
            session.close();
            }
    }

 public List<Budgetheadmaster> findByImId(Short imId) {
        Session session = HibernateUtil.getSession();
        try {
            int index = 0;
            List<Budgetheadmaster> bhmList  =  session.createQuery("Select u from Budgetheadmaster u where u.institutionmaster.imId = :imId").setParameter("imId", imId).list();
            for (index=0; index < bhmList.size(); ++index)
                Hibernate.initialize(bhmList.get(index).getInstitutionmaster());
            return bhmList;
        }
        finally {
            session.close();
            }
   }

 public List<Budgetheadmaster> findforInsitutetBudgetHeadId(Integer erpmuId) {
        Session session = HibernateUtil.getSession();
        try {
            //int index = 0;
        List<Budgetheadmaster> bhmList  =  session.createQuery("Select u from Budgetheadmaster  u where u.institutionmaster.imId  in (select r.institutionmaster.imId from Erpmuserrole r where r.erpmusers.erpmuId = :erpmuId)").setParameter("erpmuId",erpmuId).list();
            /*for (index=0; index < list.size(); ++index)
                Hibernate.initialize(list.get(index).getInstitutionmaster());*/
        return bhmList;
        }
        finally {
            session.close();
            }
   }



}

