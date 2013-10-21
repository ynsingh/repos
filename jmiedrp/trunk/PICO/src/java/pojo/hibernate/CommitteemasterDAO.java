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

public class CommitteemasterDAO {


  public void save(Committeemaster cm) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(cm);
            tx.commit();
        }
        catch (RuntimeException re) {
            if(cm != null)
                tx.rollback();
            throw re;
        }
        finally {
            session.close();
        }
    }

    public void update(Committeemaster cm) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(cm);
            tx.commit();
        }
        catch (RuntimeException re) {
            if(cm != null)
                tx.rollback();
            throw re;
        }
        finally {
            session.close();
        }
    }


    public void delete(Committeemaster cm) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(cm);
            tx.commit();
        }
        catch (RuntimeException re) {
            if(cm != null)
                tx.rollback();
            throw re;
        }
        finally {
            session.close();
        }
    }

    public List<Committeemaster> findAll() {                
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            List<Committeemaster> list = session.createQuery("from Committeemaster").list();
            return list;
        }
        finally {
            session.close();
            }
    }

    public List<Committeemaster> findCommittees(Short imId, Integer simId, Integer dmId) {
        String SQL =    "select u from Committeemaster u where u.institutionmaster.imId = :imId and "
                        + "u.subinstitutionmaster.simId = :simId and "
                        + "u.departmentmaster.dmId = :dmId";
                
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            List<Committeemaster> cmList = session.createQuery(SQL)
                                                  .setParameter("imId", imId)
                                                  .setParameter("simId", simId)
                                                  .setParameter("dmId", dmId)
                                                  .list();  
            for (int index=0; index < cmList.size(); ++index) {
                Hibernate.initialize(cmList.get(index).getInstitutionmaster());
                Hibernate.initialize(cmList.get(index).getSubinstitutionmaster());
                Hibernate.initialize(cmList.get(index).getDepartmentmaster());
                Hibernate.initialize(cmList.get(index).getErpmGenMaster());
            }            
            return cmList;
        }
        finally {
            session.close();
            }
    }

    public List<Committeemaster> findCommittees(Short imId, Integer simId) {
        String SQL =    "select u from Committeemaster u where u.institutionmaster.imId = :imId and "
                        + "u.subinstitutionmaster.simId = :simId ";        
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            List<Committeemaster> cmList = session.createQuery(SQL).setParameter("imId", imId).setParameter("simId", simId).list();
            for (int index=0; index < cmList.size(); ++index) {
                Hibernate.initialize(cmList.get(index).getInstitutionmaster());
                Hibernate.initialize(cmList.get(index).getSubinstitutionmaster());
                Hibernate.initialize(cmList.get(index).getDepartmentmaster());
                Hibernate.initialize(cmList.get(index).getErpmGenMaster());
            }

            return cmList;
        }
        finally {
            session.close();
            }
    }

    

    public List<Committeemaster> findCommittees(Short imId) {
        String SQL =    "select u from Committeemaster u where u.institutionmaster.imId = :imId";
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            List<Committeemaster> cmList = session.createQuery(SQL).setParameter("imId", imId).list();
            for (int index=0; index < cmList.size(); ++index) {
                Hibernate.initialize(cmList.get(index).getInstitutionmaster());
                Hibernate.initialize(cmList.get(index).getSubinstitutionmaster());
                Hibernate.initialize(cmList.get(index).getDepartmentmaster());
                Hibernate.initialize(cmList.get(index).getErpmGenMaster());
            }
            return cmList;
        }
        finally {
            session.close();
            }
    }
        


    public Committeemaster findCommitteeById(Integer committeeId) {
        String SQL =    "select u from Committeemaster u where u.committeeId = :committeeId";                     
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            List<Committeemaster> cm = session.createQuery(SQL).setParameter("committeeId", committeeId).list();
            return cm.get(0);
        }
        finally {
            session.close();
            }
    }

    public List<Committeemaster> findCommitteeByLevel(Character committeeLevel) {
        String SQL =    "select u from Committeemaster u where u.committeeLevel = :committeeLevel";               
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            List<Committeemaster> cm = session.createQuery(SQL).setParameter("committeeLevel", committeeLevel).list();
            return cm;
        }
        finally {
            session.close();
            }        
    }

   public List<Committeemaster> findCommitteeByInstitution(Short imId) {
        String SQL =    "select u from Committeemaster u where u.institutionmaster.imId = :imId";
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            List<Committeemaster> cm = session.createQuery(SQL).setParameter("imId", imId).list();
               return cm;
        }
        finally {
            session.close();
            }        
   }

   public List<Committeemaster> findCommitteeBySubInstitution(Integer simId) {
        String SQL =    "select u from Committeemaster u where u.subinstitutionmaster.simId = :simId";
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            List<Committeemaster> cm = session.createQuery(SQL).setParameter("simId", simId).list();
            return cm;
        }
        finally {
            session.close();
            }        
   }

   public List<Committeemaster> findCommitteeByDepartment(Integer dmId) {
        String SQL =    "select u from Committeemaster u where u.departmentmaster.dmId = :dmId";
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            List<Committeemaster> cm = session.createQuery(SQL).setParameter("dmId", dmId).list();
            return cm;
        }
        finally {
            session.close();
            }        
    }
}
