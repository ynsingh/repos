/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * @author sknaqvi
 */

package pojo.hibernate;

import utils.BaseDAO;
import java.util.List;


public class WorkflowmasterDAO extends BaseDAO {


  public void save(Workflowmaster wfm) {
        try {
            beginTransaction();
            getSession().save(wfm);
            commitTransaction();
        }
        catch (RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
    }

    public void update(Workflowmaster wfm) {
        try {
            beginTransaction();
            getSession().update(wfm);
            commitTransaction();
        }
        catch (RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
    }


    public void delete(Workflowmaster wfm) {
        try {
            beginTransaction();
            getSession().delete(wfm);
            commitTransaction();
        }
        catch (RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
    }

    public List<Workflowmaster> findAll() {
        beginTransaction();
        List<Workflowmaster> list = getSession().createQuery("from Workflowmaster").list();
        commitTransaction();
        return list;
    }

    public List<Workflowmaster> findWorkFlowRecords(Short imId, Integer simId, Integer dmId) {
        String SQL =    "select u from Workflowmaster u where u.institutionmaster.imId = :imId and "
                        + "u.subinstitutionmaster.simId = :simId and "
                        + "u.departmentmaster.dmId = :dmId";
        beginTransaction();
        List<Workflowmaster> wfmList = getSession().createQuery(SQL).setParameter("imId", imId).setParameter("simId", simId).setParameter("dmId", dmId).list();
        commitTransaction();
        return wfmList;
    }

    public List<Workflowmaster> findWorkFlowRecords(Short imId, Integer simId) {
        String SQL =    "select u from Workflowmaster u where u.institutionmaster.imId = :imId and "
                        + "u.subinstitutionmaster.simId = :simId";
        beginTransaction();
        List<Workflowmaster> wfmList = getSession().createQuery(SQL).setParameter("imId", imId).setParameter("simId", simId).list();
        commitTransaction();
        return wfmList;
    }

    public List<Workflowmaster> findWorkFlowRecords(Short imId) {
        String SQL =    "select u from Workflowmaster u where u.institutionmaster.imId = :imId";
        beginTransaction();
        List<Workflowmaster> wfmList = getSession().createQuery(SQL).setParameter("imId", imId).list();
        commitTransaction();
        return wfmList;
    }

    public Workflowmaster findWorkFlowById(Integer wfmId) {
        String SQL =    "select u from Workflowmaster u where u.wfmId = :wfmId";

        beginTransaction();
        List<Workflowmaster> wfm = getSession().createQuery(SQL).setParameter("wfmId", wfmId).list();
        commitTransaction();
        return wfm.get(0);
    }

    public List<Workflowmaster> findWorkFlowListById(Integer wfmId) {
        String SQL =    "select u from Workflowmaster u where u.wfmId = :wfmId";

        beginTransaction();
        List<Workflowmaster> wfmList = getSession().createQuery(SQL).setParameter("wfmId", wfmId).list();
        commitTransaction();
        return wfmList;
    }

    public List<Workflowmaster> findByErpmGmID(Integer erpmgmEgmId) {
        String SQL =    "select u from Workflowmaster u where u.erpmGenMaster.erpmgmEgmId = :erpmgmEgmId";

        beginTransaction();
        List<Workflowmaster> wfmTypeList = getSession().createQuery(SQL).setParameter("erpmgmEgmId", erpmgmEgmId).list();
        commitTransaction();
        return wfmTypeList;
    }

}
