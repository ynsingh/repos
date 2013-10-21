package pojo.hibernate;

import utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;
import org.hibernate.Hibernate;

public class ErpmItemMasterDAO {
    
    public void save(ErpmItemMaster erpmim) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(erpmim);
            tx.commit();
        }
        catch (RuntimeException re) {
            if(erpmim != null)
                tx.rollback();
            throw re;
        }
        finally {
            session.close();
        }
    }

     public void update(ErpmItemMaster erpmim) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(erpmim);
            tx.commit();
        }
        catch (RuntimeException re) {
            if(erpmim != null)
                tx.rollback();
            throw re;
        }
        finally {
            session.close();
        }
    }

    public void delete(ErpmItemMaster erpmim) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(erpmim);
            tx.commit();
        }
        catch (RuntimeException re) {
            if(erpmim != null)
                tx.rollback();
            throw re;
        }
        finally {
            session.close();
        }
    }

    public List<ErpmItemMaster> findByImId(Short imId) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            List<ErpmItemMaster> erpmimList  = session.createQuery("Select u from ErpmItemMaster u where u.institutionmaster.imId = :imId").setParameter("imId",imId).list();            
            return erpmimList;
        }
        finally {
            session.close();
            }                
}


     public List<ErpmItemMaster> findItemsForSupplier(Short imId, String smName) {
         String SQL =   "Select u from ErpmItemMaster u where " +
                        "u.institutionmaster.imId = :imId and u.erpmimId  in " +
                        "(Select v.erpmItemMaster.erpmimId from ErpmItemRate v where v.suppliermaster.smName = :smName)";
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            List<ErpmItemMaster> erpmimList  = session.createQuery(SQL).
                                                        setParameter("imId",imId).
                                                        setParameter("smName",smName).list();
            return erpmimList;
        }
        finally {
            session.close();
            }                

     }

     public ErpmItemMaster findByErpmimId(Integer erpmimId) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            ErpmItemMaster erpmIM  = (ErpmItemMaster) session.load(ErpmItemMaster.class , erpmimId);
            Hibernate.initialize(erpmIM.getInstitutionmaster());
            Hibernate.initialize(erpmIM.getErpmItemCategoryMasterByErpmimItemCat1());
            Hibernate.initialize(erpmIM.getErpmItemCategoryMasterByErpmimItemCat2());
            Hibernate.initialize(erpmIM.getErpmItemCategoryMasterByErpmimItemCat3());
            Hibernate.initialize(erpmIM.getErpmGenMaster());
            
            return erpmIM;
        }
        finally {
            session.close();
            }                
    }

    public List<ErpmItemMaster> findItemsForUserInstitutes(Integer erpmuId) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            List<ErpmItemMaster> erpmimList  = session.createQuery("Select u from ErpmItemMaster u where u.institutionmaster.imId  in (select r.institutionmaster.imId from Erpmuserrole r where r.erpmusers.erpmuId = :erpmuId)").setParameter("erpmuId",erpmuId).list();
            for(int index = 0; index < erpmimList.size(); ++index) {
                Hibernate.initialize(erpmimList.get(index).getInstitutionmaster());
                Hibernate.initialize(erpmimList.get(index).getErpmItemCategoryMasterByErpmimItemCat1());
                Hibernate.initialize(erpmimList.get(index).getErpmItemCategoryMasterByErpmimItemCat2());
                Hibernate.initialize(erpmimList.get(index).getErpmCapitalCategory());                
                Hibernate.initialize(erpmimList.get(index).getErpmGenMaster());                
                Hibernate.initialize(erpmimList.get(index).getErpmimDepreciationMethod());  
                Hibernate.initialize(erpmimList.get(index).getErpmimDepreciationPercentage()); 
                Hibernate.initialize(erpmimList.get(index).getErpmimResidualValue());    
            }
            return erpmimList;
        }
        finally {
            session.close();
            }                

}
     public List<ErpmItemMaster> findByerpmItemCategoryMaster(Integer erpmim) {        
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            List<ErpmItemMaster> erpmicmList  = session.createQuery("Select u from ErpmItemMaster u where  u.erpmItemCategoryMasterByErpmimItemCat3.erpmicmItemId=:erpmim").setParameter("erpmim", erpmim).list();
            return erpmicmList;
        }
        finally {
            session.close();
            }                

}

    public Boolean hasDuplicate(String erpmuItemBriefDesc, Short imId) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            List<ErpmItemMaster> erpmimList  = session.createQuery("Select u from ErpmItemMaster u where u.erpmimItemBriefDesc = :erpmuItemBriefDesc and u.institutionmaster.imId = :imId").setParameter("erpmuItemBriefDesc",erpmuItemBriefDesc).setParameter("imId",imId).list();
            if (erpmimList.isEmpty())
                return Boolean.FALSE;
            else
                return Boolean.TRUE;
        }
        finally {
            session.close();
            }                

    }

    public ErpmItemMaster findByItemId(Integer itemId){        
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            List<ErpmItemMaster> list  =  session.createQuery("Select u from ErpmItemMaster u where u.erpmimId = :itemId").setParameter("itemId", itemId).list();
            return list.get(0);
        }
        finally {
            session.close();
            }                
  }

}