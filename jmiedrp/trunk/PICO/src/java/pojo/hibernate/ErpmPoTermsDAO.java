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
 * @author dell
 */
public class ErpmPoTermsDAO extends BaseDAO {
    public void save(ErpmPoTerms epoterms) {
        try {
            beginTransaction();
            getSession().save(epoterms);
            commitTransaction();
        }
        catch (RuntimeException re) {
            re.printStackTrace();
            throw re;    }
    }
public void delete(ErpmPoTerms epoterms) {
        try {
            beginTransaction();
            getSession().delete(epoterms);
            commitTransaction();
        }
        catch (RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
    }
 public void update(ErpmPoTerms epoterms) {
        try {
            beginTransaction();
            getSession().update(epoterms);
            commitTransaction();
        }
        catch (RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
    }
 public ErpmPoTerms findByPotpoId(Integer potPotId) {
        beginTransaction();
        List<ErpmPoTerms> terms  = getSession().createQuery("Select u from ErpmPoTerms u where u.potPotId = :potPotId").setParameter("potPotId",potPotId).list();
        commitTransaction();
        return terms.get(0);
    }

 public ErpmPoTerms findBypotPotIds(Integer potPotId) {
        beginTransaction();
        ErpmPoTerms terms  = (ErpmPoTerms) getSession().load(ErpmPoTerms.class , potPotId);
        commitTransaction();
        return terms;
    }

  public List<ErpmPoTerms>  findByindtIndentId(Integer potPotId) {
        beginTransaction();
        List<ErpmPoTerms> epoterms  = getSession().createQuery("Select u from ErpmPoTerms u where u.potPotId.potPotId = :potPotId").setParameter("potPotId", potPotId ).list();
        commitTransaction();
        return epoterms;
   }

 public List<ErpmPoTerms> findAll() {
        beginTransaction();
        List<ErpmPoTerms> list = getSession().createQuery("from ErpmPoTerms").list();
        commitTransaction();
        return list;
    }


  public List<ErpmPoTerms>  findBypotid(Integer potPotId) {
        beginTransaction();
        List<ErpmPoTerms> termslist  = getSession().createQuery("Select u from ErpmPoTerms u where u.potPotId = :potPotId").setParameter("potPotId",potPotId).list();
        commitTransaction();
        return termslist;
   }
public List<ErpmPoTerms>  findBytest(Integer pomPoMasterId) {
        beginTransaction();
        List<ErpmPoTerms> epoterms  = getSession().createQuery("Select u from ErpmPoTerms u where u.erpmPoMaster.pomPoMasterId = :pomPoMasterId").setParameter("pomPoMasterId", pomPoMasterId ).list();
        commitTransaction();
        return epoterms;
   }

}
