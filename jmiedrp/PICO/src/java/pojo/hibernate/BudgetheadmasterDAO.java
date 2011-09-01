
package pojo.hibernate;




import utils.BaseDAO;
import java.util.List;

public class BudgetheadmasterDAO  extends BaseDAO {

    public void save(Budgetheadmaster  bhm) {
        try {
            beginTransaction();
            getSession().save(bhm);
            commitTransaction();
            }
        catch (RuntimeException re) {
            re.printStackTrace();
            throw re;
           
        }
    }

    public void update(Budgetheadmaster bhm) {
        try {
            beginTransaction();
            getSession().update(bhm);
            commitTransaction();
        }
        catch (RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
    }

    public void delete(Budgetheadmaster  bhm) {
        try {
            beginTransaction();
            getSession().delete(bhm);
            commitTransaction();
        }
        catch (RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
    }

    public List<Budgetheadmaster > findAll() {
        beginTransaction();
        List<Budgetheadmaster> list = getSession().createQuery("select u from Budgetheadmaster  u").list();
        commitTransaction();
        return list;
    }

    public Budgetheadmaster  findBybhmId(short bhmId)
    {
        beginTransaction();
        Budgetheadmaster  bhm  = (Budgetheadmaster ) getSession().load(Budgetheadmaster .class , bhmId);
        commitTransaction();
        return bhm;
    }
 public List<Budgetheadmaster> findByImId(Short imId) {
        beginTransaction();
        List<Budgetheadmaster> bhmList  =  getSession().createQuery("Select u from Budgetheadmaster u where u.institutionmaster.imId = :imId").setParameter("imId", imId).list();
        commitTransaction();
        return bhmList;
   }

 public List<Budgetheadmaster> findforInsitutetBudgetHeadId(Integer erpmuId) {
        beginTransaction();
        List<Budgetheadmaster> bhmList  =  getSession().createQuery("Select u from Budgetheadmaster  u where u.institutionmaster.imId  in (select r.institutionmaster.imId from Erpmuserrole r where r.erpmusers.erpmuId = :erpmuId)").setParameter("erpmuId",erpmuId).list();
        commitTransaction();
        return bhmList;
   }



}

