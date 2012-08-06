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

public class GenericroleprivilegesDAO extends BaseDAO {

    public void save(Genericroleprivileges grp) {
        try {
            beginTransaction();
            getSession().save(grp);
            commitTransaction();
        }
        catch (RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
    }

     public void update(Genericroleprivileges grp) {
        try {
            beginTransaction();
            getSession().update(grp);
            commitTransaction();
        }
        catch (RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
    }

    public void delete(Genericroleprivileges grp) {
        try {
            beginTransaction();
            getSession().delete(grp);
            commitTransaction();
        }
        catch (RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
    }

    public List<Genericroleprivileges> RetrievePrivilegesForGenericRole(Byte gurId) {
        beginTransaction();
        List<Genericroleprivileges> plist = getSession().createQuery("from Genericroleprivileges u where u.genericuserroles.gurId = :gurId")
                                                  .setParameter("gurId", gurId).list();
        commitTransaction();
        return plist;
    }

}