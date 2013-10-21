/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * @author sknaqvi
 */

package pojo.hibernate;

import utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Hibernate;
import java.util.List;


public class WorkflowmasterDAO {


  public void save(Workflowmaster wfm) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(wfm);
            tx.commit();
        }
        catch (RuntimeException re) {
            if(wfm != null)
                tx.rollback();
            throw re;
        }
        finally {
            session.close();
        }
    }


    public void update(Workflowmaster wfm) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(wfm);
            tx.commit();
        }
        catch (RuntimeException re) {
            if(wfm != null)
                tx.rollback();
            throw re;
        }
        finally {
            session.close();
        }
    }

    public void delete(Workflowmaster wfm) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(wfm);
            tx.commit();
        }
        catch (RuntimeException re) {
            if(wfm != null)
                tx.rollback();
            throw re;
        }
        finally {
            session.close();
        }
    }

    public List<Workflowmaster> findAll() {               
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            List<Workflowmaster> list = session.createQuery("from Workflowmaster").list();
           return list;
        }
        finally {
            session.close();
            }
    }

    public List<Workflowmaster> findWorkFlowRecords(Short imId, Integer simId, Integer dmId) {
        String SQL =    "select u from Workflowmaster u where u.institutionmaster.imId = :imId and "
                        + "u.subinstitutionmaster.simId = :simId and "
                        + "u.departmentmaster.dmId = :dmId";        
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            List<Workflowmaster> wfmList = session.createQuery(SQL)
                                                  .setParameter("imId", imId)
                                                  .setParameter("simId", simId)
                                                  .setParameter("dmId", dmId)
                                                  .list();
            for (int index=0; index < wfmList.size(); ++index) {
                Hibernate.initialize(wfmList.get(index).getInstitutionmaster());
                Hibernate.initialize(wfmList.get(index).getSubinstitutionmaster());
                Hibernate.initialize(wfmList.get(index).getDepartmentmaster());
                Hibernate.initialize(wfmList.get(index).getErpmGenMaster());
            }

            return wfmList;
        }
        finally {
            session.close();
            }
    }

    public List<Workflowmaster> findWorkFlowRecords(Short imId, Integer simId) {
        String SQL =    "select u from Workflowmaster u where u.institutionmaster.imId = :imId and "
                        + "u.subinstitutionmaster.simId = :simId";

        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            List<Workflowmaster> wfmList = session.createQuery(SQL)
                                                  .setParameter("imId", imId)
                                                  .setParameter("simId", simId)
                                                  .list();
            for (int index=0; index < wfmList.size(); ++index) {
                Hibernate.initialize(wfmList.get(index).getInstitutionmaster());
                Hibernate.initialize(wfmList.get(index).getSubinstitutionmaster());
                Hibernate.initialize(wfmList.get(index).getDepartmentmaster());
                Hibernate.initialize(wfmList.get(index).getErpmGenMaster());
            }

            
            return wfmList;
        }
        finally {
            session.close();
            }
    }

    public List<Workflowmaster> findWorkFlowRecords(Short imId) {
        String SQL =    "select u from Workflowmaster u where u.institutionmaster.imId = :imId";
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            List<Workflowmaster> wfmList = session.createQuery(SQL).setParameter("imId", imId).list();
            for (int index=0; index < wfmList.size(); ++index) {
                Hibernate.initialize(wfmList.get(index).getInstitutionmaster());
                Hibernate.initialize(wfmList.get(index).getSubinstitutionmaster());
                Hibernate.initialize(wfmList.get(index).getDepartmentmaster());
                Hibernate.initialize(wfmList.get(index).getErpmGenMaster());
            }
           return wfmList;
        }
        finally {
            session.close();
            }
    }

    public Workflowmaster findWorkFlowById(Integer wfmId) {
        String SQL =    "select u from Workflowmaster u where u.wfmId = :wfmId";       
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            List<Workflowmaster> wfm = session.createQuery(SQL).setParameter("wfmId", wfmId).list();
            return wfm.get(0);
        }
        finally {
            session.close();
            }
    }

    public List<Workflowmaster> findWorkFlowListById(Integer wfmId) {
        String SQL =    "select u from Workflowmaster u where u.wfmId = :wfmId";
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            List<Workflowmaster> wfmList = session.createQuery(SQL).setParameter("wfmId", wfmId).list();
            return wfmList;
        }
        finally {
            session.close();
            }
    }

    public List<Workflowmaster> findByErpmGmID(Integer erpmgmEgmId) {
        String SQL =    "select u from Workflowmaster u where u.erpmGenMaster.erpmgmEgmId = :erpmgmEgmId";

        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            List<Workflowmaster> wfmTypeList = session.createQuery(SQL).setParameter("erpmgmEgmId", erpmgmEgmId).list();
            return wfmTypeList;
        }
        finally {
            session.close();
            }

    }
}
