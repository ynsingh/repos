/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 *
 * @author Tanvir Ahmed
 */

package pojo.hibernate;
import utils.BaseDAO;
import java.util.List;
import java.util.*;

public class ErpmPurchaseChallanMasterDAO extends BaseDAO {


//public List<ErpmPurchasechallanMaster> findAll() {
//        beginTransaction();
//        List<ErpmPurchasechallanMaster> list = getSession().createQuery("from ErpmPurchasechallanMaster").list();
//        commitTransaction();
//        return list;
//    }

public void save(ErpmPurchasechallanMaster PChallanMast) {
        try {
            beginTransaction();
            getSession().save(PChallanMast);
            commitTransaction();
        }
        catch (RuntimeException re) {
            re.printStackTrace();
            throw re;    }
    }

public void delete(ErpmPurchasechallanMaster PChallanMast) {
        try {
            beginTransaction();
            getSession().delete(PChallanMast);
            commitTransaction();
        }
        catch (RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
    }
 public void update(ErpmPurchasechallanMaster PChallanMast) {
        try {
            beginTransaction();
            getSession().update(PChallanMast);
            commitTransaction();
        }
        catch (RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
    }

public List<ErpmPurchasechallanMaster> findAll() {
        beginTransaction();
        List<ErpmPurchasechallanMaster> list = getSession().createQuery("from ErpmPurchasechallanMaster").list();
        commitTransaction();
        return list;
    }


 public ErpmPurchasechallanMaster findBypcmPcmId(Integer pcmPcmId) {
        beginTransaction();
        ErpmPurchasechallanMaster PChallanMast  = (ErpmPurchasechallanMaster) getSession().load(ErpmPurchasechallanMaster.class , pcmPcmId);
        commitTransaction();
        return PChallanMast;
    }

 

 public ErpmPurchasechallanMaster findByPcmId(Integer pcmPcmId) {
        beginTransaction();
        List<ErpmPurchasechallanMaster> PChallanMast  = getSession().createQuery("Select u from ErpmPurchasechallanMaster u where u.pcmPcmId = :pcmPcmId").setParameter("pcmPcmId",pcmPcmId).list();
        commitTransaction();
        return PChallanMast.get(0);
    }

 public List<ErpmPurchasechallanMaster> findPOForUserDepartments(Integer erpmuId) {
        beginTransaction();
        String SQL = "Select u from ErpmPurchasechallanMaster u "
                    +"where u.departmentmaster.dmId in "
                    + "(Select d.departmentmaster.dmId from Erpmuserrole d where d.erpmusers.erpmuId = :erpmuId)";

        List<ErpmPurchasechallanMaster> POlist  = getSession().createQuery(SQL).setParameter("erpmuId",erpmuId).list();
        commitTransaction();
        return POlist;

    }

}
