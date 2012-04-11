/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 *
 * @author SajidAziz
 */
 
package pojo.hibernate;

import utils.BaseDAO;
import java.util.List;
import java.util.*;

public class ErpmItemRateTaxesDAO  extends BaseDAO{

public void save(ErpmItemRateTaxes itemratetax) {
        try {
            beginTransaction();
            getSession().save(itemratetax);
            commitTransaction();
        }
        catch (RuntimeException re) {
            re.printStackTrace();
            throw re;    }
    }
public void delete(ErpmItemRateTaxes itemratetax) {
        try {
            beginTransaction();
            getSession().delete(itemratetax);
            commitTransaction();
        }
        catch (RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
    }
 public void update(ErpmItemRateTaxes itemratetax) {
        try {
            beginTransaction();
            getSession().update(itemratetax);
            commitTransaction();
        }
        catch (RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
    }

public List<ErpmItemRateTaxes> findAll() {
        beginTransaction();
        List<ErpmItemRateTaxes> list = getSession().createQuery("from ErpmItemRateTaxes").list();
        commitTransaction();
        return list;
    }

  public List<ErpmItemRateTaxes>  findByirItemRateId(Integer irItemRateId) {
        beginTransaction();
        List<ErpmItemRateTaxes> itemratetaxList  = getSession().createQuery("Select u from ErpmItemRateTaxes u where u.erpmItemRate.irItemRateId = :irItemRateId").setParameter("irItemRateId", irItemRateId).list();
        commitTransaction();
       return itemratetaxList;
   }

  

   /*public List<ErpmItemRateTaxes>  findByindtDetailsId(Integer irdItemRateDetailsId) {
        beginTransaction();
        List<ErpmItemRateTaxes> indentList  = getSession().createQuery("Select u from erpmItemRate u where u.erpmItemRateDetails.irdItemRateDetailsId = :irdItemRateDetailsId").setParameter("irdItemRateDetailsId", irdItemRateDetailsId).list();
        commitTransaction();
       return indentList;
   }*/

   public ErpmItemRateTaxes findByirtItemRateTaxesId(Integer irtItemRateTaxesId) {
        beginTransaction();
        ErpmItemRateTaxes itemratetax  = (ErpmItemRateTaxes) getSession().load(ErpmItemRateTaxes.class , irtItemRateTaxesId);
        commitTransaction();
        return itemratetax;
    }


}

