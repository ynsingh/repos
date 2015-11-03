/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pojo.hibernate;

import utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Hibernate;
import java.util.List;

/**
 *
 * @author erp05
 * @author <a href="mailto:jaivirpal@gmail.com">Jaivir Singh</a>2015
 */
public class WorkflowtransactionDAO {

    public void save(Workflowtransaction wft) {
        Session session = HibernateUtil.getSessionPicoFactory();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(wft);
            tx.commit();
        } catch (RuntimeException re) {
            if (wft != null) {
                tx.rollback();
            }
            throw re;
        } finally {
            session.close();
        }
    }

    public void update(Workflowtransaction wft) {
        Session session = HibernateUtil.getSessionPicoFactory();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(wft);
            tx.commit();
        } catch (RuntimeException re) {
            if (wft != null) {
                tx.rollback();
            }
            throw re;
        } finally {
            session.close();
        }
    }


    public void delete(Workflowtransaction wft) {
        Session session = HibernateUtil.getSessionPicoFactory();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(wft);
            tx.commit();
        } catch (RuntimeException re) {
            if (wft != null) {
                tx.rollback();
            }
            throw re;
        } finally {
            session.close();
        }
    }

    public List<Workflowtransaction> findAll() {
        Session session = HibernateUtil.getSessionPicoFactory();
        try {
            session.beginTransaction();
            List<Workflowtransaction> list = session.createQuery("from Workflowtransaction").list();

            return list;
        } finally {
            session.close();
        }
    }

    //Prepare list of WorkFlow Transaction for a Given Work Id
    public List<Workflowtransaction> findWFTbyWorkId(int wftWorkId) {
        String Query = "Select u from Workflowtransaction u where u.wftWorkId = :wftWorkId";
        Session session = HibernateUtil.getSessionPicoFactory();
        try {
            session.beginTransaction();
            List<Workflowtransaction> wftList = session.createQuery(Query).setParameter("wftWorkId", wftWorkId).list();
            int index = 0;
            for (index=0; index < wftList.size(); ++index) {
                Hibernate.initialize(wftList.get(index).getCommitteemasterByWftSourceId());
                Hibernate.initialize(wftList.get(index).getCommitteemasterByWftDestinationId());
                Hibernate.initialize(wftList.get(index).getErpmGenMaster());
            }

            return wftList;
        } finally {
            session.close();
        }
    }

    //Count Workfow Transactions list of WorkFlow Transaction for a Given Work Id
    public Integer countWFTbyWorkId(int wftWorkId) {
        String Query = "Select if(max(u.wftId),max(u.wftId),0)+1 from Workflowtransaction u where u.wftWorkId = :wftWorkId";

        Session session = HibernateUtil.getSessionPicoFactory();
        try {
            session.beginTransaction();
            Integer countWFT = Integer.parseInt(session.createQuery(Query).setParameter("wftWorkId", wftWorkId).uniqueResult().toString());

            return countWFT;
        } finally {
            session.close();
        }
    }

    public Workflowtransaction findbyWftId(Integer wftId) {
        Session session = HibernateUtil.getSessionPicoFactory();
        try {
            session.beginTransaction();
            Workflowtransaction wft = (Workflowtransaction) session.createQuery("Select u from Workflowtransaction u where u.wftId = :wftId").setParameter("wftId", wftId);

            return wft;
        } finally {
            session.close();
        }
    }

    /*    public int findCurrentWFTStage(int wftWorkId) {
    beginTransaction();
    List<Workflowtransaction > wftList = getSession().createQuery("Select u from Workflowtransaction u where u.wftWorkId = :wftWorkId")
    .setParameter("wftWorkId",wftWorkId).list();
    commitTransaction();
    return wftList.size();
    }
     */
    //The method below finds the stage of the given indent workflow (Identified by : WorkFlowId and WorkNo
    public Integer findCurrentWFTStage(Integer wfmId, int wftWorkId) {
        String Query = "Select if(max(u.wftStage),max(u.wftStage),0)+1 from Workflowtransaction u where u.workflowmaster.wfmId = :wfmId and u.wftWorkId = :wftWorkId";
        Integer stage;

        Session session = HibernateUtil.getSessionPicoFactory();
        try {
            session.beginTransaction();
            stage = Integer.parseInt(session.createQuery(Query).setParameter("wfmId", wfmId).setParameter("wftWorkId", wftWorkId).uniqueResult().toString());
            return stage;
        } finally {
            session.close();
        }
    }

    public Integer findCurrentWFTStageByWorkId(int wftWorkId) {
        String Query = "Select count(u) from Workflowtransaction u where u.wftWorkId = :wftWorkId";
        Integer stage;

        Session session = HibernateUtil.getSessionPicoFactory();
        try {
            session.beginTransaction();
            stage = Integer.parseInt(session.createQuery(Query).setParameter("wftWorkId", wftWorkId).uniqueResult().toString());
            return stage;
        } finally {
            session.close();
        }
    }

    public String findAuthorisedUser(int wftWorkId) {
        String Query = "Select u.wftDestinationEmail from Workflowtransaction u "
                + "where u.wftWorkId = :wftWorkId and "
                + "u.wftDate = (Select max(v.wftDate) from Workflowtransaction v where v.wftWorkId = :wftWorkId)";
        Session session = HibernateUtil.getSessionPicoFactory();
        try {
            session.beginTransaction();
            String user = session.createQuery(Query).setParameter("wftWorkId", wftWorkId).uniqueResult().toString();


            return user;
        } catch (Exception e) {
            return "No user";
        } finally {
            session.close();
        }

    }

    public List<ErpmIndentMaster> findWorkflowTransPendingForAction(String destCommitteeMailId) {
        //String SQL = "Select a from Workflowtransaction a where a.wftDestinationEmail = :destCommitteeMailId and a.workflowmaster = 25 and a.wftStage in (select max(b.wftStage) from Workflowtransaction b where b.wftWorkId = a.wftWorkId)
        String SQL = "Select b from Workflowtransaction a, ErpmIndentMaster b "
                + "where a.wftWorkId = b.indtIndentId and  a.wftDestinationEmail = :destCommitteeMailId and "
                + "a.workflowmaster = 25 and a.wftStage in "
                + "(select max(c.wftStage) from Workflowtransaction c "
                + "where c.wftWorkId = a.wftWorkId)";
        Session session = HibernateUtil.getSessionPicoFactory();
        try {
            session.beginTransaction();

            List<ErpmIndentMaster> list = session.createQuery(SQL).setParameter("destCommitteeMailId", destCommitteeMailId).list();
            return list;
        } finally {
            session.close();
        }
    }

    public int findWFTByWorkID(int wftWorkId) {
        Session session = HibernateUtil.getSessionPicoFactory();
        try {
            session.beginTransaction();
            List<Workflowtransaction> wftList = session.createQuery("Select u from Workflowtransaction u where u.wftWorkId = :wftWorkId").setParameter("wftWorkId", wftWorkId).list();

            return wftList.get(0).getWorkflowmaster().getWfmId();
        } finally {
            session.close();
        }
    }

    public Integer findWorkFlowID(int wftWorkId) {
        String Query = "Select max(u.workflowmaster.wfmId) from Workflowtransaction u where u.wftWorkId = :wftWorkId";

        Session session = HibernateUtil.getSessionPicoFactory();
        try {
            session.beginTransaction();
            Integer workFlowId = Integer.parseInt(session.createQuery(Query).setParameter("wftWorkId", wftWorkId).uniqueResult().toString());

            return workFlowId;
        } finally {
            session.close();
        }
    }
}


