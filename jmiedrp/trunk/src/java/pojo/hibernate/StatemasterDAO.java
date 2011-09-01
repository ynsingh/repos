package pojo.hibernate;

import utils.BaseDAO;
import java.util.List;

public class StatemasterDAO extends BaseDAO {

    public void save(Statemaster state) {
        try {
            beginTransaction();
            getSession().save(state);
            commitTransaction();
        } catch (RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
    }

    public List<Statemaster> findAll() {
        beginTransaction();
        List<Statemaster> list = getSession().createQuery("from Statemaster").list();
        commitTransaction();
        return list;
    }

    public List<Statemaster> findByCountryId(Byte countryId) {
        beginTransaction();
        List<Statemaster> smList = getSession().createQuery("Select u from Statemaster u where u.countrymaster.countryId = :countryId").setParameter("countryId", countryId).list();
        commitTransaction();
        return smList;
    }

    public List<Statemaster> findByCountryName(String countryName) {
        beginTransaction();
        List<Statemaster> smList = getSession().createQuery("Select u from Statemaster u where u.countrymaster.countryName = :countryName").setParameter("countryName", countryName).list();
        commitTransaction();
        return smList;
    }

}
