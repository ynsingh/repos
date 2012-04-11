/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pojo.hibernate;
import utils.BaseDAO;
import java.util.List;

/**
 *
 * @author erp05
 */
public class WorkflowtransactionDAO extends BaseDAO {

    public void save(Workflowtransaction wft) {
        try {
            beginTransaction();
            getSession().save(wft);
            commitTransaction();
        }
        catch (RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
    }

    public void update(Workflowtransaction wft) {
        try {
            beginTransaction();
            getSession().update(wft);
            commitTransaction();
        }
        catch (RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
    }


    public void delete(Workflowtransaction wft) {
        try {
            beginTransaction();
            getSession().delete(wft);
            commitTransaction();
        }
        catch (RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
    }

    public List<Workflowtransaction> findAll() {
        beginTransaction();
        List<Workflowtransaction> list = getSession().createQuery("from Workflowtransaction").list();
        commitTransaction();
        return list;
    }

    public int findCurrentWFTStage(int wftWorkId) {
        beginTransaction();
        List<Workflowtransaction > wftList = getSession().createQuery("Select u from Workflowtransaction u where u.wftWorkId = :wftWorkId")
                                             .setParameter("wftWorkId",wftWorkId).list();
        commitTransaction();
        return wftList.size();
    }

    public List<ErpmIndentMaster> findWorkflowTransPendingForAction(String destCommitteeMailId) {
        //String SQL = "Select a from Workflowtransaction a where a.wftDestinationEmail = :destCommitteeMailId and a.workflowmaster = 25 and a.wftStage in (select max(b.wftStage) from Workflowtransaction b where b.wftWorkId = a.wftWorkId)
        String SQL =    "Select b from Workflowtransaction a, ErpmIndentMaster b " +
                        "where a.wftWorkId = b.indtIndentId and  a.wftDestinationEmail = :destCommitteeMailId and " +
                        "a.workflowmaster = 25 and a.wftStage in " +
                        "(select max(c.wftStage) from Workflowtransaction c " +
                        "where c.wftWorkId = a.wftWorkId)";
        beginTransaction();

        //SELECT * from `pico_basic`.`workflowtransaction` a where a.wft_destination_email = 'sknaqvi@jmi.ac.in' and a.wft_wfm_id = '25' and a.wft_stage = (select max(b.wft_stage) from `pico_basic`.`workflowtransaction` b where b.wft_work_id = a.wft_work_id)
        List<ErpmIndentMaster> list = getSession().createQuery(SQL)
                                                     .setParameter("destCommitteeMailId",destCommitteeMailId).list();
        commitTransaction();
        return list;
    }

    public int findWorkFlowIDByWorkID(int wftWorkId) {
        beginTransaction();
        List<Workflowtransaction > wftList = getSession().createQuery("Select u from Workflowtransaction u where u.wftWorkId = :wftWorkId")
                                             .setParameter("wftWorkId",wftWorkId).list();
        commitTransaction();
        return wftList.get(0).getWorkflowmaster().getWfmId();
    }

    }


