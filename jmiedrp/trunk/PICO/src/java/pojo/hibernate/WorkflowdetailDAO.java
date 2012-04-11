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


public class WorkflowdetailDAO extends BaseDAO {


  public void save(Workflowdetail wfd) {
        try {
            beginTransaction();
            getSession().save(wfd);
            commitTransaction();
        }
        catch (RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
    }

    public void update(Workflowdetail wfd) {
        try {
            beginTransaction();
            getSession().update(wfd);
            commitTransaction();
        }
        catch (RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
    }


    public void delete(Workflowdetail wfd) {
        try {
            beginTransaction();
            getSession().delete(wfd);
            commitTransaction();
        }
        catch (RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
    }

    public List<Workflowdetail> findAll() {
        beginTransaction();
        List<Workflowdetail> list = getSession().createQuery("from Workflowdetail").list();
        commitTransaction();
        return list;
    }

    public Workflowdetail findWorkFlowDetailById(Integer wfdId) {
        String SQL =    "select u from Workflowdetail u where u.wfdId= :wfdId";

        beginTransaction();
        List<Workflowdetail> wfd = getSession().createQuery(SQL).setParameter("wfdId", wfdId).list();
        commitTransaction();
        return wfd.get(0);
    }

   public Integer findWorkFlowDetailForWFM(Integer wfmId) {
        String SQL =    "select max(u.wfdStage) from Workflowdetail u where u.workflowmaster.wfmId= :wfmId";
        try {
            beginTransaction();
            Integer wfdStageCount  = Integer.parseInt(getSession().createQuery(SQL).setParameter("wfmId", wfmId).list().get(0).toString());
            commitTransaction();
            return wfdStageCount + 1;
        }
        catch (Exception e)
        {   //This block is to handle no record case 
            return 1;
        }
    }

public List<Workflowdetail> findWorkFlowDetailsForWFM(Integer wfmId) {
        String SQL =    "select u from Workflowdetail u where u.workflowmaster.wfmId= :wfmId";
        beginTransaction();
        List<Workflowdetail> wfdList  = getSession().createQuery(SQL).setParameter("wfmId", wfmId).list();
        commitTransaction();
        return wfdList;
    }

public Committeemaster findSourceCommittee(int stage, Integer wfmId) {
        String SQL =    "select u from Workflowdetail u where u.wfdStage = :stage and u.workflowmaster.wfmId= :wfmId";
        beginTransaction();
        List<Workflowdetail> wfd = getSession().createQuery(SQL).setParameter("stage", stage).setParameter("wfmId", wfmId).list();
        commitTransaction();
        return wfd.get(0).getCommitteemasterByWfdSourceCommittee();
}

public Committeemaster findDestinationCommittee(int stage, Integer wfmId) {
        String SQL =    "select u from Workflowdetail u where u.wfdStage = :stage and u.workflowmaster.wfmId= :wfmId";
        beginTransaction();
        List<Workflowdetail> wfd = getSession().createQuery(SQL).setParameter("stage", stage).setParameter("wfmId", wfmId).list();
        commitTransaction();
        return wfd.get(0).getCommitteemasterByWfdDestinationCommittee();
}


public int findWorkFlowDetailIdByStage(int stage, Integer wfmId) {
        String SQL =    "select u from Workflowdetail u where u.wfdStage = :stage and u.workflowmaster.wfmId= :wfmId";
        beginTransaction();
        List<Workflowdetail> wfd = getSession().createQuery(SQL).setParameter("stage", stage).setParameter("wfmId", wfmId).list();
        commitTransaction();
        return wfd.get(0).getWfdId();
}

}
