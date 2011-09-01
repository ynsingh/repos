/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
 /**
 *
 * @author SajidAziz
 */
package pojo.hibernate;
import utils.BaseDAO;
import java.util.List;
import java.util.*;
/**
 *
 * @author dell
 */
public class ErpmItemRateDAO extends BaseDAO {

public void save(ErpmItemRate itemrate) {
        try {
            beginTransaction();
            getSession().save(itemrate);
            commitTransaction();
        }
        catch (RuntimeException re) {
            re.printStackTrace();
            throw re;    }
    }
public void delete(ErpmItemRate itemrate) {
        try {
            beginTransaction();
            getSession().delete(itemrate);
            commitTransaction();
        }
        catch (RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
    }
 public void update(ErpmItemRate itemrate) {
        try {
            beginTransaction();
            getSession().update(itemrate);
            commitTransaction();
        }
        catch (RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
    }

public List<ErpmItemRate> findAll() {
        beginTransaction();
        List<ErpmItemRate> list = getSession().createQuery("from ErpmItemRate").list();
        commitTransaction();
        return list;
    }

public ErpmItemRate findByirItemRateId(Integer irItemRateId) {
        beginTransaction();
        ErpmItemRate ItemRate  = (ErpmItemRate) getSession().load(ErpmItemRate.class , irItemRateId);
        commitTransaction();
        return ItemRate;
    }

public ErpmItemRate findItemRateId(Integer irItemRateId) {
        beginTransaction();
        List<ErpmItemRate> itemrate  = getSession().createQuery("Select u from ErpmItemRate u where u.irItemRateId = :irItemRateId").setParameter("irItemRateId",irItemRateId).list();
        commitTransaction();
        return itemrate.get(0);
    }
  
 public List<ErpmItemRate> finditemsForUserDepartments(Integer erpmuId) {
        beginTransaction();
        String SQL = "Select u from ErpmItemRate u "
                    +"where u.departmentmaster.dmId in "
                    + "(Select d.departmentmaster.dmId from Erpmuserrole d where d.erpmusers.erpmuId = :erpmuId)";

        List<ErpmItemRate> POlist  = getSession().createQuery(SQL).setParameter("erpmuId",erpmuId).list();
        commitTransaction();
        return POlist;

    }




}
