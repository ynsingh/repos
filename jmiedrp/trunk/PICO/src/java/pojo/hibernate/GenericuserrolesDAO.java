/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sknaqvi
 */

package pojo.hibernate;

import utils.BaseDAO;
import java.util.List;

public class GenericuserrolesDAO extends BaseDAO {

    public void save(Genericuserroles gur) {
        try {
            beginTransaction();
            getSession().save(gur);
            commitTransaction();
        }
        catch (RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
    }

     public void update(Genericuserroles gur) {
        try {
            beginTransaction();
            getSession().update(gur);
            commitTransaction();
        }
        catch (RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
    }

    public void delete(Genericuserroles gur) {
        try {
            beginTransaction();
            getSession().delete(gur);
            commitTransaction();
        }
        catch (RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
    }

    public List<Genericuserroles> findAll() {
        beginTransaction();
        List<Genericuserroles> list = getSession().createQuery("from Genericuserroles").list();
        commitTransaction();
        return list;
    }

    public String RetrieveRoleDescription(Byte gurId) {
        beginTransaction();
        List<Genericuserroles> list = getSession().createQuery("from Genericuserroles u where u.gurId = :gurId").setParameter("gurId", gurId).list();
        commitTransaction();
        return list.get(0).getGurDescription();
    }

}