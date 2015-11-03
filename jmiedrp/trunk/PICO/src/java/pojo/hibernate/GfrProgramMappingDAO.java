/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pojo.hibernate;

//import utils.BaseDAO;

/**
 *
 * @author erp01
 * @author <a href="mailto:jaivirpal@gmail.com">Jaivir Singh</a>2015
 */

import utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Hibernate;
import java.util.List;

/**
 *
 * @author erp03
 */
public class GfrProgramMappingDAO {
     public void save(GfrProgramMapping  gfrProgramMapping) {
        Session session = HibernateUtil.getSessionPicoFactory();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(gfrProgramMapping);
            tx.commit();
        }
        catch (RuntimeException re) {
            if(gfrProgramMapping != null)
                tx.rollback();
            throw re;
        }
        finally {
            session.close();
        }
    }

    public void update(GfrProgramMapping gfrProgramMapping) {
        Session session = HibernateUtil.getSessionPicoFactory();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(gfrProgramMapping);
            tx.commit();
        }
        catch (RuntimeException re) {
            if(gfrProgramMapping != null)
                tx.rollback();
            throw re;
        }
        finally {
            session.close();
        }
    }

    public void delete(GfrProgramMapping  gfrProgramMapping) {
        Session session = HibernateUtil.getSessionPicoFactory();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(gfrProgramMapping);
            tx.commit();
        }
        catch (RuntimeException re) {
            if(gfrProgramMapping != null)
                tx.rollback();
            throw re;
        }
        finally {
            session.close();
        }
    }

    public GfrProgramMapping findBygfrProgramMappingId(Integer gpmId) {
        Session session = HibernateUtil.getSessionPicoFactory();
        GfrProgramMapping gfrProgramMapping  = new GfrProgramMapping ();

        try {
            session.beginTransaction();
            gfrProgramMapping  = (GfrProgramMapping) session.load(GfrProgramMapping.class , gpmId);

            Hibernate.initialize(gfrProgramMapping); //.getImName());

            return gfrProgramMapping;
        }
        finally {
            session.close();
            }
        }

//    public GfrProgramMapping findByProgramId(int programId) {
//        Session session = HibernateUtil.getSession();
//        GfrProgramMapping gfrProgramMapping  = new GfrProgramMapping ();
//
//        try {
//            session.beginTransaction();
//        List<GfrProgramMapping> gfrProgramMappingList  =  session.createQuery("Select u from GfrProgramMapping  u where u.erpmprogram.erpmpId  = :programId)").setParameter(programId, programId).list();
//
//            Hibernate.initialize(gfrProgramMappingList);
//
//            return gfrProgramMapping;
//        }
//        finally {
//            session.close();
//            }
//        }


    public List <GfrProgramMapping> findByProgramId(Short programId) {
            Session session = HibernateUtil.getSessionPicoFactory();
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                List<GfrProgramMapping> erpmuserList  =  session.createQuery("Select u from GfrProgramMapping  u where u.erpmprogram.erpmpId  = :programId)").setParameter("programId", programId).list();
                for (int index=0; index < erpmuserList.size(); ++index)
                        Hibernate.initialize(erpmuserList.get(index).getGfrMaster());

                return erpmuserList;
            }
            catch (RuntimeException re) {
                throw re;
            }
            finally {
                session.close();
            }
     }


    public Integer findCountByProgramId(Short programId){
        Session session = HibernateUtil.getSessionPicoFactory();
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                String list = session.createQuery("select count(u) from GfrProgramMapping  u where u.erpmprogram.erpmpId  = :programId)").setParameter("programId", programId).uniqueResult().toString();

                return Integer.parseInt(list);
                }
            catch (RuntimeException re) {
                throw re;
                }
            finally {
                session.close();
            }
        }
    
   }
