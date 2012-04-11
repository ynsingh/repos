
package pojo.hibernate;

import utils.BaseDAO;
import java.util.List;

public class ErpmsubmoduleDAO  extends BaseDAO {
    public void save(Erpmsubmodule erpmsm) {
        try {
            beginTransaction();
            getSession().save(erpmsm);
            commitTransaction();
        }
        catch (RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
    }

     public void update(Erpmsubmodule erpmsm) {
        try {
            beginTransaction();
            getSession().update(erpmsm);
            commitTransaction();
        }
        catch (RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
    }

    public void delete(Erpmsubmodule erpmsm) {
        try {
            beginTransaction();
            getSession().delete(erpmsm);
            commitTransaction();
        }
        catch (RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
    }

    public List<Erpmsubmodule> findAll() {
        beginTransaction();
        List<Erpmsubmodule> list = getSession().createQuery("from Erpmsubmodule").list();
        commitTransaction();
        return list;
    }

    public List<Erpmsubmodule> findByModuleId(Byte erpmmId) {
        beginTransaction();
        List<Erpmsubmodule> list = getSession().createQuery("select e from Erpmsubmodule e where e.erpmmodule.erpmmId = :erpmmId order by esmOrder").setParameter("erpmmId", erpmmId).list();
        commitTransaction();
        return list;
    }
}