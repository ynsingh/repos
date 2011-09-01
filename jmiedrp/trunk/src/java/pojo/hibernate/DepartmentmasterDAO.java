package pojo.hibernate;

import utils.BaseDAO;
import java.util.List;

public class DepartmentmasterDAO extends BaseDAO {
    public void save(Departmentmaster dm) {
        try {
            beginTransaction();
            getSession().save(dm);
            commitTransaction();
        }
        catch (RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
    }

     public void update(Departmentmaster dm) {
        try {
            beginTransaction();
            getSession().update(dm);
            commitTransaction();
        }
        catch (RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
    }

    public void delete(Departmentmaster dm) {
        try {
            beginTransaction();
            getSession().delete(dm);
            commitTransaction();
        }
        catch (RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
    }
    public List<Departmentmaster> findAll() {
        beginTransaction();
        List<Departmentmaster> list = getSession().createQuery("from Departmentmaster").list();
        commitTransaction();
        return list;
    }

    public Departmentmaster findByDmId(Integer dmId) {
        beginTransaction();
        Departmentmaster dm  = (Departmentmaster) getSession().load(Departmentmaster.class , dmId);
        commitTransaction();
        return dm;
}

        public List<Departmentmaster> findBydmSimId(Integer dmSimId) {
        beginTransaction();
        List<Departmentmaster> list = getSession().createQuery("select u from Departmentmaster u where u.subinstitutionmaster.simId = :dmSimId").setParameter("dmSimId", dmSimId).list();
        commitTransaction();
        return list;
    }

public Departmentmaster findDeptByDMShortName(String dmShortName) {
        beginTransaction();
 List<Departmentmaster> dmList = getSession().createQuery("select distinct(u) from Departmentmaster u where u.dmShortName = :dmShortName").setParameter("dmShortName", dmShortName).list();
        commitTransaction();
        return dmList.get(0);
    }


public List<Departmentmaster> findDepartmentForUser(Integer erpmuId,Integer simId) {
        beginTransaction();
        List<Departmentmaster> dimList = getSession().createQuery("select distinct(u) from Departmentmaster u, Erpmuserrole r where r.erpmusers.erpmuId = :erpmuId and r.departmentmaster.dmId = u.dmId and u.subinstitutionmaster.simId =:simId").setParameter("erpmuId", erpmuId).setParameter("simId",simId).list();
        commitTransaction();
        return dimList;
    }

public String findDefaultDepartment(Integer dmId) {
        beginTransaction();
        List<Departmentmaster> dm = getSession().createQuery("select u from Departmentmaster u where u.dmId = :dmId").setParameter("dmId",dmId).list();
        commitTransaction();
        return dm.get(0).getDmName();
    }


}
