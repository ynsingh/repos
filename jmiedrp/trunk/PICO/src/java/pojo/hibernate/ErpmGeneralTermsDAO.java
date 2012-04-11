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
public class ErpmGeneralTermsDAO extends BaseDAO  {

public void save(ErpmGeneralTerms Gterms) {
        try {
            beginTransaction();
            getSession().save(Gterms);
            commitTransaction();
        }
        catch (RuntimeException re) {
            re.printStackTrace();
            throw re;    }
    }
public void delete(ErpmGeneralTerms Gterms) {
        try {
            beginTransaction();
            getSession().delete(Gterms);
            commitTransaction();
        }
        catch (RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
    }
 public void update(ErpmGeneralTerms Gterms) {
        try {
            beginTransaction();
            getSession().update(Gterms);
            commitTransaction();
        }
        catch (RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
    }

 public ErpmGeneralTerms findPOtermsforInsituteByGenmasterID(Integer erpmgmEgmId, Short imId) {
        beginTransaction();
        List<ErpmGeneralTerms> gterms  = getSession().createQuery("Select u from ErpmGeneralTerms u where u.erpmGenMaster.erpmgmEgmId = :erpmgmEgmId and u.institutionmaster.imId = :imId")
                                            .setParameter("erpmgmEgmId", erpmgmEgmId)
                                            .setParameter("ImId", imId).list();
        commitTransaction();
        return gterms.get(0);
        }

public ErpmGeneralTerms findTestPOtermsforInsituteByGenmasterID(Integer gtGtid) {
        beginTransaction();
        List<ErpmGeneralTerms> gterms  = getSession().createQuery("Select u from ErpmGeneralTerms u where u.gtGtid = :gtGtid").setParameter("gtGtid", gtGtid).list();

        commitTransaction();
        return gterms.get(0);
        }

public ErpmGeneralTerms findTermsforInsituteByGenmasterID(Integer erpmgmEgmId) {
        beginTransaction();
        List<ErpmGeneralTerms> gterms  = getSession().createQuery("Select u from ErpmGeneralTerms u where u.erpmGenMaster.erpmgmEgmId = :erpmgmEgmId").setParameter("erpmgmEgmId", erpmgmEgmId).list();

        commitTransaction();
        return gterms.get(0);
        }


public List<ErpmGeneralTerms> findAll() {
        beginTransaction();
        List<ErpmGeneralTerms> list = getSession().createQuery("from ErpmGeneralTerms").list();
        commitTransaction();
        return list;
    }


public List<ErpmGeneralTerms> findByErpmGmType(short erpmgmEgmType) {
        beginTransaction();
        List<ErpmGeneralTerms> erpmgmlist  = getSession().createQuery("Select u from ErpmGeneralTerms u where u.erpmGenMaster.erpmgcGenType = :erpmgmEgmType").setParameter("erpmgmEgmType",erpmgmEgmType).list();
        commitTransaction();
        return erpmgmlist;
    }

 public List <ErpmGeneralTerms> findPOtermsforInsitute(Short imId) {
        beginTransaction();
        List<ErpmGeneralTerms> gterms  = getSession().createQuery("Select u from ErpmGeneralTerms u where u.institutionmaster.imId = :imId").setParameter("imId", imId).list();
        commitTransaction();
        return gterms;
        }

  public List<ErpmGeneralTerms> findByErpmGmTypebyInsitute(Short imId) {
        beginTransaction();
        List<ErpmGeneralTerms> erpmgmlist  = getSession().createQuery("Select u from ErpmGeneralTerms u where u.institutionmaster.imId=:imId").setParameter("imId", imId).list();
        commitTransaction();
        return erpmgmlist;
    }

  public List<Subinstitutionmaster> findSubInstForUser(Integer erpmuId,short ImId) {
        beginTransaction();
        List<Subinstitutionmaster> simList = getSession().createQuery("select distinct(u) from Subinstitutionmaster u, Erpmuserrole r where r.erpmusers.erpmuId = :erpmuId and r.subinstitutionmaster.simId = u.simId and u.institutionmaster.imId = :ImId").setParameter("erpmuId", erpmuId).setParameter("ImId",ImId).list();
        commitTransaction();
        return simList;
    }


    public ErpmGeneralTerms findBygtGtid(Integer gtGtid) {
        beginTransaction();
        ErpmGeneralTerms GTerms = new ErpmGeneralTerms();
        GTerms = (ErpmGeneralTerms) getSession().load(ErpmGeneralTerms.class, gtGtid);
        commitTransaction();
        return GTerms;
    }

}




