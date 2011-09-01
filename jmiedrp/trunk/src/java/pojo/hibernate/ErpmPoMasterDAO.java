/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
    /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
 /**
 *
 * @author SajidAziz*/

package pojo.hibernate;
import utils.BaseDAO;
import java.util.List;
import java.util.*;

public class ErpmPoMasterDAO extends BaseDAO {


public void save(ErpmPoMaster pomaster) {
        try {
            beginTransaction();
            getSession().save(pomaster);
            commitTransaction();
        }
        catch (RuntimeException re) {
            re.printStackTrace();
            throw re;    }
    }
public void delete(ErpmPoMaster pomaster) {
        try {
            beginTransaction();
            getSession().delete(pomaster);
            commitTransaction();
        }
        catch (RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
    }
 public void update(ErpmPoMaster pomaster) {
        try {
            beginTransaction();
            getSession().update(pomaster);
            commitTransaction();
        }
        catch (RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
    }

public List<ErpmPoMaster> findAll() {
        beginTransaction();
        List<ErpmPoMaster> list = getSession().createQuery("from ErpmPoMaster").list();
        commitTransaction();
        return list;
    }


 public ErpmPoMaster findBypomPoMasterId(Integer pomPoMasterId) {
        beginTransaction();
        ErpmPoMaster pomaster  = (ErpmPoMaster) getSession().load(ErpmPoMaster.class , pomPoMasterId);
        commitTransaction();
        return pomaster;
    }



 public ErpmPoMaster findByPoMasterId(Integer pomPoMasterId) {
        beginTransaction();
        List<ErpmPoMaster> pomaster  = getSession().createQuery("Select u from ErpmPoMaster u where u.pomPoMasterId = :pomPoMasterId").setParameter("pomPoMasterId",pomPoMasterId).list();
        commitTransaction();
        return pomaster.get(0);
    }

 public List<ErpmPoMaster> findPOForUserDepartments(Integer erpmuId) {
        beginTransaction();
        String SQL = "Select u from ErpmPoMaster u "
                    +"where u.departmentmaster.dmId in "
                    + "(Select d.departmentmaster.dmId from Erpmuserrole d where d.erpmusers.erpmuId = :erpmuId)";

        List<ErpmPoMaster> POlist  = getSession().createQuery(SQL).setParameter("erpmuId",erpmuId).list();
        commitTransaction();
        return POlist;

    }

 }
