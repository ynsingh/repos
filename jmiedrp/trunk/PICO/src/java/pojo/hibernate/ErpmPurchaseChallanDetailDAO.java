/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pojo.hibernate;

import utils.BaseDAO;
import java.util.List;
import java.util.*;

/**
 *
 * @author Tanvir Ahmed and Sajid
 */

public class ErpmPurchaseChallanDetailDAO extends BaseDAO {

public void save(ErpmPurchasechallanDetail PCDetail) {
        try {
            beginTransaction();
            getSession().save(PCDetail);
            commitTransaction();
        }
        catch (RuntimeException re) {
            re.printStackTrace();
            throw re;    }
    }
public void delete(ErpmPurchasechallanDetail PCDetail) {
        try {
            beginTransaction();
            getSession().delete(PCDetail);
            commitTransaction();
        }
        catch (RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
    }
 public void update(ErpmPurchasechallanDetail PCDetail) {
        try {
            beginTransaction();
            getSession().update(PCDetail);
            commitTransaction();
        }
        catch (RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
    }

public List<ErpmPurchasechallanDetail> findAll() {
        beginTransaction();
        List<ErpmPurchasechallanDetail> list = getSession().createQuery("from ErpmPurchasechallanDetail").list();
        commitTransaction();
        return list;
    }


  public List<ErpmPurchasechallanDetail>  findBypcmPcmId(Integer pcmPcmId) {
        beginTransaction();
        List<ErpmPurchasechallanDetail> podetailslist  = getSession().createQuery("Select u from ErpmPurchasechallanDetail u where u.erpmPurchasechallanMaster.pcmPcmId = :pcmPcmId").setParameter("pcmPcmId", pcmPcmId).list();
        commitTransaction();
       return podetailslist;
   }

   public ErpmPurchasechallanDetail findBypcdPcdId(Integer pcdPcdId) {
        beginTransaction();
        List<ErpmPurchasechallanDetail> PCDetail  = getSession().createQuery("Select u from ErpmPurchasechallanDetail u where u.pcdPcdId = :pcdPcdId").setParameter("pcdPcdId",pcdPcdId).list();
        commitTransaction();
        return PCDetail.get(0);
    }

 


  public ErpmPurchasechallanDetail findByPCDetailsID(Integer pcdPcdId) {
        beginTransaction();
        ErpmPurchasechallanDetail PCDetail  = (ErpmPurchasechallanDetail) getSession().load(ErpmPurchasechallanDetail.class , pcdPcdId);
        commitTransaction();
        return PCDetail;
    }




  
   public List<ErpmPurchasechallanDetail>  findByPCD_PCMaster_ID(Integer PCD_PCMaster_ID) {
        beginTransaction();
        List<ErpmPurchasechallanDetail> Browselist  = getSession().createQuery("Select u from ErpmPurchasechallanDetail u where u.erpmPurchasechallanMaster.pcmPcmId = :PCD_PCMaster_ID").setParameter("PCD_PCMaster_ID", PCD_PCMaster_ID).list();
        commitTransaction();
        return Browselist;
   }

     public int  findByPCD_PCMaster_IDtest(Integer PCD_PCMaster_ID) {
        beginTransaction();
        List<ErpmPurchasechallanDetail> Browselist  = getSession().createQuery("Select u from ErpmPurchasechallanDetail u where u.erpmPurchasechallanMaster.pcmPcmId = :PCD_PCMaster_ID").setParameter("PCD_PCMaster_ID", PCD_PCMaster_ID).list();
        commitTransaction();
        return Browselist.get(0).getErpmPurchasechallanMaster().getPcmPcmId();
   }

}
