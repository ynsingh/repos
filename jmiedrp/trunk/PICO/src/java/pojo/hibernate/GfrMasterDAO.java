/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo.hibernate;

import utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Hibernate;
import java.util.List;

/**
 * @author erp03
 * @author <a href="mailto:jaivirpal@gmail.com">Jaivir Singh</a>2015
 */
public class GfrMasterDAO {
     public void save(GfrMaster  GfrMaste) {
        Session session = HibernateUtil.getSessionPicoFactory();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(GfrMaste);
            tx.commit();
        }
        catch (RuntimeException re) {
            if(GfrMaste != null)
                tx.rollback();
            throw re;
        }
        finally {
            session.close();
        }
    }

    public void update(GfrMaster GfrMaste) {
        Session session = HibernateUtil.getSessionPicoFactory();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(GfrMaste);
            tx.commit();
        }
        catch (RuntimeException re) {
            if(GfrMaste != null)
                tx.rollback();
            throw re;
        }
        finally {
            session.close();
        }
    }
 public GfrMaster findBygrfMasterId(int Id) {
        Session session = HibernateUtil.getSessionPicoFactory();
        GfrMaster grfMaster  = new GfrMaster ();

        try {
            session.beginTransaction();
            grfMaster  = (GfrMaster) session.load(GfrMaster.class , Id);

            Hibernate.initialize(grfMaster); //.getImName());


            return grfMaster;
        }
        finally {
            session.close();
            }
        }
    
    public void delete(GfrMaster  gfrMaster) {
        Session session = HibernateUtil.getSessionPicoFactory();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(gfrMaster);
            tx.commit();
        }
        catch (RuntimeException re) {
            if(gfrMaster != null)
                tx.rollback();
            throw re;
        }
        finally {
            session.close();
        }
    }

    public List<Budgetheadmaster > findAll() {
        Session session = HibernateUtil.getSessionPicoFactory();
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
        Session session = HibernateUtil.getSessionPicoFactory();
        try {
            Budgetheadmaster  bhm  = (Budgetheadmaster ) session.load(Budgetheadmaster .class , bhmId);
            Hibernate.initialize(bhm);
            return bhm;
        }
        finally {
            session.close();
            }
    }

 public List<GfrMaster> findListOfgfrMasterForGfrMapping(Short erpmProgramId) {
        Session session = HibernateUtil.getSessionPicoFactory();
        try {
          
            List<GfrMaster> gfrMasterList  =  session.createQuery("Select u from GfrMaster u where u.gfrGfrId NOT IN  (Select m.gfrMaster.gfrGfrId from GfrProgramMapping m where m.erpmprogram.erpmpId = :erpmProgramId)").setParameter("erpmProgramId", erpmProgramId).list();
           
            return gfrMasterList;
        }
        finally {
            session.close();
            }
   }


 public List<GfrMaster> findListOfgfrMaster() {
        Session session = HibernateUtil.getSessionPicoFactory();
        try {

            List<GfrMaster> gfrMasterList  =  session.createQuery("Select u from GfrMaster u ").list();
           
            return gfrMasterList;
        }
        finally {
            session.close();
            }
   }

 public List<Budgetheadmaster> findforInsitutetBudgetHeadId(Integer erpmuId) {
        Session session = HibernateUtil.getSessionPicoFactory();
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
