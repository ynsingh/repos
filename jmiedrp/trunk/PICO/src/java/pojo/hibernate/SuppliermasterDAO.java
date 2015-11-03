/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pojo.hibernate;

import utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;
import org.hibernate.Hibernate;

import java.util.List;


/**
 *
 * @author afreen
 * @author <a href="mailto:jaivirpal@gmail.com">Jaivir Singh</a>2015
 */
public class SuppliermasterDAO {
    public void save(Suppliermaster  erpmsm) {
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

     public void update(Suppliermaster  erpmsm) {
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

    public void delete(Suppliermaster  erpmsm) {
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

    public List<Suppliermaster > findAll() {
        Session session = HibernateUtil.getSessionPicoFactory();
        try {
            session.beginTransaction();
            List<Suppliermaster > list = session.createQuery("from Suppliermaster ").list();            
            return list;
        }
        finally {
            session.close();
            }        
    }

    public List<Suppliermaster> findForUserInstitutes(Integer erpmuId) {
        String SQL = "Select u from Suppliermaster u where u.institutionmaster.imId  in (select r.institutionmaster.imId from Erpmuserrole r where r.erpmusers.erpmuId = :erpmuId)";
        Session session = HibernateUtil.getSessionPicoFactory();
        try {
            session.beginTransaction();
            List<Suppliermaster > list = session.createQuery(SQL).setParameter("erpmuId",erpmuId).list();
            for(int index = 0; index < list.size(); ++index) {
                Hibernate.initialize(list.get(index).getInstitutionmaster());
                Hibernate.initialize(list.get(index).getErpmGenMasterBySmOwnershipType());
                Hibernate.initialize(list.get(index).getErpmGenMasterBySmSupplierType());
            }
            return list;
        }
        finally {
            session.close();
            }        
        
    }
    
    public Suppliermaster  findByErpmSMId(Integer erpmsmId) {        
        Session session = HibernateUtil.getSessionPicoFactory();
        try {
            session.beginTransaction();
            Suppliermaster  erpmsm  = (Suppliermaster ) session.load(Suppliermaster .class , erpmsmId);
//            Hibernate.initialize(erpmsm);
             Hibernate.initialize(erpmsm.getInstitutionmaster());
             Hibernate.initialize(erpmsm.getErpmGenMasterBySmOwnershipType());
             Hibernate.initialize(erpmsm.getErpmGenMasterBySmSupplierType());            
            return erpmsm;            
        }
        finally {
            session.close();
            }        
    }


 public List<Suppliermaster > findByImId(Short imId) {
        Session session = HibernateUtil.getSessionPicoFactory();
        try {
            session.beginTransaction();
            List<Suppliermaster>  erpmsmList  =  session.createQuery("Select u from Suppliermaster  u where u.institutionmaster.imId = :imId").setParameter("imId", imId).list();
            return erpmsmList;
        }
        finally {
            session.close();
            }        
 }

 public Suppliermaster  findByImnSup(Short imId,String smName) {
        Session session = HibernateUtil.getSessionPicoFactory();
        try {
            session.beginTransaction();
            List<Suppliermaster>  erpmsm  =  session.createQuery("Select u from Suppliermaster  u where u.institutionmaster.imId = :imId and u.smName= :smName").setParameter("imId", imId).setParameter("smName", smName).list();
            return erpmsm.get(0);
        }
        finally {
            session.close();
            }        
 }

 public String findByPANNo(String  panNo, Short imId) {
        Session session = HibernateUtil.getSessionPicoFactory();
        try {
            session.beginTransaction();
            String  supplierName  =  session.createQuery("Select u.smName from Suppliermaster u where u.smPanNo = :panNo and u.institutionmaster.imId = :imId").setParameter("panNo", panNo).setParameter("imId", imId).list().get(0).toString();
            return supplierName;
        }
        finally {
            session.close();
            }       
}

 public String findByTANNo(String  tanNo, Short imId) {
        Session session = HibernateUtil.getSessionPicoFactory();
        try {
            session.beginTransaction();
            String  supplierName  =  session.createQuery("Select u.smName from Suppliermaster u where u.smTanNo = :tanNo  and u.institutionmaster.imId = :imId").setParameter("tanNo", tanNo).setParameter("imId", imId).list().get(0).toString();
            return supplierName;
        }
        finally {
            session.close();
            }        
}
}
