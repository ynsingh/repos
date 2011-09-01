/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pojo.hibernate;

/**
 *
 * @author sknaqvi
 */
import utils.BaseDAO;
import java.util.List;

public class ErpmGenCtrlDao extends BaseDAO {
    public void save(ErpmGenCtrl erpmgctrl) {
        try {
            beginTransaction();
            getSession().save(erpmgctrl);
            commitTransaction();
        }
        catch (RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
    }

     public void update(ErpmGenCtrl erpmgctrl) {
        try {
            beginTransaction();
            getSession().update(erpmgctrl);
            commitTransaction();
        }
        catch (RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
    }

    public void delete(ErpmGenCtrl erpmgctrl) {
        try {
            beginTransaction();
            getSession().delete(erpmgctrl);
            commitTransaction();
        }
        catch (RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
    }
    public List<ErpmGenCtrl> findAll() {
        beginTransaction();
        List<ErpmGenCtrl> list = getSession().createQuery("from ErpmGenCtrl").list();
        commitTransaction();
        return list;
    }

    }
