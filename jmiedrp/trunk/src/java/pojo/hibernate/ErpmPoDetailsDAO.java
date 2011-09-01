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
 * @author Sajid Aziz
 */

public class ErpmPoDetailsDAO extends BaseDAO {

public void save(ErpmPoDetails podetail) {
        try {
            beginTransaction();
            getSession().save(podetail);
            commitTransaction();
        }
        catch (RuntimeException re) {
            re.printStackTrace();
            throw re;    }
    }
public void delete(ErpmPoDetails podetail) {
        try {
            beginTransaction();
            getSession().delete(podetail);
            commitTransaction();
        }
        catch (RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
    }
 public void update(ErpmPoDetails podetail) {
        try {
            beginTransaction();
            getSession().update(podetail);
            commitTransaction();
        }
        catch (RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
    }

public List<ErpmPoDetails> findAll() {
        beginTransaction();
        List<ErpmPoDetails> list = getSession().createQuery("from ErpmPoDetails").list();
        commitTransaction();
        return list;
    }


  public List<ErpmPoDetails>  findBypomPoMasterId(Integer pomPoMasterId) {
        beginTransaction();
        List<ErpmPoDetails> podetailslist  = getSession().createQuery("Select u from ErpmPoDetails u where u.erpmPoMaster.pomPoMasterId = :pomPoMasterId").setParameter("pomPoMasterId", pomPoMasterId).list();
        commitTransaction();
       return podetailslist;
   }



  public ErpmPoDetails findByPODetailsID(Integer podPodetailsId) {
        beginTransaction();
        ErpmPoDetails podetail  = (ErpmPoDetails) getSession().load(ErpmPoDetails.class , podPodetailsId);
        commitTransaction();
        return podetail;
    }

   public List<ErpmPoDetails>  findByPOD_POMaster_ID(Integer POD_POMaster_ID) {
        beginTransaction();
        List<ErpmPoDetails> Browselist  = getSession().createQuery("Select u from ErpmPoDetails u where u.erpmPoMaster.pomPoMasterId = :POD_POMaster_ID").setParameter("POD_POMaster_ID", POD_POMaster_ID).list();
        commitTransaction();
        return Browselist;
   }

     public int  findByPOD_POMaster_IDtest(Integer POD_POMaster_ID) {
        beginTransaction();
        List<ErpmPoDetails> Browselist  = getSession().createQuery("Select u from ErpmPoDetails u where u.erpmPoMaster.pomPoMasterId = :POD_POMaster_ID").setParameter("POD_POMaster_ID", POD_POMaster_ID).list();
        commitTransaction();
        return Browselist.get(0).getErpmPoMaster().getPomPoMasterId();
   }







}
