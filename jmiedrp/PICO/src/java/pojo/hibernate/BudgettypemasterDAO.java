/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pojo.hibernate;

import utils.BaseDAO;
import java.util.List;

/**
 *
 * @author kazim
 */
public class BudgettypemasterDAO  extends BaseDAO {

    public void save(Budgettypemaster btm) {
        try {
            beginTransaction();
            getSession().save(btm);           
            commitTransaction();            
        }
        catch (RuntimeException re) {
            re.printStackTrace();
            throw re;        
        }
    }

    public void update(Budgettypemaster btm) {
        try {
            beginTransaction();
            getSession().update(btm);
            commitTransaction();
        }
        catch (RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
    }

    public void delete(Budgettypemaster btm) {
        try {
            beginTransaction();
            getSession().delete(btm);
            commitTransaction();
        }
        catch (RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
    }

    public List<Budgettypemaster> findAll() {
        beginTransaction();
        List<Budgettypemaster> list = getSession().createQuery("select u from Budgettypemaster u").list();
        commitTransaction();
        return list;
    }

    public Budgettypemaster findBybtmId(Byte btmId)
    {
        beginTransaction();
        //Budgettypemaster btm  = new Budgettypemaster ();
        Budgettypemaster btm  = (Budgettypemaster) getSession().load(Budgettypemaster.class , btmId);
        commitTransaction();
        return btm;
    }
}