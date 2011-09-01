/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pojo.hibernate;

import utils.BaseDAO;
import java.util.List;


/**
 *
 * @author afreen
 */
public class SuppliermasterDAO extends BaseDAO {
public void save(Suppliermaster  erpmsm) {
        try {
            beginTransaction();
            getSession().save(erpmsm);
            commitTransaction();
        }
        catch (RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
    }

     public void update(Suppliermaster  erpmsm) {
        try {
            beginTransaction();
            getSession().update(erpmsm);
            commitTransaction();
        }
        catch (RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
    }

    public void delete(Suppliermaster  erpmsm) {
        try {
            beginTransaction();
            getSession().delete(erpmsm);
            commitTransaction();
        }
        catch (RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
    }
    public List<Suppliermaster > findAll() {
        beginTransaction();
        List<Suppliermaster > list = getSession().createQuery("from Suppliermaster ").list();
        commitTransaction();
        return list;
    }

    public Suppliermaster  findByErpmSMId(Integer erpmsmId) {
        beginTransaction();
       Suppliermaster  erpmsm  = (Suppliermaster ) getSession().load(Suppliermaster .class , erpmsmId);
        commitTransaction();
        return erpmsm;
      }


 public List<Suppliermaster > findByImId(Short imId) {
        beginTransaction();
        List<Suppliermaster>  erpmsmList  =  getSession().createQuery("Select u from Suppliermaster  u where u.institutionmaster.imId = :imId").setParameter("imId", imId).list();
        commitTransaction();
        return erpmsmList;
}

 public Suppliermaster  findByImnSup(Short imId,String smName) {
        beginTransaction();
        List<Suppliermaster>  erpmsm  =  getSession().createQuery("Select u from Suppliermaster  u where u.institutionmaster.imId = :imId and u.smName= :smName").setParameter("imId", imId).setParameter("smName", smName).list();
        commitTransaction();
        return erpmsm.get(0);
}

 public String findByPANNo(String  panNo, Short imId) {
        beginTransaction();
        String  supplierName  =  getSession().createQuery("Select u.smName from Suppliermaster u where u.smPanNo = :panNo and u.institutionmaster.imId = :imId").setParameter("panNo", panNo).setParameter("imId", imId).list().get(0).toString();
        commitTransaction();
        return supplierName;
}

 public String findByTANNo(String  tanNo, Short imId) {
        beginTransaction();
        String  supplierName  =  getSession().createQuery("Select u.smName from Suppliermaster u where u.smTanNo = :tanNo  and u.institutionmaster.imId = :imId").setParameter("tanNo", tanNo).setParameter("imId", imId).list().get(0).toString();
        commitTransaction();
        return supplierName;
}
}
