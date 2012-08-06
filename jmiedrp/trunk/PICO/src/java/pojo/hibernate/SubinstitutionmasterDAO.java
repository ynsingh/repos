/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo.hibernate;

/**
 *
 * @author kazim
 */
import utils.BaseDAO;
import java.util.List;

public class SubinstitutionmasterDAO extends BaseDAO {

    public void save(Subinstitutionmaster sim) {
        try {
            beginTransaction();
            getSession().save(sim);
            commitTransaction();
        } catch (RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
    }

    public void update(Subinstitutionmaster sim) {
        try {
            beginTransaction();
            getSession().update(sim);
            commitTransaction();
        } catch (RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
    }

    public void delete(Subinstitutionmaster sim) {
        try {
            beginTransaction();
            getSession().delete(sim);
            commitTransaction();
        } catch (RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
    }

    public List<Subinstitutionmaster> findAll() {
        beginTransaction();
        List<Subinstitutionmaster> list = getSession().createQuery("select u from Subinstitutionmaster u").list();
        commitTransaction();
        return list;
    }

    public Subinstitutionmaster findBySimId(Integer simId) {
        beginTransaction();
        Subinstitutionmaster sim = new Subinstitutionmaster();
        sim = (Subinstitutionmaster) getSession().load(Subinstitutionmaster.class, simId);
        commitTransaction();
        return sim;
    }

    public List<Subinstitutionmaster> findBysimImId(short simImId) {
        beginTransaction();
        List<Subinstitutionmaster> list = getSession().createQuery("select u from Subinstitutionmaster u where u.institutionmaster.imId = :simImId").setParameter("simImId", simImId).list();
        commitTransaction();
        return list;
    }

public Subinstitutionmaster findInstBySIMShortName(String simShortName) {
        beginTransaction();
 List<Subinstitutionmaster> simList = getSession().createQuery("select distinct(u) from Subinstitutionmaster u where u.simShortName = :simShortName").setParameter("simShortName", simShortName).list();
        commitTransaction();
        return simList.get(0);
    }

public List<Subinstitutionmaster> findSubInstByName(String simName) {
        beginTransaction();
 List<Subinstitutionmaster> simList = getSession().createQuery("select distinct(u) from Subinstitutionmaster u where u.simName = :simName").setParameter("simName", simName).list();
        commitTransaction();
        return simList;
    }

public List<Subinstitutionmaster> findSubInstByShortName(String simShortName) {
        beginTransaction();
 List<Subinstitutionmaster> simList = getSession().createQuery("select distinct(u) from Subinstitutionmaster u where u.simShortName = :simShortName").setParameter("simShortName", simShortName).list();
        commitTransaction();
        return simList;
    }

public List<Subinstitutionmaster> findSubInstForUser(Integer erpmuId,short ImId) {
        beginTransaction();
        List<Subinstitutionmaster> simList = getSession().createQuery("select distinct(u) from Subinstitutionmaster u, Erpmuserrole r where r.erpmusers.erpmuId = :erpmuId and r.subinstitutionmaster.simId = u.simId and u.institutionmaster.imId = :ImId").setParameter("erpmuId", erpmuId).setParameter("ImId",ImId).list();
        commitTransaction();
        return simList;
    }

public String findDefaultSubInsitute(Integer simId) {
        beginTransaction();
        List<Subinstitutionmaster> imList = getSession().createQuery("select u from Subinstitutionmaster u where u.simId = :simId").setParameter("simId",simId).list();
        commitTransaction();
        return imList.get(0).getSimName();
    }

public List<Subinstitutionmaster> findAllSubInstForUser(Integer erpmuId) {
        String SQL = "select distinct(u) from Subinstitutionmaster u, Erpmuserrole r where r.erpmusers.erpmuId = :erpmuId and r.subinstitutionmaster.simId = u.simId";
        beginTransaction();
        List<Subinstitutionmaster> simList = getSession().createQuery(SQL).
                                                          setParameter("erpmuId", erpmuId).list();
        commitTransaction();
        return simList;
    }


}
