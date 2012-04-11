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

public List<ErpmItemRate> findItemRatesForInstitutionAndItem(Short imId, Integer erpmimId) {
        beginTransaction();
        String SQL = "Select u from ErpmItemRate u "
                    +"where u.institutionmaster.imId = :imId and u.erpmItemMaster.erpmimId = :erpmimId ";

        List<ErpmItemRate> itemRateList  = getSession().createQuery(SQL).setParameter("imId",imId).setParameter("erpmimId",erpmimId).list();

        commitTransaction();
        return itemRateList;
}

//Find items with given item code whose prices have beeen approved in the given date range
public List<ErpmItemRate> findItemApprovedItems(Integer ErpmimId, Date irdWefDate, Integer erpmgmEgmId, Integer smId) {
    
    beginTransaction();
        String SQL = "Select u from ErpmItemRate u "
                    +"where u.erpmItemMaster.erpmimId = :ErpmimId and "
                    +"u.irdWefDate <= :irdWefDate and "
                    +"u.irdWetDate >= :irdWefDate and "
                    +"u.erpmGenMasterByIrCurrencyId.erpmgmEgmId = :erpmgmEgmId and "
                    +"u.suppliermaster.smId = :smId";

        List<ErpmItemRate> itemRateList  =  getSession().createQuery(SQL).
                                                setParameter("ErpmimId",ErpmimId).
                                                setParameter("irdWefDate",irdWefDate).
                                                setParameter("erpmgmEgmId", erpmgmEgmId).
                                                setParameter("smId", smId).list();

        commitTransaction();
        return itemRateList;
}

public List<ErpmItemRate> findItemApprovedItemsForToday(Integer ErpmimId, Date irdWefDate, Integer erpmgmEgmId) {

    beginTransaction();
        String SQL = "Select u from ErpmItemRate u "
                    +"where u.erpmItemMaster.erpmimId = :ErpmimId and "
                    +"u.irdWefDate <= :irdWefDate and "
                    +"u.irdWetDate >= :irdWefDate and "
                    +"u.erpmGenMasterByIrCurrencyId.erpmgmEgmId = :erpmgmEgmId"; //:erpmgmEgmId"; // and "
                    //+"u.suppliermaster.smId = :smId";

        List<ErpmItemRate> itemRateList  =  getSession().createQuery(SQL).
                                                setParameter("ErpmimId",ErpmimId).
                                                setParameter("erpmgmEgmId",erpmgmEgmId).
                                                setTimestamp("irdWefDate",irdWefDate).list();
                                                //setParameter("erpmgmEgmId", erpmgmEgmId).list();//.
                                                //setParameter("smId", smId).list();

        commitTransaction();
        return itemRateList;
}

}
