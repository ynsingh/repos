/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


package pojo.hibernate;

import utils.BaseDAO;
import java.util.List;

/**
 *
 * @author afreen
 */

public class CountrymasterDAO extends BaseDAO {


  public void save(Countrymaster cun) {
        try {
            beginTransaction();
            getSession().save(cun);
            commitTransaction();
        }
        catch (RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
    }

    public List<Countrymaster> findAll() {
        beginTransaction();
        List<Countrymaster> list = getSession().createQuery("from Countrymaster").list();
        commitTransaction();
        return list;
    }

public Byte findCountry (String countryName) {
        beginTransaction();        
        List<Countrymaster> list = getSession().createQuery("Select u from Countrymaster u where u.countryName = :countryName").setParameter("countryName",countryName).list();
        commitTransaction();
        return list.get(0).getCountryId();
    }

}
