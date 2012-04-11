/*package pojo.hibernate;

import utils.BaseDAO;
import java.util.List;

public class InstitutiontypeDeleteDAO extends BaseDAO {
    public void save(InstitutiontypeDelete it) {
        try {
            beginTransaction();
            getSession().save(it);
            commitTransaction();
        }
        catch (RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
    }

    public List<InstitutiontypeDelete> findAll() {
        beginTransaction();
        List<InstitutiontypeDelete> list = getSession().createQuery("from Institutiontype").list();
        commitTransaction();
        return list;
    }

    public List<InstitutiontypeDelete> findByITID(Byte itTypeId) {
        beginTransaction();
        List<InstitutiontypeDelete> list = getSession().createQuery("select s from Institutiontype s where s.itTypeId = :itTypeId")
                .setParameter("itTypeId", itTypeId)
                .list();
        commitTransaction();
        return list;
}

}
 *
 *
 */