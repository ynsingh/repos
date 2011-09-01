package Junk;

import utils.BaseDAO;
import java.util.List;

public class SubinstitutiontypeDAO extends BaseDAO {
    public void save(Subinstitutiontype sit) {
        try {
            beginTransaction();
            getSession().save(sit);
            commitTransaction();
        }
        catch (RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
    }

    public List<Subinstitutiontype> findAll() {
        beginTransaction();
        List<Subinstitutiontype> list = getSession().createQuery("from Subinstitutiontype").list();
        commitTransaction();
        return list;
    }

    public List<Subinstitutiontype> findByITID(Byte simtTypeId) {
        beginTransaction();
        List<Subinstitutiontype> list = getSession().createQuery("select s from Subnstitutiontype s where s.simtTypeId = :simtTypeId")
                .setParameter("simtTypeId", simtTypeId)
                .list();
        commitTransaction();
        return list;
}

}