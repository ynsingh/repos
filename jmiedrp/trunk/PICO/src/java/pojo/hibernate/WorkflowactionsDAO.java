package pojo.hibernate;
/**
 *
 * @author Tanvir Ahmed
 */
import utils.BaseDAO;
import java.util.List;

public class WorkflowactionsDAO extends BaseDAO {

  public void save(Workflowactions wfa) {
        try {
            beginTransaction();
            getSession().save(wfa);
            commitTransaction();
        }
        catch (RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
    }

    public void update(Workflowactions wfa) {
        try {
            beginTransaction();
            getSession().update(wfa);
            commitTransaction();
        }
        catch (RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
    }

    public void delete(Workflowactions wfa) {
        try {
            beginTransaction();
            getSession().delete(wfa);
            commitTransaction();
        }
        catch (RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
    }

    public List<Workflowactions> findAll() {
        beginTransaction();
        List<Workflowactions> list = getSession().createQuery("from Workflowactions").list();
        commitTransaction();
        return list;
    }

    public Workflowactions findWorkflowactionsById(Integer wfaId) {
        String SQL =    "select u from Workflowactions u where u.wfaId= :wfaId";

        beginTransaction();
        List<Workflowactions> wfa = getSession().createQuery(SQL).setParameter("wfaId", wfaId).list();
        commitTransaction();
        return wfa.get(0);
    }

    public List<Workflowactions> findWorkFlowActionsForWFD(Integer wfdId) {
        String SQL =    "select u from Workflowactions u where u.workflowdetail.wfdId= :wfdId";
        beginTransaction();
        List<Workflowactions> wfaList  = getSession().createQuery(SQL).setParameter("wfdId", wfdId).list();
        commitTransaction();
        return wfaList;
    }

}
