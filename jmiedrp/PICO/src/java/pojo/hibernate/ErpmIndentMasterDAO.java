
package pojo.hibernate;

import utils.BaseDAO;
import java.util.List;
/**
 *
 * @author Sajid Aziz
 */
public class ErpmIndentMasterDAO extends BaseDAO {
  public void save(ErpmIndentMaster erpmindtmast) {
        try {
            beginTransaction();
            getSession().save(erpmindtmast);
            commitTransaction();
        }
        catch (RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
    }

      public void update(ErpmIndentMaster erpmindtmast) {
        try {
            beginTransaction();
            getSession().update(erpmindtmast);
            commitTransaction();
        }
        catch (RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
    }

    public void delete(ErpmIndentMaster erpmindtmast) {
        try {
            beginTransaction();
            getSession().delete(erpmindtmast);
            commitTransaction();
        }
        catch (RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
    }
public List<ErpmIndentMaster> findIndentsForUserDepartments(Integer erpmuId) {
        beginTransaction();
        String SQL = "Select u from ErpmIndentMaster u "
                    +"where u.departmentmaster.dmId in "
                    + "(Select d.departmentmaster.dmId "
                    + "from Erpmuserrole d "
                    + "where d.erpmusers.erpmuId = :erpmuId)";
                    //+ "AND u.erpmusers.erpmuId =:erpmuId";
        List<ErpmIndentMaster> erpmindtmastList  = getSession().createQuery(SQL).setParameter("erpmuId",erpmuId).list();
        commitTransaction();
        return erpmindtmastList;

    }
public ErpmIndentMaster findIndentMasterId(Short indtIndentId) {
        beginTransaction();
        List<ErpmIndentMaster> erpmindtmast  = getSession().createQuery("Select u from ErpmIndentMaster u where u.indtIndentId = :indtIndentId").setParameter("indtIndentId",indtIndentId).list();
        commitTransaction();
        return erpmindtmast.get(0);
    }


//changes on 17 feb
  public List<ErpmIndentMaster > findAll() {
        beginTransaction();
        List<ErpmIndentMaster > list = getSession().createQuery("from ErpmIndentMaster ").list();
        commitTransaction();
        return list;
    }

  public ErpmIndentMaster findbyforwardedtouserId(Integer erpmuId) {
        beginTransaction();
        List<ErpmIndentMaster> erpmindtmast  = getSession().createQuery("Select u from ErpmIndentMaster u where u.erpmusersByIndtForwardedToUserId = :erpmuId").setParameter("erpmuId",erpmuId).list();
        commitTransaction();
        return erpmindtmast.get(0);
    }

public ErpmIndentMaster findSimIdbyIndentId(Short indtIndentId) {
        beginTransaction();
        List<ErpmIndentMaster> erpmindtmast  = getSession().createQuery("Select u from ErpmIndentMaster u where u.indtIndentId = :indtIndentId").setParameter("indtIndentId",indtIndentId).list();
        commitTransaction();
        return erpmindtmast.get(0);
    }


}





