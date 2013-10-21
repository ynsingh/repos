package pojo.hibernate;
/**
 *
 * @author Tanvir Ahmed
 */
import java.util.List;
import utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Hibernate;

public class WorkflowactionsDAO {

    public void save(Workflowactions wfa) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(wfa);

            tx.commit();
        } catch (RuntimeException re) {
            if (wfa != null) {
                tx.rollback();
            }
            throw re;
        } finally {
            session.close();
        }
    }

    public void update(Workflowactions wfa) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(wfa);
            tx.commit();
        } catch (RuntimeException re) {
            if (wfa != null) {
                tx.rollback();
            }
            throw re;
        } finally {
            session.close();
        }
    }

    public void delete(Workflowactions wfa) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(wfa);
            tx.commit();
        } catch (RuntimeException re) {
            if (wfa != null) {
                tx.rollback();
            }
            throw re;
        } finally {
            session.close();
        }
    }

    public List<Workflowactions> findAll() {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            List<Workflowactions> list = session.createQuery("from Workflowactions").list();

            return list;
        } finally {
            session.close();
        }
    }

    public Workflowactions findWorkflowactionsById(Integer wfaId) {


        Session session = HibernateUtil.getSession();
        try {

            String SQL = "select u from Workflowactions u where u.wfaId= :wfaId";
            session.beginTransaction();
            List<Workflowactions> wfa = session.createQuery(SQL).setParameter("wfaId", wfaId).list();

            return wfa.get(0);
        } finally {
            session.close();
        }
    }

    public List<Workflowactions> findWorkFlowActionsForWFD(Integer wfdId) {

        Session session = HibernateUtil.getSession();
        try {
            String SQL = "select u from Workflowactions u where u.workflowdetail.wfdId= :wfdId";
            int index = 0;
            session.beginTransaction();
            List<Workflowactions> wfaList = session.createQuery(SQL).setParameter("wfdId", wfdId).list();
            for (index=0; index < wfaList.size(); ++index) {
                Hibernate.initialize(wfaList.get(index).getErpmGenMaster());
            }

            return wfaList;
        } finally {
            session.close();
        }
    }

    //The method below prepares a list of actions permitted for a given work flow at a given stage
    public List<Workflowactions> getStageWorkFlowActions(Integer wfmId, int wfdStage) {

        Session session = HibernateUtil.getSession();
        try {

            String SQL = "select u from Workflowactions u where u.workflowdetail.wfdId = "
                    + "(select v.wfdId from Workflowdetail v where "
                    + "v.workflowmaster.wfmId = :wfmId and "
                    + "v.wfdStage = :wfdStage)";
            session.beginTransaction();
            List<Workflowactions> wfaList = session.createQuery(SQL).
                    setParameter("wfmId", wfmId).
                    setParameter("wfdStage", wfdStage).list();
            int index = 0;
            for (index=0; index < wfaList.size(); ++index) {
                Hibernate.initialize(wfaList.get(index).getWorkflowdetail().getCommitteemasterByWfdSourceCommittee());
                Hibernate.initialize(wfaList.get(index).getWorkflowdetail().getCommitteemasterByWfdDestinationCommittee());
                Hibernate.initialize(wfaList.get(index).getErpmGenMaster());
                Hibernate.initialize(wfaList.get(index).getWorkflowdetail().getWorkflowmaster());
            }

            return wfaList;
        } finally {
            session.close();
        }
    }
}
