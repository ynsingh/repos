/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



/**
 *
 * @author afreen
 */





package pojo.hibernate;

import utils.BaseDAO;
import java.util.List;

public class DepartmentalBudgetAllocationDAO extends BaseDAO {
    public void save(DepartmentalBudgetAllocation dba) {
        try {
            beginTransaction();
            getSession().save(dba);
            commitTransaction();
        }
        catch (RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
    }

     public void update(DepartmentalBudgetAllocation dba) {
        try {
            beginTransaction();
            getSession().update(dba);
            commitTransaction();
        }
        catch (RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
    }

    public void delete(DepartmentalBudgetAllocation dba) {
        try {
            beginTransaction();
            getSession().delete(dba);
            commitTransaction();
        }
        catch (RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
    }
    public List<DepartmentalBudgetAllocation> findAll() {
        beginTransaction();
        List<DepartmentalBudgetAllocation> list = getSession().createQuery("from DepartmentalBudgetAllocation").list();
        commitTransaction();
        return list;
    }

    public DepartmentalBudgetAllocation findByDbaId(Integer dbaId) {
        beginTransaction();
        DepartmentalBudgetAllocation dba  = (DepartmentalBudgetAllocation) getSession().load(DepartmentalBudgetAllocation.class , dbaId);
        commitTransaction();
        return dba;
}

    public List<DepartmentalBudgetAllocation> findByImId(Short imId) {
        beginTransaction();
        List<DepartmentalBudgetAllocation> dbaList  =  getSession().createQuery("Select u from DepartmentalBudgetAllocation u where u.institutionmaster.imId = :imId").setParameter("imId", imId).list();
        commitTransaction();
        return dbaList;
   }

    

     public List<DepartmentalBudgetAllocation> findBySimId(Integer SimId) {
        beginTransaction();
        List<DepartmentalBudgetAllocation> list = getSession().createQuery("select u from DepartmentalBudgetAllocation  u where u.subinstitutionmaster.simId = :SimId").setParameter("SimId", SimId).list();
        commitTransaction();
        return list;
    }

public List<DepartmentalBudgetAllocation> findByDMId(Integer dmId) {
        beginTransaction();
 List<DepartmentalBudgetAllocation> dbaList = getSession().createQuery("select distinct(u) from DepartmentalBudgetAllocation u where u.departmentmaster.dmId = :dmId").setParameter("dmId", dmId).list();
        commitTransaction();
        return dbaList;
    }
 public DepartmentalBudgetAllocation findForDepartmentBybhmId(short bhmId, Integer dmId) {
        beginTransaction();
        List<DepartmentalBudgetAllocation> dba  = getSession().createQuery("Select u from DepartmentalBudgetAllocation u where u.budgetheadmaster.bhmId = :bhmId and u.departmentmaster.dmId = :dmId")
                                            .setParameter("bhmId", bhmId)
                                            .setParameter("dmId", dmId).list();
        commitTransaction();
        return dba.get(0);
        }

}




