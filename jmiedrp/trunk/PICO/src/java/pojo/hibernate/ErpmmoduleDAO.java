
package pojo.hibernate;

import utils.BaseDAO;
import java.util.List;

public class ErpmmoduleDAO  extends BaseDAO {
    public void save(Erpmmodule erpmm) {
        try {
            beginTransaction();
            getSession().save(erpmm);
            commitTransaction();
        }
        catch (RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
    }

     public void update(Erpmmodule erpmm) {
        try {
            beginTransaction();
            getSession().update(erpmm);
            commitTransaction();
        }
        catch (RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
    }

    public void delete(Erpmmodule erpmm) {
        try {
            beginTransaction();
            getSession().delete(erpmm);
            commitTransaction();
        }
        catch (RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
    }

    public List<Erpmmodule> findAll() {
        beginTransaction();
        List<Erpmmodule> list = getSession().createQuery("from Erpmmodule").list();
        commitTransaction();
        return list;
    }

}