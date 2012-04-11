/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pojo.hibernate;

import utils.BaseDAO;
import java.util.List;

/**
 *
 * @author sknaqvi
 */
public class CommitteemasterDAO extends BaseDAO {


  public void save(Committeemaster cm) {
        try {
            beginTransaction();
            getSession().save(cm);
            commitTransaction();
        }
        catch (RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
    }

    public void update(Committeemaster cm) {
        try {
            beginTransaction();
            getSession().update(cm);
            commitTransaction();
        }
        catch (RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
    }


    public void delete(Committeemaster cm) {
        try {
            beginTransaction();
            getSession().delete(cm);
            commitTransaction();
        }
        catch (RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
    }

    public List<Committeemaster> findAll() {
        beginTransaction();
        List<Committeemaster> list = getSession().createQuery("from Committeemaster").list();
        commitTransaction();
        return list;
    }

    public List<Committeemaster> findCommittees(Short imId, Integer simId, Integer dmId) {
        String SQL =    "select u from Committeemaster u where u.institutionmaster.imId = :imId and "
                        + "u.subinstitutionmaster.simId = :simId and "
                        + "u.departmentmaster.dmId = :dmId";
        beginTransaction();
        List<Committeemaster> cmList = getSession().createQuery(SQL).setParameter("imId", imId).setParameter("simId", simId).setParameter("dmId", dmId).list();
        commitTransaction();
        return cmList;
    }

    public List<Committeemaster> findCommittees(Short imId, Integer simId) {
        String SQL =    "select u from Committeemaster u where u.institutionmaster.imId = :imId and "
                        + "u.subinstitutionmaster.simId = :simId ";
        beginTransaction();
        List<Committeemaster> cmList = getSession().createQuery(SQL).setParameter("imId", imId).setParameter("simId", simId).list();
        commitTransaction();
        return cmList;
    }

    public List<Committeemaster> findCommittees(Short imId) {
        String SQL =    "select u from Committeemaster u where u.institutionmaster.imId = :imId";
        beginTransaction();
        List<Committeemaster> cmList = getSession().createQuery(SQL).setParameter("imId", imId).list();
        commitTransaction();
        return cmList;
    }


    public Committeemaster findCommitteeById(Integer committeeId) {
        String SQL =    "select u from Committeemaster u where u.committeeId = :committeeId";
        
        beginTransaction();
        List<Committeemaster> cm = getSession().createQuery(SQL).setParameter("committeeId", committeeId).list();
        commitTransaction();
        return cm.get(0);
    }

    public List<Committeemaster> findCommitteeByLevel(Character committeeLevel) {
        String SQL =    "select u from Committeemaster u where u.committeeLevel = :committeeLevel";

        beginTransaction();
        List<Committeemaster> cm = getSession().createQuery(SQL).setParameter("committeeLevel", committeeLevel).list();
        commitTransaction();
        return cm;
    }

   public List<Committeemaster> findCommitteeByInstitution(Short imId) {
        String SQL =    "select u from Committeemaster u where u.institutionmaster.imId = :imId";

        beginTransaction();
        List<Committeemaster> cm = getSession().createQuery(SQL).setParameter("imId", imId).list();
        commitTransaction();
        return cm;
    }

   public List<Committeemaster> findCommitteeBySubInstitution(Integer simId) {
        String SQL =    "select u from Committeemaster u where u.subinstitutionmaster.simId = :simId";

        beginTransaction();
        List<Committeemaster> cm = getSession().createQuery(SQL).setParameter("simId", simId).list();
        commitTransaction();
        return cm;
    }

   public List<Committeemaster> findCommitteeByDepartment(Integer dmId) {
        String SQL =    "select u from Committeemaster u where u.departmentmaster.dmId = :dmId";

        beginTransaction();
        List<Committeemaster> cm = getSession().createQuery(SQL).setParameter("dmId", dmId).list();
        commitTransaction();
        return cm;
    }

}
