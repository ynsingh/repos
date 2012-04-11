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
 * @author SajidAziz
 */
package pojo.hibernate;

import utils.BaseDAO;
import java.util.List;
import java.util.*;

public class ErpmItemRateDetailsDAO extends BaseDAO {

public void save(ErpmItemRateDetails itemratedet) {
        try {
            beginTransaction();
            getSession().save(itemratedet);
            commitTransaction();
        }
        catch (RuntimeException re) {
            re.printStackTrace();
            throw re;    }
    }
public void delete(ErpmItemRateDetails itemratedet) {
        try {
            beginTransaction();
            getSession().delete(itemratedet);
            commitTransaction();
        }
        catch (RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
    }
 public void update(ErpmItemRateDetails itemratedet) {
        try {
            beginTransaction();
            getSession().update(itemratedet);
            commitTransaction();
        }
        catch (RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
    }

public List<ErpmItemRateDetails> findAll() {
        beginTransaction();
        List<ErpmItemRateDetails> list = getSession().createQuery("from ErpmItemRateDetails").list();
        commitTransaction();
        return list;
    }

public ErpmItemRateDetails findByirdItemRateDetailsId(Integer irdItemRateDetailsId) {
        beginTransaction();
        ErpmItemRateDetails ItemRatedetails  = (ErpmItemRateDetails) getSession().load(ErpmItemRateDetails.class , irdItemRateDetailsId);
        commitTransaction();
        return ItemRatedetails;
    }



public ErpmItemRateDetails findIndentRateDetailId(Integer irdItemRateDetailsId) {
        beginTransaction();
        List<ErpmItemRateDetails> itemratedet  = getSession().createQuery("Select u from ErpmItemRateDetails u where u.erpmItemRate.irItemRateId = :irdItemRateDetailsId").setParameter("irdItemRateDetailsId",irdItemRateDetailsId).list();
        commitTransaction();
        return itemratedet.get(0);
    }
}
